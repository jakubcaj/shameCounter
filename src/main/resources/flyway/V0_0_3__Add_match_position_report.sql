create or replace view report.match_position_report as
  (
  select
         row_number() over () as id,
         subQ.employee_id,
         subQ.firstname,
         subQ.lastname,
         subQ.player_group_id,
         subQ.player_group_name,
         subQ.season_id,
         subQ.season_name,
         subQ.attacker_count,
         subQ.goalman_count,
         subQ.both_count,
         subQ.attacker_count + subQ.goalman_count + subQ.both_count as total_match_count
  from (select pos_con.employee_id,
               pos_con.firstname,
               pos_con.lastname,
               pg.id                 as player_group_id,
               pg.name               as player_group_name,
               pos_con.season_id,
               pos_con.season_name,
               sum(pos_con.attacker) as attacker_count,
               sum(pos_con.goalman)  as goalman_count,
               sum(pos_con.both_pos) as both_count
        from (select pos_con.employee_id,
                     pos_con.firstname,
                     pos_con.lastname,
                     pos_con.match_id,
                     pos_con.season_id,
                     pos_con.season_name,
                     case
                       when pos_con.attacker_count > 1 and pos_con.goalman_count = 0 then 1
                       else 0 end as attacker,
                     case
                       when pos_con.goalman_count > 1 and pos_con.attacker_count = 0 then 1
                       else 0 end as goalman,
                     case
                       when pos_con.attacker_count > 1 and pos_con.goalman_count > 1 then 1
                       else 0 end as both_pos
              from (select e.id                                                as employee_id,
                           e.firstname,
                           e.lastname,
                           m.id                                                as match_id,
                           s.id                                                as season_id,
                           s.season_name,
                           count(*) filter (where rg.player_role = 'ATTACKER') as attacker_count,
                           count(*) filter (where rg.player_role = 'GOALMAN')  as goalman_count
                    from employee e
                           cross join season s
                           left join helper.round_goals rg on rg.employee_id = e.id
                           left join round r on r.id = rg.round_id
                           left join match m on r.match_id = m.id and m.season_id = s.id and m.finished = true
                    group by e.id, e.firstname, e.lastname, m.id, s.id, s.season_name) pos_con) pos_con
               join employee_player_group epg on epg.employee_id = pos_con.employee_id
               join player_group pg on epg.playergroup_id = pg.id
        group by pos_con.employee_id, pos_con.firstname,
                 pos_con.lastname, pg.id, pg.name, pos_con.season_id, pos_con.season_name) subQ
  order by subQ.season_id desc, subQ.attacker_count + subQ.goalman_count + subQ.both_count desc);