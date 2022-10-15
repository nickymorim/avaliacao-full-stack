# Avaliacao Fullstack
## Tecnologias utilizadas
- Backend: Java 17 com o framework SpringBoot 
- Frontend: Angular 14

Spring Boot é um dos frameworks mais conhecidos e utilizados para o desenvolvimento de APIs REST com Java. Está há muitos anos no mercado e possui farta comunidade, por isso a escolha para o desenvolvimento do backend.

O Angular, assim como o Spring, é um dos frameworks mais conhecido e utilizado para desenvolvimento de SPA. Foi utilizada a sua versão mais recente.

## Como executar o projeto

No diretório do backend, com Java (17+) e Maven (3.8.2) instalados, executar os seguintes comandos:

```mvn clean install``` 

```mvn spring-boot:run```

A API estará disponível no endereço localhost:8080 e pode ser verificada no endereço

```http://localhost:8080/actuator/health```

Tambem está disponível um Swagger para testes diretos na API

```http://localhost:8080/swagger-ui.html```

Já para executar o projeto do frontend, será necessaria a instalação do Node (16.18) e do Angular CLI.

```npm install```

```ng serve -o```

A aplicação estará disponivel em ```http://localhost:4200```