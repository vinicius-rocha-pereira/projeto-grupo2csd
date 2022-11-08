### Cadastro de usuarios
``
    {"nome":"João Rocha", "data_nascimento":"1990-10-23", "cpf":"13154595915", "email":"vini.rocha.lindo@gmail.com", "senha":"vinilindao123", "contato":"41992232701", "cidade":"Porto Velho", "rua":"São Francisco", "bairro":"Leste", "numero":"9898", "complemento":"Casa dos fundos", "situacao":"pendente"}
```
### Login de usuarios
```
    {"cpf":"03369587845", "senha":"vinilindao123,  "situacao":"pendente"}
```
### Cadastro de clientes
```
    {"nome":"Andre Sanches", "data_nascimento":"1997-10-15", "cpf":"45698745612", "email":"andre.sanches@gmail.com", "contato":"99845874564", "cidade":"São Paulo", "rua":"Cruzeiro do Sul", "bairro":"Norte", "numero":"7878", "complemento":"Casa,  "situacao":"pendente"}
```
### Cadastro de livros
```
    {"isbn":"9780001912366", "titulo":"Harry Potter e a Pedra Filosofal", "genero":"Fantasia", "autor":"J. K. Rowling", "editora":"Rocco", "qtd_paginas":"Fantasia", "data_lancamento":"1997-06-27", "edicao":"1", "qtd_livro":"10,  "situacao":"pendente"}
```
### Cadastro de movimentações
```
    {"isbn":"9780001912366", "cpf":"45698745612", "status_locacao":"Locado", "data_locacao":"2022-11-05", "data_devolucao":"2022-11-07", "data_limite":"2022-11-10,  "situacao":"pendente"}
```
#Querys Cassandra
CREATE KEYSPACE biblioteca_grupo2_csd WITH replication = {'class': 'SimpleStrategy', 'replication_factor': 3};`
USE biblioteca_grupo2_csd;
...
CREATE TABLE tbl_usuarios (nome text, data_nascimento date, cpf text, email text, senha text, contato text, cidade text, rua text, bairro text, numero text, complemento text, PRIMARY KEY ((cpf, senha)));`
...
CREATE TABLE tbl_clientes (nome text, data_nascimento date, cpf text, email text, contato text, cidade text, rua text, bairro text, numero text, complemento text, PRIMARY KEY ((cpf)));
...
CREATE TABLE tbl_livros_genero (isbn text, titulo text, genero text, autor text, editora text, qtd_paginas int, data_lancamento date, edicao text, qtd_livro int, PRIMARY KEY (genero, titulo, autor, data_lancamento, isbn));
...
CREATE TABLE tbl_livros_autor (isbn text, titulo text, genero text, autor text, editora text, qtd_paginas int, data_lancamento date, edicao text, qtd_livro int, PRIMARY KEY (autor, genero, titulo, data_lancamento, isbn));
...
CREATE TABLE tbl_livros_titulo (isbn text, titulo text, genero text, autor text, editora text, qtd_paginas int, data_lancamento date, edicao text, qtd_livro int, PRIMARY KEY (titulo, genero, autor, data_lancamento, isbn));
...
CREATE TABLE tbl_movimentacoes_isbn (isbn text, cpf text, status_locacao text, data_locacao date, data_devolucao date, data_limite date, PRIMARY KEY (isbn, status_locacao, cpf));
...
CREATE TABLE tbl_movimentacoes_cpf (isbn text, cpf text, status_locacao text, data_locacao date, data_devolucao date, data_limite date, PRIMARY KEY (cpf, status_locacao, isbn));
...
kafka-topics.sh --create --bootstrap-server localhost:29092 --topic fazer-login
kafka-topics.sh --create --bootstrap-server localhost:29092 --topic cadastrar-usuarios
kafka-topics.sh --create --bootstrap-server localhost:29092 --topic cadastrar-cliente
kafka-topics.sh --create --bootstrap-server localhost:29092 --topic cadastrar-livro
kafka-topics.sh --create --bootstrap-server localhost:29092 --topic fazer-movimentacao
...
kafka-console-producer.sh --broker-list localhost:29092 --topic fazer-movimentacao --property "parse.key=true" --property "key.separator=:"
...
kafka-console-producer.sh --broker-list localhost:29092 --topic cadastrar-usuarios
kafka-console-consumer.sh --bootstrap-server localhost:29092 --topic cadastrar-usuarios
...
{"situacao":"pendente","nome":"vinicius", "cpf":"03542165425"}
{"situacao": "pendente", "velocidade": 99, "placa": "AAA-0001", "multa": 200.0, "pontos": 7}
