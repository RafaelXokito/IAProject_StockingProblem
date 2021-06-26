package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationDisplacement<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationDisplacement(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {

        //criação dos cortes
        int cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int cut2;
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (cut1==cut2);

        //tamanho da subSequência (subSequência é o tamanho/numero de genes)
        int subSequenceSize = 0;
        if(cut2 >= cut1){
            subSequenceSize = cut2-cut1+1;
        }else{
            subSequenceSize = (ind.getNumGenes()-cut1)+cut2+1;
        }

        //Posição para inserir a sequência
        int position1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int position2 = ((position1+subSequenceSize-1)%ind.getNumGenes());


        if (cut1 == position1)
            return;
        //subSequenceAux -> genes que vamos que não vamos alterar/transformar ou que não
        //pertencem à subSequência selecionada (subSequência -> sequência que provem dos cortes)
        int[] subSequenceAux = new int[ind.getNumGenes()-subSequenceSize];

        //Preenchimento do subSequenceAux
        for (int i = cut2+1, j = 0; j < subSequenceAux.length; i++) {
            if(i < cut1 || i > cut2)
                subSequenceAux[j++] = ind.getGene(i%ind.getNumGenes());
        }
        //mutatedSequence -> Sequência usada para o cromossomo mutado
        //usada para depois copiar para o individuo (ultimo passo deste método)
        int[] mutatedSequence = new int[ind.getNumGenes()];

        //preenchimento parcial da mutatedSequence com a sub-sequência a partir dos cortes
        for (int i = 0; i < subSequenceSize; i++) {
            mutatedSequence[(i+position1)%mutatedSequence.length] = ind.getGene((i+cut1)%mutatedSequence.length);
        }

        //finalização do preenchimento da mutatedSequence com a subSequenceAux
        for (int i = (subSequenceSize + position1 > mutatedSequence.length) ? (subSequenceSize + position1) % mutatedSequence.length : 0, j = 0; i < mutatedSequence.length && j < subSequenceAux.length; i++) {
            if(i < position1 || i > position2){
                mutatedSequence[i] = subSequenceAux[j++];
            }
        }

        //cópia para da mutatedSequence para o individuo (ind)
        for (int i = 0; i < ind.getNumGenes(); i++) {
            ind.setGene(i,mutatedSequence[i]);
            //System.out.print(ind.getGene(i) + " ");
        }
    }

    @Override
    public String toString(){
        return "DM";
    }
}

/*
RASCUNHOS USADOS PARA COMPREENSÃO

			  c1 c2p1p2
6 12 2 7 14 11 5 0 1 8 13 3 9 10 4
cut1: 6 cut2: 7
pos1: 8 pos2: 5
6 12 2 7 14 11 5 0 1 8 13 3 9 10 4

6 12 2 7 14 11 5 0 1 8 13 3 9 10 4
cut1: 6 cut2: 7
pos1: 8 pos2: 9
6 12 2 7 14 11 5 0 1 8 13 3 9 10 4

			   c1c2p1p2
6 12 2 7 14 11 5 0 1 8 13 3 9 10 4
cut1: 6 cut2: 7
pos1: 8 pos2: 9
6 12 2 7 14 11 5 0 1 8 13 3 9 10 4

6 12 7 2 14 11 5 1 0 (c1)8 13 3 (c2)9 10 4
cut1: 10 cut2: 13
pos1: 14 pos2: 0
6 12 7 2 14 11 5 1 0 8 13 3 9 10 4

(4 7 5 9 14 2 10) 8 0 12 1 11 13 3 6
cut1: 0 cut2: 6
before pos1: 4 pos2: 10
after pos1: 7 pos2: 10
4 7 5 9 [p1]14 2 10 8 0 12 [p2]1 11 13 3 6

4 7 5 9 (4 7 5 9 14 2 10) 11 13 3 6
0 0 0 0 (4 7 5 9 14 2 10) 0  0  0 0


8 0 12 1 (4 7 5 9 14 2 10) 11 13 3 6
--------------------
8 0 12 1 (4 7 5 9 14 2 10) 11 13 3 6

[p1]8 0 12 1 4 7 [p2]5 9 14 2 10 11 13 3 6

(4 7 5 9 14 2 10) 11 13 3 6 8 0 12 1

------------------------

(4 7 5 9 14 2 10) 8 0 12 1 11 13 3 6

0 0 0 0 (4 7 5 9 14 2 10) 0  0  0 0
8 0 12 1 11 13 3 6


(13 0 5 2 4 12 7 10 3 8 1 11 9) 6 14
cut1: 0 cut2: 12
pos1: 11 pos2: 8
4 12 7 10 3 8 1 11 9) 6 14 (13 0 5 2

13 0 5 2 4 12 7 10 3 8 1 11 9 6 14
cut1: 0 cut2: 12
pos1: 11 pos2: 8
4 12 7 10 3 8 1 11 9 6 14 13 0 5 2

1 11 2 7 12 0 14 5 6 3) (4 8 13 10 9
cut1: 10 cut2: 9
pos1: 1 pos2: 0
--------------
1 11 2 7 12 0 14 5 (6 3 4) 8 13 10 9
8->10
10-8 = 2+1 = 3
--------------
1 11 2 7 12 0 14 5 6) 3 (4 8 13 10 9
10->8
15-10 = 5 + 8 + 1 = 14
--------------
1 11 2 7 12 0 14 5 6 (3 4) 8 13 10 9
9->10 = 1 + 1 = 2
--------------
1 11 2 7 12 0 14 5 6 3) (4 8 13 10 9
10 -> 9
15-10 = 5 + 9 + 1
--------------
1 11 2 7 12 0 14 5 6 3) (4 8 13 10 9
cut1: 10 cut2: 9
pos1: 1 pos2: 0
3 4 8 13 10 9 1 11 2 7 12 0 14 5 6
-------------
12) 8 (4 14 9 5 1 3 2 0 13 6 7 10 11
cut1: 2 cut2: 0
pos1: 7 pos2: 5
12 8 4 14 9 5) 1 (3 2 0 13 6 7 10 11


12) 8 (4 14 9 5 1 3 2 0 13 6 7 10 11

-> 8
-> 4 14 9 5 1 3 2 0 13 6 7 10 11 12

--> 13 6 7 10 11 12 8 4 14 9 5 1 3 2 0

10 14) 7 11 6 2 3 4 5 8 (13 1 0 9 12
cut1: 10 cut2: 1
pos1: 0 pos2: 7
(13 1 10 9 12 10 14 14) 7 11 6 2 3 4 0


(13 0 5 2 4 12 7 10 3 8 1 11 9) 6 14
cut1: 0 cut2: 12
pos1: 11 pos2: 9

11+13=24%15
13 0 5 2 4 12 7 10 3 8) 1 (11 9 6 14

12) 8 (4 14 9 5 1 3 2 0 13 6 7 10 11
cut1: 2 cut2: 0
pos1: 7 pos2: 6

(7+14)=21%15

14 13 1 5 9 8 7 0 4 10 11 6 3 12 2
cut1: 3 cut2: 8
pos1: 9 pos2: -1
10 11 6 3 12 2 14 13 1 5 9 8 7 0 4

(9+6-1)%15
-------------------------------
5 8 11 14 1 7 10 6 2 0 13 9 12 3 4
cut1: 14 cut2: 8
pos1: 8 pos2: 2
10 6 2 2 0 13 9 12 4 5 8 11 14 1 7

9 4 6 3 5 13 12 0 14 7 11 1 8 10 2
cut1: 3 cut2: 1
pos1: 5 pos2: 3
10 2 9 4 4 3 5 13 12 0 14 7 11 1 8

14 4 1 12 5 10 7 0 2 6 3 9 8 13 11
cut1: 11 cut2: 4
pos1: 9 pos2: 2
1 12 5 5 10 7 0 2 6 9 8 13 11 14 4

14 11 4 6 0 5 12 9 3 7 1 10 2 13 8
cut1: 13 cut2: 9
pos1: 6 pos2: 2
9 3 7 7 1 10 13 8 14 11 4 6 0 5 12

1 8 11 6 14 3 7 9 0 5 13 2 12 4 10
cut1: 13 cut2: 1
pos1: 0 pos2: 3
4 10 1 8 8 11 6 14 3 7 9 0 5 13 2

size=14
subSequence = [8];
mutatedSequence = []
12) 8 (4 14 9 5 1 3 2 0 13 6 7 10 11
cut1: 2 cut2: 0
pos1: 7 pos2: 5
13 6 7 10 11 12 12 4 14 9 5 1 3 2 0
 */