-- Gerado por Oracle SQL Developer Data Modeler 23.1.0.087.0806
--   em:        2023-09-09 16:28:13 BRT
--   site:      Oracle Database 11g
--   tipo:      Oracle Database 11g

CREATE TABLE agenda (
    id_agenda              INTEGER NOT NULL,
    data                   DATE,
    tipo_serviço           VARCHAR2(50 CHAR),
    retirada_rua           VARCHAR2(50 CHAR),
    retirada_bairro        VARCHAR2(30 CHAR),
    guincho_porto_id       INTEGER NOT NULL,
    retirada_estado        VARCHAR2(30 CHAR),
    retirada_cidade        VARCHAR2(30 CHAR),
    cep                    INTEGER,
    gest_guin_id_gest_guin INTEGER NOT NULL
);

CREATE UNIQUE INDEX agenda_idx ON
    agenda (
        guincho_porto_id
    DESC );

CREATE UNIQUE INDEX agenda_idxv1 ON
    agenda (
        gest_guin_id_gest_guin
    ASC );

ALTER TABLE agenda ADD CONSTRAINT agenda_pk PRIMARY KEY ( id_agenda );

CREATE TABLE apolice (
    id_apolice              INTEGER NOT NULL,
    nome_segurado_1         VARCHAR2(4000),
    numero_segurado         INTEGER,
    veiculo_segurado_placa  CHAR(7 CHAR),
    veiculo_segurado_modelo CHAR(10 CHAR),
    data_inicio_vigencia    DATE,
    data_fim_vigencia       DATE,
    atend_id_atendimento    INTEGER NOT NULL
);

ALTER TABLE apolice ADD CONSTRAINT apolice_pk PRIMARY KEY ( id_apolice );

CREATE TABLE apolice_registro (
    id                         INTEGER,
    "Reg_Veic_ID Reg Veiculos" INTEGER NOT NULL,
    apolice_id_apolice         INTEGER NOT NULL
);

ALTER TABLE apolice_registro ADD CONSTRAINT apolice_registro__un UNIQUE ( "Reg_Veic_ID Reg Veiculos" );

CREATE TABLE atendimento (
    id_atendimento        INTEGER NOT NULL,
    nome_cliente          VARCHAR2(50 CHAR) NOT NULL,
    descrição_ocorrencia  VARCHAR2(50 CHAR) NOT NULL,
    data_hora_atendimento DATE,
    protocolo             CHAR(10 CHAR)
);

ALTER TABLE atendimento ADD CONSTRAINT atendimento_pk PRIMARY KEY ( id_atendimento );

CREATE TABLE cliente (
    cliente_id    NUMBER NOT NULL,
    id_cliente    INTEGER NOT NULL,
    nome_segurado VARCHAR2(50 CHAR) NOT NULL,
    numero_seguro INTEGER,
    rua           VARCHAR2(50 CHAR),
    bairro        VARCHAR2(30 CHAR),
    estado        VARCHAR2(30 CHAR),
    cep           INTEGER,
    gest_guin_id_ INTEGER NOT NULL
);

ALTER TABLE cliente ADD CONSTRAINT cliente_pk PRIMARY KEY ( cliente_id );

ALTER TABLE cliente ADD CONSTRAINT cliente_id_cliente_un UNIQUE ( id_cliente );

CREATE TABLE gestao_guinchos (
    id_gestao_guinchos    INTEGER NOT NULL,
    rua                   VARCHAR2(4000),
    protocolo             CHAR(10 CHAR),
    bairro                VARCHAR2(4000),
    estado                VARCHAR2(4000),
    cidade                VARCHAR2(4000),
    cep                   INTEGER,
    atend_id_atendimento2 INTEGER NOT NULL
);

ALTER TABLE gestao_guinchos ADD CONSTRAINT gest_guinc_pk PRIMARY KEY ( id_gestao_guinchos );

CREATE TABLE guincho_portov2 (
    id_guincho_porto       INTEGER NOT NULL,
    veiculo_modelo         CHAR(10 CHAR),
    veiculo_quantidade     INTEGER,
    placa                  CHAR(7 CHAR),
    tipo                   VARCHAR2(4000),
    gest_guin_id_gest_guin INTEGER NOT NULL
);

ALTER TABLE guincho_portov2 ADD CONSTRAINT guincho_portov1_pk PRIMARY KEY ( id_guincho_porto );

CREATE TABLE guincho_terceirizado (
    id_guincho_terceirizado INTEGER NOT NULL,
    veiculo_modelo          CHAR(10 CHAR),
    veiculo_quantidade      INTEGER,
    placa                   CHAR(7 CHAR),
    tipo                    VARCHAR2(4000),
    gest_guin_id_gest_guin  INTEGER NOT NULL
);

ALTER TABLE guincho_terceirizado ADD CONSTRAINT guincho_terceirizado_pk PRIMARY KEY ( id_guincho_terceirizado );

CREATE TABLE historico_ocorrencia (
    id_historico_ocorrência CHAR(10 CHAR) NOT NULL,
    tipos_solicitação       VARCHAR2(4000),
    protocolos              CHAR(10 CHAR),
    datas                   DATE,
    historico_sinistro_id   NUMBER NOT NULL
);

CREATE TABLE historico_sinistro (
    id                         NUMBER,
    atendimento_id_atendimento INTEGER NOT NULL,
    cliente_id                 INTEGER NOT NULL,
    hist_sinistro_id           NUMBER NOT NULL,
    hist_ocorr_id_hist_ocorr   CHAR 
--  WARNING: CHAR size not specified 
     NOT NULL,
    sinistro_id                INTEGER NOT NULL,
    cliente_id2                INTEGER NOT NULL
);

ALTER TABLE historico_sinistro ADD CONSTRAINT historico_sinistro_pk PRIMARY KEY ( hist_sinistro_id );

CREATE TABLE registro_veiculos (
    id_registro_veiculos INTEGER NOT NULL,
    marca                CHAR(10 CHAR),
    modelo               CHAR(10 CHAR),
    peso                 NUMBER(38, 2),
    ano_fabricação       DATE,
    carga                NUMBER(38, 2),
    atend_id_atendimento INTEGER NOT NULL
);

ALTER TABLE registro_veiculos ADD CONSTRAINT registro_veiculos_pk PRIMARY KEY ( id_registro_veiculos );

CREATE TABLE sinistro (
    id_sinistro     INTEGER NOT NULL,
    data_sinistro   DATE,
    numero_apolice  INTEGER,
    numero_sinistro INTEGER,
    envolvidos      VARCHAR2(4000)
);

ALTER TABLE sinistro ADD CONSTRAINT sinistro_pk PRIMARY KEY ( id_sinistro );

CREATE TABLE sinistro_atendimento (
    id_sinistro_atendimento INTEGER NOT NULL,
    data_sinistro           DATE,
    numero_apolice          INTEGER,
    numero_sinistro         INTEGER,
    envolvidos              VARCHAR2(4000),
    atend_id_atendimento    INTEGER NOT NULL
);

ALTER TABLE sinistro_atendimento ADD CONSTRAINT sinistro_atendimento_pk PRIMARY KEY ( id_sinistro_atendimento );

CREATE TABLE sinistro_gestao (
    id                     INTEGER,
    gest_guin_id_gest_guin INTEGER NOT NULL,
    sin_atend_id_sin_atend INTEGER NOT NULL
);

ALTER TABLE agenda
    ADD CONSTRAINT agend_gest_guin_fk FOREIGN KEY ( gest_guin_id_gest_guin )
        REFERENCES gestao_guinchos ( id_gestao_guinchos );

ALTER TABLE apolice
    ADD CONSTRAINT apolice_atendimento_fk FOREIGN KEY ( atend_id_atendimento )
        REFERENCES atendimento ( id_atendimento );

ALTER TABLE apolice_registro
    ADD CONSTRAINT apolice_reg_veiculos_fk FOREIGN KEY ( "Reg_Veic_ID Reg Veiculos" )
        REFERENCES registro_veiculos ( id_registro_veiculos );

ALTER TABLE apolice_registro
    ADD CONSTRAINT apolice_registro_apolice_fk FOREIGN KEY ( apolice_id_apolice )
        REFERENCES apolice ( id_apolice );

ALTER TABLE cliente
    ADD CONSTRAINT cliente_gestão_guinchos_fk FOREIGN KEY ( gest_guin_id_ )
        REFERENCES gestao_guinchos ( id_gestao_guinchos );

ALTER TABLE gestao_guinchos
    ADD CONSTRAINT gest_guin_atend_fkv2 FOREIGN KEY ( atend_id_atendimento2 )
        REFERENCES atendimento ( id_atendimento );

ALTER TABLE guincho_portov2
    ADD CONSTRAINT guin_portov1guin_fk FOREIGN KEY ( gest_guin_id_gest_guin )
        REFERENCES gestao_guinchos ( id_gestao_guinchos );

ALTER TABLE guincho_terceirizado
    ADD CONSTRAINT guin_terc_gest_guin_fk FOREIGN KEY ( gest_guin_id_gest_guin )
        REFERENCES gestao_guinchos ( id_gestao_guinchos );

ALTER TABLE historico_ocorrencia
    ADD CONSTRAINT hist_oc_sin_fk FOREIGN KEY ( historico_sinistro_id )
        REFERENCES historico_sinistro ( hist_sinistro_id );

ALTER TABLE historico_sinistro
    ADD CONSTRAINT hist_sin_atend_fk FOREIGN KEY ( atendimento_id_atendimento )
        REFERENCES atendimento ( id_atendimento );

ALTER TABLE historico_sinistro
    ADD CONSTRAINT hist_sin_cliente_fk FOREIGN KEY ( cliente_id2 )
        REFERENCES cliente ( id_cliente );

ALTER TABLE historico_sinistro
    ADD CONSTRAINT historico_sinistro_fk FOREIGN KEY ( sinistro_id )
        REFERENCES sinistro ( id_sinistro );

ALTER TABLE registro_veiculos
    ADD CONSTRAINT reg_veic_atend_fk FOREIGN KEY ( atend_id_atendimento )
        REFERENCES atendimento ( id_atendimento );

ALTER TABLE sinistro_gestao
    ADD CONSTRAINT sin_gest_guinc_fk FOREIGN KEY ( gest_guin_id_gest_guin )
        REFERENCES gestao_guinchos ( id_gestao_guinchos );

ALTER TABLE sinistro_atendimento
    ADD CONSTRAINT sini_atend_fk FOREIGN KEY ( atend_id_atendimento )
        REFERENCES atendimento ( id_atendimento );

ALTER TABLE sinistro_gestao
    ADD CONSTRAINT sini_gest_sin_atend_fk FOREIGN KEY ( sin_atend_id_sin_atend )
        REFERENCES sinistro_atendimento ( id_sinistro_atendimento );

CREATE SEQUENCE cli_cliente_id_seq START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER cliente_cliente_id_trg BEFORE
    INSERT ON cliente
    FOR EACH ROW
    WHEN ( new.cliente_id IS NULL )
BEGIN
    :new.cliente_id := cli_cliente_id_seq.nextval;
END;
/

CREATE SEQUENCE hist_sinistro_hist_sinist START WITH 1 NOCACHE ORDER;

CREATE OR REPLACE TRIGGER historico_sinistro_hist_sinist BEFORE
    INSERT ON historico_sinistro
    FOR EACH ROW
    WHEN ( new.hist_sinistro_id IS NULL )
BEGIN
    :new.hist_sinistro_id := hist_sinistro_hist_sinist.nextval;
END;
/



-- Relatório do Resumo do Oracle SQL Developer Data Modeler: 
-- 
-- CREATE TABLE                            14
-- CREATE INDEX                             2
-- ALTER TABLE                             34
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           2
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          2
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              0
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 1
