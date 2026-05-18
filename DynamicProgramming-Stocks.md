-> Stocks 1- One transaction 

```java
class Solution {
    public int maxProfit(int[] prices) {
        int min=prices[0];
        int minarr[]=new int[prices.length];
        for(int i=0;i<prices.length;i++){
            if(min>prices[i]){
               min=prices[i];
            }
            minarr[i]=min;
        }
        for(int i=0;i<prices.length;i++){
            System.out.println(minarr[i]);
        }
        int profit[]=new int[prices.length];
        for(int i=1;i<profit.length;i++){
            profit[i]=prices[i]-minarr[i-1];
        }
        for(int i=0;i<profit.length;i++){
            System.out.println(profit[i]);
        }
        int max=0;
        for(int i=0;i<profit.length;i++){
            if(profit[i]>max){
                max=profit[i];
            }
        }
        return max;
    }
}
```


-> Stocks 2 - infinite transactions

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
-> Stocks 3- 2 transactions

```java
import java.util.Arrays;

class StockProfit {
    static int getAns(int[] Arr, int n, int ind, int buy, int cap, int[][][] dp) {
        if (ind == n || cap == 0)
            return 0;

        if (dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit;

        if (buy == 0) {
            profit = Math.max(
                    getAns(Arr, n, ind + 1, 0, cap, dp),
                    -Arr[ind] + getAns(Arr, n, ind + 1, 1, cap, dp)
            );
        } else {
            profit = Math.max(
                    getAns(Arr, n, ind + 1, 1, cap, dp),
                    Arr[ind] + getAns(Arr, n, ind + 1, 0, cap - 1, dp)
            );
        }

        return dp[ind][buy][cap] = profit;
    }

    static int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getAns(prices, n, 0, 0, 2, dp);
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println("The maximum profit that can be generated is " + maxProfit(prices));
    }
}
```

-> Stocks 4- k transactions allowed

```java
import java.util.Arrays;

public class StockBuySell {

    public static int maximumProfit(int[] prices, int n, int k) {
        int[][][] dp = new int[n][2][k + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return getAns(prices, n, 0, 0, k, dp);
    }

    public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
        if (ind == n || cap == 0) {
            return 0;
        }

        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }

        int profit;

        if (buy == 0) {
            profit = Math.max(
                    getAns(prices, n, ind + 1, 0, cap, dp),
                    -prices[ind] + getAns(prices, n, ind + 1, 1, cap, dp)
            );
        } else {
            profit = Math.max(
                    getAns(prices, n, ind + 1, 1, cap, dp),
                    prices[ind] + getAns(prices, n, ind + 1, 0, cap - 1, dp)
            );
        }

        dp[ind][buy][cap] = profit;
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        int k = 2;

        System.out.println(
            "The maximum profit that can be generated is " +
            maximumProfit(prices, n, k)
        );
    }
}
```
-> Stocks 5(both normal and short selling leetcode 3573)(Rithwik solution)

```java
class Solution {
    long recur(int prices[], int i, int b, int k, long dp[][][]) {
        if (i >= prices.length||k <= 0)
            return (b == 0) ? 0L : (long) -1e18;


        if (dp[i][b][k] != Long.MIN_VALUE)
            return dp[i][b][k];

        long skip = recur(prices, i + 1, b, k, dp);
        long ans;

        if (b == 0) 
        {
            long openLong = recur(prices, i + 1, 2, k, dp) - prices[i];
            long openShort = recur(prices, i + 1, 1, k, dp) + prices[i];
            ans = Math.max(skip, Math.max(openLong, openShort));
        }
        else if (b == 1) 
        {
            ans = Math.max(skip, recur(prices, i + 1, 0, k - 1, dp) - prices[i]);
        } 
        else 
        {
            ans = Math.max(skip, recur(prices, i + 1, 0, k - 1, dp) + prices[i]);
        }

        return dp[i][b][k] = ans;
    }

    public long maximumProfit(int[] prices, int k) {  
        int n = prices.length;
        long[][][] dp = new long[n][3][k + 1];
        for (long[][] a : dp) {
            for (long[] b : a)
                Arrays.fill(b, Long.MIN_VALUE);
        }
        return recur(prices, 0, 0, k, dp); 
    }
}
```

-> Stocks with fees - 714. add "-fee"

-> Stocks with cooldown - 309. replace idx+1 with idx+2 and add || idx==n+1

##### Example notes for reference(not actual problems)

````markdown

-> 1. Normal Stock Buy/Sell (BUY -> SELL)

```java
class Solution {

    public int maxProfit(int[] prices, int k) {

        int n = prices.length;

        // dp[index][buy][cap]
        Integer[][][] dp = new Integer[n][2][k + 1];

        // Start from day 0
        // buy = 1 means we can BUY now
        return solve(0, 1, k, prices, dp);
    }

    int solve(int ind, int buy, int cap,
              int[] prices, Integer[][][] dp) {

        // No days left OR no transactions left
        if (ind == prices.length || cap == 0)
            return 0;

        // Memoization
        if (dp[ind][buy][cap] != null)
            return dp[ind][buy][cap];

        int profit;

        // BUY state
        if (buy == 1) {

            // Option 1: Skip buying
            int skip = solve(ind + 1, 1, cap, prices, dp);

            // Option 2: Buy stock
            int take = -prices[ind]
                    + solve(ind + 1, 0, cap, prices, dp);

            profit = Math.max(skip, take);
        }

        // SELL state
        else {

            // Option 1: Skip selling
            int skip = solve(ind + 1, 0, cap, prices, dp);

            // Option 2: Sell stock
            int sell = prices[ind]
                    + solve(ind + 1, 1, cap - 1, prices, dp);

            profit = Math.max(skip, sell);
        }

        return dp[ind][buy][cap] = profit;
    }
}
```

---

-> 2. Short Selling Approach 1 (Reverse Traversal Trick)

-> Core idea:
Traverse from n-1 to 0
Use MIN instead of MAX
Final answer uses abs()

Sequence becomes: SELL -> BUY BACK

```java
class Solution {

    public long maximumProfit(int[] prices, int k) {

        int n = prices.length;

        Integer[][][] dp = new Integer[n][2][k + 1];

        // Start from last index
        // buy = 1 means we can SHORT SELL now
        int ans = solve(n - 1, 1, k, prices, dp);

        // Internally answer becomes negative
        // convert to positive
        return Math.abs(ans);
    }

    int solve(int ind, int buy, int cap,
              int[] prices, Integer[][][] dp) {

        // No days left OR no transactions left
        if (ind < 0 || cap == 0)
            return 0;

        // Memoization
        if (dp[ind][buy][cap] != null)
            return dp[ind][buy][cap];

        int profit;

        // BUY BACK state
        if (buy == 0) {

            // Option 1: Skip buy back
            int skip = solve(ind - 1, 0, cap, prices, dp);

            // Option 2: Buy back stock
            // buying costs money => negative
            int buyBack = -prices[ind]
                    + solve(ind - 1, 1, cap - 1, prices, dp);

            // Use MIN because answers become negative internally
            profit = Math.min(skip, buyBack);
        }

        // SHORT SELL state
        else {

            // Option 1: Skip short sell
            int skip = solve(ind - 1, 1, cap, prices, dp);

            // Option 2: Short sell stock
            // selling first gives money => positive
            int sellFirst = prices[ind]
                    + solve(ind - 1, 0, cap, prices, dp);

            profit = Math.min(skip, sellFirst);
        }

        return dp[ind][buy][cap] = profit;
    }
}
```

---

-> 3. Short Selling Approach 2 (Cleaner Method)

-> Core idea:
Normal traversal (0 -> n-1)
But reverse transaction meaning

Sequence: SELL -> BUY BACK

This is cleaner than Approach 1.

```java
class Solution {

    public long maximumProfit(int[] prices, int k) {

        int n = prices.length;

        Integer[][][] dp = new Integer[n][2][k + 1];

        // buy = 0 means we can SHORT SELL now
        return solve(0, 0, k, prices, dp);
    }

    int solve(int ind, int buy, int cap,
              int[] prices, Integer[][][] dp) {

        // No days left OR no transactions left
        if (ind == prices.length || cap == 0)
            return 0;

        // Memoization
        if (dp[ind][buy][cap] != null)
            return dp[ind][buy][cap];

        int profit;

        // SHORT SELL state
        if (buy == 0) {

            // Option 1: Skip short selling
            int skip = solve(ind + 1, 0, cap, prices, dp);

            // Option 2: Sell first
            int sellFirst = prices[ind]
                    + solve(ind + 1, 1, cap, prices, dp);

            profit = Math.max(skip, sellFirst);
        }

        // BUY BACK state
        else {

            // Option 1: Skip buy back
            int skip = solve(ind + 1, 1, cap, prices, dp);

            // Option 2: Buy back stock
            int buyBack = -prices[ind]
                    + solve(ind + 1, 0, cap - 1, prices, dp);

            profit = Math.max(skip, buyBack);
        }

        return dp[ind][buy][cap] = profit;
    }
}
```

````
