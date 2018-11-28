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
  city                 VARCHAR(50),
  state                VARCHAR(50),
  studentPopulation    VARCHAR(128),
  cost                 VARCHAR(50),
  featured             SMALLINT
);

INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost, featured) 
VALUES ('Illinois State University', 'Normal', 'IL', '18000', '25000', 1);
INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost, featured)
VALUES ('University of Illinois at Urbana–Champaign', 'Champaign-Urbana', 'IL', '34000', '31000', 1);
INSERT INTO LinkedUDB.universities (name, city, state, studentPopulation, cost, featured)
VALUES ('Northern Illinois University', 'Dekalb', 'IL', '20000', '18000', 1);
