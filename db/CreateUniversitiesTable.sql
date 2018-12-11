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
  accountId            INT,
  name                 VARCHAR(50),
  city                 VARCHAR(50),
  state                VARCHAR(50),
  studentPopulation    VARCHAR(128),
  cost                 VARCHAR(50),
  accentColor          VARCHAR(6),
  applicationUrl       VARCHAR(50),
  featured             SMALLINT,
  imagePath            VARCHAR(255)
);

INSERT INTO LinkedUDB.universities (accountId, name, city, state, studentPopulation, cost, accentColor, featured)
VALUES (1, 'Illinois State University', 'Normal', 'IL', '18000', '25000', 'CE1126', 1);
INSERT INTO LinkedUDB.universities (accountId, name, city, state, studentPopulation, cost, accentColor, featured)
VALUES (20, 'University of Illinois at Urbana–Champaign', 'Champaign-Urbana', 'IL', '34000', '31000', 'E84A27', 1);
INSERT INTO LinkedUDB.universities (accountId, name, city, state, studentPopulation, cost, accentColor, featured)
VALUES (30, 'Northern Illinois University', 'Dekalb', 'IL', '20000', '18000', 'C8102E', 1);
