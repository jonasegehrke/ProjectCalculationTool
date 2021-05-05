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
project_id INT references project(id),
PRIMARY KEY (id)
);

CREATE table subtask(
id INT NOT NULL auto_increment,
title VARCHAR(35),
hours double,
task_id INT references task(id),
PRIMARY KEY (id)
);


/* Example */
INSERT INTO project (title, hours) VALUES ("MyFirstProject", 7);

INSERT INTO task (title, hours, project_id) VALUES ("Design", 7, 1);

INSERT INTO subtask (title, hours, task_id) VALUES ("Domain Model", 4, 1);
INSERT INTO subtask (title, hours, task_id) VALUES ("Use Cases", 3, 1);
/* End of example */


SELECT * FROM subtask;
SELECT * FROM task;
SELECT * FROM project;