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

-> leetcode -fair distribution of cookies (backtracking concept)

```java
class Solution {
    int res=Integer.MAX_VALUE; 
    public int distributeCookies(int[] cookies, int k) {
        int n=cookies.length;
        int[]alloc=new int[k];
        gen(cookies,alloc,0,k);
        return res;
    }

      
    public void gen(int arr[],int []alloc,int index,int k){
        if(index==arr.length){
            pall(alloc);
            return;
        }
        for(int st=0;st<k;st++){
            alloc[st]+=arr[index];
            gen(arr,alloc,index+1,k);
            alloc[st]-=arr[index];
        }
    }

    public void pall(int[] alloc){
        for(int i=0;i<alloc.length;i++){
            System.out.print(alloc[i]+" ");
        }
        System.out.println();
    }
}
```


-> Generate all subsets
```java
import java.util.*;

public class AllSubsets {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        generateSubsets(arr, 0, current, result);

        // Print all subsets
        for (List<Integer> subset : result) {
            System.out.println(subset);
        }
    }

    static void generateSubsets(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        // Add the current subset to result (copy it)
        result.add(new ArrayList<>(current));

        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);                        // choose
            generateSubsets(arr, i + 1, current, result); // explore
            current.remove(current.size() - 1);          // un-choose (backtrack)
        }
    }
} 
```

->Generate all permutaitons

```java
import java.util.*;

public class PermutationsList {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean[] used = new boolean[arr.length];

        permute(arr, used, current, result);

        // Print all permutations
        for (List<Integer> perm : result) {
            System.out.println(perm);
        }
    }

    static void permute(int[] arr, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == arr.length) {
            result.add(new ArrayList<>(current)); // copy current permutation
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if (!used[i]) {
                used[i] = true;
                current.add(arr[i]);         // choose
                permute(arr, used, current, result); // explore
                current.remove(current.size() - 1);  // un-choose
                used[i] = false;
            }
        }
    }
} 
```

-> N-Queen's problem

```java
import java.util.*;
 
public class Main {
 
    static int N = 4;
 
    public static void main(String[] args) {
 
        char[][] board = new char[N][N];
 
        for (int i = 0; i < N; i++) {
            Arrays.fill(board[i], '.');
        }
 
        solve(board, 0);
    }
 
    static void solve(char[][] board, int row) {
 
        if (row == N) {
            printBoard(board);
            return;
        }
 
        for (int col = 0; col < N; col++) {
 
            if (isSafe(board, row, col)) {
 
                board[row][col] = 'Q';
 
                solve(board, row + 1);
 
                board[row][col] = '.';
            }
        }
    }
 
    static boolean isSafe(char[][] board, int row, int col) {
 
        for (int i = 0; i < row; i++) {
            if (board[i][col] == 'Q') {
                return false;
            }
        }
 
        for (int i = row - 1, j = col - 1;
             i >= 0 && j >= 0;
             i--, j--) {
 
            if (board[i][j] == 'Q') {
                return false;
            }
        }
 
        for (int i = row - 1, j = col + 1;
             i >= 0 && j < N;
             i--, j++) {
 
            if (board[i][j] == 'Q') {
                return false;
            }
        }
 
        return true;
    }
 
    static void printBoard(char[][] board) {
         //print(board);
    }
}
```
-> Merge Sort

```java
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
 
        int[] arr = {5, 2, 8, 1, 3};
 
        mergeSort(arr, 0, arr.length - 1);
 
        System.out.println(Arrays.toString(arr));
    }
 
    static void mergeSort(int[] arr, int low, int high) {
 
        if (low >= high) {
            return;
        }
 
        int mid = (low + high) / 2;
 
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);
 
        merge(arr, low, mid, high);
    }
 
    static void merge(int[] arr, int low, int mid, int high) {
 
        int[] temp = new int[high - low + 1];
 
        int left = low;
        int right = mid + 1;
        int k = 0;
 
        while (left <= mid && right <= high) {
 
            if (arr[left] <= arr[right]) {
                temp[k++] = arr[left++];
            } else {
                temp[k++] = arr[right++];
            }
        }
 
        while (left <= mid) {
            temp[k++] = arr[left++];
        }
 
        while (right <= high) {
            temp[k++] = arr[right++];
        }
 
        for (int i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
    }
}
```
-> Quick Sort 

```java
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
 
        int[] arr = {5, 2, 8, 1, 3};
 
        quickSort(arr, 0, arr.length - 1);
 
        System.out.println(Arrays.toString(arr));
    }
 
    static void quickSort(int[] arr, int low, int high) {
 
        if (low >= high) {
            return;
        }
 
        int pivotIndex = partition(arr, low, high);
 
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }
 
    static int partition(int[] arr, int low, int high) {
 
        int pivot = arr[high];
 
        int i = low - 1;
 
        for (int j = low; j < high; j++) {
 
            if (arr[j] < pivot) {
 
                i++;
 
                swap(arr, i, j);
            }
        }
 
        swap(arr, i + 1, high);
 
        return i + 1;
    }
 
    static void swap(int[] arr, int i, int j) {
 
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
```
-> combination sum leetcode

```java
import java.util.*;
 
public class Main {
 
    public static void main(String[] args) {
 
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
 
        List<List<Integer>> result = new ArrayList<>();
 
        backtrack(candidates, target, 0, new ArrayList<>(), result);
 
        System.out.println(result);
    }
 
    static void backtrack(int[] arr,
                          int target,
                          int index,
                          List<Integer> current,
                          List<List<Integer>> result) {
 
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
 
        if (target < 0 || index == arr.length) {
            return;
        }
 
        current.add(arr[index]);
 
        backtrack(arr,
                  target - arr[index],
                  index,
                  current,
                  result);
 
        current.remove(current.size() - 1);
 
        backtrack(arr,
                  target,
                  index + 1,
                  current,
                  result);
    }
}
```
