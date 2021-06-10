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




