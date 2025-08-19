-> Stocks 2

```java

import java.util.*;

class StockProfit {

    static int getMaximumProfitUtil(int[] Arr, int ind, int buy, int n, int[][] dp) {
        if (ind == n) return 0;
        if (dp[ind][buy] != -1) return dp[ind][buy];

        int profit;
        if (buy == 0) {
            profit = Math.max(
                getMaximumProfitUtil(Arr, ind + 1, 0, n, dp),
                -Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 1, n, dp)
            );
        } else {
            profit = Math.max(
                getMaximumProfitUtil(Arr, ind + 1, 1, n, dp),
                Arr[ind] + getMaximumProfitUtil(Arr, ind + 1, 0, n, dp)
            );
        }

        dp[ind][buy] = profit;
        return profit;
    }

    static int getMaximumProfit(int[] Arr, int n) {
        int[][] dp = new int[n][2];
        for (int i = 0; i < n; i++) Arrays.fill(dp[i], -1);
        if (n == 0) return 0;
        return getMaximumProfitUtil(Arr, 0, 0, n, dp);
    }

    public static void main(String[] args) {
        int n = 6;
        int[] Arr = {7, 1, 5, 3, 6, 4};
        System.out.println("The maximum profit that can be generated is " + getMaximumProfit(Arr, n));
    }
}

```
