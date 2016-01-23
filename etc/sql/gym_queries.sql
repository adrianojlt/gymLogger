use gymlogger;
show tables;
desc repetition;

select * from musclegroup;
select * from exercise;
select * from repetition where id = 261;
select * from repetition where id_workout = 31;
select * from repetition order by id desc;
select * from repetition where id_workout = 33;
select * from workout;
select * from workout order by id desc;
select * from workout where id = 3;
select * from user;


select id from exercise where id_musclegroup = 2;
select * from repetition where id_exercise = 300; 
select * from repetition where id_exercise in ( select id from exercise where id_musclegroup = 2 );
select * from repetition where id_exercise in ( select id from exercise where id_musclegroup = 2 );

-- musclegroups
select * from musclegroup g
INNER JOIN exercise e on g.id = e.id_musclegroup;
-- where g.id = 1;
select * from exercise e
INNER JOIN musclegroup g on g.id = e.id_musclegroup;
	
-- list exercises by musclegroup identified
select * from exercise where id_musclegroup = 2;

select * from musclegroup g 
inner join exercise e on e.id_musclegroup = g.id
inner join repetition r on r.id_exercise = e.id
inner join workout w on w.id = r.id_workout where w.id = 28;

-- QUERY_GET_WORKOUTS
SELECT id, start, end FROM workout w  ORDER BY id DESC LIMIT 0,5;
SELECT COUNT(*) FROM workout ORDER BY id DESC;

-- QUERY_GET_ALL_WORKOUTS
SELECT w.id AS wid, w.start, w.end, g.id AS gid, g.name AS gname, e.id AS eid, e.name AS ename, r.id AS rid, r.num, r.weight
FROM workout w
INNER JOIN repetition r 	ON r.id_workout = w.id
INNER JOIN exercise e 		ON e.id = r.id_exercise
INNER JOIN musclegroup g 	ON g.id = e.id_musclegroup
-- group by wid
ORDER BY w.start DESC, rid ASC; 
-- limit 10 offset 0;

-- QUERY_GET_GROUPS_FROM_WORKOUT 
SELECT g.id AS gid, g.name AS gname, e.id AS eid, e.name AS ename, r.id AS rid, r.num AS num, r.weight AS weight 
FROM repetition r
INNER JOIN exercise e  		ON e.id = r.id_exercise
INNER JOIN musclegroup g  	ON g.id = e.id_musclegroup
-- WHERE r.id_workout = 23
WHERE e.id = 600
ORDER BY rid ASC;


-- QUERY_GET_REPETITIONS
SELECT r.id as id , r.weight as weight , r.num as num , e.id as id_exercise , e.name as name , e.nome as nome , g.id as id_musclegroup , g.nome as musclegroupnome, g.name as musclegroupname 
FROM repetition r
INNER JOIN exercise e 		on e.id = r.id_exercise 
INNER JOIN musclegroup g 	on g.id = e.id_musclegroup 
ORDER BY id DESC;

-- list workouts by muscle group QUERY_GET_REPETITIONS_BY_WORKOUTID
SELECT r.id , r.id_workout as wid, g.name as gname, e.id as eid, e.name as ename, r.weight, r.num 
FROM repetition r 
INNER JOIN exercise e 		on e.id = r.id_exercise
INNER JOIN musclegroup g 	on g.id = e.id_musclegroup
-- WHERE id_exercise IN ( SELECT id FROM exercise WHERE id_musclegroup IN ( 3 ) ) 
WHERE r.id_workout = 31;
-- ORDER BY wid DESC, id ASC;


-- (A) view all workouts ... QUERY_GET_ALL_WORKOUTS
select w.id as id_work , r.id as id_rep ,   w.start , w.end , g.id as group_id , g.name as group_name , r.id_exercise , e.name as exercise_name , r.weight , r.num  
from workout w
inner join repetition 	r on r.id_workout 	= w.id
inner join exercise 	e on e.id 			= r.id_exercise
inner join musclegroup	g on g.id 			= e.id_musclegroup
order by start DESC, id_work DESC, id_rep ASC;
-- order by id_work desc , id_rep asc;
-- order by id_work desc, id_exercise desc;
-- where w.id = 29 order by id_rep desc;

-- same as (A) but with condition
select * from (select w.id as id_work , r.id as id_rep ,   w.start , w.end , g.id as group_id , g.name as group_name , r.id_exercise , e.name as exercise_name , r.weight , r.num  
from workout w
inner join repetition 	r on r.id_workout 	= w.id
inner join exercise 	e on e.id 			= r.id_exercise
inner join musclegroup	g on g.id 			= e.id_musclegroup
where w.id > 0 order by id_rep desc) as workouts where weight < 20;

-- TMP
select w.id as workout_id, w.start as start, w.end as end, r.id as repetition_id, r.weight, r.num
from workout w
inner join repetition r on r.id_workout = w.id
order by repetition_id desc;

select * from repetition r 
inner join exercise  	e on e.id = r.id_exercise
where id_exercise = 304;

-- secound highest weight
SELECT id , MAX(weight) as max FROM repetition WHERE weight NOT IN (SELECT MAX(weight) FROM repetition);

-- max weight by exercise
SELECT id_exercise, e.name as ename, MAX(weight) FROM repetition  r
LEFT JOIN exercise e on e.id = r.id_exercise
GROUP BY id_exercise;




