/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  slfx7
 * Created: Dec 2, 2018
 */

drop table LinkedUDB.appointments;

CREATE TABLE LinkedUDB.appointments (
  appointmentId             INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  startTime                 TIME,
  endTime                   TIME,
  aptDate                   DATE,
  universityId              INT NOT NULL,
  studentId                 INT
--   FOREIGN KEY (universityId)
--     REFERENCES LinkedUDB.UNIVERSITIES (universityId),
--  FOREIGN KEY (studentId)
--    REFERENCES LinkedUDB.STUDENTS (studentId)
);

