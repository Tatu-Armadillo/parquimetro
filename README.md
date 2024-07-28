# Pré-requisitos:
* Docker (Poderá ser subtituído por Postgres, Mongo e Mailhog instalados em sua máquina, entretanto precisaria de
* modificações no application.properties que não serão abranjidos por este tutorial)
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

Caso deseje utilizar a aplicação em modo desenvolvimento local com propósito de debugar/testar outros ambientes,
somente comente a linha 7 e descomente a linha 8 do application.properties.