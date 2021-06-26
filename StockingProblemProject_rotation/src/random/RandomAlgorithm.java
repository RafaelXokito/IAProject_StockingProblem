package random;

import algorithms.Algorithm;
import algorithms.AlgorithmEvent;
import algorithms.Individual;
import algorithms.Problem;
import ga.Population;

import java.util.Random;

public class RandomAlgorithm<I extends Individual, P extends Problem<I>> extends Algorithm<I, P> {
    //TODO this class might require the definition of additional methods and/or attributes
    private I ind;
    public RandomAlgorithm(int maxIterations, Random rand) {
        super(maxIterations, rand);
    }

    @Override
    public I run(P problem) {
        //verificar com o professor
        ind = globalBest = problem.getNewIndividual();
        globalBest.computeFitness();
        fireIterationEnded(new AlgorithmEvent(this));
        for (t = 1; t < maxIterations; t+=2) {
            this.ind = problem.getNewIndividual();
            this.ind.computeFitness();
            if (this.ind.compareTo(globalBest) > 0) {
                globalBest = (I) this.ind.clone();
            }
            fireIterationEnded(new AlgorithmEvent(this));
        }
        fireRunEnded(new AlgorithmEvent(this));
        return globalBest;
    }

    @Override
    public double getAverageFitness() {
        return ind.getFitness();
    }
}


