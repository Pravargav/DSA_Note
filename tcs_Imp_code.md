```java
public class FrequencyCounter {
    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "apple", "orange", "banana", "apple"};
        HashMap<String, Integer> map = new HashMap<>();
        for (String str : arr) {
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```
```java
Arrays.toString(arr)
Arrays.sort(arr);
Arrays.sort(arr, Collections.reverseOrder());
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list, (a, b) -> b - a);
```
```java
//Rotate array by k pos
import java.util.*;

class ArrayRotation {

    static void reverseSection(int[] array, int start, int end) {
        while (start < end) {
            int temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }
    // Function to left rotate array by k positions
    static void leftRotate(int[] array, int positions) {
        int length = array.length;
        positions %= length;
        if (positions == 0) return;

        reverseSection(array, 0, positions - 1);
        reverseSection(array, positions, length - 1);
        reverseSection(array, 0, length - 1);
    }

    // Function to right rotate array by k positions
    static void rightRotate(int[] array, int positions) {
        int length = array.length;
        leftRotate(array, length - (positions % length));
    }
}


```
```java
System.out.println((double) (arr[ind1] + arr[ind2]) / 2); 
```
```java
Set<Integer> set = new HashSet<>(list);
Set<Integer> set = new LinkedHashSet<>(list);//insertion order
Set<Integer> set = new TreeSet<>(list);//sorted order
ArrayList<Integer> list = new ArrayList<>(set);
```
