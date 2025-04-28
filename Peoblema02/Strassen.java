public class Strassen {

    // Método principal para multiplicação de matrizes
    public static int[][] multiply(int[][] A, int[][] B) {
        int n = A.length;

        // Caso base: matriz 1x1
        if (n == 1) {
            int[][] result = { { A[0][0] * B[0][0] } };
            return result;
        }

        int newSize = n / 2;

        // Dividir A e B em submatrizes
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        split(A, A11, 0, 0);
        split(A, A12, 0, newSize);
        split(A, A21, newSize, 0);
        split(A, A22, newSize, newSize);

        split(B, B11, 0, 0);
        split(B, B12, 0, newSize);
        split(B, B21, newSize, 0);
        split(B, B22, newSize, newSize);

        // Cálculo das 7 multiplicações de Strassen
        int[][] M1 = multiply(add(A11, A22), add(B11, B22));
        int[][] M2 = multiply(add(A21, A22), B11);
        int[][] M3 = multiply(A11, subtract(B12, B22));
        int[][] M4 = multiply(A22, subtract(B21, B11));
        int[][] M5 = multiply(add(A11, A12), B22);
        int[][] M6 = multiply(subtract(A21, A11), add(B11, B12));
        int[][] M7 = multiply(subtract(A12, A22), add(B21, B22));

        // Combinar submatrizes no resultado final
        int[][] C11 = add(subtract(add(M1, M4), M5), M7);
        int[][] C12 = add(M3, M5);
        int[][] C21 = add(M2, M4);
        int[][] C22 = add(subtract(add(M1, M3), M2), M6);

        // Montar matriz final
        int[][] C = new int[n][n];
        join(C11, C, 0, 0);
        join(C12, C, 0, newSize);
        join(C21, C, newSize, 0);
        join(C22, C, newSize, newSize);

        return C;
    }

    // Soma de matrizes
    private static int[][] add(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] + B[i][j];
        return result;
    }

    // Subtração de matrizes
    private static int[][] subtract(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                result[i][j] = A[i][j] - B[i][j];
        return result;
    }

    // Copia submatriz
    private static void split(int[][] parent, int[][] child, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++)
            for (int j = 0; j < child.length; j++)
                child[i][j] = parent[i + rowOffset][j + colOffset];
    }

    // Junta submatriz
    private static void join(int[][] child, int[][] parent, int rowOffset, int colOffset) {
        for (int i = 0; i < child.length; i++)
            for (int j = 0; j < child.length; j++)
                parent[i + rowOffset][j + colOffset] = child[i][j];
    }

    // Função principal para teste
    public static void main(String[] args) {
        // Matrizes de teste
        int[][] A = {
            {1, 2},
            {3, 4}
        };

        int[][] B = {
            {5, 6},
            {7, 8}
        };

        // Medir tempo de execução
        long startTime = System.nanoTime();  // Marcar o início do tempo

        // Multiplicar as matrizes usando Strassen
        int[][] result = multiply(A, B);

        long endTime = System.nanoTime();  // Marcar o final do tempo
        long duration = endTime - startTime;  // Calcular a duração

        // Exibir resultado da multiplicação
        System.out.println("Resultado da multiplicação:");
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        // Exibir o tempo de execução
        System.out.printf("Tempo de execução: %.2f ms%n", duration / 1e6);
    }
}
