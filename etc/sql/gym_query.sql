use gymlogger;
show tables;

show create table workout;

select * from repetition;
select * from user;
select * from workout;
select * from exercise;

select * from repetition where id_Exercise >= 300 and id_Exercise <= 400;
select * from repetition where id_workout = 40;


SELECT LAST_INSERT_ID();
select id from user where name = 'adriano';
delete from repetition where id_workout = 2;
set @id = (SELECT LAST_INSERT_ID());



-- ddl --
alter table workout add constraint check_dates check(end < getdate());
alter table workout change end end datetime check (start < end);
alter table repetition change weight weight varchar(81) not null;
-- delete from workout where id = 2;