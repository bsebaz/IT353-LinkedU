/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Bailey
 * Created: Nov 26, 2018
 */

drop table LinkedUDB.universityDetails;

CREATE TABLE LinkedUDB.universityDetails (
  detailId                  INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  universityId              INT,
  detailType                VARCHAR(50),
  detailName                VARCHAR(50),
  detailContent             VARCHAR(500)
);

INSERT INTO LINKEDUDB.UNIVERSITYDETAILS (UNIVERSITYID, DETAILTYPE, DETAILNAME, DETAILCONTENT) 
	VALUES (1, 'text', 'Description', 'This school is great, everyone loves it!');
INSERT INTO LINKEDUDB.UNIVERSITYDETAILS (UNIVERSITYID, DETAILTYPE, DETAILNAME, DETAILCONTENT) 
	VALUES (1, 'list', 'Clubs', 'Computer, Climbing, Game Dev, Anime');
INSERT INTO LINKEDUDB.UNIVERSITYDETAILS (UNIVERSITYID, DETAILTYPE, DETAILNAME, DETAILCONTENT) 
	VALUES (4, 'text', 'Chicago''s Public Research University', 'Located in the heart of one of the world’s great cities, the University of Illinois at Chicago is a vital part of the educational, technological and cultural fabric of the region. As Chicago’s only public research university with 30,000 students, 15 colleges, a hospital and a health sciences system, UIC provides you access to excellence and opportunity.');
INSERT INTO LINKEDUDB.UNIVERSITYDETAILS (UNIVERSITYID, DETAILTYPE, DETAILNAME, DETAILCONTENT) 
	VALUES (4, 'text', 'Value Diversity', 'UIC is proud to be recognized as having one of the most ethnically and culturally rich college campuses in America. Our welcoming environment and diverse student body engender deeper learning and provide new perspectives on life. Come experience the strength of multiple voices, races, cultures, beliefs, identities, orientations and points of view.');
