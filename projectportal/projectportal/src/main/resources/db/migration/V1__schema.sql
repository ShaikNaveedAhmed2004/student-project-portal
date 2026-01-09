CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    role ENUM('STUDENT','FACULTY') NOT NULL
);

CREATE TABLE student (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    branch VARCHAR(100),
    year VARCHAR(20),
    CONSTRAINT fk_student_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE faculty (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    department VARCHAR(100),
    CONSTRAINT fk_faculty_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE project (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    faculty_id BIGINT,
    deadline DATE NOT NULL,
    CONSTRAINT fk_project_faculty FOREIGN KEY (faculty_id) REFERENCES faculty(id) ON DELETE SET NULL
);

CREATE TABLE application (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    student_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    applied_at DATETIME NOT NULL,
    status VARCHAR(100) NOT NULL,
    CONSTRAINT fk_application_student FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE,
    CONSTRAINT fk_application_project FOREIGN KEY (project_id) REFERENCES project(id) ON DELETE CASCADE
);
