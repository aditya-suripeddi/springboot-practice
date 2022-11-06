
The code for this repo is taken from [Spring Boot Database Migrations with Flyway Example | JavaTechie](https://www.youtube.com/watch?v=w12DsiqpxEg) 
to understand and get started using flyway by implementing a simple database migration.

With flyway you can keep the database migration scripts required / expected by code with the repository itself. 
The scripts that modify database are run when the spring boot application starts.

### flyway_schema_history

flyway keeps a separate table <b>flyway_schema_history</b> to track the database changes. It
refers this table to evaluate from which point it needs to start applying the database version changes as deltas like git.

### Setup

Create a database with name <em>sample</em> in mysql on your local.
Update the application.properties with database access credentials 


### Simulation

The previous commit, say, only has <em>resources/db/migration/V1.1__createTable.sql</em> which populates
the USERS and SOCIAL_SECURITY table

Later, requirements have changed and there is a need to add a mobile column to USERS table with contents of 
mobile column from SOCIAL_SECURITY table (when ssid of user matches with that in social security table)

This commit has code and migration script as per above requirement changes

This migration is defined in <em>resources/db/migration/V2.1__addColumn.sql</em>.  

The code also changes, <em>mobile</em> attribute is added to <em>model/User.java</em> to support
database read operations from USER table as part of migration described above

You can notice that on adding the <em>resources/db/migration/V2.1__addColumn.sql</em> and running the app
locally the <b>flyway_schema_history table</b> registers a new record with a commit for this schema change
and updates the USERS database with mobile column <b>before spring boot server starts</b>

Notice the logs for flyway validations and messages of successful migrations / failed migrations


### References
 
1. [Spring Boot Database Migrations with Flyway Example | JavaTechie](https://www.youtube.com/watch?v=w12DsiqpxEg)

2. [Flyway website](https://flywaydb.org/)
