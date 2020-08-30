-- users
INSERT INTO users(id, email, first_name, last_name,password)
VALUES (1, 'foo1@gmail.com', 'foo', 'bar', 'pass');

INSERT INTO users(id, email, first_name, last_name, password)
VALUES (2, 'foo2@gmail.com', 'foo', 'bar', 'pass');

-- marathons
INSERT INTO marathon(id, title)
VALUES (1, 'foo1');

INSERT INTO marathon(id, title)
VALUES (2, 'foo2');

-- sprints
INSERT INTO sprint(id, finish_date, start_date, title,marathon_id)
VALUES (1, current_date, current_date, 'foo1', 1);

INSERT INTO sprint(id, finish_date, start_date, title, marathon_id)
VALUES (2, current_date, current_date, 'foo2', 2);

-- tasks
INSERT INTO task(id, created, title, updated,sprint_id)
VALUES (1, current_date, 'foo1', current_date, 1);

INSERT INTO task(id, created, title, updated, sprint_id)
VALUES (2, current_date, 'foo2', current_date, 2);

-- progresses
INSERT INTO progress(id, started, status, updated, task_id, user_id)
VALUES (1, current_date, 'STARTED', current_date, 1, 1);

INSERT INTO progress(id, started, status, updated, task_id, user_id)
VALUES (2, current_date, 'STARTED', current_date, 1, 1);

INSERT INTO progress(id, started, status, updated, task_id, user_id)
VALUES (3, current_date, 'STARTED', current_date, 2, 1);



