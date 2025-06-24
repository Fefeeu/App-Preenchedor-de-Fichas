CREATE DATABASE rpp;
USE rpp;

CREATE USER 'visualizacao'@'localhost' IDENTIFIED BY 'rpp1234';	
GRANT SELECT ON rpp.* TO 'visualizacao'@'localhost';
GRANT INSERT ON rpp.* TO 'visualizacao'@'localhost';

CREATE TABLE `classe` (
  `idClasse` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(100) NOT NULL
);

CREATE TABLE `ficha` (
  `idFicha` int(11) NOT NULL,
  `user_idUser` int(11) NOT NULL,
  `vivo` tinyint(4) NOT NULL DEFAULT 1,
  `nivel` int(11) NOT NULL DEFAULT 1,
  `deslocamento` float NOT NULL DEFAULT 9,
  `dadoDeVida` int(11) NOT NULL DEFAULT 6,
  `pontosDeVidaBase` int(11) NOT NULL DEFAULT 1,
  `pontosDeVIdaTemporario` int(11) NOT NULL DEFAULT 0,
  `inspiracao` tinyint(4) NOT NULL DEFAULT 0,
  `nomePersonagem` varchar(45) NOT NULL DEFAULT '',
  `classe_idClasse` varchar(45) DEFAULT NULL,
  `raca_idRaca` varchar(45) DEFAULT NULL,
  `antecedente` varchar(45) NOT NULL DEFAULT '',
  `tendencia` varchar(45) NOT NULL DEFAULT '',
  `xp` int(11) NOT NULL DEFAULT 0,
  `idade` int(11) DEFAULT NULL,
  `altura` float DEFAULT NULL,
  `peso` float DEFAULT NULL,
  `olho` varchar(45) NOT NULL DEFAULT '',
  `pele` varchar(45) NOT NULL DEFAULT '',
  `cabelo` varchar(45) NOT NULL DEFAULT '',
  `forca` int(11) NOT NULL DEFAULT 10,
  `destreza` int(11) NOT NULL DEFAULT 10,
  `constituicao` int(11) NOT NULL DEFAULT 10,
  `inteligencia` int(11) NOT NULL DEFAULT 10,
  `sabedoria` int(11) NOT NULL DEFAULT 10,
  `carisma` int(11) NOT NULL DEFAULT 10,
  `p_forca` tinyint(4) NOT NULL DEFAULT 0,
  `p_destreza` tinyint(4) NOT NULL DEFAULT 0,
  `p_constituicao` tinyint(4) NOT NULL DEFAULT 0,
  `p_inteligencia` tinyint(4) NOT NULL DEFAULT 0,
  `p_sabedoria` tinyint(4) NOT NULL DEFAULT 0,
  `p_carisma` tinyint(4) NOT NULL DEFAULT 0,
  `p_acrobacia` tinyint(4) NOT NULL DEFAULT 0,
  `p_arcanismo` tinyint(4) NOT NULL DEFAULT 0,
  `p_atletismo` tinyint(4) NOT NULL DEFAULT 0,
  `p_atuacao` tinyint(4) NOT NULL DEFAULT 0,
  `p_blefar` tinyint(4) NOT NULL DEFAULT 0,
  `p_furtividade` tinyint(4) NOT NULL DEFAULT 0,
  `p_historia` tinyint(4) NOT NULL DEFAULT 0,
  `p_intimidacao` tinyint(4) NOT NULL DEFAULT 0,
  `p_intuicao` tinyint(4) NOT NULL DEFAULT 0,
  `p_investigacao` tinyint(4) NOT NULL DEFAULT 0,
  `p_lidarComAnimais` tinyint(4) NOT NULL DEFAULT 0,
  `p_medicina` tinyint(4) NOT NULL DEFAULT 0,
  `p_natureza` tinyint(4) NOT NULL DEFAULT 0,
  `p_percepcao` tinyint(4) NOT NULL DEFAULT 0,
  `p_persuasao` tinyint(4) NOT NULL DEFAULT 0,
  `p_prestigitacao` tinyint(4) NOT NULL DEFAULT 0,
  `p_religiao` tinyint(4) NOT NULL DEFAULT 0,
  `p_sobrevivencia` tinyint(4) NOT NULL DEFAULT 0,
  `historia` varchar(100) NOT NULL DEFAULT '',
  `aparencia` varchar(100) NOT NULL DEFAULT '',
  `personalidade` varchar(100) NOT NULL DEFAULT '',
  `ideal` varchar(100) NOT NULL DEFAULT '',
  `ligacao` varchar(100) NOT NULL DEFAULT '',
  `defeitos` varchar(100) NOT NULL DEFAULT '',
  `idiomas` varchar(100) NOT NULL DEFAULT '',
  `proficiencias` varchar(100) NOT NULL DEFAULT '',
  `inventario_idInventario` int(11) DEFAULT NULL,
  `magiaUser_idMagiaUser` int(11) DEFAULT NULL
);

CREATE TABLE `inventario` (
  `idInventario` int(11) NOT NULL,
  `pc` int(11) NOT NULL DEFAULT 0,
  `pp` int(11) NOT NULL DEFAULT 0,
  `pe` int(11) NOT NULL DEFAULT 0,
  `po` int(11) NOT NULL DEFAULT 0,
  `pl` int(11) NOT NULL DEFAULT 0
);

CREATE TABLE `item` (
  `idItem` int(11) NOT NULL,
  `inventario_idInventario` int(11) NOT NULL,
  `tipo` enum('comum','consumivel','magico','equipavel','equipavelMagico','arma','armaMagica') NOT NULL DEFAULT 'comum',
  `nome` varchar(45) NOT NULL DEFAULT '',
  `descricao` varchar(45) NOT NULL DEFAULT '',
  `peso` float NOT NULL DEFAULT 0.1,
  `moeda` char(1) NOT NULL DEFAULT 'o',
  `preco` int(11) NOT NULL DEFAULT 0,
  `usosMaximo` int(11) DEFAULT NULL,
  `usos` int(11) DEFAULT NULL,
  `efeito` varchar(45) DEFAULT NULL,
  `bonus` int(11) DEFAULT NULL,
  `bonus_ca` int(11) DEFAULT NULL,
  `proficiencia` tinyint(4) DEFAULT NULL,
  `numeroDeDados` int(11) DEFAULT NULL,
  `dadoDeDano` int(11) DEFAULT NULL,
  `atributo` varchar(45) DEFAULT NULL
);

CREATE TABLE `magia` (
  `idMagia` int(11) NOT NULL,
  `magiaUser_idMagiaUser` int(11) NOT NULL,
  `tipo` enum('ataque','cura','efeito') NOT NULL DEFAULT 'efeito',
  `nome` varchar(45) NOT NULL DEFAULT '',
  `descricao` varchar(45) NOT NULL DEFAULT '',
  `nivel` int(11) NOT NULL DEFAULT 0,
  `tempoConjuracao` varchar(45) NOT NULL DEFAULT '',
  `duracao` varchar(45) NOT NULL DEFAULT '',
  `alcance` varchar(45) NOT NULL DEFAULT '',
  `area` varchar(45) NOT NULL DEFAULT '',
  `escola` varchar(45) NOT NULL DEFAULT '',
  `tipoAcerto` enum('resistencia','teste','nenhum') NOT NULL DEFAULT 'nenhum',
  `ladoDado` int(11) NOT NULL DEFAULT 6,
  `numeroDados` int(11) NOT NULL DEFAULT 1
);

CREATE TABLE `magiauser` (
  `idMagiaUser` int(11) NOT NULL,
  `nivel1` int(11) NOT NULL DEFAULT 0,
  `nivel2` int(11) NOT NULL DEFAULT 0,
  `nivel3` int(11) NOT NULL DEFAULT 0,
  `nivel4` int(11) NOT NULL DEFAULT 0,
  `nivel5` int(11) NOT NULL DEFAULT 0,
  `nivel6` int(11) NOT NULL DEFAULT 0,
  `nivel7` int(11) NOT NULL DEFAULT 0,
  `nivel8` int(11) NOT NULL DEFAULT 0,
  `nivel9` int(11) NOT NULL DEFAULT 0
);

CREATE TABLE `raca` (
  `idRaca` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(100) NOT NULL
);

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(45) NOT NULL
);

ALTER TABLE `classe`
  ADD PRIMARY KEY (`idClasse`);

ALTER TABLE `ficha`
  ADD PRIMARY KEY (`idFicha`,`user_idUser`),
  ADD KEY `raca_idRaca` (`raca_idRaca`),
  ADD KEY `classe_idClasse` (`classe_idClasse`),
  ADD KEY `inventario_idInventario` (`inventario_idInventario`),
  ADD KEY `user_idUser` (`user_idUser`),
  ADD KEY `magiaUser_idMagiaUser` (`magiaUser_idMagiaUser`);

ALTER TABLE `inventario`
  ADD PRIMARY KEY (`idInventario`);

ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`,`inventario_idInventario`),
  ADD KEY `inventario_idInventario` (`inventario_idInventario`);

ALTER TABLE `magia`
  ADD PRIMARY KEY (`idMagia`,`magiaUser_idMagiaUser`),
  ADD KEY `magiaUser_idMagiaUser` (`magiaUser_idMagiaUser`);

ALTER TABLE `magiauser`
  ADD PRIMARY KEY (`idMagiaUser`);

ALTER TABLE `raca`
  ADD PRIMARY KEY (`idRaca`);

ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

ALTER TABLE `ficha`
  ADD CONSTRAINT `ficha_ibfk_1` FOREIGN KEY (`raca_idRaca`) REFERENCES `raca` (`idRaca`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_2` FOREIGN KEY (`classe_idClasse`) REFERENCES `classe` (`idClasse`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_3` FOREIGN KEY (`inventario_idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `ficha_ibfk_4` FOREIGN KEY (`user_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_5` FOREIGN KEY (`magiaUser_idMagiaUser`) REFERENCES `magiauser` (`idMagiaUser`) ON DELETE CASCADE ON UPDATE NO ACTION;

ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`inventario_idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE `magia`
  ADD CONSTRAINT `magia_ibfk_1` FOREIGN KEY (`magiaUser_idMagiaUser`) REFERENCES `magiauser` (`idMagiaUser`) ON DELETE CASCADE ON UPDATE CASCADE;