int fun(int m, int n) {
    int arr[][]=new int[m][n];
	for(int i=0;i<m;i++){
	  for(int j=0;j<n;j++){
	     if(i==0||j==0){
		arr[i][j]=1;
	     }
	     else{
		arr[i][j]=arr[i-1][j]+arr[i][j-1];
	     }  
	}
}
   return arr[m-1][n-1];
}

//Equivalent to-
int fun(int m, int n) {
    if (i == 0 && j == 0)
        return 1;
    if (i < 0 || j < 0)
        return 0;
    up = fun(i - 1, j);
    left = fun(i, j - 1);
    return up + left;
}

//Equivalent to -
int fun(int m, int n) {
    if (i == 0 && j == 0)
        return 1;
    if (i < 0 || j < 0)
        return 0;
    if(dp[i][j]!=0) return dp[i][j];
    up = fun(i - 1, j);
    left = fun(i, j - 1);
    return dp[i][j]=up + left;
}
