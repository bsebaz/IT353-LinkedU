/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Katie
 * Created: Dec 2, 2018
 */

drop table LinkedUDB.files;

CREATE TABLE LinkedUDB.files (
  fileId                    INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  content                   INT, --type is placeholder for now
  studentId                 INT, --Foreign key to tie file to student account

    CONSTRAINT FK_FileStudent FOREIGN KEY (studentId)
    REFERENCES LinkedUDB.Students(studentId)
);