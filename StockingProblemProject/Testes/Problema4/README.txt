Para o dataset Problema4 começámos baseados nos testes preliminares feitos ao dataset Problema1. 
Assim executando testes gerais a este mais precisos. 

Population_size: 50, 100, 150
Max_generations: 150, 200
Selection: tournament
Tournament_size: 2, 4, 6
Recombination: pmx, ox, cx2
Recombination_probability: 0.6, 0.7, 0.8
Mutation: insert, exchange, simpleinversion
Mutation_probability: 0.7, 0.8, 0.9
Probability_of_1s: 0.05

Top10

Population size:	Max generations:	Selection:	Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150	200	Tournament(4)	PMX	0,8	EM	0,9		243,02
100	200	Tournament(4)	PMX	0,8	EM	0,9		243,56
150	200	Tournament(4)	PMX	0,6	EM	0,9		243,56
150	150	Tournament(4)	PMX	0,8	EM	0,9		243,76
150	200	Tournament(6)	PMX	0,8	EM	0,9		244,12
150	150	Tournament(4)	PMX	0,6	EM	0,9		244,26
100	150	Tournament(4)	PMX	0,8	EM	0,9		244,3
150	200	Tournament(6)	PMX	0,7	EM	0,9		244,3
150	150	Tournament(6)	PMX	0,8	EM	0,9		244,5
150	200	Tournament(4)	PMX	0,7	EM	0,9		244,5


A melhor combinação acabou com uma média de 243,02 e seguimos com ela para os testes do tamanho da população.

-->Testes ao tamanho da população
Variámos a população e as gerações em:
Population_size: 50, 100, 150, 200, 400
Max_generations: 100, 125, 150, 175, 200

Top 5
Population size:	Max generations:	Selection:	Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:
200	200	Tournament(4)	PMX	0,8	EM	0,9		241,78
400	200	Tournament(4)	PMX	0,8	EM	0,9		241,92
200	175	Tournament(4)	PMX	0,8	EM	0,9		242,12
200	150	Tournament(4)	PMX	0,8	EM	0,9		242,64
400	175	Tournament(4)	PMX	0,8	EM	0,9		242,8

Efetuamos os calculos de Media sobre a População multiplicado pelas Gerações para sabermos a relação direta entre a média do fitness e (Population*Generations)

A melhor combinação em relação ao calculo de Average/(Popupation*Generations) é de pop 100 e ger 200. E a média aumenta de 241,78 para 243,56 o que não é muito por isso vamos optar pela combinação de pop 100 e ger 200.

-->Testes tamanho de torneio
Population_size: 100
Max_generations: 200

Tournament_size: 2, 4, 6, 8, 10

Top 5
Population size:	Max generations:	Selection:	Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
100	200	Tournament(2)	PMX	0,4	EM	0,4		250,34	4,352516513
100	200	Tournament(6)	PMX	0,4	EM	0,4		252,12	6,256644468
100	200	Tournament(4)	PMX	0,4	EM	0,4		252,76	4,667161878
100	200	Tournament(10)	PMX	0,4	EM	0,4		252,94	5,04146804
100	200	Tournament(8)	PMX	0,4	EM	0,4		253,8	5,366563146


Os melhores resultados vieram do torneio(2)
Prosseguindo com o melhor resultado para o próximo teste

-->Testes à recombinação (Recombination, Recombination_probability)

Relativamente à recombinação tentámos alargar a nossa visão relativa às probabilidades, já que os testes perliminares não tinham sido conclusivos.
	Recombination: pmx, ox, cx2
	Recombination_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

Recombination:	Recombination prob,:	Average:
-----------------------------------------------------------
PMX	0,1	251,22
	0,2	250,46
	0,3	250,62
	0,4	250,34
	0,5	250,34
	0,6	249,32
	0,7	246,6
	0,8	248,64
	0,9	250,06
-----------------------------------------------------------
OX	0,1	251,4
	0,2	251,82
	0,3	251,94
	0,4	252,94
	0,5	254,9
	0,6	256,24
	0,7	258,4
	0,8	259,36
	0,9	260
-----------------------------------------------------------
CX2	0,1	252
	0,2	250,6
	0,3	251,3
	0,4	252,78
	0,5	255,54
	0,6	258,1
	0,7	261,56
	0,8	262,76
	0,9	262,44
-----------------------------------------------------------

Top 5 ordenado por fitness:

Recombination:	Recombination prob,:	Average:
PMX				0,7						246,6
PMX				0,8						248,64
PMX				0,6						249,32
PMX				0,9						250,06
PMX				0,4						250,34


Prosseguindo então com o melhor resultado dos testes anteriores para os próximos testes
	Rec.Prob	Average
	PMX	0,7		246,6 *MELHOR RESULTADO*

-->Testes à mutação
	No desenvolvimento do projeto, como extra, criámos algumas mutações extra, então nestes testes fizémos variar as mesmas
	com probabilidades demutação diferentes:
	Mutation: insert, exchange, displacement, simpleinversion, invertionsimple, inversiondisplacement
	Mutation_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Pudemos então afirmar que a mutação que mais se destacou por ter um valor de fitness mais baixo foi a 'EM' com uma
	probabilidade de mutação de 0,4.

Mutation:	Mutation prob,:	Average:
------------------------------------------------
Insert	0,1	253,96
	0,2	251,36
	0,3	249,1
	0,4	249,5
	0,5	249,84
	0,6	251,56
	0,7	252,9
	0,8	253,52
	0,9	255,92
------------------------------------------------
EM	0,1	253,08
	0,2	250,32
	0,3	249,3
	0,4	246,6 	*Melhor Resultado*
	0,5	248,16
	0,6	249,68
	0,7	253,4
	0,8	255,46
	0,9	254,72
------------------------------------------------
DM	0,1	256,26
	0,2	254,7
	0,3	258,52
	0,4	260,54
	0,5	259,7
	0,6	261,3
	0,7	261,68
	0,8	261,38
	0,9	260,92
------------------------------------------------
SIM	0,1	257,98
	0,2	255,66
	0,3	253,54
	0,4	255,76
	0,5	259,16
	0,6	259,48
	0,7	260,56
	0,8	260,84
	0,9	261,68
------------------------------------------------
ISM	0,1	258,96
	0,2	259,8
	0,3	260,24
	0,4	261,22
	0,5	260,4
	0,6	259,84
	0,7	260,34
	0,8	259,96
	0,9	259,98
------------------------------------------------
IDM	0,1	257,5
	0,2	256
	0,3	259,1
	0,4	260,74
	0,5	261,04
	0,6	261,98
	0,7	261,74
	0,8	263,36
	0,9	263,5
------------------------------------------------

Top 5 
Mutation:	Mutation prob,:	Average:
EM			0,4 			246,6
EM			0,5				248,16
Insert		0,3				249,1
EM			0,3				249,3
Insert		0,4				249,5


Assim, depois destes testes assumimos que os melhores parâmetros são:
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
100					200					Tournament(2)	PMX				0,7						EM			0,4					246,6


