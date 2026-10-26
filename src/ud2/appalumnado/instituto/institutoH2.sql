CREATE TABLE cursos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR (100),
    horas INT
);

CREATE TABLE alumnos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR (50),
    apellidos VARCHAR (100),
    fecha_nacimiento DATE,
    curso VARCHAR (100),
    nota_media DECIMAL (4,2),
    curso_id INT,
    FOREIGN KEY curso_id REFERENCES curso(id)
);

