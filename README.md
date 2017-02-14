# addressmanager

## _Arquitetura_ ##

- Spring Boot
- Spring JPA
- Banco de Dados MYSQL
- Java 8
- Gradle
- JUnit

A aplicação foi desenvolvida baseada no conceito de CLEAN ARCHITECTURE. Garantindo que as camadas que se interagem conheçam apenas a camada interior. Para mais informações acesse: [https://8thlight.com/](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html)

## _Para Rodar a Aplicação_ ##


Como a aplicação utiliza uma base MySQL, será necessário criar a base para que a tabela seja criada. Na aplicação, as credenciais para acessar o mysql server é: 

```
usuário: root
senha: (vazio)
porta: 3306
```

Caso utilize credenciais diferentes, acesse o arquivo [application.properties](https://github.com/jairomfj/addressmanager/blob/master/application.properties) e modifique as seguintes informações:

```
spring.datasource.url = jdbc:mysql://localhost:3306/addressmanager
spring.datasource.username = root
spring.datasource.password =
```

Após garantir que as credenciais estão corretas, conecte-se à sua base e digite o seguinte comando:

```CREATE DATABASE addressmanager;```

Para rodar a aplicaço, acesse o terminal no diretório raiz do projeto e digite o comando:

```./gradlew bootRun```

A aplicação rodará no endereço: ```http://localhost:8080/```

**Observação:** Não é necessário rodar no tomcat. Ambas as aplicações possuem um embedded server permitindo subir a aplicação e responder em uma determinada porta. 

## _Requisições_ ##

### Criar ou atualizar endereço: ###

###### CRIAR request ######

_http://localhost:8080/endereco POST_

```curl -H "Content-Type: application/json" -X POST -d '{"rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","bairro":"Bairro 00","estado":"Estado 00","complemento":"complemento 1"}' http://localhost:8080/endereco```

###### ATUALIZAR request ######

_http://localhost:8080/endereco PUT_

```curl -H "Content-Type: application/json" -X PUT -d '{"id":1, "rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","bairro":"Bairro 00","estado":"Estado 00","complemento":"complemento 1"}' http://localhost:8080/endereco```

###### response success ######

```{"mensagem":"Sucesso","endereco":{"id":9,"rua":"Rua 00","userId":1,"numero":1,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"}}```

###### response error ######

**Para testar cenarios de falha ao CRIAR:**
- Colocar um cep invalido;
- Colocar um cep diferente dos anteriores;
- Utilizar um dos ceps citados anteriormente e mudar um atributo obrigatório (rua, cidade, estado, bairro).

**Para testar cenários de falha ao ATUALIZAR:**
- Colocar um cep diferente dos anteriores;
- Utilizar um dos ceps citados anteriormente e mudar um atributo obrigatório (rua, cidade, estado, bairro);
- Colocar um id que não existe na base.

```{"mensagem":"Endereço inválido. Corrija os campos e tente novamente","endereco":null}```

### Pegar endereço: ###

###### request ######

_http://localhost:8080/endereco/{enderecoId} GET_

```curl -X GET http://localhost:8080/endereco/1```

###### response success ######

{"mensagem":"Sucesso","response":{"id":1,"rua":"Rua 00","userId":1,"numero":100,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"}}

###### response error ######

**Para testar cenários de falha:**
- Colocar um endereço que não existe na base.

```{"mensagem":"O endereco com o ID informado não foi encontrado","response":null}```

### Pegar todos os enderecos de um usuário: ###

###### request ######

_http://localhost:8080/endereco/listar/{userId} GET_

```curl -X GET http://localhost:8080/endereco/listar/1```

###### response success ######

```{"mensagem":"Sucesso","response":[{"id":1,"rua":"Rua 00","userId":1,"numero":100,"cep":"12345600","cidade":"Cidade 00","estado":"Estado 00","bairro":"Bairro 00","complemento":"complemento 1"},{"id":4,"rua":"Rua 05","userId":1,"numero":1,"cep":"12345605","cidade":"Cidade 05","estado":"Estado 05","bairro":"Bairro 05","complemento":"complemento 1"}]}```

### Deletar endereco: ###

###### request ######

_http://localhost:8080/endereco/{enderecoId} DELETE_

```curl -X DELETE http://localhost:8080/endereco/1```

###### response success ######

```{"mensagem":"Sucesso","response":null}```

###### response error ######

**Para testar cenários de falha:**
- Colocar um endereço que não existe na base.

```{"mensagem":"O endereco com o ID informado não foi encontrado","response":null}```
