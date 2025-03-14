CREATE DATABASE hansen5841_Company_JDBC; -- change to be your own database!
USE hansen5841_Company_JDBC; -- change to be your own database!
-- DROP DATABASE ssfoley_Company_JDBC;  -- for testing and in case it gets screwed up and you want to start from scratch

CREATE TABLE Employee (
  SSN NVARCHAR(11) PRIMARY KEY NOT NULL,-- NVARCHAR(11) NVARCHAR(9) INT
  Salary NUMERIC(8,2) NOT NULL,
  FirstName NVARCHAR(10) NOT NULL,
  MiddleName NVARCHAR(10),
  LastName NVARCHAR(10) NOT NULL
);

CREATE TABLE Project(
  ID INTEGER PRIMARY KEY AUTO_INCREMENT,
  Description NVARCHAR(255)
);

CREATE TABLE Works_On(
  ID INTEGER NOT NULL,
  SSN NVARCHAR(11) NOT NULL,
  Hours INTEGER NOT NULL,
  CONSTRAINT fk_project_id FOREIGN KEY (ID) REFERENCES Project(ID),
  CONSTRAINT fk_employee_ssn FOREIGN KEY (SSN) REFERENCES Employee(SSN)
);

INSERT INTO Employee(SSN, Salary, FirstName, LastName) VALUES ('123-45-6789', 123445.00, 'John', 'Smith');
INSERT INTO Employee(SSN, Salary, FirstName, MiddleName, LastName) VALUES ('123-45-6799', 13445.00, 'James', 'Tom', 'Lee');
INSERT INTO Employee(SSN, Salary, FirstName, LastName) VALUES ('123-45-9876', 600000.00, 'Ada', 'Lovelace');
INSERT INTO Employee(SSN, Salary, FirstName, MiddleName, LastName) VALUES ('123-54-6799', 903445.00, 'Grace', 'Murray', 'Hopper');


INSERT INTO Project(Description) VALUES ('A test project.');
INSERT INTO Project(Description) VALUES ('Another test project.');
INSERT INTO Project(Description) VALUES ('Project Awesomesauce');
INSERT INTO Project(Description) VALUES ('Dumpster fire');


INSERT INTO Works_On(ID, SSN, Hours) VALUES (4, '123-45-6799', 3);
INSERT INTO Works_On(ID, SSN, Hours) VALUES (3, '123-45-9876', 33);
INSERT INTO Works_On(ID, SSN, Hours) VALUES (3, '123-54-6799', 13);
INSERT INTO Works_On(ID, SSN, Hours) VALUES (1, '123-45-6799', -3);
INSERT INTO Works_On(ID, SSN, Hours) VALUES (4, '123-45-6789', 5);
INSERT INTO Works_On(ID, SSN, Hours) VALUES (1, '123-54-6799', 3);

-- SELECT * FROM Project NATURAL JOIN Works_On NATURAL JOIN Employee;
