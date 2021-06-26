Para o dataset Problema5 começámos baseados nos testes preliminares feitos ao dataset Problema1. 
Assim executando testes gerais a este mais precisos. 

Population_size: 50, 100, 150
Max_generations: 50, 75
Selection: tournamen
Tournament_size: 2, 4, 6
Recombination: pmx, ox, cx2
Recombination_probability: 0.6, 0.7, 0.8
Mutation: insert, exchange, displacement
Mutation_probability: 0.2, 0.3, 0.4
Probability_of_1s: 0.05

Top 10

Population size:	Max generations:	Selection:	Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
150	75	Tournament(6)	PMX	0,8	EM	0,4							1056,44	7,743797518
150	75	Tournament(6)	PMX	0,6	EM	0,4							1059,5	7,958014828
150	75	Tournament(4)	PMX	0,8	Insert	0,4							1059,64	7,659660567
150	75	Tournament(4)	PMX	0,8	EM	0,4							1059,66	8,853496484
150	75	Tournament(4)	PMX	0,7	EM	0,4							1060,42	7,472857553
150	75	Tournament(6)	PMX	0,7	EM	0,3							1060,56	7,459651466
150	50	Tournament(6)	PMX	0,8	EM	0,4							1060,94	6,842251092
150	75	Tournament(4)	PMX	0,6	EM	0,4							1061,24	7,522127359
150	75	Tournament(4)	PMX	0,7	EM	0,3							1061,94	6,900463753
150	75	Tournament(4)	PMX	0,7	Insert	0,4							1062,26	7,604761666


A melhor combinação acabou com uma média de 1056,44 e seguimos com ela para os testes do tamanho da população.

-->Testes ao tamanho da população
Variámos a população e as gerações em:
Population_size: 50, 100, 150, 200, 400
Max_generations: 75, 125, 150, 175, 200

Top 5
Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
PMX	0,8	EM	0,4							1048,48	9,07136153
PMX	0,8	EM	0,4							1049,08	8,86304688
PMX	0,8	EM	0,4							1049,94	9,104746015
PMX	0,8	EM	0,4							1050,38	9,010860114
PMX	0,8	EM	0,4							1051,24	9,113857581


150	200						1051,24	0,035041333
Efetuamos os calculos de Media sobre a População multiplicado pelas Gerações para sabermos a relação direta entre a média do fitness e (Population*Generations)

A melhor combinação em relação ao calculo de Average/(Popupation*Generations) é de pop 150 e ger 200. E a média aumenta de 1048,48 para 1051,24 o que não é muito por isso vamos optar pela combinação de pop 150 e ger 200.

-->Testes tamanho de torneio
Population_size: 150
Max_generations: 200

Tournament_size: 2, 4, 6, 8, 10

Population size:	Max generations:	Selection:	Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
150	200	Tournament(6)	PMX	0,8	EM	0,4							1051,24	9,113857581
150	200	Tournament(4)	PMX	0,8	EM	0,4							1055	8,069696401
150	200	Tournament(8)	PMX	0,8	EM	0,4							1055	7,644605941
150	200	Tournament(10)	PMX	0,8	EM	0,4							1055,52	9,024943213
150	200	Tournament(2)	PMX	0,8	EM	0,4							1060,7						12,54312561

Os melhores resultados vieram do torneio(6)
Prosseguindo com o melhor resultado para o próximo teste

-->Testes à recombinação (Recombination, Recombination_probability)

Relativamente à recombinação tentámos alargar a nossa visão relativa às probabilidades, já que os testes perliminares não tinham sido conclusivos.
	Recombination: pmx, ox, cx2
	Recombination_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

Recombination:	Recombination prob,:	Average:
-----------------------------------------------------------
PMX				0,1						1058,18
				0,2						1059,18
				0,3						1058,74
				0,4						1057,9
				0,5						1056,1
				0,6						1055,38
				0,7						1056,68
				0,8						1051,24
				0,9						1053,72
-----------------------------------------------------------
OX				0,1						1059,74
				0,2						1055,56
				0,3						1056,82
				0,4						1058,08
				0,5						1056,94
				0,6						1057,98
				0,7						1057,56
				0,8						1060,3
				0,9						1063,78
-----------------------------------------------------------
CX2				0,1						1059,92
				0,2						1058,9
				0,3						1059,56
				0,4						1057,48
				0,5						1059,68
				0,6						1057,94
				0,7						1059,74
				0,8						1058,3
				0,9						1060,18
-----------------------------------------------------------

Prosseguindo então com o melhor resultado dos testes anteriores para os próximos testes
	Rec.Prob	Average
	PMX	0,8 	1051,24 *MELHOR RESULTADO*


-->Testes à mutação
	No desenvolvimento do projeto, como extra, criámos algumas mutações extra, então nestes testes fizémos variar as mesmas
	com probabilidades demutação diferentes:
	Mutation: insert, exchange, displacement, simpleinversion, invertionsimple, inversiondisplacement
	Mutation_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Pudemos então afirmar que a mutação que mais se destacou por ter um valor de fitness mais baixo foi a 'EM' com uma
	probabilidade de mutação de 0,8.

Mutation:	Mutation prob.:	Average:
-----------------------------------------------------------
Insert 		0,1				1064,78
			0,2				1061,62
			0,3				1060,08
			0,4				1060,5
			0,5				1055,84
			0,6				1057,22
			0,7				1055,06
			0,8				1053,16
			0,9				1051,26
-----------------------------------------------------------
EM			0,1				1065,12
			0,2				1059,86
			0,3				1058,78
			0,4				1051,24
			0,5				1053,82
			0,6				1052,82
			0,7				1047,98
			0,8				1044,7
			0,9				1045,72
-----------------------------------------------------------
DM			0,1				1086,16
			0,2				1083,96
			0,3				1081,24
			0,4				1080,76
			0,5				1075,24
			0,6				1070,66
			0,7				1066,64
			0,8				1078,4
			0,9				1077,96
-----------------------------------------------------------
SIM			0,1				1085,7
			0,2				1082,84
			0,3				1082,5
			0,4				1081,52
			0,5				1078,72
			0,6				1078,04
			0,7				1074,5
			0,8				1068,74
			0,9				1072,46
-----------------------------------------------------------
ISM			0,1				1094,48
			0,2				1095,44
			0,3				1094,02
			0,4				1096,28
			0,5				1093,24
			0,6				1096,04
			0,7				1091,98
			0,8				1094,44
			0,9				1093,48
-----------------------------------------------------------
IDM			0,1				1088,38
			0,2				1085,3
			0,3				1086,48
			0,4				1083,2
			0,5				1082,5
			0,6				1075,06
			0,7				1073,06
			0,8				1086,54
			0,9				1090,9
-----------------------------------------------------------

Assim, depois destes testes assumimos que os melhores parâmetros são:
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150 				200					Tournament(6)	PMX				0.8						EM			0.8					1044,7	


