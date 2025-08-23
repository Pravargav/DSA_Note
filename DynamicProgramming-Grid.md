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

-> Triangle (Note: For Lists use null instead of -1 unless we get time limit exceeded error, Also the concept used is dfs)

```java


class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

     
        List<List<Integer>> dp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < triangle.get(i).size(); j++) {
                row.add(null); 
            }
            dp.add(row);
        }

        return memo(triangle, 0, 0, dp);
    }

    private int memo(List<List<Integer>> triangle, int row, int col, List<List<Integer>> dp) {
     
        if (row == triangle.size() - 1) {
            return triangle.get(row).get(col);
        }

      
        if (dp.get(row).get(col) != null) {
            return dp.get(row).get(col);
        }

        
        int down = memo(triangle, row + 1, col, dp);
        int downRight = memo(triangle, row + 1, col + 1, dp);

        int best = triangle.get(row).get(col) + Math.min(down, downRight);

        dp.get(row).set(col, best); 

        return best;
    }
}

```

-> Minimum falling path (Note: For Lists use null instead of -1 unless we get time limit exceeded error, Also the concept used is dfs)

```java
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        int n = matrix.length;
        Integer[][] dp = new Integer[n][n];
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, memo(matrix, dp, 0, j));
        }
        return ans;
    }

    private int memo(int[][] matrix, Integer[][] dp, int i, int j) {
        int n = matrix.length;
        if (j < 0 || j >= n) return Integer.MAX_VALUE;
        if (i == n - 1) return matrix[i][j];

        if (dp[i][j] != null) return dp[i][j];

        int down = memo(matrix, dp, i + 1, j);
        int left = memo(matrix, dp, i + 1, j - 1);
        int right = memo(matrix, dp, i + 1, j + 1);

        dp[i][j] = matrix[i][j] + Math.min(down, Math.min(left, right));
        return dp[i][j];
    }
}
```
