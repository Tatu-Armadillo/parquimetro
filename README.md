# Pré-requisitos:
* Docker (ou Postgres instalado em sua máquina, entretanto este tutorial está escrito para o caso do Docker)
* Java SDK 17
* Alguma IDE para desenolvimento JAVA.
* Maven (https://maven.apache.org/install.html)
* Em alguma pasta de sua preferência abra o terminal e clone o projeto:
```
git clone https://github.com/Tatu-Armadillo/parquimetro
```

# Como executar o Backend:
Acesse a pasta localizada do projeto

Antes de executar o programa, há a necessidade de criar e executar uma imagem de um container, que será usado como banco de dados. 
```
docker-compose up -d
```

Link de acesso a documentação dos endpoints 
```
http://localhost:9090/api/swagger-ui/index.html
```
