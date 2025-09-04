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
-> number of subsets with sum k

```java
class Solution {
    public int numSubsets(int[] nums, int target) {
        int n = nums.length;
        Integer[][] dp = new Integer[n + 1][target + 1]; // Use Boolean instead of boolean
        return fun(nums, target, n, dp);
    }

    public int fun(int[] nums, int sum, int l, Integer[][] dp) {
        if (sum == 0) return 1;
        if (l == 0) return 0;

        // Check memoization (now valid since dp is Boolean[][])
        if (dp[l][sum] != null) {
            return dp[l][sum];
        }

        if (nums[l - 1] > sum) {
            dp[l][sum] = fun(nums, sum, l - 1, dp);
        } else {
            dp[l][sum] = fun(nums, sum - nums[l - 1], l - 1, dp) + 
                         fun(nums, sum, l - 1, dp);
        }

        return dp[l][sum];
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
-> Longest Common Subsequence( return sequence rather than length)

```java
public class LCSubsequenceMemoArray {

    private static String[][] memo;

    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new String[m + 1][n + 1];
        return lcsHelper(s1, s2, m, n);
    }

    private static String lcsHelper(String s1, String s2, int m, int n) {
        // Check if already computed
        if (memo[m][n] != null) {
            return memo[m][n];
        }
        
        // Base case
        if (m == 0 || n == 0) {
            memo[m][n] = "";
            return "";
        }
        
        String result;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            result = lcsHelper(s1, s2, m - 1, n - 1) + s1.charAt(m - 1);
        } else {
            String lcs1 = lcsHelper(s1, s2, m - 1, n);
            String lcs2 = lcsHelper(s1, s2, m, n - 1);
            result = lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        }
        
        memo[m][n] = result;
        return result;
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        String result = lcs(s1, s2);
        System.out.println("LCS is: " + result);
        System.out.println("Length of LCS is: " + result.length());
        // Output:
        // LCS is: GTAB
        // Length of LCS is: 4
    }
}
```
-> Longest Common Substring (return substring rather than the length)

```java
public class LCSubstringMemoString {

    private static String[][] memo;
    private static String maxSubstring = "";

    public static String lcSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new String[m + 1][n + 1];
        maxSubstring = "";
        
        lcSubstringHelper(s1, s2, m, n);
        
        return maxSubstring;
    }

    private static String lcSubstringHelper(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return "";
        }
        
        // Check if already computed
        if (memo[m][n] != null) {
            return memo[m][n];
        }
        
        String result;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            String prev = lcSubstringHelper(s1, s2, m - 1, n - 1);
            result = prev + s1.charAt(m - 1);
            
            // Update max substring if current is longer
            if (result.length() > maxSubstring.length()) {
                maxSubstring = result;
            }
        } else {
            result = "";
            lcSubstringHelper(s1, s2, m - 1, n);
            lcSubstringHelper(s1, s2, m, n - 1);
        }
        
        memo[m][n] = result;
        return result;
    }

    public static void main(String[] args) {
        String s1 = "ABABC";
        String s2 = "BABCA";
        
        String result = lcSubstring(s1, s2);
        System.out.println("LCSubstring is: " + result);
        System.out.println("Length of LCSubstring is: " + result.length());
        // Output:
        // LCSubstring is: BABC
        // Length of LCSubstring is: 4
    }
}
```

-> Longest Increasing Subsequence

```java
import java.util.Arrays;

class LISMemoization {
    private int[][] dp;

    private int lisHelper(int[] nums, int prevIndex, int currIndex) {
        if (currIndex == nums.length) return 0;

        if (dp[prevIndex + 1][currIndex] != -1) {
            return dp[prevIndex + 1][currIndex];
        }

        // Option 1: Skip current element
        int notTake = lisHelper(nums, prevIndex, currIndex + 1);

        // Option 2: Take current element (if valid)
        int take = 0;
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
            take = 1 + lisHelper(nums, currIndex, currIndex + 1);
        }

        // Memoize and return
        return dp[prevIndex + 1][currIndex] = Math.max(take, notTake);
    }

    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        dp = new int[n + 1][n];  // prevIndex+1 ranges from 0..n
        for (int[] row : dp) Arrays.fill(row, -1);

        return lisHelper(nums, -1, 0);
    }

    // Test
    public static void main(String[] args) {
        LISMemoization solver = new LISMemoization();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("Length of LIS = " + solver.lengthOfLIS(nums));
    }
}
```

->Stickler Thief(House Robber)

```java
class SticklerThief {
    public int FindMaxSum(int arr[], int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);  // initialize with -1
        return rob(arr, n - 1, dp);
    }

    private int rob(int[] arr, int i, int[] dp) {
        if (i < 0) return 0;        // no houses left
        if (dp[i] != -1) return dp[i];  // already computed

        // Option 1: Rob this house, then jump to i-2
        int pick = arr[i] + rob(arr, i - 2, dp);

        // Option 2: Skip this house
        int notPick = rob(arr, i - 1, dp);

        // Store and return best result
        return dp[i] = Math.max(pick, notPick);
    }

    // Test
    public static void main(String[] args) {
        SticklerThief thief = new SticklerThief();
        int arr[] = {5, 5, 10, 100, 10, 5};
        int n = arr.length;
        System.out.println("Maximum loot = " + thief.FindMaxSum(arr, n));
    }
}
```
