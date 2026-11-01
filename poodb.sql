-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 01-11-2026 a las 22:05:32
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `poodb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `administradores`
--

CREATE TABLE `administradores` (
  `id` int(11) NOT NULL,
  `usuario` varchar(30) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `correo` varchar(50) NOT NULL,
  `contraseña` varchar(256) NOT NULL,
  `rol` enum('GERENTE','SUPERVISOR','MOSTRADOR','') NOT NULL,
  `rutaimagen` varchar(256) NOT NULL,
  `estado` enum('ACTIVO','SUSPENDIDO','INACTIVO','') NOT NULL,
  `creado` timestamp NOT NULL DEFAULT current_timestamp(),
  `actualizado` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `administradores`
--

INSERT INTO `administradores` (`id`, `usuario`, `nombre`, `apellidos`, `correo`, `contraseña`, `rol`, `rutaimagen`, `estado`, `creado`, `actualizado`) VALUES
(1, 'XDseade', 'Joshua Odin', 'Montoya Flores', 'ejemplo@dj.com', 'sadjsp2', 'GERENTE', 'C:\\Users\\ARMADOS BETO\\Desktop\\imagenes\\197-1974130_transparent-fnaf-8-bit-pixel-freddy-fnaf-3.png', 'ACTIVO', '2026-11-01 20:15:58', '2026-11-01 20:15:58'),
(2, 'LDASJDPWkjlrwes', 'safdesfds', 'ASHAOh', 'sde@gsa.com', 'sadfesa', 'SUPERVISOR', 'C:\\Users\\ARMADOS BETO\\Desktop\\1.png', 'SUSPENDIDO', '2026-11-01 20:17:13', '2026-11-01 20:29:35'),
(6, 'sadfes', 'aDADF', 'dsafesad', 'sdfa@yt.com', 'SDFSDF', 'MOSTRADOR', 'C:\\Users\\ARMADOS BETO\\Pictures\\94_sin_titulo_20251206083239.jpg', 'INACTIVO', '2026-11-01 20:18:57', '2026-11-01 20:23:49');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `administradores`
--
ALTER TABLE `administradores`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `administradores`
--
ALTER TABLE `administradores`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
