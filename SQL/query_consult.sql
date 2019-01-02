-- ------------------------------------------------------------------------------------------------
-- CONSULT LOGIN INNER JOIN Niveis
-- ------------------------------------------------------------------------------------------------
SELECT * FROM Usuario a inner join Niveis n on a.CodNivel = n.CodNivel;

-- ------------------------------------------------------------------------------------------------
-- LOCALIZA Usuario
-- ------------------------------------------------------------------------------------------------
select * from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.CodUsuario = 2;
select n.NomeNivel from Usuario u inner join Niveis n on u.CodNivel = n.CodNivel where u.Nome = 'teste';
-- SELECT LISTA WEB
select u.Nome from Usuario u where u.CodNivel = 4;
-- LOCALIZA EMAIL USER
select u.EmailFunc from Usuario u where u.CodUsuario = 1;


-- ------------------------------------------------------------------------------------------------
-- ALTERAR Usuario
-- ------------------------------------------------------------------------------------------------
update Usuario u set u.Nome = 'Felipe M Ferreira', u.Senha = '123', u.EmailFunc = 'felipe.m.ferreira', u.CodNivel = 2 where u.CodUsuario = 8;

-- ------------------------------------------------------------------------------------------------
-- SELECT Niveis
-- ------------------------------------------------------------------------------------------------
select n.NomeNivel from Niveis n;

select n.CodNivel from Niveis n where n.NomeNivel = 'Administrador';

-- ------------------------------------------------------------------------------------------------
-- SELECT Projetos
-- ------------------------------------------------------------------------------------------------
select * from Projetos;
select * from Projetos p where p.CodProjeto = 1;
select p.NomeProjeto from Projetos p;
-- ------------------------------------------------------------------------------------------------
-- ALTERAR PROJETO
-- ------------------------------------------------------------------------------------------------
update Projetos p set p.NomeProjeto = 'BD' where p.CodProjeto = 1;

-- ------------------------------------------------------------------------------------------------
-- SELECT TIPO REQUISICAO
-- ------------------------------------------------------------------------------------------------
select * from TipoReq;
select * from TipoReq where TipoRequisicao = 'Nacional';

-- ------------------------------------------------------------------------------------------------
-- SELECT Destinacao
-- ------------------------------------------------------------------------------------------------
select * from Destinacao;
select * from Destinacao where Destinacao = 'Equipamentos';

-- ------------------------------------------------------------------------------------------------
-- SELECT TIPO FRETE
-- ------------------------------------------------------------------------------------------------
select * from TipoFrete;
select * from TipoFrete where TipoFrete = 'CIF';

-- ------------------------------------------------------------------------------------------------
-- SELECT STATUS REQUISICAO
-- ------------------------------------------------------------------------------------------------
select * from StatusRequisicao;
select * from StatusRequisicao where Status = 'Nova';
select st1.Status as st1, st2.Status as st2 from StatusRequisicao st1 inner join StatusRequisicao st2 where st1.Status = 'Nova' and st2.Status='Enviada';
select st.Status from StatusRequisicao st where st.Status in ('Nova', 'Enviada', 'Cancelada', 'Arquivada', 'Desarquivada');
-- ------------------------------------------------------------------------------------------------
-- SELECT Moedas
-- ------------------------------------------------------------------------------------------------
select * from Moedas;
select * from Moedas where Abrev = 'BRL';

-- ------------------------------------------------------------------------------------------------
-- SELECT STATUS ARQ REQUISICAO
-- ------------------------------------------------------------------------------------------------
select * from StatusArq;
select * from StatusArq where Arqvd = 'S';

-- ------------------------------------------------------------------------------------------------
-- SELECT Solicitante
-- ------------------------------------------------------------------------------------------------
select * from Solicitante;
select * from Solicitante where NomeSolicitante = 'felipe';

-- ------------------------------------------------------------------------------------------------
-- SELECT Requisicoes
-- ------------------------------------------------------------------------------------------------
select * from Requisicoes;

-- SELECT FULL
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.DataAprovTecnico, r.Justificativa, r.Vinculacao, r.Aprovador, r.AprovadorTecnico, r.TipoAprovador, 
d.CodDest, d.Destinacao, 
m.CodMoeda, m.Abrev,
p.CodProjeto, p.NomeProjeto,
tr.CodTipoReq, tr.TipoRequisicao,
sr.CodStatus, sr.Status,
tf.CodFrete, tf.TipoFrete,
u.CodUsuario, u.Nome,
sa.CodArq, sa.Arqvd,
s.CodSolicitante, s.NomeSolicitante
from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest 
inner join Moedas m on r.CodMoeda = m.CodMoeda 
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq
inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus
inner join TipoFrete tf on tf.CodFrete = r.CodFrete
inner join Usuario u on u.CodUsuario = r.CodUsuario
inner join StatusArq sa on sa.CodArq = r.CodArq
inner join Solicitante s on s.CodSolicitante = r.CodSolicitante;

-- SELECT C/ WHERE
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.Justificativa, r.Vinculacao, r.Aprovador, r.TipoAprovador, 
d.CodDest, d.Destinacao, 
m.CodMoeda, m.Abrev,
p.CodProjeto, p.NomeProjeto,
tr.CodTipoReq, tr.TipoRequisicao,
sr.CodStatus, sr.Status,
tf.CodFrete, tf.TipoFrete,
u.CodUsuario, u.Nome,
sa.CodArq, sa.Arqvd,
s.CodSolicitante, s.NomeSolicitante
from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest 
inner join Moedas m on r.CodMoeda = m.CodMoeda 
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq
inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus
inner join TipoFrete tf on tf.CodFrete = r.CodFrete
inner join Usuario u on u.CodUsuario = r.CodUsuario
inner join StatusArq sa on sa.CodArq = r.CodArq
inner join Solicitante s on s.CodSolicitante = r.CodSolicitante
where u.Nome like 'compras';

-- SELECT LIST Requisicoes
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, 
p.CodProjeto, p.NomeProjeto,
tr.CodTipoReq, tr.TipoRequisicao,
sr.CodStatus, sr.Status,
u.CodUsuario, u.Nome
from Requisicoes r 
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq
inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus
inner join Usuario u on u.CodUsuario = r.CodUsuario
where r.TipoAprovador = 'Aprovador Administrativo' and sr.Status = 'Aprovada' or sr.Status = 'Aprovada Tecnico';

select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = 'Compras' and r.CodArq = 1 order by CodRequisicao desc;

-- SELECT FOLLOW UP 
select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, r.DataPrevisaoEntrega, r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodRequisicao inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Item i on i.CodRequisicao = r.CodRequisicao inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao where u.Nome = ? and f.Escolha = 1 and r.DataSolicitacao between ? and ? order by r.CodRequisicao;

-- FOLLOW-UP ESTIMATIVAS DE DATA E VALORES
select r.CodRequisicao, s.NomeSolicitante, r.DataSolicitacao, r.DataCriacao, r.DataAprov, 
p.NomeProjeto, i.NomeItem, f.NomeFornecedor, e.EtapaRequisicao, f.TempoProducao, r.DataPrevisaoEntrega, 
r.DataEntrega, u.Nome from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodRequisicao 
inner join Projetos p on p.CodProjeto = r.CodProjeto 
inner join Item i on i.CodRequisicao = r.CodRequisicao 
inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao 
inner join Usuario u on u.CodUsuario = r.CodUsuario 
inner join EtapaRequisicao e on e.CodEtapaRequisicao = r.CodEtapaRequisicao;

select f.ValorInicial, f.ValorFinal, 
r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, 
f.TempoProducao, r.DataPrevisaoEntrega, 
r.DataEntrega, datediff(r.DataEntrega, r.DataSolicitacao) as Tempo 
from Requisicoes r 
inner join Solicitante s on s.CodSolicitante = r.CodRequisicao 
inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao;

select sum(datediff(r.DataCriacao, r.DataSolicitacao)) as TempoReq, 
sum(datediff(r.DataAprov, r.DataCriacao)) as TempoAprov,
sum(datediff(r.DataAprovTecnico, r.DataCriacao)) as TempoAprovTec,
sum(f.TempoProducao) as TempoProc,
sum(datediff(r.DataEntrega, r.DataSolicitacao)) as TempoTotal,
count(r.CodRequisicao) NumeroCompras,
avg(datediff(r.DataEntrega, r.DataSolicitacao)) as Media
from Requisicoes r 
inner join Usuario u on u.CodUsuario = r.CodUsuario  
inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao
group by u.Nome;

select u.Nome, sum(f.ValorInicial) as Inicial,sum(f.ValorFinal) as Final, sum(f.ValorInicial - f.ValorFinal) as Desconto,
count(r.CodRequisicao) NumeroCompras, avg(f.ValorInicial - f.ValorFinal) as Media
from Requisicoes r 
inner join Usuario u on u.CodUsuario = r.CodUsuario 
inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao
group by u.Nome;

select u.Nome, sum(f.ValorInicial) as Inicial,sum(f.ValorFinal) as Final, sum(f.ValorInicial - f.ValorFinal) as Desconto, count(r.CodRequisicao) NumeroCompras, avg(f.ValorInicial - f.ValorFinal) as Media from Requisicoes r  inner join Usuario u on u.CodUsuario = r.CodUsuario  inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao group by u.Nome;

-- SELECT P/ ALTERAR E APROVAR
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.Justificativa, r.Vinculacao, r.TipoAprovador, 
d.Destinacao, 
m.Abrev,
p.NomeProjeto,
tr.TipoRequisicao,
tf.TipoFrete,
u.Nome,
s.NomeSolicitante
from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest 
inner join Moedas m on r.CodMoeda = m.CodMoeda 
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq
inner join TipoFrete tf on tf.CodFrete = r.CodFrete
inner join Usuario u on u.CodUsuario = r.CodUsuario
inner join Solicitante s on s.CodSolicitante = r.CodSolicitante
where r.CodRequisicao = 23;

select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.Justificativa, r.Vinculacao, r.TipoAprovador, d.Destinacao, m.Abrev, p.NomeProjeto, tr.TipoRequisicao, tf.TipoFrete, sr.Status, u.Nome, s.NomeSolicitante from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest inner join Moedas m on r.CodMoeda = m.CodMoeda inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join TipoFrete tf on tf.CodFrete = r.CodFrete inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante where r.CodRequisicao = 23;

select * from StatusRequisicao;
-- UPDATE APROV REQ
update Requisicoes r set r.DataAprov = 2, r.CodigoStatus = 2, r.Aprovador = 2 where r.CodRequisicao = 19;

-- UPDATE APROV TEC REQ
update Requisicoes r set r.DataAprovTecnico = 2, r.CodigoStatus = 7, r.AprovadorTecnico = 2, r.TipoAprovador = 'Aprovador Financeiro' where r.CodRequisicao = 19;

select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.Justificativa, r.Vinculacao, r.TipoAprovador, d.Destinacao, m.Abrev, p.NomeProjeto, tr.TipoRequisicao, tf.TipoFrete, u.Nome, s.NomeSolicitante from Requisicoes r inner join Destinacao d on r.CodDest = d.CodDest inner join Moedas m on r.CodMoeda = m.CodMoeda inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join TipoFrete tf on tf.CodFrete = r.CodFrete inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante where r.CodRequisicao = 23;

-- SELECT VINCULACAO
select r.CodRequisicao from Requisicoes r order by CodRequisicao desc;

-- SELECT RELATORIO REQ COMPRA
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.Justificativa, r.Aprovador, 
p.NomeProjeto,
tr.TipoRequisicao,
u.Nome,
s.NomeSolicitante,
i.NomeItem, i.Unidade, i.Quantidade, i.InformacoesAdicionais
from Requisicoes r
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq
inner join Usuario u on u.CodUsuario = r.CodUsuario
inner join Solicitante s on s.CodSolicitante = r.CodSolicitante
inner join Item i on i.CodRequisicao = r.CodRequisicao
where r.CodRequisicao = 20;

select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, r.DataAprov, r.Justificativa, r.Aprovador, p.NomeProjeto, tr.TipoRequisicao, u.Nome, s.NomeSolicitante from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante where r.CodRequisicao = 20;

-- UPDATE ARQUIVAR REQUISICAO
update Requisicoes r set r.CodArq = 2, r.CodigoStatus = 5 where r.CodRequisicao = 19;

-- FINALIZAR REQUISICAO
update Requisicoes r set r.DataEntrega = 1, r.CodigoStatus = 6 where r.CodRequisicao = 1;

-- UPDATE JUSTIFICATIVA
update Requisicoes r set r.Justificativa = 1 where r.CodRequisicao = 1;

-- CANCELAR REQUISICAO
update Requisicoes r set r.CodigoStatus = 5 where r.CodRequisicao = 1;

-- RELATORIO ORDEM DE COMPRA
select r.CodRequisicao, r.DataSolicitacao, u.Nome, u.EmailFunc from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario;
	-- FORNECEDOR
select f.CodRequisicao, r.DataSolicitacao, s.NomeSolicitante, f.NomeFornecedor, tf.TipoFrete, m.Abrev, f.ValorFinal from Requisicoes r inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join TipoFrete tf on tf.CodFrete = r.CodFrete inner join Moedas m on m.CodMoeda = r.CodMoeda inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao where f.Escolha = true;
	-- ITENS
select i.CodRequisicao, i.NomeItem, i.Unidade, i.Quantidade, i.DescricaoTecnica, i.ValorTotal from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao where i.CodRequisicao = $P{CodRequisicao};

select r.CodRequisicao, r.DataSolicitacao, u.Nome, u.EmailFunc from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario where r.CodRequisicao = 1;
select r.CodRequisicao, r.DataSolicitacao, u.Nome, u.EmailFunc, s.NomeSolicitante, f.NomeFornecedor, tf.TipoFrete, m.Abrev, f.ValorFinal from Requisicoes r inner join Usuario u on u.CodUsuario = r.CodUsuario inner join Solicitante s on s.CodSolicitante = r.CodSolicitante inner join TipoFrete tf on tf.CodFrete = r.CodFrete inner join Moedas m on m.CodMoeda = r.CodMoeda inner join Fornecedores f on f.CodRequisicao = r.CodRequisicao where f.Escolha = true and f.CodRequisicao = r.CodRequisicao;

-- FILTROS REQUISICAO TODOS
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome like ? and sr.Status like ? and r.CodArq = 1 order by CodRequisicao desc;

-- LOCALIZA REQ ARQV
select r.CodArq from Requisicoes r where r.CodRequisicao = 1;

-- LOCALIZA REQ CANC
select r.CodRequisicao, r.DataSolicitacao, r.DataCriacao, p.CodProjeto, p.NomeProjeto, tr.CodTipoReq, tr.TipoRequisicao, sr.CodStatus, sr.Status, u.CodUsuario, u.Nome from Requisicoes r inner join Projetos p on p.CodProjeto = r.CodProjeto inner join TipoReq tr on tr.CodTipoReq = r.CodTipoReq inner join StatusRequisicao sr on sr.CodStatus = r.CodigoStatus inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome = "Nacional" and r.CodigoStatus = 5 order by CodRequisicao desc;

-- UPDATE REQUISICAO
update Requisicoes r set r.CodProjeto = ?, r.CodDest = ?, r.CodTipoReq = ?, r.CodMoeda = ?, r.TipoAprovador = ?, r.Justificativa = ?, r.Motivo = ? where r.CodRequisicao = ?;
-- ------------------------------------------------------------------------------------------------
-- SELECT Fornecedores
-- ------------------------------------------------------------------------------------------------
select * from Fornecedores;
select * from Fornecedores where CodFornecedor = '1';
select f.NomeFornecedor, f.Telefone, f.Email, f.Contato, f.InformacoesAdicionais, f.ValorInicial, f.ValorFinal, f.Escolha from Fornecedores f where f.CodRequisicao = 2;

-- SELECT RELATORIO REQ COMPRA
select f.CodRequisicao, f.NomeFornecedor, m.Abrev, f.ValorInicial, f.ValorFinal, f.Escolha from Fornecedores f inner join Requisicoes r on f.CodRequisicao = r.CodRequisicao inner join Moedas m on m.CodMoeda = r.CodMoeda where f.CodRequisicao = r.CodRequisicao;

-- UPDATE INF FINANCEIRAS
update Fornecedores f set f.CPF = 2, f.CNPJ = 2, f.Banco = 1, f.Agencia = 2, f.Conta = 1 where f.CodRequisicao = 1;

-- SELECT INF ADICIONAIS
select f.CodFornecedor, f.CPF, f.CNPJ, f.Banco, f.Agencia, f.Conta from Fornecedores f where f.Escolha = 1 and f.CodRequisicao = 1;

-- BUSCA E PREENCHE INFO ADICIONAIS
select f.NomeFornecedor, f.CodFornecedor, f.CPF, f.CNPJ, f.Banco, f.Agencia, f.Conta from Fornecedores f where f.NomeFornecedor like '%alun%';

-- LISTA FORNECEDOR
select f.NomeFornecedor, f.CodRequisicao from Fornecedores f inner join Requisicoes r on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where f.NomeFornecedor like '%f%' and u.Nome like '%N%' and f.Escolha = true;
select f.NomeFornecedor, f.CodRequisicao from Fornecedores f inner join Requisicoes r on f.CodRequisicao = r.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = 8 and f.Escolha = true;

-- UPDATE FORCENEDOR SOLICITACAO
update FornecedoresSolicitacao f set f.NomeFornecedor = '', f.Telefone = '', f.Email = '', f.InfrmacoesAdicionais = '', f.Contato = '', f.ValorTotal = '' where f.CodFornecedor = 1 and f.CodSolicitacao = 1;

-- DELETE
delete from FornecedoresSolicitacao where CodFornecedor = ? and CodSolicitacao = ?;

-- UPDATE FORNECEDOR TEMPO DE PRODUCAO
select f.NomeFornecedor, f.CodFornecedor from Fornecedores f where f.CodRequisicao = 2 and f.Escolha = 0;
update Fornecedores f set f.TempoProducao = ? where f.CodRequisicao = ? and f.Escolha = 1;
-- ------------------------------------------------------------------------------------------------
-- SELECT ITENS
-- ------------------------------------------------------------------------------------------------
select * from Item;
select * from Item where CodRequisicao = '20';
select i.CodRequisicao, i.NomeItem, i.Unidade, i.Quantidade, i.InformacoesAdicionais from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao where i.CodRequisicao = r.CodRequisicao;

-- FILTRO ITEM
select i.NomeItem, i.CodRequisicao from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where i.NomeItem like '%1%' and u.Nome like '%%';
select i.NomeItem, i.CodRequisicao from Item i inner join Requisicoes r on r.CodRequisicao = i.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = 8;

-- UPDATE ITEM
update ItemSolicitacao i set i.NomeItem = ?, i.Quantidade = ?, i.Unidade = ?, i.DescricaoTecnica = ?, i.InformacoesAdicionais = ?, i.ValorUnitario = ?, i.ValorTotal = ? where i.CodItemSolicitacao = ? and i.CodSolicitacao = ?;
-- DELETE
delete from ItemSolicitacao where CodItemSolicitacao = ? and CodSolicitacao = ?;
select * from FornecedoresSolicitacao;
-- ------------------------------------------------------------------------------------------------
-- SELECT TIPO COBRANCA
-- ------------------------------------------------------------------------------------------------
select * from TipoCobranca;
select tc.TipoCobranca from TipoCobranca tc;
select tc.CodTipoCobranca from TipoCobranca tc where tc.TipoCobranca = 'Câmbio';

-- ------------------------------------------------------------------------------------------------
-- SELECT ORDEM PAGAMENTO
-- ------------------------------------------------------------------------------------------------
select * from OrdemPagto;
select o.DataOP, o.Parcela, o.ValorPagar, o.DataVencimento, tc.TipoCobranca, o.NCM, o.PrevisaoEmbarque, o.Comentarios from OrdemPagto o inner join TipoCobranca tc on tc.CodTipoCobranca = o.CodTipoCobranca where o.CodRequisicao = 1;

-- OP MAIS RECENTE DATA
select max(o.DataOP) as DataOP, o.Parcela, o.ValorPagar, o.DataVencimento, tc.TipoCobranca, o.NCM, o.PrevisaoEmbarque, o.Comentarios from OrdemPagto o inner join TipoCobranca tc on tc.CodTipoCobranca = o.CodTipoCobranca where o.CodRequisicao = 1 group by o.CodOrdemPagamento;

-- UPDATE ORDEM PAGAMENTO
update OrdemPagto o set o.Parcela = '', o.ValorPagar = '', o.DataVencimento = '', o.CodTipoCobranca = 1, o.NCM = '', o.PrevisaoEmbarque = '', o.Comentarios = '' where o.CodOrdemPagamento = 1;  

-- SELECT RELATORIO ORDEM PAGAMENTO
select op.CodRequisicao, op.Parcela, op.DataOP, op.DataVencimento, f.NomeFornecedor, p.NomeProjeto, d.Destinacao, t.TipoCobranca, f.CNPJ, f.CPF, f.Banco, f.Agencia, f.Conta, op.ValorPagar, m.Abrev, op.Comentarios
from OrdemPagto op 
inner join Fornecedores f on f.CodRequisicao = op.CodRequisicao 
inner join Requisicoes r on r.CodRequisicao = op.CodRequisicao 
inner join Projetos p on p.CodProjeto = r.CodProjeto
inner join Destinacao d on d.CodDest = r.CodDest
inner join TipoCobranca t on t.CodTipoCobranca = op.CodTipoCobranca
inner join Moedas m on m.CodMoeda = r.CodMoeda
where op.CodOrdemPagamento = 9 and f.Escolha = 1;

select op.CodRequisicao, op.Parcela, op.DataOP, op.DataVencimento, f.NomeFornecedor, p.NomeProjeto, d.Destinacao, t.TipoCobranca, f.CNPJ, f.CPF, f.Banco, f.Agencia, f.Conta, op.ValorPagar, m.Abrev, op.Comentarios from OrdemPagto op  inner join Fornecedores f on f.CodRequisicao = op.CodRequisicao  inner join Requisicoes r on r.CodRequisicao = op.CodRequisicao  inner join Projetos p on p.CodProjeto = r.CodProjeto inner join Destinacao d on d.CodDest = r.CodDest inner join TipoCobranca t on t.CodTipoCobranca = op.CodTipoCobranca inner join Moedas m on m.CodMoeda = r.CodMoeda where op.CodOrdemPagamento = 9 and f.Escolha = 1;

-- LIST OP
select o.CodRequisicao, o.Parcela, o.ValorPagar, o.DataOP, o.DataVencimento, o.NCM, o.PrevisaoEmbarque from OrdemPagto o inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.Nome like '%%' order by o.CodRequisicao desc;
select o.CodRequisicao, o.Parcela, o.ValorPagar, o.DataOP, o.DataVencimento, o.NCM, o.PrevisaoEmbarque from OrdemPagto o inner join Requisicoes r on r.CodRequisicao = o.CodRequisicao inner join Usuario u on u.CodUsuario = r.CodUsuario where u.CodUsuario = 8 order by o.CodRequisicao desc;

-- ------------------------------------------------------------------------------------------------
-- Solicitacoes
-- ------------------------------------------------------------------------------------------------
-- RELTORIO LISTA
select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodUsuario = 8 and s.CodArq = 1 and st.CodStatus = 1 and st.CodStatus = 1 order by s.CodSolicitacao desc;

-- RELATORIO CARREGA
select  s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante where s.CodSolicitacao = 1 order by CodSolicitacao desc;
-- UPDATE STATUS SOLICITACAO
update Solicitacoes s set s.CodigoStatus = 2 where s.CodSolicitacao = 1;
-- ARQUIVAR SOLICITACAO
update Solicitacoes s set s.CodArq = 2, s.CodigoStatus = 8 where s.CodSolicitacao = 1;
-- DESARQUICAR SOLICITACAO
update Solicitacoes s set s.CodArq = 1, s.CodigoStatus = 9 where s.CodSolicitacao = 1;
-- CANCELAR
update Solicitacoes s set s.CodigoStatus = 5 where s.CodSolicitacao = 1;

-- FITRO SOLIC
select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.CodStatus, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where u.Nome like '%%' and st.Status like '%%' and s.CodArq = 1 order by s.CodSolicitacao desc;

-- LISTA ARQ
select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodArq = 2 order by s.CodSolicitacao desc;

-- LISTA ENVIADA
select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodigoStatus = 2 order by s.CodSolicitacao desc;

-- LISTA CANCELADA
select  s.CodSolicitacao, s.DataSolicitacao, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodigoStatus = 5 order by s.CodSolicitacao desc;

-- RELATORIO SOLICITACAO COMPRA
select s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, u.Nome, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join Usuario u on s.CodUsuario = u.CodUsuario where s.CodSolicitacao = 4 order by CodSolicitacao desc;

-- UPDATE
update Solicitacoes s set s.CodTipoReq = ?, s.CodUsuario = ?, s.CodProjeto = ?, s.Justificativa = ? where s.CodSolicitacao = ?;

-- UPDATE RECUSADA
update Solicitacoes s set s.CodigoStatus = ?, s.Motivo = ? where s.CodSolicitacao = ?;

-- UPDATE RECUSADA USER
update Solicitacoes s set s.CodigoStatus = ?, s.CodTipoReq = ?, s.CodUsuario = ?, s.CodProjeto = ?, s.Justificativa = ? where s.CodSolicitacao = ?;
-- ------------------------------------------------------------------------------------------------
--  SOLICITACOES USER
-- ------------------------------------------------------------------------------------------------
select s.EmailSolicitante, s.DataSolicitacao, s.Motivo, u.EmailFunc, u.Nome from Solicitacoes s inner join Usuario u on u.CodUsuario = s.CodUsuario where s.CodSolicitacao = 1;
select  s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, s.Motivo, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd, u.Nome from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where so.NomeSolicitante = 'visitante' and s.CodArq = 1 and st.CodStatus = 2 order by s.CodSolicitacao desc;
select  s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, s.Motivo, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd, u.Nome from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where so.NomeSolicitante like 'visitante' and st.Status like '%%' order by s.CodSolicitacao desc;


-- RECUSADA
select  s.CodSolicitacao, s.DataSolicitacao, s.Justificativa, s.Motivo, p.NomeProjeto, t.TipoRequisicao, st.Status, so.NomeSolicitante, sta.Arqvd, u.Nome from Solicitacoes s inner join Projetos p on s.CodProjeto = p.CodProjeto inner join TipoReq t on s.CodTipoReq = t.CodTipoReq inner join StatusRequisicao st on s.CodigoStatus = st.CodStatus inner join Solicitante so on s.CodSolicitante = so.CodSolicitante inner join StatusArq sta on s.CodArq = sta.CodArq inner join Usuario u on u.CodUsuario = s.CodUsuario where so.NomeSolicitante like 'visitante' and st.Status in ('Nova', 'Enviada', 'Cancelada', 'Arquivada', 'Desarquivada') order by s.CodSolicitacao desc;
-- ------------------------------------------------------------------------------------------------
--  
-- ------------------------------------------------------------------------------------------------
update OrdemPagto o set o.DataOP = ?, o.ValorPagar = ?, o.DataVencimento = ?, o.CodTipoCobranca = ?, o.NCM = ?, o.PrevisaoEmbarque = ?, o.Comentarios = ? where o.CodRequisicao = ? and o.Parcela = ?;


-- ------------------------------------------------------------------------------------------------
--  TESTE IMPORT BASE DE DADOS SISTEMA ANTIGO
-- ------------------------------------------------------------------------------------------------

insert into csem_2018.Requisicoes (`CodRequisicao`, `DataSolicitacao`, `DataCriacao`, `DataAprov`, `DataEntrega`, `DataAprovTecnico`, `DataPrevisaoEntrega`, `Justificativa`, `Motivo`, `Vinculacao`, `Aprovador`, `AprovadorTecnico`, `TipoAprovador`, `CodDest`, `CodMoeda`, `CodProjeto`, `CodTipoReq`, `CodigoStatus`, `CodFrete`, `CodUsuario`, `CodArq`, `CodSolicitante`, `CodEtapaRequisicao`) VALUES
(5, '2018-01-03 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(7, '2018-01-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(8, '2018-01-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(9, '2018-01-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(10, '2018-01-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(11, '2018-01-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(12, '2018-01-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(13, '2018-01-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(14, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(15, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(16, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(17, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(18, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(19, '2018-01-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(20, '2018-01-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(21, '2018-01-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(22, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(23, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(24, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(25, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(26, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(27, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(28, '2018-01-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(29, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(30, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(31, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(32, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(33, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(34, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(35, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(36, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(37, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(38, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(39, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(40, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(41, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(42, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(44, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(45, '2018-01-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(46, '2018-01-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(47, '2018-01-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(48, '2018-01-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(49, '2018-01-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(50, '2018-01-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(51, '2018-01-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(52, '2018-01-17 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(53, '2018-01-17 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(54, '2018-01-17 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(55, '2018-01-18 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(56, '2018-01-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(57, '2018-01-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(58, '2018-01-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(59, '2018-01-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(60, '2018-01-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(62, '2018-01-17 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(63, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(64, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(65, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(66, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(67, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(68, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(69, '2018-01-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(70, '2018-01-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(71, '2018-01-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(72, '2018-01-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(73, '2018-01-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(74, '2018-01-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(75, '2018-01-24 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(76, '2018-01-24 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(77, '2018-01-25 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(78, '2018-01-25 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(79, '2018-01-25 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(80, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(81, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(82, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(83, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(84, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(85, '2018-01-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(86, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(87, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(88, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(89, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(90, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(91, '2018-01-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(92, '2018-01-30 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(93, '2018-01-30 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(94, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(95, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(96, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(97, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(98, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(99, '2018-01-31 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(100, '2018-02-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(101, '2018-02-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(102, '2018-02-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(103, '2018-02-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(104, '2018-02-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(105, '2018-02-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(106, '2018-02-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(107, '2018-02-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(108, '2018-02-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(109, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(110, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(111, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(112, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(113, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(114, '2018-02-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(115, '2018-02-07 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(116, '2018-02-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(117, '2018-02-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(118, '2018-02-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(119, '2018-02-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(120, '2018-02-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(121, '2018-02-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(122, '2018-02-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(123, '2018-02-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(124, '2018-02-14 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(125, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(126, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(127, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(128, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(129, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(130, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(131, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(132, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(133, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(134, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(135, '2018-02-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(136, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(137, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(138, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(139, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(140, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(141, '2018-02-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(142, '2018-02-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(143, '2018-02-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(145, '2018-02-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(146, '2018-02-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(147, '2018-02-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(148, '2018-02-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(149, '2018-02-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(150, '2018-02-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(151, '2018-02-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(152, '2018-02-27 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(153, '2018-02-27 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(154, '2018-02-27 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(155, '2018-02-27 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(156, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(157, '2018-02-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(158, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(159, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(160, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(161, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(162, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(163, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(164, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(165, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(166, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(167, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(168, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(169, '2018-02-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(170, '2018-03-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(171, '2018-03-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(172, '2018-03-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(173, '2018-03-01 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(174, '2018-03-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(175, '2018-03-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(176, '2018-03-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(177, '2018-03-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(178, '2018-03-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(179, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(180, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(181, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(182, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(183, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(184, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(185, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(186, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(187, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(188, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(189, '2018-03-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(190, '2018-03-07 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(191, '2018-03-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(192, '2018-03-08 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(193, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(194, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(195, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(196, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(197, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(198, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(199, '2018-03-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(200, '2018-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(201, '2018-03-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(202, '2018-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(203, '2018-03-13 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(204, '2018-03-14 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(205, '2018-03-14 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(206, '2018-03-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(207, '2018-03-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(208, '2018-03-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(209, '2018-03-15 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(210, '2018-03-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(211, '2018-03-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(212, '2018-03-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(213, '2018-03-16 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(214, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(215, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(216, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(217, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(218, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(219, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(220, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(221, '2018-03-19 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(222, '2018-03-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(223, '2018-03-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(224, '2018-03-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(225, '2018-03-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(226, '2018-03-20 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(227, '2018-03-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(228, '2018-03-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(229, '2018-03-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(230, '2018-03-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(231, '2018-03-21 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(232, '2018-03-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(233, '2018-03-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(235, '2018-03-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(236, '2018-03-22 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(237, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(238, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(239, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(240, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(241, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(242, '2018-03-23 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(243, '2018-03-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(244, '2018-03-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(245, '2018-03-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(246, '2018-03-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(247, '2018-03-26 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(248, '2018-03-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(249, '2018-03-28 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(250, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(251, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(252, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(253, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(254, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(255, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(256, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(257, '2018-03-29 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(258, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(259, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(261, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(262, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(263, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(264, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(265, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(266, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(267, '2018-04-02 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(268, '2018-04-03 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(269, '2018-04-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(270, '2018-04-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(271, '2018-04-04 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(272, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(273, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(274, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(275, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(276, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(277, '2018-04-05 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(278, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(279, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(280, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(281, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(282, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(283, '2018-04-06 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(284, '2018-04-09 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(285, '2018-04-10 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(286, '2018-04-10 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(287, '2018-04-10 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(288, '2018-04-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(289, '2018-04-11 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(290, '2018-04-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(291, '2018-04-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(292, '2018-04-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1),
(293, '2018-04-12 00:00:00', NULL, NULL, NULL, NULL, NULL, '', '', 0, '', NULL, '', 1, 1, 7, 1, 6, 3, 1, 1, 3, 1);
