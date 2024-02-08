# Golden Raspberry Awards

 <h2>Descrição do Projeto</h2>
API RESTful para gerenciar a lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards.

<h2>Para executar o projeto, será necessário instalar os seguintes programas:</h2>
- JDK 17: Necessário para executar o projeto Java
- Maven 3.5.3: Necessário para realizar o build do projeto Java
- IDE(Eclipse/Intellij): Para desenvolvimento do projeto

<h2>Para iniciar o desenvolvimento, é necessário clonar o projeto do GitHub num diretório de sua preferência:</h2>
- cd "diretorio de sua preferencia"
- git clone https://github.com/williamsfalcao/goldenraspberryawards

<h2>Para construir o projeto com o Maven, executar os comando abaixo:</h2>
- mvn clean install

<h2>Para rodar os testes, utilize o comando abaixo:</h2>
- mvn test

<h2>Executando a aplicação</h2>
Você pode executar a aplicação por linha de comando conforme abaixo ou pela sua IDE preferida:

- mvn exec:java -Dexec.mainClass="com.goldenraspberryawards.GoldenraspberryawardsApplication"

Quando a aplicação estive startada esse será o seu PATH: http://localhost:8080.

A aplicação utiliza um banco de dados em memoria (H2) que é carregado a partir de um arquivo csv que esta na raiz do projeto contendo uma lista de produções.

Podemos acessar ao console do banco através do seguindo endereço:
- PATH + /h2-console

Obs - Você pode alterar essa caminho dentro do arquivo "application.yml" que fica na pasta resource da aplicação.

![image](https://github.com/williamsfalcao/goldenraspberryawards/assets/25464585/47b07e10-6015-41ae-9bdc-305d458e9ea3)

Preencha os campos conforme dados abaixo e clique em conectar.
- JDBC URL: jdbc:h2:mem:testdb
- USER NAME: sa
  
Obs - Você pode alterar essas configurações dentro do arquivo "application.yml" que fica na pasta resource da aplicação.

<h2>Segue a lista de ENDPOINTs da aplicação:</h2>

<h3>ENDPOINT - Consultar Ganhadores</h3>

- URL: PATH + /awards

- METHOD: GET
  
- RESPONSE: {
              "min": [
                      {
                        "producer": "string",
                        "interval": number,
                        "previousWin": number,
                        "followingWin": number
                      }
                    ],
            "max": [
                     {
                        "producer": "string",
                        "interval": number,
                        "previousWin": number,
                        "followingWin": number
                    }
                  ]
            }

<h3>ENDPOINT - Consultar Produções</h3>

- URL: PATH + /movies
  
- METHOD: GET
  
- RESPONSE: [
              {
                "year" : number,
                "title": "string",
                "studios": "string",
                "producers": "string",
                "winner": "string"
              }
            ]

<h3>ENDPOINT -  Consulta Produção</h3>

- URL: PATH + /movies/{id}
  
- PARAM: "id" (Long)
  
- METHOD - GET
  
- RESPONSE: {
          "year" : number,
          "title": "string",
          "studios": "string",
          "producers": "string",
          "winner": "string"
        }

<h3>ENDPOINT - Cadastrar Produção</h3>

- URL: PATH + /movies
  
- METHOD: POST
  
- BODY:
  {
        "year" : number,
        "title": "string",
        "studios": "string",
        "producers": "string",
        "winner": "string"
      }
  
- RESPONSE: ""

<h3>ENDPOINT - Excluir Produção</h3>

- URL: PATH + /movies/{id}
  
- PARAM: "ID" (Long)
  
- METHOD - DEL
  
- RESPONSE: ""

<h3>ENDPOINT - Atualizar Produção</h3>

- URL: PATH + /movies/{id}
  
- PARAM: "ID" (Long)
  
- METHOD - PUT
  
- BODY: {
        "year" : number,
        "title": "string",
        "studios": "string",
        "producers": "string",
        "winner": "string"
      }
  
- RESPONSE: ""


