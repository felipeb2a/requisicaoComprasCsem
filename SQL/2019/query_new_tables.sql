-- -----------------------------------------------------
-- Table `sunew_2019`.`Unidade`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sunew_2019`.`Unidade` (
  `CodUnidade` INT NOT NULL AUTO_INCREMENT,
  `Unidade` VARCHAR(45) NULL,
  PRIMARY KEY (`CodUnidade`))
ENGINE = InnoDB;

insert into sunew_2019.Unidade values (1, 'Litro');
insert into sunew_2019.Unidade values (2, 'Mililitro');
insert into sunew_2019.Unidade values (3, 'Metro');
insert into sunew_2019.Unidade values (4, 'Metro quadrado');
insert into sunew_2019.Unidade values (5, 'Metro cúbico');
insert into sunew_2019.Unidade values (6, 'Centímetro');
insert into sunew_2019.Unidade values (7, 'Milimetro');
insert into sunew_2019.Unidade values (8, 'Polegada');
insert into sunew_2019.Unidade values (9, 'Caixa');
insert into sunew_2019.Unidade values (10, 'Pacote');
insert into sunew_2019.Unidade values (11, 'Valor Bruto');
insert into sunew_2019.Unidade values (12, 'Quilograma');
insert into sunew_2019.Unidade values (13, 'Grama');
insert into sunew_2019.Unidade values (14, 'Barra');
insert into sunew_2019.Unidade values (15, 'Saco');
insert into sunew_2019.Unidade values (16, 'Galão');
insert into sunew_2019.Unidade values (17, 'Conjunto');
insert into sunew_2019.Unidade values (18, 'Kit');
insert into sunew_2019.Unidade values (19, 'Unidade');

-- -----------------------------------------------------
-- Table `sunew_2019`.`CodigoProdutoERP`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sunew_2019`.`CodigoProdutoERP` (
  `CodProduto` INT NOT NULL,
  `CodProdutoERP` INT NOT NULL,
  `Descricao` MEDIUMTEXT NULL,
  `UnidadeDeMedida` VARCHAR(200) NULL,
  PRIMARY KEY (`CodProduto`))
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `sunew_2019`.`ProjetosDeArea`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sunew_2019`.`ProjetosDeArea` (
  `CodProjetoDeArea` INT NOT NULL AUTO_INCREMENT,
  `NomeProjetosDeArea` VARCHAR(1000) NULL,
  `CodProjeto` INT(11) NOT NULL,
  PRIMARY KEY (`CodProjetoDeArea`),
  INDEX `fk_ProjetosDeArea_Projetos1_idx` (`CodProjeto` ASC),
  UNIQUE INDEX `NomeProjetosDeArea_UNIQUE` (`NomeProjetosDeArea` ASC),
  CONSTRAINT `fk_ProjetosDeArea_Projetos1`
    FOREIGN KEY (`CodProjeto`)
    REFERENCES `sunew_2019`.`Projetos` (`CodProjeto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- ALTER TABLE ITEM
ALTER TABLE `sunew_2019`.`Item` 
ADD COLUMN `CodProdutoERP` INT(11) NULL DEFAULT NULL AFTER `CodRequisicao`,
ADD INDEX `fk_Item_CodigoProdutoERP1_idx` (`CodProdutoERP` ASC);
ALTER TABLE `sunew_2019`.`item` 
ADD CONSTRAINT `fk_Item_CodigoProdutoERP1_idx`
  FOREIGN KEY (`CodProdutoERP`)
  REFERENCES `sunew_2019`.`codigoprodutoerp` (`CodProduto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- ALTER TABLE ITEM SOLICITACAO
ALTER TABLE `sunew_2019`.`itemsolicitacao` 
ADD COLUMN `CodProdutoERP` INT(11) NULL DEFAULT NULL AFTER `CodSolicitacao`,
ADD INDEX `fk_ItemSolicitacao_CodigoProdutoERP1_idx` (`CodProdutoERP` ASC);
ALTER TABLE `sunew_2019`.`itemsolicitacao` 
ADD CONSTRAINT `fk_ItemSolicitacao_CodigoProdutoERP1`
  FOREIGN KEY (`CodProdutoERP`)
  REFERENCES `sunew_2019`.`codigoprodutoerp` (`CodProduto`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


-- ALTER TABLE REQUISICAO



-- ALTER TABLE SOLICITACAO
ALTER TABLE `sunew_2019`.`solicitacoes` 
ADD COLUMN `CodProjetoDeArea` INT(11) NULL DEFAULT NULL AFTER `CodUsuario`,
ADD INDEX `fk_Solicitacoes_ProjetosDeArea1_idx` (`CodProjetoDeArea` ASC);
ALTER TABLE `sunew_2019`.`solicitacoes` 
ADD CONSTRAINT `fk_Solicitacoes_ProjetosDeArea1`
  FOREIGN KEY (`CodProjetoDeArea`)
  REFERENCES `sunew_2019`.`projetosdearea` (`CodProjetoDeArea`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

