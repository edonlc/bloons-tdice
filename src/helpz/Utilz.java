package helpz;

import java.util.ArrayList;

public class Utilz {

    public static int[][] arrayToMatrix(ArrayList<Integer> list, int ySize, int xSize){
        int[][] newArr = new int[ySize][xSize];

        for (int j = 0; j < newArr.length; j++)
            for (int i = 0; i < newArr[j].length; i++) {
                int index = j * ySize + i;
                newArr[j][i] = list.get(index);
            }
        return newArr;
    }

    public static int[] matrixToVector(int [][] matrix) {
        int[] vector = new int[matrix.length * matrix[0].length];

        for (int j = 0; j < matrix.length; j++)
            for (int i = 0; i < matrix[j].length; i++) {
                int index = j * matrix.length + i;
                vector[index] = matrix[j][i];
            }
        return vector;
    }
}
