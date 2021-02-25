CREATE TABLE BUSINESSCARD (
	name varchar(10) NOT NULL,
	phone char(13),
	companyname varchar(12),
	createdate datetime default now()
);