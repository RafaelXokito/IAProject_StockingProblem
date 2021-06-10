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
        int gen1Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int gen2Index;
        do {
            gen2Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (gen1Index==gen2Index);

        int[] genomeCopy = ind.getGenome();
        int[] genomeAux = new int[genomeCopy.length];
        genomeAux[gen2Index] = genomeCopy[gen1Index];

        for (int i = 0, j = 0; i < genomeCopy.length-1; i++, j++) {
            if(j == gen1Index){
                j++;
            }
            if (genomeAux[i] == 0)
                genomeAux[i] = genomeCopy[j];
            else {
                genomeAux[i+1] = genomeCopy[j];
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