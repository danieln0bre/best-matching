import java.util.*;

public class Levenshtein {
    
    public static int calculate(String str1, String str2) {
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = j;
        }
        
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
                }
            }
        }
        
        return dp[str1.length()][str2.length()];
    }
    
    public static String findClosestWord(String target, List<String> vocabulary) {
        String closestWord = "";
        int minDistance = Integer.MAX_VALUE;
        
        for (String word : vocabulary) {
            int distance = calculate(target, word);
            if (distance < minDistance) {
                minDistance = distance;
                closestWord = word;
            }
        }
        
        return closestWord;
    }

    public static void main(String[] args) {
        String target = "kitten";
        List<String> vocabulary = Arrays.asList("sitting", "kitchen", "mittens", "biting", "mitten");
        String closestWord = findClosestWord(target, vocabulary);
        
        System.out.println("Closest word to '" + target + "' is: " + closestWord);
    }
}