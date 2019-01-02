-- MySQL Script generated by MySQL Workbench
-- Thu Apr 12 09:05:38 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema compras
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema compras
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `csem_2018` DEFAULT CHARACTER SET latin1 ;
USE `csem_2018` ;

-- -----------------------------------------------------
-- Table `csem_2018`.`Destinacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Destinacao` (
  `CodDest` INT(11) NOT NULL AUTO_INCREMENT,
  `Destinacao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodDest`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Moedas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Moedas` (
  `CodMoeda` INT(11) NOT NULL AUTO_INCREMENT,
  `Moeda` VARCHAR(255) NULL DEFAULT NULL,
  `Abrev` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodMoeda`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Projetos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Projetos` (
  `CodProjeto` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeProjeto` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodProjeto`),
  UNIQUE INDEX `NomeProjeto_UNIQUE` (`NomeProjeto` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`TipoReq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`TipoReq` (
  `CodTipoReq` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoRequisicao` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodTipoReq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`StatusRequisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`StatusRequisicao` (
  `CodStatus` INT(11) NOT NULL,
  `Status` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodStatus`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`TipoFrete`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`TipoFrete` (
  `CodFrete` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoFrete` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodFrete`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Niveis`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Niveis` (
  `CodNivel` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeNivel` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodNivel`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Usuario` (
  `CodUsuario` INT(11) NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(255) NOT NULL,
  `Senha` VARCHAR(255) NULL DEFAULT NULL,
  `EmailFunc` VARCHAR(255) NULL DEFAULT NULL,
  `CodNivel` INT(11) NOT NULL,
  PRIMARY KEY (`CodUsuario`),
  UNIQUE INDEX `Nome_UNIQUE` (`Nome` ASC),
  INDEX `fk_Usuario_Niveis1_idx` (`CodNivel` ASC),
  CONSTRAINT `fk_Usuario_Niveis1`
    FOREIGN KEY (`CodNivel`)
    REFERENCES `csem_2018`.`Niveis` (`CodNivel`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`StatusArq`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`StatusArq` (
  `CodArq` INT(11) NOT NULL AUTO_INCREMENT,
  `Arqvd` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodArq`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Solicitante`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Solicitante` (
  `CodSolicitante` INT(11) NOT NULL,
  `NomeSolicitante` VARCHAR(45) NULL,
  PRIMARY KEY (`CodSolicitante`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `csem_2018`.`EtapaRequisicao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`EtapaRequisicao` (
  `CodEtapaRequisicao` INT NOT NULL,
  `EtapaRequisicao` VARCHAR(45) NULL,
  PRIMARY KEY (`CodEtapaRequisicao`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `csem_2018`.`Requisicoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Requisicoes` (
  `CodRequisicao` INT(11) NOT NULL AUTO_INCREMENT,
  `DataSolicitacao` DATETIME NULL DEFAULT NULL,
  `DataCriacao` DATETIME NULL DEFAULT NULL,
  `DataAprov` DATETIME NULL DEFAULT NULL,
  `DataEntrega` DATETIME NULL,
  `DataAprovTecnico` DATETIME NULL,
  `DataPrevisaoEntrega` DATETIME NULL,
  `Justificativa` MEDIUMTEXT NULL DEFAULT NULL,
  `Motivo` MEDIUMTEXT NULL,
  `Vinculacao` INT(11) NULL DEFAULT NULL,
  `Aprovador` VARCHAR(45) NULL,
  `AprovadorTecnico` VARCHAR(45) NULL,
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
  `CodEtapaRequisicao` INT(11) NOT NULL,
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
  INDEX `fk_Requisicoes_EtapaRequisicao1_idx` (`CodEtapaRequisicao` ASC),
  CONSTRAINT `fk_Requisicoes_Destinacao1`
    FOREIGN KEY (`CodDest`)
    REFERENCES `csem_2018`.`Destinacao` (`CodDest`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Moedas1`
    FOREIGN KEY (`CodMoeda`)
    REFERENCES `csem_2018`.`Moedas` (`CodMoeda`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Projetos1`
    FOREIGN KEY (`CodProjeto`)
    REFERENCES `csem_2018`.`Projetos` (`CodProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoReq1`
    FOREIGN KEY (`CodTipoReq`)
    REFERENCES `csem_2018`.`TipoReq` (`CodTipoReq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusRequisicao1`
    FOREIGN KEY (`CodigoStatus`)
    REFERENCES `csem_2018`.`StatusRequisicao` (`CodStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoFrete1`
    FOREIGN KEY (`CodFrete`)
    REFERENCES `csem_2018`.`TipoFrete` (`CodFrete`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Usuario1`
    FOREIGN KEY (`CodUsuario`)
    REFERENCES `csem_2018`.`Usuario` (`CodUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusArq1`
    FOREIGN KEY (`CodArq`)
    REFERENCES `csem_2018`.`StatusArq` (`CodArq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Solicitante1`
    FOREIGN KEY (`CodSolicitante`)
    REFERENCES `csem_2018`.`Solicitante` (`CodSolicitante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_EtapaRequisicao1`
    FOREIGN KEY (`CodEtapaRequisicao`)
    REFERENCES `csem_2018`.`EtapaRequisicao` (`CodEtapaRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Item` (
  `CodItem` INT(11) NOT NULL,
  `NomeItem` VARCHAR(255) NULL DEFAULT NULL,
  `Unidade` VARCHAR(255) NULL DEFAULT NULL,
  `Quantidade` INT(11) NULL DEFAULT NULL,
  `DescricaoTecnica` MEDIUMTEXT NULL DEFAULT NULL,
  `InformacoesAdicionais` MEDIUMTEXT NULL DEFAULT NULL,
  `ValorUnitario` DOUBLE NULL DEFAULT NULL,
  `ValorTotal` DOUBLE NULL,
  `CodRequisicao` INT(11) NOT NULL,
  PRIMARY KEY (`CodItem`),
  INDEX `fk_Item_Requisicoes1_idx` (`CodRequisicao` ASC),
  CONSTRAINT `fk_Item_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `csem_2018`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Fornecedores`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Fornecedores` (
  `CodFornecedor` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeFornecedor` VARCHAR(255) NULL DEFAULT NULL,
  `Telefone` VARCHAR(255) NULL DEFAULT NULL,
  `Email` VARCHAR(255) NULL DEFAULT NULL,
  `Contato` VARCHAR(255) NULL DEFAULT NULL,
  `InformacoesAdicionais` MEDIUMTEXT NULL,
  `ValorInicial` DOUBLE NULL DEFAULT NULL,
  `ValorFinal` DOUBLE NULL,
  `Escolha` TINYINT(1) NULL DEFAULT NULL,
  `CNPJ` VARCHAR(255) NULL DEFAULT NULL,
  `CPF` VARCHAR(255) NULL DEFAULT NULL,
  `Banco` INT(11) NULL DEFAULT NULL,
  `Conta` VARCHAR(255) NULL DEFAULT NULL,
  `Agencia` INT(11) NULL DEFAULT NULL,
  `TempoProducao` INT(11) NULL,
  `CodRequisicao` INT(11) NOT NULL,
  PRIMARY KEY (`CodFornecedor`),
  INDEX `fk_Fornecedores_Requisicoes1_idx` (`CodRequisicao` ASC),
  CONSTRAINT `fk_Fornecedores_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `csem_2018`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`TipoCobranca`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`TipoCobranca` (
  `CodTipoCobranca` INT(11) NOT NULL AUTO_INCREMENT,
  `TipoCobranca` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodTipoCobranca`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`OrdemPagto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`OrdemPagto` (
  `CodOrdemPagamento` INT(11) NOT NULL,
  `DataOP` DATETIME NULL DEFAULT NULL,
  `DataVencimento` DATETIME NULL DEFAULT NULL,
  `Parcela` INT(11) NULL DEFAULT NULL,
  `ValorPagar` DOUBLE NULL DEFAULT NULL,
  `NCM` VARCHAR(10) NULL DEFAULT NULL,
  `PrevisaoEmbarque` DATETIME NULL DEFAULT NULL,
  `Comentarios` VARCHAR(255) NULL DEFAULT NULL,
  `CodTipoCobranca` INT(11) NOT NULL,
  `CodRequisicao` INT(11) NOT NULL,
  INDEX `fk_OrdemPagto_TipoCobranca1_idx` (`CodTipoCobranca` ASC),
  PRIMARY KEY (`CodOrdemPagamento`),
  INDEX `fk_OrdemPagto_Requisicoes1_idx` (`CodRequisicao` ASC),
  CONSTRAINT `fk_OrdemPagto_TipoCobranca1`
    FOREIGN KEY (`CodTipoCobranca`)
    REFERENCES `csem_2018`.`TipoCobranca` (`CodTipoCobranca`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrdemPagto_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `csem_2018`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`TipoEmbarque`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`TipoEmbarque` (
  `CodTipoEmbarque` INT(11) NOT NULL,
  `NomeEmbarque` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`CodTipoEmbarque`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`ReqInterOutros`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`ReqInterOutros` (
  `CodReqInterOutros` INT(11) NOT NULL,
  `Frete` DOUBLE NULL DEFAULT NULL,
  `TaxaSiscomex` DOUBLE NULL DEFAULT NULL,
  `Impostos` DOUBLE NULL DEFAULT NULL,
  `CodTipoEmbarque` INT(11) NOT NULL,
  `CodRequisicao` INT(11) NOT NULL,
  INDEX `fk_ReqInterOutros_TipoEmbarque1_idx` (`CodTipoEmbarque` ASC),
  PRIMARY KEY (`CodReqInterOutros`),
  INDEX `fk_ReqInterOutros_Requisicoes1_idx` (`CodRequisicao` ASC),
  CONSTRAINT `fk_ReqInterOutros_TipoEmbarque1`
    FOREIGN KEY (`CodTipoEmbarque`)
    REFERENCES `csem_2018`.`TipoEmbarque` (`CodTipoEmbarque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReqInterOutros_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `csem_2018`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`ReqPO`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`ReqPO` (
  `CodRequisicao` INT(11) NOT NULL,
  `NCM` VARCHAR(255) NULL DEFAULT NULL,
  `PrevisaoEmbarque` DATETIME NULL DEFAULT NULL,
  `CodTipoEmbarque` INT(11) NOT NULL,
  `Endereço` MEDIUMTEXT NULL DEFAULT NULL,
  `Telefone` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `fk_ReqPO_TipoEmbarque1_idx` (`CodTipoEmbarque` ASC),
  PRIMARY KEY (`CodRequisicao`),
  CONSTRAINT `fk_ReqPO_TipoEmbarque1`
    FOREIGN KEY (`CodTipoEmbarque`)
    REFERENCES `csem_2018`.`TipoEmbarque` (`CodTipoEmbarque`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReqPO_Requisicoes1`
    FOREIGN KEY (`CodRequisicao`)
    REFERENCES `csem_2018`.`Requisicoes` (`CodRequisicao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`Solicitacoes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`Solicitacoes` (
  `CodSolicitacao` INT(11) NOT NULL AUTO_INCREMENT,
  `DataSolicitacao` DATETIME NULL DEFAULT NULL,
  `Justificativa` MEDIUMTEXT NULL,
  `EmailSolicitante` VARCHAR(255) NULL,
  `Motivo` MEDIUMTEXT NULL,
  `CodProjeto` INT(11) NOT NULL,
  `CodTipoReq` INT(11) NOT NULL,
  `CodigoStatus` INT(11) NOT NULL,
  `CodSolicitante` INT(11) NOT NULL,
  `CodArq` INT(11) NOT NULL,
  `CodUsuario` INT(11) NOT NULL,
  PRIMARY KEY (`CodSolicitacao`),
  INDEX `fk_Requisicoes_Projetos1_idx` (`CodProjeto` ASC),
  INDEX `fk_Requisicoes_TipoReq1_idx` (`CodTipoReq` ASC),
  INDEX `fk_Requisicoes_StatusRequisicao1_idx` (`CodigoStatus` ASC),
  INDEX `fk_Requisicoes_Solicitante1_idx` (`CodSolicitante` ASC),
  INDEX `fk_Solicitacoes_StatusArq1_idx` (`CodArq` ASC),
  INDEX `fk_Solicitacoes_Usuario1_idx` (`CodUsuario` ASC),
  CONSTRAINT `fk_Requisicoes_Projetos10`
    FOREIGN KEY (`CodProjeto`)
    REFERENCES `csem_2018`.`Projetos` (`CodProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoReq10`
    FOREIGN KEY (`CodTipoReq`)
    REFERENCES `csem_2018`.`TipoReq` (`CodTipoReq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusRequisicao10`
    FOREIGN KEY (`CodigoStatus`)
    REFERENCES `csem_2018`.`StatusRequisicao` (`CodStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Solicitante10`
    FOREIGN KEY (`CodSolicitante`)
    REFERENCES `csem_2018`.`Solicitante` (`CodSolicitante`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Solicitacoes_StatusArq1`
    FOREIGN KEY (`CodArq`)
    REFERENCES `csem_2018`.`StatusArq` (`CodArq`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Solicitacoes_Usuario1`
    FOREIGN KEY (`CodUsuario`)
    REFERENCES `csem_2018`.`Usuario` (`CodUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`ItemSolicitacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`ItemSolicitacao` (
  `CodItemSolicitacao` INT(11) NOT NULL,
  `NomeItem` VARCHAR(255) NULL DEFAULT NULL,
  `Unidade` VARCHAR(255) NULL DEFAULT NULL,
  `Quantidade` INT(11) NULL DEFAULT NULL,
  `DescricaoTecnica` MEDIUMTEXT NULL DEFAULT NULL,
  `InformacoesAdicionais` MEDIUMTEXT NULL DEFAULT NULL,
  `ValorUnitario` DOUBLE NULL DEFAULT NULL,
  `ValorTotal` DOUBLE NULL,
  `CodSolicitacao` INT(11) NOT NULL,
  PRIMARY KEY (`CodItemSolicitacao`),
  INDEX `fk_ItemSolicitacao_Solicitacoes1_idx` (`CodSolicitacao` ASC),
  CONSTRAINT `fk_ItemSolicitacao_Solicitacoes1`
    FOREIGN KEY (`CodSolicitacao`)
    REFERENCES `csem_2018`.`Solicitacoes` (`CodSolicitacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `csem_2018`.`FornecedoresSolicitacao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `csem_2018`.`FornecedoresSolicitacao` (
  `CodFornecedor` INT(11) NOT NULL AUTO_INCREMENT,
  `NomeFornecedor` VARCHAR(255) NULL DEFAULT NULL,
  `Telefone` VARCHAR(255) NULL DEFAULT NULL,
  `Email` VARCHAR(255) NULL DEFAULT NULL,
  `Contato` VARCHAR(255) NULL DEFAULT NULL,
  `InformacoesAdicionais` MEDIUMTEXT NULL,
  `ValorTotal` DOUBLE NULL DEFAULT NULL,
  `CodSolicitacao` INT(11) NOT NULL,
  PRIMARY KEY (`CodFornecedor`),
  INDEX `fk_Fornecedores_copy1_Solicitacoes1_idx` (`CodSolicitacao` ASC),
  CONSTRAINT `fk_Fornecedores_copy1_Solicitacoes1`
    FOREIGN KEY (`CodSolicitacao`)
    REFERENCES `csem_2018`.`Solicitacoes` (`CodSolicitacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
