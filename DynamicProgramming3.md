-> Longest Increasing Subsequence(striver)

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
-> Longest Increasing Subsequence(chatgpt)

```java
import java.util.*;

public class LIS_RecursionMemo {
    static int[][] memo;

    // Recursive function
    private static int lisHelper(int[] nums, int prevIndex, int currIndex) {
        if (currIndex == nums.length) {
            return 0; // reached end
        }

        if (memo[prevIndex + 1][currIndex] != -1) {
            return memo[prevIndex + 1][currIndex];
        }

        // Option 1: Skip current element
        int notTake = lisHelper(nums, prevIndex, currIndex + 1);

        // Option 2: Take current element (if valid)
        int take = 0;
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
            take = 1 + lisHelper(nums, currIndex, currIndex + 1);
        }

        // Store result in memo table
        memo[prevIndex + 1][currIndex] = Math.max(take, notTake);
        return memo[prevIndex + 1][currIndex];
    }

    // Main function
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        // memo[prevIndex+1][currIndex], prevIndex=-1 shifted by +1
        memo = new int[n + 1][n];
        for (int[] row : memo) Arrays.fill(row, -1);

        return lisHelper(nums, -1, 0);
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println("LIS length: " + lengthOfLIS(nums));
    }
}
```

-> Longest Increasing Subsequence print the sequence(chatgpt)-(same code as above but minor changes)

```java
import java.util.*;

public class LIS_RecMemoPrint {
    static int[][] memo;
    static int[][] choice; // to reconstruct path

    private static int lisHelper(int[] nums, int prevIndex, int currIndex) {
        if (currIndex == nums.length) return 0;

        if (memo[prevIndex + 1][currIndex] != -1)
            return memo[prevIndex + 1][currIndex];

        // Option 1: skip current element
        int notTake = lisHelper(nums, prevIndex, currIndex + 1);

        // Option 2: take current element (if valid)
        int take = 0;
        if (prevIndex == -1 || nums[currIndex] > nums[prevIndex]) {
            take = 1 + lisHelper(nums, currIndex, currIndex + 1);
        }

        // Record best choice
        if (take > notTake) {
            choice[prevIndex + 1][currIndex] = 1; // take
        } else {
            choice[prevIndex + 1][currIndex] = 0; // skip
        }

        return memo[prevIndex + 1][currIndex] = Math.max(take, notTake);
    }

    // Function to reconstruct the LIS sequence using 'choice'
    private static List<Integer> reconstruct(int[] nums, int prevIndex, int currIndex) {
        List<Integer> result = new ArrayList<>();
        while (currIndex < nums.length) {
            int decision = choice[prevIndex + 1][currIndex];
            if (decision == 1) { // take
                result.add(nums[currIndex]);
                prevIndex = currIndex;
            }
            currIndex++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = nums.length;

        memo = new int[n + 1][n];
        choice = new int[n + 1][n];

        for (int[] row : memo) Arrays.fill(row, -1);

        int length = lisHelper(nums, -1, 0);
        List<Integer> seq = reconstruct(nums, -1, 0);

        System.out.println("LIS length: " + length);
        System.out.println("LIS sequence: " + seq);
    }
}
```
