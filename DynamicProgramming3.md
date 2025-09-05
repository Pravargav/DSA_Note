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
