CREATE DATABASE IF NOT EXISTS addressbook;
USE addressbook;
DROP TABLE IF EXISTS address;
CREATE TABLE address (	
	firstName	varchar(50),
	middleName	varchar(50),
	lastName	varchar(50),
	mobileNumber	int,
	workNumber	int,
	email		varchar(50),
	primary key (firstName)
);
	



