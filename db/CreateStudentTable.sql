/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Bailey
 * Created: Nov 26, 2018
 */

drop table LinkedUDB.students;

CREATE TABLE LinkedUDB.students (
  studentId                 INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  firstName                 VARCHAR(50),
  lastName                  VARCHAR(50),
  age                       VARCHAR(50),
  school                    VARCHAR(128),
  yearGraduated             VARCHAR(50),
  gpa                       VARCHAR(50)
);

INSERT INTO LinkedUDB.students (firstName, lastName, age, school, yearGraduated, gpa) 
VALUES ('Bruce', 'Macklin', '17', 'SC Prep', '2018', '4.0');
INSERT INTO LinkedUDB.students (firstName, lastName, age, school, yearGraduated, gpa)
VALUES ('Jenice', 'Hepher', '17', 'St. Charles North', '2018', '4.2');
INSERT INTO LinkedUDB.students (firstName, lastName, age, school, yearGraduated, gpa)
VALUES ('Peter', 'Franklin', '18', 'St. Charles North', '2018', '3.8');
INSERT INTO LinkedUDB.students (firstName, lastName, age, school, yearGraduated, gpa)
VALUES ('Stephen', 'King', '17', 'St. Chalres North', '2018', '3.6');
INSERT INTO LinkedUDB.students (firstName, lastName, age, school, yearGraduated, gpa)
VALUES ('Kyle', 'Mercer', '18', 'St. Charles North', '2018', '4.6');
