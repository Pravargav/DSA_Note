```java
import java.util.HashMap;
import java.util.Map;

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
Arrays.sort(arr);
Arrays.toString(arr)
Arrays.sort(arr, Collections.reverseOrder());
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list);
Collections.sort(list, Collections.reverseOrder());
Collections.sort(list, (a, b) -> b - a);
```
