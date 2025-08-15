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
