package ga.geneticoperators;

import algorithms.IntVectorIndividual;
import algorithms.Problem;
import ga.GeneticAlgorithm;

public class RecombinationOrder<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    private int[] child1, child2, segment1, segment2, sequence1, sequence2;

    private int cut1;
    private int cut2;

    public RecombinationOrder(double probability) {
        super(probability);
    }

    //ORDER CROSS ORVER OPERATOR
    //https://www.hindawi.com/journals/cin/2017/7430125/
    @Override
    public void recombine(I ind1, I ind2) {
        //TODO
        child1 = new int[ind1.getNumGenes()];
        child2 = new int[ind2.getNumGenes()];
        cut1 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        do {
            cut2 = GeneticAlgorithm.random.nextInt(ind1.getNumGenes());
        } while (cut1 == cut2);
        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        create_Segments(cut1, cut2, ind1, ind2);
        create_Sequences(cut2, ind1, ind2);
        crossOver(child1, ind1);
        crossOver(child2, ind2);

        for (int i = 0; i < ind1.getNumGenes(); i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }

    }

    private boolean check_forDuplicates(int[] offspring, int indexOfElement) {
        for (int index = 0; index < offspring.length; index++) {
            if ((offspring[index] == offspring[indexOfElement]) &&
                    (indexOfElement != index)) {
                return true;
            }
        }
        return false;
    }

    private void create_Segments(int cutPoint1, int cutPoint2, I ind1, I ind2) {
        int capacity_ofSegments = (cutPoint2 - cutPoint1) + 1;
        segment1 = new int[capacity_ofSegments];
        segment2 = new int[capacity_ofSegments];
        int segment1and2Index = 0;
        for (int index = 0; index < ind1.getNumGenes(); index++) {
            if ((index >= cutPoint1) && (index <= cutPoint2)) {
                int x = ind1.getGene(index);
                int y = ind2.getGene(index);
                segment1[segment1and2Index] = x;
                segment2[segment1and2Index] = y;
                segment1and2Index++;
            }
        }
    }

    //starting from the second cut point of one parent, the bits from the
    //other parent are copied in the same order omitting existing bits.
    private void create_Sequences(int cutPoint2, I ind1, I ind2){
        int capacity_ofSequences = ind1.getNumGenes();
        sequence1 = new int[capacity_ofSequences];
        sequence2 = new int[capacity_ofSequences];
        int index = 0;
        int aux = capacity_ofSequences-(cutPoint2+1);
        for (; index < aux; index++) {
            sequence1[index] = ind1.getGene(index+cutPoint2+1);
            sequence2[index] = ind2.getGene(index+cutPoint2+1);
        }
        for (int i = 0; i < capacity_ofSequences-aux; index++, i++) {
            sequence1[index] = ind1.getGene(i);
            sequence2[index] = ind2.getGene(i);
        }
    }

    private void insert_Segments(int[] offspring, int[] segment) {
        int segmentIndex = 0;
        for (int index = 0; index < offspring.length; index++) {
            if ((index >= cut1) && (index <= cut2)) {
                offspring[index] = segment[segmentIndex];
                segmentIndex++;
            }
        }
    }

    public void crossOver(int[] offspring, I ind) {
        int[] sequence = new int[0];
        if (offspring == child1) {
            sequence = sequence2;
            int[] segment = segment1;
            insert_Segments(offspring, segment);
        } else if (offspring == child2) {
            sequence = sequence1;
            int[] segment = segment2;
            insert_Segments(offspring, segment);
        }

        //acrescentar a parte esquerda (depois do cut2) vindo da sequencia
        int index = 0;
        for (int i = 0; i < offspring.length-cut2-1;i++) {
            do {
                offspring[i + cut2 + 1] = sequence[index % sequence.length];
                index++;
            }while (check_forDuplicates(offspring, i + cut2 + 1));
        }

        //acrescentar a parte direita (antes do cut1) vindo da sequencia
        for (int i = 0; i < cut1;i++) {
            do {
                offspring[i] = sequence[index % sequence.length];
                index++;
            }while (check_forDuplicates(offspring, i));
        }
    }

    @Override
    public String toString(){
        //TODO
        return "OX";
    }
}