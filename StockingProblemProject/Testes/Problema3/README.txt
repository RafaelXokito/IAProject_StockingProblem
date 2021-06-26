Para o dataset Problema3 começámos baseados nos testes preliminares feitos ao dataset Problema1. 
Assim executando testes gerais a este mais precisos. 

-->Testes Gerais ao dataset Problema3
Population_size: 50, 100, 150
Max_generations: 125, 175
Tournament_size: 2, 4, 6
Recombination: pmx, ox, cx2
Recombination_probability: 0.6, 0.7, 0.8
Probability_of_1s: 0, 0.05, 0.01
Top 10
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150					175					Tournament(2)	PMX				0,6						EM			0,4					339,48
150					175					Tournament(2)	PMX				0,6						EM			0,4					339,48
150					175					Tournament(2)	PMX				0,6						EM			0,4					339,48
100					175					Tournament(2)	PMX				0,6						EM			0,4					340,2
100					175					Tournament(2)	PMX				0,6						EM			0,4					340,2
100					175					Tournament(2)	PMX				0,6						EM			0,4					340,2
150					175					Tournament(2)	PMX				0,6						EM			0,3					341,32
150					175					Tournament(2)	PMX				0,6						EM			0,3					341,32
150					175					Tournament(2)	PMX				0,6						EM			0,3					341,32
150					175					Tournament(2)	PMX				0,6						Insert		0,3					341,52

A melhor combinação acabou com uma média de 339,48 e seguimos com ela para os testes do tamanho da população.

-->Testes ao tamanho da população
Variámos a população e as gerações em:
Population_size: 50, 100, 150, 200, 400
Max_generations: 100, 125, 150, 175

Top 5
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150					175					Tournament(2)	PMX				0,6						EM			0,4					339,48
100					175					Tournament(2)	PMX				0,6						EM			0,4					340,2
100					150					Tournament(2)	PMX				0,6						EM			0,4					341
150					150					Tournament(2)	PMX				0,6						EM			0,4					341,34
200					175					Tournament(2)	PMX				0,6						EM			0,4					341,72

Efetuamos os calculos de Media sobre a População multiplicado pelas Gerações para sabermos a relação direta entre a média do fitness e (Population*Generations)

A melhor combinação em relação ao calculo de Average/(Popupation*Generations) é de pop 50 e ger 100. Mas a média aumenta de 339,48 para 348,06 por isso vamos optar pela combinação de pop 150 e ger 175.

-->Testes tamanho de torneio

Population_size: 150
Max_generations: 175

Tournament_size: 2, 4, 6, 8, 10

Top 5
Population size:	Max generations:	Selection:		Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
150					175					Tournament(2)	PMX				0,4						EM			0,4					340,74		4,983211816
150					175					Tournament(10)	PMX				0,4						EM			0,4					343,5		6,862215386
150					175					Tournament(6)	PMX				0,4						EM			0,4					343,76		7,309062867
150					175					Tournament(4)	PMX				0,4						EM			0,4					344,8		6,705221846
150					175					Tournament(8)	PMX				0,4						EM			0,4					344,98		5,240190836


Os melhores resultados vieram do torneio(2)
Prosseguindo com o melhor resultado para o próximo teste


-->Testes à recombinação (Recombination, Recombination_probability)

Relativamente à recombinação tentámos alargar a nossa visão relativa às probabilidades, já que os testes perliminares não tinham sido conclusivos.
	Recombination: pmx, ox, cx2
	Recombination_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9


Recombination:	Recombination prob,:	Average:
--------------------------------------------------------------------------------------------------
PMX				0,1						344,24
				0,2						342,7
				0,3						342,38
				0,4						340,74
				0,5						341,02
				0,6						339,48
				0,7						343,1
				0,8						351,58
				0,9						356,4
--------------------------------------------------------------------------------------------------
OX				0,1						344,04
				0,2						344,72
				0,3						346,56
				0,4						350,7
				0,5						352,28
				0,6						355,6
				0,7						357,78
				0,8						359,78
				0,9						359,92
--------------------------------------------------------------------------------------------------
CX2				0,1						345,7
				0,2						345,6
				0,3						347,88
				0,4						352,8
				0,5						355,92
				0,6						358,68
				0,7						361,26
				0,8						362,48
				0,9						363,5
--------------------------------------------------------------------------------------------------

Top5 avaliado por fitness:
Recombination:	Recombination prob,:	Average:
PMX				0,6						339,48
PMX				0,4						340,74
PMX				0,5						341,02
PMX				0,3						342,38
PMX				0,2						342,7

Prosseguindo então com o melhor resultado dos testes anteriores para os próximos testes
	Rec.Prob	Average
	PMX	0,6		339,48 *MELHOR RESULTADO*

-->Testes à mutação
	No desenvolvimento do projeto, como extra, criámos algumas mutações extra, então nestes testes fizémos variar as mesmas
	com probabilidades demutação diferentes:
	Mutation: insert, exchange, displacement, simpleinversion, invertionsimple, inversiondisplacement
	Mutation_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Pudemos então afirmar que a mutação que mais se destacou por ter um valor de fitness mais baixo foi a 'EM' com uma
	probabilidade de mutação de 0,4.

Mutation:	Mutation prob,:	Average:
-----------------------------------------
Insert		0,1				349
			0,2				344,58
			0,3				341,52
			0,4				344,88
			0,5				348,34
			0,6				349,46
			0,7				351,62
			0,8				352,04
			0,9				353,32
-----------------------------------------
EM			0,1				345,46
			0,2				343,14
			0,3				341,32
			0,4				339,48 *Melhor Resultado*
			0,5				342,3
			0,6				347,78
			0,7				350,9
			0,8				351,84
			0,9				352,74
-----------------------------------------
DM			0,1				354,84
			0,2				352,32
			0,3				358,9
			0,4				360,46
			0,5				361,02
			0,6				361,12
			0,7				361,76
			0,8				361,94
			0,9				361,6
-----------------------------------------
SIM			0,1				356,02
			0,2				354,18
			0,3				352,86
			0,4				357,84
			0,5				359,56
			0,6				360,68
			0,7				361,2
			0,8				360,92
			0,9				361,1
-----------------------------------------
ISM			0,1				357,26
			0,2				359,62
			0,3				358,92
			0,4				358,74
			0,5				358,78
			0,6				358,66
			0,7				358,54
			0,8				359,46
			0,9				359,56
-----------------------------------------
IDM			0,1				356,74
			0,2				353,2
			0,3				359,6
			0,4				362,12
			0,5				361,94
			0,6				362,46
			0,7				362,56
			0,8				363,48
			0,9				364,14


Assim, depois destes testes assumimos que os melhores parâmetros são:
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150					175					Tournament(2)	PMX				0,6						EM			0,4					339,48