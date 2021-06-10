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
        int gen1Index,gen2Index;

        do {
            gen1Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        }while(gen1Index % 2 != 0);
        do {
            gen2Index = GeneticAlgorithm.random.nextInt(ind.getNumGenes());
        } while (gen1Index == gen2Index || gen2Index % 2 != 0);

        int aux = ind.getGene(gen1Index); //guardamos o valor do gene para não ser perdido
        int auxRotation = ind.getGene(gen1Index+1); //guardamos o valor do gene (rotação) para não ser perdido

        ind.setGene(gen1Index,ind.getGene(gen2Index));
        ind.setGene(gen1Index+1,ind.getGene(gen2Index+1));

        ind.setGene(gen2Index,aux);
        ind.setGene(gen2Index+1,auxRotation);
    }

    @Override
    public String toString(){
        return "EM";
    }
}