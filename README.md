# Marathon project Study Platform.
#### Steps for deploying 
* Run project:
```
mvn spring-boot:run
```
* Run tests:
```
mvn test
```
* Run sql script in 
```
/resources/sql/fill_all_tables
```
* Script will create three users and one admin. 
Table of emails and passwords for authentication:

Login | Password
------------ | -------------
user1@gmail.com | user1
user2@gmail.com | user2
user2@gmail.com | user2
user3@gmail.com | user3
admin@gmail.com | admin

