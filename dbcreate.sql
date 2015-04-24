DROP DATABASE IF EXISTS login;
CREATE DATABASE login;
CREATE USER 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL ON login .* TO 'admin'@'localhost';
FLUSH PRIVELEGES;
USE login;

CREATE TABLE login.users(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
	username VARCHAR(20) NOT NULL,
	password VARCHAR(40) NOT NULL,
	role VARCHAR(20) NOT NULL
);

INSERT INTO login.users(username, password, role) VALUES ('yeezus', 'kanye', 'GODMODE');
INSERT INTO login.users(username, password, role) VALUES ('admin', 'admin', 'ADMIN');
INSERT INTO login.users(username, password, role) VALUES ('user', 'user', 'USER');
INSERT INTO login.users(username, password, role) VALUES ('peon', 'peon', 'PEON');
