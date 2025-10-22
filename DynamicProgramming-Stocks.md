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
        // Base case: If we have processed all stocks or have no capital left, return 0 profit
        if (ind == n || cap == 0)
            return 0;

        // If the result for this state is already calculated, return it
        if (dp[ind][buy][cap] != -1)
            return dp[ind][buy][cap];

        int profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 0, cap, dp),
                    -Arr[ind] + getAns(Arr, n, ind + 1, 1, cap, dp));
        }

        if (buy == 1) { // We can sell the stock
            profit = Math.max(0 + getAns(Arr, n, ind + 1, 1, cap, dp),
                    Arr[ind] + getAns(Arr, n, ind + 1, 0, cap - 1, dp));
        }

        // Store the calculated profit in the dp array and return it
        return dp[ind][buy][cap] = profit;
    }

    static int maxProfit(int[] prices) {
        int n = prices.length;

        // Creating a 3D dp array of size [n][2][3]
        int[][][] dp = new int[n][2][3];

        // Initialize the dp array with -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        // Calculate and return the maximum profit
        return getAns(prices, n, 0, 0, 2, dp);
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;

        // Calculate and print the maximum profit
        System.out.println("The maximum profit that can be generated is " + maxProfit(prices));
    }
}
```

-> Stocks 4- k transactions allowed

```java
import java.util.Arrays;

public class StockBuySell {
    
    // Function to calculate the maximum profit
    public static int maximumProfit(int[] prices, int n, int k) {
        // Creating a 3D array dp of size [n][2][k+1]
        int[][][] dp = new int[n][2][k + 1];
        
        // Initialize dp with -1 to mark states as not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        return getAns(prices, n, 0, 0, k, dp);
    }
    
    // Recursive function to calculate the maximum profit
    public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
        // Base case
        if (ind == n || cap == 0) {
            return 0;
        }
        
        // If the result is already calculated, return it
        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }
        
        int profit;
        
        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(prices, n, ind + 1, 0, cap, dp),
                             -prices[ind] + getAns(prices, n, ind + 1, 1, cap, dp));
        } else { // We can sell the stock
            profit = Math.max(0 + getAns(prices, n, ind + 1, 1, cap, dp),
                             prices[ind] + getAns(prices, n, ind + 1, 0, cap - 1, dp));
        }
        
        // Store the result in dp and return it
        dp[ind][buy][cap] = profit;
        return profit;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int n = prices.length;
        int k = 2;
        
        System.out.println("The maximum profit that can be generated is " + maximumProfit(prices, n, k));
    }
}

```
````markdown
-> Sample Example for stocks 5 i.e code for only short selling transaction(normal transaction excluded)

  - from  n-1 to 0 - short selling & from 0 to n-1 normal
  - use max - short selling & use min - normal
  - start with sell -short selling & start with buy - normal
  - cap-1 in buy- short selling & cap-1 in sell - normal
  - final answer use Math.abs
    
```java
class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];

        // Initialize dp with -1 to mark states as not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
     
        return Math.abs(getAns(prices, n, n-1, 1, k, dp));
    }

    public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
        // Base case
        if (ind <0 || cap == 0) {
            return 0;
        }

        // If the result is already calculated, return it
        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }

        int profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.min(0 + getAns(prices, n, ind - 1, 0, cap, dp),
                    -prices[ind] + getAns(prices, n, ind - 1, 1, cap-1 , dp));
        } else { // We can sell the stock
            profit = Math.min(0 + getAns(prices, n, ind - 1, 1, cap, dp),
                    prices[ind] + getAns(prices, n, ind - 1, 0, cap, dp));
        }

        // Store the result in dp and return it
        dp[ind][buy][cap] = profit;
        return profit;
    }
}
```
--------------------------(or)---------------------------------

->(method 2 of above) Sample Example for stocks 5 i.e code for only short selling transaction(normal transaction excluded)

  - just change order from n-1 to 0 TO 0 to n-1 of stocks 4 problem i.e normal problem
    
```java
class Solution {
    public long maximumProfit(int[] prices, int k) {
        int n = prices.length;
        int[][][] dp = new int[n][2][k + 1];

        // Initialize dp with -1 to mark states as not calculated yet
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        int fn=getAns(prices, n, n-1, 0, k, dp);
        for(int i=0;i<n;i++){
            for(int j=0;j<2;j++){
                for(int l=1;l<k+1;l++){
                    System.out.print(dp[i][j][l]+" ");
                }
                System.out.println();
            }
            System.out.println();
        }
        return fn;
    }

    public static int getAns(int[] prices, int n, int ind, int buy, int cap, int[][][] dp) {
        // Base case
        if (ind <0 || cap == 0) {
            return 0;
        }

        // If the result is already calculated, return it
        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }

        int profit;

        if (buy == 0) { // We can buy the stock
            profit = Math.max(0 + getAns(prices, n, ind - 1, 0, cap, dp),
                    -prices[ind] + getAns(prices, n, ind - 1, 1, cap , dp));
        } else { // We can sell the stock
            profit = Math.max(0 + getAns(prices, n, ind - 1, 1, cap, dp),
                    prices[ind] + getAns(prices, n, ind - 1, 0, cap-1, dp));
        }

        // Store the result in dp and return it
        dp[ind][buy][cap] = profit;
        return profit;
    }
}
```

````
-> Stocks with fees - 714. add "-fee"

-> Stocks with cooldown - 309. replace idx+1 with idx+2 and add || idx==n+1
