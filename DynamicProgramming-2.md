-> 0/1 knapsack

```java
class Solution {
    public int numSubseq(int[] nums, int target) {
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
