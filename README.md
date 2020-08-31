<h1 align="center">
  DB - Praktikum
</h1>

<h4 align="center">Sommersemester 2020</h4>
<h4 align="center">Georg Walther, Robin Seidel</h4>

## How it works
- creates a database using PostgreSQL containing the data of a social network micro world
- data is filled from given sources into the tables

## Getting Started

### Use Docker-Compose

```
# Install and start Docker demon
# Build and Run the Database
docker-compose up --build

# Install psql (PostgreSQL interactive terminal)
# Run the database setup on Windows using
cd database
runDB.bat
# Make sure the path to psql in .\database\runDB.bat fits your postgres version

# Transform into relation-model
- Inside com.dbfiller.Main.java, put in the password of your local DB
--> DriverManager.getConnection(..., ..., <PASSWORD>)
- Run com.dbfiller.Main.java
```