-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Máj 14. 15:04
-- Kiszolgáló verziója: 10.1.38-MariaDB
-- PHP verzió: 7.3.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `sgbank`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `szamla`
--
CREATE DATABASE IF NOT EXISTS sgbank DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_hungarian_ci;

USE sgbank;
DROP TABLE IF EXISTS szamla;
CREATE TABLE `szamla` (
                          `id` int(6) NOT NULL,
                          `szamlaszam` varchar(10) COLLATE utf8_hungarian_ci NOT NULL,
                          `egyenleg` int(15) NOT NULL,
                          `aktiv` int(1) NOT NULL,
                          `userkey` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `tranzakcio`
--
DROP TABLE IF EXISTS tranzakcio;
CREATE TABLE `tranzakcio` (
                              `tranID` int(8) NOT NULL,
                              `bemenoszamla` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
                              `kimenoszamla` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
                              `osszeg` int(15) NOT NULL,
                              `datum` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `users`
--
DROP TABLE IF EXISTS users;
CREATE TABLE `users` (
                         `id` int(6) NOT NULL,
                         `teljesnev` varchar(50) COLLATE utf8_hungarian_ci NOT NULL,
                         `telefonszam` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
                         `okmany` varchar(20) COLLATE utf8_hungarian_ci NOT NULL,
                         `cim` varchar(255) COLLATE utf8_hungarian_ci NOT NULL,
                         `aktiv` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `szamla`
--
ALTER TABLE `szamla`
    ADD PRIMARY KEY (`id`),
    ADD KEY `const1` (`userkey`);

--
-- A tábla indexei `tranzakcio`
--
ALTER TABLE `tranzakcio`
    ADD PRIMARY KEY (`tranID`);

--
-- A tábla indexei `users`
--
ALTER TABLE `users`
    ADD PRIMARY KEY (`id`),
    ADD UNIQUE KEY `telefonszam` (`telefonszam`),
    ADD UNIQUE KEY `okmany` (`okmany`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `szamla`
--
ALTER TABLE `szamla`
    MODIFY `id` int(6) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `tranzakcio`
--
ALTER TABLE `tranzakcio`
    MODIFY `tranID` int(8) NOT NULL AUTO_INCREMENT;

--
-- Megkötések a kiírt táblákhoz
--

--
-- Megkötések a táblához `szamla`
--
ALTER TABLE `szamla`
    ADD CONSTRAINT `const1` FOREIGN KEY (`userkey`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
