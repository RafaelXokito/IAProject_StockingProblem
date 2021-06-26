-->Testes preliminares à mutação para serem escolhidas 3 mutações "preferidas"
	No primeiro teste - Sem variancia da probabilidade de motação (0.1): (Average)
		Insert
		EM
		IDM
	No segundo teste - Com variancia da probabilidade de mutação (0.1, 0.2, 0.3): (Average)
		Insert - 0.3
		EM - 0.3
		SIM - 0.3

Com base nos testes anteriores pudemos verificar quais as mutações que se sairam melhor e em que probabilidade de mutação.
Com isto passamos para os testes preliminares relativos à recombinação, usámos como base o melhor dos testes anteriores
variando assim os algoritmos de recombinação e a sua probabilidade.

-->Testes preliminares à recombinação para termos preceção/sensibilidade da probabilidade de recombinção
	Com variação da probabilidade entre 0.6, 0.7, 0.8: (Average)
		PMX - 0.8 - 284.42
		OX - 0.6 - 286.08
		CX2 - 0.6 - 286.26

Não achando conclusivos os testes relativos à recombinação, iremos então prosseguir com estes valores para mais alguns testes preliminares
Iremos então realizar mais alguns testes adicionando algumas variações (max_generation, recombination, recombination_probability, mutation_probability)

-->Testes preliminares variados 1
	Podémos confirmar que a probabilidade de recombinação pendeu para baixo, a maior parte dos melhores valores são (0.6 e 0.7)
		Obtivémos como melhor resultado:
			Mínimo: 284.2	PMX	0.7	Insert	0.4
	A diferença entre gerações não foi suficientemente grande, então assumimos que o valor mais baixo (100) já é estável.
	O algoritmo de recombinação que tem conseguido valores mais baixos é o PMX.
	A probabilidade de mutação que alcançou um valor de fitness mais baixo voi 0.7 no entanto o valor que mais aparece é 0.6
	A probabilidade de mutação mantem-se estável entre 0.2 e 0.3.
	Avg Prob Recombinação	0.65
	Avg Prob Mutação	0.283333333

Com base nos parâmetros experenciados nos testes preliminares, procedemos às experiencias de testes gerais.

-->Testes gerais 

Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:	StdDev:
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
150					100					Tournament(2)	PMX				0,6						Insert		0,4					284,06		0,237486842
150					100					Tournament(2)	PMX				0,6						Insert		0,4					284,06		0,237486842
150					100					Tournament(2)	PMX				0,6						Insert		0,4					284,06		0,237486842
150					100					Tournament(4)	PMX				0,8						Insert		0,4					284,12		0,324961536
150					100					Tournament(4)	PMX				0,8						Insert		0,4					284,12		0,324961536
150					100					Tournament(4)	PMX				0,8						Insert		0,4					284,12		0,324961536
150					100					Tournament(2)	PMX				0,6						Insert		0,3					284,14		0,400499688
<<<<<<< Updated upstream

Como podemos observar o melhor resultado obtido foi 284,04 com a Mutation EM (0,3). Mas a Insert também aparece muita vez no Top, por isso não a vamos deixar de lado. A pior foi a DM.


-->Testes ao tamanho da população
Variámos a população e as gerações em:
Population_size: 50, 100, 150, 200
Max_generations: 50, 100, 150, 200

Otendo então os resultados em top (Prob. 0's = 0.5)
------------------------------------------------------------------------------------------------------------------------------------------------------
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:	StdDev:
200					150					Tournament(2)	PMX				0,6						Insert		0,3					284			0
200					150					Tournament(2)	PMX				0,6						EM			0,3					284			0
200					200					Tournament(2)	PMX				0,6						Insert		0,3					284			0
200					200					Tournament(2)	PMX				0,6						EM			0,3					284			0
150					150					Tournament(2)	PMX				0,6						Insert		0,3					284,02		0,14
150					150					Tournament(2)	PMX				0,6						EM			0,3					284,02		0,14
150					200					Tournament(2)	PMX				0,6						Insert		0,3					284,02		0,14
150					200					Tournament(2)	PMX				0,6						EM			0,3					284,02		0,14
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
200					100					Tournament(2)	PMX				0,6						Insert		0,3					284,06		0,237486842
-------------------------------------------------------------------------------------------------------------------------------------------------------
Population size:	Max generations:	Average		Average/(Population*Generations)
pop 150				ger 100				284,04		0,018936
------------------------------------------------------------------------------------

Em relação à media dos resultados, o melhor seria com População 150 e Gerações 100, que são 50 a menos em ambos os campos do melhor resultado a cima. Será que 0,4 a menos de Average vale esses 50 valores a mais de população + 50 valores a mais de MaxGeneration?
Tendo isto em conta, vamos então usar a seguinte combinação:

Population size:	Max generations:	Recombination:	Recombination prob.:	Mutation prob.:
150					100					PMX				0,6						0,3

-->Tamanho do torneio
Variámos o valor do torneio em:
Tournament_size: 2, 4, 6, 8, 10

Otendo então os resultados em top (Prob. 0's = 0.5)
=======
>>>>>>> Stashed changes

Como podemos observar o melhor resultado obtido foi 284,04 com a Mutation EM (0,3). Mas a Insert também aparece muita vez no Top, por isso não a vamos deixar de lado. A pior foi a DM.


-->Testes ao tamanho da população
Variámos a população e as gerações em:
Population_size: 50, 100, 150, 200
Max_generations: 50, 100, 150, 200

Otendo então os resultados em top (Prob. 0's = 0.5)
------------------------------------------------------------------------------------------------------------------------------------------------------
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:	StdDev:
200					150					Tournament(2)	PMX				0,6						Insert		0,3					284			0
200					150					Tournament(2)	PMX				0,6						EM			0,3					284			0
200					200					Tournament(2)	PMX				0,6						Insert		0,3					284			0
200					200					Tournament(2)	PMX				0,6						EM			0,3					284			0
150					150					Tournament(2)	PMX				0,6						Insert		0,3					284,02		0,14
150					150					Tournament(2)	PMX				0,6						EM			0,3					284,02		0,14
150					200					Tournament(2)	PMX				0,6						Insert		0,3					284,02		0,14
150					200					Tournament(2)	PMX				0,6						EM			0,3					284,02		0,14
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
200					100					Tournament(2)	PMX				0,6						Insert		0,3					284,06		0,237486842
-------------------------------------------------------------------------------------------------------------------------------------------------------
Population size:	Max generations:	Average		Average/(Population*Generations)
pop 150				ger 100				284,04		0,018936
------------------------------------------------------------------------------------

Em relação à media dos resultados, o melhor seria com População 150 e Gerações 100, que são 50 a menos em ambos os campos do melhor resultado a cima. Será que 0,4 a menos de Average vale esses 50 valores a mais de população + 50 valores a mais de MaxGeneration?
Tendo isto em conta, vamos então usar a seguinte combinação:

Population size:	Max generations:	Recombination:	Recombination prob.:	Mutation prob.:
150					100					PMX				0,6						0,3

-->Tamanho do torneio
Variámos o valor do torneio em:
Tournament_size: 2, 4, 6, 8, 10

Population size:	Max generations:	Selection:		Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
150					100					Tournament(2)	PMX				0,6						EM			0,3					284,04		0,195959179
150					100					Tournament(4)	PMX				0,6						EM			0,3					284,5		0,854400375
150					100					Tournament(6)	PMX				0,6						EM			0,3					284,6		1,039230485
150					100					Tournament(10)	PMX				0,6						EM			0,3					284,86		0,98
150					100					Tournament(8)	PMX				0,6						EM			0,3					284,88		1,259206099

Os melhores resultados vieram do torneio(2)
Prosseguindo com o melhor resultado para o próximo teste

-->Testes à recombinação (Recombination, Recombination_probability)

Relativamente à recombinação tentámos alargar a nossa visão relativa às probabilidades, já que os testes perliminares não tinham sido conclusivos.
	Recombination: pmx, ox, cx2
	Recombination_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9



Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
--------------------------------------------------------------------------------------------------
CX2				0,2						EM			0,3					284,44		0,725534286
				0,1						EM			0,3					284,52		0,83042158
				0,3						EM			0,3					284,56		0,752595509
				0,4						EM			0,3					285,02		1,067520492
				0,5						EM			0,3					285,52		0,964157663
				0,9						EM			0,3					285,76		0,991160935
				0,6						EM			0,3					285,9		1,170469991
				0,7						EM			0,3					285,98		1,303687079
				0,8						EM			0,3					286			1,2489996
--------------------------------------------------------------------------------------------------
OX				0,3						EM			0,3					284,44		0,697423831
				0,4						EM			0,3					284,5		0,640312424
				0,2						EM			0,3					284,64		0,91126286
				0,1						EM			0,3					284,72		1,020588066
				0,5						EM			0,3					285,06		1,027813213
				0,6						EM			0,3					285,62		1,056219674
				0,7						EM			0,3					285,62		1,093434955
				0,8						EM			0,3					285,66		1,176605286
				0,9						EM			0,3					285,66		1,159482643
--------------------------------------------------------------------------------------------------
PMX				0,6						EM			0,3					284,04		0,195959179
				0,5						EM			0,3					284,2		0,447213595
				0,4						EM			0,3					284,24		0,512249939
				0,7						EM			0,3					284,32		0,507543102
				0,9						EM			0,3					284,52		0,538144962
				0,3						EM			0,3					284,58		0,961041102
				0,8						EM			0,3					284,64		0,74188948
				0,1						EM			0,3					284,78		1,08240473
				0,2						EM			0,3					284,78		1,08240473
--------------------------------------------------------------------------------------------------

Top 5 avaliado por fitness:
Recombination:	Recombination prob,:	Mutation:	Mutation prob,:		Average:	StdDev:
PMX				0,6						EM			0,3					284,04		0,195959179
PMX				0,5						EM			0,3					284,2		0,447213595
PMX				0,4						EM			0,3					284,24		0,512249939
PMX				0,7						EM			0,3					284,32		0,507543102
OX				0,3						EM			0,3					284,44		0,697423831

Prosseguindo então com o melhor resultado dos testes anteriores para os próximos testes
	Rec.Prob	Average
	PMX	0,6		284,04 *MELHOR RESULTADO*

-->Testes à mutação
	No desenvolvimento do projeto, como extra, criámos algumas mutações extra, então nestes testes fizémos variar as mesmas
	com probabilidades demutação diferentes:
	Mutation: insert, exchange, displacement, simpleinversion, invertionsimple, inversiondisplacement
	Mutation_probability: 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9

	Pudemos então afirmar que a mutação que mais se destacou por ter um valor de fitness mais baixo foi a 'EM' com uma
	probabilidade de mutação de 0.3.

Mutation:	Mutation prob.:	Average:
-----------------------------------------
DM			0,2				284,78
			0,1				285,14
			0,3				285,32
			0,6				285,62
			0,7				285,74
			0,4				285,9
			0,5				285,9
			0,9				285,92
			0,8				285,94
-----------------------------------------
EM			0,3				284,04 *Melhor Resultado*
			0,4				284,2
			0,5				284,2
			0,2				284,24
			0,9				284,26
			0,6				284,28
			0,8				284,32
			0,1				284,4
			0,7				284,4
-----------------------------------------
IDM			0,1				284,82
			0,2				285,1
			0,6				285,48
			0,3				285,5
			0,4				285,52
			0,5				285,62
			0,8				285,66
			0,7				285,86
			0,9				285,96
-----------------------------------------
Insert		0,4				284,06
			0,3				284,14
			0,5				284,16
			0,7				284,2
			0,6				284,22
			0,8				284,26
			0,9				284,26
			0,2				284,3
			0,1				284,48
-----------------------------------------
ISM			0,5				285,36
			0,3				285,4
			0,1				285,42
			0,9				285,44
			0,2				285,5
			0,6				285,58
			0,7				285,6
			0,4				285,62
			0,8				285,7
-----------------------------------------
SIM			0,2				284,44
			0,4				284,8
			0,3				284,82
			0,1				284,84
			0,5				284,94
			0,7				285,14
			0,9				285,22
			0,6				285,24
			0,8				285,36
-----------------------------------------


Assim, depois destes testes assumimos que os melhores parâmetros são:
Population size:	Max generations:	Selection:		Recombination:	Recombination prob.:	Mutation:	Mutation prob.:		Average:
150					100					Tournament(2)	PMX				0.6						EM			0,3					284,04
