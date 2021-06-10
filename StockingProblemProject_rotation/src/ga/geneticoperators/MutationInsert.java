package ga.geneticoperators;

import ga.GeneticAlgorithm;
import algorithms.IntVectorIndividual;
import algorithms.Problem;

public class MutationInsert<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInsert(double probability) {
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

        for(int i = cut2-3; i >= cut1 ; i-=2) {
            int aux = ind.getGene(i + 2);
            ind.setGene(i + 2, ind.getGene(i));
            ind.setGene(i, aux);

            aux = ind.getGene(i + 3);
            ind.setGene(i + 3, ind.getGene(i+1));
            ind.setGene(i+1, aux);
        }

    }


    @Override
    public String toString() {
        return "Insert";
    }
}