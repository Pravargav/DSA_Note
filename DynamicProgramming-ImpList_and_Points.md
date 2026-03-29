----
-> path1 + path2 and return 1; at bae case gives count number of ways. Similarily fib(n-1) + fib(n-2) gives number of ways count to climb stairs with one and two steps.

 -> max(path1,arr[i]+path2) return 0; at bae case gives house robberer sum who never robs adjacent houses.
 
 -> longest increasing subsequences, stock based problems are 1d dynamic array type of problems which are similar to climbing stairs problem which has 0 or 1 ie pick or not pick and final answer is one permutation of the binary array like the 6 length array's final answer binary representation of longest increasing subsequence is 001111 or 110010 etc...the answer is (array a,b,c,d,e,f) c,d,e,f or  a,b,e etc ... out of 2 pow 6 permutations of binary numbers.
 
-> in dp on grids problem don't confuse dp array with the given values array like 1 or 2 steps possible restriction in climbing stairs here 3 movements possible only to the next column not 9 or anything else.

---

```java
//This is..
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
```

https://youtube.com/playlist?list=PLM68oyaqFM7QsfZJ8W2YHgiA2EDp3FryV&si=LTEcZVE1UBx4uWq3
----
**1)floydd warshall**

**2)Smallest Sum Contiguos Array(practice)**

**3)Stictly Increasing Array(practice)**

---

**1)Count ways To Reach Nth Stair(practice)**

**2)Stickler Thief(practice)**

---

**1)Maximum sum subarray(practice)**

**2)Maximum product subarray(practice)**

**3)Trapping water(practice)**

---

**Matrix Chain Multiplication concept**

---

**1)Longest Arthimatic Progression(practice)**

**2)Largest Square found in matrix(practice)**

---

**1)Diameter of Tree(Trees)**

**2)Maximum path sum between two leaf nodes(Trees)**

---

**1)Number of Paths(Grid)**

**2)Maximum path Sum(Grid)**

---

**1)Only one transation(Stocks)**

**2)Infinite Transactions(Stocks)**

**3)At most two transactions(Stocks)**

**4)At most k transactions(Stocks)**

---

**1)Longest Common Subsequence(String/Sequence)**

**2)Longest Common Substring(String/Sequence)**

**3)Longest Increasing Subsequence(String/Sequence)**

---

**1)0-1 knapsack bounded(Knapsack)**

**2)is Subset Sum(Knapsack)**
---
