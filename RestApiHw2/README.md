# REST API
## Descripition
#### 1. This homework require to build a rest api.
#### 2. My project have two entities: Product and Customer. The relationship is Many to Many
#### 3. Write basic CRUD operation logic. Both entity can add, addAll, findbyid, findbyname, delete, and update; Customer have a operation called enrolledProduct
#### 4. Use log4j2 build system's log part
#### 5. Connect with Mysql, and use postman test CRUD operation.
## Bug I met during learning
#### 1. Use @JasonIgnore on both entity's set; Miss annotation cause stackoverflow error.
#### 2. Log4j2 have dpendency conflict. Exclude logback in pom.xml.
#### 3. Connection error: use Mysql5Dialect instead of MysqlDialect in configuration file.

