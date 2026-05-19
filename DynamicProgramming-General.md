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
        // Find the longest common substring ending
        // at every pair of characters and take the 
        // maximum of all.
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                maxLen = Math.max(maxLen, lcSubstringHelper(s1, s2, i, j));
            }
        }
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

-> Longest arthimatic progression(recurrsion code-if you want you can convert to dp)-(gfg solution)

```java
import java.util.ArrayList;

class GfG {

    static int solve(int index, int diff, ArrayList<Integer> arr) {
        if (index < 0) {
            return 0;
        }

        int ans = 0;

        for (int j = index - 1; j >= 0; j--) {
            if (arr.get(index) - arr.get(j) == diff) {
                ans = Math.max(ans, 1 + solve(j, diff, arr));
            }
        }

        return ans;
    }

    static int lengthOfLongestAP(ArrayList<Integer> arr) {
        int n = arr.size();

        if (n <= 2) {
            return n;
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = arr.get(j) - arr.get(i);
                ans = Math.max(ans, 2 + solve(i, diff, arr));
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

-> Stone Game leetcode - Alice bob kind of sums when both play optimally- **vvimp**

```java
class Solution {
    int [][][] memo;
    public boolean stoneGame(int[] piles) {
        memo = new int[piles.length + 1][piles.length + 1][2];
        for(int [][] arr : memo)
            for(int [] subArr : arr)
                Arrays.fill(subArr, -1);
        
        return (helper(0, piles.length - 1, piles, 1) > 0);
    }
    
    public int helper(int l, int r, int [] piles, int ID){
        if(r < l)
            return 0;
        if(memo[l][r][ID] != -1)
            return memo[l][r][ID];
        
        int next = Math.abs(ID - 1);
        if(ID == 1)
            memo[l][r][ID] = Math.max(piles[l] + helper(l + 1, r, piles, next), piles[r] + helper(l, r - 1, piles, next));
        else
            memo[l][r][ID] = Math.min(-piles[l] + helper(l + 1, r, piles, next), -piles[r] + helper(l, r - 1, piles, next));
        
        return memo[l][r][ID];
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
````
````markdown
->if(true) -> 63 values = 32 + 16 + 8 + 4 + 2 + 1

->if(pick==-9) -> 32 values

->if(pick==5) -> 16 values

->if(pick==10)-> 8 values

->if(pick==-50)-> 4 values

->if(pick==-10)-> 2 values

->if(pick==30)-> 1 values

-> note: {3, -1, -5, 2, 5, -9} return 1 as index==nums.length,
 max of (-9, 1) = 1,
 max(5*1,1*1)= 5*1 ,
 max(2*5*1,1*5*1)=2*5*1,
 max(-5*2*5*1,1*2*5*1)=1*2*5*1, 
 max(-1*1*2*5*1,1*1*2*5*1)=1*1*2*5*1, 
 max(3*1*1*2*5*1,1*1*1*2*5*1)=3*1*1*2*5*1 = 30
````

````markdown


           A               A                 A                A                A               A
 (or)  | | | | |        | | | | |        | | | | |        | | | | |        | | | | |       | | | | |
    B  0 0 0 0 0        0 0 0 0 1        0 0 0 1 1        0 0 1 1 1        0 1 1 1 1       1 1 1 1 1



           B               B                 B                B                B               B
(and)  | | | | |        | | | | |        | | | | |        | | | | |        | | | | |       | | | | |
    A  0 0 0 0 0        0 0 0 0 1        0 0 0 1 1        0 0 1 1 1        0 1 1 1 1       1 1 1 1 1



                                                         
                                                                    |                                                                           (A)

               |                                     |                                |                                 |                       (B)

   |     |      |      |      |         |     |      |      |      |         |     |      |      |      |       |     |      |      |      |    (A)

|||||| |||||| |||||| |||||| ||||||  |||||| |||||| |||||| |||||| ||||||  |||||| |||||| |||||| |||||| ||||||  |||||| |||||| |||||| |||||| ||||||  (B)

                                    (i) **sample tree recursion**
	  
````
Your first code is correct because it follows proper backtracking / subset generation.

Your second code is wrong because it tries to recombine answers incorrectly and loses subset state.


---

Core Idea of Problem

In 2708. Maximum Strength of a Group, we must:

choose any non-empty subset

calculate product

return maximum product


So every recursive path must maintain:

current subset product

whether we picked something or not



---

Why First Code Works

Your first code:

solve(nums,index,p)

Here:

index → current position

p → product of currently selected subset


At every step:

pick     -> include nums[index]
notpick  -> skip nums[index]

This is TRUE backtracking.


---

Visualization

Suppose:

nums = [2,3]

Tree:

(1)
               /           \
            *2              skip
          (2)                (1)
        /     \            /     \
      *3      skip      *3      skip
     (6)      (2)      (3)      (1)

Generated subset products:

[2,3] -> 6
[2]   -> 2
[3]   -> 3
[]    -> 1

Maximum = 6


---

Why Second Code Fails

Your second code:

pick = nums[index] * solve(index+1)

This looks similar but is fundamentally different.


---

BIG PROBLEM

solve(index+1) already returns:

maximum product possible from remaining array

NOT:

product of one specific subset

So you're multiplying current number with an already maximized result.

That creates invalid combinations.


---

Example Where It Breaks

Consider:

nums = [-1, 2, -3]

Suppose:

solve(2) returns max(-3,1)=1

Then:

pick = 2 * 1 = 2

But:

1 came from "not picking -3"

now we combined:

picked 2

skipped -3



Recursion loses actual subset structure.


---

Key Difference

Correct Backtracking

Carries current state downward.

solve(index, currentProduct)

Each path = one real subset.


---

Wrong Divide & Recombine

Returns already optimized answers upward.

nums[index] * solve(index+1)

This mixes incompatible decisions.


---

Clean Correct Backtracking Code

import java.util.*;

class Main {

    long ans = Long.MIN_VALUE;

    void solve(int[] nums, int index, long product, boolean taken) {

        // Base case
        if (index == nums.length) {

            // subset should not be empty
            if (taken) {
                ans = Math.max(ans, product);
            }

            return;
        }

        // PICK current element
        solve(nums,
              index + 1,
              product * nums[index],
              true);

        // NOT PICK current element
        solve(nums,
              index + 1,
              product,
              taken);
    }

    public long maxStrength(int[] nums) {

        solve(nums, 0, 1, false);

        return ans;
    }

    public static void main(String[] args) {

        int[] nums = {3, -1, -5, 2, 5, -9};

        Main obj = new Main();

        System.out.println(obj.maxStrength(nums));
    }
}


---

Why We Need taken

Because empty subset product becomes:

1

But empty subset is NOT allowed.

So:

taken == true

means at least one element selected.


---

Time Complexity

At every index:

pick
not pick

So total subsets:

2^n

Complexity:

O(2^n)


---

Simple Rule to Remember

Use parameter/state passing when:

You are constructing subsets/combinations/permutations.

solve(index,currentState)

Examples:

subset generation

backtracking

path building

permutations

combinations



---

Use divide & recombine when:

Subproblems are independent.

Examples:

merge sort

tree DP

Fibonacci DP

max path problems


Pattern:

return combine(left,right)

NOT for subset construction problems.

-> Right Code (Backtracking)

```java
class Main {

    long ans = Long.MIN_VALUE;

    long solve(int[] nums, int index, long product, boolean taken) {

        if (index == nums.length) {
            if (taken) {
                ans = Math.max(ans, product);
            }
            return ans;
        }

        solve(nums, index + 1, product * nums[index], true);

        solve(nums, index + 1, product, taken);

        return ans;
    }

    public long maxStrength(int[] nums) {
        return solve(nums, 0, 1, false);
    }

    public static void main(String[] args) {

        int[] nums = {3, -1, -5, 2, 5, -9};

        Main obj = new Main();

        System.out.println(obj.maxStrength(nums));
    }
}
```

-> Wrong Code (Divide & Recombine)

```java
class Main {

    long solve(int[] nums, int index) {

        if (index == nums.length) {
            return 1;
        }

        long pick = nums[index] * solve(nums, index + 1);

        long notpick = solve(nums, index + 1);

        return Math.max(pick, notpick);
    }

    public long maxStrength(int[] nums) {
        return solve(nums, 0);
    }

    public static void main(String[] args) {

        int[] nums = {3, -1, -5, 2, 5, -9};

        Main obj = new Main();

        System.out.println(obj.maxStrength(nums));
    }
}
```
