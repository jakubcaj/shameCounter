-- create view report.round_goals as (
--   select
--     subQuery.*,
--     goalman.id          as goalman_employee_id,
--     goalman.goal_count  as goalman_goal_count,
--     attacker.id         as attacker_employee_id,
--     attacker.goal_count as attacker_goal_count
--   from (
--          select
--            r.id     as round_id,
--            t.id     as team_id,
--            count(*) as total_goals
--          from round r
--            join team t on r.id = t.round_id
--            join teamscore t2 on t.id = t2.team_id
--          group by t.id, r.id) subQuery
--     join team t on t.id = subQuery.team_id
--     join (select
--             tp.team_id,
--             e.*,
--             coalesce(count(ts.*), 0) as goal_count
--           from teamplayer tp
--             join employee e on tp.employee_id = e.id
--             left join teamscore ts on tp.team_id = ts.team_id and ts.employee_id = e.id
--           where tp.player_role = 'GOALMAN'
--           group by tp.team_id, e.id, e.firstname, e.lastname) goalman on goalman.team_id = t.id
--     join (select
--             tp.team_id,
--             e.*,
--             coalesce(count(ts.*), 0) as goal_count
--           from teamplayer tp
--             join employee e on tp.employee_id = e.id
--             left join teamscore ts on tp.team_id = ts.team_id and ts.employee_id = e.id
--           where tp.player_role = 'ATTACKER'
--           group by tp.team_id, e.id, e.firstname, e.lastname) attacker on attacker.team_id = t.id)
create schema report;
create schema helper;

create or replace view helper.round_goals as (
  select
    subQuery.*,
    player.id         as employee_id,
    player.goal_count as goal_count,
    player.player_role
  from (
         select
           r.id     as round_id,
           t.id     as team_id,
           count(*) as total_goals
         from round r
           join team t on r.id = t.round_id
           left join teamscore t2 on t.id = t2.team_id
           where r.running = false
         group by t.id, r.id) subQuery
    join team t on t.id = subQuery.team_id
    join (select
            tp.team_id,
            e.*,
            tp.player_role,
            coalesce(count(ts.*), 0) as goal_count
          from teamplayer tp
            join employee e on tp.employee_id = e.id
            left join teamscore ts on tp.team_id = ts.team_id and ts.employee_id = e.id
          --         where tp.player_role = 'GOALMAN'
          group by tp.team_id, e.id, e.firstname, e.lastname, tp.player_role) player on player.team_id = t.id);

create view helper.round_winners as (
  select
    rg.round_id,
    rg.team_id,
    rg.total_goals,
    rg.employee_id,
    rg.goal_count,
    rg.player_role
  from helper.round_goals rg
    join (
           select
             round_id,
             max(total_goals) as max_goals
           from helper.round_goals
           group by round_id
         ) maxGoals on maxGoals.round_id = rg.round_id
  where rg.total_goals = maxGoals.max_goals
  group by rg.round_id, rg.team_id, rg.total_goals, rg.employee_id, rg.goal_count, rg.player_role);

create or replace view helper.match_winners as (
  select
    m.id as match_id,
    subQuery.employee_id,
    subQuery.total_goal_count
  from match m
    join (
           select
             r.match_id,
             rw.employee_id,
             count(*)           as win_count,
             sum(rw.goal_count) as total_goal_count
           from helper.round_winners rw
             join round r on r.id = rw.round_id
           group by r.match_id, rw.employee_id
         ) subQuery on subQuery.match_id = m.id
    join (
           select
             m.id                    as match_id,
             max(subQuery.win_count) as max_win_count
           from match m
             join (
                    select
                      r.match_id,
                      rw.employee_id,
                      count(*) as win_count
                    from helper.round_winners rw
                      join round r on r.id = rw.round_id
                    group by r.match_id, rw.employee_id
                  ) subQuery on subQuery.match_id = m.id
           group by m.id
         ) subQuery2 on subQuery2.match_id = m.id
  where subQuery.win_count = subQuery2.max_win_count and m.finished = true);

create or replace view report.match_win_count_report as (
  select
    e.id,
    e.firstname,
    e.lastname,
    count(mw.*) as win_count,
    subQuery.match_count
  from employee e
    left join helper.match_winners mw on mw.employee_id = e.id
    join (
           select
             subQuery.employee_id,
             count(subQuery.match_id) as match_count
           from (
                  select
                    e.id as employee_id,
                    m.id as match_id
                  from employee e
                    left join helper.round_goals rg on rg.employee_id = e.id
                    left join round r on rg.round_id = r.id
                    left join match m on r.match_id = m.id and m.finished = true
                  group by e.id, m.id) subQuery
           group by subQuery.employee_id
         ) subQuery on subQuery.employee_id = e.id
  group by e.id, e.firstname, e.lastname, subQuery.match_count
  order by count(mw.*) desc, subQuery.match_count desc);

create or replace view report.employee_matches_report as (
  select
    row_number() over (),
    e.id as employee_id,
    e.firstname,
    e.lastname,
    m.id           as match_id,
    rg_own.players as team_mate,
    rg_en.players  as opponents,
    case when mw.match_id is not null then true else false end as won
  from employee e
    left join (select
                 subQ.employee_id,
                 subQ.round_id,
                 string_agg(e.firstname || ' ' || e.lastname, ', ') as players
               from (select
                       rg.employee_id,
                       rg.round_id,
                       rg_own.employee_id as player
                     from helper.round_goals rg
                       join helper.round_goals rg_own on rg_own.round_id = rg.round_id and rg_own.team_id = rg.team_id and
                                                         rg_own.employee_id != rg.employee_id
                     group by rg.employee_id, rg.round_id, rg_own.employee_id
                    ) subQ
                 join employee e on e.id = subQ.player
               group by subQ.employee_id, subQ.round_id
              ) rg_own on rg_own.employee_id = e.id
    join round r on r.id = rg_own.round_id
    join match m on r.match_id = m.id
    join (select
            subQ.employee_id,
            subQ.match_id,
            string_agg(e.firstname || ' ' || e.lastname, ', ') as players
          from (select
                  rg.employee_id,
                  m.id               as match_id,
                  rg_own.employee_id as player
                from helper.round_goals rg
                  join helper.round_goals rg_own on rg_own.round_id = rg.round_id and rg_own.team_id != rg.team_id
                  join round r on r.id = rg.round_id
                  join match m on r.match_id = m.id
                group by rg.employee_id, m.id, rg_own.employee_id
               ) subQ
            join employee e on e.id = subQ.player
          group by subQ.employee_id, subQ.match_id
         ) rg_en on rg_en.employee_id = e.id and rg_en.match_id = m.id
    left join helper.match_winners mw on mw.match_id = m.id and mw.employee_id = e.id
  group by e.id, e.firstname, e.lastname, m.id, rg_own.players, rg_en.players, mw.match_id);