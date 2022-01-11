package editdistance;

import java.util.HashMap;
import java.util.Map;


/**
 *
 * @author Manuel
 */
public class EditDistance {
    private final static Map<String,Integer> memoMap= new HashMap<>();
    
    public static void main(String[] args) {
        
        String str1 = "sunday";
        String str2 = "saturday";
        int m=str1.length();
        int n=str2.length();
        int memoRes=memo(str1,m , str2, n);
        System.out.println("Distancia Memoization: " + memoRes);
        System.out.println("Distancia Tabulation: " + tabu(str1, str2));
        
    }
    
    public static int memo(String str1, int m, String str2, int n) {
        String key= Integer.toString(m)+""+Integer.toString(n);
        if(EditDistance.memoMap.containsKey(key)){
            return EditDistance.memoMap.get(key);
        }
        if(m == 0) {
            return n;
        }
        if(n == 0) {
            return m;
        }
        if(str1.charAt(m-1) == str2.charAt(n-1)) {
            EditDistance.memoMap.put(key, memo(str1, m-1, str2, n-1));
            return EditDistance.memoMap.get(key);
        }
        EditDistance.memoMap.put(key, 1+Math.min(memo(str1, m - 1, str2, n),Math.min(memo(str1, m, str2, n-1), memo(str1, m-1, str2, n-1))));
        return (EditDistance.memoMap.get(key));
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
