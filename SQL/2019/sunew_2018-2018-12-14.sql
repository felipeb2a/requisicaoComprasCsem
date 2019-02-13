-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: localhost    Database: sunew_2018
-- ------------------------------------------------------
-- Server version	5.7.24-0ubuntu0.16.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Destinacao`
--

DROP TABLE IF EXISTS `Destinacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Destinacao` (
  `CodDest` int(11) NOT NULL AUTO_INCREMENT,
  `Destinacao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodDest`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Destinacao`
--

LOCK TABLES `Destinacao` WRITE;
/*!40000 ALTER TABLE `Destinacao` DISABLE KEYS */;
INSERT INTO `Destinacao` VALUES (1,'Equipamentos'),(2,'Software'),(3,'Uso e Consumo'),(4,'Outros');
/*!40000 ALTER TABLE `Destinacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EtapaRequisicao`
--

DROP TABLE IF EXISTS `EtapaRequisicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EtapaRequisicao` (
  `CodEtapaRequisicao` int(11) NOT NULL,
  `EtapaRequisicao` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CodEtapaRequisicao`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EtapaRequisicao`
--

LOCK TABLES `EtapaRequisicao` WRITE;
/*!40000 ALTER TABLE `EtapaRequisicao` DISABLE KEYS */;
INSERT INTO `EtapaRequisicao` VALUES (1,'Nova'),(2,'Aguardando liberação'),(3,'Aguardando Pagamento'),(4,'Aprovação externa'),(5,'Aprovação interna'),(6,'Devolução'),(7,'Em atraso'),(8,'Em Rota'),(9,'Entregue'),(10,'Mudança de escopo'),(11,'Não Aprovado'),(12,'Orçamentos'),(13,'Troca'),(14,'Aguardando Nota Fiscal'),(15,'Aguardando Boleto'),(16,'Entrega Parcial');
/*!40000 ALTER TABLE `EtapaRequisicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Fornecedores`
--

DROP TABLE IF EXISTS `Fornecedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Fornecedores` (
  `CodFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `NomeFornecedor` varchar(255) DEFAULT NULL,
  `Telefone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Contato` varchar(255) DEFAULT NULL,
  `InformacoesAdicionais` mediumtext,
  `ValorInicial` double DEFAULT NULL,
  `ValorFinal` double DEFAULT NULL,
  `Escolha` tinyint(1) DEFAULT NULL,
  `CNPJ` varchar(255) DEFAULT NULL,
  `CPF` varchar(255) DEFAULT NULL,
  `Banco` varchar(255) DEFAULT NULL,
  `Conta` varchar(255) DEFAULT NULL,
  `Agencia` varchar(255) DEFAULT NULL,
  `TempoProducao` int(11) DEFAULT NULL,
  `Logistica` int(11) DEFAULT NULL,
  `CodRequisicao` int(11) NOT NULL,
  PRIMARY KEY (`CodFornecedor`),
  KEY `fk_Fornecedores_Requisicoes1_idx` (`CodRequisicao`),
  CONSTRAINT `fk_Fornecedores_Requisicoes1` FOREIGN KEY (`CodRequisicao`) REFERENCES `Requisicoes` (`CodRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1226 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Fornecedores`
--

LOCK TABLES `Fornecedores` WRITE;
/*!40000 ALTER TABLE `Fornecedores` DISABLE KEYS */;
/*!40000 ALTER TABLE `Fornecedores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FornecedoresSolicitacao`
--

DROP TABLE IF EXISTS `FornecedoresSolicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FornecedoresSolicitacao` (
  `CodFornecedor` int(11) NOT NULL AUTO_INCREMENT,
  `NomeFornecedor` varchar(255) DEFAULT NULL,
  `Telefone` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Contato` varchar(255) DEFAULT NULL,
  `InformacoesAdicionais` mediumtext,
  `ValorTotal` double DEFAULT NULL,
  `CodSolicitacao` int(11) NOT NULL,
  PRIMARY KEY (`CodFornecedor`),
  KEY `fk_Fornecedores_copy1_Solicitacoes1_idx` (`CodSolicitacao`),
  CONSTRAINT `fk_Fornecedores_copy1_Solicitacoes1` FOREIGN KEY (`CodSolicitacao`) REFERENCES `Solicitacoes` (`CodSolicitacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=271 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FornecedoresSolicitacao`
--

LOCK TABLES `FornecedoresSolicitacao` WRITE;
/*!40000 ALTER TABLE `FornecedoresSolicitacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `FornecedoresSolicitacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Item`
--

DROP TABLE IF EXISTS `Item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Item` (
  `CodItem` int(11) NOT NULL,
  `NomeItem` varchar(2000) DEFAULT NULL,
  `Unidade` varchar(255) DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  `DescricaoTecnica` mediumtext,
  `InformacoesAdicionais` mediumtext,
  `ValorUnitario` double DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  `CodRequisicao` int(11) NOT NULL,
  PRIMARY KEY (`CodItem`),
  KEY `fk_Item_Requisicoes1_idx` (`CodRequisicao`),
  CONSTRAINT `fk_Item_Requisicoes1` FOREIGN KEY (`CodRequisicao`) REFERENCES `Requisicoes` (`CodRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Item`
--

LOCK TABLES `Item` WRITE;
/*!40000 ALTER TABLE `Item` DISABLE KEYS */;
/*!40000 ALTER TABLE `Item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ItemSolicitacao`
--

DROP TABLE IF EXISTS `ItemSolicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ItemSolicitacao` (
  `CodItemSolicitacao` int(11) NOT NULL,
  `NomeItem` varchar(255) DEFAULT NULL,
  `Unidade` varchar(255) DEFAULT NULL,
  `Quantidade` int(11) DEFAULT NULL,
  `DescricaoTecnica` mediumtext,
  `InformacoesAdicionais` mediumtext,
  `ValorUnitario` double DEFAULT NULL,
  `ValorTotal` double DEFAULT NULL,
  `CodSolicitacao` int(11) NOT NULL,
  PRIMARY KEY (`CodItemSolicitacao`),
  KEY `fk_ItemSolicitacao_Solicitacoes1_idx` (`CodSolicitacao`),
  CONSTRAINT `fk_ItemSolicitacao_Solicitacoes1` FOREIGN KEY (`CodSolicitacao`) REFERENCES `Solicitacoes` (`CodSolicitacao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ItemSolicitacao`
--

LOCK TABLES `ItemSolicitacao` WRITE;
/*!40000 ALTER TABLE `ItemSolicitacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `ItemSolicitacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Moedas`
--

DROP TABLE IF EXISTS `Moedas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Moedas` (
  `CodMoeda` int(11) NOT NULL AUTO_INCREMENT,
  `Moeda` varchar(255) DEFAULT NULL,
  `Abrev` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodMoeda`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Moedas`
--

LOCK TABLES `Moedas` WRITE;
/*!40000 ALTER TABLE `Moedas` DISABLE KEYS */;
INSERT INTO `Moedas` VALUES (1,'Real','BRL'),(2,'Dólar','USD'),(3,'Euro','EUR'),(4,'Yen','JPY'),(5,'Yuan','CNY'),(6,'Libra','GBP'),(7,'Franco Suíço','CHF'),(8,'Dólar Australiano','AUD'),(9,'Dólar Canadence','CAD'),(10,'Dirrã dos Emirados Árabes Unidos','AED');
/*!40000 ALTER TABLE `Moedas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Niveis`
--

DROP TABLE IF EXISTS `Niveis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Niveis` (
  `CodNivel` int(11) NOT NULL AUTO_INCREMENT,
  `NomeNivel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodNivel`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Niveis`
--

LOCK TABLES `Niveis` WRITE;
/*!40000 ALTER TABLE `Niveis` DISABLE KEYS */;
INSERT INTO `Niveis` VALUES (1,'Administrador'),(2,'Aprovador Financeiro'),(3,'Aprovador Tecnico'),(4,'Compras'),(5,'Administrativo'),(6,'Financeiro'),(7,'RH');
/*!40000 ALTER TABLE `Niveis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrdemPagto`
--

DROP TABLE IF EXISTS `OrdemPagto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `OrdemPagto` (
  `CodOrdemPagamento` int(11) NOT NULL,
  `DataOP` datetime DEFAULT NULL,
  `DataVencimento` datetime DEFAULT NULL,
  `Parcela` int(11) DEFAULT NULL,
  `ValorPagar` double DEFAULT NULL,
  `NCM` varchar(10) DEFAULT NULL,
  `PrevisaoEmbarque` datetime DEFAULT NULL,
  `Comentarios` varchar(255) DEFAULT NULL,
  `CodTipoCobranca` int(11) NOT NULL,
  `CodRequisicao` int(11) NOT NULL,
  PRIMARY KEY (`CodOrdemPagamento`),
  KEY `fk_OrdemPagto_TipoCobranca1_idx` (`CodTipoCobranca`),
  KEY `fk_OrdemPagto_Requisicoes1_idx` (`CodRequisicao`),
  CONSTRAINT `fk_OrdemPagto_Requisicoes1` FOREIGN KEY (`CodRequisicao`) REFERENCES `Requisicoes` (`CodRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_OrdemPagto_TipoCobranca1` FOREIGN KEY (`CodTipoCobranca`) REFERENCES `TipoCobranca` (`CodTipoCobranca`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrdemPagto`
--

LOCK TABLES `OrdemPagto` WRITE;
/*!40000 ALTER TABLE `OrdemPagto` DISABLE KEYS */;
/*!40000 ALTER TABLE `OrdemPagto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Projetos`
--

DROP TABLE IF EXISTS `Projetos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Projetos` (
  `CodProjeto` int(11) NOT NULL AUTO_INCREMENT,
  `NomeProjeto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodProjeto`),
  UNIQUE KEY `NomeProjeto_UNIQUE` (`NomeProjeto`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Projetos`
--

LOCK TABLES `Projetos` WRITE;
/*!40000 ALTER TABLE `Projetos` DISABLE KEYS */;
INSERT INTO `Projetos` VALUES (35,'ABDI'),(9,'Adm/Financeiro Geral'),(19,'AES'),(27,'Alsol'),(4,'Auditoria'),(101,'Big Five - Dubai 2018'),(26,'Cambridge 2017'),(14,'Cambridge 2018'),(30,'Caoa'),(39,'Cogumelo Rio Sul'),(10,'Compras'),(3,'Contabilidade'),(32,'Csem (Energisa)'),(102,'Daily – IVECO'),(52,'Desenvolvimento de Produtos'),(34,'Doc BBC'),(12,'Estacao Tubo Curitiba - JCHEBLY'),(15,'Fundação CERTI'),(42,'Fundep Votorantim'),(23,'Greenbuilding 2017'),(16,'IKEA'),(44,'Impressao'),(5,'Juridico'),(45,'Laminacao'),(20,'Lopec 2017'),(7,'Manutencao'),(49,'Marketing'),(33,'Metalco (MRV)'),(40,'Metalco Diversos'),(37,'Metalco ENEL'),(31,'MMCite'),(38,'Multilight Gauthama'),(13,'Natura'),(29,'Orlando'),(8,'Outros (Adm)'),(51,'Outros (Comercial)'),(53,'Outros (Desenv. Prod.)'),(47,'Outros (Produção)'),(25,'Pilkington'),(11,'PMO'),(36,'Polaris'),(43,'Producao Geral'),(1,'Projetos Diretoria (Presidencia)'),(50,'Prospecção de Vendas'),(46,'Qualidade'),(22,'Raízen'),(17,'Randon'),(2,'Recursos Humanos'),(28,'Rock in Rio 2017'),(99,'Siemens'),(76,'SK 05 - Kroenert Infrared System'),(77,'SK 08 - Kroenert Exhaust System'),(78,'SK 09 - Jiga de Testes'),(66,'SK 10 - Portico Kroenert'),(67,'SK 11 - Portico Laminadora'),(68,'SK 12 - Telemetria'),(69,'SK 13 - Sistema Limpeza Telas Ultrasom'),(70,'SK 14 - Mesa corte web Kroenert'),(71,'SK 16 - Visao Computacional Coating'),(72,'SK 17A - Revisoes Simulador Solar'),(73,'SK 18 - Mesa Inspecao'),(54,'SK 19 - Laminadora Sunew'),(64,'SK 20 - Rolos Expostos/Carrinho de Rolos'),(65,'SK 21 - Busbar'),(55,'SK 22 - Die 110mm'),(58,'SK 24 - Bar Code'),(57,'SK 25 - Plataforma E5'),(74,'SN 02 - Upgrade Mass Suply'),(75,'SN 03 - Instalação de medidor de vazão N'),(56,'SN 20 - Sistema de cameras para impressã'),(59,'SN 26 - Troca da posição dos dutos de ág'),(60,'SN 33 - Remoção de ferrugens em partes d'),(61,'SN 34 - Melhoria no sistema de ajuste da'),(62,'SN 36 - Alteração da posição das manguei'),(63,'SN 40 - Melhorias no sistema de cabeamen'),(98,'SN 45 - Alinhamento laminadora'),(79,'SN46 - Melhorias Plotter'),(80,'SN47 - Mesa emenda E2'),(81,'SN48 - Suporte PC estações'),(82,'SN49 - Tela backup'),(83,'SN50 - Suporte mass supply'),(84,'SN51 - Identificador de falhas'),(85,'SN52 - Identificador de emenda'),(86,'SN53 - shims/meniscus reposição'),(87,'SN54 - Modificação caminho turn bar'),(88,'SN55 - Tratamento corona/plasma laminadora'),(89,'SN56 - Substituição nip de borracha'),(90,'SN57 - Spool unwinder busbar'),(91,'SN58 - Direcionar N2 laser laminadora'),(92,'SN59 - Carrinho barcode'),(93,'SN60 - Base entre nips laminadora'),(94,'SN61 - Contrato manutenção Kroenert'),(95,'SN62 - Alimentação de prata'),(96,'SN63 - Suporte rolos impressão'),(97,'SN64 - Simulador solar 2,5m'),(41,'Solarvolt Itau Academia'),(100,'Tevisa'),(6,'Ti'),(18,'Totvs'),(24,'Vale do Pinhão'),(48,'Vendas'),(21,'Warka Water');
/*!40000 ALTER TABLE `Projetos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReqInterOutros`
--

DROP TABLE IF EXISTS `ReqInterOutros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReqInterOutros` (
  `CodReqInterOutros` int(11) NOT NULL,
  `Frete` double DEFAULT NULL,
  `TaxaSiscomex` double DEFAULT NULL,
  `Impostos` double DEFAULT NULL,
  `CodTipoEmbarque` int(11) NOT NULL,
  `CodRequisicao` int(11) NOT NULL,
  PRIMARY KEY (`CodReqInterOutros`),
  KEY `fk_ReqInterOutros_TipoEmbarque1_idx` (`CodTipoEmbarque`),
  KEY `fk_ReqInterOutros_Requisicoes1_idx` (`CodRequisicao`),
  CONSTRAINT `fk_ReqInterOutros_Requisicoes1` FOREIGN KEY (`CodRequisicao`) REFERENCES `Requisicoes` (`CodRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReqInterOutros_TipoEmbarque1` FOREIGN KEY (`CodTipoEmbarque`) REFERENCES `TipoEmbarque` (`CodTipoEmbarque`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReqInterOutros`
--

LOCK TABLES `ReqInterOutros` WRITE;
/*!40000 ALTER TABLE `ReqInterOutros` DISABLE KEYS */;
/*!40000 ALTER TABLE `ReqInterOutros` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReqPO`
--

DROP TABLE IF EXISTS `ReqPO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReqPO` (
  `CodRequisicao` int(11) NOT NULL,
  `NCM` varchar(255) DEFAULT NULL,
  `PrevisaoEmbarque` datetime DEFAULT NULL,
  `CodTipoEmbarque` int(11) NOT NULL,
  `Endereço` mediumtext,
  `Telefone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodRequisicao`),
  KEY `fk_ReqPO_TipoEmbarque1_idx` (`CodTipoEmbarque`),
  CONSTRAINT `fk_ReqPO_Requisicoes1` FOREIGN KEY (`CodRequisicao`) REFERENCES `Requisicoes` (`CodRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ReqPO_TipoEmbarque1` FOREIGN KEY (`CodTipoEmbarque`) REFERENCES `TipoEmbarque` (`CodTipoEmbarque`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReqPO`
--

LOCK TABLES `ReqPO` WRITE;
/*!40000 ALTER TABLE `ReqPO` DISABLE KEYS */;
/*!40000 ALTER TABLE `ReqPO` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Requisicoes`
--

DROP TABLE IF EXISTS `Requisicoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Requisicoes` (
  `CodRequisicao` int(11) NOT NULL AUTO_INCREMENT,
  `DataSolicitacao` datetime DEFAULT NULL,
  `DataCriacao` datetime DEFAULT NULL,
  `DataAprov` datetime DEFAULT NULL,
  `DataEntrega` datetime DEFAULT NULL,
  `DataAprovTecnico` datetime DEFAULT NULL,
  `DataPrevisaoEntrega` datetime DEFAULT NULL,
  `Justificativa` mediumtext,
  `Motivo` mediumtext,
  `Vinculacao` int(11) DEFAULT NULL,
  `Aprovador` varchar(45) DEFAULT NULL,
  `AprovadorTecnico` varchar(45) DEFAULT NULL,
  `TipoAprovador` varchar(45) DEFAULT NULL,
  `CodDest` int(11) NOT NULL,
  `CodMoeda` int(11) NOT NULL,
  `CodProjeto` int(11) NOT NULL,
  `CodTipoReq` int(11) NOT NULL,
  `CodigoStatus` int(11) NOT NULL,
  `CodFrete` int(11) NOT NULL,
  `CodUsuario` int(11) NOT NULL,
  `CodArq` int(11) NOT NULL,
  `CodSolicitante` int(11) NOT NULL,
  `CodEtapaRequisicao` int(11) NOT NULL,
  PRIMARY KEY (`CodRequisicao`),
  KEY `fk_Requisicoes_Destinacao1_idx` (`CodDest`),
  KEY `fk_Requisicoes_Moedas1_idx` (`CodMoeda`),
  KEY `fk_Requisicoes_Projetos1_idx` (`CodProjeto`),
  KEY `fk_Requisicoes_TipoReq1_idx` (`CodTipoReq`),
  KEY `fk_Requisicoes_StatusRequisicao1_idx` (`CodigoStatus`),
  KEY `fk_Requisicoes_TipoFrete1_idx` (`CodFrete`),
  KEY `fk_Requisicoes_Usuario1_idx` (`CodUsuario`),
  KEY `fk_Requisicoes_StatusArq1_idx` (`CodArq`),
  KEY `fk_Requisicoes_Solicitante1_idx` (`CodSolicitante`),
  KEY `fk_Requisicoes_EtapaRequisicao1_idx` (`CodEtapaRequisicao`),
  CONSTRAINT `fk_Requisicoes_Destinacao1` FOREIGN KEY (`CodDest`) REFERENCES `Destinacao` (`CodDest`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_EtapaRequisicao1` FOREIGN KEY (`CodEtapaRequisicao`) REFERENCES `EtapaRequisicao` (`CodEtapaRequisicao`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Moedas1` FOREIGN KEY (`CodMoeda`) REFERENCES `Moedas` (`CodMoeda`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Projetos1` FOREIGN KEY (`CodProjeto`) REFERENCES `Projetos` (`CodProjeto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Solicitante1` FOREIGN KEY (`CodSolicitante`) REFERENCES `Solicitante` (`CodSolicitante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusArq1` FOREIGN KEY (`CodArq`) REFERENCES `StatusArq` (`CodArq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusRequisicao1` FOREIGN KEY (`CodigoStatus`) REFERENCES `StatusRequisicao` (`CodStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoFrete1` FOREIGN KEY (`CodFrete`) REFERENCES `TipoFrete` (`CodFrete`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoReq1` FOREIGN KEY (`CodTipoReq`) REFERENCES `TipoReq` (`CodTipoReq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Usuario1` FOREIGN KEY (`CodUsuario`) REFERENCES `Usuario` (`CodUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=899 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Requisicoes`
--

LOCK TABLES `Requisicoes` WRITE;
/*!40000 ALTER TABLE `Requisicoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `Requisicoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Solicitacoes`
--

DROP TABLE IF EXISTS `Solicitacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Solicitacoes` (
  `CodSolicitacao` int(11) NOT NULL AUTO_INCREMENT,
  `DataSolicitacao` datetime DEFAULT NULL,
  `Justificativa` mediumtext,
  `EmailSolicitante` varchar(255) DEFAULT NULL,
  `Motivo` mediumtext,
  `CodProjeto` int(11) NOT NULL,
  `CodTipoReq` int(11) NOT NULL,
  `CodigoStatus` int(11) NOT NULL,
  `CodSolicitante` int(11) NOT NULL,
  `CodArq` int(11) NOT NULL,
  `CodUsuario` int(11) NOT NULL,
  PRIMARY KEY (`CodSolicitacao`),
  KEY `fk_Requisicoes_Projetos1_idx` (`CodProjeto`),
  KEY `fk_Requisicoes_TipoReq1_idx` (`CodTipoReq`),
  KEY `fk_Requisicoes_StatusRequisicao1_idx` (`CodigoStatus`),
  KEY `fk_Requisicoes_Solicitante1_idx` (`CodSolicitante`),
  KEY `fk_Solicitacoes_StatusArq1_idx` (`CodArq`),
  KEY `fk_Solicitacoes_Usuario1_idx` (`CodUsuario`),
  CONSTRAINT `fk_Requisicoes_Projetos10` FOREIGN KEY (`CodProjeto`) REFERENCES `Projetos` (`CodProjeto`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_Solicitante10` FOREIGN KEY (`CodSolicitante`) REFERENCES `Solicitante` (`CodSolicitante`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_StatusRequisicao10` FOREIGN KEY (`CodigoStatus`) REFERENCES `StatusRequisicao` (`CodStatus`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Requisicoes_TipoReq10` FOREIGN KEY (`CodTipoReq`) REFERENCES `TipoReq` (`CodTipoReq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Solicitacoes_StatusArq1` FOREIGN KEY (`CodArq`) REFERENCES `StatusArq` (`CodArq`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Solicitacoes_Usuario1` FOREIGN KEY (`CodUsuario`) REFERENCES `Usuario` (`CodUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=221 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Solicitacoes`
--

LOCK TABLES `Solicitacoes` WRITE;
/*!40000 ALTER TABLE `Solicitacoes` DISABLE KEYS */;
/*!40000 ALTER TABLE `Solicitacoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Solicitante`
--

DROP TABLE IF EXISTS `Solicitante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Solicitante` (
  `CodSolicitante` int(11) NOT NULL,
  `NomeSolicitante` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`CodSolicitante`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Solicitante`
--

LOCK TABLES `Solicitante` WRITE;
/*!40000 ALTER TABLE `Solicitante` DISABLE KEYS */;
/*!40000 ALTER TABLE `Solicitante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StatusArq`
--

DROP TABLE IF EXISTS `StatusArq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StatusArq` (
  `CodArq` int(11) NOT NULL AUTO_INCREMENT,
  `Arqvd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodArq`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StatusArq`
--

LOCK TABLES `StatusArq` WRITE;
/*!40000 ALTER TABLE `StatusArq` DISABLE KEYS */;
INSERT INTO `StatusArq` VALUES (1,'N'),(2,'S');
/*!40000 ALTER TABLE `StatusArq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `StatusRequisicao`
--

DROP TABLE IF EXISTS `StatusRequisicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `StatusRequisicao` (
  `CodStatus` int(11) NOT NULL,
  `Status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodStatus`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `StatusRequisicao`
--

LOCK TABLES `StatusRequisicao` WRITE;
/*!40000 ALTER TABLE `StatusRequisicao` DISABLE KEYS */;
INSERT INTO `StatusRequisicao` VALUES (1,'Nova'),(2,'Enviada'),(3,'Aprovada'),(4,'Recusada'),(5,'Cancelada'),(6,'Finalizada'),(7,'Aprovada Tecnico'),(8,'Arquivada'),(9,'Desarquivada');
/*!40000 ALTER TABLE `StatusRequisicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoCobranca`
--

DROP TABLE IF EXISTS `TipoCobranca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoCobranca` (
  `CodTipoCobranca` int(11) NOT NULL AUTO_INCREMENT,
  `TipoCobranca` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodTipoCobranca`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoCobranca`
--

LOCK TABLES `TipoCobranca` WRITE;
/*!40000 ALTER TABLE `TipoCobranca` DISABLE KEYS */;
INSERT INTO `TipoCobranca` VALUES (1,'Depósito Bancário'),(2,'Câmbio'),(3,'Boleto Bancário');
/*!40000 ALTER TABLE `TipoCobranca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoEmbarque`
--

DROP TABLE IF EXISTS `TipoEmbarque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoEmbarque` (
  `CodTipoEmbarque` int(11) NOT NULL,
  `NomeEmbarque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodTipoEmbarque`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoEmbarque`
--

LOCK TABLES `TipoEmbarque` WRITE;
/*!40000 ALTER TABLE `TipoEmbarque` DISABLE KEYS */;
INSERT INTO `TipoEmbarque` VALUES (1,'Via Courier'),(2,'Importação Normal');
/*!40000 ALTER TABLE `TipoEmbarque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoFrete`
--

DROP TABLE IF EXISTS `TipoFrete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoFrete` (
  `CodFrete` int(11) NOT NULL AUTO_INCREMENT,
  `TipoFrete` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodFrete`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoFrete`
--

LOCK TABLES `TipoFrete` WRITE;
/*!40000 ALTER TABLE `TipoFrete` DISABLE KEYS */;
INSERT INTO `TipoFrete` VALUES (1,'CIF'),(2,'FOB'),(3,'-');
/*!40000 ALTER TABLE `TipoFrete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `TipoReq`
--

DROP TABLE IF EXISTS `TipoReq`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `TipoReq` (
  `CodTipoReq` int(11) NOT NULL AUTO_INCREMENT,
  `TipoRequisicao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`CodTipoReq`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `TipoReq`
--

LOCK TABLES `TipoReq` WRITE;
/*!40000 ALTER TABLE `TipoReq` DISABLE KEYS */;
INSERT INTO `TipoReq` VALUES (1,'Nacional'),(2,'Internacional'),(3,'RH'),(4,'Frete'),(5,'Requisição Aberta'),(6,'Frete Internacional');
/*!40000 ALTER TABLE `TipoReq` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Usuario`
--

DROP TABLE IF EXISTS `Usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Usuario` (
  `CodUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(255) NOT NULL,
  `Senha` varchar(255) DEFAULT NULL,
  `EmailFunc` varchar(255) DEFAULT NULL,
  `CodNivel` int(11) NOT NULL,
  PRIMARY KEY (`CodUsuario`),
  UNIQUE KEY `Nome_UNIQUE` (`Nome`),
  KEY `fk_Usuario_Niveis1_idx` (`CodNivel`),
  CONSTRAINT `fk_Usuario_Niveis1` FOREIGN KEY (`CodNivel`) REFERENCES `Niveis` (`CodNivel`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Usuario`
--

LOCK TABLES `Usuario` WRITE;
/*!40000 ALTER TABLE `Usuario` DISABLE KEYS */;
INSERT INTO `Usuario` VALUES (1,'Administrador','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918',NULL,1),(2,'Alvaro.Leite','4321A844EE760EE6CA5575A7C16C34D2A40EC1B3D76003681B8A3848DC1F4CC5','alvaro.leite@sunew.com.br',2),(3,'Ariane Costa','CC1AF790B8E0C7460A7D41F6B57105AC04B4CAC6A1EDF9653BF85B006B77EE80','ariane.campos@csembrasil.com.br',2),(5,'Francine Santana','35A9E381B1A27567549B5F8A6F783C167EBF809F1C4D6A9E367240484D8CE281','francine.santana@csembrasil.com.br',6),(6,'Flávia Albuquerque','A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3','flavia.albuquerque@sunew.com.br',4),(7,'Licença Maternidade Rafaela Oliveira','DA70DFA4D9F95AC979F921E8E623358236313F334AFCD06CDDF8A5621CF6A1E9','rafaela.oliveira@csembrasil.com.br',4),(8,'Paula Laguárdia','FA88D374B9CF5E059FAD4A2FE406FEAE4C49CBF4803083EC521D3C75EE22557C','paula.laguardia@csembrasil.com.br',7),(9,'Erika Ferreira','DA70DFA4D9F95AC979F921E8E623358236313F334AFCD06CDDF8A5621CF6A1E9','erika.ferreira@csembrasil.com.br',6),(10,'Guilherme Dias','D829857EB1366E70BE857A69886D1555AF0D32681BEAB068AFB93492C2E2B843','guilherme.dias@sunew.com.br',6),(11,'Lidia Vieira','524B2D27A1E7FBC3A1614FA661E2DCAD68462352FEEB8BF633DEACCFB8AA84F3','lidia.vieira@csembrasil.com.br',4),(12,'Melisa Antunes','92A6A32F99DEF322D70EA1167A99C6859AB4E8BBC593B997EC5994D244A82475','melisa.antunes@sunew.com.br',5),(13,'Isabela Dias','651DD230D8037258B26577B184B3C0CD783C5F270E4727CFA92D056AEBA1E547','isabela.dias@sunew.com.br',4),(14,'Peter Levermore','651DD230D8037258B26577B184B3C0CD783C5F270E4727CFA92D056AEBA1E547','peter.levermore@sunew.com.br',3),(15,'Vinicius Zanchin','9A7A4D68CF7730E56A4C75D635274323C6FF6236209C91E2513D1CC8722320D3','vinicius.zanchin@sunew.com.br',3),(16,'Guilherme Lemos','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','guilherme.lemos@csembrasil.com.br',4);
/*!40000 ALTER TABLE `Usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-14  0:00:02
