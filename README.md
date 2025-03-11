# Projeto Springfield

Este projeto configura um ambiente Docker com PostgreSQL, pgAdmin e uma aplicação Java.

## Pré-requisitos

- Docker instalado na sua máquina
- Docker Compose instalado na sua máquina

## Configuração

1. Clone o repositório para a sua máquina local:

```sh
git clone <URL_DO_REPOSITORIO>
cd cidade-springfield
```

2. Construa e inicie os contêineres:

```sh
docker-compose up --build
```

Este comando irá:
- Construir a imagem da aplicação a partir do `Dockerfile`
- Iniciar os serviços definidos no `docker-compose.yml`

## Acessando os serviços

- PostgreSQL estará disponível na porta `5432`.
- pgAdmin estará disponível na porta `5050`. Acesse `http://localhost:5050` e faça login com:
  - Email: `admin@admin.com`
  - Senha: `root`
- A aplicação estará disponível na porta `8080`. Acesse `http://localhost:8080`.

## Parando os contêineres

Para parar e remover os contêineres, redes e volumes criados pelo `docker-compose up`, execute:

```sh
docker-compose down
```

## Estrutura do Projeto

- `docker-compose.yml`: Define os serviços Docker.
- `Dockerfile`: Define a construção da imagem Docker da aplicação.
- `target/app.jar`: Arquivo JAR da aplicação Java.

