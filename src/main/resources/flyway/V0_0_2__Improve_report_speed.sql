create or replace view helper.match_winners as
  (select
          subQ.match_id,
          subQ.employee_id,
          subQ.total_goals as total_goal_count
   from (select subQ.match_id,
                subQ.employee_id,
                subQ.total_goals,
                row_number() over (partition by match_id order by win_count desc) as row_number
         from (select m.id                as match_id,
                      rw.employee_id,
                      count(*)            as win_count,
                      sum(rw.goal_count) as total_goals
               from helper.round_winners rw
                      join round r on r.id = rw.round_id
                      join match m on r.match_id = m.id and m.finished = true
               group by m.id, rw.employee_id) subQ) subQ
   where subQ.row_number in (1, 2));