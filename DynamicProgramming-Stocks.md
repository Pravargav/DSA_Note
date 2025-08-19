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
