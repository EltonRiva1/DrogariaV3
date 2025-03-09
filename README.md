# 🏪 DrogariaV3

![Java](https://img.shields.io/badge/Java-8-blue) ![JSF](https://img.shields.io/badge/JSF-2.2-orange) ![PrimeFaces](https://img.shields.io/badge/PrimeFaces-5.2-purple) ![MySQL](https://img.shields.io/badge/MySQL-8.0.29-blue) ![Tomcat](https://img.shields.io/badge/Tomcat-8.0-yellow)

**DrogariaV3** é um sistema web para gestão de drogarias, desenvolvido utilizando **JavaServer Faces (JSF)**, **PrimeFaces**, **Hibernate** e **MySQL** como banco de dados. O projeto é executado no servidor de aplicações **Apache Tomcat**.

## 🚀 Tecnologias Utilizadas

- **Java 8**
- **JSF 2.2**
- **PrimeFaces 5.2**
- **Hibernate**
- **MySQL 8.0.29**
- **Apache Tomcat 8.0**
- **Maven**

## 📂 Estrutura do Projeto

O projeto segue uma arquitetura MVC com os seguintes módulos principais:

- **View**: Páginas XHTML utilizando PrimeFaces.
- **Controller**: Managed Beans que controlam a interação entre a View e o Modelo.
- **Model**: Entidades JPA e regras de negócio.
- **DAO (Data Access Object)**: Camada responsável pelo acesso ao banco de dados via Hibernate.

## 🏗 Configuração do Ambiente

### 1️⃣ Pré-requisitos

- **JDK 8** instalado
- **Apache Tomcat 8** configurado
- **MySQL 8.0.29** instalado e rodando
- **Maven** configurado no sistema

### 2️⃣ Configurar o Banco de Dados

Crie o banco de dados no MySQL:

```sql
CREATE DATABASE drogaria_v3 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Atualize as credenciais no arquivo `hibernate.cfg.xml`:

```xml
<property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/drogariav3?useTimezone=true&serverTimezone=UTC" />
<property name="javax.persistence.jdbc.user" value="root" />
<property name="javax.persistence.jdbc.password" value="root" />
```

### 3️⃣ Executando o Projeto no Tomcat

1. Compile e empacote o projeto com Maven:

   ```bash
   mvn clean package
   ```

2. Copie o arquivo `.war` gerado (`DrogariaV3.war`) para a pasta `webapps` do Tomcat:

   ```bash
   cp target/DrogariaV3.war /path/to/tomcat/webapps/
   ```

3. Inicie o Tomcat:

   ```bash
   /path/to/tomcat/bin/startup.sh   # Linux/Mac
   /path/to/tomcat/bin/startup.bat  # Windows
   ```

4. Acesse o sistema no navegador:

   ```
   http://localhost:8080/DrogariaV3
   ```

## 📡 Endpoints Principais

O sistema possui as seguintes funcionalidades:

- **Cadastro de Produtos**
- **Gerenciamento de Usuários**
- **Controle de Vendas**

## 🎯 Testando o Sistema

Para testar a aplicação, utilize a credencial cadastrada em sua base:

- **Usuário:** user
- **Senha:** password

## 🛠 Possíveis Problemas e Soluções

### 🔹 O Tomcat não inicia corretamente

- Verifique se a porta **8080** já está em uso e altere no `server.xml` se necessário.

### 🔹 Erro de conexão com o MySQL

- Certifique-se de que o banco está rodando e que as credenciais estão corretas no `hibernate.cfg.xml`.

### 🔹 PrimeFaces não carrega os estilos corretamente

- Verifique a inclusão do **theme** do PrimeFaces no `web.xml` ou `faces-config.xml`.

---

💡 **Desenvolvido por [Elton Riva](https://github.com/EltonRiva1) 🚀**

