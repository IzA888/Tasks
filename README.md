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
