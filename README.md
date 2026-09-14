# Loja API

API REST simples em **Java Spring Boot** para gerenciar **Cliente**, **Produto** e **Pedido**, com persistência em banco **H2 local**. O projeto segue o padrão **MVC** (Model, Controller e Service + Repository).

## Tecnologias

- Java 17
- Spring Boot 3.4
- Spring Web, Spring Data JPA e Bean Validation
- Banco H2 (arquivo local em `data/loja`)
- Maven

## Pré-requisitos

- JDK 17 ou superior
- IntelliJ IDEA, Eclipse ou outra IDE com suporte a Maven
- Maven 3.9+ (somente se for executar pelo terminal)
- Postman (para importar a collection de testes)

## Como executar

### Pela IDE 

**IntelliJ IDEA**

**Eclipse**

### Pelo terminal (Maven oficial)

Com o Maven instalado, na pasta raiz do projeto:

```bash
mvn spring-boot:run
```

A API sobe em:

- **API:** http://localhost:8080
- **Console H2:** http://localhost:8080/h2-console

Dados de conexão do H2:

- **JDBC URL:** `jdbc:h2:file:./data/loja`
- **User:** `sa`
- **Password:** (vazio)

## Rotas da API

### Clientes (`/api/clientes`)

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/api/clientes` | Lista todos os clientes |
| GET | `/api/clientes/{id}` | Busca cliente por ID |
| GET | `/api/clientes/nome/{nome}` | Busca cliente por nome |
| GET | `/api/clientes/count` | Conta clientes |
| POST | `/api/clientes` | Cria cliente |
| PUT | `/api/clientes/{id}` | Atualiza cliente |
| DELETE | `/api/clientes/{id}` | Remove cliente |

Exemplo de body (POST/PUT):

```json
{
  "nome": "Ana Souza",
  "email": "ana@email.com"
}
```

### Produtos (`/api/produtos`)

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/api/produtos` | Lista todos os produtos |
| GET | `/api/produtos/{id}` | Busca produto por ID |
| GET | `/api/produtos/nome/{nome}` | Busca produto por nome |
| GET | `/api/produtos/count` | Conta produtos |
| POST | `/api/produtos` | Cria produto |
| PUT | `/api/produtos/{id}` | Atualiza produto |
| DELETE | `/api/produtos/{id}` | Remove produto |

Exemplo de body (POST/PUT):

```json
{
  "nome": "Teclado",
  "preco": 150.00
}
```

### Pedidos (`/api/pedidos`)

| Método | Rota | Descrição |
| --- | --- | --- |
| GET | `/api/pedidos` | Lista todos os pedidos |
| GET | `/api/pedidos/{id}` | Busca pedido por ID |
| GET | `/api/pedidos/cliente/{nome}` | Busca pedidos pelo nome do cliente |
| GET | `/api/pedidos/produto/{nome}` | Busca pedidos pelo nome do produto |
| GET | `/api/pedidos/count` | Conta pedidos |
| POST | `/api/pedidos` | Cria pedido |
| PUT | `/api/pedidos/{id}` | Atualiza pedido |
| DELETE | `/api/pedidos/{id}` | Remove pedido |

Exemplo de body (POST/PUT):

```json
{
  "clienteId": 1,
  "produtoId": 1
}
```

A data do pedido é preenchida automaticamente no servidor.

## Testes no Postman

1. Abra o Postman.
2. Use **Import** e selecione o arquivo `postman/Loja-API.postman_collection.json`.
3. Com a API rodando, execute as requisições na ordem: criar cliente, criar produto e depois criar pedido.

## Estrutura MVC

```text
src/main/java/com/loja/api
├── controller   # Recebe as requisições HTTP
├── service      # Regras simples de negócio
├── repository   # Acesso ao banco (Spring Data JPA)
├── model        # Entidades Cliente, Produto e Pedido
├── dto          # Dados de entrada do pedido
└── exception    # Tratamento de erros (404 / validação)
```

Fluxo: **Controller → Service → Repository → H2**.

## Documentação C4

O modelo C4 do projeto está no arquivo dentro do diretorio docs (contexto, containers, componentes e código).

Modelo C4: https://drive.google.com/file/d/18ZLvxnJmj-aNWsPR6lmnirdfzafdUZqK/view?usp=sharing