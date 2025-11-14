

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
-> Smallest sum subarray

```java
import java.util.Arrays;
import java.util.List;

public class Main {
    public static int smallestSumSubarr(List<Integer> arr) {
        int n = arr.size();
        int min_sum = Integer.MAX_VALUE;
        int temp;
        for (int i = 0; i < n; i++) {
            temp = 0;
            for (int j = i; j < n; j++) {
                temp += arr.get(j);
                min_sum = Math.min(min_sum, temp);
            }
        }
        return min_sum;
    }

    public static void main(String[] args) {
        List<Integer> arr = Arrays.asList(3, -4, 2, -3, -1, 7, -5);
        System.out.println(smallestSumSubarr(arr));
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
-> Sliding window and max value till a point from back and front - used in reducing a problem from  nested loop to single loop 2-3 times

```java
class Solution {
    public int maxSumTwoNoOverlap(int[] nums, int firstLen, int secondLen) {

        List<List<Integer>> fl=new ArrayList<>();
        List<List<Integer>> sl=new ArrayList<>();
        List<Integer> lmk=new ArrayList<>();



        //sliding window sum - window length firstLen
        int sumft=0;
        for(int i=0;i<firstLen;i++){
            sumft+=nums[i];
        }
        lmk.add(sumft);
        int r=firstLen-1;
        List<Integer> fl2=Arrays.asList(0,r);
        fl.add(fl2);
      
        for(int i=1;i<=nums.length - firstLen;i++){
            sumft=sumft-nums[i-1];
            sumft=sumft+nums[i+firstLen-1];
            int s=i+firstLen-1;
            List<Integer> fl3=Arrays.asList(i,s);
            fl.add(fl3);
            lmk.add(sumft);
        }
        System.out.println("---");


        //sliding window sum - window length secondLen
        List<Integer> lk=new ArrayList<>();
        int sumbk=0;
        for(int i=0;i<secondLen;i++){
            sumbk+=nums[i];
        }
        lk.add(sumbk);
        int q=secondLen-1;
        List<Integer> sl2=Arrays.asList(0,q);
        sl.add(sl2);
        for(int i=1;i<=nums.length - secondLen;i++){
            sumbk=sumbk-nums[i-1];
            sumbk=sumbk+nums[i+secondLen-1];
            int p=i+secondLen-1;
            List<Integer> sl3=Arrays.asList(i,p);
            sl.add(sl3);
            lk.add(sumbk);
        }
        System.out.println("---");



        List<Integer> lf=new ArrayList<>();
        List<Integer> bf=new ArrayList<>();
        



        //Saving the max value from front
        int maxf=Integer.MIN_VALUE;
        for(int i=0;i<lk.size();i++){
            maxf=Math.max(lk.get(i),maxf);
            lf.add(maxf);
        }
        System.out.println("---");



        //Saving the max value from back
        int maxb=Integer.MIN_VALUE;
        for(int i=lk.size()-1;i>=0;i--){
            maxb=Math.max(lk.get(i),maxb);
            bf.add(maxb);
        }
        Collections.reverse(bf);


        System.out.println(fl);
        System.out.println(lmk);

        System.out.println(sl);
        System.out.println(lf);
        System.out.println(bf);


        return 0;
    }
}
```
````
