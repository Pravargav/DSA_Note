-> isSubsetSum

```java
public class SubsetSum {
    public boolean isSubsetSum(int[] nums, int sum) {
        int n = nums.length;
        Boolean[][] dp = new Boolean[n + 1][sum + 1]; // Use Boolean instead of boolean
        return fun(nums, sum, n, dp);
    }

    public boolean fun(int[] nums, int sum, int l, Boolean[][] dp) {
        if (sum == 0) return true;
        if (l == 0) return false;

        // Check memoization (now valid since dp is Boolean[][])
        if (dp[l][sum] != null) {
            return dp[l][sum];
        }

        if (nums[l - 1] > sum) {
            dp[l][sum] = fun(nums, sum, l - 1, dp);
        } else {
            dp[l][sum] = fun(nums, sum - nums[l - 1], l - 1, dp) || 
                         fun(nums, sum, l - 1, dp);
        }

        return dp[l][sum];
    }

    public static void main(String[] args) {
        SubsetSum solver = new SubsetSum();
        int[] nums = {3, 34, 4, 12, 5, 2};
        int sum = 9;
        System.out.println(solver.isSubsetSum(nums, sum)); // Output: true
    }
}

```
-> Longest Common SubSequence

```java
import java.util.Arrays;

public class LongestCommonSubsequenceMemo {

    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize with -1
        }
        return lcsHelper(s1, s2, m, n, memo);
    }

    private static int lcsHelper(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // Return memoized value if already computed
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = 1 + lcsHelper(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = Math.max(
                lcsHelper(s1, s2, m - 1, n, memo),
                lcsHelper(s1, s2, m, n - 1, memo)
            );
        }
        
        return memo[m][n];
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        System.out.println("Length of LCS is: " + lcs(s1, s2));
        // Expected output: 4 (GTAB)
    }
}
```

-> Longest Common Substring

```java
import java.util.Arrays;

public class LongestCommonSubstringMemo {

    private static int maxLen = 0;
    private static int[][] memo;

    public static int lcSubstring(String s1, String s2) {
        maxLen = 0;
        int m = s1.length();
        int n = s2.length();
        memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize with -1
        }
        lcSubstringHelper(s1, s2, m, n);
        return maxLen;
    }

    private static int lcSubstringHelper(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // Return memoized value if already computed
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        
        int res;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            res = 1 + lcSubstringHelper(s1, s2, m - 1, n - 1);
            maxLen = Math.max(maxLen, res);
        } else {
            res = 0;
            lcSubstringHelper(s1, s2, m - 1, n);
            lcSubstringHelper(s1, s2, m, n - 1);
        }
        
        memo[m][n] = res;
        return res;
    }

    public static void main(String[] args) {
        String s1 = "ABABC";
        String s2 = "BABCA";
        
        System.out.println("Length of LCSubstring is: " + lcSubstring(s1, s2));
        // Expected output: 4 (BABC)
    }
}
```
