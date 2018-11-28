/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Bailey
 * Created: Nov 26, 2018
 */

drop table LinkedUDB.universities;

CREATE TABLE LinkedUDB.universities (
  universityId         INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  name                 VARCHAR(50),
  city                  VARCHAR(50),
  state                       VARCHAR(50),
  studentPopulation                    VARCHAR(128),
  cost             VARCHAR(50)
);

INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost) 
VALUES ('ISU', 'Normal', 'IL', '18000', '25000');
INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost)
VALUES ('UIUC', 'Champaign-Urbana', 'IL', '34000', '31000');
INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost)
VALUES ('NIU', 'Dekalb', 'IL', '20000', '18000');
