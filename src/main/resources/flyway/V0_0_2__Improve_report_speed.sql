create or replace view report.employee_matches_report as
  (select query.id,
          query.employee_id,
          query.firstname,
          query.lastname,
          query.player_group_id,
          query.player_group_name,
          query.match_id,
          query.season_id,
          query.season_name,
          query.team_mate,
          query.opponents,
          case when query.match_id_c is not null then true else false end as won
   from (select row_number() over ()          as id,
                e.id                          as employee_id,
                e.firstname,
                e.lastname,
                mpg.player_group_id,
                mpg.player_group_name,
                m.id                          as match_id,
                m.season_id,
                s.season_name,
                rg_own.players                as team_mate,
                rg_en.players                 as opponents,
                (select mw.match_id
                 from helper.match_winners mw
                 where mw.match_id = m.id
                   and mw.employee_id = e.id) as match_id_c
         from employee e
                left join (select subQ.employee_id,
                                  subQ.round_id,
                                  string_agg(e.firstname || ' ' || e.lastname, ', ') as players
                           from (select rg.employee_id,
                                        rg.round_id,
                                        rg_own.employee_id as player
                                 from helper.round_goals rg
                                        join helper.round_goals rg_own on rg_own.round_id = rg.round_id and
                                                                          rg_own.team_id = rg.team_id and
                                                                          rg_own.employee_id != rg.employee_id
                                 group by rg.employee_id, rg.round_id,
                                          rg_own.employee_id) subQ
                                  join employee e on e.id = subQ.player
                           group by subQ.employee_id,
                                    subQ.round_id) rg_own on rg_own.employee_id = e.id
                join round r on r.id = rg_own.round_id
                join match m on r.match_id = m.id
                left join season s on m.season_id = s.id
                join helper.match_player_group mpg on mpg.match_id = m.id
                left join helper.round_goals rg on rg.employee_id = e.id and rg.round_id = r.id
                left join (select subQ.round_id,
                                  subQ.team_id,
                                  string_agg(subQ.firstname || ' ' || subQ.lastname, ',') as players
                           from (select rg.round_id,
                                        rg.team_id,
                                        e.firstname,
                                        e.lastname,
                                        row_number() over (partition by rg.round_id, rg.team_id order by e.firstname, e.lastname) as row_number
                                 from helper.round_goals rg
                                        join employee e on rg.employee_id = e.id) subQ
                           where subQ.row_number in (1, 2)
                           group by subQ.round_id, subQ.team_id) rg_en
                  on rg_en.round_id = r.id and rg_en.team_id != rg.team_id
         group by e.id, e.firstname, e.lastname, mpg.player_group_id, mpg.player_group_name, m.id, m.season_id,
                  s.season_name,
                  rg_own.players, rg_en.players) query);