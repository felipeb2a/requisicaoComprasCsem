-- ------------------------------------------------------------------------------------------------
-- NIVEIS
-- ------------------------------------------------------------------------------------------------
insert into compras.niveis values (1, 'Administrador');
insert into compras.niveis values (2, 'Aprovador Administrativo');
insert into compras.niveis values (3, 'Aprovador Tecnico');
insert into compras.niveis values (4, 'Compras');
insert into compras.niveis values (5, 'Administrativo');
insert into compras.niveis values (6, 'Financeiro');
insert into compras.niveis values (7, 'RH');
insert into compras.niveis values (8, 'Nacional');
insert into compras.niveis values (9, 'Internacional');

-- ------------------------------------------------------------------------------------------------
-- SOLICITANTE
-- ------------------------------------------------------------------------------------------------

insert into compras.solicitante values (1, 'felipe.ferreira');

-- ------------------------------------------------------------------------------------------------
-- PROJETOS
-- ------------------------------------------------------------------------------------------------
insert into compras.projetos values (1, 'Recursos Próprios');
insert into compras.projetos values (2, 'BNDES LTCC');
insert into compras.projetos values (3, 'BNDES OE');
insert into compras.projetos values (4, 'Fiat');
insert into compras.projetos values (5, 'Medabil');
insert into compras.projetos values (6, 'Votorantim');
insert into compras.projetos values (7, 'ABERTO');
insert into compras.projetos values (8, 'Plataforma OE');
insert into compras.projetos values (9, 'CSEM PE');
insert into compras.projetos values (10, 'Plataforma LTCC');
insert into compras.projetos values (11, 'Energisa');
insert into compras.projetos values (12, 'BOSCH');
insert into compras.projetos values (13, 'PHILIPS');
insert into compras.projetos values (14, 'CSEM 10 anos');
insert into compras.projetos values (15, 'UFPE');
insert into compras.projetos values (16, 'AES');

-- ------------------------------------------------------------------------------------------------
-- TIPO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into compras.tiporeq values (1, 'Nacional');
insert into compras.tiporeq values (2, 'Inernacional');
insert into compras.tiporeq values (3, 'RH');
insert into compras.tiporeq values (4, 'Frete');
insert into compras.tiporeq values (5, 'Requisição Aberta');
insert into compras.tiporeq values (6, 'Frete Internacional');

-- ------------------------------------------------------------------------------------------------
-- DESTINACAO
-- ------------------------------------------------------------------------------------------------
insert into compras.destinacao values (1, 'Equipamentos');
insert into compras.destinacao values (2, 'Software');
insert into compras.destinacao values (3, 'Uso e Consumo');
insert into compras.destinacao values (4, 'Outros');

-- ------------------------------------------------------------------------------------------------
-- TIPO FRETE
-- ------------------------------------------------------------------------------------------------
insert into compras.tipofrete values (1, 'CIF');
insert into compras.tipofrete values (2, 'FOB');
insert into compras.tipofrete values (3, '-');

-- ------------------------------------------------------------------------------------------------
-- STATUS REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into compras.statusrequisicao values (1, 'Nova');
insert into compras.statusrequisicao values (2, 'Enviada');
insert into compras.statusrequisicao values (3, 'Aprovada');
insert into compras.statusrequisicao values (4, 'Recusada');
insert into compras.statusrequisicao values (5, 'Cancelada');

-- ------------------------------------------------------------------------------------------------
-- MOEDAS
-- ------------------------------------------------------------------------------------------------
insert into compras.moedas values (1, 'Real', 'BRL');
insert into compras.moedas values (2, 'Dólar', 'USD');
insert into compras.moedas values (3, 'Euro', 'EUR');
insert into compras.moedas values (4, 'Yen', 'JPY');
insert into compras.moedas values (5, 'Yuan', 'CNY');


-- ------------------------------------------------------------------------------------------------
-- STATUS ARQUIVAMENTO REQUISICAO
-- ------------------------------------------------------------------------------------------------
insert into compras.statusarq values (1, 'N');
insert into compras.statusarq values (2, 'S');

-- ------------------------------------------------------------------------------------------------
-- USUARIO
-- ------------------------------------------------------------------------------------------------
insert into compras.usuario values (1,'admin','admin','admin@csembrasil.com.br',1);
insert into compras.usuario values (2,'Aprovador Administrativo','csem1234','aprovador.administrativo@csembrasil.com.br',2);
insert into compras.usuario values (3,'Aprovador Tecnico','csem1234','aprovador.tecnico@csembrasil.com.br',3);
insert into compras.usuario values (4,'Compras','csem1234','compras@csembrasil.com.br',4);
insert into compras.usuario values (5,'Administrativo','csem1234','administrativo@csembrasil.com.br',5);
insert into compras.usuario values (6,'Financeiro','csem1234','financeiro@csembrasil.com.br',6);
insert into compras.usuario values (7,'RH','csem1234','rh@csembrasil.com.br',7);
insert into compras.usuario values (8,'Nacional','csem1234','compras@csembrasil.com.br',4);
insert into compras.usuario values (9,'Internacional','csem1234','compras@csembrasil.com.br',4);

-- ------------------------------------------------------------------------------------------------
-- TIPO COBRANÇA
-- ------------------------------------------------------------------------------------------------
insert into compras.tipocobranca values (1, 'Depósito Bancário');
insert into compras.tipocobranca values (2, 'Câmbio');
insert into compras.tipocobranca values (3, 'Boleto Bancário');

-- ------------------------------------------------------------------------------------------------
-- TIPO EMBARQUE
-- ------------------------------------------------------------------------------------------------
insert into compras.tipoembarque values (1, 'Via Courier');
insert into compras.tipoembarque values (2, 'Importação Normal');

-- ------------------------------------------------------------------------------------------------
-- 
-- ------------------------------------------------------------------------------------------------