import org.jfree.data.xy.XYSeries;

public class MatrixMultiplicationTimePlot {
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int n = matrix1.length;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static double measureExecutionTime(int n) {
        int[][] matrix1 = generateRandomMatrix(n);
        int[][] matrix2 = generateRandomMatrix(n);

        long startTime = System.nanoTime();
        multiplyMatrices(matrix1, matrix2);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e9;
    }

    public static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }
}
