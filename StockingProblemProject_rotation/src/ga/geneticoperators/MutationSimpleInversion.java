package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationSimpleInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationSimpleInversion(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int cut1,cut2;
        do {
            cut1 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while(cut1 % 2 != 0);
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (cut1 == cut2 || cut2 % 2 != 0);

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }
        cut2++;

        for (int i = 0; i < (cut2-cut1/2); i+=2) {
            int aux = ind.getGene((cut1+i)%ind.getNumGenes());
            int auxRotation = ind.getGene((cut1+i+1)%ind.getNumGenes());

            ind.setGene((cut1+i)%ind.getNumGenes(),ind.getGene((cut2-i-1)%ind.getNumGenes()));
            ind.setGene((cut1+i+1)%ind.getNumGenes(),ind.getGene((cut2-i)%ind.getNumGenes()));

            ind.setGene((cut2-i-1)%ind.getNumGenes(), aux);
            ind.setGene((cut2-i)%ind.getNumGenes(), auxRotation);
        }
    }

    @Override
    public String toString(){
        return "SIM";
    }
}