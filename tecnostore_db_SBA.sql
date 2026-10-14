DROP DATABASE IF EXISTS tecnostore_db_SBA;
CREATE DATABASE tecnostore_db_SBA;
USE tecnostore_db_SBA;

-- 1. Tabla marca
CREATE TABLE marca (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- 2. Tabla sistema_operativo
CREATE TABLE sistema_operativo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE
);

-- 3. Tabla gama
CREATE TABLE gama (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(30) NOT NULL UNIQUE
);

-- 4. Tabla persona
CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    identificacion VARCHAR(20) NOT NULL UNIQUE,
    correo VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100),
    telefono VARCHAR(20)
);

-- 5. Tabla vendedor (Vinculada a persona)
CREATE TABLE vendedor (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_persona INT NOT NULL UNIQUE,
    rol VARCHAR(50) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES persona(id) ON DELETE CASCADE
);

-- 6. Tabla celular
CREATE TABLE celular (
    id INT AUTO_INCREMENT PRIMARY KEY,
    modelo VARCHAR(100) NOT NULL,
    id_marca INT NOT NULL,
    id_sistema_operativo INT NOT NULL,
    id_gama INT NOT NULL,
    precio DECIMAL(10,2) NOT NULL CHECK (precio >= 0),
    stock INT NOT NULL CHECK (stock >= 0),
    FOREIGN KEY (id_marca) REFERENCES marca(id),
    FOREIGN KEY (id_sistema_operativo) REFERENCES sistema_operativo(id),
    FOREIGN KEY (id_gama) REFERENCES gama(id)
);

-- 7. Tabla venta
CREATE TABLE venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_persona INT NOT NULL, -- Cliente
    id_vendedor INT,        -- Vendedor opcional
    fecha DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_persona) REFERENCES persona(id),
    FOREIGN KEY (id_vendedor) REFERENCES vendedor(id)
);

-- 8. Tabla detalle_venta
CREATE TABLE detalle_venta (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_venta INT NOT NULL,
    id_celular INT NOT NULL,
    cantidad INT NOT NULL CHECK (cantidad > 0),
    subtotal DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (id_venta) REFERENCES venta(id),
    FOREIGN KEY (id_celular) REFERENCES celular(id)
);

-- ========================================================
-- 2. INSERCIÓN DE DATOS DE PRUEBA
-- ========================================================

-- Inserts para Gamas (3 especificados)
INSERT INTO gama (nombre) VALUES 
('Baja'),
('Media'),
('Alta');

-- Inserts para Sistemas Operativos (3 especificados)
INSERT INTO sistema_operativo (nombre) VALUES 
('Android'),
('iOS'),
('HarmonyOS');

-- Inserts para Marcas (5 especificadas)
INSERT INTO marca (nombre) VALUES 
('Samsung'),
('Apple'),
('Xiaomi'),
('Motorola'),
('Huawei');

-- Inserts para Personas (2 Vendedores + 3 Clientes)
INSERT INTO persona (nombre, identificacion, correo, contraseña, telefono) VALUES 
-- Vendedores (IDs 1 y 2)
('Carlos Gómez', '101010101', 'carlos.vendedor@tecnostore.com', 'pass123', '3001112233'),
('Laura Martínez', '202020202', 'laura.admin@tecnostore.com', 'admin123', '3104445566'),
-- Clientes (IDs 3, 4 y 5)
('Juan Pérez', '1098765432', 'juan.perez@gmail.com', NULL, '3159876543'),
('María Rodríguez', '1122334455', 'maria.rodriguez@hotmail.com', NULL, '3201234567'),
('Andrés López', '9988776655', 'andres.lopez@yahoo.com', NULL, '3017654321');

-- Inserts para Vendedores (2 Vendedores vinculados a persona 1 y 2)
INSERT INTO vendedor (id_persona, rol) VALUES 
(1, 'Asesor de Ventas'),
(2, 'Administrador');

-- Inserts para Celulares (Modelos variados para pruebas)
-- Estructura: (modelo, id_marca, id_sistema_operativo, id_gama, precio, stock)
-- IDs Marcas: 1:Samsung, 2:Apple, 3:Xiaomi, 4:Motorola, 5:Huawei
-- IDs SO: 1:Android, 2:iOS, 3:HarmonyOS
-- IDs Gama: 1:Baja, 2:Media, 3:Alta
INSERT INTO celular (modelo, id_marca, id_sistema_operativo, id_gama, precio, stock) VALUES 
('Galaxy S23 Ultra', 1, 1, 3, 1200.00, 8),
('Galaxy A54', 1, 1, 2, 350.00, 3),         -- Stock bajo (< 5) para probar reporte
('iPhone 15 Pro', 2, 2, 3, 1300.00, 6),
('iPhone 13', 2, 2, 3, 750.00, 2),          -- Stock bajo (< 5) para probar reporte
('Redmi Note 12', 3, 1, 2, 220.00, 15),
('Poco C50', 3, 1, 1, 95.00, 4),            -- Stock bajo (< 5) para probar reporte
('Moto G84', 4, 1, 2, 260.00, 10),
('Moto E13', 4, 1, 1, 85.00, 20),
('P60 Pro', 5, 3, 3, 980.00, 5),
('Nova 11i', 5, 3, 2, 290.00, 1);           -- Stock bajo (< 5) para probar reporte