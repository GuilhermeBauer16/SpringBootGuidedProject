create table pacientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    telefone VARCHAR(20) NOT NULL,
    cpf VARCHAR(11) NOT NULL ,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    ativo TINYINT,

    primary key(id)

);