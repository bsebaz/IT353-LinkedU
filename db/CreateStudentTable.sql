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
  studentId         INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  accountId                    INT, --Foreign key to tie student accounts to user account
  firstName                 VARCHAR(50),
  lastName                  VARCHAR(50),
  age                       VARCHAR(50),
  school                    VARCHAR(128),
  yearGraduated             VARCHAR(50),
  gpa                       VARCHAR(50)


-- uncomment this block once we're ready to connect tables
--    CONSTRAINT FK_UserStudent FOREIGN KEY (accountId)
--    REFERENCES LinkedUDB.accounts(accountId)
);

INSERT INTO LinkedUDB.students (userId, firstName, lastName, age, school, yearGraduated, gpa) 
VALUES (1, 'bruce', 'macklin', '17', 'SC Prep', '2018', '4.0');
INSERT INTO LinkedUDB.students (userId, firstName, lastName, age, school, yearGraduated, gpa)
VALUES (2, 'jenice', 'hepher', '17', 'St. Charles North', '2018', '4.2');
INSERT INTO LinkedUDB.students (userId, firstName, lastName, age, school, yearGraduated, gpa)
VALUES (3, 'peter', 'franklin', '18', 'St. Charles North', '2018', '3.8');
INSERT INTO LinkedUDB.students (userId, firstName, lastName, age, school, yearGraduated, gpa)
VALUES (4, 'Stephen', 'king', '17', 'St. Chalres North', '2018', '3.6');
INSERT INTO LinkedUDB.students (userId, firstName, lastName, age, school, yearGraduated, gpa)
VALUES (5, 'kyle', 'mercer', '18', 'St. Charles North', '2018', '4.6');
