CREATE TABLE clientes(id INT PRIMARY KEY AUTO_INCREMENT, nombre VARCHAR(50), apellidos VARCHAR(100), fecha_alta DATE, vip BOOLEAN)
INSERT INTO clientes (nombre, apellidos, fecha_alta, vip) VALUES (?, ?, ?, ?)
DELETE FROM clientes WHERE ID = ?
SELECT * FROM clientes WHERE ID = ?
UPDATE clientes SET nombre = ?, apellidos = ?, fecha_alta = ?, vip = ? WHERE ID = ?