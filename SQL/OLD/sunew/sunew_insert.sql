-- ------------------------------------------------------------------------------------------------
-- NIVEIS
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.Niveis values (1, 'Administrador');
insert into sunew_2018.Niveis values (2, 'Aprovador Financeiro');
insert into sunew_2018.Niveis values (3, 'Aprovador Tecnico');
insert into sunew_2018.Niveis values (4, 'Compras');
insert into sunew_2018.Niveis values (5, 'Administrativo');
insert into sunew_2018.Niveis values (6, 'Financeiro');
insert into sunew_2018.Niveis values (7, 'RH');
insert into sunew_2018.Niveis values (8, 'Nacional');
insert into sunew_2018.Niveis values (9, 'Internacional');
-- ------------------------------------------------------------------------------------------------
-- SOLICITANTE
-- ------------------------------------------------------------------------------------------------

insert into sunew_2018.Solicitante values (1, 'felipe.ferreira');

-- ------------------------------------------------------------------------------------------------
-- Projetos
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.Projetos values (1, 'Recursos Próprios');
insert into sunew_2018.Projetos values (2, 'BNDES LTCC');
insert into sunew_2018.Projetos values (3, 'BNDES OE');
insert into sunew_2018.Projetos values (4, 'Fiat');
insert into sunew_2018.Projetos values (5, 'Medabil');
insert into sunew_2018.Projetos values (6, 'Votorantim');
insert into sunew_2018.Projetos values (7, 'ABERTO');
insert into sunew_2018.Projetos values (8, 'Plataforma OE');
insert into sunew_2018.Projetos values (9, 'CSEM PE');
insert into sunew_2018.Projetos values (10, 'Plataforma LTCC');
insert into sunew_2018.Projetos values (11, 'Energisa');
insert into sunew_2018.Projetos values (12, 'BOSCH');
insert into sunew_2018.Projetos values (13, 'PHILIPS');
insert into sunew_2018.Projetos values (14, 'CSEM 10 anos');
insert into sunew_2018.Projetos values (15, 'UFPE');
insert into sunew_2018.Projetos values (16, 'AES');

-- ------------------------------------------------------------------------------------------------
-- TIPO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.TipoReq values (1, 'Nacional');
insert into sunew_2018.TipoReq values (2, 'Internacional');
insert into sunew_2018.TipoReq values (3, 'RH');
insert into sunew_2018.TipoReq values (4, 'Frete');
insert into sunew_2018.TipoReq values (5, 'Requisição Aberta');
insert into sunew_2018.TipoReq values (6, 'Frete Internacional');

-- ------------------------------------------------------------------------------------------------
-- Destinacao
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.Destinacao values (1, 'Equipamentos');
insert into sunew_2018.Destinacao values (2, 'Software');
insert into sunew_2018.Destinacao values (3, 'Uso e Consumo');
insert into sunew_2018.Destinacao values (4, 'Outros');

-- ------------------------------------------------------------------------------------------------
-- TIPO FRETE
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.TipoFrete values (1, 'CIF');
insert into sunew_2018.TipoFrete values (2, 'FOB');
insert into sunew_2018.TipoFrete values (3, '-');

-- ------------------------------------------------------------------------------------------------
-- STATUS REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.StatusRequisicao values (1, 'Nova');
insert into sunew_2018.StatusRequisicao values (2, 'Enviada');
insert into sunew_2018.StatusRequisicao values (3, 'Aprovada');
insert into sunew_2018.StatusRequisicao values (4, 'Recusada');
insert into sunew_2018.StatusRequisicao values (5, 'Cancelada');
insert into sunew_2018.StatusRequisicao values (6, 'Finalizada');
insert into sunew_2018.StatusRequisicao values (7, 'Aprovada Tecnico');
insert into sunew_2018.StatusRequisicao values (8, 'Arquivada');
insert into sunew_2018.StatusRequisicao values (9, 'Desarquivada');

-- ------------------------------------------------------------------------------------------------
-- ETAPA REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.EtapaRequisicao values (1, 'Nova');
insert into sunew_2018.EtapaRequisicao values (2, 'Aguardando liberação');
insert into sunew_2018.EtapaRequisicao values (3, 'Aguardando Pagamento');
insert into sunew_2018.EtapaRequisicao values (4, 'Aprovação externa');
insert into sunew_2018.EtapaRequisicao values (5, 'Aprovação interna');
insert into sunew_2018.EtapaRequisicao values (6, 'Devolução');
insert into sunew_2018.EtapaRequisicao values (7, 'Em atraso');
insert into sunew_2018.EtapaRequisicao values (8, 'Em Rota');
insert into sunew_2018.EtapaRequisicao values (9, 'Entregue');
insert into sunew_2018.EtapaRequisicao values (10, 'Mudança de escopo');
insert into sunew_2018.EtapaRequisicao values (11, 'Não Aprovado');
insert into sunew_2018.EtapaRequisicao values (12, 'Orçamentos');
insert into sunew_2018.EtapaRequisicao values (13, 'Troca');

-- ------------------------------------------------------------------------------------------------
-- Moedas
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.Moedas values (1, 'Real', 'BRL');
insert into sunew_2018.Moedas values (2, 'Dólar', 'USD');
insert into sunew_2018.Moedas values (3, 'Euro', 'EUR');
insert into sunew_2018.Moedas values (4, 'Yen', 'JPY');
insert into sunew_2018.Moedas values (5, 'Yuan', 'CNY');


-- ------------------------------------------------------------------------------------------------
-- STATUS ARQUIVAMENTO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.StatusArq values (1, 'N');
insert into sunew_2018.StatusArq values (2, 'S');

-- ------------------------------------------------------------------------------------------------
-- Usuario
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.Usuario values (1,'admin','admin','felipe.ferreira@csembrasil.com.br',1);
insert into sunew_2018.Usuario values (2,'Aprovador Financeiro','csem1234','felipe.ferreira@csembrasil.com.br',2);
insert into sunew_2018.Usuario values (3,'Aprovador Tecnico','csem1234','felipe.ferreira@csembrasil.com.br',3);
insert into sunew_2018.Usuario values (4,'Compras','csem1234','felipe.ferreira@csembrasil.com.br',4);
insert into sunew_2018.Usuario values (5,'Administrativo','csem1234','felipe.ferreirao@csembrasil.com.br',5);
insert into sunew_2018.Usuario values (6,'Financeiro','csem1234','felipe.ferreira@csembrasil.com.br',6);
insert into sunew_2018.Usuario values (7,'RH','csem1234','felipe.ferreira@csembrasil.com.br',7);
insert into sunew_2018.Usuario values (8,'Nacional','csem1234','felipe.ferreira@csembrasil.com.br',4);
insert into sunew_2018.Usuario values (9,'Internacional','csem1234','felipe.ferreira@csembrasil.com.br',4);

-- ------------------------------------------------------------------------------------------------
-- TIPO COBRANÇA
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.TipoCobranca values (1, 'Depósito Bancário');
insert into sunew_2018.TipoCobranca values (2, 'Câmbio');
insert into sunew_2018.TipoCobranca values (3, 'Boleto Bancário');

-- ------------------------------------------------------------------------------------------------
-- TIPO EMBARQUE
-- ------------------------------------------------------------------------------------------------
insert into sunew_2018.TipoEmbarque values (1, 'Via Courier');
insert into sunew_2018.TipoEmbarque values (2, 'Importação Normal');

-- ------------------------------------------------------------------------------------------------
-- 
-- ------------------------------------------------------------------------------------------------