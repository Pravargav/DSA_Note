-> Maximum Rectangle area with all 1's - uses histogram concept - from a bar of histogram count until the bar less height than the current from both sides i.e. 
area = arr[i]*(nextsmallbar-previoussmallbar-1) - Monotonic Stack concept 


[link1](https://www.youtube.com/watch?v=Bzat9vgD0fs)

[link2](https://www.youtube.com/watch?v=tOylVCugy9k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=56)

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int maxArea = 0;
        int cols = matrix[0].length;
        int[] heights = new int[cols];

        for (char[] row : matrix) {
            for (int i = 0; i < cols; i++) {
                // Update heights: increment if '1', reset if '0'
                heights[i] = (row[i] == '1') ? heights[i] + 1 : 0;
            }
            // Compare max area of each row and find final max of all rows
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Nearest Smaller to Left
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            left[i] = (stack.isEmpty()) ? -1 : stack.peek();
            stack.push(i);
        }

        stack.clear(); // Reuse stack

        // Nearest Smaller to Right
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            right[i] = (stack.isEmpty()) ? n : stack.peek();
            stack.push(i);
        }

        // Compute max area
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            maxArea = Math.max(maxArea, heights[i] * width);
        }

        return maxArea;
    }
}
```

-> Count Square Submatrices with all 1's - uses concept of number of squares end at the specific point - (i,j) i.e. min(dp[i-1][j],dp[i-1][j-1],dp[i][j-1]);

[link1](https://www.youtube.com/watch?v=auS1fynpnjo&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=57)

```java
class Solution {
    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] dp = new int[m][n];
        int totalSquares = 0;
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = 1; // first row/col can only form 1x1 square
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i-1][j], 
                                       Math.min(dp[i][j-1], dp[i-1][j-1]));
                    }
                    totalSquares += dp[i][j]; 
                }
            }
        }
        
        return totalSquares;
    }
}
```
