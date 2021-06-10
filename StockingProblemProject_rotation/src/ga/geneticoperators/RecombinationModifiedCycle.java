package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;

import java.util.Arrays;

public class RecombinationModifiedCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {
    //https://www.hindawi.com/journals/cin/2017/7430125/
    //Cycle Crossover Operator
    public RecombinationModifiedCycle(double probability) {
        super(probability);
    }

    private int[] child1, child2, offspring1, offspring2;

    @Override
    public void recombine(I ind1, I ind2) {
        child1 = new int[ind1.getNumGenes()];
        Arrays.fill(child1, 0, ind1.getNumGenes(), -1);
        child2 = new int[ind2.getNumGenes()];
        Arrays.fill(child2, 0, ind1.getNumGenes(), -1);
        offspring1 = new int[ind1.getNumGenes()];
        offspring2 = new int[ind2.getNumGenes()];

        //A primeira posicao do filho1, é a primeira do pai2
        child1[0] = ind2.getGene(0);
        child1[1] = ind2.getGene(1);


        int aux = 0, rept = 2, p;

        for (p = 0; p < child2.length-1; p++) {
            //meter no child2
            aux = setChild2(child2, ind2.getGenome(), ind1.getGenome(), aux, rept, p);
            rept = 2;
            //meter no child1
            aux = findPosition(ind1.getGenome(), ind2.getGenome(), aux);
            if (check_forDuplicates(child1, ind2.getGene(aux))) {
                offspringCreate(child1, ind1.getGenome(), offspring1);
                int value = offspring1[p];
                int rotation = offspring2[p+1];
                for (int index = p; value == -1; ++index){
                    value = offspring1[index % child1.length];
                }
                child1[p+1] = value;
                child1[p+2] = rotation;
                aux = p+1;
                rept = 1;
            }else if(p < child1.length - 1 || child1[p+1] == 0){
                child1[p+1] = ind2.getGene(aux);
                child1[p+2] = ind2.getGene(aux+1);
            }
        }
        setChild2(child2, ind2.getGenome(), ind1.getGenome(), aux, rept, p);

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }

    private int setChild2(int[] child2, int[] parent2, int[] parent1, int aux, int rept, int p) {
        for (int i = 0; i < rept; i++) {
            aux = findPosition(parent2, parent1, aux);
        }
        if (check_forDuplicates(child2, parent2[aux])) {
            offspringCreate(child2, parent2, offspring2);
            int value = offspring2[p];
            int rotation = offspring2[p+1];
            for (int index = p; value == -1; ++index){
                value = offspring2[index % child1.length];
            }
            child2[p] = value;
            child2[p+1] = rotation;
            aux = p;
        }else {
            child2[p] = parent2[aux];
            child2[p+1] = parent2[aux+1];
        }
        return aux;
    }

    private void offspringCreate(int[] child, int[] parent, int[] offspring) {
        int m;
        for (int l = 0; l < child.length; l+=2) {
            for (m = 0; m < parent.length; m+=2) {
                //se for igual, trocar pela mesma posição do pai
                if(parent[m] == child[l]) {
                    offspring[m] = -1;
                    offspring[m+1] = -1;
                    break;
                }
            }
        }
        for (int i = 0; i < child.length; i++) {
            if(offspring[i]!=-1)
                offspring[i] = parent[i];
        }
    }

    private boolean check_forDuplicates(int[] offspring, int value) {
        for (int index = 0; index < offspring.length; index+=2) {
            if ((offspring[index] == value)) {
                return true;
            }
        }
        return false;
    }


    private int findPosition(int[] parent1, int[] parent2, int aux) {
        for (int i = 0; i < parent1.length; i+=2) {
            if (parent1[i] == parent2[aux]) {
                aux = i;
                break;
            }
        }
        return aux;
    }

    @Override
    public String toString(){
        return "CX2";
    }
}