public class TrendDetector { 
  
     public static boolean hasTrend(String[] S, String[] S_line) { 
         // i percorre S, j percorre S_line 
         int i = 0,  
            j = 0; 
         int n = S.length,  
            m = S_line.length; 
  
        // Enquanto houver elementos em S e ainda não tivermos casado toda S_line: 
         while (i < n && j < m) { 
             // Se o evento atual de S coincide com o da subsequência 
             if (S[i].equals(S_line[j])) { 
                 // avançamos o ponteiro da subsequência 
                 j++; 
             } 
             // sempre avançamos em S 
             i++; 
         } 
         // se j == m, encontramos todos os elementos de S_line 
         return (j == m); 
     } 
  
     public static void main(String[] args) { 
         // Sequência de teste 
         String[] S = { 
             "buy Amazon",  
            "buy Google",  
            "buy Apple",  
            "buy Google",  
            "buy Google",  
            "buy NVIDIA" 
         }; 
         // Caso positivo 
         String[] trend1 = { 
             "buy Google",  
            "buy Apple",  
            "buy Google",  
            "buy NVIDIA" 
         }; 
         // Caso negativo 
         String[] trend2 = { 
             "buy Google",  
            "sell Apple" 
         }; 
  
        // Testes básicos 
         System.out.println("Trend1 é subsequência? " + hasTrend(S, trend1)); // deve imprimir true 
         System.out.println("Trend2 é subsequência? " + hasTrend(S, trend2)); // deve imprimir false 
  
        // Micro‐benchmark: 1 milhão de chamadas 
         long start = System.nanoTime(); 
         for (int k = 0; k < 1_000_000; k++) { 
             hasTrend(S, trend1); 
         } 
         long elapsed = System.nanoTime() - start; 
         System.out.printf("1M execuções em %.2f ms%n", elapsed / 1e6); 
     } 
 } 