# 📝 API de Tasks

API RESTful para gerenciamento de **Tasks**, permitindo criar, listar, atualizar e deletar tarefas.

---

## ⚡ Como funciona

A API segue o padrão **CRUD** (Create, Read, Update, Delete):

1. **Create** → Cria uma nova task no banco de dados.
2. **Read** → Lista todas as tasks ou busca uma task por ID.
3. **Update** → Atualiza uma task existente.
4. **Delete** → Remove uma task do banco.

Cada task possui os seguintes campos:

```json
{
  "id": 1,               // ID único gerado automaticamente
  "title": "Título",     // Título da task (obrigatório)
  "description": "Descrição da task", // Detalhes da task
  "completed": false     // Status da task (default: false)
}
```

O backend utiliza **Spring Boot + JPA + PostgreSQL**, com endpoints REST consumindo/produzindo **JSON**.

---

## 🌐 Base URL

```
http://localhost:8080/tasks
```

---

## 🔹 Endpoints

### 1. Listar todas as tasks

```
GET /tasks
```

**Resposta 200 OK**

```json
[
  {
    "id": 1,
    "title": "Comprar leite",
    "description": "Comprar leite no supermercado",
    "completed": false
  }
]
```

**Explicação:** Retorna todas as tasks cadastradas no banco.

---

### 2. Buscar task por ID

```
GET /tasks/{id}
```

**Parâmetros**

* `id` → ID da task

**Resposta 200 OK**

```json
{
  "id": 1,
  "title": "Comprar leite",
  "description": "Comprar leite no supermercado",
  "completed": false
}
```

**Resposta 404 Not Found**

```json
{
  "error": "Task não encontrada"
}
```

**Explicação:** Busca uma task específica pelo seu ID.

---

### 3. Criar uma nova task

```
POST /tasks
```

**Body (JSON)**

```json
{
  "title": "Nova Task",
  "description": "Descrição da task",
  "completed": false
}
```

**Resposta 201 Created**

```json
{
  "id": 3,
  "title": "Nova Task",
  "description": "Descrição da task",
  "completed": false
}
```

**Explicação:** Cria uma nova task e retorna o objeto criado com ID gerado pelo banco.

---

### 4. Atualizar uma task

```
PUT /tasks/{id}
```

**Parâmetros**

* `id` → ID da task

**Body (JSON)**

```json
{
  "title": "Task Atualizada",
  "description": "Nova descrição",
  "completed": true
}
```

**Resposta 200 OK**

```json
{
  "id": 3,
  "title": "Task Atualizada",
  "description": "Nova descrição",
  "completed": true
}
```

**Resposta 404 Not Found**

```json
{
  "error": "Task não encontrada"
}
```

**Explicação:** Atualiza os campos de uma task existente. Campos não enviados permanecem inalterados.

---

### 5. Deletar uma task

```
DELETE /tasks/{id}
```

**Parâmetros**

* `id` → ID da task

**Resposta 204 No Content**
**Resposta 404 Not Found**

```json
{
  "error": "Task não encontrada"
}
```

**Explicação:** Remove a task do banco. Não retorna conteúdo no corpo.

---

## 🔹 Observações

* **Content-Type:** `application/json`
* **Validações:**

  * `title` é obrigatório.
  * `completed` é opcional (default: `false`).
* **Status Codes:**

  * `200 OK` → Sucesso na leitura ou atualização
  * `201 Created` → Task criada
  * `204 No Content` → Task deletada com sucesso
  * `404 Not Found` → Task não encontrada

---

## 💻 Exemplos de cURL

**Criar task**

```bash
curl -X POST http://localhost:8080/tasks \
-H "Content-Type: application/json" \
-d '{"title": "Estudar", "description": "Ler capítulo 3", "completed": false}'
```

**Listar tasks**

```bash
curl http://localhost:8080/tasks
```

**Atualizar task**

```bash
curl -X PUT http://localhost:8080/tasks/1 \
-H "Content-Type: application/json" \
-d '{"title": "Estudar Java", "completed": true}'
```

**Deletar task**

```bash
curl -X DELETE http://localhost:8080/tasks/1
```

---
## 👥 CRUD de Users e Autenticação (JWT)

Adiciona gerenciamento de usuários e autenticação via JWT. Usuários possuem credenciais para acessar endpoints protegidos (tasks e operações de usuário).

--- 

### Modelo de User

Request/response com password: apenas para criação/atualização. Nunca retorne o campo `password` nas respostas.

```json
{
  "id": 1,
  "username": "johndoe"
}
```

Campos principais:
- `username` (string, obrigatório, único)
- `password` (string, obrigatório no cadastro, mínimo 6 caracteres; armazenado hashed)

--- 

## 🔐 Autenticação

### 1. Registro (Register)

```
POST /user/save
```

Body (JSON):
```json
{
  "username": "johndoe",
  "password": "senhaSegura123"
}
```

Respostas:
- **201 Created**: retorna usuário (sem password) e opcionalmente token.
```json
{
  "id": 2,
  "username": "johndoe",}
```
- **400 Bad Request**: dados inválidos, username já existente.

---

### 2. Login

```
POST /user/login
```

Body (JSON):
```json
{
  "username": "johndoe",
  "password": "senhaSegura123"
}
```

**Resposta 200 OK**
```json
{
  "token": "eyJhbGciOiJIUzI1...",
  "tokenType": "Bearer",
  "expiresIn": 3600
}
```

**Erros**
- **401 Unauthorized**: credenciais inválidas.

Uso do token:
- Cabeçalho Authorization: `Authorization: Bearer <token>`

---

## 🔹 Endpoints de Users (protegidos por JWT)

Observação: todos os endpoints abaixo exigem header `Authorization: Bearer <token>` a menos que explicitado.

### 2. Buscar user por ID
```
GET /user/{id}
```
- **200 OK**: retorna o usuário pelo id (sem password).
- **404 Not Found**: usuário não encontrado.
- **403 Forbidden**: só o próprio usuário ou admins podem acessar.

### 3. Criar user
```
POST /user
```
- Permite criar um novo usuário (Admin ou open registration se implementado).
Body:
```json
{
  "username": "janedoe",
  "password": "novaSenha123",
}
```
- **201 Created**: usuário criado (sem password).
- **400 Bad Request**: validação falhou.

### 4. Atualizar user
```
PUT /user/{id}
```
Body (exemplo parcial):
```json
{
  "username": "novonomedoe",
  "password": "novaSenha123"
}
```
- **200 OK**: usuário atualizado (sem password).
- **403 Forbidden**: só o próprio usuário ou admin pode atualizar.
- **404 Not Found**: usuário não encontrado.

### 5. Deletar user
```
DELETE /user/{id}
```
- **204 No Content**: deletado com sucesso.
- **403 Forbidden**: só o próprio usuário ou admin.
- **404 Not Found**: usuário não encontrado.

--- 

## 🎯 Segurança e Validações

- Senhas devem ser armazenadas hashed (ex: BCrypt).
- `username` devem ser únicos.
<!-- - Tokens JWT devem conter claims (sub, roles, exp). -->
- Endpoints sensíveis exigem validação de token 
<!-- e verificação de roles (ex.: ADMIN). -->
- Nunca retornar `password` no payload de resposta.

--- 

## 💻 Exemplos de cURL (Autenticação & Uso)

Registrar novo user:
```bash
curl -X POST http://localhost:8080/user/save \
-H "Content-Type: application/json" \
-d '{"username":"johndoe","password":"senhaSegura123"}'
```

Login e obter JWT:
```bash
curl -X POST http://localhost:8080/user/login \
-H "Content-Type: application/json" \
-d '{"username":"johndoe","password":"senhaSegura123"}'
```

Usar token Bearer para listar users:
```bash
curl http://localhost:8080/user \
-H "Authorization: Bearer eyJhbGciOiJIUzI1..."
```

Atualizar usuário (autorizado):
```bash
curl -X PUT http://localhost:8080/user/2 \
-H "Authorization: Bearer eyJhbGciOiJIUzI1..." \
-H "Content-Type: application/json" \
-d '{"username":"nnovonomedoe"}'
```


## 🔹 Observações finais

- Content-Type: `application/json`
- Status codes relevantes:
  - `200 OK` → leitura/atualização bem-sucedida
  - `201 Created` → criação
  - `204 No Content` → exclusão
  - `400 Bad Request` → validação
  - `401 Unauthorized` → token inválido/ausente
  - `403 Forbidden` → sem permissão
  - `404 Not Found` → recurso não encontrado
- Integre autenticação JWT com filtros (Spring Security) e proteja rotas sensíveis.
- Considere endpoints extras para gerenciamento de roles, reset de senha e logout (invalidar refresh token).

## 🔒 CORS e CSRF — explicação simples

CORS (Cross-Origin Resource Sharing)
- O que é: política do navegador que controla requisições entre origens (ex.: front em http://localhost:3000 e API em http://localhost:8080).
- Por que configurar: sem CORS corretamente configurado o navegador bloqueia chamadas do front.
- Como aplicar (exemplo Spring Boot): permitir origens, métodos e headers, preferencialmente por domínio específico em produção.

CSRF (Cross-Site Request Forgery)
- O que é: ataque que explora credenciais de sessão do navegador para executar ações indesejadas.
- Quando proteger: aplicações que usam autenticação baseada em cookies ou sessões precisam de proteção CSRF.
- Em APIs REST stateless com JWT (token enviado no header Authorization) é comum desabilitar o CSRF:
```java
http.cors().and().csrf().disable();
```
- Se usar cookies ou formulários, mantenha CSRF ativo e use um repositório de token:
```java
http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
```

Resumo rápido:
- Use CORS para permitir que o front acesse a API (configurar origens e métodos).
- Desabilite CSRF em APIs stateless com JWT no header; mantenha CSRF habilitado se depender de cookies/sessões e valide o token CSRF no cliente.
- Em produção, permitir apenas origens e métodos necessários e usar SameSite/secure cookies quando aplicável.