create or replace view report.own_goal_report as
  (select row_number() over () as id,
          e.id                 as employee_id,
          e.firstname,
          e.lastname,
          pg.id                as player_group_id,
          pg.name              as player_group_name,
          s.id                 as season_id,
          s.season_name,
          coalesce(subQ.own_goal_count, 0) as own_goal_count
   from employee e
          cross join season s
          join employee_player_group epg on epg.employee_id = e.id
          join player_group pg on epg.playergroup_id = pg.id
          left join (select
                            m.season_id,
                            tp.employee_id,
                            count(ts.*) filter (where ts.goal_type = 'OWN_GOAL') as own_goal_count
                     from team t
                            join teamplayer tp on t.id = tp.team_id
                            join teamscore ts on ts.team_id = t.id and ts.employee_id = tp.employee_id
                            join round r on t.round_id = r.id
                            join match m on r.match_id = m.id
                     group by m.season_id, tp.employee_id) subQ on subQ.season_id = s.id and subQ.employee_id = e.id
   order by s.id desc, subQ.own_goal_count desc);

create or replace view report.faggy_goal_report as
  (select row_number() over () as id,
          e.id                 as employee_id,
          e.firstname,
          e.lastname,
          pg.id                as player_group_id,
          pg.name              as player_group_name,
          s.id                 as season_id,
          s.season_name,
          coalesce(subQ.faggy_goal_count, 0) as faggy_goal_count
   from employee e
          cross join season s
          join employee_player_group epg on epg.employee_id = e.id
          join player_group pg on epg.playergroup_id = pg.id
          left join (select
                            m.season_id,
                            tp.employee_id,
                            count(ts.*) filter (where ts.goal_type = 'FAGGY_GOAL') as faggy_goal_count
                     from team t
                            join teamplayer tp on t.id = tp.team_id
                            join teamscore ts on ts.team_id = t.id and ts.employee_id = tp.employee_id
                            join round r on t.round_id = r.id
                            join match m on r.match_id = m.id
                     group by m.season_id, tp.employee_id) subQ on subQ.season_id = s.id and subQ.employee_id = e.id
   order by s.id desc, subQ.faggy_goal_count desc);

create or replace view report.stamp_goal_report as
  (select row_number() over () as id,
          e.id                 as employee_id,
          e.firstname,
          e.lastname,
          pg.id                as player_group_id,
          pg.name              as player_group_name,
          s.id                 as season_id,
          s.season_name,
          coalesce(subQ.stamp_goal_count, 0) as stamp_goal_count
   from employee e
          cross join season s
          join employee_player_group epg on epg.employee_id = e.id
          join player_group pg on epg.playergroup_id = pg.id
          left join (select
                            m.season_id,
                            tp.employee_id,
                            count(ts.*) filter (where ts.goal_type = 'STAMP') as stamp_goal_count
                     from team t
                            join teamplayer tp on t.id = tp.team_id
                            join teamscore ts on ts.team_id = t.id and ts.employee_id = tp.employee_id
                            join round r on t.round_id = r.id
                            join match m on r.match_id = m.id
                     group by m.season_id, tp.employee_id) subQ on subQ.season_id = s.id and subQ.employee_id = e.id
   order by s.id desc, subQ.stamp_goal_count desc);