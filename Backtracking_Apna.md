-> print all subsets of array(i.e subsequences not subarrays)

```java
import java.util.*;
 
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        
        backtrack(arr, 0, new ArrayList<>(), result);
        
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }
 
    static void backtrack(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current)); // add current subset
        
        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);              // choose
            backtrack(arr, i + 1, current, result); // explore
            current.remove(current.size() - 1);     // un-choose
        }
    }
}
```
-> Print all permutations of array

```java
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
 
        permute(arr, 0);
    }
 
    static void permute(int[] arr, int index) {
 
        // Base case
        if (index == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }
 
        for (int i = index; i < arr.length; i++) {
 
            // Swap
            swap(arr, index, i);
 
            // Recurse
            permute(arr, index + 1);
 
            // Backtrack
            swap(arr, index, i);
        }
    }
 
    static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```
-> Stone Game leetcode - Alice bob kind of sums when both play optimally

```java
class Solution {
    int [][][] memo;
    public boolean stoneGame(int[] piles) {
        memo = new int[piles.length + 1][piles.length + 1][2];
        for(int [][] arr : memo)
            for(int [] subArr : arr)
                Arrays.fill(subArr, -1);
        
        return (helper(0, piles.length - 1, piles, 1) > 0);
    }
    
    public int helper(int l, int r, int [] piles, int ID){
        if(r < l)
            return 0;
        if(memo[l][r][ID] != -1)
            return memo[l][r][ID];
        
        int next = Math.abs(ID - 1);
        if(ID == 1)
            memo[l][r][ID] = Math.max(piles[l] + helper(l + 1, r, piles, next), piles[r] + helper(l, r - 1, piles, next));
        else
            memo[l][r][ID] = Math.min(-piles[l] + helper(l + 1, r, piles, next), -piles[r] + helper(l, r - 1, piles, next));
        
        return memo[l][r][ID];
    }
}
```
