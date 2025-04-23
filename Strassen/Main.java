public class Main {
    public static void main(String[] args) {
        int[][] A = {
            {1, 2},
            {3, 4}
        };
    
        int[][] B = {
            {5, 6},
            {7, 8}
        };
    
        int[][] result = Strassen.multiply(A, B);
    
        for (int[] row : result) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
