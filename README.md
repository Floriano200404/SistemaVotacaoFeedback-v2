# Sistema Integrado de Votação e Feedback Acadêmico – Campus Calama

<p align="center">
  <img src="https://img.shields.io/badge/status-em%20desenvolvimento-yellow" alt="Status do Projeto">
  <img src="https://img.shields.io/badge/Java-17%2B-blue?logo=java&logoColor=white" alt="Java 17+">
  <img src="https://img.shields.io/badge/Interface-Java%20Swing-orange" alt="Java Swing">
  <img src="https://img.shields.io/badge/Banco%20de%20Dados-MySQL-blue?logo=mysql&logoColor=white" alt="MySQL">
</p>

## 📝 Índice

1.  [Sobre o Projeto](#-sobre-o-projeto)
2.  [✨ Funcionalidades](#-funcionalidades)
3.  [🎨 Design da Interface (UI/UX)](#-design-da-interface-uiux)
4.  [🛠️ Tecnologias e Arquitetura](#️-tecnologias-e-arquitetura)
5.  [🚀 Como Configurar e Executar](#-como-configurar-e-executar)
6.  [🌿 Fluxo de Trabalho com Git](#-fluxo-de-trabalho-com-git)
7.  [🤝 Como Contribuir](#-como-contribuir)
8.  [🏷️ Padrão de Labels](#️-padrão-de-labels)
9.  [👥 Equipe](#-equipe)
10. [📄 Licença](#-licença)

---

## 📌 Sobre o Projeto

O **Sistema Integrado de Votação e Feedback Acadêmico** é uma solução desktop desenvolvida em **Java Swing** para modernizar e automatizar os processos de eleições e coletas de feedback no **IFRO Campus Calama**.

> Este projeto foi concebido como parte da disciplina de Engenharia de Software, Programação Visual e Banco de Dados, com a missão de substituir métodos manuais por uma plataforma centralizada, **segura, transparente e inclusiva**.

---

## ✨ Funcionalidades

-   **Gestão de Eleições Formais:** Crie, gerencie e audite votações para representantes de turma, diretórios acadêmicos e outras finalidades oficiais.
-   **Pesquisas Institucionais:** Elabore e aplique pesquisas para avaliar a satisfação com infraestrutura, eventos e serviços do campus.
-   **Feedback Anônimo:** Permita que alunos e servidores enviem sugestões e críticas de forma segura e anônima.
-   **Enquetes Rápidas:** Realize consultas informais sobre temas diversos de maneira ágil.
-   **Autenticação Segura:** Controle de acesso por níveis de permissão (administrador, votante).

---

## 🎨 Design da Interface (UI/UX)

Toda a modelagem das interfaces e prototipagem do fluxo de usuário foi realizada na plataforma **Figma**. O design busca ser intuitivo e acessível para todos os públicos do campus.

> 🔗 **[Acesse o protótipo no Figma aqui](https://www.figma.com/proto/bUeKRzIuL4ZGmvmXlLJ2Rt/Sistema-Eleitoral-para-o-Campus-Calama?node-id=0-1&t=XJctViub0FrLasjG-1)**

---

## 🛠️ Tecnologias e Arquitetura

### Tecnologias Utilizadas

| Tecnologia | Aplicação |
| :--- | :--- |
| **Java 17+** | Linguagem principal do projeto. |
| **Java Swing** | Construção das interfaces gráficas desktop. |
| **MySQL/JDBC** | Persistência e gerenciamento de dados. |
| **Figma** | Design e prototipagem das telas (UI/UX). |
| **Git/GitHub** | Controle de versão e colaboração. |

### Arquitetura em Camadas

O projeto segue uma arquitetura em camadas para garantir a separação de responsabilidades, facilitando a manutenção, testabilidade e escalabilidade.

| Camada | Responsabilidade |
| :--- | :--- |
| **View** | Interface gráfica (`Login`, `MenuPrincipal`, etc). |
| **Controller** | Intermedia a View e o Model, tratando eventos. |
| **Model** | Entidades e objetos de negócio (`Usuario`, `Voto`). |
| **Service** | Regras de negócio complexas. |
| **Repository** | Camada de acesso aos dados (interação com o BD). |
| **Util** | Classes utilitárias (conexão, logs, etc). |

---

## 🚀 Como Configurar e Executar

Siga os passos abaixo para configurar o ambiente de desenvolvimento e executar o projeto.

### 1. Pré-requisitos

-   **JDK 17** ou superior.
-   **Git** instalado e configurado.
-   Uma **IDE Java** (NetBeans, IntelliJ, Eclipse).
-   **MySQL Server** e um cliente de banco de dados (MySQL Workbench, DBeaver).

> 📚 **Precisando de ajuda com Git ou a configuração inicial? [Acesse nosso Guia Completo aqui](https://docs.google.com/document/d/1HHP1YgrczhxzryjZ-kSDeyXaN_lxsu2g-c4aHI8usIU/edit?usp=sharing)**

### 2. Passos de Instalação e Execução

1.  **Clone o Repositório**
    ```bash
    git clone git@github.com:Floriano200404/SistemaVotacaoFeedback.git
    cd SistemaVotacaoFeedback
    ```

2.  **Configure o Banco de Dados**
    a. Abra seu cliente MySQL e crie um novo schema (banco de dados) com o nome exato `mydb`.
    ```sql
    CREATE SCHEMA mydb;
    ```
    b. Localize o arquivo de script `schema.sql` na raiz do projeto.
    c. Execute este script no schema `mydb` para criar todas as tabelas.

3.  **Configure a Conexão no Código**
    a. Na sua IDE, navegue até o pacote `util` e abra o arquivo de conexão (ex: `Database.java`).
    b. Atualize as credenciais `USER` e `PASSWORD` com as suas informações de acesso ao MySQL.

4.  **Execute o Projeto**
    a. Abra o projeto na sua IDE.
    b. Localize e execute a classe principal `Main.java`.
    c. Use as seguintes credenciais para o primeiro acesso:
       - **Login:** `admin@ifro.edu.br`
       - **Senha:** `123`

---

## 🌿 Fluxo de Trabalho com Git

Adotamos o **Git Flow** para organizar o desenvolvimento. Todo o trabalho deve ser feito em *branches* separadas para garantir a estabilidade da `main` e da `develop`.

| Branch | Finalidade |
| :--- | :--- |
| `main` | Versão estável e de produção. Recebe merges apenas da `develop`. |
| `develop`| Ramo principal de desenvolvimento. Integra todas as *features*. |
| `feature/nome-da-funcionalidade` | Desenvolvimento de novas funcionalidades. |
| `bugfix/descricao-do-bug` | Correções de bugs que não são urgentes. |
| `hotfix/correcao-urgente` | Correções críticas que precisam ir direto para a `main`. |

### Passo a Passo para uma Contribuição


### 1. Mude para a branch 'develop' e sincronize com o repositório remoto
```
git checkout develop
git pull origin develop
```
### 2. Crie uma nova branch para sua funcionalidade
```
# Exemplo: git checkout -b feature/implementar-tela-login
git checkout -b feature/sua-funcionalidade
```
### 3. Desenvolva sua tarefa e faça commits atômicos
```
# Use o padrão de Commits Semânticos (ex: "feat:", "fix:", "docs:")
git add .
git commit -m "tipo(escopo): mensagem descritiva"
```

### 4. Envie sua branch para o repositório remoto
```
git push origin feature/sua-funcionalidade
Abra um Pull Request: Ao finalizar o desenvolvimento na sua branch, abra um Pull Request para a develop do repositório original, referenciando a issue que você criou no título ou na descrição.
```
## 🤝 Como Contribuir
Este projeto é aberto a contribuições! Para ajudar, siga estes passos:

Crie uma Issue: Antes de começar, crie uma issue para descrever o bug que você encontrou ou a funcionalidade que deseja adicionar. Isso ajuda a alinhar as expectativas. Use os nossos labels para categorizar.

Faça um Fork: Crie um fork do projeto para o seu próprio GitHub.

Siga o Fluxo de Trabalho: Siga o fluxo de trabalho com Git descrito acima.

## 🏷️ Padrão de Labels

Utilizamos labels para organizar, priorizar e rastrear o status de *Issues* e *Pull Requests*.

| Label                 | Cor       | Descrição                                                              |
| :-------------------- | :-------- | :--------------------------------------------------------------------- |
| `01 not started`      | `#FFD700` | A tarefa ainda não foi iniciada.                                       |
| `02 work in progress` | `#0E8A16` | Tarefa em andamento.                                                   |
| `03 bug`              | `#B60205` | Relata um erro ou comportamento inesperado no sistema.                 |
| `04 question`         | `#D876E3` | Dúvida ou pergunta sobre o projeto.                                    |
| `05 dependent`        | `#FBCA04` | Tarefa que depende da conclusão de outra.                              |
| `06 priority`         | `#E99695` | Indica a prioridade da tarefa.                                         |
| `07 enhancement`      | `#A2EEEF` | Proposta de nova funcionalidade ou melhoria de uma existente.          |
| `11 front-end`        | `#006B75` | Relacionado à interface do usuário (Swing).                            |
| `12 back-end`         | `#0052CC` | Relacionado à lógica de negócios, serviços ou banco de dados.          |
| `13 documentation`    | `#5319E7` | Tarefas relacionadas à documentação do projeto (`README`, wikis).      |
| `15 done`             | `#00C775` | Tarefa concluída e pronta para revisão ou merge.                       |
| `16 standby`          | `#E6E6E6` | Tarefa em espera por mais informações ou recursos.                     |

> 💡 **Dica:** Use no máximo 3 labels por issue/PR para manter a organização.

---

## 👥 Equipe

| Nome                         | Função                             | Contato                       |
| :--------------------------- | :--------------------------------- | :---------------------------- |
| Floriano Vieira de Araújo Neto | Gerente de Projeto & Desenvolvedor | florianoneto2004@gmail.com    |
| *Athos Moreno Ribeiro* | *Banco/Back-end* | *athos8197@gmail.com* |
| *Estenio Silva de Castro* | *Figma/Front-end* | *estenio.silva.castro@gmail.com* |
| *Eychila Vitória Maia das Chagas* | *Front-end* | *eychilamaia@gmail.com* |
| *Manoel de Jesus Moreira de Aguiar* | *Front-end* | *manoelmaguiar@gmail.com* |

---

## 📄 Licença

Este projeto é distribuído sob a licença MIT. Veja o arquivo `LICENSE.md` para mais detalhes.



---

<p align="center">
  Mantido por <strong>Floriano Vieira de Araújo Neto</strong>
  <br>
  Aluno de Análise e Desenvolvimento de Sistemas – IFRO Campus Calama
</p>




