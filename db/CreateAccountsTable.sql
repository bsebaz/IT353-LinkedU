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
  accountId         INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  username             VARCHAR(50) UNIQUE,
  password             VARCHAR(50),
  accountType          VARCHAR(20), --student/recruiter
  isAdmin              SMALLINT
  
);

INSERT INTO LinkedUDB.accounts(username, password, accountType, isAdmin) VALUES ('user', 'password', 'student', 1);

