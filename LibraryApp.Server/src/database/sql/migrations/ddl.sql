CREATE TABLE author(
ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
firstname VARCHAR(200),
lastname VARCHAR(200) NOT NULL
);

CREATE TABLE MEMBER(
ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
firstname VARCHAR(200) NOT NULL,
lastname VARCHAR(200) NOT NULL,
birthday DATE NOT NULL,
email VARCHAR(255)
);

CREATE TABLE building(
ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
NAME VARCHAR(100) NOT NULL
);

CREATE TABLE employee(
ID BIGINT PRIMARY KEY NOT NULL,
firstname VARCHAR(200) NOT NULL,
lastname VARCHAR(200) NOT NULL,
PASSWORD VARCHAR(128) NOT NULL,
buildingID BIGINT NOT NULL,
CONSTRAINT employee_building_fk FOREIGN KEY (buildingID) REFERENCES building(ID) ON UPDATE CASCADE
);

CREATE TABLE book(
ID BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
title VARCHAR(200) NOT NULL,
DESCRIPTION TEXT,
authorID BIGINT NOT NULL,
CONSTRAINT book_author_fk FOREIGN KEY (authorID) REFERENCES author(ID) ON UPDATE CASCADE
);

CREATE  TABLE copyofbook(
ID BIGINT NOT NULL AUTO_INCREMENT,
bookID BIGINT NOT NULL,
buildingID BIGINT NOT NULL,
PRIMARY KEY(ID, bookID),
CONSTRAINT copyofbook_book_fk FOREIGN KEY (bookID) REFERENCES book(ID) ON UPDATE CASCADE,
CONSTRAINT copyofbook_building_fk FOREIGN KEY (buildingID) REFERENCES building(ID) ON UPDATE CASCADE
);

CREATE TABLE lending(
ID BIGINT NOT NULL,
bookID BIGINT NOT NULL,
copyofbookID BIGINT NOT NULL,
memberID BIGINT NOT NULL,
lending_date DATE NOT NULL,
return_date DATE,
PRIMARY KEY(ID, bookID, copyofbookID, memberID),
CONSTRAINT lending_book_fk FOREIGN KEY (bookID) REFERENCES book(ID) ON UPDATE CASCADE,
CONSTRAINT lending_copyofbook_fk FOREIGN KEY (copyofbookID) REFERENCES copyofbook(ID) ON UPDATE CASCADE,
CONSTRAINT lending_member_fk FOREIGN KEY (memberID) REFERENCES MEMBER(ID) ON UPDATE CASCADE
);