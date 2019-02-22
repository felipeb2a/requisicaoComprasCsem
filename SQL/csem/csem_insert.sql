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
-- UNIDADES
-- ------------------------------------------------------------------------------------------------
insert into csem_2018.Unidade values (1, 'Litro');
insert into csem_2018.Unidade values (2, 'Mililitro');
insert into csem_2018.Unidade values (3, 'Metro');
insert into csem_2018.Unidade values (4, 'Metro quadrado');
insert into csem_2018.Unidade values (5, 'Metro cúbico');
insert into csem_2018.Unidade values (6, 'Centímetro');
insert into csem_2018.Unidade values (7, 'Milimetro');
insert into csem_2018.Unidade values (8, 'Polegada');
insert into csem_2018.Unidade values (9, 'Caixa');
insert into csem_2018.Unidade values (10, 'Pacote');
insert into csem_2018.Unidade values (11, 'Valor Bruto');
insert into csem_2018.Unidade values (12, 'Quilograma');
insert into csem_2018.Unidade values (13, 'Grama');
insert into csem_2018.Unidade values (14, 'Barra');
insert into csem_2018.Unidade values (15, 'Saco');
insert into csem_2018.Unidade values (16, 'Galão');
insert into csem_2018.Unidade values (17, 'Conjunto');
insert into csem_2018.Unidade values (18, 'Kit');


