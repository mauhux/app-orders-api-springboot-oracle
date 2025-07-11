
-- CREAR TABLA CLIENTES

CREATE TABLE ORDERS_APP.CLIENTES (
	ID INTEGER NOT NULL,
	NOMBRES VARCHAR2(100) NULL,
	APELLIDOS VARCHAR2(100) NULL,
	CONSTRAINT CLIENTES_PK PRIMARY KEY (ID)
);


-- SCRIPT PARA CREAR LA SECUENCIA PARA INCREMENTAR

CREATE SEQUENCE CLIENTES_SEQ
	START WITH 1
	INCREMENT BY 1
	NOCACHE
	NOCYCLE;


-- AGREGAR VALORES A LA TABLA CLIENTES

INSERT INTO CLIENTES (ID, NOMBRES, APELLIDOS) VALUES (CLIENTES_SEQ.NEXTVAL, 'MARIO', 'BROS');
INSERT INTO CLIENTES (ID, NOMBRES, APELLIDOS) VALUES (CLIENTES_SEQ.NEXTVAL, 'JUAN', 'PEREZ');
INSERT INTO CLIENTES (ID, NOMBRES, APELLIDOS) VALUES (CLIENTES_SEQ.NEXTVAL, 'JULIANA', 'RENGIFO');


-- OBTENER TODOS LOS CLIENTES

SELECT * FROM CLIENTES

SELECT ID, NOMBRES, APELLIDOS FROM CLIENTES

