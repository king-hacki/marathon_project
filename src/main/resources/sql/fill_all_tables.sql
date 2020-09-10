START TRANSACTION;

-- roles
INSERT INTO role(role_name)
VALUES ('ROLE_USER');
INSERT INTO role(role_name)
VALUES ('ROLE_ADMIN');

-- users
INSERT INTO users(email, first_name, last_name, password)
VALUES ('user1@gmail.com', 'John', 'Smith', '$2a$10$VgFIvZkGi2xbRFnsejMiFuSrlPmkI4dc73n0qe.qIg/7e5mpJfmNi');
INSERT INTO users(email, first_name, last_name, password)
VALUES ('user2@gmail.com', 'Zakhar', 'Kostyshyn', '$2a$10$U0oEh9zNNQkJxp8pTw525.38w5vIKQf57teqlu.R2VIoRW4f/hGRu');
INSERT INTO users(email, first_name, last_name, password)
VALUES ('user3@gmail.com', 'Svitlana', 'Pyatkivska', '$2a$10$YmknfDgQPl8D7ImlNEiVfO73iwguAD9l098VZl7D21e0IWb0a5I0O');
INSERT INTO users(email, first_name, last_name, password)
VALUES ('admin@gmail.com', 'admin', 'admin', '$2a$10$juk/tuc7LoEoa3q1Js486Oa08xa1BhlvKdaktxIJ0IpUKAP9.nth2');

--user-role
INSERT INTO user_role(user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (2, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (3, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (4, 1);
INSERT INTO user_role(user_id, role_id)
VALUES (4, 2);

-- marathons
INSERT INTO marathon(title)
VALUES ('Java Marathon');
INSERT INTO marathon(title)
VALUES ('JavaScript Marathon');

--user-marathon
INSERT INTO user_marathon(user_id, marathon_id)
VALUES(1, 1);
INSERT INTO user_marathon(user_id, marathon_id)
VALUES(2, 2);
INSERT INTO user_marathon(user_id, marathon_id)
VALUES(3, 2);
INSERT INTO user_marathon(user_id, marathon_id)
VALUES(3, 1);

--Java Marathon' sprints
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-4', '2020-09-1', 'Spring IOC', 1);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-8', '2020-09-5', 'Spring JPA', 1);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-12', '2020-09-9', 'Spring MVC', 1);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-17', '2020-09-13', 'Spring Exceptions', 1);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-21', '2020-09-18', 'Spring Security', 1);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-25', '2020-09-22', 'Multithreading', 1);

--JavaScript Marathon' sprints
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-4', '2020-09-1', 'JavaScript Syntax', 2);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-8', '2020-09-5', 'JavaScript Libraries', 2);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-12', '2020-09-9', 'Node', 2);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-17', '2020-09-13', 'React', 2);
INSERT INTO sprint(finish_date, start_date, title, marathon_id)
VALUES ('2020-09-21', '2020-09-18', 'React-Redux', 2);


--  JAVA MARATHON TASKS   --
-- tasks for Spring IOC sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 1);
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Some Tests', 'Tests', 1);
-- task for Spring JPA sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 2);
-- task for Spring MVC sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 3);
-- task for Spring Exceptions sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 4);
-- tasks for Spring Security sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 5);
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Some Tests', 'Tests', 5);
-- tasks for Multithreading sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Some Tests', 'Tests', 6);


--  JAVASCRIPT MARATHON TASKS   --
-- tasks for JavaScript Syntax sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 7);
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Some Tests', 'Tests', 7);
-- task for JavaScript Libraries sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 8);
-- task for Node sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 9);
-- task for React sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 10);
-- tasks for React-Redux sprint
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Give Right answer', 'Practice Task', 11);
INSERT INTO task(status, task_description, title, sprint_id)
VALUES ('NOT_STARTED', 'Some Tests', 'Tests', 11);

COMMIT;




