-> Distinct Subsequences

```java
import java.util.*;

class TUF {
    static int prime = (int) (Math.pow(10, 9) + 7);

    // Function to count the number of distinct subsequences of s1 that are equal to s2
    static int countUtil(String s1, String s2, int ind1, int ind2, int[][] dp) {
        // If we have exhausted s2, there's one valid subsequence (empty string) in s1.
        if (ind2 < 0)
            return 1;
        // If we have exhausted s1 but not s2, there are no valid subsequences.
        if (ind1 < 0)
            return 0;

        // If the result is already computed, return it.
        if (dp[ind1][ind2] != -1)
            return dp[ind1][ind2];

        // If the characters at the current positions match, we can either leave one character from s1
        // or continue to the next character in s1 while staying at the same character in s2.
        if (s1.charAt(ind1) == s2.charAt(ind2)) {
            int leaveOne = countUtil(s1, s2, ind1 - 1, ind2 - 1, dp);
            int stay = countUtil(s1, s2, ind1 - 1, ind2, dp);

            // Add the two possibilities and take modulo prime to avoid integer overflow.
            return dp[ind1][ind2] = (leaveOne + stay) % prime;
        } else {
            // If the characters don't match, we can only continue to the next character in s1.
            return dp[ind1][ind2] = countUtil(s1, s2, ind1 - 1, ind2, dp);
        }
    }

    // Function to calculate the count of distinct subsequences of s1 equal to s2
    static int subsequenceCounting(String s1, String s2, int lt, int ls) {
        // Initialize a DP array to store intermediate results
        int dp[][] = new int[lt][ls];
        for (int rows[] : dp)
            Arrays.fill(rows, -1);

        // Call the recursive helper function to compute the count
        return countUtil(s1, s2, lt - 1, ls - 1, dp);
    }

    public static void main(String args[]) {
        String s1 = "babgbag";
        String s2 = "bag";

        System.out.println("The Count of Distinct Subsequences is " +
                subsequenceCounting(s1, s2, s1.length(), s2.length()));
    }
}
```
-> Edit Distance

```java
import java.util.*;

class TUF {
    // Function to calculate the minimum edit distance between two strings
    static int editDistanceUtil(String S1, String S2, int i, int j, int[][] dp) {
        // Base cases
        if (i < 0)
            return j + 1;
        if (j < 0)
            return i + 1;

        // If the result is already computed, return it
        if (dp[i][j] != -1)
            return dp[i][j];

        // If the characters at the current positions match, no edit is needed
        if (S1.charAt(i) == S2.charAt(j))
            return dp[i][j] = editDistanceUtil(S1, S2, i - 1, j - 1, dp);

        // Minimum of three choices:
        // 1. Replace the character in S1 with the character in S2.
        // 2. Delete the character in S1.
        // 3. Insert the character from S2 into S1.
        else
            return dp[i][j] = 1 + Math.min(editDistanceUtil(S1, S2, i - 1, j - 1, dp),
                    Math.min(editDistanceUtil(S1, S2, i - 1, j, dp), editDistanceUtil(S1, S2, i, j - 1, dp)));
    }

    static int editDistance(String S1, String S2) {
        int n = S1.length();
        int m = S2.length();

        int[][] dp = new int[n][m];
        for (int row[] : dp)
            Arrays.fill(row, -1);

        // Call the recursive helper function
        return editDistanceUtil(S1, S2, n - 1, m - 1, dp);
    }

    public static void main(String args[]) {
        String s1 = "horse";
        String s2 = "ros";

        System.out.println("The minimum number of operations required is: " +
                editDistance(s1, s2));
    }
}
```
