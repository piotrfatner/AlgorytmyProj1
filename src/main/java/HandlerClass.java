import java.util.Random;

public class HandlerClass {
    int[][] initMatrix;

    public void generateMatrix(int widthOfMatrix, int heightOfMatrix) {
        initMatrix = new int[widthOfMatrix][heightOfMatrix];
        Random r = new Random();
        for (int i = 0; i < widthOfMatrix; i++) {
            for (int j = 0; j < heightOfMatrix; j++) {
                initMatrix[i][j] = r.nextInt(10 - 1) + 1;
            }
            System.out.println();
        }
    }

    public void printMatrix(int widthOfMatrix, int heightOfMatrix, int[][] matrix) {
        for (int i = 0; i < widthOfMatrix; i++) {
            for (int j = 0; j < heightOfMatrix; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }


    public void bottomUpMethod(int widthOfMatrix, int heigthOfMatrix, int priceOfCut) {
        printMatrix(widthOfMatrix, heigthOfMatrix, initMatrix);
        int[][] resultMatrix = new int[widthOfMatrix][heigthOfMatrix];
        System.out.println("Bottom up method:\n");

        for (int i = 0; i < widthOfMatrix; i++) {
            for (int j = 0; j < heigthOfMatrix; j++) {
                resultMatrix[i][j] = initMatrix[i][j];
                int tempWidthValue = resultMatrix[i][j];
                int tempHeigthValue = resultMatrix[i][j];

                for (int width = 1; width <= i; width++) {
                    int widthResult = Math.max(tempWidthValue, resultMatrix[width - 1][j] + resultMatrix[i - width][j] - priceOfCut);
                    if (tempWidthValue < widthResult)
                        tempWidthValue = widthResult;
                }

                for (int heigth = 1; heigth <= j; heigth++) {
                    int heigthResult = Math.max(tempHeigthValue, resultMatrix[i][heigth - 1] + resultMatrix[i][j - heigth] - priceOfCut);
                    if (tempHeigthValue < heigthResult)
                        tempHeigthValue = heigthResult;
                }

                resultMatrix[i][j] = Math.max(tempWidthValue, tempHeigthValue);
            }
        }

        System.out.println("Best result:" + resultMatrix[widthOfMatrix - 1][heigthOfMatrix - 1]);
        System.out.println("Result matrix:\n");
        printMatrix(widthOfMatrix, heigthOfMatrix, resultMatrix);

    }


    public int topDownMethod(int[][] resultMatrix, int widthOfMatrix, int heigthOfMatrix, int priceOfCut) {
        /*if(resultMatrix == null){
            resultMatrix = new int[widthOfMatrix][heigthOfMatrix];
            return resultMatrix;
        }*/

        if (widthOfMatrix == 1 && heigthOfMatrix == 1) {
            return initMatrix[widthOfMatrix - 1][heigthOfMatrix - 1];
        }

        int tempWidth = initMatrix[widthOfMatrix-1][heigthOfMatrix-1];
        int tempHeigth = initMatrix[widthOfMatrix-1][heigthOfMatrix-1];

        for (int i = 1; i < widthOfMatrix; i++) {
            int widthResult = Math.max(tempWidth, topDownMethod(resultMatrix, i, heigthOfMatrix, priceOfCut) +
                    topDownMethod(resultMatrix, widthOfMatrix - i, heigthOfMatrix, priceOfCut) - priceOfCut);
            if(widthResult>tempWidth){
                tempWidth = widthResult;
            }
        }
        for (int j = 1; j < heigthOfMatrix; j++) {
            int heigthResult = Math.max(tempWidth, topDownMethod(resultMatrix, widthOfMatrix, j, priceOfCut) +
                    topDownMethod(resultMatrix, widthOfMatrix, heigthOfMatrix-j, priceOfCut)-priceOfCut);
            if(heigthResult>tempHeigth){
                tempHeigth= heigthResult;
            }
        }
        resultMatrix[widthOfMatrix-1][heigthOfMatrix-1] = Math.max(tempWidth,tempHeigth);
        return resultMatrix[widthOfMatrix-1][heigthOfMatrix-1];
    }
}
