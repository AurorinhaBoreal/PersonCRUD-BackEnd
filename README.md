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
- ✅ Container Docker
- ✅ Conectar Banco
- ✅ Entidade Pessoa (ID, Nome, Data Nasc, CPF)
- ✅ Entidade Endereço (ID, personID, Street, Number, Neighborhood, City, UF, Country)
- ✅ Relacionar Pessoa e Endereço (1:n)
- ✅ Listar Pessoas e Endereços
- ✅ Atualizar Pessoa e Endereços
- ✅ Excluir Pessoas e Endereços
- ✅ Mostrar Idade da Pessoa
- 🚧 Validações
- 🚧 Testes Unitários
 
-> EXTRAS:
- 🚧 Tratamento de Exceções
- 🚧 Testes de Integração
- ✅ Swagger
- ✅ Informar endereço principal da Pessoa
- 🚧 Paginação da Lista de Pessoas
 
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
- 🚧 [US008] Criar Método para paginação dos dados a serem apresentados

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