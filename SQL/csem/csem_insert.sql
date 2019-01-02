-- ------------------------------------------------------------------------------------------------
-- NIVEIS
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.Niveis values (1, 'Administrador');
insert into csem_2018.Niveis values (2, 'Aprovador Financeiro');
insert into csem_2018.Niveis values (3, 'Aprovador Tecnico');
insert into csem_2018.Niveis values (4, 'Compras');
insert into csem_2018.Niveis values (5, 'Administrativo');
insert into csem_2018.Niveis values (6, 'Financeiro');
insert into csem_2018.Niveis values (7, 'RH');
-- insert into csem_2018.Niveis values (8, 'Nacional');
-- insert into csem_2018.Niveis values (9, 'Internacional');
-- ------------------------------------------------------------------------------------------------
-- SOLICITANTE
-- ------------------------------------------------------------------------------------------------

 insert into csem_2018.Solicitante values (1, 'felipe.ferreira');

-- ------------------------------------------------------------------------------------------------
-- Projetos
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.Projetos values (1, 'Recursos Próprios');
insert into csem_2018.Projetos values (2, 'Plataforma OE');
insert into csem_2018.Projetos values (3, 'Plataforma LTCC');
insert into csem_2018.Projetos values (4, 'Aberto');
insert into csem_2018.Projetos values (5, 'Energisa');
insert into csem_2018.Projetos values (6, 'BOSCH – Sensores de Mineração');
insert into csem_2018.Projetos values (7, 'AES');
insert into csem_2018.Projetos values (8, 'PHILIPS');
insert into csem_2018.Projetos values (9, 'EMBRAPII – Nexa');

-- ------------------------------------------------------------------------------------------------
-- TIPO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.TipoReq values (1, 'Nacional');
insert into csem_2018.TipoReq values (2, 'Internacional');
insert into csem_2018.TipoReq values (3, 'RH');
insert into csem_2018.TipoReq values (4, 'Frete');
insert into csem_2018.TipoReq values (5, 'Requisição Aberta');
insert into csem_2018.TipoReq values (6, 'Frete Internacional');

-- ------------------------------------------------------------------------------------------------
-- Destinacao
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.Destinacao values (1, 'Equipamentos');
insert into csem_2018.Destinacao values (2, 'Software');
insert into csem_2018.Destinacao values (3, 'Uso e Consumo');
insert into csem_2018.Destinacao values (4, 'Outros');

-- ------------------------------------------------------------------------------------------------
-- TIPO FRETE
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.TipoFrete values (1, 'CIF');
insert into csem_2018.TipoFrete values (2, 'FOB');
insert into csem_2018.TipoFrete values (3, '-');

-- ------------------------------------------------------------------------------------------------
-- STATUS REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.StatusRequisicao values (1, 'Nova');
insert into csem_2018.StatusRequisicao values (2, 'Enviada');
insert into csem_2018.StatusRequisicao values (3, 'Aprovada');
insert into csem_2018.StatusRequisicao values (4, 'Recusada');
insert into csem_2018.StatusRequisicao values (5, 'Cancelada');
insert into csem_2018.StatusRequisicao values (6, 'Finalizada');
insert into csem_2018.StatusRequisicao values (7, 'Aprovada Tecnico');
insert into csem_2018.StatusRequisicao values (8, 'Arquivada');
insert into csem_2018.StatusRequisicao values (9, 'Desarquivada');

-- ------------------------------------------------------------------------------------------------
-- ETAPA REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.EtapaRequisicao values (1, 'Nova');
insert into csem_2018.EtapaRequisicao values (2, 'Aguardando liberação');
insert into csem_2018.EtapaRequisicao values (3, 'Aguardando Pagamento');
insert into csem_2018.EtapaRequisicao values (4, 'Aprovação externa');
insert into csem_2018.EtapaRequisicao values (5, 'Aprovação interna');
insert into csem_2018.EtapaRequisicao values (6, 'Devolução');
insert into csem_2018.EtapaRequisicao values (7, 'Em atraso');
insert into csem_2018.EtapaRequisicao values (8, 'Em Rota');
insert into csem_2018.EtapaRequisicao values (9, 'Entregue');
insert into csem_2018.EtapaRequisicao values (10, 'Mudança de escopo');
insert into csem_2018.EtapaRequisicao values (11, 'Não Aprovado');
insert into csem_2018.EtapaRequisicao values (12, 'Orçamentos');
insert into csem_2018.EtapaRequisicao values (13, 'Troca');

-- ------------------------------------------------------------------------------------------------
-- Moedas
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.Moedas values (1, 'Real', 'BRL');
insert into csem_2018.Moedas values (2, 'Dólar', 'USD');
insert into csem_2018.Moedas values (3, 'Euro', 'EUR');
insert into csem_2018.Moedas values (4, 'Yen', 'JPY');
insert into csem_2018.Moedas values (5, 'Yuan', 'CNY');


-- ------------------------------------------------------------------------------------------------
-- STATUS ARQUIVAMENTO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.StatusArq values (1, 'N');
insert into csem_2018.StatusArq values (2, 'S');

-- ------------------------------------------------------------------------------------------------
-- Usuario
-- ------------------------------------------------------------------------------------------------
-- insert into csem_2018.Usuario values (1,'admin','8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918','felipe.ferreira@csembrasil.com.br',1);
-- insert into csem_2018.Usuario values (2,'Aprovador Financeiro','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',2);
-- insert into csem_2018.Usuario values (3,'Aprovador Tecnico','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',3);
-- insert into csem_2018.Usuario values (4,'Compras','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',4);
-- insert into csem_2018.Usuario values (5,'Administrativo','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreirao@csembrasil.com.br',5);
-- insert into csem_2018.Usuario values (6,'Financeiro','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',6);
-- insert into csem_2018.Usuario values (7,'RH','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',7);
-- insert into csem_2018.Usuario values (8,'Nacional','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',4);
-- insert into csem_2018.Usuario values (9,'Internacional','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',4);

-- DEFINITIVO
insert into csem_2018.Usuario values (1, 'Administrador', '8C6976E5B5410415BDE908BD4DEE15DFB167A9C873FC4BB8A81F6F2AB448A918', NULL, 1);
-- insert into csem_2018.Usuario values (2, 'Ariane Campos', 'BF6AAAAB7C143CA12AE448C69FB72BB4CF1B29154B9086A927A0A91AE334CDF7', 'ariane.campos@csembrasil.com.br', 6);
insert into csem_2018.Usuario values (3, 'Ariane Costa', 'CC1AF790B8E0C7460A7D41F6B57105AC04B4CAC6A1EDF9653BF85B006B77EE80', 'ariane.campos@csembrasil.com.br', 2);
insert into csem_2018.Usuario values (4, 'Francine Santana', '35A9E381B1A27567549B5F8A6F783C167EBF809F1C4D6A9E367240484D8CE281', 'francine.santana@csembrasil.com.br', 6);
insert into csem_2018.Usuario values (5, 'Flávia Albuquerque', 'A665A45920422F9D417E4867EFDC4FB8A04A1F3FFF1FA07E998E86F7F7A27AE3', 'flavia.albuquerque@csembrasil.com.br', 4);
insert into csem_2018.Usuario values (6, 'Rafaela Oliveira', 'DA70DFA4D9F95AC979F921E8E623358236313F334AFCD06CDDF8A5621CF6A1E9', 'rafaela.oliveira@csembrasil.com.br', 4);
insert into csem_2018.Usuario values (7, 'Paula Laguárdia', 'FA88D374B9CF5E059FAD4A2FE406FEAE4C49CBF4803083EC521D3C75EE22557C', 'paula.laguardia@csembrasil.com.br', 7);
insert into csem_2018.Usuario values (8, 'Erika Ferreira', 'DA70DFA4D9F95AC979F921E8E623358236313F334AFCD06CDDF8A5621CF6A1E9', 'erika.ferreira@csembrasil.com.br', 6);
insert into csem_2018.Usuario values (9, 'Lidia Vieira', '524B2D27A1E7FBC3A1614FA661E2DCAD68462352FEEB8BF633DEACCFB8AA84F3', 'lidia.vieira@csembrasil.com.br', 4);
-- TESTE
insert into csem_2018.Usuario values (11,'Nacional','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',4);
-- insert into csem_2018.Usuario values (12,'Internacional','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',4);
insert into csem_2018.Usuario values (13,'Aprovador Financeiro','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',2);
-- insert into csem_2018.Usuario values (14,'Aprovador Tecnico','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',3);
-- insert into csem_2018.Usuario values (15,'RH','A9C32CE86493113095D3FF438CCDFDA4365A4FC3C0154B84B65B3948D6BE03FA','felipe.ferreira@csembrasil.com.br',7);
-- ------------------------------------------------------------------------------------------------
-- TIPO COBRANÇA
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.TipoCobranca values (1, 'Depósito Bancário');
insert into csem_2018.TipoCobranca values (2, 'Câmbio');
insert into csem_2018.TipoCobranca values (3, 'Boleto Bancário');

-- ------------------------------------------------------------------------------------------------
-- TIPO EMBARQUE
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.TipoEmbarque values (1, 'Via Courier');
insert into csem_2018.TipoEmbarque values (2, 'Importação Normal');

-- ------------------------------------------------------------------------------------------------
-- 
-- ------------------------------------------------------------------------------------------------

insert into csem_2018.Requisicoes (`CodRequisicao`, `DataSolicitacao`, `DataCriacao`, `DataAprov`, `DataEntrega`, `DataAprovTecnico`, `DataPrevisaoEntrega`,`Logistica`, `Justificativa`, `Motivo`, `Vinculacao`, `Aprovador`, `AprovadorTecnico`, `TipoAprovador`, `CodDest`, `CodMoeda`, `CodProjeto`, `CodTipoReq`, `CodigoStatus`, `CodFrete`, `CodUsuario`, `CodArq`, `CodSolicitante`, `CodEtapaRequisicao`) VALUES
(1,'2018-01-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(2,'2018-01-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(3,'2018-01-03 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(5,'2018-01-03 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(7,'2018-01-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(8,'2018-01-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(9,'2018-01-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(10,'2018-01-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(11,'2018-01-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(12,'2018-01-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(13,'2018-01-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(14,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(15,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(16,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(17,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(18,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(19,'2018-01-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(20,'2018-01-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(21,'2018-01-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(22,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(23,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(24,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(25,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(26,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(27,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(28,'2018-01-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(29,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(30,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(31,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(32,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(33,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(34,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(35,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(36,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(37,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(38,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(39,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(40,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(41,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(42,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(44,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(45,'2018-01-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(46,'2018-01-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(47,'2018-01-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(48,'2018-01-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(49,'2018-01-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(50,'2018-01-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(51,'2018-01-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(52,'2018-01-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(53,'2018-01-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(54,'2018-01-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(55,'2018-01-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(56,'2018-01-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(57,'2018-01-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(58,'2018-01-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(59,'2018-01-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(60,'2018-01-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(62,'2018-01-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(63,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(64,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(65,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(66,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(67,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(68,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(69,'2018-01-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(70,'2018-01-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(71,'2018-01-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(72,'2018-01-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(73,'2018-01-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(74,'2018-01-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(75,'2018-01-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(76,'2018-01-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(77,'2018-01-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(78,'2018-01-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(79,'2018-01-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(80,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(81,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(82,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(83,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(84,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(85,'2018-01-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(86,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(87,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(88,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(89,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(90,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(91,'2018-01-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(92,'2018-01-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(93,'2018-01-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(94,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(95,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(96,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(97,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(98,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(99,'2018-01-31 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(100,'2018-02-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(101,'2018-02-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(102,'2018-02-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(103,'2018-02-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(104,'2018-02-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(105,'2018-02-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(106,'2018-02-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(107,'2018-02-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(108,'2018-02-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(109,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(110,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(111,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(112,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(113,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(114,'2018-02-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(115,'2018-02-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(116,'2018-02-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(117,'2018-02-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(118,'2018-02-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(119,'2018-02-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(120,'2018-02-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(121,'2018-02-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(122,'2018-02-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(123,'2018-02-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(124,'2018-02-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(125,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(126,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(127,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(128,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(129,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(130,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(131,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(132,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(133,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(134,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(135,'2018-02-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(136,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(137,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(138,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(139,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(140,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(141,'2018-02-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(142,'2018-02-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(143,'2018-02-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(145,'2018-02-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(146,'2018-02-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(147,'2018-02-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(148,'2018-02-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(149,'2018-02-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(150,'2018-02-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(151,'2018-02-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(152,'2018-02-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(153,'2018-02-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(154,'2018-02-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(155,'2018-02-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(156,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(157,'2018-02-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(158,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(159,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(160,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(161,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(162,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(163,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(164,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(165,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(166,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(167,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(168,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(169,'2018-02-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(170,'2018-03-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(171,'2018-03-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(172,'2018-03-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(173,'2018-03-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(174,'2018-03-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(175,'2018-03-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(176,'2018-03-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(177,'2018-03-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(178,'2018-03-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(179,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(180,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(181,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(182,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(183,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(184,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(185,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(186,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(187,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(188,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(189,'2018-03-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(190,'2018-03-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(191,'2018-03-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(192,'2018-03-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(193,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(194,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(195,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(196,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(197,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(198,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(199,'2018-03-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(200,'2018-03-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(201,'2018-03-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(202,'2018-03-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(203,'2018-03-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(204,'2018-03-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(205,'2018-03-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(206,'2018-03-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(207,'2018-03-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(208,'2018-03-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(209,'2018-03-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(210,'2018-03-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(211,'2018-03-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(212,'2018-03-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(213,'2018-03-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(214,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(215,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(216,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(217,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(218,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(219,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(220,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(221,'2018-03-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(222,'2018-03-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(223,'2018-03-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(224,'2018-03-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(225,'2018-03-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(226,'2018-03-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(227,'2018-03-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(228,'2018-03-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(229,'2018-03-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(230,'2018-03-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(231,'2018-03-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(232,'2018-03-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(233,'2018-03-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(235,'2018-03-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(236,'2018-03-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(237,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(238,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(239,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(240,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(241,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(242,'2018-03-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(243,'2018-03-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(244,'2018-03-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(245,'2018-03-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(246,'2018-03-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(247,'2018-03-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(248,'2018-03-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(249,'2018-03-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(250,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(251,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(252,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(253,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(254,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(255,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(256,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(257,'2018-03-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(258,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(259,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(261,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(262,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(263,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(264,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(265,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(266,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(267,'2018-04-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(268,'2018-04-03 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(269,'2018-04-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(270,'2018-04-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(271,'2018-04-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(272,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(273,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(274,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(275,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(276,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(277,'2018-04-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(278,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(279,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(280,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(281,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(282,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(283,'2018-04-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(284,'2018-04-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(285,'2018-04-10 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(286,'2018-04-10 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(287,'2018-04-10 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(288,'2018-04-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(289,'2018-04-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(290,'2018-04-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(291,'2018-04-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(292,'2018-04-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(293,'2018-04-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(294,'2018-04-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(295,'2018-04-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(296,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(297,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(298,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(299,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(300,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(301,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(302,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(303,'2018-04-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(304,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(305,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(306,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(307,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(308,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(309,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(310,'2018-04-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(312,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(313,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(314,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(315,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(316,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(317,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(318,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(319,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(320,'2018-04-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(321,'2018-04-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(322,'2018-04-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(323,'2018-04-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(324,'2018-04-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(325,'2018-04-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(326,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(327,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(328,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(329,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(330,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(331,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(332,'2018-04-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(333,'2018-04-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(334,'2018-04-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(335,'2018-04-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(336,'2018-04-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(337,'2018-04-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(338,'2018-04-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(339,'2018-04-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(340,'2018-04-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(341,'2018-04-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(342,'2018-04-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(343,'2018-05-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(344,'2018-05-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(345,'2018-05-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(346,'2018-05-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(347,'2018-05-02 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(348,'2018-05-03 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(349,'2018-05-03 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(350,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(351,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(352,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(353,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(354,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(355,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(356,'2018-05-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(357,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(358,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(359,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(360,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(361,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(362,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(363,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(364,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(365,'2018-05-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(366,'2018-05-09 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(367,'2018-05-10 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(368,'2018-05-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(369,'2018-05-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(370,'2018-05-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(371,'2018-05-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(372,'2018-05-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(373,'2018-05-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(374,'2018-05-14 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(375,'2018-05-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(376,'2018-05-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(377,'2018-05-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(378,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(380,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(381,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(382,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(383,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(384,'2018-05-16 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(385,'2018-05-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(387,'2018-05-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(388,'2018-05-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(389,'2018-05-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(390,'2018-05-17 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(391,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(392,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(393,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(394,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(395,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(396,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(397,'2018-05-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(398,'2018-05-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(399,'2018-05-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(400,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(401,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(402,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(403,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(404,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(405,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(406,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(407,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(408,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(410,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(411,'2018-05-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(412,'2018-05-23 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(413,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(414,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(415,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(416,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(417,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(418,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(420,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(422,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(423,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(424,'2018-05-24 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(425,'2018-05-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(426,'2018-05-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(428,'2018-05-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(429,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(430,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(431,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(432,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(433,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(434,'2018-05-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(435,'2018-05-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(436,'2018-05-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(437,'2018-05-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(438,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(439,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(440,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(441,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(442,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(443,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(444,'2018-05-30 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(445,'2018-06-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(446,'2018-06-01 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(447,'2018-06-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(448,'2018-06-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(449,'2018-06-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(450,'2018-06-04 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(451,'2018-06-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(452,'2018-06-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(453,'2018-06-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(454,'2018-06-05 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(455,'2018-06-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(456,'2018-06-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(457,'2018-06-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(458,'2018-06-06 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(459,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(460,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(461,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(462,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(463,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(464,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(465,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(466,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(467,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(468,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(469,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(470,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(471,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(472,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(473,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(474,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(475,'2018-06-07 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(476,'2018-06-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(477,'2018-06-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(478,'2018-06-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(479,'2018-06-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(480,'2018-06-08 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(481,'2018-06-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(482,'2018-06-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(483,'2018-06-11 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(484,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(485,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(486,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(487,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(488,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(489,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(490,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(491,'2018-06-12 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(493,'2018-06-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(494,'2018-06-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(495,'2018-06-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(496,'2018-06-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(497,'2018-06-13 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(498,'2018-06-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(499,'2018-06-15 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(500,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(501,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(502,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(503,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(504,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(505,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(506,'2018-06-18 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(507,'2018-06-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(508,'2018-06-19 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(509,'2018-06-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(510,'2018-06-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(511,'2018-06-20 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(512,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(513,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(514,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(515,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(516,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(517,'2018-06-21 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(518,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(519,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(520,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(521,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(522,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(523,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(524,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(525,'2018-06-22 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(526,'2018-06-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(527,'2018-06-25 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(528,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(529,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(530,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(531,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(532,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(533,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(534,'2018-06-26 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(535,'2018-06-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(536,'2018-06-27 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(537,'2018-06-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(538,'2018-06-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(539,'2018-06-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(540,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(541,'2018-06-28 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(542,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(543,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(544,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(545,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(546,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(547,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(548,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(549,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1),
(550,'2018-06-29 0:00:00', NULL, NULL, NULL, NULL, NULL, 0, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 1, 1);