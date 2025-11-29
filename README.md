# TheLovers

**Status:** Projeto descontinuado (Foco Educacional)

## Sobre o Projeto

O TheLovers foi idealizado como um aplicativo de relacionamento voltada para o público alternativo.

Embora não tenha sido finalizado, esse projeto foi fundamental para consolidar meu conhecimento em arquitetura de software, focando na integração entre um front-end moderno e uma API segura.

O objetivo central foi criar algo que passasse de um simples CRUD e me fizesse enfrentar desafios reais de engenharia, como criptografia local, autenticação via tokens rotativos e componentização de UI.

## Arquitetura e Decisões Técnicas

### Mobile (Kotlin Multiplatform)
O projeto foi configurado como Kotlin Multiplatform (KMP), visando compartilhar a lógica de negócios entre Android e Web.
- **Arquitetura:** MVVM com Clean Architecture, separando camadas de domínio, dados e apresentação.
- **UI:** Interface 100% declarativa com Jetpack Compose e Compose Multiplatform.
- **Segurança:** Implementação de criptografia local utilizando Google Tink (AEAD). Os tokens de sessão (JWT) não são salvos em texto plano; eles são encriptados antes da persistência no dispositivo.
- **Navegação:** Uso de Type-Safe Navigation para gerenciamento de rotas.
- **Gerenciamento de Estado:** Uso de StateFlow e padrões unidirecionais.

### Backend (Java & Spring Boot)
A API foi construída pensando em escalabilidade e segurança, organizada por funcionalidades em vez de camadas técnicas horizontais.
- **Autenticação:** Sistema sem senhas com login via SMS usando a API do Twilio.
- **Sessão:** Estratégia de segurança com JWT (curta duração) e Refresh Token (longa duração com rotação automática a cada uso), garantindo sessões seguras e persistentes.
- **Dados:** Banco de dados PostgreSQL rodando em Docker.

## Stack Tecnológica

**Mobile & Client**
- Linguagem: Kotlin (Kotlin Multiplatform)
- UI: Jetpack Compose, Material3
- Injeção de Dependência: Koin
- Networking: Ktor Client
- Banco Local: Room Database
- Segurança: Google Tink
- Assincronismo: Coroutines & Flow

**Backend**
- Linguagem: Java 17
- Framework: Spring Boot
- Banco de Dados: PostgreSQL (Docker)
- Migrations: Flyway
- Segurança: Spring Security, JWT
- Integrações: Twilio SDK

## Agradecimentos
Esse projeto **NÃO SERIA POSSÍVEL** se não fosse esse cara aqui: https://www.youtube.com/@PhilippLackner. 
Todo o conhecimento que eu adquiri relacionado a Kotlin foi de maneira gratuita através dos vídeos dele. Conteúdos atualizados e ótima didática.

## Nota
Este código reflete meu estudo aprofundado sobre ecossistemas nativos e segurança em aplicações móveis. Funcionalidades de servidor dependem de variáveis de ambiente e containers Docker para execução.
