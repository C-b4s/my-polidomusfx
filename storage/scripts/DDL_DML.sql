-- database: ../Databases/Polidomus.sqlite


DROP VIEW IF EXISTS vwUsuarioDetalle;
DROP TABLE IF EXISTS Polidomus;
DROP TABLE IF EXISTS UsuarioCliente;
DROP TABLE IF EXISTS UsuarioTecnico;
DROP TABLE IF EXISTS Contrasena;
DROP TABLE IF EXISTS Correo;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS UsuarioTipo;

CREATE TABLE UsuarioTipo(
     IdUsuarioTipo  INTEGER PRIMARY KEY AUTOINCREMENT
    ,Nombre         VARCHAR(20)  NOT NULL UNIQUE
    ,Descripcion    VARCHAR(100) NULL
    ,Estado         VARCHAR(1)   NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);


CREATE TABLE Usuario (
     IdUsuario      INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdUsuarioTipo  INTEGER NOT NULL REFERENCES UsuarioTipo(IdUsuarioTipo)
    ,Nombre         VARCHAR(50) NOT NULL
    ,Apellido       VARCHAR(50) NOT NULL
    ,Cedula         VARCHAR(15) NOT NULL UNIQUE
    ,FechaNacimiento DATE NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'X'))
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
);


CREATE TABLE UsuarioCliente (
     IdUsuario      INTEGER PRIMARY KEY -- Relación 1 a 1 con Usuario
    ,Direccion      VARCHAR(100) NULL
    ,Telefono       VARCHAR(20) NULL
    ,RUC            VARCHAR(20) NULL
    ,Preferencias   TEXT NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);

CREATE TABLE UsuarioTecnico (
     IdUsuario      INTEGER PRIMARY KEY -- Relación 1 a 1 con Usuario
    ,Especialidad   VARCHAR(50) NOT NULL
    ,Licencia       VARCHAR(50) NULL
    ,AniosExperiencia INTEGER DEFAULT 0
    ,Disponibilidad BOOLEAN DEFAULT 1
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);


CREATE TABLE Polidomus (
      IdPolidomus       INTEGER PRIMARY KEY AUTOINCREMENT
     ,IdUsuarioCliente  INTEGER NOT NULL 
     ,Serie             VARCHAR(50) NOT NULL UNIQUE
     ,Alias             VARCHAR(100) NULL            -- Ej: "Casa de Playa"
     ,TipoPredio        VARCHAR(20) DEFAULT 'Residencial'
     ,Direccion         VARCHAR(255) NULL
     ,Referencia        VARCHAR(255) NULL
     ,Latitud           REAL NULL
     ,Longitud          REAL NULL
     ,Observaciones     TEXT NULL
     ,Estado            VARCHAR(1) NOT NULL DEFAULT 'A' CHECK (Estado IN ('A', 'I', 'X'))
     ,FechaCreacion     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FechaModifica     DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
     ,FOREIGN KEY (IdUsuarioCliente) REFERENCES UsuarioCliente(IdUsuario) ON DELETE RESTRICT
);

CREATE TABLE Correo (
     IdCorreo       INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdUsuario      INTEGER NOT NULL
    ,Email          VARCHAR(100) NOT NULL UNIQUE
    ,EsPrincipal    BOOLEAN NOT NULL DEFAULT 0
    ,Validado       BOOLEAN NOT NULL DEFAULT 0
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);


CREATE TABLE Contrasena (
     IdContrasena   INTEGER PRIMARY KEY AUTOINCREMENT
    ,IdUsuario      INTEGER NOT NULL
    ,Hash           VARCHAR(255) NOT NULL
    ,Salt           VARCHAR(100) NULL
    ,EsActual       BOOLEAN NOT NULL DEFAULT 1
    ,FechaExpiracion DATETIME NULL
    ,Estado         VARCHAR(1)  NOT NULL DEFAULT 'A'
    ,FechaCreacion  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FechaModifica  DATETIME NOT NULL DEFAULT (datetime('now','localtime'))
    ,FOREIGN KEY (IdUsuario) REFERENCES Usuario(IdUsuario) ON DELETE CASCADE
);


CREATE TRIGGER trg_Update_Usuario AFTER UPDATE ON Usuario
BEGIN UPDATE Usuario SET FechaModifica = datetime('now','localtime') WHERE IdUsuario = NEW.IdUsuario; END;

CREATE TRIGGER trg_Update_UsuarioCliente AFTER UPDATE ON UsuarioCliente
BEGIN UPDATE UsuarioCliente SET FechaModifica = datetime('now','localtime') WHERE IdUsuario = NEW.IdUsuario; END;

CREATE TRIGGER trg_Update_UsuarioTecnico AFTER UPDATE ON UsuarioTecnico
BEGIN UPDATE UsuarioTecnico SET FechaModifica = datetime('now','localtime') WHERE IdUsuario = NEW.IdUsuario; END;

CREATE TRIGGER trg_Update_Polidomus AFTER UPDATE ON Polidomus
BEGIN UPDATE Polidomus SET FechaModifica = datetime('now','localtime') WHERE IdPolidomus = NEW.IdPolidomus; END;



-- Tipos de Usuario
INSERT INTO UsuarioTipo (Nombre, Descripcion) VALUES 
 ('Administrador', 'Control total'),
 ('Cliente', 'Solicita servicios'),
 ('Tecnico', 'Realiza servicios');

-- === CREAR CLIENTE ===
INSERT INTO Usuario (IdUsuarioTipo, Nombre, Apellido, Cedula) 
VALUES (2, 'Juan', 'Perez', '1710000001');

INSERT INTO UsuarioCliente (IdUsuario, Direccion, Telefono)
VALUES (last_insert_rowid(), 'Av. Amazonas y Colon', '099999999');

INSERT INTO Correo (IdUsuario, Email, EsPrincipal)
VALUES ((SELECT IdUsuario FROM Usuario WHERE Cedula='1710000001'), 'juan@gmail.com', 1);

INSERT INTO Contrasena (IdUsuario, Hash)
VALUES ((SELECT IdUsuario FROM Usuario WHERE Cedula='1710000001'), 'hash_secreto_juan');

-- === CREAR PROPIEDAD DEL CLIENTE (POLIDOMUS) ===
INSERT INTO Polidomus (IdUsuarioCliente, Serie, Alias, Direccion, TipoPredio) 
VALUES (
    (SELECT IdUsuario FROM Usuario WHERE Cedula='1710000001'),
    'SERIE-A001', 'Casa Central', 'Av. Amazonas N45', 'Residencial'
);

-- === CREAR TÉCNICO ===
INSERT INTO Usuario (IdUsuarioTipo, Nombre, Apellido, Cedula) 
VALUES (3, 'Maria', 'Lopez', '1710000002');

INSERT INTO UsuarioTecnico (IdUsuario, Especialidad, Licencia)
VALUES (last_insert_rowid(), 'Domótica Avanzada', 'LIC-2026-X');

INSERT INTO Correo (IdUsuario, Email, EsPrincipal)
VALUES ((SELECT IdUsuario FROM Usuario WHERE Cedula='1710000002'), 'maria_tech@polidomus.com', 1);

/* =========================================================
    VISTA DE RESUMEN
   ========================================================= */
CREATE VIEW vwUsuarioDetalle AS
SELECT 
     U.IdUsuario
    ,UT.Nombre AS Rol
    ,U.Nombre || ' ' || U.Apellido AS NombreCompleto
    ,C.Email
    ,CASE WHEN UT.Nombre = 'Cliente' THEN UC.Direccion ELSE 'N/A' END AS DirCliente
    ,CASE WHEN UT.Nombre = 'Tecnico' THEN UTec.Especialidad ELSE 'N/A' END AS EspTecnico
    ,P.Alias AS PropiedadPolidomus
FROM Usuario U
JOIN UsuarioTipo UT ON U.IdUsuarioTipo = UT.IdUsuarioTipo
LEFT JOIN Correo C ON U.IdUsuario = C.IdUsuario AND C.EsPrincipal = 1
LEFT JOIN UsuarioCliente UC ON U.IdUsuario = UC.IdUsuario
LEFT JOIN UsuarioTecnico UTec ON U.IdUsuario = UTec.IdUsuario
LEFT JOIN Polidomus P ON UC.IdUsuario = P.IdUsuarioCliente;

SELECT * FROM vwUsuarioDetalle;