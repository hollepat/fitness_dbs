
--------- Create table ----------

CREATE TABLE IF NOT EXISTS Person (
	nationalId CHAR(11) PRIMARY key,
	phone CHAR(11) UNIQUE,
	birth DATE CHECK (birth < now()),
	firstName VARCHAR(120) NOT NULL,
	lastName VARCHAR(120) NOT NULL,
	city VARCHAR(120) NOT NULL,
	street VARCHAR(120) NOT NULL, 
	postalCode char(6) NOT NULL,
	CONSTRAINT nationalId_check CHECK (nationalId ~ '^[0-9]{6}\/[0-9]{4}$'),
	CONSTRAINT postalCode_check CHECK (postalcode ~ '^[0-9]{3}[[:blank:]][0-9]{2}$'),
	CONSTRAINT phone CHECK (phone ~ '^[0-9]{3}[[:blank:]][0-9]{3}[[:blank:]][0-9]{3}')
);



CREATE TABLE IF NOT EXISTS Employee (
    nationalId CHAR(11) PRIMARY KEY,
    contractNumber INT UNIQUE,
    workPosition VARCHAR(120),
    salary DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (nationalId) REFERENCES Person (nationalId) ON DELETE CASCADE,
	CONSTRAINT nationalId_check CHECK (nationalId ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS Customer (
	nationalId CHAR(11) PRIMARY KEY,
	FOREIGN KEY (nationalId) REFERENCES Person (nationalId) ON DELETE CASCADE,
	CONSTRAINT nationalId_check CHECK (nationalId ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS Trainer (
	nationalId CHAR(11) PRIMARY KEY,
	FOREIGN KEY (nationalId) REFERENCES Person (nationalId) ON DELETE CASCADE,
	CONSTRAINT nationalId_check CHECK (nationalId ~ '^[0-9]{6}\/[0-9]{4}$')
);


CREATE TABLE IF NOT EXISTS Supervise (
    supervised CHAR(11) PRIMARY KEY,
    supervisor CHAR(11) NOT NULL,
    FOREIGN KEY (supervised) REFERENCES Employee(nationalId) ON DELETE CASCADE,
    FOREIGN KEY (supervisor) REFERENCES Employee(nationalId) ON DELETE CASCADE,
	CONSTRAINT supervised_check CHECK (supervised ~ '^[0-9]{6}\/[0-9]{4}$'),
	CONSTRAINT supervisor_check CHECK (supervisor ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS Invoice (
	invoiceNumber INT PRIMARY KEY,
	employee CHAR(11) NOT NULL,
	dateOfIssue DATE DEFAULT now(),
	cost DECIMAL(10, 2) NOT NULL,
	FOREIGN KEY (employee) REFERENCES Employee (nationalId) ON DELETE SET NULL,
	CONSTRAINT cost_check CHECK (cost >= 0),
	CONSTRAINT dateOfIssue_check CHECK (dateOfIssue <= now()),
	CONSTRAINT employee_check CHECK (employee ~ '^[0-9]{6}\/[0-9]{4}$')
	
);

CREATE TABLE IF NOT EXISTS Membership (
	type VARCHAR(120),
	customer CHAR(11),
	invoice INT,
	expireDate Date NOT NULL,
	cost DECIMAL(10, 2) DEFAULT 0,
	PRIMARY KEY (type, customer, invoice),
	FOREIGN KEY (customer) REFERENCES Customer (nationalId) ON DELETE CASCADE,
	FOREIGN KEY (invoice) REFERENCES Invoice (invoiceNumber) ON DELETE CASCADE,
	CONSTRAINT cost_check CHECK (cost >= 0),
	CONSTRAINT customer_check CHECK (customer ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS TrainersLicense (
	trainer CHAR(11),
	license VARCHAR(120),
	PRIMARY KEY (trainer, license),
	FOREIGN KEY (trainer) REFERENCES Trainer (nationalId) ON DELETE CASCADE,
	CONSTRAINT trainer_check CHECK (trainer ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS WorkoutClass(
	name VARCHAR(120),
	day DATE,
	time TIME,
	teacher CHAR(11),
	capacity INT,
	PRIMARY KEY (name, day, time, teacher),
	CONSTRAINT class_capacity CHECK (capacity >= 0),
	CONSTRAINT teacher_fk 
		FOREIGN KEY (teacher) REFERENCES Trainer (nationalId)
		ON DELETE CASCADE ,
	CONSTRAINT teacher_check CHECK (teacher ~ '^[0-9]{6}\/[0-9]{4}$')
);

CREATE TABLE IF NOT EXISTS TakeClass(
	customer CHAR(11),
	name VARCHAR(120),
	day DATE,
	time TIME,
	teacher CHAR(11),
	price DECIMAL(10, 2) NOT NULL,
	PRIMARY KEY (customer, name, day, time, teacher),
	CONSTRAINT customer_fk
		FOREIGN KEY (customer) REFERENCES Customer (nationalId)
		ON DELETE CASCADE ,
	CONSTRAINT teacher_fk 
		FOREIGN KEY (teacher) REFERENCES Trainer (nationalId)
		ON DELETE CASCADE ,
	CONSTRAINT check_price CHECK (price >= 0),
	CONSTRAINT customer_check CHECK (customer ~ '^[0-9]{6}\/[0-9]{4}$'),
	CONSTRAINT teacher_check CHECK (teacher ~ '^[0-9]{6}\/[0-9]{4}$')
);


--------- Drop table ------------
-- DROP VIEW powerlifting_classes;
-- DROP TABLE TakeClass;
-- DROP TABLE WorkoutClass;
-- DROP TABLE TrainersLicense;
-- DROP TABLE Membership;
-- DROP TABLE Invoice;
-- DROP TABLE Supervise;
-- DROP TABLE Employee;
-- DROP TABLE Trainer;
-- DROP TABLE Customer;
-- DROP TABLE Person;
