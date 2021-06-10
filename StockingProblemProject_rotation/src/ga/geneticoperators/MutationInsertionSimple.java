package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationInsertionSimple<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInsertionSimple(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        //TODO
        int gen1Index,gen2Index;

        do {
            gen1Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while(gen1Index % 2 != 0);
        do {
            gen2Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (gen1Index == gen2Index || gen2Index % 2 != 0);

        int[] genomeCopy = ind.getGenome();
        int[] genomeAux = new int[genomeCopy.length];
        genomeAux[gen2Index] = genomeCopy[gen1Index];

        for (int i = 0, j = 0; i < genomeCopy.length-3; i+=2, j+=2) {
            if(j == gen1Index){
                j+=2;
            }
            if (genomeAux[i] == 0) {
                genomeAux[i] = genomeCopy[j];
                genomeAux[i+1] = genomeCopy[j+1];
            }
            else {
                genomeAux[i+2] = genomeCopy[j];
                genomeAux[i+3] = genomeCopy[j+1];
            }

        }


        for (int i = 0; i < genomeCopy.length; i++) {
            ind.setGene(i,genomeCopy[i]);
        }
    }

    @Override
    public String toString(){
        return "ISM";
    }
}