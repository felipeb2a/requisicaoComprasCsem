-- -----------------------------------------------------
-- Table `compras`.`Niveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Niveis` (
  `CodNivel` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeNivel` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodNivel`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Usuario` (
  `CodUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NULL DEFAULT NULL,
  `Senha` VARCHAR(255) NULL DEFAULT NULL,
  `EmailFunc` VARCHAR(255) NULL DEFAULT NULL,
  `CodNivel` INT(11) NOT NULL,
  PRIMARY KEY (`CodUsuario`),
  INDEX `fk_Usuario_Niveis1_idx` (`CodNivel` ASC),
  CONSTRAINT `fk_Usuario_Niveis1`
    FOREIGN KEY (`CodNivel`)
    REFERENCES `compras`.`Niveis` (`CodNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`Solicitante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Solicitante` (
  `CodSolicitante` INT NOT NULL,
  `NomeSolicitante` VARCHAR(45) NULL,
  PRIMARY KEY (`CodSolicitante`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `compras`.`Projetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Projetos` (
  `CodigoProjeto` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeProjeto` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodigoProjeto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`TipoReq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`TipoReq` (
  `CodTipoReq` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoRequisicao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodTipoReq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`Destinacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Destinacao` (
  `CodDest` INT(11) NOT NULL AUTO_INCREMENT,
  `Destinacao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodDest`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`TipoFrete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`TipoFrete` (
  `CodFrete` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoFrete` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodFrete`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`StatusRequisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`StatusRequisicao` (
  `CodStatus` INT(11) NOT NULL,
  `Status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodStatus`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`Moedas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Moedas` (
  `CodMoeda` INT(11) NOT NULL AUTO_INCREMENT,
  `Moeda` VARCHAR(255) NULL DEFAULT NULL,
  `Abrev` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodMoeda`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`StatusArq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`StatusArq` (
  `CodArq` INT(11) NOT NULL AUTO_INCREMENT,
  `Arqvd` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodArq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `compras`.`Requisicoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Requisicoes` (
  `CodRequisicao` INT(11) NOT NULL AUTO_INCREMENT,
  `DataSolicitacao` DATETIME NULL DEFAULT NULL,
  `DataCriacao` DATETIME NULL DEFAULT NULL,
  `DataAprov` DATETIME NULL DEFAULT NULL,
  `Justificativa` MEDIUMTEXT NULL DEFAULT NULL,
  `Vinculacao` INT(11) NULL DEFAULT NULL,
  `Aprovador` VARCHAR(45) NULL,
  `TipoAprovador` VARCHAR(45) NULL,
  `CodDest` INT(11) NOT NULL,
  `CodMoeda` INT(11) NOT NULL,
  `CodProjeto` INT(11) NOT NULL,
  `CodTipoReq` INT(11) NOT NULL,
  `CodigoStatus` INT(11) NOT NULL,
  `CodFrete` INT(11) NOT NULL,
  `CodUsuario` INT(11) NOT NULL,
  `CodArq` INT(11) NOT NULL,
  `CodSolicitante` INT(11) NOT NULL,
  PRIMARY KEY (`CodRequisicao`),
  INDEX `fk_Requisicoes_Destinacao1_idx` (`CodDest` ASC),
  INDEX `fk_Requisicoes_Moedas1_idx` (`CodMoeda` ASC),
  INDEX `fk_Requisicoes_Projetos1_idx` (`CodProjeto` ASC),
  INDEX `fk_Requisicoes_TipoReq1_idx` (`CodTipoReq` ASC),
  INDEX `fk_Requisicoes_StatusRequisicao1_idx` (`CodigoStatus` ASC),
  INDEX `fk_Requisicoes_TipoFrete1_idx` (`CodFrete` ASC),
  INDEX `fk_Requisicoes_Usuario1_idx` (`CodUsuario` ASC),
  INDEX `fk_Requisicoes_StatusArq1_idx` (`CodArq` ASC),
  INDEX `fk_Requisicoes_Solicitante1_idx` (`CodSolicitante` ASC),
  CONSTRAINT `fk_Requisicoes_Destinacao1`
    FOREIGN KEY (`CodDest`)
    REFERENCES `compras`.`Destinacao` (`CodDest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Moedas1`
    FOREIGN KEY (`CodMoeda`)
    REFERENCES `compras`.`Moedas` (`CodMoeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Projetos1`
    FOREIGN KEY (`CodProjeto`)
    REFERENCES `compras`.`Projetos` (`CodProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoReq1`
    FOREIGN KEY (`CodTipoReq`)
    REFERENCES `compras`.`TipoReq` (`CodTipoReq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusRequisicao1`
    FOREIGN KEY (`CodigoStatus`)
    REFERENCES `compras`.`StatusRequisicao` (`CodStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoFrete1`
    FOREIGN KEY (`CodFrete`)
    REFERENCES `compras`.`TipoFrete` (`CodFrete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Usuario1`
    FOREIGN KEY (`CodUsuario`)
    REFERENCES `compras`.`Usuario` (`CodUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusArq1`
    FOREIGN KEY (`CodArq`)
    REFERENCES `compras`.`StatusArq` (`CodArq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Solicitante1`
    FOREIGN KEY (`CodSolicitante`)
    REFERENCES `compras`.`Solicitante` (`CodSolicitante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

-- -----------------------------------------------------
-- Table `compras`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Item` (
  `CodRequisicao` INT(11) NOT NULL,
  `NomeItem` INT(11) NULL DEFAULT NULL,
  `Unidade` VARCHAR(255) NULL DEFAULT NULL,
  `Quantidade` INT(11) NULL DEFAULT NULL,
  `DescricaoTecnica` MEDIUMTEXT NULL DEFAULT NULL,
  `InformacoesAdicionais` MEDIUMTEXT NULL DEFAULT NULL,
  `ValorUnitario` DOUBLE NULL DEFAULT NULL,
  `ValorTotal` DOUBLE NULL,
  PRIMARY KEY (`CodRequisicao`),
  CONSTRAINT `fk_Item_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `compras`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `compras`.`Fornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `compras`.`Fornecedores` (
  `CodFornecedor` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeFornecedor` VARCHAR(255) NULL DEFAULT NULL,
  `Telefone` VARCHAR(255) NULL DEFAULT NULL,
  `Email` VARCHAR(255) NULL DEFAULT NULL,
  `Contato` VARCHAR(255) NULL DEFAULT NULL,
  `ValorTotal` DOUBLE NULL DEFAULT NULL,
  `Escolha` TINYINT(1) NULL DEFAULT NULL,
  `CNPJ` VARCHAR(255) NULL DEFAULT NULL,
  `CPF` VARCHAR(255) NULL DEFAULT NULL,
  `Banco` INT(11) NULL DEFAULT NULL,
  `Conta` VARCHAR(255) NULL DEFAULT NULL,
  `Agencia` INT(11) NULL DEFAULT NULL,
  `CodRequisicao` INT(11) NOT NULL,
  PRIMARY KEY (`CodFornecedor`),
  INDEX `fk_Fornecedores_Requisicoes1_idx` (`CodRequisicao` ASC),
  CONSTRAINT `fk_Fornecedores_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `compras`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;

