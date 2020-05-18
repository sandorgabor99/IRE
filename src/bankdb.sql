CREATE DATABASE IF NOT EXISTS sgbank DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE sgbank;

create table if not exists transaction
(
    tranID       int(8) auto_increment
        primary key,
    bemenoszamla varchar(255)                       not null,
    kimenoszamla varchar(255)                       not null,
    osszeg       int(15)                            not null,
    datum        datetime default CURRENT_TIMESTAMP not null,
    leiras       varchar(255)                       null
)
    collate = utf8_hungarian_ci;

create table if not exists users
(
    id          int(6)           not null
        primary key,
    teljesnev   varchar(50)      not null,
    telefonszam varchar(20)      not null,
    okmany      varchar(20)      not null,
    cim         varchar(255)     not null,
    aktiv       int(1)           not null,
    szamlas     int(1) default 0 not null,
    constraint okmany
        unique (okmany),
    constraint telefonszam
        unique (telefonszam)
)
    collate = utf8_hungarian_ci;

create table if not exists account
(
    id         int(6) auto_increment
        primary key,
    szamlaszam varchar(10) not null,
    egyenleg   int(15)     not null,
    aktiv      int(1)      not null,
    userkey    int(6)      null,
    constraint const1
        foreign key (userkey) references users (id)
)
    collate = utf8_hungarian_ci;

INSERT INTO transaction (tranID, bemenoszamla, kimenoszamla, osszeg, datum, leiras) VALUES (1, '8068553303', '7055992198', 10, '2020-05-18 18:41:01', 'Proba');
INSERT INTO users (id, teljesnev, telefonszam, okmany, cim, aktiv, szamlas) VALUES (705599, 'Próba Péter 2', '(11)/111-1111', '321332AA', 'Ads 843 jsf', 1, 0);
INSERT INTO users (id, teljesnev, telefonszam, okmany, cim, aktiv, szamlas) VALUES (806855, 'Kori Gabor', '(83)/748-9327', '837483AA', '3737 jfiejfg jgejg 5', 1, 0);
INSERT INTO users (id, teljesnev, telefonszam, okmany, cim, aktiv, szamlas) VALUES (807875, 'Kecske Talp', '(21)/732-7818', '137271AA', '3282 Asd Út 4', 1, 0);
INSERT INTO users (id, teljesnev, telefonszam, okmany, cim, aktiv, szamlas) VALUES (915001, 'Proba Peter', '(61)/232-6761', '276123AA', '893u jifejge opge 4', 0, 0);
INSERT INTO users (id, teljesnev, telefonszam, okmany, cim, aktiv, szamlas) VALUES (964805, 'Próba Daniel', '(21)/321-3132', '217327AA', '2878 nincs szamlaja 1', 1, 0);
INSERT INTO account (id, szamlaszam, egyenleg, aktiv, userkey) VALUES (8, '8068553303', 21232, 1, 806855);
INSERT INTO account (id, szamlaszam, egyenleg, aktiv, userkey) VALUES (9, '7055992198', 232311, 1, 705599);