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
-> Longest Increasing Subsequence(Binary Search)-(striver/Gfg)-(not so important)

```java
import java.util.*;

class LIS {
    // Function to find the length of the longest increasing subsequence
    static int longestIncreasingSubsequence(int[] arr, int n) {
        List<Integer> temp = new ArrayList<>();
        temp.add(arr[0]);

        int len = 1;

        for (int i = 1; i < n; i++) {
            if (arr[i] > temp.get(temp.size() - 1)) {
                // arr[i] > the last element of temp array

                temp.add(arr[i]);
                len++;
            } else {
                // Replacement step
                int ind = Collections.binarySearch(temp, arr[i]);

                if (ind < 0) {
                    ind = -ind - 1;
                }

                temp.set(ind, arr[i]);
            }
        }

        return len;
    }

    public static void main(String[] args) {
        int[] arr = {10, 9, 2, 5, 3, 7, 101, 18};
        int n = arr.length;

        System.out.println("The length of the longest increasing subsequence is " +
                longestIncreasingSubsequence(arr, n));
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
-> Longest bitonic sequence -(same code as above from both forward and backward direction)

```java
import java.util.*;

class TUF {
    // Function to find the length of the longest bitonic subsequence
    static int longestBitonicSequence(int[] arr, int n) {
        // Arrays to store lengths of increasing and decreasing subsequences
        int[] dp1 = new int[n];
        int[] dp2 = new int[n];

        // Initialize both arrays with 1, as each element itself is a subsequence of length 1
        Arrays.fill(dp1, 1);
        Arrays.fill(dp2, 1);

        // Calculate the lengths of increasing subsequences
        for (int i = 0; i < n; i++) {
            for (int prevIndex = 0; prevIndex < i; prevIndex++) {
                if (arr[prevIndex] < arr[i]) {
                    dp1[i] = Math.max(dp1[i], 1 + dp1[prevIndex]);
                }
            }
        }

        // Reverse the direction of nested loops and calculate the lengths of decreasing subsequences
        for (int i = n - 1; i >= 0; i--) {
            for (int prevIndex = n - 1; prevIndex > i; prevIndex--) {
                if (arr[prevIndex] < arr[i]) {
                    dp2[i] = Math.max(dp2[i], 1 + dp2[prevIndex]);
                }
            }
        }

        int maxi = -1;

        // Calculate the length of the longest bitonic subsequence
        for (int i = 0; i < n; i++) {
            maxi = Math.max(maxi, dp1[i] + dp2[i] - 1);
        }

        return maxi;
    }

    public static void main(String[] args) {
        int arr[] = {1, 11, 2, 10, 4, 5, 2, 1};
        int n = arr.length;

        System.out.println("The length of the longest bitonic subsequence is " +
                longestBitonicSequence(arr, n));
    }
}

```
Note - Longest String Chain Problem( A mixture of lis and lcs of strings)

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
-> Longest divisible subset( same as above code just change greater than symbol to modular division ==0 symbol)

```java
import java.util.*;

class DivisibleSet {
    // Function to find the longest divisible subset
    static List<Integer> divisibleSet(List<Integer> arr) {
        int n = arr.size();

        // Sort the array
        Collections.sort(arr);

        List<Integer> dp = new ArrayList<>(Collections.nCopies(n, 1));
        List<Integer> hash = new ArrayList<>(Collections.nCopies(n, 0));

        for (int i = 0; i < n; i++) {
            hash.set(i, i); // Initializing with current index
            for (int prev_index = 0; prev_index <= i - 1; prev_index++) {
                if (arr.get(i) % arr.get(prev_index) == 0 && 1 + dp.get(prev_index) > dp.get(i)) {
                    dp.set(i, 1 + dp.get(prev_index));
                    hash.set(i, prev_index);
                }
            }
        }

        int ans = -1;
        int lastIndex = -1;

        for (int i = 0; i < n; i++) {
            if (dp.get(i) > ans) {
                ans = dp.get(i);
                lastIndex = i;
            }
        }

        List<Integer> temp = new ArrayList<>();
        temp.add(arr.get(lastIndex));

        while (hash.get(lastIndex) != lastIndex) {
            lastIndex = hash.get(lastIndex);
            temp.add(arr.get(lastIndex));
        }

        // Reverse the array
        Collections.reverse(temp);

        return temp;
    }

    public static void main(String[] args) {

        List<Integer> arr = Arrays.asList(1, 16, 7, 8, 4);

        List<Integer> ans = divisibleSet(arr);

        System.out.print("The longest divisible subset elements are: ");
        for (int i = 0; i < ans.size(); i++) {
            System.out.print(ans.get(i) + " ");
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


