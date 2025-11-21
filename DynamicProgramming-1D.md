

##### Frog jump problems

-> frog jump (i+1 or i+2)

```java
import java.util.*;
class TUF{
static int solve(int ind,int[] height,int[] dp){
    if(ind==0) return 0;
    if(dp[ind]!=-1) return dp[ind];
    int jumpTwo = Integer.MAX_VALUE;
    int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
    if(ind>1)
        jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
    
    return dp[ind]=Math.min(jumpOne, jumpTwo);
}


public static void main(String args[]) {

  int height[]={30,10,60 , 10 , 60 , 50};
  int n=height.length;
  int dp[]=new int[n];
  Arrays.fill(dp,-1);
  System.out.println(solve(n-1,height,dp));
}
}
```

-> frog jump k(i+1 to i+k possible){This solution can be used to  solve above where k=2}

```java

import java.util.*;

class TUF {
    // Recursive function to calculate the minimum cost to reach the end
    // from a given index with at most 'k' jumps.
    static int solveUtil(int ind, int[] height, int[] dp, int k) {
        // Base case: If we are at the beginning (index 0), no cost is needed.
        if (ind == 0)
            return 0;

        // If the result for this index has been previously calculated, return it.
        if (dp[ind] != -1)
            return dp[ind];

        int mmSteps = Integer.MAX_VALUE;

        // Loop to try all possible jumps from '1' to 'k'
        for (int j = 1; j <= k; j++) {
            // Ensure that we do not jump beyond the beginning of the array
            if (ind - j >= 0) {
                // Calculate the cost for this jump and update mmSteps with the minimum cost
                int jump = solveUtil(ind - j, height, dp, k) + Math.abs(height[ind] - height[ind - j]);
                mmSteps = Math.min(jump, mmSteps);
            }
        }

        // Store the minimum cost for this index in the dp array and return it.
        return dp[ind] = mmSteps;
    }

    // Function to find the minimum cost to reach the end of the array
    static int solve(int n, int[] height, int k) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1); // Initialize a memoization array to store calculated results
        return solveUtil(n - 1, height, dp, k); // Start the recursion from the last index
    }

    public static void main(String args[]) {
        int height[] = { 30, 10, 60, 10, 60, 50 };
        int n = height.length;
        int k = 2;
        System.out.println(solve(n, height, k)); // Print the result of the solve function
    }
}
```

##### House robber


->Stickler Thief(House Robber)

```java
class SticklerThief {
    public int FindMaxSum(int arr[], int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, -1);  // initialize with -1
        return rob(arr, n - 1, dp);
    }

    private int rob(int[] arr, int i, int[] dp) {
        if (i < 0) return 0;        // no houses left
        if (dp[i] != -1) return dp[i];  // already computed

        // Option 1: Rob this house, then jump to i-2
        int pick = arr[i] + rob(arr, i - 2, dp);

        // Option 2: Skip this house
        int notPick = rob(arr, i - 1, dp);

        // Store and return best result
        return dp[i] = Math.max(pick, notPick);
    }

    // Test
    public static void main(String[] args) {
        SticklerThief thief = new SticklerThief();
        int arr[] = {5, 5, 10, 100, 10, 5};
        int n = arr.length;
        System.out.println("Maximum loot = " + thief.FindMaxSum(arr, n));
    }
}
```

-> House Robber 2

```java
class Solution {
    public int rob(int[] nums) {
        int n=nums.length;
        if(n==1){
            return nums[0];
        }
        int temp1[]=new int[n-1];
        for(int i=0;i<n-1;i++){
            temp1[i]=nums[i];
            System.out.println(temp1[i]);
        }
        int temp2[]=new int[n-1];
        for(int i=1;i<n;i++){
            temp2[i-1]=nums[i];
            System.out.println(temp2[i-1]);
        }
        int dp1[]=new int[n-1];
        Arrays.fill(dp1,-1);
        int f1=fun(temp1,n-2,dp1);
        int dp2[]=new int[n-1];
        Arrays.fill(dp2,-1);
        int f2=fun(temp2,n-2,dp2);
        if(f1>f2){
            return f1;
        }
        return f2;
    }
    public int fun(int[] nums,int i,int[] dp){
        if(i<0) return 0;
        if(dp[i]!=-1) return dp[i];
        
        int pick=nums[i]+fun(nums,i-2,dp);
        int notpick=fun(nums,i-1,dp);
        return dp[i]=Math.max(pick,notpick);
    }
}
```

##### Max/min from both sides compute(Kandane's use if necessary)

-> max product subarray

```java
class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length; //size of array.

        int pre = 1, suff = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (pre == 0) pre = 1;
            pre *= nums[i];
            ans = Math.max(ans,pre);
        }

        for (int i = 0; i < n; i++) {
            if (suff == 0) suff = 1;
            suff *= nums[n - i - 1];
            ans = Math.max(ans, suff);
        }
        return ans;
    }
}

```
->Trapping Rain water

```java
import java.util.*;
class TUF {
    static int trap(int[] arr) {
        int n = arr.length;
        int waterTrapped = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            int leftMax = 0, rightMax = 0;
            while (j >= 0) {
                leftMax = Math.max(leftMax, arr[j]);
                j--;
            }
            j = i;
            while (j < n) {
                rightMax = Math.max(rightMax, arr[j]);
                j++;
            }
            waterTrapped += Math.min(leftMax, rightMax) - arr[i];
        }
        return waterTrapped;
    }
    public static void main(String args[]) {
        int arr[] = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("The duplicate element is " + trap(arr));
    }
}
```



##### Example notes for reference(not actual problems)

````markdown
-> House robber 4 (without list)


    
```java
class Solution {

    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        return fun( nums, 0, k);
    }

    public int fun( int nums[], int idx, int k) {
        if(k==0)
           return 0;
        if(idx>=nums.length) return Integer.MAX_VALUE;

        int take = Math.max(nums[idx],fun( nums,idx+2, k-1));
        int not_take= fun(nums ,idx+1, k);
        return Math.min(take,not_take);
    }
}
```
--------------------------(or)---------------------------------

->House robber 4 (with list)
    
```java
class Solution {
    int min = Integer.MAX_VALUE;

    public int minCapability(int[] nums, int k) {
        int n = nums.length;
        List<Integer> lt = new ArrayList<>();
        fun(n, nums, lt, 0, k);
        return min;
    }

    public void fun(int n, int nums[], List<Integer> lt, int idx, int k) {
        if (idx >= n) {
            int max = Integer.MIN_VALUE;
            if (lt.size() >= k) {
                for (int i = 0; i < lt.size(); i++) {
                    max = Math.max(max, lt.get(i));
                }
                // System.out.println(max);
                min = Math.min(min, max);
            }
            return;
        }

        lt.add(nums[idx]);
        fun(n, nums, lt, idx + 2, k);
        lt.remove(lt.size() - 1);
        fun(n, nums, lt, idx + 1, k);
    }
}
```

````

````markdown

-> VERY VERY VERY IMPORTANT CODE FOR MAX SUFFIX, MAX PREFIX OF SLIDING WINDOW

-> used in reducing a problem from  nested loop to single loop 2-3 times

```java
import java.util.*;

class Solution {
    public int maxSum(int[] nums, int firstLen, int secondLen) {

        // -----------------------------------------
        // FIRST WINDOW (size = firstLen)
        // -----------------------------------------

        List<List<Integer>> firstWinRanges = new ArrayList<>();   // stores [start, end]
        List<Integer> firstWinSums = new ArrayList<>();           // stores window sums

        int firstWindowSum = 0;

        // initial window sum
        for (int i = 0; i < firstLen; i++) {
            firstWindowSum += nums[i];
        }
        firstWinSums.add(firstWindowSum);
        firstWinRanges.add(Arrays.asList(0, firstLen - 1));

        // sliding forward
        for (int i = 1; i <= nums.length - firstLen; i++) {
            firstWindowSum = firstWindowSum - nums[i - 1] + nums[i + firstLen - 1];
            firstWinSums.add(firstWindowSum);
            firstWinRanges.add(Arrays.asList(i, i + firstLen - 1));
        }

        System.out.println("---");

        // -----------------------------------------
        // SECOND WINDOW (size = secondLen)
        // -----------------------------------------

        List<List<Integer>> secondWinRanges = new ArrayList<>();
        List<Integer> secondWinSums = new ArrayList<>();

        int secondWindowSum = 0;

        // initial window sum
        for (int i = 0; i < secondLen; i++) {
            secondWindowSum += nums[i];
        }
        secondWinSums.add(secondWindowSum);
        secondWinRanges.add(Arrays.asList(0, secondLen - 1));

        // sliding forward
        for (int i = 1; i <= nums.length - secondLen; i++) {
            secondWindowSum = secondWindowSum - nums[i - 1] + nums[i + secondLen - 1];
            secondWinSums.add(secondWindowSum);
            secondWinRanges.add(Arrays.asList(i, i + secondLen - 1));
        }

        System.out.println("---");

        // -----------------------------------------
        // Max values from front & back (based on secondWinSums)
        // -----------------------------------------

        List<Integer> maxPrefix = new ArrayList<>(); // max from 0..i
        List<Integer> maxSuffix = new ArrayList<>(); // max from i..end

        // prefix max (front → back)
        int prefixMax = Integer.MIN_VALUE;
        for (int sum : secondWinSums) {
            prefixMax = Math.max(prefixMax, sum);
            maxPrefix.add(prefixMax);
        }

        System.out.println("---");

        // suffix max (back → front)
        int suffixMax = Integer.MIN_VALUE;
        for (int i = secondWinSums.size() - 1; i >= 0; i--) {
            suffixMax = Math.max(suffixMax, secondWinSums.get(i));
            maxSuffix.add(suffixMax);
        }
        Collections.reverse(maxSuffix);

        // -----------------------------------------
        // Debug Output (same as before)
        // -----------------------------------------
        System.out.println(firstWinRanges);
        System.out.println(firstWinSums);

        System.out.println(secondWinRanges);
        System.out.println(maxPrefix);
        System.out.println(maxSuffix);

        return 0;
    }
} 
```
````

````markdown
-> pk is sum of a subarray containing all positive elements

-> nk is sum of a subarray containing all negative elements

-> For max sum subarray first remove the nk from both ends if they exist

   ex: n1 p1 n2 p2 n3 to p1 n2 p2, n1 p1 n2 p2 n3 p3 to p1 n2 p2 n3 p3, p1 n1 p2 n2 p3 n3 to p1 n1 p2 n2 p3

   ie. the final output should contain a pk on both ends

-> Theorm1 -

   if p2 + n2 + p3 + n3 + p4 + n4 > p3 + n3 + p4 + n4 then p2 + n2 > 0

   if p2 + n2 + p3 + n3 + p4 + n4 < p3 + n3 + p4 + n4 then p2 + n2 < 0 then make p2 + n2 = 0 i.e dont include p2 + n2 in sum

-> Kandane algorithm-

```java
 public int maxSubArray(int[] nums) {
    int currentSum = 0;
    int maxSum = Integer.MIN_VALUE;

    for (int n : nums) {
        currentSum += n;

        if (currentSum > maxSum) {
            maxSum = currentSum;
        }

        if (currentSum < 0) {
            currentSum = 0;
        }
    }

    return maxSum;
}
```
    
````
