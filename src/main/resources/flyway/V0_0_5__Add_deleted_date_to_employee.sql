create or replace view helper.season_date as
  (select s.id,
          s.season_name,
          s.started_at,
          s2.started_at as ended_at,
          s.active
   from season s
          left join season s2 on s2.id = (select s3.id
                                          from season s3
                                          where s3.started_at > s.started_at
                                          order by s3.started_at
                                          limit 1));

create or replace function display_employee(employee_id bigint, season_id bigint)
  returns boolean
as $$
declare
  tempvar bigint := 0;
begin
  select 1 into tempvar
  from employee e
         cross join helper.season_date s
  where e.id = $1
    and s.id = $2
    and (e.deleted_date is null
           or e.deleted_date > s.ended_at or (e.deleted_date >= s.started_at and e.deleted_date <= s.ended_at) or
         (s.ended_at is null and e.deleted_date > s.started_at));

  if tempvar = 1
  then return true;
  else return false;
  end if;
end; $$
language plpgsql;


create or replace view report.match_win_count_report as
  (select e.id,
          e.firstname,
          e.lastname,
          pg.id                             as player_group_id,
          pg.name                           as player_group_name,
          count(mw.*)                       as win_count,
          coalesce(subQuery.match_count, 0) as match_count,
          s.id                              as season_id,
          s.season_name
   from employee e
          cross join season s
          left join (select mw.employee_id,
                            m.season_id
                     from helper.match_winners mw
                            join match m on m.id = mw.match_id) mw on mw.employee_id = e.id and mw.season_id = s.id
          left join (select subQuery.employee_id,
                            count(subQuery.match_id) as match_count,
                            subQuery.season_id,
                            subQuery.season_name
                     from (select e.id as employee_id,
                                  m.id as match_id,
                                  s.id as season_id,
                                  s.season_name
                           from employee e
                                  left join helper.round_goals rg on rg.employee_id = e.id
                                  left join round r on rg.round_id = r.id
                                  left join match m on r.match_id = m.id and m.finished = true
                                  left join season s on m.season_id = s.id
                           group by e.id, m.id, s.id,
                                    s.season_name) subQuery
                     group by subQuery.employee_id, subQuery.season_id,
                              subQuery.season_name) subQuery
            on subQuery.employee_id = e.id and subQuery.season_id = s.id
          left join employee_player_group g on e.id = g.employee_id
          left join player_group pg on g.playergroup_id = pg.id
          left join helper.season_date season_end on season_end.id = s.id
   where display_employee(e.id, s.id) is true
   group by e.id, e.firstname, e.lastname, pg.id, pg.name, subQuery.match_count,
            s.id, s.season_name
   order by s.id desc, count(mw.*) desc, subQuery.match_count desc);


create or replace view report.round_win_count_report as
  (select row_number() over ()                     as id,
          e.id                                     as employee_id,
          e.firstname,
          e.lastname,
          pg.id                                    as player_group_id,
          pg.name                                  as player_group_name,
          s.id                                     as season_id,
          s.season_name,
          coalesce(subQueryWon.count_win_round, 0) as win_count,
          coalesce(subQueryAll.count_all_round, 0) as round_count
   from employee e
          cross join season s
          join employee_player_group epg on epg.employee_id = e.id
          join player_group pg on pg.id = epg.playergroup_id
          left join (select sub.employee_id,
                            mpg.player_group_id,
                            m.season_id,
                            count(sub.employee_id) as count_all_round
                     from (select rg.employee_id,
                                  r.match_id,
                                  case when rw.round_id is null then false else true end as won
                           from helper.round_goals rg
                                  left join helper.round_winners rw
                                    on rw.round_id = rg.round_id and rw.employee_id = rg.employee_id
                                  join round r on r.id = rg.round_id) sub
                            join match m on m.id = sub.match_id
                            join helper.match_player_group mpg on mpg.match_id = sub.match_id
                     group by sub.employee_id, mpg.player_group_id, m.season_id) subQueryAll
            on subQueryAll.employee_id = e.id and subQueryAll.season_id = s.id
          left join (select sub.employee_id,
                            mpg.player_group_id,
                            m.season_id,
                            count(sub.employee_id) as count_win_round
                     from (select rg.employee_id,
                                  r.match_id,
                                  case when rw.round_id is null then false else true end as won
                           from helper.round_goals rg
                                  left join helper.round_winners rw
                                    on rw.round_id = rg.round_id and rw.employee_id = rg.employee_id
                                  join round r on r.id = rg.round_id) sub
                            join match m on m.id = sub.match_id
                            join helper.match_player_group mpg on mpg.match_id = sub.match_id
                     where sub.won = true
                     group by sub.employee_id, mpg.player_group_id, m.season_id) subQueryWon
            on subQueryWon.employee_id = e.id and subQueryWon.season_id = s.id
   where display_employee(e.id, s.id) is true
   group by e.id, e.firstname, e.lastname, pg.id, pg.name, s.id, s.season_name, subQueryAll.count_all_round,
            subQueryWon.count_win_round
   order by s.id desc, pg.id, coalesce(subQueryWon.count_win_round, 0) desc);

create or replace view report.goal_did_get_report as
  (select row_number() over ()                    as id,
          e.id                                    as employee_id,
          e.firstname,
          e.lastname,
          pg.id                                   as player_group_id,
          pg.name                                 as player_group_name,
          s.id                                    as season_id,
          s.season_name,
          coalesce(subGoalCount.goal_count, 0)    as goal_count,
          coalesce(subGoalGetCount.goal_count, 0) as goal_get_count
   from employee e
          cross join season s
          join employee_player_group epg on epg.employee_id = e.id
          join player_group pg on pg.id = epg.playergroup_id
          left join (select rg.employee_id,
                            m.season_id,
                            sum(rg.goal_count) as goal_count
                     from helper.round_goals rg
                            join round r on r.id = rg.round_id
                            join match m on r.match_id = m.id
                     group by rg.employee_id, m.season_id) subGoalCount
            on subGoalCount.employee_id = e.id and subGoalCount.season_id = s.id
          left join (select rg.employee_id,
                            m.season_id,
                            sum(rg2.goal_count) as goal_count
                     from helper.round_goals rg
                            join helper.round_goals rg2 on rg2.round_id = rg.round_id and rg.team_id != rg2.team_id
                            join round r on r.id = rg.round_id
                            join match m on r.match_id = m.id
                     where rg.player_role = 'GOALMAN'
                     group by rg.employee_id, m.season_id) subGoalGetCount
            on subGoalGetCount.employee_id = e.id and subGoalGetCount.season_id = s.id
   where display_employee(e.id, s.id) is true
   group by e.id, e.firstname, e.lastname, pg.id, pg.name, s.id, s.season_name, subGoalCount.goal_count,
            subGoalGetCount.goal_count
   order by s.id desc, coalesce(subGoalCount.goal_count, 0) desc, e.id);

create or replace view report.match_position_report as
  (select row_number() over ()                                       as id,
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
                     where display_employee(e.id, s.id) is true
                     group by e.id, e.firstname, e.lastname, m.id, s.id, s.season_name) pos_con) pos_con
                join employee_player_group epg on epg.employee_id = pos_con.employee_id
                join player_group pg on epg.playergroup_id = pg.id
         group by pos_con.employee_id, pos_con.firstname,
                  pos_con.lastname, pg.id, pg.name, pos_con.season_id, pos_con.season_name) subQ
   order by subQ.season_id desc, subQ.attacker_count + subQ.goalman_count + subQ.both_count desc);

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
  where display_employee(e.id, s.id) is true
   order by subQ.own_goal_count desc);

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
  where display_employee(e.id, s.id) is true
   order by subQ.faggy_goal_count desc);

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
  where display_employee(e.id, s.id) is true
   order by subQ.stamp_goal_count desc);