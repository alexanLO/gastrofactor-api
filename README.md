# Coccion API (Fatores de Correção e Cocção)

## 📌 Visão Geral

A API Coccion é uma aplicação backend desenvolvida com Spring Boot par auxiliar cozinheiros, nutricionistas e estudnates a calcular:

- Fator de Correção: peerda durante a limpeza/preparação (ex: expansão do arroz, encolhimento da carne).
- Fator de Cocção: perda ou ganho durante o cozimento (ex: expansão do arroz,e ncolhimento da carne).

Este projeto faz parte do meu portfólio e visa demonstrar habilidades em Java, Spring Boot, APIs REST e arquitetura hexagonal. Planos futuros incluem um frontend em Angular/Typescript para consumir esta API ou transforma em um app.

## 🛠️ Tecnologias:

- Java 21
- Spring Boot 3+
- Spring Web
- Spring Data JPA (Hibernate)
- PostgreSQL
- Swagger / OpenAPI
- Lombok
- Flyway
- Maven
- JUnit & Mockito (para testes)

## 📊 Conceitos de Domínio

Ingrediente: qualquer alimento usado para cálculo (vegetais, carne, grãos, etc.).
Fator de Correção (FC): Fórmula → PesoGross / PesoLíquido
Fator de Cozimento (FCC): Fórmula → PesoCozido / PesoCru

## 📁 Estrutura do Projeto

```bash
src/
 └── main/
     ├── java/
     │   └── br/com/coccionaapi/
     │       ├── adapter/   
     │       │   ├── input/
     │       │   │   ├── controller/
     │       │   │   ├── dto/
     │       │   │   └── mapper/
     │       │   └── output/
     │       │       ├── entity/
     │       │       ├── repository/
     │       │       └── mapper/
     │       ├── domain/
     │       │       ├── model/
     │       │       └── service/
     │       ├── port/
     │       │   ├── input/
     │       │   └── output/
     │       └── config/
     └── resources/
         ├── application.yml
         ├── static/
         └── db/
```

## 🛡️ Segurança
Este projeto não inclui dados sensíveis. O arquivo .env está ignorado via .gitignore. Use o .env.example como referência para configurar seu ambiente local.

## 🚀 Começando

1. Clone o repositório: git clone Link
2. Configure o banco de dados:
    Edite src/main/resources/application.yml:

    ```yml
    spring:
    datasource:
        url: jdbc:postgresql://localhost:5432/coccionapi
        username: your_user
        password: your_password
    jpa:
        hibernate:
        ddl-auto: update
        show-sql: true
    ```

3. Crie o arquivo .env:

    `cp .env.exemplo .env`
    preencha com suas configurações:

    DB_HOST=localhost  
    DB_PORT=5432  
    DB_NAME=cocciona  
    DB_USER=seu_usuario  
    DB_PASS=sua_senha  

4. Execute a aplicação

    ```bash
    ./mvnw springboot:run
    ```

A API estará disponível em: 👉 [Swagger](http://localhost:8081/swagger-ui/index.html#/)

## 📖 Endpoints da API (MVP)

/Ingredients

- POST /ingredient → criar ingrediente
- GET /ingredient → listar ingredientes
- GET /ingredient/{id}/correction-factor → obter fator-de-correção
- GET /ingredient/{id}/cooking-factor → obter fator-de-cozimento

## 🔮 Roteiro

- Backend MVP (API Spring Boot)
- Adicionar histórico de cálculos
- Adicionar exportação para PDF/Excel
- Implementar autenticação e usuários
- Frontend em Angular

## 👩‍💻 Autor

Desenvolvido por **Alexan Matheusa Lima de Oliveira** — minha inspiração para api foi meu companheiro de vida que é cozinheiro.

[Site Pessoal](Futuramente)  
[Linkedin](https://www.linkedin.com/in/alexan-limaa/)
