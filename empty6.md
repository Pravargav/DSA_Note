//dp min max number of paths

import java.util.*;

class TUF {
    // Helper function to calculate the number of paths through the maze
    static int mazeObstaclesUtil(int i, int j, int[][] maze, int[][] dp) {
        // If there's an obstacle at this cell or out of bounds, return 0
        if (i >= 0 && j >= 0 && maze[i][j] == -1)
            return 0;
        // If we've reached the start cell, there's one valid path
        if (i == 0 && j == 0)
            return 1;
        // If we're out of bounds, return 0
        if (i < 0 || j < 0)
            return 0;
        // If we've already calculated this cell, return the stored result
        if (dp[i][j] != -1)
            return dp[i][j];

        // Calculate the number of paths by moving up and left
        int up = mazeObstaclesUtil(i - 1, j, maze, dp);
        int left = mazeObstaclesUtil(i, j - 1, maze, dp);

        // Store the result and return it
        return dp[i][j] = up + left;
    }

    // Main function to calculate the number of paths through the maze
    static int mazeObstacles(int n, int m, int[][] maze) {
        int dp[][] = new int[n][m];
        
        // Initialize the dp array with -1
        for (int row[] : dp)
            Arrays.fill(row, -1);
        
        // Call the helper function starting from the bottom-right cell
        return mazeObstaclesUtil(n - 1, m - 1, maze, dp);
    }

    public static void main(String args[]) {
        // Define the maze
        int[][] maze = {
            {0, 0, 0},
            {0, -1, 0},
            {0, 0, 0}
        };

        int n = maze.length;
        int m = maze[0].length;

        // Calculate and print the number of paths through the maze
        System.out.println(mazeObstacles(n, m, maze));
    }
}

---------------------------------------------------------------------------------------
//dp min max path sum

import java.util.*;

class TUF {
  // Function to find the maximum number of chocolates using dynamic programming
  static int maxChocoUtil(int i, int j1, int j2, int n, int m, int[][] grid,
                          int[][][] dp) {
    // Check if j1 and j2 are valid column indices
    if (j1 < 0 || j1 >= m || j2 < 0 || j2 >= m)
      return (int) (Math.pow(-10, 9));

    // If we are at the last row, return the sum of chocolates in the selected columns
    if (i == n - 1) {
      if (j1 == j2)
        return grid[i][j1];
      else
        return grid[i][j1] + grid[i][j2];
    }

    // If the result for this state is already computed, return it
    if (dp[i][j1][j2] != -1)
      return dp[i][j1][j2];

    int maxi = Integer.MIN_VALUE;
    // Iterate through possible moves in the next row
    for (int di = -1; di <= 1; di++) {
      for (int dj = -1; dj <= 1; dj++) {
        int ans;
        // If j1 and j2 are the same, add chocolates from grid[i][j1] only
        if (j1 == j2)
          ans = grid[i][j1] + maxChocoUtil(i + 1, j1 + di, j2 + dj, n, m, grid, dp);
        else
          // Add chocolates from both j1 and j2
          ans = grid[i][j1] + grid[i][j2] + maxChocoUtil(i + 1, j1 + di, j2 + dj, n, m, grid, dp);
        // Update maxi with the maximum result
        maxi = Math.max(maxi, ans);
      }
    }
    // Store the result in the dp array and return it
    return dp[i][j1][j2] = maxi;
  }

  // Function to find the maximum number of chocolates
  static int maximumChocolates(int n, int m, int[][] grid) {
    // Create a 3D array to store computed results
    int dp[][][] = new int[n][m][m];

    // Initialize the dp array with -1
    for (int row1[][] : dp) {
      for (int row2[] : row1) {
        Arrays.fill(row2, -1);
      }
    }

    // Call the utility function to find the maximum number of chocolates
    return maxChocoUtil(0, 0, m - 1, n, m, grid, dp);
  }

  public static void main(String args[]) {
    int matrix[][] = {{2, 3, 1, 2},
                      {3, 4, 2, 2},
                      {5, 6, 3, 5}};
    int n = matrix.length;
    int m = matrix[0].length;

    // Call the maximumChocolates function and print the result
    System.out.println(maximumChocolates(n, m, matrix));
  }
}

