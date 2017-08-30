# addressmanager

## _Technologies_ ##

- Spring Boot
- Spring JPA
- MYSQL
- Java 8
- Gradle
- JUnit

This application was developed based on the [CLEAN ARCHITECTURE](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) concept.

## _Setup_ ##

Since the application connects to a MySQL database, it will be necessary to connect to the DB and create the schema. The default credentials to connect to the database server is:

```
user: root
pass: (empty)
port: 3306
```

In case your credentials is different, modify the file [application.properties](https://github.com/jairomfj/addressmanager/blob/master/application.properties)

```
spring.datasource.url = jdbc:mysql://localhost:3306/addressmanager
spring.datasource.username = root
spring.datasource.password =
```

After setting up the credentials and connect to your database, run the following command:

```CREATE DATABASE addressmanager;```

To run your application, open the terminal in the project's directory and run:

```./gradlew bootRun```

The application will be running on: ```http://localhost:8080/```

## _Requests_ ##

### Create or update an address: ###

###### CREATE ######

_http://localhost:8080/endereco POST_

```curl -H "Content-Type: application/json" -X POST -d '{"rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","bairro":"Bairro 00","estado":"Estado 00","complemento":"complemento 1"}' http://localhost:8080/endereco```

###### UPDATE ######

_http://localhost:8080/endereco PUT_

```curl -H "Content-Type: application/json" -X PUT -d '{"id":1, "rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","bairro":"Bairro 00","estado":"Estado 00","complemento":"complemento 1"}' http://localhost:8080/endereco```

###### response success ######

```{"mensagem":"Sucesso","endereco":{"id":9,"rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"}}```

###### response error ######

**Test failure cenario - CREATE:**
- Input an invalid CEP;
- Input and CEP already persisted;

**Test failure cenario - UPDATE:**
- Input a CEP not persisted;
- Input a CEP not persisted with different address (rua, cidade, estado, bairro);
- Input an ID that does not exist.

```{"mensagem":"Endereço inválido. Corrija os campos e tente novamente","endereco":null}```

### GET: ###

###### request ######

_http://localhost:8080/endereco/{enderecoId} GET_

```curl -X GET http://localhost:8080/endereco/1```

###### response success ######

{"mensagem":"Sucesso","response":{"id":1,"rua":"Rua 00","userId":1,"numero":100,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"}}

###### response error ######

**Test failure cenario:**
- Execute the request passing an enderecoId that wasn't previously added to the database.

```{"mensagem":"O endereco com o ID informado não foi encontrado","response":null}```

### Get all user addresses: ###

###### request ######

_http://localhost:8080/endereco/listar/{userId} GET_

```curl -X GET http://localhost:8080/endereco/listar/1```

###### response success ######

```{"mensagem":"Sucesso","response":[{"id":1,"rua":"Rua 00","userId":1,"numero":100,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"},{"id":4,"rua":"Rua 05","userId":1,"numero":1,"cep":"12345605","cidade":"Cidade 05","estado":"Estado 05","bairro":"Bairro 05","complemento":"complemento 1"}]}```

### Delete address: ###

###### request ######

_http://localhost:8080/endereco/{enderecoId} DELETE_

```curl -X DELETE http://localhost:8080/endereco/1```

###### response success ######

```{"mensagem":"Sucesso","response":null}```

###### response error ######

**Test failure cenario:**
- Execute the request passing an enderecoId that wasn't previously added to the database.

```{"mensagem":"O endereco com o ID informado não foi encontrado","response":null}```
