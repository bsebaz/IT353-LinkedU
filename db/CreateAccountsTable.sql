/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Katie
 * Created: Nov 28, 2018
 */

drop table LinkedUDB.accounts;

CREATE TABLE LinkedUDB.accounts (
  accountId            INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username             VARCHAR(50) UNIQUE,
  password             VARCHAR(50),
  email                VARCHAR(100),
  accountType          VARCHAR(20), --student/recruiter
  isAdmin              SMALLINT
);

INSERT INTO LINKEDUDB.ACCOUNTS (USERNAME, PASSWORD, EMAIL, ACCOUNTTYPE, ISADMIN) 
	VALUES ('user', 'password', 'mmchug1@outlook.com', 'student', 1);
INSERT INTO LINKEDUDB.ACCOUNTS (USERNAME, PASSWORD, EMAIL, ACCOUNTTYPE, ISADMIN) 
	VALUES ('student', 'password', 'mmchug1@outlook.com', 'student', 0);
INSERT INTO LINKEDUDB.ACCOUNTS (USERNAME, PASSWORD, EMAIL, ACCOUNTTYPE, ISADMIN) 
	VALUES ('university', 'password', 'mmchug1@outlook.com', 'recruiter', 0);

