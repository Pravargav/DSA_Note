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
```java
public int[] sortByFrequency(int[] arr) {
    Map<Integer, Integer> freq = new HashMap<>();

    for (int num : arr) {
        freq.put(num, freq.getOrDefault(num, 0) + 1);
    }

    List<Integer> list = new ArrayList<>();
    for (int num : arr) list.add(num);

    Collections.sort(list, (a, b) -> {
        if (!freq.get(a).equals(freq.get(b)))
            return freq.get(b) - freq.get(a);
        return a - b;
    });

    for (int i = 0; i < arr.length; i++) {
        arr[i] = list.get(i);
    }

    return arr;
}

```
```java
public int[] replaceWithRank(int[] arr) {
    int[] sortedArr = arr.clone();
    Arrays.sort(sortedArr);

    HashMap<Integer, Integer> rankMap = new HashMap<>();
    int rank = 1;

    for (int num : sortedArr) {
        if (!rankMap.containsKey(num)) {
            rankMap.put(num, rank++);
        }
    }

    int[] result = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
        result[i] = rankMap.get(arr[i]);
    }

    return result;
}
```
```java
class Solution {
    public int maxProductsubarray(int[] nums) {
        int n = nums.length; //size of array.

        int pre = 1, suff = 1;
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (pre == 0) pre = 1;
            pre *= nums[i];
            ans = Math.max(ans,pre);
        }

        for (int i = 0; i < n; i++) {
            if (suff == 0) suff = 1;
            suff *= nums[n - i - 1];
            ans = Math.max(ans, suff);
        }
        return ans;
    }
}

```
-> https://youtu.be/hnswaLJvr6g?si=pqkYYKi773NwxUi9

-> An Amrstrong number is a number that is equal to the sum of its own digits each raised to the power of the number of digits.

-> A perfect number is defined as a number that is the sum of its proper divisors ( all its positive divisors excluding itself).

-> A year is a leap year only if it satisfies the following condition-  i) The year is divisible by ii) the year is divisible by 4 but not by 100

```java
static double myPow(double x, int n) {
    double ans = 1;
    if (x == 0 || x == 1) {
        return x;
    }
    if (n < 0) {
        x = 1 / x;
        n = -(n + 1);
        ans *= x;
    }
    while (n > 0) {
        if (n % 2 == 1) {
            ans *= x;
            n--;
        } else {
            x *= x;
            n /= 2;
        }
    }
    return ans;
}
```
