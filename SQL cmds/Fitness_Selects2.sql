
----- SELECTS -----
/*SELECT nationalid
FROM trainer
ORDER BY RAND()
LIMIT 1;*/

SELECT * FROM employee
ORDER BY nationalid ASC;
SELECT * FROM takeclass;
SELECT "type" FROM membership;

--------- transaction ---------
-- transaction to add customer to model, if transaction would not be used than there could be added a new customer which would be given same invoicenumber --> konflikt
DROP FUNCTION addmember(nationalid CHAR(11), phone CHAR(11), firstName VARCHAR(120), lastName VARCHAR(120), city VARCHAR(120), street VARCHAR(120), postalCode CHAR(6), typ VARCHAR(120));
CREATE OR REPLACE FUNCTION addMember(nationalid CHAR(11), phone CHAR(11), firstName VARCHAR(120), lastName VARCHAR(120), city VARCHAR(120), street VARCHAR(120), postalCode CHAR(6), typ VARCHAR(120))
RETURNS BOOLEAN
AS $$
	DECLARE
		emp CHAR(11);
		invoiceid INT;
		setprice INT;
	BEGIN
		-- create customer
		INSERT INTO person (nationalid, phone, firstName, lastName, city, street, postalCode) VALUES (nationalid, phone,firstName, lastName, city, street, postalCode);
		INSERT INTO customer VALUES (nationalid);
		-- create membership
		emp := (SELECT employee.nationalid FROM employee LIMIT 1);
		setprice := 10;
		invoiceid := 1+(SELECT invoicenumber FROM invoice ORDER BY invoicenumber DESC LIMIT 1);
		INSERT INTO invoice VALUES (invoiceid, emp, now(), 10);
		INSERT INTO membership VALUES (typ, nationalid, invoiceid, '2024-05-08', setprice);
		RETURN true;
	END;
$$
LANGUAGE plpgsql;

BEGIN TRANSACTION ISOLATION LEVEL SERIALIZABLE;
SELECT addMember('100918/9376', '003 558 328', 'Marketa', 'Holubova', 'Liberec', 'Sadová', '458 98', 'Basic');
COMMIT TRANSACTION;

SELECT * FROM person WHERE (person.firstname = 'Marketa') and (person.lastname = 'Holubova');
 
-------- view --------
/* View containing all PowerLifting classes.*/
DROP VIEW PowerLifting_classes;
CREATE VIEW PowerLifting_classes AS
SELECT * FROM workoutclass WHERE (workoutclass."name" = 'PowerLifting')
WITH LOCAL CHECK OPTION;	-- only in this view (to update even in parents views use cascade)
 
-- insert new PowerLifting class
INSERT INTO PowerLifting_classes VALUES (3000, 'PowerLifting', '2022-03-09','17:00:00', '699937/4749', 20);

-- denied insert because name is 'CrossFit' and not PowerLifting, which is condition for view
INSERT INTO powerlifting_classes VALUES (3001, 'CrossFit', '2022-03-09','17:00:00', '699937/4749', 20);

-- show powerlifting_classes
SELECT * FROM powerlifting_classes
ORDER BY "day" ASC;

-------- trigger --------
-- trigger function checking price paid for taking class
CREATE OR REPLACE FUNCTION check_price()
RETURNS TRIGGER AS $$
	BEGIN 
		IF ((NEW.price IS NULL) OR (NEW.price > 200)) THEN
			RAISE EXCEPTION 'Invalid price value';
		ELSE
			RETURN NEW; 
		END IF;
	END;
$$
LANGUAGE plpgsql;
-- trigger, which check every row (that is being INSERTED OR UPDATED) for check_price() conditions
CREATE OR REPLACE TRIGGER class_tg_class_price BEFORE INSERT OR UPDATE ON takeclass
FOR EACH ROW EXECUTE PROCEDURE check_price();
-- this insert will fail with exception 'Invalid price value'
INSERT INTO takeclass VALUES (60295, '100918/9376', 150);


------- index ------
DROP INDEX IF EXISTS person_idx_firstName;
explain (analyze) SELECT * FROM person order by person.firstname;
-- index that can help to find faster the biggest invoicenumber used so far
CREATE INDEX person_idx_firstName ON person(firstName);

-- this is how we can get the biggest invoice number from index
SELECT MAX(invoiceNumber) FROM Invoice;
