Plataforma de Assinatura Digital

Esta documentação fornece uma visão geral do projeto e instruções detalhadas sobre como configurar e executar a aplicação.

Visão Geral

Este projeto é uma plataforma de assinatura digital completa, construída com uma arquitetura de microsserviços e conteinerizada com Docker Compose.

Backend: Desenvolvido em Java com o framework Spring Boot, responsável pela lógica de negócio, segurança (JWT), armazenamento de documentos e gerenciamento de assinaturas.

Frontend Administrativo: Uma aplicação em React e TypeScript para gerenciar documentos, visualizar assinaturas e interagir com as funcionalidades de administração.

Frontend do Usuário: Uma aplicação em React e TypeScript para o usuário final, otimizada para o processo de assinatura de documentos.

Banco de Dados: PostgreSQL, utilizado para armazenar os dados da aplicação.

Servidores Web: Servidores Nginx leves e eficientes para servir as aplicações frontend.

Como Iniciar o Projeto

Para rodar a aplicação, você só precisa ter o Docker e o Docker Compose instalados em seu sistema.

1. Navegue até a pasta raiz do projeto:

Abra o terminal e vá para o diretório digital-signature-platform.

Bash

cd C:\Projetos\digital-signature-platform
2. Remova os volumes antigos (inicialização limpa):

Se você já tentou rodar o projeto antes e encontrou erros de banco de dados, execute este comando para garantir uma inicialização limpa. Ele irá remover todos os contêineres e volumes de dados do Docker.

Bash

docker-compose down -v
3. Inicie os serviços:

Execute o comando abaixo para construir as imagens e iniciar todos os contêineres definidos no arquivo docker-compose.yml.

Bash

docker-compose up --build

Após a execução, o Docker Compose irá baixar as imagens base, construir seus serviços e iniciar o backend, os frontends e o banco de dados.

URLs de Acesso

Quando todos os serviços estiverem em execução, você poderá acessar as interfaces da seguinte forma:

Frontend Administrativo: http://localhost:3000

Frontend do Usuário: http://localhost:3001

Credenciais de Acesso

A aplicação de backend já está pré-configurada com um usuário administrador para facilitar os testes.

Email: admin@email.com

Senha: admin123

Como Parar a Aplicação

Para desligar todos os serviços e remover os contêineres, use o comando:

Bash

docker-compose down

Isso encerrará os contêineres, mas manterá o volume de dados do PostgreSQL, permitindo que você reinicie a aplicação rapidamente mais tarde sem perder seus dados.


Copyright (c) 2025 Josafa de Pinho Souza. Todos os direitos reservados.

