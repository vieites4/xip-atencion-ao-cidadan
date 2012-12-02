
INSERT INTO reciboscategoria(id, abreviatura, nombre, referencia)
VALUES 
    (1,'IBI', 'Imposto de Bens Inmobles', ''), 
    (2,'IVTM','Imposto de Vehículos de Tracción Mecánica', 'Matrícula');


INSERT INTO recibo(
            id, ano, dataaprobacion, datacobro, datalimitepagamento, descripcion, 
            esautoliquidacion, estado, importe, numerorecibo, referencia, 
            tipo, categoria_id, ciudadano_id)
VALUES (1, 2010, '2010-10-01 10:10', '2010-10-01 10:10', '2010-10-01 10:10', '', 
            false, 1, 10.0, 1, '234s234', 'rb', 1, 24);