USE login;
CREATE TABLE login.data(
	role VARCHAR(20) NOT NULL;
	room VARCHAR(40) NOT NULL;
	accesscode VARCHAR(5) NOT NULL	
);

INSERT INTO login.data(role, room, accesscode) VALUES ('GODMODE','Master Control Room', '20884');
INSERT INTO login.data(role, room, accesscode) VALUES ('ADMIN','Biohazard Lab', '11764');
INSERT INTO login.data(role, room, accesscode) VALUES ('USER','Cubicle Sector', '33642');
INSERT INTO login.data(role, room, accesscode) VALUES ('PEON','Bathroom', '10101');