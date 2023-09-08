CREATE DATABASE hotelAlura;

USE hotelAlura;

-- Tabla para las reservas

CREATE TABLE
    reservas (
        id INT NOT NULL AUTO_INCREMENT,
        fecha_entrada DATE NOT NULL,
        fecha_salida DATE NOT NULL,
        valor VARCHAR(50),
        forma_de_pago VARCHAR(50) NOT NULL,
        PRIMARY KEY (id)
    );

-- Tabla de huéspedes

CREATE TABLE
    huespedes (
        id INT NOT NULL AUTO_INCREMENT,
        nombre VARCHAR(50) NOT NULL,
        apellido VARCHAR(50) NOT NULL,
        fecha_nacimiento DATE NOT NULL,
        nacionalidad VARCHAR(50) NOT NULL,
        telefono VARCHAR(50) NOT NULL,
        id_reserva INT NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY (id_reserva) REFERENCES reservas(id)
    );

-- Tabla de usuarios

CREATE TABLE
    usuarios (
        nombre VARCHAR(50) NOT NULL,
        contraseña VARCHAR(50) NOT NULL
    );

-- Agregamos el usuario administrador

INSERT INTO usuarios (nombre, contraseña) VALUES ('admin', 'admin');