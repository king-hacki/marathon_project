INSERT INTO marathon(id, title)
VALUES (1, 'foo1');

INSERT INTO marathon(id, title)
VALUES (2, 'foo2');

INSERT INTO users(id, email, first_name, last_name, password)
VALUES (1, 'test@gmail.com', 'test', 'test', 'test');

INSERT INTO user_marathon(user_id, marathon_id)
VALUES (1, 1);