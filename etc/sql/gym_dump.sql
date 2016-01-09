--
use gymlogger;

DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Workout;
DROP TABLE IF EXISTS Repetition;
DROP TABLE IF EXISTS Exercise;
DROP TABLE IF EXISTS MuscleGroup;

CREATE TABLE User (
    id              INT             NOT NULL    AUTO_INCREMENT,
    name            VARCHAR(30)     NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Workout (
    id              INT             NOT NULL    AUTO_INCREMENT,
    id_User         INT             NOT NULL,
    start           DATETIME        NOT NULL,
    end             DATETIME        NOT NULL,        
    PRIMARY KEY(id)
);

CREATE TABLE Repetition (
    id              INT             NOT NULL    AUTO_INCREMENT,
    id_Workout      INT             NOT NULL,
    id_exercise     INT             NOT NULL,
    weight          VARCHAR(45),
    num             TINYINT         NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE Exercise (
    id              INT             NOT NULL,
    id_MuscleGroup  INT             NOT NULL,
    name            VARCHAR(45)     NOT NULL,
    nome            VARCHAR(45)     NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE MuscleGroup (
    id              INT             NOT NULL,
    name            VARCHAR(15)     NOT NULL,
    nome            VARCHAR(15)     NOT NULL,
    PRIMARY KEY(id)
);






INSERT INTO MuscleGroup (id,name,nome) VALUES 
    (1,     'Legs',        'Pernas'),
    (2,     'Back',        'Costas'),
    (3,     'Chest',       'Peitorais'),
    (4,     'Shoulders',   'Ombros'),
    (5,     'Biceps',      'Biceps'),
    (6,     'Triceps',     'Triceps');
    
INSERT INTO Exercise (id,id_MuscleGroup,name,nome) VALUES
    (101,1,     'dumbbell squats',              ''), -- Legs --
    (102,1,     'front squats',                 ''),
    (103,1,     'squats',                       ''),
    (104,1,     'power squats',                 ''),
    (105,1,     'angle leg presses',            ''),
    (106,1,     'hack squats',                  ''),
    (107,1,     'leg extensions',               ''),
    (108,1,     'lying leg curls',              ''),
    (109,1,     'god mornings',                 ''),
    (110,1,     'standing calf raises',         ''),
    (200,2,     'reverse chin-ups',             ''), -- Back --
    (201,2,     'chin-ups',                     ''),
    (202,2,     'lat pull-downs',               ''),
    (203,2,     'back lat pull-downs',          ''),
    (204,2,     'close-grip lat pull-downs',    ''),
    (205,2,     'seated rows',                  ''),
    (206,2,     'one-arm dumbbell rows',        ''),
    (207,2,     'bent rows',                    ''),
    (208,2,     'freestanding t-bar rows',      ''),
    (209,2,     't-bar rows',                   ''),
    (210,2,     'pull-ups',                     ''),
    (300,3,   'bench presses',                  ''), -- Chest --
    (301,3,   'incline presses',                ''),
    (302,3,   'close-grip bench presses',       ''),
    (303,3,   'parallel bar dips',              ''),
    (304,3,   'dumbbell presses',               ''),
    (305,3,   'dumbbell flys',                  ''),
    (306,3,   'incline dumbbell presses',       ''),
    (307,3,   'incline dumbbell flys',          ''),
    (308,3,   'pec deck flys',                  ''),
    (309,3,   'cable crossover flys',           ''),
    (310,3,   'dumbbell pullovers',             ''),
    (311,3,   'barbbell pullovers',             ''),
    (400,4,   'back press',                     ''), -- Shoulders --
    (401,4,   'seated front presses',           ''),
    (402,4,   'seated dumbbell presses',        ''),
    (403,4,   'front dumbbell presses',         ''),
    (404,4,   'bent-over lateral raises',       ''),
    (405,4,   'lateral dumbbell raises',        ''),
    (406,4,   'alternate front arm raises',     ''),
    (407,4,   'side-lying lateral raises',      ''),
    (408,4,   'low-pulley lateral raises',      ''),
    (409,4,   'low-pulley front raises',        ''),
    (410,4,   'one-dumbbell front raises',      ''),
    (411,4,   'barbell front raises',           ''),
    (412,4,   'upright rows',                   ''),
    (413,4,   'nautilus lateral raises',        ''),
    (414,4,   'pec deck rear-delt laterals',    ''),
    (415,4,   'pec-deck rear-delt laterals',    ''), 
    (500,5,   'curls',                          ''), -- Biceps --
    (501,5,   'concentration curls',            ''),
    (502,5,   'hammer curls',                   ''),
    (503,5,   'low-pulley curls',               ''),
    (504,5,   'high-pulley curls',              ''),
    (505,5,   'barbell curls',                  ''),
    (506,5,   'machine curls',                  ''),
    (507,5,   'preacher curls',                 ''),
    (508,5,   'reverse curls',                  ''),
    (509,5,   'reverse wrist curls',            ''),
    (510,5,   'wrist curls',                    ''),
    (511,5,   'seated curls',                   ''),
    (600,6,   'push-downs',                     ''), -- Triceps --
    (601,6,   'reverse push-downs',             ''),
    (602,6,   'dumbbell extensions',            ''), 
    (603,6,   'seated e-z bar extensions',      ''),
    (604,6,   'kickbacks',                      ''),
    (605,6,   'dips',                           ''),
    (606,6,   'one-arm dumbbell extension',     ''),
    (607,6,   'seated dumbbell extension',      ''),
    (608,6,   'triceps extensions',             ''),
    (609,6,   'one arm push-down',              '');



INSERT INTO User (id,name) VALUES (1,'adriano');

-- chest
INSERT INTO Workout (id_User,start,end) VALUES (1,'2012-01-04 17:30:00','2012-01-04 18:00:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,50,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,50,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,60,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,36,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,36,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,36,8);

-- shoulders
INSERT INTO Workout (id_User,start,end) VALUES (1,'2012-01-07 20:30:00','2012-01-07 21:00:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,38,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,38,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,38,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,405,12,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,405,12,8);

-- chest --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-04-16 20:05:00','2014-04-04 20:40:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,50,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,60,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,60,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,45,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,45,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,306,45,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,25,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,25,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,25,10);

-- biceps --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-04-19 20:30:00','2014-04-04 21:05:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,10);

-- triceps --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-01 12:45:00','2014-05-01 13:30:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,605,null,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,605,null,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,605,null,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);

-- shoulders --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-07 20:00:00','2014-05-07 20:20:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,406,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,406,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,406,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,38,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,38,8);

-- biceps --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-11 12:30:00','2014-05-11 13:00:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,38,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,38,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,500,16,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,500,16,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,500,16,8);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-12 20:45:00','2014-05-12 21:10:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,50,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,60,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,300,60,10);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-17 12:35:00','2014-05-17 12:55:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,608,28,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,608,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,608,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);

-- chest --
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-05-27 20:10:00','2014-05-27 20:45:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,50,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,60,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,301,70,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,304,45,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,304,45,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,304,45,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,305,12,10);


INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-02 20:00:00','2014-06-02 20:25:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,400,30,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,400,40,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,400,40,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,411,14,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,411,14,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,411,14,12);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-03 23:20:00','2014-06-03 23:45:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,507,12,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,508,14,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,508,14,10);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-08 11:35:00','2014-06-08 11:50:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,603,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,600,28,12);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-12 11:15:00','2014-06-12 11:40:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,210,null,6);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,210,null,6);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,210,null,6);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,203,40,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,203,40,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,203,40,12);

-- biceps
INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-18 11:00:00','2014-06-18 11:30:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,505,32,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,511,12,10);

INSERT INTO Workout (id_User,start,end) VALUES (1,'2014-06-28 16:15:00','2014-06-28 16:50:00');
set @last_workout_id = (SELECT LAST_INSERT_ID());
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,402,16,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,402,20,8);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,402,20,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,412,28,10);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,411,10,12);
INSERT INTO Repetition ( id_Workout, id_exercise, weight, num ) VALUES (@last_workout_id,411,10,20);
