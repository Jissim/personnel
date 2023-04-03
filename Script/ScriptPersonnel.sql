/*GRANT ALL ON personnel . * TO 'ikimeii'@'localhost' IDENTIFIED BY 'Iki&Meii123';*/

DROP DATABASE IF EXISTS personnel;
CREATE DATABASE IF NOT EXISTS personnel;

DROP TABLE IF EXISTS Employe;
DROP TABLE IF EXISTS Ligue;

CREATE TABLE IF NOT EXISTS Employe
(
    id_employe INT NOT NULL AUTO_INCREMENT,
    nom_employe VARCHAR (50),
    prenom_employe VARCHAR (50),
    password VARCHAR (50),
    email VARCHAR (50);
    DateArrivee DATE,
    DateDepart DATE,
    id_ligue INT,
    CONSTRAINT PK_employe primary key(id_employe),
    FOREIGN KEY (id_ligue) REFERENCES Employe(id_employe)
);

CREATE TABLE IF NOT EXISTS Ligue
(
    id_ligue INT NOT NULL AUTO_INCREMENT,
    nom_ligue VARCHAR (50),
    administrateur VARCHAR (50),
    CONSTRAINT PK_ligue primary key(id_ligue)
)

ALTER TABLE Employe
ADD CONSTRAINT FK_id_ligue
FOREING KEY (id_ligue) references Ligue(id_ligue);