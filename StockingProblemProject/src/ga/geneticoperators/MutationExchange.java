package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class MutationExchange<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {


    public MutationExchange(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int gen1Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        int gen2Index;
        do {
            gen2Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while (gen1Index==gen2Index);

        int aux = ind.getGene(gen1Index); //guardamos o valor do gene para não ser perdido
        ind.setGene(gen1Index,ind.getGene(gen2Index));
        ind.setGene(gen2Index,aux);


    }

    @Override
    public String toString(){
        return "EM";
    }
}