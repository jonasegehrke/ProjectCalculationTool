CREATE SCHEMA project_calculator;
USE project_calculator;


CREATE table project(
id INT NOT NULL AUTO_INCREMENT,
title VARCHAR(35),
hours double,
PRIMARY KEY (id)
);

CREATE table task(
id INT NOT NULL auto_increment,
title VARCHAR(35),
hours double,
project_id INT,
PRIMARY KEY (id),
FOREIGN KEY (project_id) references project(id)
);

CREATE table employee(
id INT NOT NULL auto_increment,
emp_name VARCHAR(35),
job_title VARCHAR(35),
planned_hours double,
PRIMARY KEY (id)
);

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


SELECT * FROM subtask;
SELECT * FROM task;
SELECT * FROM project;