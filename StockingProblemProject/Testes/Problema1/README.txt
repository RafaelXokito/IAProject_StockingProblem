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
