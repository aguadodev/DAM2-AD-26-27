CREATE TABLE IF NOT EXISTS platos (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    precio DECIMAL(5, 2) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    disponible BOOLEAN
                    )