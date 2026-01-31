-- database: ../Databases/Polidomus.sqlite
PRAGMA foreign_keys = OFF;

DROP VIEW IF EXISTS vwUsuarioDetalle;
DROP TABLE IF EXISTS Polidomus;
DROP TABLE IF EXISTS Arduinos;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS UsuarioTipo;
DROP TABLE IF EXISTS Estado;


CREATE TABLE Estado (
      IdEstado       INTEGER PRIMARY KEY AUTOINCREMENT
     ,Nombre         VARCHAR(20) NOT NULL UNIQUE
     ,Descripcion    VARCHAR(100) NULL
     ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE UsuarioTipo(
      IdUsuarioTipo  INTEGER PRIMARY KEY AUTOINCREMENT
     ,Nombre         VARCHAR(20)  NOT NULL UNIQUE
     ,Descripcion    VARCHAR(100) NULL
     ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A'
     ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Usuario (
      IdUsuario       INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuarioTipo   INTEGER NOT NULL REFERENCES UsuarioTipo(IdUsuarioTipo)
     ,IdEstado        INTEGER NOT NULL REFERENCES Estado(IdEstado)
     ,Nombre          VARCHAR(100) NOT NULL
     ,Correo          VARCHAR(100) NOT NULL UNIQUE 
     ,Contrasena      VARCHAR(255) NOT NULL
     ,Direccion       VARCHAR(100) NULL
     ,Descripcion     VARCHAR(100) NULL
     ,Estado          VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Polidomus (
      IdPolidomus     INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuario       INTEGER NOT NULL REFERENCES Usuario(IdUsuario) ON DELETE RESTRICT
     ,IdEstado        INTEGER NOT NULL REFERENCES Estado(IdEstado)
     ,Serie           VARCHAR(50) NOT NULL UNIQUE
     ,FechaCreacion   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE Arduinos (
      IdArduinos      INTEGER PRIMARY KEY AUTOINCREMENT
     ,Nombre          VARCHAR(20) NOT NULL UNIQUE
     ,Descripcion     VARCHAR(100) NULL
     ,Estado          VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica   DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TRIGGER trg_Update_Estado AFTER UPDATE ON Estado
BEGIN UPDATE Estado SET FechaModifica = datetime('now','localtime') WHERE IdEstado = NEW.IdEstado; END;

CREATE TRIGGER trg_Update_UsuarioTipo AFTER UPDATE ON UsuarioTipo
BEGIN UPDATE UsuarioTipo SET FechaModifica = datetime('now','localtime') WHERE IdUsuarioTipo = NEW.IdUsuarioTipo; END;

CREATE TRIGGER trg_Update_Usuario AFTER UPDATE ON Usuario
BEGIN UPDATE Usuario SET FechaModifica = datetime('now','localtime') WHERE IdUsuario = NEW.IdUsuario; END;

CREATE TRIGGER trg_Update_Polidomus AFTER UPDATE ON Polidomus
BEGIN UPDATE Polidomus SET FechaModifica = datetime('now','localtime') WHERE IdPolidomus = NEW.IdPolidomus; END;

INSERT INTO Estado (Nombre, Descripcion) VALUES ('Activo', 'Registro habilitado'), ('Inactivo', 'Registro deshabilitado');

INSERT INTO UsuarioTipo (Nombre, Descripcion) VALUES 
 ('Cliente', 'Solicita servicios'),
 ('Tecnico', 'Realiza servicios');

-- Insertar 12 Usuarios Clientes (Juan Perez original + 11 nuevos)
-- Contraseña: cliente123 -> Hash SHA-256: 7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2
INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Juan Perez', 'juan@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Av. Amazonas');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Carlos Rodriguez', 'carlos.rodriguez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Calle Principal 123');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Ana Martinez', 'ana.martinez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Avenida Brasil 456');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Diego Hernandez', 'diego.hernandez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Pasaje Los Andes 789');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Maria Lopez', 'maria.lopez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Calle Flores 321');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Roberto Sanchez', 'roberto.sanchez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Av. Pichincha 654');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Patricia Gomez', 'patricia.gomez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Barrio San Blas 987');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Fernando Diaz', 'fernando.diaz@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Sector Centro 111');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Gabriela Nunez', 'gabriela.nunez@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Urbanizacion Los Pinos 222');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Miguel Castro', 'miguel.castro@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Villa Marina 333');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Laura Vargas', 'laura.vargas@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Conjunto Residencial 444');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Direccion) 
VALUES (1, 1, 'Andres Valenzuela', 'andres.valenzuela@gmail.com', '7b52009b64fd0a2a49e6d8a939753077792b0554330e7d1d77e370150b5395f2', 'Zona Residencial 555');

-- Insertar Usuarios Tecnicos
-- Contraseña: tecnico123 -> Hash SHA-256: 9a6ec4f9ad5ad2c1eaa01b50e94f44a4196c6ea02b0a5cdacdbf76abc8c2e4e4
INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena, Descripcion) 
VALUES (2, 1, 'Tecnico Senior', 'tecnico@polidomus.com', '9a6ec4f9ad5ad2c1eaa01b50e94f44a4196c6ea02b0a5cdacdbf76abc8c2e4e4', 'Tecnico especializado en instalación');

-- Insertar Polidomus para cada Usuario Cliente
INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (1, 1, 'SERIE-001');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (2, 1, 'SERIE-002');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (3, 1, 'SERIE-003');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (4, 1, 'SERIE-004');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (5, 1, 'SERIE-005');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (6, 1, 'SERIE-006');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (7, 1, 'SERIE-007');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (8, 1, 'SERIE-008');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (9, 1, 'SERIE-009');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (10, 1, 'SERIE-010');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (11, 1, 'SERIE-011');

INSERT INTO Polidomus (IdUsuario, IdEstado, Serie) 
VALUES (12, 1, 'SERIE-012');

INSERT INTO Arduinos (Nombre, Descripcion) VALUES ('Arduino 01', 'Arduino número 1'), ('Arduino 02', 'Arduino número 2'), ('Arduino 03', 'Arduino número 3');

CREATE VIEW vwUsuarioDetalle AS
SELECT 
     U.IdUsuario
    ,UT.Nombre AS TipoUsuario
    ,U.Nombre
    ,U.Correo
    ,U.Direccion
    ,U.Descripcion  
    ,P.Serie AS SeriePolidomus
    ,E.Nombre AS EstadoGeneral
FROM Usuario U
JOIN UsuarioTipo UT ON U.IdUsuarioTipo = UT.IdUsuarioTipo
JOIN Estado E ON U.IdEstado = E.IdEstado
LEFT JOIN Polidomus P ON U.IdUsuario = P.IdUsuario;

SELECT * FROM vwUsuarioDetalle;