/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jftur
 * Created: Dec 3, 2018
 */

drop table LinkedUDB.applicationForms;

CREATE TABLE LinkedUDB.applicationForms (
  universityId          INT NOT NULL PRIMARY KEY,
  applicationId         VARCHAR(50) NOT NULL,
  title                 VARCHAR(50),
  content               VARCHAR(20)
);

--INSERT INTO LinkedUDB.applicationForms(universityId, applicationId, title, content) VALUES ('Illinois State University', 'Information Systems 1', 'Application for Illinois State University: Department of Information Systems', '');