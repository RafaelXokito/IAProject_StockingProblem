package stockingproblem;

import algorithms.Algorithm;
import algorithms.IntVectorIndividual;

import java.util.ArrayList;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes
    private char[][] materialCut;

    private int verticalCuts;
    private int horizontalCuts;

    private int widthMaterialUsed;


    public StockingProblemIndividual(StockingProblem problem, int size) {
        super(problem, size);
        int aux;
        for (int i = 0, j; i < size; i++) {
            aux = Algorithm.random.nextInt(size);
            j = 0;
            do { //[1 2 3 2]
                if(genome[j] == aux){
                    i--;
                    break;
                }
                genome[i] = aux;
                j++;
            }while (j < i);
        }
        verticalCuts = 0;
        horizontalCuts = 0;
        widthMaterialUsed = 0;
        materialCut = new char[problem.getMaterialHeight()][problem.getMaxMaterialWidth()];
    }


    public StockingProblemIndividual(StockingProblemIndividual original) {
        super(original);
        this.materialCut = original.materialCut;
        this.horizontalCuts = original.horizontalCuts;
        this.verticalCuts = original.verticalCuts;
        this.widthMaterialUsed = original.widthMaterialUsed;
    }

    //genome: [0,1,4,3,2,5]
    private void processMaterialCut(){
        materialCut = new char[problem.getMaterialHeight()][problem.getMaxMaterialWidth()];
        verticalCuts = 0;
        horizontalCuts = 0;
        widthMaterialUsed = 0;

        ArrayList<Item> items = new ArrayList<>();
        for (int index = 0; index < genome.length; index++) {
            items.add(problem.getItems().get(genome[index]));
        }
        for (Item item :items) { //Corre todos os items
            for (int columnMaterial = 0; columnMaterial < materialCut[0].length; columnMaterial++) {
                //Corre todas as colunas do material, ou até encontrar lugar para meter a peça
                for (int lineMaterial = 0; lineMaterial < materialCut.length; lineMaterial++) {
                    //Corre todas as linhas, ou até encontrar lugar para meter a peça
                    if (checkValidPlacement(item,lineMaterial,columnMaterial)){
                        //Encontrámos o lugar para meter a peça
                        for (int lineItem = 0; lineItem < item.getLines(); lineItem++) {
                            for (int columnItem = 0; columnItem < item.getColumns(); columnItem++) {
                                if (item.getMatrix()[lineItem][columnItem] != 0) {
                                    materialCut[lineMaterial + lineItem][columnMaterial + columnItem] = item.getRepresentation();
                                    if (widthMaterialUsed < columnMaterial + columnItem + 1){
                                        widthMaterialUsed = columnMaterial + columnItem + 1;
                                    }
                                }
                            }
                        }

                        columnMaterial = materialCut[0].length;
                        break;
                    }
                }
            }
        }
        //Saber quantos cortes fazemos
        for (int columnMaterial = 0; columnMaterial < widthMaterialUsed; columnMaterial++) {
            for (int lineMaterial = 0; lineMaterial <= problem.getMaterialHeight()-1; lineMaterial++) {
                if(lineMaterial < problem.getMaterialHeight()-1)
                    if(materialCut[lineMaterial][columnMaterial] != materialCut[lineMaterial+1][columnMaterial]){
                        verticalCuts ++;
                    }
                if(materialCut[lineMaterial][columnMaterial] != materialCut[lineMaterial][columnMaterial+1]){
                    horizontalCuts ++;
                }
            }
        }
    }

    @Override
    public double computeFitness() {
        processMaterialCut();
        fitness = (verticalCuts + horizontalCuts)*0.3 + widthMaterialUsed;
        return fitness;
    }

    private boolean checkValidPlacement(Item item, int lineIndex, int columnIndex) {
        int[][] itemMatrix = item.getMatrix();
        for (int i = 0; i < itemMatrix.length; i++) {
            for (int j = 0; j < itemMatrix[i].length; j++) {
                if (itemMatrix[i][j] != 0) {
                    if ((lineIndex + i) >= materialCut.length
                            || (columnIndex + j) >= materialCut[0].length
                            || materialCut[lineIndex + i][columnIndex + j] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public char[][] getMaterialCut(){
        return materialCut;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\nOrdem das peças: [");
        for (int index = 0; index < genome.length; index++) {
            sb.append(genome[index]+" ");
        }
        sb.append("]");

        int totalCuts = verticalCuts+horizontalCuts;
        sb.append("\nCortes: "+totalCuts+" ["+horizontalCuts+"]"+"["+verticalCuts+"]");
        sb.append("\nLargura de material usado: "+widthMaterialUsed);
        int rows = materialCut.length;
        int columns = materialCut[0].length;
        String str = "\n|";

        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                if(materialCut[i][j] != 0)
                    str += materialCut[i][j] + " ";
                else
                    str += "  ";
            }

            sb.append(str + "|\n");
            str = "|";
        }
        //TODO
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(StockingProblemIndividual i) {
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public StockingProblemIndividual clone() {
        return new StockingProblemIndividual(this);

    }



}