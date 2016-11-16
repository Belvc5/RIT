-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 15-11-2016 a las 05:19:25
-- Versión del servidor: 5.5.24-log
-- Versión de PHP: 5.4.3

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `baserit`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE IF NOT EXISTS `empleado` (
  `idpersona` int(11) NOT NULL,
  `sueldo` decimal(10,0) NOT NULL,
  `acceso` varchar(15) NOT NULL,
  `login` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`idpersona`, `sueldo`, `acceso`, `login`, `password`, `estado`) VALUES
(5, '1000', 'Administrador', 'admin', 'adminrit2016', 'Habilitado'),
(6, '600', 'Agente ANT', 'user', 'userit2016', 'Habilitado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infraccion`
--

CREATE TABLE IF NOT EXISTS `infraccion` (
  `idinfraccion` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_infraccion` varchar(30) NOT NULL,
  `clase_contravencion` varchar(30) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `valor_multa` decimal(7,2) NOT NULL,
  `puntos_licencia` varchar(45) NOT NULL,
  `dias_prision` varchar(20) NOT NULL,
  PRIMARY KEY (`idinfraccion`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=11 ;

--
-- Volcado de datos para la tabla `infraccion`
--

INSERT INTO `infraccion` (`idinfraccion`, `tipo_infraccion`, `clase_contravencion`, `descripcion`, `valor_multa`, `puntos_licencia`, `dias_prision`) VALUES
(2, 'Contravención', 'Primera Clase', 'Conducir sin haber obtenido licencia; se retendrá\nel vehículo hasta cancelar la multa', '366.00', 'disminucion de 10 puntos ', '1 a 3 días'),
(3, 'Contravención', 'Segunda Clase', 'Conducir con licencia caducada, anulada, revocada o suspendida, la misma\nque deberá ser retirada inmediatamente.', '183.00', 'disminución de 9 puntos', '0 días'),
(4, 'Delito', 'Primera Clase', 'Exceso de pasajeros en transporte público', '1098.00', 'Suspensión de 6 a 12 meses', '6 a 12 meses'),
(6, 'Contravención', 'Primera Clase', 'Faltar de obra a la autoridad o Agente de Tránsito', '366.00', 'disminucíon de 10 puntos', '1 a 3 días'),
(9, 'Contravención', 'Tercera Clase', 'El conductor de transporte público o comercial que se niugue a brindar el servicio', '146.40', 'disminución de 7.5 puntos', '0 días'),
(10, 'Contravención', 'Quinta Clase', 'conducir un vehículo en sentido contrario a la vía normal de circulación', '54.90', 'disminución de 3 puntos ', '0 días');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `infractor`
--

CREATE TABLE IF NOT EXISTS `infractor` (
  `idpersona` int(11) NOT NULL,
  `placa_vehiculo` varchar(30) NOT NULL,
  PRIMARY KEY (`idpersona`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `infractor`
--

INSERT INTO `infractor` (`idpersona`, `placa_vehiculo`) VALUES
(1, 'ABB-2314'),
(2, 'ABC-2547'),
(7, 'ABD-2256');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE IF NOT EXISTS `persona` (
  `idpersona` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(30) NOT NULL,
  `apellido` varchar(30) NOT NULL,
  `tipo_documento` varchar(15) NOT NULL,
  `num_documento` varchar(11) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` varchar(8) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`idpersona`),
  UNIQUE KEY `telefono` (`telefono`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`idpersona`, `nombre`, `apellido`, `tipo_documento`, `num_documento`, `direccion`, `telefono`, `email`) VALUES
(1, 'Cristina', 'Crespo', 'DNI', '0312417569', 'Yanahurco y los Andes', '4101423', 'cristina.crespo@ucuenca.ec'),
(2, 'María Bélen', 'Vélez Castillo', 'DNI', '0105741599', 'venezuela y remigio crespo', '2814023', 'belvc5@gmail.com'),
(3, 'Andres', 'Perez', 'DNI', '0104631289', 'Ecuador', '2815738', 'andresp@gmail.com'),
(5, 'Belen', 'Velez', 'DNI', '0105741599', 'Ecuador', '2811085', 'bel@gmail.com'),
(6, 'Paul', 'Valdez', 'DNI', '0104583459', 'Ecuador', '4101243', ''),
(7, 'Maria', 'Jimenez', 'DNI', '0106238579', 'tarqui y sucre', '2884702', 'belen_05_velez@hotmail.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro`
--

CREATE TABLE IF NOT EXISTS `registro` (
  `idregistro` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora` varchar(10) NOT NULL,
  `lugar` varchar(50) NOT NULL,
  `idinfraccion` int(11) NOT NULL,
  `idinfractor` int(11) NOT NULL,
  `idempleado` int(11) NOT NULL,
  `estado` varchar(20) NOT NULL,
  PRIMARY KEY (`idregistro`),
  KEY `fk_reserva_infraccion_idx` (`idinfraccion`),
  KEY `fk_reserc-infractor_idx` (`idinfractor`),
  KEY `fk_reserva_empleado_idx` (`idempleado`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=8 ;

--
-- Volcado de datos para la tabla `registro`
--

INSERT INTO `registro` (`idregistro`, `fecha`, `hora`, `lugar`, `idinfraccion`, `idinfractor`, `idempleado`, `estado`) VALUES
(1, '2016-11-01', '10:30am', 'Av. Americas', 2, 1, 6, 'Enviada'),
(3, '2016-11-04', '10:30', 'cuba 1-32 y venezuela', 3, 2, 5, 'Enviada'),
(5, '2016-11-06', '3:30', 'Panamericana Norte', 2, 2, 5, 'Enviada'),
(6, '2016-11-08', '12:50pm', 'Don Bosco y Loja', 9, 2, 5, 'Enviada'),
(7, '2016-11-11', '5:30 pm', 'Av.12 de octubre e Isabela Catolica', 6, 2, 5, 'Enviada');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_persona_empleado` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `infractor`
--
ALTER TABLE `infractor`
  ADD CONSTRAINT `fk_persona_infractor` FOREIGN KEY (`idpersona`) REFERENCES `persona` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `registro`
--
ALTER TABLE `registro`
  ADD CONSTRAINT `fk_reserva-infractor` FOREIGN KEY (`idinfractor`) REFERENCES `infractor` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reserva_empleado` FOREIGN KEY (`idempleado`) REFERENCES `empleado` (`idpersona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_reserva_infraccion` FOREIGN KEY (`idinfraccion`) REFERENCES `infraccion` (`idinfraccion`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
