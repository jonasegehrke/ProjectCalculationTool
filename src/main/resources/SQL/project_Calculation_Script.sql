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

CREATE table subtask(
id INT NOT NULL auto_increment,
title VARCHAR(35),
hours double,
task_id INT,
PRIMARY KEY (id),
FOREIGN KEY (task_id) references task(id)
);


SELECT * FROM subtask;
SELECT * FROM task;
SELECT * FROM project;