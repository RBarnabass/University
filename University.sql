#CREATE DATABASE university;

INSERT INTO university.degrees VALUES (1, current_timestamp, 'PROFESSOR');
INSERT INTO university.degrees VALUES (2, current_timestamp, 'ASSOCIATE_PROFESSOR');
INSERT INTO university.degrees VALUES (3, current_timestamp, 'ASSISTANT');

INSERT INTO university.departments VALUES (1, current_timestamp, 'Electronics');
INSERT INTO university.departments VALUES (2, current_timestamp, 'Biology');
INSERT INTO university.departments VALUES (3, current_timestamp, 'Philosophy');
INSERT INTO university.departments VALUES (4, current_timestamp, 'Chemistry');

INSERT INTO university.employees VALUES (1, current_timestamp, 'Roman', 'Berezhnov', 5700.25, 1);
INSERT INTO university.employees VALUES (2, current_timestamp, 'Kate', 'Azarowa', 800.31, 3);
INSERT INTO university.employees VALUES (3, current_timestamp, 'Anna', 'Slusarchuk', 900.45, 3);
INSERT INTO university.employees VALUES (4, current_timestamp, 'Alice', 'Poopkina', 500.54, 3);
INSERT INTO university.employees VALUES (5, current_timestamp, 'Tina', 'Karol', 1700.15, 2);

INSERT INTO university.department_employees VALUES (1, current_timestamp, true, 1, 1);
INSERT INTO university.department_employees VALUES (2, current_timestamp, null, 1, 2);
INSERT INTO university.department_employees VALUES (3, current_timestamp, null, 1, 3);
INSERT INTO university.department_employees VALUES (4, current_timestamp, null, 1, 4);
INSERT INTO university.department_employees VALUES (5, current_timestamp, null, 1, 5);