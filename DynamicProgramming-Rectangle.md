-> Maximum Rectangle area with all 1's - uses histogram concept - from a bar of histogram count until the bar less height than the current from both sides i.e. 
area = arr[i]*(nextsmallbar-previoussmallbar-1)

[link1](https://www.youtube.com/watch?v=Bzat9vgD0fs)

[link2](https://www.youtube.com/watch?v=tOylVCugy9k&list=PLgUwDviBIf0qUlt5H_kiKYaNSqJ81PMMY&index=56)

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
