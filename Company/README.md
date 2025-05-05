# README

## Step One: Setup the database using the Company.sql file
  - You will need to edit the file and run it in MySQL Workbench. You can also do it from the command line, but it is easier to explore and fix problems in the GUI.

## Step Two: Get the JDBC file.
  - You will need the appropriate jar file for connecting to the database. It is essentially a library you need to connect to a database.

## Step Three: Start trying to run the code.
  - The code is mostly commented out so you can start working with it piece by piece.
  - The first step is the connection. Once we have that sorted, you can work on the more problem specific and programming pieces.

## Step Four: Run a select query, and get the results
  - In this step we run a raw SQL query and process the results. Each attribute from a tuple is then populated in a new Employee object and added to an ArrayList.

## Step Five: Encapsulation and prepareStatements
  - It is not a good idea to send raw SQL queries to a database for software engineeing and security reasons. In this example, we hide the code to interact with the database in the Database object, and use a prepareStatement to parameterize the query.

## Step Six: Database modification
  - In this step we work on inserting, updating, and deleting tuples in the database. 