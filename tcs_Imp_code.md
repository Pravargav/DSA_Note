```java
import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {
    public static void main(String[] args) {
        String[] arr = {"apple", "banana", "apple", "orange", "banana", "apple"};
        
        // Initialize HashMap: Key is the String, Value is the Count
        HashMap<String, Integer> map = new HashMap<>();

        for (String str : arr) {
            // If the string is already in the map, increment its count
            // Otherwise, put it in the map with a value of 1
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        // Display the results
        System.out.println("String Frequencies:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
```
