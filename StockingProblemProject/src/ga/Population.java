package ga;

import algorithms.Algorithm;
import algorithms.Individual;
import algorithms.Problem;

import java.util.ArrayList;
import java.util.List;

public class Population <I extends Individual, P extends Problem<I>>{

    private final List<I> individuals;
    private I best; 

    public Population(int size) {
        individuals = new ArrayList<>(size);
    }

    public Population(int size, P problem) {
        individuals = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
                individuals.add(problem.getNewIndividual());
        }
    }

    public I evaluate(){
        best = getIndividual(0);
        for (I individual : individuals) {
            individual.computeFitness();
            if(individual.compareTo(best) > 0){
                best = individual;
            }
        }
        return best;
    }

    public void replace(int index, I individual){
        individuals.set(index,individual);
    }
    
    public double getAverageFitness() {
        double fitnessSum = 0;
        for (I individual : individuals) {
            fitnessSum += individual.getFitness();
        }
        return fitnessSum / individuals.size();
    }
    
    public void addIndividual(I individual){
        individuals.add(individual);
    }
    
    public I getIndividual(int index) {
        return individuals.get(index);
    }
    
    public I getBest(){
        return best;
    }

    public int getSize() {
        return individuals.size();
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (I individual: individuals) {
            sb.append(individual);
        }
        return sb.toString();
    }
}
