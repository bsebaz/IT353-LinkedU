/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Bailey
 * Created: Nov 26, 2018
 */

drop table LinkedUDB.admins;

CREATE TABLE LinkedUDB.admins (
  adminId                  INT NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
  accountId                INT, --Foreign key to tie student accounts to user account
  email                    VARCHAR(20),
  name                     VARCHAR(20)



-- uncomment this block once we're ready to connect tables
--    CONSTRAINT FK_UserAdmin FOREIGN KEY (accountId)
--    REFERENCES LinkedUDB.accounts(accountId)
);

INSERT INTO LinkedUDB.admins (accountId, email, name) 
VALUES (1, 'abc@123.com', 'katie');
