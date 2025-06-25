# Sistema Integrado de Votação e Feedback Acadêmico – Campus Calama

Projeto desktop desenvolvido em **Java Swing**, com arquitetura em camadas, voltado para criar e gerenciar votações e feedbacks acadêmicos no IFRO Campus Calama.

> Este repositório foi criado como parte do projeto final da disciplina de Engenharia de Software, visando substituir métodos manuais por uma solução automatizada, **segura, transparente e inclusiva**.

---

## 🎯 Objetivo

Criar uma plataforma desktop que:
- Substitua processos manuais e descentralizados por uma solução automatizada
- Garanta **transparência, segurança e anonimato**
- Permita **eleições formais, pesquisas institucionais, feedback anônimo e enquetes rápidas**

---

## 🔧 Tecnologias Utilizadas

| Tecnologia | Uso |
|------------|-----|
| Java 17+ | Linguagem principal |
| Java Swing | Interface gráfica desktop |
| MySQL / JDBC | Banco de dados e persistência |
| Git + GitHub/GitLab | Controle de versão e colaboração |

---

## 🗂 Arquitetura em Camadas

O projeto segue uma **estrutura organizada em camadas** para facilitar manutenção e escalabilidade:

| Camada       | Função |
|--------------|--------|
| **Model**     | Entidades do sistema (`Usuario`, `Enquete`, `Voto`) |
| **View**      | Telas gráficas (`Login`, `Menu Principal`, etc.) |
| **Controller**| Lógica de ações do usuário |
| **Service**   | Regras de negócio (futuro) |
| **Repository**| Acesso ao banco de dados (simulado ou real) |
| **Util**      | Funções auxiliares (conexão com banco, logs) |

---

## 🚀 Como Executar o Projeto

### Pré-requisitos

- JDK 17 ou superior instalado
- IDE Java (como Netbeans)
- Git instalado
- MySQL (opcional - futuro)

  ## Modelagem das interfaces

A modelagem das interfaces é feito através da plataforma FIGMA. Para acessar, [clique aqui](https://www.figma.com/proto/bUeKRzIuL4ZGmvmXlLJ2Rt/Sistema-Eleitoral-para-o-Campus-Calama?node-id=0-1&t=XJctViub0FrLasjG-1).

## Configuração do setup

Antes de iniciar a configuração do projeto, verifique se você possui os seguintes passos realizados:

- Chave SSH gerada e salva dentro do seu perfil no Github

```
ssh-keygen -t rsa
```

- Usuário Git configurado

```
git config --global user.name "Your-name"
git config --global user.email "Your-email"
```

## Configuração inicial do projeto

- Para clonar o projeto em sua máquina, clique em "Clone" na tela inicial do projeto no Github e selecione a opção "Clone with SSH";
- Agora, dentro do PhpStorm, clique em "GET FROM VCS" e cole o link copiado do github


### Passo a passo

1. Clone o repositório:

\`\`\`bash
git clone git@github.com:Floriano200404/SistemaVotacaoFeedback.git
cd SistemaVotacaoFeedback
\`\`\`

2. Abra o projeto na sua IDE:
   - IntelliJ: File > Open > Selecione a pasta do projeto
   - Marque a pasta \`src\` como fonte (Sources Root)

3. Execute a classe \`Main.java\`

4. Teste com:
   - **Login:** \`admin\`
   - **Senha:** \`123\`


---

## 📋 Git Flow Adotado

Para garantir controle de versão seguro e eficiente, adotamos o seguinte fluxo:

| Branch | Finalidade |
|--------|------------|
| \`main\` | Versão estável do projeto – apenas via Pull Request |
| \`develop\` | Ramo de desenvolvimento principal |
| \`feature/nome-da-funcionalidade\` | Novas funcionalidades |
| \`bugfix/descricao\` | Correções de bugs |

# Sempre comece pela develop
git checkout develop
git pull origin develop

# Crie uma nova feature branch
git checkout -b feature/tela-login
# Trabalhe nas telas e classes

# Adicione e faça commit
git add .
git commit -m "feat: implementa tela de login com validação"

# Envie a branch para o repositório
git push origin feature/tela-login

### Exemplo de uso:

\`\`\`bash
git checkout develop
git pull origin develop

git checkout -b feature/tela-login
# Desenvolva sua nova tela

git add .
git commit -m "Tela de login concluída"
git push origin feature/tela-login
\`\`\`

Após revisão, abra uma **Pull Request** para \`develop\`.

---
## 🏷️ Labels

Os labels são usados para categorizar issues e pull requests de forma consistente, ajudando na organização e acompanhamento das tarefas. Abaixo estão os labels utilizados no projeto e suas funções:

| Label | Cor | Descrição |
|-------|-----|-----------|
| `01 not started` | Amarelo | A issue ou PR ainda não foi iniciada. |
| `02 work in progress` | Verde | A issue ou PR está em andamento. |
| `03 bug` | Vermelho | Relata um problema ou erro no sistema. |
| `04 question` | Roxo | Uma dúvida ou pergunta relacionada ao projeto. |
| `05 dependent` | Laranja | A issue depende da conclusão de outra issue antes de ser finalizada. |
| `06 priority` | Rosa | Indica a prioridade da issue ou PR. |
| `07 enhancement` | Verde claro | Proposta de nova funcionalidade ou melhoria. |
| `08 update request` | Roxo claro | Solicitação de atualização ou correção. |
| `09 correction` | Marrom | Correção de algo que não está funcionando corretamente. |
| `10 sql` | Azul | Issues ou PRs relacionados ao banco de dados SQL. |
| `11 front-end` | Verde escuro | Issues ou PRs relacionadas à interface do usuário (front-end). |
| `12 back-end` | Verde-azulado | Issues ou PRs relacionadas ao servidor ou lógica de negócios (back-end). |
| `13 documentation` | Roxo escuro | Issues ou PRs relacionadas à documentação do projeto. |
| `14 database` | Cinza | Issues ou PRs relacionadas ao banco de dados. |
| `15 done` | verde-claro | A issue ou PR foi concluída e pronta para revisão. |
| `16 standby` | Cinza-claro | A issue ou PR está em espera por mais informações ou recursos. |

> 💡 Dica: Use até 3 labels por issue ou pull request para manter tudo claro e organizado.

---

## 📌 Como Criar uma Issue

Issues são ótimas para acompanhar bugs, melhorias, tarefas e perguntas.

### Passo a passo:

1. No GitHub/GitLab, clique em **"Issues"** no repositório.
2. Clique em **"New issue"**.
3. Escreva um título claro e descritivo:
   - Exemplo: `[Front-end] Implementar tela de login`
4. Na descrição, inclua:
   - O que precisa ser feito
   - Por quê é importante
   - Qualquer link ou referência relevante
5. Adicione os **labels adequados** (ex.: `02 front-end`, `07 enhancement`)
6. Clique em **Submit new issue**

---

### 🔍 Exemplo de Issue

#### Título:
`[Back-end] Conexão com banco de dados MySQL`

#### Descrição:
Implementar conexão JDBC com o banco de dados MySQL para permitir persistência dos votos e usuários. Incluir tratamento de exceções e uso de padrões de projeto.

#### Labels:
- `02 back-end`
- `14 database`

---

## 🔄 Como Criar um Pull Request (PR)

Pull Requests são usados para revisar e integrar alterações ao projeto.

### Passo a passo:

1. **Crie uma branch de feature:**
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/nova-funcionalidade

## 👥 Integrantes do Projeto

| Nome | Função | Contato |
|------|--------|---------|
| Floriano Vieira de Araújo Neto | Gerente de Projeto | florianoneto2004@gmail.com |
| [Nome do colega] | Desenvolvedor | email@example.com |
| [Nome do colega] | Desenvolvedor / Testador | email@example.com |

---




## 💬 Ajuda e Suporte

Tem dúvidas? Encontrou problemas?
- Crie uma **issue** no repositório
- Ou entre em contato com o responsável pelo projeto

---

## 🤝 Contribuição

Contribuições são bem-vindas!

### Como contribuir:

1. Fork o projeto
2. Crie uma branch: \`git checkout -b feature/minha-feature\`
3. Faça suas alterações
4. Commit: \`git commit -m "Descrição das alterações"\`
5. Push: \`git push origin feature/minha-feature\`
6. Abra uma Pull Request

---



## 📌 Referências Úteis

- [Como criar merge requests](https://docs.gitlab.com/ee/user/project/merge_requests/creating_merge_requests.html )
- [GitLab CI/CD](https://docs.gitlab.com/ee/ci/quick_start/ )
- [Controle de branches](https://docs.gitlab.com/topics/git/add_files/ )
- [Integrações no GitLab](https://gitlab.com/silvakaio/sgi-3/-/settings/integrations )

---

> ✅ Este projeto é mantido por **Floriano Vieira de Araújo Neto**  
> Aluno de Analista de Sistema ADS – IFRO Campus Calama  
> Email: florianoneto2004@gmail.com
