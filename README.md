# 🙋🏠 CRUD PESSOA

## 🧑🏻‍💻 Executar
    gradle run      # Iniciar Projeto
    gradle tests    # Iniciar Testes

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
- Container Docker
- Conectar Banco
- Entidade Pessoa (ID, Nome, Data Nasc, CPF)
- Entidade Endereço (ID, personID, Street, Number, Neighborhood, City, UF, Country)
- Relacionar Pessoa e Endereço (1:n)
- Listar Pessoas e Endereços
- Atualizar Pessoa e Endereços
- Excluir Pessoas e Endereços
- Mostrar Idade da Pessoa
- Validações
- Testes Unitários
 
-> EXTRAS:
- Tratamento de Exceções
- Testes de Integração
- Swagger
- Informar endereço principal da Pessoa
- Paginação da Lista de Pessoas
 
US's - MVP
- 🚧 [US001] Como usuário, quero me cadastrar
  - ✅ [US001-1] Criar Entidade Pessoa
  - ✅ [US-001-2] Criar Entidade Endereço
  - 🚧 [US-001-3] Inserir Pessoa com Endereço no Banco
- Como usuário, quero poder vincular um ou mais endereços a minha pessoa
- Como usuário, quero poder atualizar os dados da minha pessoa
- Como usuário, quero poder atualizar os dados dos meus endereços
- Como usuário, quero poder excluir os dados da minha pessoa
- Como usuário, quero poder excluir os dados dos meus endereços
- Como usuário, quero poder ver a minha idade no sistema
- Como usuário, quero poder ver minhas informações e endereços no sistema
 
US's - EXTRA
- Como usuário, quero poder indicar um endereço principal
- Como usuário, quero poder indicar um nome social
 
-> INFO
- Estrutura Service, Repository, Controller
- Postgres
- LOMBOK
- DTO >>> Record
 
-> ARQUIVOS
- Model:
  Pessoa = ID (pk) | first_name | second_name | data_nasc | cpf
  Endereco = ID (pk) | ID_pessoa (fk) | street | number | neighborhood | city, state, country
 
- Repository
  Criar Pessoa
  Criar Endereço (Separado, mas vinculado? Validação do ID de Pessoa)
  Atualizar Pessoa (por ID)
  Atualizar Endereço (por ID)
  Excluir Pessoa (por ID)
  Excluir Endereço (por ID)
  Visualizar Informações Pessoa (por ID)
  Visualizar Informações Endereço (por ID)
  Visualizar Idade Pessoa (por ID)
 
  Extra
  Excluir Pessoa (por nome completo)
  Visualizar Informações Pessoa (por nome completo)
  Visualizar Pessoas por páginação
  Visualizar Idade Pessoa (por nome completo)
  Indicar Endereço Principal (por ID)
  Adicionar Nome Social a Pessoa (por ID)
  Visualizar Nome Social (por ID)
- Service
  createPerson
  createAdress
  updatePerson (ID)
  updateAdress (ID)
  deletePerson (ID - SoftDelete)
  deleteAdress (ID - SoftDelete)
  getPerson (ID)
  getAdress (ID)
  getPersonAge (ID)
 
  Extra
  deletePerson (name)
  getPerson (name)
  getPersonAge (name)
  setMainAdress (ID)
  setSocialName (ID)
  getSocialName (ID)
 
- Controller
  /person/create/
  /person/update/:id
  /person/delete/:id
  /person/get/:id
  /person/getage/:id
  /adress/create/
  /adress/update/:id
  /adress/delete/:id
  /adress/get/:id
 
  Extra
  /person/delete/:name
  /person/get/:name
  /person/getage/:name
  /person/setsn/
  /person/getsn/:id
  /adress/setmain/:id