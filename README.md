# Project Manager API

API para gerenciamento de projetos e funcionários utilizando Spring Boot 3.5.0 e java 17.

---

## Como executar

### Opção 01: Direto pela IDE
- 1- Clonar o projeto:
```bash
  git clone https://github.com/mrenancidrao/project-manager-api.git
```

- 2- Subir o banco de dados (PostgreSQL):
```bash
  docker-compose up -d
```

- 3- Run na classe ProjectManagerApiApplication.java

### Opção 02: Executar Script no Linux/macOS
```bash
  ./run.sh
``` 

### Opção 03: Executar Script no Windows
```bash
  run.bat
``` 


###### A aplicação estará disponível em: http://localhost:8080

### Pré-requisitos
- Java 17+
- Maven
- Git

---

### Build do projeto

```bash
mvn clean install
