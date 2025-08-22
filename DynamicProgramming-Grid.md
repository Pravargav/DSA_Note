-> Unique Paths 

```java
class Solution {
    public int uniquePaths(int m, int n) {
       
        int dp[][] = new int[m][n];
        

        for (int[] row : dp)
            Arrays.fill(row, -1);

        
        return memo(m - 1, n - 1, dp);
    }

    int memo(int i, int j, int[][] dp) {
   
        if (i == 0 && j == 0)
            return 1;
        
      
        if (i < 0 || j < 0)
            return 0;

       
        if (dp[i][j] != -1)
            return dp[i][j];

    
        int up = memo(i - 1, j, dp);
        int left = memo(i, j - 1, dp);

     
        return dp[i][j] = up + left;
    }
}
```

-> Unique paths 2 ( Same code as above just add  "if (grid[i][j] == 1) return 0;")

```java

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int dp[][] = new int[m][n];
        for (int arr[] : dp) {
            Arrays.fill(arr, -1);
        }
        return memo(obstacleGrid, m - 1, n - 1, dp);
    }
        public int memo(int grid[][], int i, int j, int dp[][]) {
        if (i < 0 || j < 0) {
            return 0;
        }

        if (grid[i][j] == 1) {
            return 0;
        }

        if (i == 0 && j == 0) {
            return 1; 
        }

        if (dp[i][j] != -1) {
            return dp[i][j]; 
        }

        int up = memo(grid, i - 1, j, dp);
        int left = memo(grid, i, j - 1, dp);

        return dp[i][j] = up + left;
    }
}

```

-> Minimum Path Sum

```java
class Solution 
{
    public int minPathSum(int[][] grid) 
    {
        int m = grid.length;
        int n = grid[0].length;
        int dp[][] = new int[m][n];
        for (int row[] : dp){
            Arrays.fill(row, -1);
        }
        return memo(m - 1, n - 1, grid, dp);
		}
    int memo(int i, int j, int[][] matrix, int[][] dp) {
       
        if (i == 0 && j == 0)
            return matrix[0][0];
        if (i < 0 || j < 0)
            return (int) Math.pow(10, 9); 
        if (dp[i][j] != -1)
            return dp[i][j]; 

        int up = matrix[i][j] + memo(i - 1, j, matrix, dp);
        int left = matrix[i][j] + memo(i, j - 1, matrix, dp);


        return dp[i][j] = Math.min(up, left);
    }

  
}
```
