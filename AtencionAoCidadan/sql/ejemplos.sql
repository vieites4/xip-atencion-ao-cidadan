INSERT INTO usuario(
            id, email, movil, tipo, usuario)
    VALUES (124, 'paquito@mail.com', '645546456', 2, 'flogar');

INSERT INTO ciudadano(
            id, apellidos, direccion, dni, nombre, nivelinstruccion, sexo, 
            designacion, telefono, usuario_id, cp)
    VALUES (124, 'Lopez García', 'Calle de la Rosa','12345678Z', 'Franciso', 'Bachiller', 'M', 
            'Paquito', '987897987', 124, '19879');

INSERT INTO reciboscategoria(id, abreviatura, nombre, referencia)
VALUES 
    (1,'IBI', 'Imposto de Bens Inmobles', ''), 
    (2,'IVTM','Imposto de Vehículos de Tracción Mecánica', 'Matrícula');


INSERT INTO recibo(
            id, ano, dataaprobacion, datacobro, datalimitepagamento, descripcion, 
            esautoliquidacion, estado, importe, numerorecibo, referencia, 
            tipo, categoria_id, ciudadano_id)
VALUES (124, 2010, '2010-10-01 10:10', '2010-10-01 10:10', '2010-10-01 10:10', '', 
            false, 1, 10.0, 1, '0893hvp', 'rb', 2, 124);