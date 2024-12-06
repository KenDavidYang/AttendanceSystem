CREATE DATABASE group4_oop_db;
USE group4_oop_db;

-- student attendance table creation 
CREATE TABLE attendance_table (
SessionID INT PRIMARY KEY AUTO_INCREMENT,
StudentNumber VARCHAR(255),
FirstName VARCHAR(255),
LastName VARCHAR(255),
CourseAndSection VARCHAR(255),
CheckInDateTime DATETIME,
CheckOutDateTime DATETIME,
Temperature DECIMAL(3,1)
);

-- teacher attendance table creation
CREATE TABLE teacher_attendance_table (
SessionID INT PRIMARY KEY AUTO_INCREMENT,
TeacherNumber VARCHAR(255),
FirstName VARCHAR(255),
LastName VARCHAR(255),
CheckInDateTime DATETIME,
CheckOutDateTime DATETIME,
Temperature DECIMAL(3,1)
);

-- insert value into student attendance table
INSERT INTO attendance_table (StudentNumber, FirstName, LastName, CourseAndSection, CheckInDateTime, CheckOutDateTime, Temperature)
VALUES ("MN 1-23", "James", "Alcantara", "DICT 2-4", NOW(), NOW(), 36.1);

-- insert value into teacher attendance table
INSERT INTO teacher_attendance_table (TeacherNumber, FirstName, LastName, CheckInDateTime, CheckOutDateTime, Temperature)
VALUES ("MN 1-23", "Enrique", "De jesus", NOW(), NOW(), 36.1);

-- show table, uncomment
SELECT * FROM attendance_table;
-- SELECT * FROM teacher_attendance_table;