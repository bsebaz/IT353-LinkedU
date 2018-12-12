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
  accountId                 INT, --Foreign key to tie student accounts to user account
  firstName                 VARCHAR(50),
  lastName                  VARCHAR(50),
  age                       VARCHAR(50),
  school                    VARCHAR(128),
  yearGraduated             VARCHAR(50),
  gpa                       VARCHAR(50),
  imagePath                 VARCHAR(255)


-- uncomment this block once we're ready to connect tables
--    CONSTRAINT FK_UserStudent FOREIGN KEY (accountId)
--    REFERENCES LinkedUDB.accounts(accountId)
);

INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (1, 'Bruce', 'Macklin', '17', 'SC Prep', '2018', '4.0', NULL);
INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (20, 'Jenice', 'Hepher', '17', 'St. Charles North', '2018', '4.2', NULL);
INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (30, 'Peter', 'Franklin', '18', 'St. Charles North', '2018', '3.8', NULL);
INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (40, 'Stephen', 'King', '17', 'St. Chalres North', '2018', '3.6', NULL);
INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (50, 'Kyle', 'Mercer', '18', 'St. Charles North', '2018', '4.6', NULL);
INSERT INTO LINKEDUDB.STUDENTS (ACCOUNTID, FIRSTNAME, LASTNAME, AGE, SCHOOL, YEARGRADUATED, GPA, IMAGEPATH) 
	VALUES (2, 'Michael', 'McHugh', '21', 'Naperville North High School', '2015', '3.93', 'image/42840328_1059631164245427_4066358934296854528_n.jpg');

