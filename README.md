# 🙋🏠 CRUD PESSOA

## 🧑🏻‍💻 Executar
  Este projeto utiliza o Docker para subir um banco PostgreSQL, por isso ao abrir o mesmo execute:
    docker compose up -d    # Sobe o container do PostgreSQL
  
  Após isso pode iniciar o projeto normalmente. 
  
  Para visualizar a documentação das requisições, acesse o link do Swagger: http://localhost:8080/swagger-ui/index.html

## 🗃️ **Dependências:** 
- 🗄️ Spring Data JPA 
- 🌐  Spring Web 
- 📃 PostgreSQL Driver 
- 🛠️ SpringBoot DevTools 
- 🐋 Docker Compose Support 
 
## 📂 Estrutura do Projeto
    src-|
        |
        |-controller    # Vincula métodos com o endereçamento de navegação
        |-service       # Organiza e faz a validação dos dados
        |-repository    # Envia as query de inserção de dados para o banco
    
## 📄 Entities


## Minimum Viable Product
- ✅ Container Docker
- ✅ Conectar Banco
- ✅ Entidade Pessoa (ID, Nome, Data Nasc, CPF)
- ✅ Entidade Endereço (ID, personID, Street, Number, Neighborhood, City, UF, Country)
- ✅ Relacionar Pessoa e Endereço (1:n)
- ✅ Listar Pessoas e Endereços
- ✅ Atualizar Pessoa e Endereços
- ✅ Excluir Pessoas e Endereços
- ✅ Mostrar Idade da Pessoa
- ✅ Validações
- ✅ Testes Unitários
 
-> EXTRAS:
- ✅ Tratamento de Exceções
- 🚧 Testes de Integração
- ✅ Swagger
- ✅ Informar endereço principal da Pessoa
- ✅ Paginação da Lista de Pessoas
 
US's - MVP
- ✅ [US001] Como usuário, quero me cadastrar
  - ✅ [US001-1] Criar Entidade Pessoa
  - ✅ [US001-2] Criar Entidade Endereço
  - ✅ [US001-3] Inserir Pessoa com Endereço no Banco
- ✅ [US002] Como usuário, quero poder vincular um ou mais endereços a minha pessoa e defini-los como primários ou secundários
- ✅ [US003] Como usuário, quero poder visualizar minhas informações
- ✅ [US004] Como usuário, quero poder atualizar meu endereço e dados pessoais
  - ✅ [US004-1] Atualizar os dados da minha pessoa
  - ✅ [US004-2] Atualizar os dados dos meus endereços
- ✅ [US005] Como usuário quero poder excluir meus endereços e meus dados
  - ✅ [US005-1] Excluir os dados da minha pessoa e por consequência os de Endereço
  - ✅ [US005-1] Excluir os dados de meus endereços
- ✅ [US006] Como usuário, quero poder visualizar a minha idade no sistema
- 🚧 [US007] Criar Mappers para conversão DTO - Entidade
- ✅ [US008] Criar Método para paginação dos dados a serem apresentados

US's - EXTRA
- ✅ Como usuário, quero poder indicar um endereço principal
- 🚧 Como usuário, quero poder indicar um nome social
 
-> INFO
- Estrutura Service, Repository, Controller
- Postgres
- LOMBOK
- DTO
- Swagger

-> MODELS:

  tbl_person:
    person_id - int auto increment (PK)
    firstName - varchar(20)
    lastName - varchar(20)
    cpf - char(11)
    birthDate - Date
    adress - Foreign Key 

  tbl_address:
    address_id - int auto increment (PK) 
    person_id - int (FK)
    zipCode - varchar(12) 
    street - varchar(25)
    number - varchar(5)
    neighborhood - varchar(20)
    city - varchar(20)
    UF - char(2)
    country - varchar(15)
 
- CONTROLLERS:
  -> /person/list                # Lista as pessoas e seus respectivos endereços adicionados ao banco
  -> /person/pageable            # Lista as pessoas por meio de paginação
  -> /person/update/{personID}   # Atualiza a pessoa especificada no banco
  -> /person/delete/{personID}   # Apaga a pessoa e seus respectivos endereços no banco
  -> /person/getage/{personID}   # Informa a idade da pessoa especificada


  -> /address/list                 # Lista os endereços já adicionados ao banco
  -> /adress/create/{personID}     # Adiciona o endereço ao banco^e o vincula a um usuário
  -> /adress/update/{addressID}    # Atualiza o endereço espeicificado no banco
  -> /adress/delete/{addressID}    # Deleta o endereço especificado do banco