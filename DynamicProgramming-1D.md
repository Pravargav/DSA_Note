## Both problems are of dp[i]==dp[i-j] or dp[val]=dp[val-sub]+1  i.e. .... both are of type dp[x],dp[x-y]

-> Divisor Game

```java
class Solution {
   public boolean divisorGame(int N) {
        boolean[] dp = new boolean[N+1];
        dp[0] = false;
        dp[1] = false;
        for(int i=2; i <= N; i++){
            for(int j=1; j < i; j++){
                if(i % j == 0){
                    if(dp[i-j] == false){
                        dp[i] = true;
                        break;
                    }
                }
            }
        }
        return dp[N];
    }
}
```
->Counting bits

```java
class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        int j = 1;

        for (int i = 1; i <= n; i++) {
            if (j * 2 == i) {
                j = i;
            }

            dp[i] = dp[i - j] + 1;
        }

        return dp;
    }
}
```
