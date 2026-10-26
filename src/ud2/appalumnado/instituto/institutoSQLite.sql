CREATE TABLE IF NOT EXISTS cursos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (100),
    horas INT
);

CREATE TABLE IF NOT EXISTS alumnos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (50),
    apellidos VARCHAR (100),
    fecha_nacimiento DATE,
    curso VARCHAR (100),
    nota_media DECIMAL (4,2),
    curso_id INT,
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);

CREATE TABLE modulos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nombre VARCHAR (100),
    horas INTEGER
);

CREATE TABLE alumno_modulo (
    id_alumno     INTEGER NOT NULL,
    id_modulo     INTEGER NOT NULL,
    
    PRIMARY KEY (id_alumno, id_modulo),
    

    CONSTRAINT fk_am_alumno
        FOREIGN KEY (id_alumno) REFERENCES alumnos(id)
        ON DELETE CASCADE ON UPDATE CASCADE,

     CONSTRAINT fk_am_modulo
        FOREIGN KEY (id_modulo) REFERENCES modulos(id)
        ON DELETE CASCADE ON UPDATE CASCADE
);
