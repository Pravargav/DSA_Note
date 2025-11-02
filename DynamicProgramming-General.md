-> is Subset Sum equal to k

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
-> number of subsets with sum k

```java
class Solution {
    public int numSubsets(int[] nums, int target) {
        int n = nums.length;
        Integer[][] dp = new Integer[n + 1][target + 1]; // Use Boolean instead of boolean
        return fun(nums, target, n, dp);
    }

    public int fun(int[] nums, int sum, int l, Integer[][] dp) {
        if (sum == 0) return 1;
        if (l == 0) return 0;

        // Check memoization (now valid since dp is Boolean[][])
        if (dp[l][sum] != null) {
            return dp[l][sum];
        }

        if (nums[l - 1] > sum) {
            dp[l][sum] = fun(nums, sum, l - 1, dp);
        } else {
            dp[l][sum] = fun(nums, sum - nums[l - 1], l - 1, dp) + 
                         fun(nums, sum, l - 1, dp);
        }

        return dp[l][sum];
    }
}
```
-> Longest Common SubSequence

```java
import java.util.Arrays;

public class LongestCommonSubsequenceMemo {

    public static int lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize with -1
        }
        return lcsHelper(s1, s2, m, n, memo);
    }

    private static int lcsHelper(String s1, String s2, int m, int n, int[][] memo) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // Return memoized value if already computed
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            memo[m][n] = 1 + lcsHelper(s1, s2, m - 1, n - 1, memo);
        } else {
            memo[m][n] = Math.max(
                lcsHelper(s1, s2, m - 1, n, memo),
                lcsHelper(s1, s2, m, n - 1, memo)
            );
        }
        
        return memo[m][n];
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        System.out.println("Length of LCS is: " + lcs(s1, s2));
        // Expected output: 4 (GTAB)
    }
}
```

-> Longest Common Substring

```java
import java.util.Arrays;

public class LongestCommonSubstringMemo {

    private static int maxLen = 0;
    private static int[][] memo;

    public static int lcSubstring(String s1, String s2) {
        maxLen = 0;
        int m = s1.length();
        int n = s2.length();
        memo = new int[m + 1][n + 1];
        for (int[] row : memo) {
            Arrays.fill(row, -1); // Initialize with -1
        }
        lcSubstringHelper(s1, s2, m, n);
        return maxLen;
    }

    private static int lcSubstringHelper(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        
        // Return memoized value if already computed
        if (memo[m][n] != -1) {
            return memo[m][n];
        }
        
        int res;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            res = 1 + lcSubstringHelper(s1, s2, m - 1, n - 1);
            maxLen = Math.max(maxLen, res);
        } else {
            res = 0;
            lcSubstringHelper(s1, s2, m - 1, n);
            lcSubstringHelper(s1, s2, m, n - 1);
        }
        
        memo[m][n] = res;
        return res;
    }

    public static void main(String[] args) {
        String s1 = "ABABC";
        String s2 = "BABCA";
        
        System.out.println("Length of LCSubstring is: " + lcSubstring(s1, s2));
        // Expected output: 4 (BABC)
    }
}
```
-> Longest Common Subsequence( return sequence rather than length)

```java
public class LCSubsequenceMemoArray {

    private static String[][] memo;

    public static String lcs(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new String[m + 1][n + 1];
        return lcsHelper(s1, s2, m, n);
    }

    private static String lcsHelper(String s1, String s2, int m, int n) {
        // Check if already computed
        if (memo[m][n] != null) {
            return memo[m][n];
        }
        
        // Base case
        if (m == 0 || n == 0) {
            memo[m][n] = "";
            return "";
        }
        
        String result;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            result = lcsHelper(s1, s2, m - 1, n - 1) + s1.charAt(m - 1);
        } else {
            String lcs1 = lcsHelper(s1, s2, m - 1, n);
            String lcs2 = lcsHelper(s1, s2, m, n - 1);
            result = lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        }
        
        memo[m][n] = result;
        return result;
    }

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";
        
        String result = lcs(s1, s2);
        System.out.println("LCS is: " + result);
        System.out.println("Length of LCS is: " + result.length());
        // Output:
        // LCS is: GTAB
        // Length of LCS is: 4
    }
}
```
-> Longest Common Substring (return substring rather than the length)

```java
public class LCSubstringMemoString {

    private static String[][] memo;
    private static String maxSubstring = "";

    public static String lcSubstring(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        memo = new String[m + 1][n + 1];
        maxSubstring = "";
        
        lcSubstringHelper(s1, s2, m, n);
        
        return maxSubstring;
    }

    private static String lcSubstringHelper(String s1, String s2, int m, int n) {
        if (m == 0 || n == 0) {
            return "";
        }
        
        // Check if already computed
        if (memo[m][n] != null) {
            return memo[m][n];
        }
        
        String result;
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            String prev = lcSubstringHelper(s1, s2, m - 1, n - 1);
            result = prev + s1.charAt(m - 1);
            
            // Update max substring if current is longer
            if (result.length() > maxSubstring.length()) {
                maxSubstring = result;
            }
        } else {
            result = "";
            lcSubstringHelper(s1, s2, m - 1, n);
            lcSubstringHelper(s1, s2, m, n - 1);
        }
        
        memo[m][n] = result;
        return result;
    }

    public static void main(String[] args) {
        String s1 = "ABABC";
        String s2 = "BABCA";
        
        String result = lcSubstring(s1, s2);
        System.out.println("LCSubstring is: " + result);
        System.out.println("Length of LCSubstring is: " + result.length());
        // Output:
        // LCSubstring is: BABC
        // Length of LCSubstring is: 4
    }
}
```
-> Longest arthimatic progression(recurrsion code-if you want you can convert to dp)-(gfg solution)

```java
// Java program to find the length of the longest 
// arithmetic progression (AP) using recursion
import java.util.ArrayList;

class GfG {

    // Recursive function to find the length of AP 
    // ending at index `index` with difference `diff`
    static int solve(int index, int diff, 
                     ArrayList<Integer> arr) {

        // Base case: if index goes out of bounds, 
        // return 0
        if (index < 0) {
            return 0;
        }

        int ans = 0;

        // Iterate through previous elements 
        // to find matching differences
        for (int j = index - 1; j >= 0; j--) {

            // If the difference matches, extend the AP
            if (arr.get(index) - arr.get(j) == diff) {
                ans = Math.max(ans, 
                               1 + solve(j, diff, arr));
            }
        }

        return ans;
    }

    // Function to find the length of the longest 
    // arithmetic progression (AP) in the array
    static int lengthOfLongestAP(ArrayList<Integer> arr) {
        
        int n = arr.size();

        // If there are less than 3 elements, 
        // return the size
        if (n <= 2) {
            return n;
        }

        int ans = 0;

        // Iterate through all pairs of elements as 
        // possible first two terms
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                // Calculate difference and find AP 
                // using recursion
                int diff = arr.get(j) - arr.get(i);
                ans = Math.max(ans, 
                               2 + solve(i, diff, arr));
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(1);
        arr.add(7);
        arr.add(10);
        arr.add(15);
        arr.add(27);
        arr.add(29);

        System.out.println(lengthOfLongestAP(arr));
    }
}
```

-> 2305. leetcode -fair distribution of cookies (backtracking concept)

```java
class Solution {
    int res=Integer.MAX_VALUE; 
    public int distributeCookies(int[] cookies, int k) {
        int n=cookies.length;
        int[]alloc=new int[k];
        gen(cookies,alloc,0,k);
        return res;
    }

      
    public void gen(int arr[],int []alloc,int index,int k){
        if(index==arr.length){
            pall(alloc);
            return;
        }
        for(int st=0;st<k;st++){
            alloc[st]+=arr[index];
            gen(arr,alloc,index+1,k);
            alloc[st]-=arr[index];
        }
    }

    public void pall(int[] alloc){
        for(int i=0;i<alloc.length;i++){
            System.out.print(alloc[i]+" ");
        }
        System.out.println();
    }
}
```

-> 1025. Divisor Game(GFG - leetcode)

```java
class Solution {
    public boolean divisorGame(int n) {
        int N = n;

        int[][] dp = new int[N + 1][2];

        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < 2; j++) {
                dp[i][j] = -1;
            }
        }

        if (divisorGame(N, 1, dp) == 1)
            return true;
        else
            return false;
    }

    int divisorGame(int N, int A, int dp[][]) {

        if (N == 1 || N == 3)
            return 0;

        if (N == 2)
            return 1;

        if (dp[N][A] != -1)
            return dp[N][A];

        int ans = (A == 1) ? 0 : 1;

        for (int i = 1; i * i <= N; i++) {

            if (N % i == 0) {

                if (A == 1)
                    ans |= divisorGame(N - i, 0, dp);

                else
                    ans &= divisorGame(N - i, 1, dp);
            }
        }

        return dp[N][A] = ans;
    }
}
```
##### Example notes for reference(not actual problems)

````markdown
-> 2708. Maximum Strength of a Group( using void return type)(backtracking)


    
```java
class Solution {
    int ans = Integer.MIN_VALUE;

    public void solve(int[] nums, int index, int product, int size) {
        if (index == nums.length) {
            if (size != 0) {
                ans = Math.max(ans, product);
            }
            return;
        }

        // Pick the current element
        solve(nums, index + 1, product * nums[index], size + 1);

        // Do not pick the current element
        solve(nums, index + 1, product, size);
    }

    public int maxStrength(int[] nums) {
        solve(nums, 0, 1, 0);
        return ans;
    }
}
```
--------------------------(or)---------------------------------

-> 2708. Maximum Strength of a Group( using int return type)(backtracking)
    
```java
class Solution {
    public int solve(int[] nums, int index, int product, int size) {
        if (index == nums.length) {
            return size != 0 ? product : Integer.MIN_VALUE;
        }

        // Pick the current element
        int pick = solve(nums, index + 1, product * nums[index], size + 1);

        // Do not pick the current element
        int skip = solve(nums, index + 1, product, size);

        return Math.max(pick, skip);
    }

    public int maxStrength(int[] nums) {
        return solve(nums, 0, 1, 0);
    }
}
```

````

````markdown
-> 2708. Maximum Strength of a Group(right answer)(Backtracking)


    
```java
import java.util.*;
class Main {
    List<List<Integer>> h=new ArrayList<>();
    public int solve(int[] nums, int index,int p) {
        // Base case: when all elements are processed
        if (index == nums.length) {
            return p;
        }

        // Pick the current element
        int pick=solve(nums, index + 1, p*nums[index]);

        // Do not pick the current element
        int notpick=solve(nums, index + 1,p);
        List<Integer> l=new ArrayList<>();
        l.add(pick);
        l.add(notpick);
        h.add(l);
        return Math.max(pick,notpick);
    }

    public void maxStrength(int[] nums) {
        int val=solve(nums, 0, 1);
        System.out.println(h);
        System.out.println(h.size());
    }

    public static void main(String[] args) {
        int[] nums = {3, -1, -5, 2, 5, -9};
        Main obj = new Main();
        obj.maxStrength(nums);
    }
} 
```
--------------------------(or)---------------------------------

-> 2708. Maximum Strength of a Group( wrong answer)(Divide and recombine)
    
```java
import java.util.*;
class Main {
	List<List<Integer>> lt=new ArrayList<>();
	public int solve(int[] nums, int index) {
		// Base case: when all elements are processed
		if (index == nums.length) {
			return 1;
		}

		// Pick the current element
		int pick=nums[index]*solve(nums, index + 1);

		// Do not pick the current element
		int notpick=solve(nums, index + 1);
		List<Integer> l=new ArrayList<>();
		if(true) {
			l.add(pick);
			l.add(notpick);
			lt.add(l);
		}
		return Math.max(pick,notpick);
	}

	public void maxStrength(int[] nums) {
		int val=solve(nums, 0);
		System.out.println(lt);
		System.out.println(lt.size());
	}

	public static void main(String[] args) {
		int[] nums = {3, -1, -5, 2, 5, -9};
		Main obj = new Main();
		obj.maxStrength(nums);
	}
}
```
->if(true) -> 63 values = 32 + 16 + 8 + 4 + 2 + 1

->if(pick==-9) -> 32 values

->if(pick==5) -> 16 values

->if(pick==10)-> 8 values

->if(pick==-50)-> 4 values

->if(pick==-10)-> 2 values

->if(pick==30)-> 1 values

-> note: {3, -1, -5, 2, 5, -9} return 1 as index==nums.length, max of (-9, 1) = 1, max(5*1,1*1)= 5*1 , max(2*5*1,1*5*1)=2*5*1,

 max(-5*2*5*1,1*2*5*1)=1*2*5*1, max(-1*1*2*5*1,1*1*2*5*1)=1*1*2*5*1, max(3*1*1*2*5*1,1*1*1*2*5*1)=3*1*1*2*5*1 = 30
````
