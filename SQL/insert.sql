INSERT INTO `Destinacao` (`CodDest`, `Destinacao`) VALUES
(1, 'Equipamentos'),
(2, 'Software'),
(3, 'Uso e Consumo'),
(4, 'Outros');

INSERT INTO `Moedas` (`CodMoeda`, `Moeda`, `Abrev`) VALUES
(1, 'Real', 'BRL'),
(2, 'Dólar', 'USD'),
(3, 'Euro', 'EUR'),
(4, 'Yen', 'JPY'),
(5, 'Yuan', 'CNY');

INSERT INTO `Niveis` (`CodNivel`, `NomeNivel`) VALUES
(1, 'Mestre'),
(2, 'Aprovação'),
(3, 'Compras'),
(4, 'Administrativo'),
(5, 'Financeiro'),
(6, 'RH');

INSERT INTO `Projetos` (`CodigoProjeto`, `NomeProjeto`) VALUES
(1, 'Recursos Próprios'),
(2, 'BNDES LTCC'),
(3, 'BNDES OE'),
(4, 'Fiat'),
(5, 'Medabil'),
(6, 'Votorantim'),
(7, 'ABERTO'),
(8, 'Plataforma OE'),
(9, 'CSEM PE');

-- --------------------------------------------------------

--
-- Estrutura da tabela `Requisitantes`
-- PARA CODIFICAR A SENHA INSERT EXEMPLO (1, 'User Teste', PASSWORD('test'), 3, 'user.test@csembrasil.com.br'),

INSERT INTO `Requisitantes` (`CodigoRequisitante`, `NomeRequisitante`, `Senha`, `CodNivel`, `EmailFunc`) VALUES 
-- (1, 'Flávia Albuquerque', PASSWORD('123'), 3, 'flavia.albuquerque@csembrasil.com.br'),
-- (2, 'Francine Santana', PASSWORD('789'), 5, 'francine.santana@csembrasil.com.br'),
-- (3, 'Ariane Campos', PASSWORD('012'), 5, 'ariane.campos@csembrasil.com.br'),
-- (4, 'Rafaela Oliveira', PASSWORD('345'), 3, 'rafaela.oliveira@csembrasil.com.br'),
-- (5, 'Administrador', PASSWORD('admin'), 1, NULL),
-- (6, 'Paula Laguárdia', PASSWORD('901'), 6, 'paula.laguardia@csembrasil.com.br'),
-- (7, 'Ariane Costa', PASSWORD('008'), 2, 'ariane.campos@csembrasil.com.br'),
-- (8, 'Erika Ferreira', PASSWORD('345'), 5, 'erika.ferreira@csembrasil.com.br');

(1, 'Flávia Albuquerque', '123', 3, 'flavia.albuquerque@csembrasil.com.br'),
(2, 'Francine Santana','789', 5, 'francine.santana@csembrasil.com.br'),
(3, 'Ariane Campos', '012', 5, 'ariane.campos@csembrasil.com.br'),
(4, 'Rafaela Oliveira', '345', 3, 'rafaela.oliveira@csembrasil.com.br'),
(5, 'Administrador', 'admin', 1, NULL),
(6, 'Paula Laguárdia', '901', 6, 'paula.laguardia@csembrasil.com.br'),
(7, 'Ariane Costa', '008', 2, 'ariane.campos@csembrasil.com.br'),
(8, 'Erika Ferreira', '345', 5, 'erika.ferreira@csembrasil.com.br');

INSERT INTO `StatusArq` (`CodArq`, `Arqvd`) VALUES
(1, 'N'),
(2, 'S');

INSERT INTO `StatusRequisicao` (`CodigoStatus`, `Status`) VALUES
(0, 'Nova'),
(1, 'Enviada'),
(2, 'Aprovada'),
(3, 'Recusada'),
(4, 'Cancelada');

INSERT INTO `TipoCobranca` (`CodTipoCobranca`, `TipoCobranca`) VALUES
(1, 'Depósito Bancário'),
(2, 'Câmbio'),
(4, 'Boleto Bancário');

INSERT INTO `TipoEmbarque` (`TipoEmbarque`, `NomeEmbarque`) VALUES
(1, 'Via Courier'),
(2, 'Importação Normal');

INSERT INTO `TipoFrete` (`CodFrete`, `TipoFrete`) VALUES
(1, 'CIF'),
(2, 'FOB'),
(3, '-');

INSERT INTO `TipoReq` (`CodTipoReq`, `TipoRequisicao`, `NivelAcesso`) VALUES
(1, 'Nacional', 6),
(2, 'Internacional', 3),
(3, 'RH', 4),
(4, 'Frete', 3),
(5, 'Requisição Aberta', 3),
(6, 'Frete Internacional', 3);

