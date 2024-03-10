----- SELECT COMMANDS helper -------
-- SELECT * FROM person;
-- SELECT * FROM employee;
-- SELECT * FROM supervise;
SELECT * FROM workoutclass;
SELECT * FROM takeclass;
-- SELECT * FROM invoice
-- ORDER BY invoice.moneycost DESC;

--- DELETE ALL -----
-- DELETE FROM TakeClass *;
-- DELETE FROM WorkoutClass *;
-- DELETE FROM TrainersLicense *;
-- DELETE FROM Membership *;
-- DELETE FROM Invoice *;
-- DELETE FROM Supervise *;
-- DELETE FROM Employee *;
-- DELETE FROM Trainer *;
-- DELETE FROM Customer *;
-- DELETE FROM Person *;

--- outer join ---
/*
Joins table Person and Membership on same nationalid of person/customer and only person from city Hluk. 
Result is rows selected by SELECT clause when they meet condition and also row where one of
conditions is missing.
*/
SELECT person.firstname, person.lastname, person.city ,membership.type 
FROM membership 
FULL OUTER JOIN person
ON (person.nationalid = membership.customer) 
WHERE (person.city = 'Hluk');

SELECT cost, cost*2 as c FROM invoice;
--- inner join ---
/*
Joins table Person and WorkoutClass on same nationalid of person/teacher. Result 
contains only persons who match some teacher in workoutclasses. And where capacity < 10.
*/
SELECT person.*, workoutclass.name, workoutclass.capacity 
FROM person 
INNER JOIN workoutclass
ON (person.nationalid = workoutclass.teacher) 
WHERE (workoutclass.capacity < 10);

--- condition on data ---
/*
SELECT all workoutclasses that start after 12:00:00.
*/
SELECT * FROM workoutclass 
WHERE (workoutclass."time" >= '12:00:00');

--- agregaci a podmínku na hodnotu agregační funkce ---
/*
Takes all types of workout classes and compute average price, which each customer paid for this type of class. Then
filter those having average > 12 and order them in ascending order by average.
*/

SELECT workoutclass.name, AVG(takeclass.price) AS average
FROM takeclass INNER JOIN workoutclass ON takeclass.id = workoutclass.id
GROUP BY workoutclass.name
ORDER BY avg(takeclass.price) DESC ;
--HAVING AVG(takeclass.price) > 12.6;

-- not working
-- FROM takeclass
-- GROUP BY takeclass.name
-- HAVING AVG(takeclass.price) > 12
-- ORDER BY average;


SELECT name, SUM(capacity) AS SUM FROM workoutclass
group by NAME;
--- řazení a stránkování ---
/*
SELECT all membershiptypes only once and order them in ascendend order.
*/
SELECT DISTINCT "type" FROM membership
ORDER BY "type" ASC;

--- množinové operace ---
/*
Query return union of trainer and customer table without duplicities.
*/
SELECT * FROM trainer
UNION
SELECT * FROM customer;

--- vnořený SELECT ---
/*
Query returns table with first name and last name of employee from person table 
and his position from employee table. At last it filter only those who has
over average salary.
*/

SELECT p.firstName, p.lastName, e.workposition
FROM person AS p
JOIN employee AS e ON e.nationalid = p.nationalid
WHERE (e.salary > (SELECT AVG(salary) FROM employee));

