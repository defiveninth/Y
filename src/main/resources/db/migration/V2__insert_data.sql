INSERT INTO students (name, course)
VALUES ('Abdurrauf', 1),
       ('Maxat', 2),
       ('Daulet', 1);

INSERT INTO books (name, genre, student_id)
VALUES ('Introduction to Java', 'Programming', 1),
       ('Introduction to JS', 'Programming', 1),
       ('Introduction to Python', 'Programming', 2),
       ('Introduction to Go', 'low level Programming', 3);

INSERT INTO groups (name, type)
VALUES ('Group A', 'Science'),
       ('Group B', 'Arts'),
       ('Group C', 'Engineering');

INSERT INTO student_groups (student_id, group_id)
VALUES (1, 1),
       (1, 1),
       (2, 2),
       (1, 1),
       (1, 1);
