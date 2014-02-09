INSERT INTO User(id,version,nickname,password,role,statusMessage,status) VALUES ('1','1','Luke','luke','user','Polazlem se',1)
INSERT INTO User(id,version,nickname,password,role,statusMessage,status) VALUES ('2','1','Johny','johny','user','Jem',1)
INSERT INTO User(id,version,nickname,password,role,statusMessage,status) VALUES ('3','1','Pablo','pablo','user','Jem se',1)
INSERT INTO User(id,version,nickname,password,role,statusMessage,status) VALUES ('4','1','Pablox','pablo','user','Jem se',1)

INSERT INTO Contact(id,version,isUser_id,ownUser_id,status) VALUES ('1','1',2,1,'INCOMING_CHAT')
INSERT INTO Contact(id,version,isUser_id,ownUser_id,status) VALUES ('1','1',2,4,'NONE')
INSERT INTO Contact(id,version,isUser_id,ownUser_id,status) VALUES ('2','1',2,3,'INCOMING_CHAT')