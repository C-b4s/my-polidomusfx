-- database: ../Databases/Polidomus.sqlite
PRAGMA foreign_keys = OFF;

DROP VIEW IF EXISTS vwUsuarioDetalle;
DROP TABLE IF EXISTS Polidomus;
DROP TABLE IF EXISTS Arduinos;
DROP TABLE IF EXISTS UsuarioCliente;
DROP TABLE IF EXISTS UsuarioTecnico;
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
      IdUsuario      INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuarioTipo  INTEGER NOT NULL REFERENCES UsuarioTipo(IdUsuarioTipo)
     ,IdEstado       INTEGER NOT NULL REFERENCES Estado(IdEstado)
     ,Nombre         VARCHAR(100) NOT NULL
     ,Correo         VARCHAR(100) NOT NULL UNIQUE 
     ,Contrasena     VARCHAR(255) NOT NULL
     ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TABLE UsuarioCliente (
      IdUsuarioCliente INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuario        INTEGER NOT NULL UNIQUE
     ,Direccion        VARCHAR(100) NULL
     ,Estado           VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);

CREATE TABLE UsuarioTecnico (
      IdUsuarioTecnico INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuario        INTEGER NOT NULL UNIQUE
     ,Descripcion      VARCHAR(100) NULL
     ,Estado           VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica    DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);

CREATE TABLE Polidomus (
      IdPolidomus       INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuarioCliente  INTEGER NOT NULL 
     ,IdUsuarioTecnico  INTEGER NULL
     ,IdEstado          INTEGER NOT NULL REFERENCES Estado(IdEstado)
     ,Serie             VARCHAR(50) NOT NULL UNIQUE
     ,FechaCreacion     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FOREIGN KEY (IdUsuarioCliente) REFERENCES UsuarioCliente(IdUsuarioCliente) ON DELETE RESTRICT
     ,FOREIGN KEY (IdUsuarioTecnico) REFERENCES UsuarioTecnico(IdUsuarioTecnico) ON DELETE SET NULL
);

CREATE TABLE Arduinos (
      IdArduinos       INTEGER PRIMARY KEY AUTOINCREMENT
     ,Nombre         VARCHAR(20) NOT NULL UNIQUE
     ,Descripcion    VARCHAR(100) NULL
     ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
     ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);

CREATE TRIGGER trg_Update_Estado AFTER UPDATE ON Estado
BEGIN UPDATE Estado SET FechaModifica = datetime('now','localtime') WHERE IdEstado = NEW.IdEstado; END;

CREATE TRIGGER trg_Update_UsuarioTipo AFTER UPDATE ON UsuarioTipo
BEGIN UPDATE UsuarioTipo SET FechaModifica = datetime('now','localtime') WHERE IdUsuarioTipo = NEW.IdUsuarioTipo; END;

CREATE TRIGGER trg_Update_Usuario AFTER UPDATE ON Usuario
BEGIN UPDATE Usuario SET FechaModifica = datetime('now','localtime') WHERE IdUsuario = NEW.IdUsuario; END;

CREATE TRIGGER trg_Update_UsuarioCliente AFTER UPDATE ON UsuarioCliente
BEGIN UPDATE UsuarioCliente SET FechaModifica = datetime('now','localtime') WHERE IdUsuarioCliente = NEW.IdUsuarioCliente; END;

CREATE TRIGGER trg_Update_UsuarioTecnico AFTER UPDATE ON UsuarioTecnico
BEGIN UPDATE UsuarioTecnico SET FechaModifica = datetime('now','localtime') WHERE IdUsuarioTecnico = NEW.IdUsuarioTecnico; END;

CREATE TRIGGER trg_Update_Polidomus AFTER UPDATE ON Polidomus
BEGIN UPDATE Polidomus SET FechaModifica = datetime('now','localtime') WHERE IdPolidomus = NEW.IdPolidomus; END;

INSERT INTO Estado (Nombre, Descripcion) VALUES ('Activo', 'Registro habilitado'), ('Inactivo', 'Registro deshabilitado');

INSERT INTO UsuarioTipo (Nombre, Descripcion) VALUES 
 ('Cliente', 'Solicita servicios'),
 ('Tecnico', 'Realiza servicios');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena) 
VALUES (2, 1, 'Juan Perez', 'juan@gmail.com', '123');

INSERT INTO UsuarioCliente (IdUsuario, Direccion)
VALUES ((SELECT IdUsuario FROM Usuario WHERE Correo='juan@gmail.com'), 'Av. Amazonas');

INSERT INTO Usuario (IdUsuarioTipo, IdEstado, Nombre, Correo, Contrasena) 
VALUES (2, 1, 'Maria Lopez', 'maria@polidomus.com', '456');

INSERT INTO UsuarioTecnico (IdUsuario, Descripcion)
VALUES ((SELECT IdUsuario FROM Usuario WHERE Correo='maria@polidomus.com'), 'Tecnico Senior');

INSERT INTO Polidomus (IdUsuarioCliente, IdUsuarioTecnico, IdEstado, Serie) 
VALUES (
    (SELECT IdUsuarioCliente FROM UsuarioCliente),
    (SELECT IdUsuarioTecnico FROM UsuarioTecnico),
    1,
    'SERIE-001'
);

INSERT INTO Arduinos (Nombre, Descripcion) VALUES ('Arduino 01', 'Arduino número 1'), ('Arduino 02', 'Arduino número 2'), ('Arduino 03', 'Arduino número 3');

CREATE VIEW vwUsuarioDetalle AS
SELECT 
     U.IdUsuario
    ,UT.Nombre AS TipoUsuario
    ,U.Nombre
    ,U.Correo
    ,UC.Direccion
    ,UTec.Descripcion
    ,P.Serie AS SeriePolidomus
    ,E.Nombre AS EstadoGeneral
FROM Usuario U
JOIN UsuarioTipo UT ON U.IdUsuarioTipo = UT.IdUsuarioTipo
JOIN Estado E ON U.IdEstado = E.IdEstado
LEFT JOIN UsuarioCliente UC ON U.IdUsuario = UC.IdUsuario
LEFT JOIN UsuarioTecnico UTec ON U.IdUsuario = UTec.IdUsuario
LEFT JOIN Polidomus P ON UC.IdUsuarioCliente = P.IdUsuarioCliente;

SELECT * FROM vwUsuarioDetalle;