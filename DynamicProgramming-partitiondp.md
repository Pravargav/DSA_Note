### partition dp

-> Matrix Chain multiplication
```java
import java.util.*;

class TUF{
static int f(int arr[], int i, int j, int[][] dp){
    
    // base condition
    if(i == j)
        return 0;
        
    if(dp[i][j]!=-1)
        return dp[i][j];
    
    int mini = Integer.MAX_VALUE;
    
    // partioning loop
    for(int k = i; k<= j-1; k++){
        
    int ans = f(arr,i,k,dp) + f(arr, k+1,j,dp) + arr[i-1]*arr[k]*arr[j];
        
    mini = Math.min(mini,ans);
        
    }
    
    return mini;
}


static int matrixMultiplication(int[] arr, int N){
    
    int dp[][]= new int[N][N];
    
    for(int row[]:dp)
    Arrays.fill(row,-1);
    
    int i =1;
    int j = N-1;
    
    
    return f(arr,i,j,dp);
    
    
}

public static void main(String args[]) {
	
	int arr[] = {10, 20, 30, 40, 50};
	
	int n = arr.length;
	
	System.out.println("The minimum number of operations are "+
        matrixMultiplication(arr,n));
	
}
}
```
-> Minimum cost to cut the stick-same as above - i.e. inplace of "arr[i-1]*arr[k]*arr[j] replace with cuts.get(j + 1) - cuts.get(i - 1)"

```java
import java.util.*;

public class TUF {
    // Recursive function to calculate the minimum cost
    static int f(int i, int j, ArrayList<Integer> cuts) {
        // Base case
        if (i > j) {
            return 0;
        }

        int mini = Integer.MAX_VALUE;

        for (int ind = i; ind <= j; ind++) {
            int ans = cuts.get(j + 1) - cuts.get(i - 1) +
                      f(i, ind - 1, cuts) +
                      f(ind + 1, j, cuts);

            mini = Math.min(mini, ans);
        }

        return mini;
    }

    // Function to calculate the minimum cost
    static int cost(int n, int c, ArrayList<Integer> cuts) {
        // Modify the cuts array
        cuts.add(n);
        cuts.add(0);
        Collections.sort(cuts);

        return f(1, c, cuts);
    }

    public static void main(String[] args) {
        ArrayList<Integer> cuts = new ArrayList<>(Arrays.asList(3, 5, 1, 4));
        int c = cuts.size();
        int n = 7;

        System.out.println("The minimum cost incurred: " + cost(n, c, cuts));
    }
}
```
-> Burst Ballons- i.e. "cuts.get(j + 1) - cuts.get(i - 1) replace with a.get(i - 1) * a.get(ind) * a.get(j + 1)"

```java
import java.util.*;

public class TUF {
    // Function to recursively calculate the maximum coins
    static int maxCoins(int i, int j, ArrayList<Integer> a, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
        int maxi = Integer.MIN_VALUE;
        for (int ind = i; ind <= j; ind++) {
            int cost = a.get(i - 1) * a.get(ind) * a.get(j + 1)
                       + maxCoins(i, ind - 1, a, dp) + maxCoins(ind + 1, j, a, dp);
            maxi = Math.max(maxi, cost);
        }
        return dp[i][j] = maxi;
    }

    // Wrapper function to calculate the maximum coins
    static int maxCoins(ArrayList<Integer> a) {
        int n = a.size();
        a.add(1);
        a.add(0, 1);
        int[][] dp = new int[n + 2][n + 2];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        return maxCoins(1, n, a, dp);
    }

    public static void main(String[] args) {
        ArrayList<Integer> a = new ArrayList<>(Arrays.asList(3, 1, 5, 8));
        int ans = maxCoins(a);
        System.out.println(ans);
    }
}
```
### front partition

-> palindrome partitioning
```java
public class PalindromePartitioning {
    static boolean isPalindrome(int i, int j, String s) {
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }

    static int f(int i, int n, String str, int[] dp) {
        // Base case:
        if (i == n) return 0;

        if (dp[i] != -1) return dp[i];
        int minCost = Integer.MAX_VALUE;
        // String[i...j]
        for (int j = i; j < n; j++) {
            if (isPalindrome(i, j, str)) {
                int cost = 1 + f(j + 1, n, str, dp);
                minCost = Math.min(minCost, cost);
            }
        }
        return dp[i] = minCost;
    }

    static int palindromePartitioning(String str) {
        int n = str.length();
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return f(0, n, str, dp) - 1;
    }

    public static void main(String[] args) {
        String str = "BABABCBADCEDE";
        int partitions = palindromePartitioning(str);
        System.out.println("The minimum number of partitions: " + partitions);
    }
}
```
-> maximum sum after partitioning- same as above - i.e. find maximum element of the partition - find maximum sum of all sums - so used two times max function

```java

public class MaxSumAfterPartitioning {
    static int f(int ind, int[] num, int k, int[] dp) {
        int n = num.length;
        // Base case:
        if (ind == n) return 0;

        if (dp[ind] != -1) return dp[ind];
        int len = 0;
        int maxi = Integer.MIN_VALUE;
        int maxAns = Integer.MIN_VALUE;
        
        // Iterate through the next 'k' elements or remaining elements if less than 'k'.
        for (int j = ind; j < Math.min(ind + k, n); j++) {
            len++;
            maxi = Math.max(maxi, num[j]);
            int sum = len * maxi + f(j + 1, num, k, dp);
            maxAns = Math.max(maxAns, sum);
        }
        return dp[ind] = maxAns;
    }

    static int maxSumAfterPartitioning(int[] num, int k) {
        int n = num.length;
        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        return f(0, num, k, dp);
    }

    public static void main(String[] args) {
        int[] num = {1, 15, 7, 9, 2, 5, 10};
        int k = 3;
        int maxSum = maxSumAfterPartitioning(num, k);
        System.out.println("The maximum sum is: " + maxSum);
    }
}
```
