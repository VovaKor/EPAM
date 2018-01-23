# Final project
### Project description
 **11.** Система **Автопарк**. В систему могут зайти **Водители** и **Администраторы**. В **Автопарке** есть автобусы, маршруты. **Администратор** может назначать на **Маршруты** свободные автобусы, в автобусы свободных **Водителей**, а также освобождать их, делая свободными. **Водитель** может увидеть свое место работы, а также он должен подтвердить свое новое **Назначение**.
 
###  Installation
This installation assumes you have already installed Apache Tomcat server and MySQL database.

Put `mysql-connector-java-6.0.6.jar` in the `$CATALINA_HOME/lib` directory. 

Check your `<Resource/>` declaration in `$CATALINA_BASE/conf/server.xml`. Database connection URL must contain parameter `nullNamePatternMatchesAll` set to `true`, for example
```
<Resource ...
          ...
          url="jdbc:mysql://localhost:3306/your_database_name?useSSL=false&amp;nullNamePatternMatchesAll=true"
          ...
          ... />
```
Check character encoding in your database: 

```
mysql> SELECT default_character_set_name FROM information_schema.SCHEMATA 
WHERE schema_name = "your_database_name";
```
It must be set to UTF8:
```
+----------------------------+
| default_character_set_name |
+----------------------------+
| utf8                       |
+----------------------------+
```
If it's not
```
mysql> ALTER DATABASE databasename CHARACTER SET utf8 COLLATE utf8_unicode_ci;
```
Remove your existing `$CATALINA_BASE/webapps/ROOT` directory and/or `$CATALINA_BASE/webapps/ROOT.war` file and place provided `ROOT.war` instead.
### Quick start

**1.** Start your Tomcat server.

**2.** Open your browser and go to your web site page, for example 
````
http://localhost:8080/
````
**3.** Default director email `director@director.com` and password `director`. Now you can change it into your own. 