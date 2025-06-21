CREATE DATABASE rpp;
USE rpp;

CREATE USER 'visualizacao'@'localhost' IDENTIFIED BY 'rpp1234';	
GRANT SELECT ON rpp.* TO 'visualizacao'@'localhost';
GRANT INSERT ON rpp.* TO 'visualizacao'@'localhost';

CREATE TABLE `classe` (
  `idClasse` varchar(45) NOT NULL,
  `nome` varchar(45) DEFAULT NULL,
  `descricao` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `classe`
--

INSERT INTO `classe` (`idClasse`, `nome`, `descricao`) VALUES
('artifice', 'Artífice', 'Inventor de engenhocas mágicas'),
('barbaro', 'Bárbaro', 'Guerreiro feroz com fúria em batalha'),
('bardo', 'Bardo', 'Artista mágico que inspira aliados'),
('bruxo', 'Bruxo', 'Conjurador com pacto sobrenatural'),
('clerigo', 'Clérigo', 'Devoto com magias divinas'),
('druida', 'Druida', 'Guardião da natureza que se transforma'),
('feiticeiro', 'Feiticeiro', 'Magia inata no sangue'),
('guerreiro', 'Guerreiro', 'Mestre em combate físico'),
('ladino', 'Ladino', 'Especialista em furtividade'),
('mago', 'Mago', 'Estudioso de magias arcanas'),
('monge', 'Monge', 'Guerreiro espiritual com artes marciais'),
('paladino', 'Paladino', 'Cavaleiro sagrado com juramentos'),
('patrulheiro', 'Patrulheiro', 'Caçador e explorador');

-- --------------------------------------------------------

--
-- Estrutura para tabela `ficha`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `inventario`
--

CREATE TABLE `inventario` (
  `idInventario` int(11) NOT NULL,
  `pc` int(11) NOT NULL DEFAULT 0,
  `pp` int(11) NOT NULL DEFAULT 0,
  `pe` int(11) NOT NULL DEFAULT 0,
  `po` int(11) NOT NULL DEFAULT 0,
  `pl` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `item`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `magia`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `magiauser`
--

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
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `raca`
--

CREATE TABLE `raca` (
  `idRaca` varchar(45) NOT NULL,
  `nome` varchar(45) NOT NULL,
  `descricao` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `raca`
--

INSERT INTO `raca` (`idRaca`, `nome`, `descricao`) VALUES
('anao', 'Anão', 'Resistente e habilidoso em montanhas'),
('draconato', 'Draconato', 'Híbrido humanoide com traços de dragão'),
('elfo', 'Elfo', 'Gracioso e com afinidade arcana'),
('gnomo', 'Gnomo', 'Pequeno, inventivo e ilusionista'),
('halfling', 'Halfling', 'Pequeno, ágil e sortudo'),
('humano', 'Humano', 'Adaptável e versátil'),
('meio-elfo', 'Meio-Elfo', 'Mistura de humano e elfo, diplomata'),
('meio-orc', 'Meio-Orc', 'Forte e resistente, com herança orc'),
('tiefling', 'Tiefling', 'Herança infernal e poderes sombrios');

-- --------------------------------------------------------

--
-- Estrutura para tabela `user`
--

CREATE TABLE `user` (
  `idUser` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(100) NOT NULL,
  `senha` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Despejando dados para a tabela `user`
--

INSERT INTO `user` (`idUser`, `nome`, `email`, `senha`) VALUES
(1, 'delamain', 'delamain@nc.com', 'delamain123');

--
-- Índices para tabelas despejadas
--

--
-- Índices de tabela `classe`
--
ALTER TABLE `classe`
  ADD PRIMARY KEY (`idClasse`);

--
-- Índices de tabela `ficha`
--
ALTER TABLE `ficha`
  ADD PRIMARY KEY (`idFicha`,`user_idUser`),
  ADD KEY `raca_idRaca` (`raca_idRaca`),
  ADD KEY `classe_idClasse` (`classe_idClasse`),
  ADD KEY `inventario_idInventario` (`inventario_idInventario`),
  ADD KEY `user_idUser` (`user_idUser`),
  ADD KEY `magiaUser_idMagiaUser` (`magiaUser_idMagiaUser`);

--
-- Índices de tabela `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`idInventario`);

--
-- Índices de tabela `item`
--
ALTER TABLE `item`
  ADD PRIMARY KEY (`idItem`,`inventario_idInventario`),
  ADD KEY `inventario_idInventario` (`inventario_idInventario`);

--
-- Índices de tabela `magia`
--
ALTER TABLE `magia`
  ADD PRIMARY KEY (`idMagia`,`magiaUser_idMagiaUser`),
  ADD KEY `magiaUser_idMagiaUser` (`magiaUser_idMagiaUser`);

--
-- Índices de tabela `magiauser`
--
ALTER TABLE `magiauser`
  ADD PRIMARY KEY (`idMagiaUser`);

--
-- Índices de tabela `raca`
--
ALTER TABLE `raca`
  ADD PRIMARY KEY (`idRaca`);

--
-- Índices de tabela `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`idUser`);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `ficha`
--
ALTER TABLE `ficha`
  ADD CONSTRAINT `ficha_ibfk_1` FOREIGN KEY (`raca_idRaca`) REFERENCES `raca` (`idRaca`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_2` FOREIGN KEY (`classe_idClasse`) REFERENCES `classe` (`idClasse`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_3` FOREIGN KEY (`inventario_idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE NO ACTION,
  ADD CONSTRAINT `ficha_ibfk_4` FOREIGN KEY (`user_idUser`) REFERENCES `user` (`idUser`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ficha_ibfk_5` FOREIGN KEY (`magiaUser_idMagiaUser`) REFERENCES `magiauser` (`idMagiaUser`) ON DELETE CASCADE ON UPDATE NO ACTION;

--
-- Restrições para tabelas `item`
--
ALTER TABLE `item`
  ADD CONSTRAINT `item_ibfk_1` FOREIGN KEY (`inventario_idInventario`) REFERENCES `inventario` (`idInventario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Restrições para tabelas `magia`
--
ALTER TABLE `magia`
  ADD CONSTRAINT `magia_ibfk_1` FOREIGN KEY (`magiaUser_idMagiaUser`) REFERENCES `magiauser` (`idMagiaUser`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;