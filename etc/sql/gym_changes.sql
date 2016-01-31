
USE gymlogger;
DESC workout;

RENAME TABLE exercice TO exercise;
ALTER TABLE repetition CHANGE id_Exercice id_exercise int(11);
ALTER TABLE repetition CHANGE id_Workout id_workout int(11);
ALTER TABLE workout CHANGE id_User id_user int(11);
ALTER TABLE exercise CHANGE id_MuscleGroup id_musclegroup int(11);

DELETE FROM repetition WHERE id_workout = 33;
DELETE FROM workout WHERE id = 33;

-- trick to update field from select to the same table
update workout set end = (select w.start from (select * from workout) as w where w.id = 30) where id = 30;

-- fix data errors
update workout set end = '2014-11-27 23:45:00' where id = 30;
update workout set end = '2014-10-20 23:45:00' where id = 28;
update workout set end = '2014-04-19 21:05:00' where id = 4;
update workout set end = '2014-04-16 20:40:00' where id = 3;

-- update workouts
update repetition set weight = 50 where id = 263;
update repetition set weight = 36 where id = 277;



