Para o dataset Problema2 começámos baseados nos testes preliminares feitos ao dataset Problema1. 
Assim executando testes gerais a este mais precisos. 

-->Testes Gerais ao dataset Problema2
	Com isto pudémos concluir que por ser um dataset mais pequeno não havia muita descrepância de fitness. No entanto começamos a ver 
	certas combinações a destacarem-se por aparecerem mais frequêntemente com valores mais baixos.

	Apresento então o top 10 resultados ordenados por Average(fitness):
	(Devido a algum problema de configuração o desvio padrão não está no formáto desejado.)

	Prob 0's	Population size:	Max generations:	Selection:		Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
	0			150					150					Tournament(4)	PMX				0,8						EM			0,4					305,22		5.185.711.137.346.540
	0.05		150					150					Tournament(4)	PMX				0,8						EM			0,4					305,22		5.185.711.137.346.540
	0.01		150					150					Tournament(4)	PMX				0,8						EM			0,4					305,22		5.185.711.137.346.540
	0			150					150					Tournament(6)	PMX				0,8						EM			0,4					305,38		4.922.966.585.301.990
	0.05		150					150					Tournament(6)	PMX				0,8						EM			0,4					305,38		4.922.966.585.301.990
	0.01		150					150					Tournament(6)	PMX				0,8						EM			0,4					305,38		4.922.966.585.301.990
	0			100					150					Tournament(2)	PMX				0,6						EM			0,4					305,8		4.715.930.449.020.630
	0.05		100					150					Tournament(2)	PMX				0,6						EM			0,4					305,8		4.715.930.449.020.630
	0.01		100					150					Tournament(2)	PMX				0,6						EM			0,4					305,8		4.715.930.449.020.630

Seguimos então com o melhor valor dos resultados a cima para os testes do tamanho da população

-->Testes ao tamanho da população
	Variámos a população e as gerações em:
	Population_size: 50, 100, 200, 400
	Max_generations: 25, 50, 100, 200

	Por lápso variámos também a mutação
	Mutation: insert, exchange, displacement

	Otendo então os resultados em top (Prob. 0's = 0.5)
	Population size:	Max generations:	Selection:		Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
	400					200					Tournament(4)	PMX				0,8						EM			0,4					300,26		5.436.211.916.399.140 *MELHOR RESULTADO*
	400					100					Tournament(4)	PMX				0,8						EM			0,4					301,76		5.512.023.222.012.040
	400					200					Tournament(4)	PMX				0,8						Insert		0,4					302,42		4.976.303.849.243.930
	400					100					Tournament(4)	PMX				0,8						Insert		0,4					303,38		5.560.179.853.206.180
	200					200					Tournament(4)	PMX				0,8						EM			0,4					303,6		4.878.524.367.060.180
	200					100					Tournament(4)	PMX				0,8						EM			0,4					305,18		4.869.045.080.916.790
	400					50					Tournament(4)	PMX				0,8						EM			0,4					305,9		5.790.509.476.721.360
	200					200					Tournament(4)	PMX				0,8						Insert		0,4					306,82		5.673.411.672.001.240
	100					200					Tournament(4)	PMX				0,8						EM			0,4					306,88		4.541.541.588.491.730 

	Efetuamos os calculos de Media sobre a População multiplicado pelas Gerações
	Para sabermos a relação direta entre a média do fitness e (Population*Generations)

	Population size:	Max generations:	Average:	Average/(Population*Generations)
	400					200					300,26		0,00375325
	400					100					301,76		0,007544
	400					200					302,42		0,00378025
	400					100					303,38		0,0075845
	200					200					303,6		0,00759
	200					100					305,18		0,015259
	400					50					305,9		0,015295
	200					200					306,82		0,0076705
	100					200					306,88		0,015344 *MELHOR RESULTADO*

Prosseguindo com o melhor resultado desta ultima abordagem para o próximo teste

-->Testes ao tamanho do torneio
	Variámos 
	Selection: tournament
	Tournament_size: 2, 4, 6, 8, 10

	Os resultados deste teste prováram aquilo que temos vindo a apercebernos.
	Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:	StdDev:
	100					200					Tournament(4)	PMX				0,8						EM			0,4					306,88		4.541.541.588.491.730
	100					200					Tournament(8)	PMX				0,8						EM			0,4					307,06		5.711.076.956.231.630
	100					200					Tournament(2)	PMX				0,8						EM			0,4					308,14		7.249.855.170.967.210
	100					200					Tournament(10)	PMX				0,8						EM			0,4					308,34		4.091.992.179.855.670
	100					200					Tournament(6)	PMX				0,8						EM			0,4					308,76		481.896.254.395.072

Os melhores resultados vieram do torneio(4)
Prosseguindo com o melhor resultado para o próximo teste

-->Testes à recombinação (Recombination, Recombination_probability)
	Relativamente à recombinação tentámos alargar a nossa visão relativa às probabilidades, já que os testes perliminares não tinham sido conclusivos.
	Recombination: pmx, ox, cx2
	Recombination_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Ao concluir estes testes pudémos agora dizer com certezas que a recombinação que mais se destacou pelos mellhores resultados foi a 'pmx',
	relativamente à probabilidade de recombinação conseguimos afirmar que valores [0.2, 0.7] destacaram-se na generalidade das recombinações,
	no entanto, na recombinação pmx, a que obteve melhores resultados, os melhores valores mantiveram-se entre [0.6, 0.9] 

	Rec.Prob	Average
		0,1		307,4
		0,2		307,74
		0,3		307,76
		0,4		308,08
	PMX	0,5		307,5
		0,6		305,36 *MELHOR RESULTADO*
		0,7		306,58
		0,8		306,88
		0,9		306,24
	-------------------
		0,1		308,78
		0,2		307,3
		0,3		307,52
		0,4		308,28
	OX	0,5		309,18
		0,6		311,08
		0,7		310,64
		0,8		313,6
		0,9		317,2
	-------------------
		0,1		308,78
		0,2		307,58
		0,3		309,88
		0,4		307,38
	CX2	0,5		308,18
		0,6		308,4
		0,7		307,44
		0,8		309,06
		0,9		314,34

	TOP 10 Ordenado por fitness

	Recombination:	Recombination prob.:	Average:
	PMX				0,6						305,36
	PMX				0,9						306,24
	PMX				0,7						306,58
	PMX				0,8						306,88
	OX				0,2						307,3
	CX2				0,4						307,38
	PMX				0,1						307,4
	CX2				0,7						307,44
	PMX				0,5						307,5
	OX				0,3						307,52

Prosseguindo então com o melhor resultado dos testes anteriores para os próximos testes
	Rec.Prob	Average
	PMX	0,6		305,36 *MELHOR RESULTADO*

-->Testes à mutação
	No desenvolvimento do projeto, como extra, criámos algumas mutações extra, então nestes testes fizémos variar as mesmas
	com probabilidades demutação diferentes:
	Mutation: insert, exchange, displacement, simpleinversion, invertionsimple, inversiondisplacement
	Mutation_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Pudemos então afirmar que a mutação que mais se destacou por ter um valor de fitness mais baixo foi a 'EM' com uma
	probabilidade de mutação de 0.8.
	No geral a mutação que obteve valores mais baixos de fitness foi a 'EM', em qualquer probabilidade superou todas outras.

				Average:
			0,1	316,08
			0,2	313,72
			0,3	314,76
			0,4	313,34
	Insert	0,5	311,46
			0,6	308,56
			0,7	306,92
			0,8	305,84
			0,9	305,58
	------------------
			0,1	313,14
			0,2	309,56
			0,3	308,68
			0,4	305,36
	EM		0,5	306,78
			0,6	305,06
			0,7	301,6
			0,8	300,08 *MELHOR RESULTADO*
			0,9	302,14
	------------------
			0,1	323,62
			0,2	322,14
			0,3	321,32
			0,4	319,56
	DM		0,5	316,8
			0,6	315,58
			0,7	319
			0,8	319,5
			0,9	320,78
	------------------
			0,1	323,44
			0,2	321,86
			0,3	320,98
			0,4	320,38
	SIM		0,5	319,02
			0,6	317,2
			0,7	314,2
			0,8	317,9
			0,9	319,34
	------------------
			0,1	327,58
			0,2	326,62
			0,3	327,92
			0,4	328,4
	ISM		0,5	327,86
			0,6	327,82
			0,7	328,88
			0,8	327,18
			0,9	327,74
	------------------
			0,1	323,92
			0,2	322,58
			0,3	321,3
			0,4	319,76
	IDM		0,5	318,04
			0,6	317,22
			0,7	320,24
			0,8	322,16
			0,9	321,96

Assim, depois destes testes assumimos que os melhores parâmetros são:
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
100					200					Tournament(4)	PMX				0.6						EM			0.8					300,08

