package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationInversionDisplacement<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInversionDisplacement(double probability) {
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


        //se o corte for igual à posição não existe alteração nos genes
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
        //invertedSubSequence -> subSequência invertida
        int[] invertedSubSequence = new int[subSequenceSize];

        //preenchimento da invertedSubSequence
        for (int i = cut2, j = 0; j < subSequenceSize; i--, j++) {
            if(i < 0){
                i = ind.getNumGenes()-1;
            }
            invertedSubSequence[j] = ind.getGene(i);
        }

        //preenchimento parcial da mutatedSequence com a invertedSubSequence
        for (int i = 0; i < subSequenceSize; i++) {
            mutatedSequence[(i+position1)%mutatedSequence.length] = invertedSubSequence[i];
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
        return "IDM";
    }
}

/*
RASCUNHOS USADOS PARA COMPREENSÃO


invertedSubSequence = [0 8 14 9 5 10 12 13 1]
3 2 6 7 4 11 (1 13 12 10 5 9 14 8 0)
cut1: 6 cut2: 14
pos1: 4 pos2: 12
3 2 6 7 0 8 14 9 5 10 12 13 1 4 11

3 2 6 7 4 11 1 13 12 10 5 9 14 8 0
cut1: 6 cut2: 14
pos1: 4 pos2: 12
3 2 6 7 0 8 14 9 5 10 12 13 1 4 11


9 8 7 6 5 4 3 2 1 0 14 13 12 11 10
*/