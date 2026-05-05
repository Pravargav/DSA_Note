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
