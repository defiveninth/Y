CREATE TABLE students
(
    id     BIGSERIAL PRIMARY KEY,
    name   VARCHAR(255),
    course INT
);

CREATE TABLE books
(
    id         BIGSERIAL PRIMARY KEY,
    name       VARCHAR(255),
    genre      VARCHAR(255),
    student_id BIGINT,
    CONSTRAINT fk_books_student
        FOREIGN KEY (student_id)
            REFERENCES students (id)
            ON DELETE SET NULL
);

CREATE TABLE groups
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    type VARCHAR(255)
);

CREATE TABLE student_groups
(
    student_id BIGINT NOT NULL,
    group_id   BIGINT NOT NULL,
    PRIMARY KEY (student_id, group_id),
    CONSTRAINT fk_student_groups_student
        FOREIGN KEY (student_id)
            REFERENCES students (id)
            ON DELETE CASCADE,
    CONSTRAINT fk_student_groups_group
        FOREIGN KEY (group_id)
            REFERENCES groups (id)
            ON DELETE CASCADE
);
