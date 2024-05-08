public class StrassenMatrixMultiplication {
    public static int[][] multiplyMatrices(int[][] A, int[][] B) {
        int n = A.length;

        if (n <= 2) {
            return standardMatrixMultiplication(A, B);
        }

        // Divide as matrizes em submatrizes menores
        int[][] A11 = new int[n/2][n/2];
        int[][] A12 = new int[n/2][n/2];
        int[][] A21 = new int[n/2][n/2];
        int[][] A22 = new int[n/2][n/2];
        int[][] B11 = new int[n/2][n/2];
        int[][] B12 = new int[n/2][n/2];
        int[][] B21 = new int[n/2][n/2];
        int[][] B22 = new int[n/2][n/2];

        divideMatrix(A, A11, 0, 0);
        divideMatrix(A, A12, 0, n/2);
        divideMatrix(A, A21, n/2, 0);
        divideMatrix(A, A22, n/2, n/2);
        divideMatrix(B, B11, 0, 0);
        divideMatrix(B, B12, 0, n/2);
        divideMatrix(B, B21, n/2, 0);
        divideMatrix(B, B22, n/2, n/2);

        // Calcula os produtos intermediários
        int[][] P1 = multiplyMatrices(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] P2 = multiplyMatrices(addMatrices(A21, A22), B11);
        int[][] P3 = multiplyMatrices(A11, subtractMatrices(B12, B22));
        int[][] P4 = multiplyMatrices(A22, subtractMatrices(B21, B11));
        int[][] P5 = multiplyMatrices(addMatrices(A11, A12), B22);
        int[][] P6 = multiplyMatrices(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] P7 = multiplyMatrices(subtractMatrices(A12, A22), addMatrices(B21, B22));

        // Calcula os elementos da matriz resultante
        int[][] C11 = subtractMatrices(addMatrices(addMatrices(P1, P4), P7), P5);
        int[][] C12 = addMatrices(P3, P5);
        int[][] C21 = addMatrices(P2, P4);
        int[][] C22 = subtractMatrices(addMatrices(addMatrices(P1, P3), P6), P2);

        // Combina os resultados em uma única matriz
        combineMatrices(C11, C12, C21, C22, A);

        return A;
    }

    private static int[][] standardMatrixMultiplication(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }

    private static void divideMatrix(int[][] parent, int[][] child, int iStart, int jStart) {
        for (int i1 = 0, i2 = iStart; i1 < child.length; i1++, i2++) {
            for (int j1 = 0, j2 = jStart; j1 < child.length; j1++, j2++) {
                child[i1][j1] = parent[i2][j2];
            }
        }
    }

    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }

    private static void combineMatrices(int[][] C11, int[][] C12, int[][] C21, int[][] C22, int[][] C) {
        int n = C11.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = C11[i][j];
                C[i][j + n] = C12[i][j];
                C[i + n][j] = C21[i][j];
                C[i + n][j + n] = C22[i][j];
            }
        }
    }

    public static double measureExecutionTime(int n) {
        int[][] matrix1 = generateRandomMatrix(n);
        int[][] matrix2 = generateRandomMatrix(n);

        long startTime = System.nanoTime();
        multiplyMatrices(matrix1, matrix2);
        long endTime = System.nanoTime();

        return (endTime - startTime) / 1e9; // Convertendo para segundos
    }

    private static int[][] generateRandomMatrix(int n) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = (int) (Math.random() * 10); // Preencher com valores aleatórios entre 0 e 9
            }
        }
        return matrix;
    }
}
