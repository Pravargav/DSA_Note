import java.util.* ;
import java.io.*; 
import java.util.*;
public class Solution {
	public static int maximumNonAdjacentSum(ArrayList<Integer> nums) {
		// Write your code here.
		Integer[] dp = new Integer[nums.size()];
        return recc(nums,0,dp);
	}

	public static int recc(ArrayList<Integer> nums,int i,Integer[] dp){

        if(i>=nums.size())

            return 0;

        if(dp[i]!=null)

            return dp[i];

        int acc = nums.get(i) + recc(nums,i+2,dp); //accept

        int rej = recc(nums,i+1,dp); //reject

        return dp[i] = Math.max(acc,rej);

    }
}

//Maximum sum of non adjecnet elements coding-ninjas
//pravargav24@gmail.com
