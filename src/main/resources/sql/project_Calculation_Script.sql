/*Peer programming: Andreas Holm Andersen, Jacob Gade Harder, Jonas Emil Gehrke og Jimmi Paw Pisalita
*/
Drop schema if exists project_calculator;

/* DDL: Lav databasen project_calculator */
CREATE SCHEMA project_calculator;
/* Tilgå databasen */
USE project_calculator;

/* DDL: Lav table project */
CREATE table project(
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(35),
hours double,
PRIMARY KEY (id)
);

/* DDL: Lav table task */
CREATE table task(
id INT NOT NULL auto_increment,
title VARCHAR(35),
hours double,
project_id INT,
PRIMARY KEY (id),
FOREIGN KEY (project_id) references project(id)
);

/* DDL: Lav table employee */
CREATE table employee(
id INT NOT NULL auto_increment,
emp_name VARCHAR(35),
job_title VARCHAR(35),
planned_hours double,
PRIMARY KEY (id)
);

/* DDL: Lav table subtask */
CREATE table subtask(
id INT NOT NULL auto_increment,
title VARCHAR(35),
hours double,
task_id INT,
employee_id INT,
PRIMARY KEY (id),
FOREIGN KEY (task_id) references task(id),
FOREIGN KEY (employee_id) references employee(id)
);

/* DML: Laver eksempler på et project og employees for nemmere testing af produkt */
INSERT INTO project (title) VALUES("MyFirstProject");
INSERT INTO employee (emp_name, job_title) VALUES("Jonas Emil Gehrke", "Software Developer");
INSERT INTO employee (emp_name, job_title) VALUES("Jimmi Paw Pisalita", "Software Developer");
INSERT INTO employee (emp_name, job_title) VALUES("Jacob Gade Harder", "System Designer");
INSERT INTO employee (emp_name, job_title) VALUES("Andreas Holm Andersen", "System Designer");


SELECT * FROM subtask;
SELECT * FROM task;
SELECT * FROM project;
SELECT * FROM employee;

