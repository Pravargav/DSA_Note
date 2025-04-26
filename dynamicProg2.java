import java.util.* ;
import java.io.*; 
public class Solution {

    public static int frogJump(int n, int height[]) {
         
         int dp[]=new int[n];
         Arrays.fill(dp,-1);
        int val= solve(n-1,height,dp);
        return val;
    }

    public static int solve(int ind,int[] height,int[] dp){
            if(ind==0) return 0;
            if(dp[ind]!=-1) return dp[ind];
    int jumpTwo = Integer.MAX_VALUE;
    int jumpOne= solve(ind-1, height,dp)+ Math.abs(height[ind]-height[ind-1]);
    if(ind>1)
        jumpTwo = solve(ind-2, height,dp)+ Math.abs(height[ind]-height[ind-2]);
    
    return dp[ind]=Math.min(jumpOne, jumpTwo);
    }



}

//coding ninjas frog jump striver 
