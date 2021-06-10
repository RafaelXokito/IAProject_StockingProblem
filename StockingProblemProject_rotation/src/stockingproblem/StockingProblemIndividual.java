package stockingproblem;

import algorithms.Algorithm;
import algorithms.IntVectorIndividual;

import java.util.ArrayList;

public class StockingProblemIndividual extends IntVectorIndividual<StockingProblem, StockingProblemIndividual> {
    public static final int ROTACAO_VERTICAL = 1;
    //TODO this class might require the definition of additional methods and/or attributes
    private char[][] materialCut;

    private int verticalCuts;
    private int horizontalCuts;

    private int widthMaterialUsed;


    public StockingProblemIndividual(StockingProblem problem, int size) {
        /*
        * Sendo que estamos a trabalhar com a rotação da peça também
        * O genome/cromossoma vai ter de guardar/gerar essa mesma rotação
        * //[gene, rotação, gene, rotação, gene, rotação,...]
        * Sendo que o gene vai receber
        * Existem 4 tipos de rotação 0º, 90º, 180º e 270º
        * A cada rotação (graus) vamos definir um valor agradável (0,1,2,3)
        * 0º -> 0
        * 90º -> 1
        * 180º -> 2
        * 270º -> 3
        *
        * Sendo que a cada gene vamos ter uma rotação, vamos ter então o dobro das
        * posições no cromossoma/genome
        */
        super(problem, size*2);
        int aux;
        int numRotacoes = 4; //temos 4 tipos de rotação (0,1,2,3)
        for (int i = 0, j = 0; i < genome.length; i+=2) {
            aux = Algorithm.random.nextInt(size);
            j = 0;
            do { //[1 2 3 2]

                if(genome[j] == aux && i != 0){
                    i-=2;
                    break;
                }
                genome[i] = aux;
                j+=2;
            }while (j < i);
        }

        for (int i = 1; i < genome.length; i+=2) {

            int auxRotation = 0;
            do {
                auxRotation = Algorithm.random.nextInt(numRotacoes);
            }while(problem.getItems().get(genome[i-1]).getColumns() > problem.getMaterialHeight() && (auxRotation == ROTACAO_VERTICAL || auxRotation == 3));
            genome[i] = auxRotation;
            //System.out.println(genome[i]-1 + " " + auxRotation);

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

        for (int index = 0; index < genome.length; index+=2)
        {
            //Corre todos os items
            Item item = problem.getItems().get(genome[index]);
            Item itemRotated = new Item(item);
            /*System.out.print(item.getId());
            System.out.print(item.getRepresentation());
            System.out.println("\nOriginal: "+item.toString());*/

            int rotationItem = genome[index+1];
            for (int i = 0; i < rotationItem; i++) {
                itemRotated.setMatrix(rotateMatrixBy90Degrees(itemRotated));
            }

            /*System.out.print(item.getId());
            System.out.print(item.getRepresentation());
            System.out.println("\nrotation: "+rotationItem+item.toString());*/
            for (int columnMaterial = 0; columnMaterial < materialCut[0].length; columnMaterial++) {
                //Corre todas as colunas do material, ou até encontrar lugar para meter a peça
                for (int lineMaterial = 0; lineMaterial < materialCut.length; lineMaterial++) {
                    //Corre todas as linhas, ou até encontrar lugar para meter a peça
                    if (checkValidPlacement(itemRotated,lineMaterial,columnMaterial)){
                        //Encontrámos o lugar para meter a peça
                        for (int lineItem = 0; lineItem < itemRotated.getLines(); lineItem++) {
                            for (int columnItem = 0; columnItem < itemRotated.getColumns(); columnItem++) {
                                if (itemRotated.getMatrix()[lineItem][columnItem] != 0) {
                                    materialCut[lineMaterial + lineItem][columnMaterial + columnItem] = itemRotated.getRepresentation();
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
        /*for (int columnMaterial = 0; columnMaterial < materialCut[0].length; columnMaterial++) {
            for (int lineMaterial = 0; lineMaterial < materialCut.length; lineMaterial++) {
                if(materialCut[lineMaterial][columnMaterial]  != 0){
                    widthMaterialUsed = columnMaterial;
                    break;
                }
            }
        }*/
    }

    private int[][] rotateMatrixBy90Degrees(Item item) {

        int colsOfRotatedMatrix = item.getMatrix().length;
        int rowsOfRotatedMatrix = item.getMatrix()[0].length;

        int[][] rotatedItemMatrix = new int[rowsOfRotatedMatrix][colsOfRotatedMatrix];

        for (int i = 0; i < colsOfRotatedMatrix; i++) {
            for (int j = 0; j < rowsOfRotatedMatrix; j++) {
                rotatedItemMatrix[j][(colsOfRotatedMatrix-1)-i] = item.getMatrix()[i][j];
            }
        }
        return rotatedItemMatrix;
    }

    @Override
    public double computeFitness() {
        processMaterialCut();
        fitness = verticalCuts + horizontalCuts + (widthMaterialUsed*2);

        //System.out.println(toString());

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