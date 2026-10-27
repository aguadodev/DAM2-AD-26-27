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


-- ============================================
-- CURSOS
-- ============================================
INSERT INTO cursos (nombre, horas) VALUES
('DAM1 - Desarrollo de Aplicaciones Multiplataforma 1º', 2000),
('DAM2 - Desarrollo de Aplicaciones Multiplataforma 2º', 2000),
('ASIR1 - Administración de Sistemas Informáticos en Red 1º', 2000),
('ASIR2 - Administración de Sistemas Informáticos en Red 2º', 2000);
-- IDs: 1=DAM1, 2=DAM2, 3=ASIR1, 4=ASIR2

-- ============================================
-- MÓDULOS
-- ============================================
INSERT INTO modulos (nombre, horas) VALUES
-- DAM1 (IDs 1-6)
('Programación', 256),
('Bases de Datos', 192),
('Entornos de Desarrollo', 96),
('Sistemas Informáticos', 192),
('Lenguajes de Marcas', 128),
('Formación y Orientación Laboral', 96),
-- DAM2 (IDs 7-12)
('Acceso a Datos', 128),
('Desarrollo de Interfaces', 160),
('Programación Multimedia y Dispositivos Móviles', 128),
('Programación de Servicios y Procesos', 128),
('Sistemas de Gestión Empresarial', 96),
('Proyecto de Desarrollo de Aplicaciones Multiplataforma', 50),
-- ASIR1 (IDs 13-18)
('Implantación de Sistemas Operativos', 192),
('Planificación y Administración de Redes', 192),
('Fundamentos de Hardware', 96),
('Gestión de Bases de Datos', 128),
('Lenguajes de Marcas y Sistemas de Gestión de Información', 128),
('Formación y Orientación Laboral', 96),
-- ASIR2 (IDs 19-24)
('Administración de Sistemas Operativos', 160),
('Servicios de Red e Internet', 160),
('Implantación de Aplicaciones Web', 128),
('Administración de Sistemas Gestores de Bases de Datos', 128),
('Seguridad y Alta Disponibilidad', 128),
('Proyecto de Administración de Sistemas Informáticos en Red', 50);

-- ============================================
-- ALUMNOS
-- ============================================
INSERT INTO alumnos (nombre, apellidos, fecha_nacimiento, email, nota_media, curso_id) VALUES
-- DAM1 (curso_id = 1)
('Lucía',      'García Fernández',       '2005-03-14', 'lucia.garcia@alumnos.es',      7.85, 1),
('Marcos',     'Ruiz Delgado',           '2004-11-02', 'marcos.ruiz@alumnos.es',       6.40, 1),
('Ainhoa',     'Martínez López',         '2005-07-21', 'ainhoa.martinez@alumnos.es',   8.90, 1),
('Daniel',     'Sánchez Ortega',         '2005-01-30', 'daniel.sanchez@alumnos.es',    5.75, 1),
('Paula',      'Romero Navarro',         '2004-09-17', 'paula.romero@alumnos.es',      9.10, 1),
('Javier',     'Torres Molina',          '2005-05-08', 'javier.torres@alumnos.es',     6.95, 1),
('Carmen',     'Jiménez Rubio',          '2004-12-25', 'carmen.jimenez@alumnos.es',    7.30, 1),
('Hugo',       'Fernández Gil',          '2005-02-11', 'hugo.fernandez@alumnos.es',    4.85, 1),

-- DAM2 (curso_id = 2)
('Elena',      'Moreno Castro',          '2003-06-19', 'elena.moreno@alumnos.es',      8.45, 2),
('Pablo',      'Vázquez Serrano',        '2003-10-04', 'pablo.vazquez@alumnos.es',     7.20, 2),
('Sara',       'Hernández Prieto',       '2004-04-28', 'sara.hernandez@alumnos.es',    9.35, 2),
('Adrián',     'Lozano Blanco',          '2003-08-15', 'adrian.lozano@alumnos.es',     6.60, 2),
('Nuria',      'Cano Iglesias',          '2004-01-09', 'nuria.cano@alumnos.es',        8.10, 2),
('Iván',       'Medina Pascual',         '2003-03-22', 'ivan.medina@alumnos.es',       5.95, 2),
('Rocío',      'Santos Guerrero',        '2004-07-07', 'rocio.santos@alumnos.es',      7.75, 2),

-- ASIR1 (curso_id = 3)
('Alberto',    'Ramos Peña',             '2005-04-12', 'alberto.ramos@alumnos.es',     7.05, 3),
('Marta',      'Núñez Salas',            '2004-10-30', 'marta.nunez@alumnos.es',       8.25, 3),
('Sergio',     'Díaz Fuentes',           '2005-08-03', 'sergio.diaz@alumnos.es',       6.15, 3),
('Andrea',     'Cabrera Vega',           '2005-06-25', 'andrea.cabrera@alumnos.es',    9.00, 3),
('Raúl',       'Ortega Caballero',       '2004-02-14', 'raul.ortega@alumnos.es',       5.45, 3),
('Lorena',     'Méndez Ríos',            '2005-11-19', 'lorena.mendez@alumnos.es',     7.60, 3),
('Diego',      'Suárez Herrero',         '2004-05-05', 'diego.suarez@alumnos.es',      6.80, 3),

-- ASIR2 (curso_id = 4)
('Cristina',   'Aguilar Mora',           '2003-12-01', 'cristina.aguilar@alumnos.es',  8.70, 4),
('Alejandro',  'Vidal Cortés',           '2003-09-23', 'alejandro.vidal@alumnos.es',   7.40, 4),
('Isabel',     'Reyes Domínguez',        '2004-03-16', 'isabel.reyes@alumnos.es',      9.15, 4),
('Óscar',      'Navarro Soler',          '2003-07-11', 'oscar.navarro@alumnos.es',     6.25, 4),
('Beatriz',    'Herrera Lorenzo',        '2004-01-27', 'beatriz.herrera@alumnos.es',   7.90, 4),
('Rubén',      'Castillo Pardo',         '2003-11-08', 'ruben.castillo@alumnos.es',    5.60, 4),
('Silvia',     'Delgado Márquez',        '2004-06-14', 'silvia.delgado@alumnos.es',    8.05, 4);

-- ============================================
-- ALUMNO_MÓDULO (matrículas)
-- Alumnos DAM1: 1-8   -> módulos 1-6
-- Alumnos DAM2: 9-15  -> módulos 7-12
-- Alumnos ASIR1: 16-22 -> módulos 13-18
-- Alumnos ASIR2: 23-29 -> módulos 19-24
-- ============================================

-- DAM1: cada alumno matriculado en los 6 módulos (1..6)
INSERT INTO alumno_modulo (id_alumno, id_modulo) VALUES
(1,1),(1,2),(1,3),(1,4),(1,5),(1,6),
(2,1),(2,2),(2,3),(2,4),(2,5),(2,6),
(3,1),(3,2),(3,3),(3,4),(3,5),(3,6),
(4,1),(4,2),(4,3),(4,4),(4,5),(4,6),
(5,1),(5,2),(5,3),(5,4),(5,5),(5,6),
(6,1),(6,2),(6,3),(6,4),(6,5),(6,6),
(7,1),(7,2),(7,3),(7,4),(7,5),(7,6),
(8,1),(8,2),(8,3),(8,4),(8,5),(8,6);

-- DAM2: cada alumno matriculado en los 6 módulos (7..12)
INSERT INTO alumno_modulo (id_alumno, id_modulo) VALUES
(9,7),(9,8),(9,9),(9,10),(9,11),(9,12),
(10,7),(10,8),(10,9),(10,10),(10,11),(10,12),
(11,7),(11,8),(11,9),(11,10),(11,11),(11,12),
(12,7),(12,8),(12,9),(12,10),(12,11),(12,12),
(13,7),(13,8),(13,9),(13,10),(13,11),(13,12),
(14,7),(14,8),(14,9),(14,10),(14,11),(14,12),
(15,7),(15,8),(15,9),(15,10),(15,11),(15,12);

-- ASIR1: cada alumno matriculado en los 6 módulos (13..18)
INSERT INTO alumno_modulo (id_alumno, id_modulo) VALUES
(16,13),(16,14),(16,15),(16,16),(16,17),(16,18),
(17,13),(17,14),(17,15),(17,16),(17,17),(17,18),
(18,13),(18,14),(18,15),(18,16),(18,17),(18,18),
(19,13),(19,14),(19,15),(19,16),(19,17),(19,18),
(20,13),(20,14),(20,15),(20,16),(20,17),(20,18),
(21,13),(21,14),(21,15),(21,16),(21,17),(21,18),
(22,13),(22,14),(22,15),(22,16),(22,17),(22,18);

-- ASIR2: cada alumno matriculado en los 6 módulos (19..24)
INSERT INTO alumno_modulo (id_alumno, id_modulo) VALUES
(23,19),(23,20),(23,21),(23,22),(23,23),(23,24),
(24,19),(24,20),(24,21),(24,22),(24,23),(24,24),
(25,19),(25,20),(25,21),(25,22),(25,23),(25,24),
(26,19),(26,20),(26,21),(26,22),(26,23),(26,24),
(27,19),(27,20),(27,21),(27,22),(27,23),(27,24),
(28,19),(28,20),(28,21),(28,22),(28,23),(28,24),
(29,19),(29,20),(29,21),(29,22),(29,23),(29,24);