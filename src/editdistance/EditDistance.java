/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editdistance;

/**
 *
 * @author Manuel
 */
public class EditDistance {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String str1 = "gato";
        String str2 = "cat";
        System.out.println("Distancia Lev: " + memo(str1, 4, str2, 3));
        System.out.println("Distancia Lev: " + tabu(str1, str2));
        
    }
    
    public static int memo(String str1, int m, String str2, int n) {
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        if(str1.charAt(m-1) == str2.charAt(n-1)) {
            return memo(str1, m-1, str2, n-1);
        } else {
            return 1 + Math.min(memo(str1, m - 1, str2, n) +1, Math.min(memo(str1, m, str2, n-1) +1, memo(str1, m-1, str2, n-1)));
        }
    }
    
    public static int tabu(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        
        int[][] table = new int[m+1][n+1];
        for (int i = 1; i <= m; i++) {
            table[i][0] = i;
        }
        for (int j = 1; j <= n; j++) {
            table[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if(str1.charAt(i-1) == str2.charAt(j-1)) {
                    table[i][j] = table[i-1][j-1];
                } else {
                    table[i][j] = Math.min(table[i-1][j], Math.min(table[i][j-1], table[i-1][j-1]))+1;
                }
            }
        }
        
        return table[m][n];
    }
    
}
