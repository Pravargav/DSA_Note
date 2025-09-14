-> Longest Increasing Subsequence(striver)

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


-> Longest Increasing Subsequence(Tabulation)-(striver)

```java
import java.util.*;

class TUF{
static int longestIncreasingSubsequence(int arr[], int n){
    
    int dp[][]=new int[n+1][n+1];
    
    for(int ind = n-1; ind>=0; ind --){
        for (int prev_index = ind-1; prev_index >=-1; prev_index --){
            
            int notTake = 0 + dp[ind+1][prev_index +1];
    
            int take = 0;
    
            if(prev_index == -1 || arr[ind] > arr[prev_index]){
                
                take = 1 + dp[ind+1][ind+1];
            }
    
            dp[ind][prev_index+1] = Math.max(notTake,take);
            
        }
    }
    
    return dp[0][0];
}

public static void main(String args[]) {
	
	int arr[] = {10,9,2,5,3,7,101,18};
	
	int n = arr.length;
	
	System.out.println("The length of the longest increasing subsequence is 
        "+longestIncreasingSubsequence(arr,n));
	
}
}
```

-> Longest Increasing Subsequence(Space optimization-not important)-(striver)

```java
import java.util.*;

class TUF{
static int longestIncreasingSubsequence(int arr[], int n){
    
    int next[]=new int[n+1];
    int cur[]=new int[n+1];
    
    for(int ind = n-1; ind>=0; ind --){
        for (int prev_index = ind-1; prev_index >=-1; prev_index --){
            
            int notTake = 0 + next[prev_index +1];
    
            int take = 0;
    
            if(prev_index == -1 || arr[ind] > arr[prev_index]){
                
                take = 1 + next[ind+1];
            }
    
            cur[prev_index+1] = Math.max(notTake,take);
        }
        next = cur.clone();
    }
    
    return cur[0];
}

public static void main(String args[]) {
	
	int arr[] = {10,9,2,5,3,7,101,18};
	
	int n = arr.length;
	
	System.out.println("The length of the longest increasing subsequence is 
        "+longestIncreasingSubsequence(arr,n));
	
}
}
```
-> Longest Increasing Subsequence(another simple approach)-(striver)

```java
import java.util.*;

class TUF{
static int longestIncreasingSubsequence(int arr[], int n){
    
    int dp[]=new int[n];
    Arrays.fill(dp,1);
    
    for(int i=0; i<=n-1; i++){
        for(int prev_index = 0; prev_index <=i-1; prev_index ++){
            
            if(arr[prev_index]<arr[i]){
                dp[i] = Math.max(dp[i], 1 + dp[prev_index]);
            }
        }
    }
    
    int ans = -1;
    
    for(int i=0; i<=n-1; i++){
        ans = Math.max(ans, dp[i]);
    }
    
    return ans;
}

public static void main(String args[]) {
	
	int arr[] = {10,9,2,5,3,7,101,18};
	
	int n = arr.length;
	
	System.out.println("The length of the longest increasing subsequence is 
        "+longestIncreasingSubsequence(arr,n));
	
}
}
```
-> Longest Increasing Subsequence(for printing the actual subsequence)-(Same as above approach except adding hash array for storing indexes)-(striver)

```java
import java.util.*;

class TUF{
static int longestIncreasingSubsequence(int arr[], int n){
    
    int[] dp=new int[n];
    Arrays.fill(dp,1);
    int[] hash=new int[n];
    Arrays.fill(hash,1);
    
    for(int i=0; i<=n-1; i++){
        
        hash[i] = i; // initializing with current index
        for(int prev_index = 0; prev_index <=i-1; prev_index ++){
            
            if(arr[prev_index]<arr[i] && 1 + dp[prev_index] > dp[i]){
                dp[i] = 1 + dp[prev_index];
                hash[i] = prev_index;
            }
        }
    }
    
    int ans = -1;
    int lastIndex =-1;
    
    for(int i=0; i<=n-1; i++){
        if(dp[i]> ans){
            ans = dp[i];
            lastIndex = i;
        }
    }
    
    ArrayList<Integer> temp=new ArrayList<>();
    temp.add(arr[lastIndex]);
    
    while(hash[lastIndex] != lastIndex){ // till not reach the initialization value
        lastIndex = hash[lastIndex];
        temp.add(arr[lastIndex]);    
    }
    
    // reverse the array 
    
    System.out.print("The subsequence elements are ");
    
    for(int i=temp.size()-1; i>=0; i--){
        System.out.print(temp.get(i)+" ");
    }
    System.out.println();
    
    return ans;
}

public static void main(String args[]) {
	
	int arr[] = {10,9,2,5,3,7,101,18};
	
	int n = arr.length;
	longestIncreasingSubsequence(arr,n);
	
}
}
```
-> Count lis(chatgpt/gfg)

```java
public class CountLIS {
    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] dp = new int[n];     // LIS length ending at i
        int[] count = new int[n];  // count of LIS ending at i

        int maxLen = 1, result = 0;

        for (int i = 0; i < n; i++) {
            dp[i] = 1;      // every element is LIS of length 1
            count[i] = 1;   // initially one sequence for each
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        count[i] = count[j];   // inherit count
                    } else if (dp[j] + 1 == dp[i]) {
                        count[i] += count[j];  // add ways
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        // sum up counts for all max length LIS
        for (int i = 0; i < n; i++) {
            if (dp[i] == maxLen) {
                result += count[i];
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 4, 7};
        System.out.println("Number of LIS: " + findNumberOfLIS(nums));
    }
}

```
