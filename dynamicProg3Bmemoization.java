import java.util.*; 

public class Solution {

        public static int ninjaTraining(int n, int points[][]) { 

       int[][] dp = new int[n][4];  

      for (int[] i: dp) {  

          Arrays.fill(i,-1); 

       }    

       return calMaxPointMemoization(n-1, 3, points, dp);

   }

   public static int calMaxPointMemoization(int day, int lastTask, int[][] points, int[][] dp){

        //if day is 0 we need to take the task with maximum points which was not performed in day 1

        if(day==0){

            int maximumPoint = 0;

            for(int task = 0; task<3; task++){

                if(task!=lastTask){

                    maximumPoint = Math.max(maximumPoint, points[0][task]);

                }

            }

            return maximumPoint;

        }

 

        if(dp[day][lastTask]!=-1) return dp[day][lastTask];

 

        int maximumPoint = 0;

        for(int task=0; task<3; task++){

            if(task!=lastTask){

                int point = points[day][task] + calMaxPointMemoization(day-1, task, points, dp);

                maximumPoint = Math.max(maximumPoint, point);

            }

        }

 

        return dp[day][lastTask] = maximumPoint;

    }

 }
