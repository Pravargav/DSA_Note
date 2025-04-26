import java.util.*; 

public class Solution {

        public static int ninjaTraining(int n, int points[][]) { 

       int[][] dp = new int[n][3];  

      for (int[] i: dp) {  

          Arrays.fill(i,-1); 

       }    

    for (int i=0;i<3;i++) {   

         dp[0][i] = points[0][i]; 

       }  

      for (int i=1;i<n;i++) {  

          for (int j=0;j<3;j++) {   

             int maxi=0;   

             for (int k=0;k<3;k++) {   

                 if (j==k)    

                    continue;     

               maxi = Math.max(maxi,dp[i-1][k]);  

              }         

       dp[i][j] = points[i][j] + maxi;    

        }  

      }    

    int maxi = 0;  

      for (int i=0;i<3;i++) {   

         maxi = Math.max(maxi,dp[n-1][i]); 

       }  

      return maxi; 

   }

 }
