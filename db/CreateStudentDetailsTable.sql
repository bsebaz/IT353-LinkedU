/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Bailey
 * Created: Nov 26, 2018
 */

drop table LinkedUDB.studentDetails;

CREATE TABLE LinkedUDB.studentDetails (
  detailId                  INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  studentId                 INT,
  detailType                VARCHAR(50),
  detailName                VARCHAR(50),
  detailContent             VARCHAR(255)
);
