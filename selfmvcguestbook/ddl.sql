CREATE TABLE SELFGUESTBOOKLOG (
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	ip varchar(255) NOT NULL,
	method varchar(10) NOT NULL,
	regdate datetime,
	PRIMARY KEY (id)
);

CREATE TABLE SELFGUESTBOOK(
	id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
	name varchar(255) NOT NULL,
	content text,
	regdate datetime,
	PRIMARY KEY (id)
);