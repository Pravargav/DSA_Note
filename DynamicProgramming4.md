
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
