CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE tb_employee (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL,
    salary NUMERIC(12,2) NOT NULL,
    address VARCHAR(255),
    cellphone VARCHAR(15)
);

CREATE TABLE tb_project (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    last_update_date TIMESTAMP,
    description VARCHAR(255),
    start_date TIMESTAMP,
    end_date TIMESTAMP
);

CREATE TABLE tb_project_employee (
    project_id UUID NOT NULL,
    employee_id UUID NOT NULL,
    PRIMARY KEY (project_id, employee_id),
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES tb_project(id),
    CONSTRAINT fk_employee FOREIGN KEY (employee_id) REFERENCES tb_employee(id)
);
