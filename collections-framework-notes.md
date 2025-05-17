->(ArrayList) Definition: A dynamic array is a resizable array that grows or shrinks in size as elements are added or removed.
Memory Allocation: Elements are stored in a contiguous block of memory.

->(LinkedList) Definition: A doubly linked list is a linear data structure where each element (node) contains a value and two pointers: one to the previous node and one to the next node.
Memory Allocation: Elements are stored in non-contiguous memory locations, with each node dynamically allocated.

->Methods Specific to ArrayList

1)void ensureCapacity(int minCapacity)
2)void trimToSize()
3)void removeRange(int fromIndex, int toIndex)

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>();
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Orange");

        List<String> list2 = new ArrayList<>();
        list2.add("Banana");
        list2.add("Grapes");

        // removeAll: Remove elements from list1 that are also in list2
        list1.removeAll(list2);
        System.out.println("After removeAll: " + list1); // Output: [Apple, Orange]

        // Add elements back for retainAll example
        list1.clear();
        list1.add("Apple");
        list1.add("Banana");
        list1.add("Orange");

        // retainAll: Keep only elements in list1 that are also in list2
        list1.retainAll(list2);
        System.out.println("After retainAll: " + list1); // Output: [Banana]
    }
}
```

```java

import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new LinkedList<>();
        list2.add(2);
        list2.add(4);
        list2.add(6);

        // removeAll: Remove elements from list1 that are in list2
        list1.removeAll(list2);
        System.out.println("After removeAll: " + list1); // Output: [1, 3]

        // Add elements back for retainAll example
        list1.clear();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        // retainAll: Keep only elements in list1 that are also in list2
        list1.retainAll(list2);
        System.out.println("After retainAll: " + list1); // Output: [2, 4]
    }
}
```

```java
import java.util.ArrayList;
import java.util.List;

public class ToArrayExample {
    public static void main(String[] args) {
        // Create an ArrayList
        List<String> list = new ArrayList<>();
        list.add("Apple");
        list.add("Banana");
        list.add("Cherry");

        // Example 1: Using Object[] toArray()
        Object[] objectArray = list.toArray();
        System.out.println("Object[] toArray():");
        for (Object obj : objectArray) {
            System.out.println(obj);
        }
        // Output:
        // Apple
        // Banana
        // Cherry

        // Example 2: Using <T> T[] toArray(T[] a)
        String[] stringArray1 = new String[list.size()];
        stringArray1 = list.toArray(stringArray1);
        System.out.println("\n<T> T[] toArray(T[] a) with exact-sized array:");
        for (String str : stringArray1) {
            System.out.println(str);
        }
        // Output:
        // Apple
        // Banana
        // Cherry

        // Example 3: Using <T> T[] toArray(T[] a) with a smaller array
        String[] stringArray2 = new String[1]; // Smaller than the list size
        stringArray2 = list.toArray(stringArray2);
        System.out.println("\n<T> T[] toArray(T[] a) with smaller array:");
        for (String str : stringArray2) {
            System.out.println(str);
        }
        // Output:
        // Apple
        // Banana
        // Cherry

        // Example 4: Using <T> T[] toArray(T[] a) with a larger array
        String[] stringArray3 = new String[5]; // Larger than the list size
        stringArray3 = list.toArray(stringArray3);
        System.out.println("\n<T> T[] toArray(T[] a) with larger array:");
        for (String str : stringArray3) {
            System.out.println(str == null ? "null" : str);
        }
        // Output:
        // Apple
        // Banana
        // Cherry
        // null
        // null
    }
}
```

->The subList method provides a view of a portion of the original list.
Changes to the sublist are reflected in the original list, and vice versa.

The sublist is backed by the original list, so structural modifications (e.g., adding or removing elements) to the original list outside the sublist range can cause the sublist to become invalid.

```java
import java.util.ArrayList;
import java.util.List;

public class SubListExample {
    public static void main(String[] args) {
        // Create an ArrayList
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add("Date");
        fruits.add("Elderberry");

        // Get a sublist from index 1 (inclusive) to 4 (exclusive)
        List<String> subList = fruits.subList(1, 4);

        // Print the original list and the sublist
        System.out.println("Original List: " + fruits);
        // Output: Original List: [Apple, Banana, Cherry, Date, Elderberry]

        System.out.println("Sublist: " + subList);
        // Output: Sublist: [Banana, Cherry, Date]

        // Modify the sublist
        subList.set(0, "Blueberry");       // Replace "Banana" with "Blueberry"
        subList.add(1, "Blackberry");      // Insert "Blackberry" at index 1 in sublist
        subList.remove(2);                 // Remove element at index 2 in sublist ("Date")

        // Now, the sublist = ["Blueberry", "Blackberry", "Cherry"]
        // And since it's backed by the original list, the original list is also changed

        System.out.println("\nAfter modifying the sublist:");
        System.out.println("Original List: " + fruits);
        // Output: Original List: [Apple, Blueberry, Blackberry, Cherry, Elderberry]

        System.out.println("Sublist: " + subList);
        // Output: Sublist: [Blueberry, Blackberry, Cherry]

        // Modify the original list
        fruits.set(2, "Cranberry");        // Replace "Blackberry" with "Cranberry"
        fruits.add("Fig");                 // Add "Fig" at the end of the list

        // Now, the sublist reflects the updated portion as long as structure isn't broken

        System.out.println("\nAfter modifying the original list:");
        System.out.println("Original List: " + fruits);
        // Output: Original List: [Apple, Blueberry, Cranberry, Cherry, Elderberry, Fig]

        System.out.println("Sublist: " + subList);
        // Output: Sublist: [Blueberry, Cranberry, Cherry]
    }
}
```

->The Iterator interface in Java (java.util.Iterator) is used to traverse collections such as List, Set, and Queue in a forward-only direction. It works with any collection that implements the Iterable interface and allows sequential access to elements. One of the key advantages of using an iterator is that it supports the safe removal of elements during iteration without causing ConcurrentModificationException, provided that the removal is done through the iterator's remove() method. The main methods in the Iterator interface include hasNext(), which checks if there are more elements to iterate over; next(), which returns the next element in the sequence; and remove(), which removes the last element returned by next() from the underlying collection. This makes Iterator a powerful and flexible tool for safely iterating and modifying collections in Java.

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorListExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        Iterator<String> iterator = names.iterator();

        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name); // Output: Alice, then Bob, then Charlie

            if (name.equals("Bob")) {
                iterator.remove(); // Removes "Bob" safely during iteration
            }
        }

        System.out.println("\nAfter iteration and removal:");
        System.out.println(names); // Output: [Alice, Charlie]
    }
}

/*
Expected Output:
Alice
Bob
Charlie

After iteration and removal:
[Alice, Charlie]
*/
```

```java
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class IteratorSetExample {
    public static void main(String[] args) {
        Set<Integer> numbers = new HashSet<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        Iterator<Integer> it = numbers.iterator();

        while (it.hasNext()) {
            Integer num = it.next();
            System.out.println(num); // Output could be 10, 20, 30 in any order

            if (num == 20) {
                it.remove(); // Removes 20
            }
        }

        System.out.println("\nSet after removing 20:");
        System.out.println(numbers); // Output: [10, 30] or [30, 10] (order not guaranteed)
    }
}

/*
Possible Output:
10
20
30

Set after removing 20:
[10, 30]
*/
```

->The ListIterator interface in Java (java.util.ListIterator) is a specialized iterator that allows traversal of List collections in both forward and backward directions. Unlike the regular Iterator, which only supports forward movement, ListIterator enables more powerful navigation and modification during iteration. It can be used only with classes that implement the List interface, such as ArrayList or LinkedList. In addition to traversing, it allows you to add, remove, and replace elements while iterating. Key methods for forward traversal include hasNext(), next(), and nextIndex(), while backward traversal is supported using hasPrevious(), previous(), and previousIndex(). For modification, it provides add(E e) to insert elements, remove() to delete the last returned element, and set(E e) to replace it. This makes ListIterator ideal for complex list operations during iteration.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListIteratorExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        ListIterator<String> iterator = fruits.listIterator();

        // Forward traversal
        System.out.println("Forward Traversal:");
        while (iterator.hasNext()) {
            int index = iterator.nextIndex();
            String fruit = iterator.next();
            System.out.println(index + ": " + fruit);

            // Output:
            // 0: Apple
            // 1: Banana
            // 2: Cherry

            if (fruit.equals("Banana")) {
                iterator.set("Blueberry"); // Replaces "Banana" with "Blueberry"
            }

            if (fruit.equals("Cherry")) {
                iterator.add("Date"); // Adds "Date" after "Cherry"
            }
        }

        System.out.println("\nList after forward traversal and modification:");
        System.out.println(fruits); // [Apple, Blueberry, Cherry, Date]

        // Backward traversal
        System.out.println("\nBackward Traversal:");
        while (iterator.hasPrevious()) {
            int index = iterator.previousIndex();
            String fruit = iterator.previous();
            System.out.println(index + ": " + fruit);

            // Output:
            // 3: Date
            // 2: Cherry
            // 1: Blueberry
            // 0: Apple

            if (fruit.equals("Date")) {
                iterator.remove(); // Removes "Date"
            }
        }

        System.out.println("\nFinal List after backward traversal:");
        System.out.println(fruits); // [Apple, Blueberry, Cherry]
    }
}
```

| Operation Category      | Method                             | `ArrayList` (Performance)    | `LinkedList` (Performance)      |
| ----------------------- | ---------------------------------- | ---------------------------- | ------------------------------- |
| **Basic Operations**    | `add`, `remove`, `size`, `isEmpty` | Fast (except remove is O(n)) | Fast for add/remove at ends     |
| **Positional Access**   | `get(index)`, `set(index, e)`      | ✅ Fast (O(1))                | ❌ Slow (O(n))                   |
|                         | `add(index, e)`, `remove(index)`   | ❌ Slower (shifts required)   | ✅ Faster at start/middle (O(n)) |
| **Search Operations**   | `indexOf`, `lastIndexOf`           | ✅ Moderate (O(n))            | ✅ Moderate (O(n))               |
| **List Iteration**      | `iterator`, `listIterator`         | ✅ Fast (sequential access)   | ✅ Fast (but node-based)         |
| **Range-view**          | `subList(from, to)`                | ✅ Supported                  | ✅ Supported                     |
| **Bulk Operations**     | `addAll`, `removeAll`, `retainAll` | ✅ Supported                  | ✅ Supported                     |
| **Conversion to Array** | `toArray()`, `toArray(T[] a)`      | ✅ Efficient                  | ✅ Slightly less efficient       |


Here is a table summarizing **methods specific to `Deque`** in Java, categorized by their functionality:

---

### 📘 **Methods Specific to `Deque`**

| **Category**                      | **Method**                                | **Description**                                               |
| --------------------------------- | ----------------------------------------- | ------------------------------------------------------------- |
| 🔹 **Operations at Head (Front)** | `void addFirst(E e)`                      | Inserts element at front; throws if full.                     |
|                                   | `boolean offerFirst(E e)`                 | Inserts at front; returns `true` if added, `false` if full.   |
|                                   | `E removeFirst()`                         | Removes and returns front; throws if empty.                   |
|                                   | `E pollFirst()`                           | Removes and returns front; returns `null` if empty.           |
|                                   | `E getFirst()`                            | Retrieves (no remove) front; throws if empty.                 |
|                                   | `E peekFirst()`                           | Retrieves (no remove) front; returns `null` if empty.         |
| 🔹 **Operations at Tail (End)**   | `void addLast(E e)`                       | Inserts element at end; throws if full.                       |
|                                   | `boolean offerLast(E e)`                  | Inserts at end; returns `true` if added, `false` if full.     |
|                                   | `E removeLast()`                          | Removes and returns end; throws if empty.                     |
|                                   | `E pollLast()`                            | Removes and returns end; returns `null` if empty.             |
|                                   | `E getLast()`                             | Retrieves (no remove) end; throws if empty.                   |
|                                   | `E peekLast()`                            | Retrieves (no remove) end; returns `null` if empty.           |
| 🔹 **Stack Operations**           | `void push(E e)`                          | Pushes element at front (LIFO); throws if full.               |
|                                   | `E pop()`                                 | Pops element from front; throws if empty.                     |
| 🔹 **Utility Methods**            | `boolean removeFirstOccurrence(Object o)` | Removes first occurrence of element; returns `true` if found. |
|                                   | `boolean removeLastOccurrence(Object o)`  | Removes last occurrence of element; returns `true` if found.  |

---

This table includes **`Deque`-specific methods** not found in other collection types, making it ideal for **double-ended queue** and **stack-like** behavior.




