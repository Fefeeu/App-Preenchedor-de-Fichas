CREATE DATABASE rpp;
USE rpp;

CREATE USER 'visualizacao'@'localhost' IDENTIFIED BY 'rpp1234';	
GRANT SELECT ON rpp.* TO 'visualizacao'@'localhost';
GRANT INSERT ON rpp.* TO 'visualizacao'@'localhost';

CREATE TABLE IF NOT EXISTS `rpp`.`raca`(
	`idRaca` VARCHAR(45) NOT NULL,
    `nome` VARCHAR(45) NOT NULL,
    `descricao` VARCHAR(100) NOT NULL,
  
	PRIMARY KEY (`idRaca`)
);

CREATE TABLE IF NOT EXISTS `rpp`.`classe` (
  `idClasse` VARCHAR(45) NOT NULL,
  `nome` VARCHAR(45) NULL,
  `descricao` VARCHAR(100) NOT NULL,
  
  PRIMARY KEY (`idClasse`)
);

CREATE TABLE IF NOT EXISTS `rpp`.`user` (
  `idUser` INT NOT NULL,
  `nome` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  
  PRIMARY KEY (`idUser`)
);

CREATE TABLE IF NOT EXISTS `rpp`.`inventario`(
	`idInventario` INT NOT NULL,
    `pc` INT NOT NULL DEFAULT 0,
	`pp` INT NOT NULL DEFAULT 0,
	`pe` INT NOT NULL DEFAULT 0,
	`po` INT NOT NULL DEFAULT 0,
	`pl` INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY (`idInventario`)
);

CREATE TABLE IF NOT EXISTS `rpp`.`magiaUser`(
	`idMagiaUser` INT NOT NULL,
    `nivel1` INT NOT NULL DEFAULT 0,
    `nivel2` INT NOT NULL DEFAULT 0,
    `nivel3` INT NOT NULL DEFAULT 0,
    `nivel4` INT NOT NULL DEFAULT 0,
    `nivel5` INT NOT NULL DEFAULT 0,
    `nivel6` INT NOT NULL DEFAULT 0,
    `nivel7` INT NOT NULL DEFAULT 0,
    `nivel8` INT NOT NULL DEFAULT 0,
    `nivel9` INT NOT NULL DEFAULT 0,
    
    PRIMARY KEY (`idMagiaUser`)
);

CREATE TABLE IF NOT EXISTS `rpp`.`ficha` (
	`idFicha` INT NOT NULL,
	`user_idUser` INT NOT NULL,
	`vivo` TINYINT NOT NULL DEFAULT 1,
	`nivel` INT NOT NULL DEFAULT 1,
	`deslocamento` FLOAT NOT NULL DEFAULT 9.0,
	`dadoDeVida` INT NOT NULL DEFAULT 6,
	`pontosDeVidaBase` INT NOT NULL DEFAULT 1,
	`pontosDeVIdaTemporario` INT NOT NULL DEFAULT 0,
	`inspiracao` TINYINT NOT NULL DEFAULT 0,
	`nomePersonagem` VARCHAR(45) NOT NULL DEFAULT '',
	`classe_idClasse` VARCHAR(45) DEFAULT NULL,
	`raca_idRaca` VARCHAR(45) DEFAULT NULL,
	`antecedente` VARCHAR(45) NOT NULL DEFAULT '',
	`tendencia` VARCHAR(45) NOT NULL DEFAULT '',
	`xp` INT NOT NULL DEFAULT 0,
	`idade` INT NULL,
	`altura` FLOAT NULL,
	`peso` FLOAT NULL,
	`olho` VARCHAR(45) NOT NULL DEFAULT '',
	`pele` VARCHAR(45) NOT NULL DEFAULT '',
	`cabelo` VARCHAR(45) NOT NULL DEFAULT '',
	`forca` INT NOT NULL DEFAULT 10,
	`destreza` INT NOT NULL DEFAULT 10,
	`constituicao` INT NOT NULL DEFAULT 10,
	`inteligencia` INT NOT NULL DEFAULT 10,
	`sabedoria` INT NOT NULL DEFAULT 10,
	`carisma` INT NOT NULL DEFAULT 10,
	`p_forca` TINYINT NOT NULL DEFAULT 0,
	`p_destreza` TINYINT NOT NULL DEFAULT 0,
	`p_constituicao` TINYINT NOT NULL DEFAULT 0,
	`p_inteligencia` TINYINT NOT NULL DEFAULT 0,
	`p_sabedoria` TINYINT NOT NULL DEFAULT 0,
	`p_carisma` TINYINT NOT NULL DEFAULT 0,
	`p_acrobacia` TINYINT NOT NULL DEFAULT 0,
	`p_arcanismo` TINYINT NOT NULL DEFAULT 0,
	`p_atletismo` TINYINT NOT NULL DEFAULT 0,
	`p_atuacao` TINYINT NOT NULL DEFAULT 0,
	`p_blefar` TINYINT NOT NULL DEFAULT 0,
	`p_furtividade` TINYINT NOT NULL DEFAULT 0,
	`p_historia` TINYINT NOT NULL DEFAULT 0,
	`p_intimidacao` TINYINT NOT NULL DEFAULT 0,
	`p_intuicao` TINYINT NOT NULL DEFAULT 0,
	`p_investigacao` TINYINT NOT NULL DEFAULT 0,
	`p_lidarComAnimais` TINYINT NOT NULL DEFAULT 0,
	`p_medicina` TINYINT NOT NULL DEFAULT 0,
	`p_natureza` TINYINT NOT NULL DEFAULT 0,
	`p_percepcao` TINYINT NOT NULL DEFAULT 0,
	`p_persuasao` TINYINT NOT NULL DEFAULT 0,
	`p_prestigitacao` TINYINT NOT NULL DEFAULT 0,
	`p_religiao` TINYINT NOT NULL DEFAULT 0,
	`p_sobrevivencia` TINYINT NOT NULL DEFAULT 0,
	`historia` VARCHAR(100) NOT NULL DEFAULT '',
	`aparencia` VARCHAR(100) NOT NULL DEFAULT '',
	`personalidade` VARCHAR(100) NOT NULL DEFAULT '',
	`ideal` VARCHAR(100) NOT NULL DEFAULT '',
	`ligacao` VARCHAR(100) NOT NULL DEFAULT '',
	`defeitos` VARCHAR(100) NOT NULL DEFAULT '',
    `idiomas` VARCHAR(100) NOT NULL DEFAULT '',
    `proficiencias` VARCHAR(100) NOT NULL DEFAULT '',
    `inventario_idInventario` INT DEFAULT NULL,
    `magiaUser_idMagiaUser` INT DEFAULT NULL,

	PRIMARY KEY (`idFicha`, `user_idUser`),

	FOREIGN KEY (`Raca_idRaca`) REFERENCES `rpp`.`raca` (`idRaca`)
    ON DELETE SET NULL  -- Define o valor padrão quando a raça for deletada
    ON UPDATE CASCADE,


	FOREIGN KEY (`classe_idClasse`) REFERENCES `rpp`.`classe` (`idClasse`)
	ON DELETE SET NULL  -- Define o valor padrão quando a raça for deletada
    ON UPDATE CASCADE,
    
    FOREIGN KEY (`inventario_idInventario`) REFERENCES `rpp`.`inventario` (`idInventario`)
	ON DELETE SET NULL  -- Define o valor padrão quando a raça for deletada
    ON UPDATE CASCADE,

	FOREIGN KEY (`user_idUser`) REFERENCES `rpp`.`user` (`idUser`)
    ON DELETE CASCADE  -- Isso fará a ficha ser deletada quando o user for deletado
    ON UPDATE CASCADE  -- Opcional: propaga atualizações do idUser
);

CREATE TABLE IF NOT EXISTS `rpp`.`magia` (
	`idMagia` INT NOT NULL,
	`magiaUser_idMagiaUser` INT NOT NULL,
	`tipo` ENUM('ataque', 'cura', 'efeito') NOT NULL DEFAULT 'efeito',
	`nome` VARCHAR(45) NOT NULL DEFAULT '',
	`descricao` VARCHAR(45) NOT NULL DEFAULT '',
	`nivel` INT NOT NULL DEFAULT 0,
	`tempoConjuracao` VARCHAR(45) NOT NULL DEFAULT '',
	`duracao` VARCHAR(45) NOT NULL DEFAULT '',
	`alcance` VARCHAR(45) NOT NULL DEFAULT '',
	`area` VARCHAR(45) NOT NULL DEFAULT '',
	`escola` VARCHAR(45) NOT NULL DEFAULT '',
	`tipoAcerto` ENUM('resistencia', 'teste', 'nenhum') NOT NULL DEFAULT 'nenhum',
	`ladoDado` INT NOT NULL DEFAULT 6,
	`numeroDados` INT NOT NULL DEFAULT 1,
    
	PRIMARY KEY (`idmagia`, `magiaUser_idMagiaUser`),
	FOREIGN KEY (`magiaUser_idMagiaUser`) REFERENCES `rpp`.`magiaUser` (`idMagiaUser`)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS `rpp`.`item` (
	`idItem` INT NOT NULL,
	`inventario_idInventario` INT NOT NULL,
	`tipo` ENUM('comum', 'consumivel', 'magico', 'equipavel', 'equipavelMagico', 'arma', 'armaMagica') NOT NULL DEFAULT 'comum',
	`nome` VARCHAR(45) NOT NULL DEFAULT '',
	`descricao` VARCHAR(45) NOT NULL DEFAULT '',
	`peso` FLOAT NOT NULL DEFAULT 0.1,
	`moeda` CHAR NOT NULL DEFAULT 'o',
	`preco` INT NOT NULL DEFAULT 0,
	`usosMaximo` INT NULL,
	`usos` INT NULL,
	`efeito` VARCHAR(45) NULL,
	`bonus` INT NULL,
	`bonus_ca` INT NULL, 
	`proficiencia` TINYINT NULL,
	`numeroDeDados` INT NULL,
	`dadoDeDano` INT NULL,
	`atributo` VARCHAR(45) NULL,
    
	PRIMARY KEY (`iditem`, `inventario_idInventario`),
    
	FOREIGN KEY (`inventario_idInventario`) REFERENCES `rpp`.`inventario` (`idInventario`)
	ON DELETE CASCADE
	ON UPDATE CASCADE
);