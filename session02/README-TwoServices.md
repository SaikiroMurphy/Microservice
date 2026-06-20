Two independent Spring Boot services demo

1) Create databases

Run as a Postgres superuser:

```bash
psql -U postgres -f create-dbs.sql
```

2) Start services

Start the user-service:

```bash
mvn -f user-service/pom.xml spring-boot:run
```

Start the inventory-service (in another shell):

```bash
mvn -f inventory-service/pom.xml spring-boot:run
```

3) Test independence

User service (port 11000):

```bash
curl http://localhost:11000/api/users/1
```

Inventory service (port 12000):

```bash
curl http://localhost:12000/api/products/1
```

These services use different JDBC URLs (`user_db` vs `inventory_db`) and different ports, demonstrating independent DB connections.
