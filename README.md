# People Manager

API feita com objetivo de cumprimento do desafio proposto no processo seletivo da Attornatus

## Stack

- Spring Web
- Spring Data JPA
- Banco de dados H2

Todo o projeto foi gerado através do spring boot

## Endpoints

- **POST /person/create** = Cria uma nova pessoa
- **PUT /person/update/{id}** = Atualiza as informação de uma pessoa, pelo seu id
- **GET /person/{id}** = Lê as informações (inclusive endereços) de uma pessoa pelo seu id
- **GET /person** = Lê as informações de todas as pessoas
- **POST /person/adress/add/{id}** = Adiciona um endereço para uma pessoa pelo seu id
- **GET /person/adress/{id}** = Lê os endereços de uma pessoa pelo seu id
- **GET /person/adress/main/{id}** = Lê o endereço principal de uma pessoa pelo seu id

A API também está acessível pelo endereço , além de ter uma especificação básica conforme OpenAPI no endereço.

## Rodando a aplicação

### Testes

`./gradlew test`

### Server

`./gradlew bootRun`

## Criar um jar

`./gradlew bootJar`
