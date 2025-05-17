----------------------------------------

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



This table includes **`Deque`-specific methods** not found in other collection types, making it ideal for **double-ended queue** and **stack-like** behavior.




### Other Utility Methods of `LinkedList`

1. **`clone()`**

   * This method creates a **shallow copy** of the `LinkedList`.
   * The new list contains the same elements, but it’s a separate list object (modifying one won’t affect the other).

2. **`descendingIterator()`**

   * This method returns an iterator that goes through the list **in reverse order** (from last element to first).
   * It’s useful if you want to traverse the list backward without reversing the list itself.



### Example with explanation and output:

```java
import java.util.LinkedList;
import java.util.Iterator;

public class LinkedListUtilityExample {
    public static void main(String[] args) {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Banana");
        linkedList.add("Cherry");

        // Clone the LinkedList (creates a shallow copy)
        LinkedList<String> clonedList = (LinkedList<String>) linkedList.clone();
        System.out.println("Cloned LinkedList: " + clonedList);
        // Output: Cloned LinkedList: [Banana, Cherry]

        // Use descendingIterator to iterate in reverse order
        System.out.println("Elements in reverse order:");
        Iterator<String> descendingIterator = linkedList.descendingIterator();
        while (descendingIterator.hasNext()) {
            System.out.println(descendingIterator.next());
        }
        // Output:
        // Elements in reverse order:
        // Cherry
        // Banana
    }
}
```



## ✅ 1. **LinkedList as a List**

When used as a **`List`**, `LinkedList` behaves like an ordered collection that allows **index-based operations**.

### 🔑 Key Features:

* Maintains insertion order.
* Allows duplicates.
* You can use methods like `add(index, element)`, `get(index)`, and `remove(index)`.

### 🔧 Example:

```java
import java.util.LinkedList;

public class LinkedListAsList {
    public static void main(String[] args) {
        LinkedList<String> cities = new LinkedList<>();

        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Chennai");
        cities.add(1, "Bangalore"); // Insert at index 1

        System.out.println("Cities List: " + cities);
        // Output: Cities List: [Delhi, Bangalore, Mumbai, Chennai]

        String city = cities.get(2); // Get element at index 2
        System.out.println("Element at index 2: " + city);
        // Output: Element at index 2: Mumbai

        cities.remove(0); // Remove element at index 0
        System.out.println("After removing index 0: " + cities);
        // Output: After removing index 0: [Bangalore, Mumbai, Chennai]
    }
}
```


## ✅ 2. **LinkedList as a Deque**

When used as a **`Deque`**, `LinkedList` allows you to work with both ends of the list — like a **queue** or a **stack**.

### 🔑 Key Features:

* Can add/remove from **front or end**.
* No direct index access.
* Supports **stack** (`push`, `pop`) and **queue** (`offer`, `poll`) behavior.

### 🔧 Example:

```java
import java.util.LinkedList;

public class LinkedListAsDeque {
    public static void main(String[] args) {
        LinkedList<String> deque = new LinkedList<>();

        // Queue behavior
        deque.addLast("A"); // offer at end
        deque.addLast("B");
        deque.addFirst("C"); // offer at front

        System.out.println("Deque after additions: " + deque);
        // Output: Deque after additions: [C, A, B]

        // Remove from both ends
        String front = deque.removeFirst(); // removes "C"
        String end = deque.removeLast();    // removes "B"

        System.out.println("Removed from front: " + front);  // Output: Removed from front: C
        System.out.println("Removed from end: " + end);      // Output: Removed from end: B
        System.out.println("Remaining Deque: " + deque);     // Output: Remaining Deque: [A]

        // Stack behavior
        deque.push("X"); // push adds at front
        deque.push("Y");
        System.out.println("After stack pushes: " + deque);
        // Output: After stack pushes: [Y, X, A]

        String popped = deque.pop(); // removes from front
        System.out.println("Popped element: " + popped);
        // Output: Popped element: Y
    }
}
```



### 🧠 Summary:

| Feature                 | `LinkedList` as List | `LinkedList` as Deque          |
| ----------------------- | -------------------- | ------------------------------ |
| Access by index         | ✅ Yes                | ❌ No                           |
| Add/remove at both ends | ❌ Not preferred      | ✅ Yes                          |
| Insertion order         | ✅ Maintains          | ✅ Maintains                    |
| Duplicate elements      | ✅ Allowed            | ✅ Allowed                      |
| Used as                 | Ordered collection   | Queue / Stack                  |
| Key Interfaces          | `List`, `Collection` | `Deque`, `Queue`, `Collection` |



1. Ordering
List:

Maintains the insertion order of elements.

Elements are stored in the order they are added.

Example: If you add [A, B, C], the list will store them as [A, B, C].

Set:

Does not maintain any order of elements (unless using a specific implementation like LinkedHashSet or TreeSet).

Elements are stored in an unordered manner.

Example: If you add [A, B, C], the set may store them as [B, A, C] or any other order.

2. Duplicates
List:

Allows duplicate elements.

You can add the same element multiple times.

Example: [A, B, A, C] is a valid list.

Set:

Does not allow duplicate elements.

Each element must be unique.

Example: If you try to add [A, B, A, C], the set will store [A, B, C].

3. Indexing
List:

Provides index-based access to elements.

You can access, add, or remove elements using their index.

Example: list.get(0) returns the first element.

Set:

Does not provide index-based access.

Elements cannot be accessed using an index.

Example: You cannot use set.get(0).

-> Methods only specific to set interface:

The Set interface in Java is part of the Java Collections Framework and represents a collection of unique elements. While the Set interface inherits methods from the Collection interface, it does not introduce any new methods of its own. Instead, it enforces the contract that all elements in a Set must be unique.

However, the Set interface is implemented by several classes (e.g., HashSet, LinkedHashSet, TreeSet), and these implementations may provide additional methods specific to their functionality. Below, I'll explain the methods specific to the Set interface and how they differ from other collections.



### **Methods Inherited from Collection Interface (for Set)**

| **Category**         | **Method**                                  | **Description**                                                          |
| -------------------- | ------------------------------------------- | ------------------------------------------------------------------------ |
| **Basic Operations** | `int size()`                                | Returns the number of elements in the set.                               |
|                      | `boolean isEmpty()`                         | Returns `true` if the set contains no elements.                          |
|                      | `boolean contains(Object o)`                | Returns `true` if the set contains the specified element.                |
|                      | `boolean add(E e)`                          | Adds the element if it's not already in the set.                         |
|                      | `boolean remove(Object o)`                  | Removes the element from the set if present.                             |
|                      | `void clear()`                              | Removes all elements from the set.                                       |
| **Bulk Operations**  | `boolean containsAll(Collection<?> c)`      | Returns `true` if the set contains all elements of the given collection. |
|                      | `boolean addAll(Collection<? extends E> c)` | Adds all elements from the specified collection to the set.              |
|                      | `boolean removeAll(Collection<?> c)`        | Removes all elements in the set that are in the specified collection.    |
|                      | `boolean retainAll(Collection<?> c)`        | Keeps only the elements that are also in the specified collection.       |
| **Array Conversion** | `Object[] toArray()`                        | Returns an array containing all elements in the set.                     |
|                      | `<T> T[] toArray(T[] a)`                    | Returns an array with the runtime type of the specified array.           |
| **Iteration**        | `Iterator<E> iterator()`                    | Returns an iterator to loop through the elements in the set.             |


-> Relationship Between LinkedHashSet and HashSet
LinkedHashSet Extends HashSet:

LinkedHashSet is a subclass of HashSet.

It inherits all the methods and behavior of HashSet but adds a linked list to maintain the insertion order of elements.

HashSet:

Implements the Set interface.

Uses a hash table for storage.

Does not maintain any order of elements.

LinkedHashSet:

Implements the Set interface.

Uses a hash table and a linked list for storage.

Maintains the insertion order of elements.

-> No, LinkedHashSet is not an inherited class from the HashSet class in Java. Instead, both LinkedHashSet and HashSet are separate classes that implement the Set interface. However, LinkedHashSet extends HashSet and adds additional functionality to maintain the insertion order of elements.

-> The HashSet class in Java is part of the Java Collections Framework and implements the Set interface. It is backed by a hash table (actually a HashMap instance) and provides constant-time performance for basic operations like add, remove, contains, and size, assuming the hash function disperses the elements properly.

-> The LinkedHashSet class in Java is a subclass of HashSet and implements the Set interface. It maintains a linked list of the entries in the set, in the order in which they were inserted. This allows LinkedHashSet to maintain insertion order, which is not supported by HashSet.

However, LinkedHashSet does not introduce any new methods that are not already present in HashSet or the Set interface. Instead, it overrides some of the internal behavior of HashSet to maintain the insertion order. Below, I'll explain the key differences and the methods that behave differently in LinkedHashSet compared to HashSet.

-> Since LinkedHashSet does not introduce any new methods, it relies on the methods inherited from HashSet and Set. However, the behavior of some methods is modified to maintain insertion order. Below are the methods inherited from HashSet and Set that behave differently in LinkedHashSet:

1. boolean add(E e)
Adds the specified element to the set if it is not already present.

In LinkedHashSet, the element is added to the end of the linked list to maintain insertion order.

2. boolean remove(Object o)
Removes the specified element from the set if it is present.

In LinkedHashSet, the element is removed from both the hash table and the linked list.

3. void clear()
Removes all elements from the set.

In LinkedHashSet, both the hash table and the linked list are cleared.

4. Iterator<E> iterator()
Returns an iterator over the elements in the set.

In LinkedHashSet, the iterator follows the insertion order of elements

-> Underlying Data Structure:


HashSet:

Uses a hash table (actually a HashMap instance) for storage.

Provides constant-time performance (O(1)) for basic operations like add, remove, and contains.

SortedSet:

Typically implemented using a balanced tree (e.g., a Red-Black tree in TreeSet).

Provides logarithmic-time performance (O(log n)) for basic operations like add, remove, and contains.



### **Methods Specific to SortedSet**

| **Category**                | **Method**                                        | **Description**                                                                       |
| --------------------------- | ------------------------------------------------- | ------------------------------------------------------------------------------------- |
| **Range Views**             | `SortedSet<E> subSet(E fromElement, E toElement)` | Returns a view of elements from `fromElement` (inclusive) to `toElement` (exclusive). |
|                             | `SortedSet<E> headSet(E toElement)`               | Returns a view of elements strictly less than `toElement`.                            |
|                             | `SortedSet<E> tailSet(E fromElement)`             | Returns a view of elements greater than or equal to `fromElement`.                    |
| **First and Last Elements** | `E first()`                                       | Returns the first (lowest) element in the set.                                        |
|                             | `E last()`                                        | Returns the last (highest) element in the set.                                        |
| **Comparator**              | `Comparator<? super E> comparator()`              | Returns the comparator used for ordering, or `null` if using natural ordering.        |




### **Methods Specific to NavigableSet**

| **Category**             | **Method**                                                                         | **Description**                                                                                 |
| ------------------------ | ---------------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------- |
| **Closest Match**        | `E lower(E e)`                                                                     | Returns the greatest element **strictly less than** the given element, or `null` if none.       |
|                          | `E floor(E e)`                                                                     | Returns the greatest element **less than or equal to** the given element, or `null` if none.    |
|                          | `E ceiling(E e)`                                                                   | Returns the smallest element **greater than or equal to** the given element, or `null` if none. |
|                          | `E higher(E e)`                                                                    | Returns the smallest element **strictly greater than** the given element, or `null` if none.    |
| **Polling**              | `E pollFirst()`                                                                    | Retrieves and removes the **first (lowest)** element, or returns `null` if empty.               |
|                          | `E pollLast()`                                                                     | Retrieves and removes the **last (highest)** element, or returns `null` if empty.               |
| **Reverse View**         | `NavigableSet<E> descendingSet()`                                                  | Returns a **reverse-order** view of the elements in the set.                                    |


-> Two types of comparators multiple comparators and Chained Comparators

https://stackoverflow.com/questions/2265503/why-do-i-need-to-override-the-equals-and-hashcode-methods-in-java

https://www.geeksforgeeks.org/override-equalsobject-hashcode-method/

-> When working with custom classes in Java, implementing the Comparable interface is crucial when the objects of that class need to be compared with each other. This interface provides a natural ordering for objects, enabling the use of sorting methods from the Arrays and Collections classes, as well as allowing the use of the custom class as keys in sorted collections like TreeMap and TreeSet.

-> When attempting to sort objects in Java without implementing either the Comparable or Comparator interface, a ClassCastException will be thrown. This error occurs because the sorting algorithm, such as Collections.sort() or Arrays.sort(), needs a way to compare the objects to determine their order. Without Comparable or Comparator, there is no defined way to compare the objects, leading to the exception



### **Difference Between Map Interface and Map.Entry Interface**

| **Feature**      | **Map Interface**                                                                                            | **Map.Entry Interface**                                                                        |
| ---------------- | ------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------------------------------- |
| **Purpose**      | Represents a collection of key-value pairs (a map).                                                          | Represents a single key-value pair (an entry in the map).                                      |
| **Scope**        | A top-level interface in the `java.util` package.                                                            | A nested interface inside the `Map` interface.                                                 |
| **Methods**      | Provides methods to work with the entire map (e.g., `put`, `get`, `remove`, `keySet`, `values`, `entrySet`). | Provides methods to work with a specific entry (e.g., `getKey()`, `getValue()`, `setValue()`). |
| **Usage**        | Used to store and manage multiple key-value pairs.                                                           | Used to access or modify a specific key-value pair within a map.                               |
| **Example**      | `Map<String, Integer> map = new HashMap<>();`                                                                | `Map.Entry<String, Integer> entry = map.entrySet().iterator().next();`                         |
| **Relationship** | A `Map` contains multiple `Map.Entry` objects.                                                               | A `Map.Entry` is a single element of a `Map`.                                                  |


### **Methods of Map.Entry Interface**

| **Method**                 | **Description**                                             |
| -------------------------- | ----------------------------------------------------------- |
| `K getKey()`               | Returns the key of the entry.                               |
| `V getValue()`             | Returns the value of the entry.                             |
| `V setValue(V value)`      | Updates the value of the entry and returns the old value.   |
| `boolean equals(Object o)` | Compares the specified object with this entry for equality. |
| `int hashCode()`           | Returns the hash code of the entry (based on key and value  |

```java
import java.util.*;

public class CompareByKeyExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Banana", 2);
        map.put("Apple", 5);
        map.put("Cherry", 3);

        // Convert map to list of entries
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        // Sort by key (alphabetically)
        entries.sort(Map.Entry.comparingByKey());

        System.out.println("Sorted by Key:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /*
        Output:
        Apple = 5
        Banana = 2
        Cherry = 3
        */
    }
}
```

```java
import java.util.*;

public class CompareByValueExample {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Banana", 2);
        map.put("Apple", 5);
        map.put("Cherry", 3);

        // Convert map to list of entries
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());

        // Sort by value (ascending)
        entries.sort(Map.Entry.comparingByValue());

        System.out.println("Sorted by Value:");
        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /*
        Output:
        Banana = 2
        Cherry = 3
        Apple = 5
        */
    }
}
```

```java
import java.util.*;

public class SortByKeyCustom {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Banana", 2);
        map.put("Apple", 5);
        map.put("Cherry", 3);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort by key in reverse (Z to A)
        entryList.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));

        System.out.println("Sorted by Key (Reverse):");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /*
        Output:
        Cherry = 3
        Banana = 2
        Apple = 5
        */
    }
}
```
```java
import java.util.*;

public class SortByValueCustom {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Banana", 2);
        map.put("Apple", 5);
        map.put("Cherry", 3);

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort by value in reverse (highest to lowest)
        entryList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        System.out.println("Sorted by Value (Reverse):");
        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /*
        Output:
        Apple = 5
        Cherry = 3
        Banana = 2
        */
    }
}
```

```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    int compareByAge(Person other) {
        return Integer.compare(this.age, other.age);
    }

    @Override
    public String toString() {
        return name + " (" + age + ")";
    }
}

import java.util.*;

public class SortByCustomObject {
    public static void main(String[] args) {
        Map<String, Person> map = new HashMap<>();
        map.put("a", new Person("Alice", 30));
        map.put("b", new Person("Bob", 25));
        map.put("c", new Person("Charlie", 35));

        List<Map.Entry<String, Person>> entryList = new ArrayList<>(map.entrySet());

        // Sort by Person age using custom comparator
        entryList.sort(Map.Entry.comparingByValue((p1, p2) -> p1.compareByAge(p2)));

        System.out.println("Sorted by Person Age:");
        for (Map.Entry<String, Person> entry : entryList) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        /*
        Output:
        b = Bob (25)
        a = Alice (30)
        c = Charlie (35)
        */
    }
}
```


### ✅ **Basic Map Methods**

| **Method**                                     | **Description**                                                              |
| ---------------------------------------------- | ---------------------------------------------------------------------------- |
| `int size()`                                   | Returns the number of key-value pairs in the map.                            |
| `boolean isEmpty()`                            | Returns `true` if the map contains no key-value pairs.                       |
| `boolean containsKey(Object key)`              | Returns `true` if the map contains the specified key.                        |
| `boolean containsValue(Object value)`          | Returns `true` if the map contains the specified value.                      |
| `V get(Object key)`                            | Returns the value associated with the specified key, or `null` if not found. |
| `V put(K key, V value)`                        | Adds or updates a key-value pair. Returns the previous value or `null`.      |
| `V remove(Object key)`                         | Removes the key-value pair for the specified key. Returns the removed value. |
| `void putAll(Map<? extends K, ? extends V> m)` | Copies all key-value pairs from the specified map into this map.             |
| `void clear()`                                 | Removes all key-value pairs from the map.                                    |


### ✅ **View Methods**

| **Method**                        | **Description**                                                               |
| --------------------------------- | ----------------------------------------------------------------------------- |
| `Set<K> keySet()`                 | Returns a `Set` view of the keys in the map.                                  |
| `Collection<V> values()`          | Returns a `Collection` view of the values in the map.                         |
| `Set<Map.Entry<K, V>> entrySet()` | Returns a `Set` view of the key-value pairs (`Map.Entry` objects) in the map. |


### ✅ **Advanced Utility Methods**

| **Method**                                                                | **Description**                                                                            |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------------------------ |
| `V getOrDefault(Object key, V defaultValue)`                              | Returns the value for the specified key, or `defaultValue` if the key is not found.        |
| `V putIfAbsent(K key, V value)`                                           | Adds the key-value pair only if the key is not already present.                            |
| `boolean remove(Object key, Object value)`                                | Removes the key-value pair only if the key is mapped to the specified value.               |
| `boolean replace(K key, V oldValue, V newValue)`                          | Replaces the value for the key only if currently mapped to `oldValue`.                     |
| `V replace(K key, V value)`                                               | Replaces the value for the key only if it is already mapped.                               |

-> A LinkedHashMap<K, V> is a subclass of HashMap<K, V> that maintains insertion order (or access order, if configured). It is part of the Java Collections Framework and implements both the Map<K, V> and Iterable<Map.Entry<K, V>> interfaces.


### ✅ **Methods Specific to `SortedMap` Interface**

| **Method**                                   | **Description**                                                                                              |
| -------------------------------------------- | ------------------------------------------------------------------------------------------------------------ |
| `Comparator<? super K> comparator()`         | Returns the comparator used to order the keys, or `null` if natural ordering is used.                        |
| `K firstKey()`                               | Returns the first (lowest) key in the map.                                                                   |
| `K lastKey()`                                | Returns the last (highest) key in the map.                                                                   |
| `SortedMap<K, V> headMap(K toKey)`           | Returns a view of the portion of the map whose keys are less than `toKey`.                                   |
| `SortedMap<K, V> tailMap(K fromKey)`         | Returns a view of the portion of the map whose keys are greater than or equal to `fromKey`.                  |
| `SortedMap<K, V> subMap(K fromKey, K toKey)` | Returns a view of the portion of the map whose keys range from `fromKey` (inclusive) to `toKey` (exclusive). |


### ✅ **Methods Specific to `NavigableMap` Interface**

`floorKey(K key)`, `ceilingKey(K key)`, `lowerKey(K key)`, `higherKey(K key)`,  
`floorEntry(K key)`, `ceilingEntry(K key)`, `lowerEntry(K key)`, `higherEntry(K key)`,  
`pollFirstEntry()`, `pollLastEntry()`, `descendingKeySet()`, `descendingMap()`... etc


```java
import java.util.HashMap;

import java.util.Map;



public class FrequencyCounter {

    public static void main(String[] args) {

        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};

        Map<String, Integer> frequencyMap = new HashMap<>();



        for (String word : words) {

            // Using getOrDefault to handle both existing and new keys

            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);

        }



        System.out.println("Frequency Map (using getOrDefault):");

        System.out.println(frequencyMap);

        // Output: {orange=1, banana=2, apple=3}

    }

}
```

```java
import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {
    public static void main(String[] args) {
        String[] words = {"apple", "banana", "apple", "orange", "banana", "apple"};
        Map<String, Integer> frequencyMap = new HashMap<>();

        for (String word : words) {
            // Using putIfAbsent to initialize if key doesn't exist
            frequencyMap.putIfAbsent(word, 0);
            frequencyMap.put(word, frequencyMap.get(word) + 1);
        }

        System.out.println("Frequency Map (using putIfAbsent):");
        System.out.println(frequencyMap);
        // Output: {orange=1, banana=2, apple=3}
    }
}
```
```java
class Task {
    String name;
    int priority;
    
    Task(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
}

// Custom comparator for Task objects (higher priority value comes first)
PriorityQueue<Task> taskQueue = new PriorityQueue<>(
    (t1, t2) -> Integer.compare(t2.priority, t1.priority) // Descending
);

taskQueue.add(new Task("Fix bug", 3));
taskQueue.add(new Task("Write docs", 1));
taskQueue.add(new Task("Implement feature", 5));

System.out.println(taskQueue.poll().name); // "Implement feature" (priority 5)
```

```java
class Student {
    String name;
    double gpa;
    int age;
    
    Student(String name, double gpa, int age) {
        this.name = name;
        this.gpa = gpa;
        this.age = age;
    }
}

// Sort by GPA (descending), then age (ascending)
PriorityQueue<Student> studentQueue = new PriorityQueue<>(
    Comparator.comparingDouble((Student s) -> s.gpa).reversed()
    .thenComparingInt(s -> s.age)
);

studentQueue.add(new Student("Alice", 3.8, 20));
studentQueue.add(new Student("Bob", 3.8, 19));
studentQueue.add(new Student("Charlie", 3.5, 21));

System.out.println(studentQueue.poll().name); // Alice (same GPA as Bob but older)
System.out.println(studentQueue.poll().name); // Bob
```

-> haspmap to list and list to hashmap inter-conversion

```java
//list to map
ArrayList<String> list = new ArrayList<>(Arrays.asList("Apple", "Banana", "Cherry"));

// Convert to HashMap with index as key
HashMap<Integer, String> map = new HashMap<>();
for (int i = 0; i < list.size(); i++) {
    map.put(i, list.get(i));
}
// Result: {0=Apple, 1=Banana, 2=Cherry}

//map to list
HashMap<String, Integer> map = new HashMap<>();
map.put("Apple", 10);
map.put("Banana", 20);
map.put("Orange", 15);

// Create the List of Lists
List<List<Object>> entryLists = new ArrayList<>();

// Iterate through the map entries
for (Map.Entry<String, Integer> entry : map.entrySet()) {
    // Create a new list for each key-value pair
    List<Object> pair = new ArrayList<>();
    pair.add(entry.getKey());
    pair.add(entry.getValue());
    
    // Add the pair to our main list
    entryLists.add(pair);
}

System.out.println(entryLists);
// Output: [[Apple, 10], [Banana, 20], [Orange, 15]]
```

-> Adding keys,values and entries to list
```java

HashMap<Integer, String> map = new HashMap<>();
map.put(1, "Apple");
map.put(2, "Banana");
map.put(3, "Cherry");

ArrayList<Integer> keyList = new ArrayList<>(map.keySet());
// Result: [1, 2, 3] (order not guaranteed)

ArrayList<String> valueList = new ArrayList<>(map.values());
// Result: [Apple, Banana, Cherry] (order matches keySet)

ArrayList<Map.Entry<Integer, String>> entryList = new ArrayList<>(map.entrySet());
// Result: [1=Apple, 2=Banana, 3=Cherry] as Entry objects

```

-> List comparators

```java

// Sort strings by number of vowels
Comparator<String> byVowels = (s1, s2) -> {
    int vowels1 = s1.replaceAll("[^aeiouAEIOU]", "").length();
    int vowels2 = s2.replaceAll("[^aeiouAEIOU]", "").length();
    return Integer.compare(vowels1, vowels2);
};

ArrayList<String> words = new ArrayList<>(Arrays.asList("apple", "banana", "pear"));
words.sort(byVowels);
// Result: [pear, apple, banana]


// Sort by name then age
Comparator<Person> byNameThenAge = Comparator
    .comparing((Person p) -> p.name)
    .thenComparingInt(p -> p.age);

people.sort(byNameThenAge);


class Person {
    String name;
    int age;
    
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

ArrayList<Person> people = new ArrayList<>();
people.add(new Person("John", 30));
people.add(new Person("Alice", 25));

// Sort by age
people.sort(Comparator.comparingInt(p -> p.age));


Comparator<String> byLength = Comparator.comparingInt(String::length);
names.sort(byLength);
// Result (for ["John", "Alice", "Bob"]): [Bob, John, Alice]

ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(5, 2, 8, 1));
Collections.sort(numbers, Comparator.reverseOrder());
// Result: [8, 5, 2, 1]

```


->Map comparators

```java
Comparator<String> byVowels = (s1, s2) -> {
    long v1 = s1.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
    long v2 = s2.toLowerCase().chars().filter(c -> "aeiou".indexOf(c) >= 0).count();
    return v1 != v2 ? Long.compare(v1, v2) : s1.compareTo(s2);
};

TreeMap<String, Integer> vowelMap = new TreeMap<>(byVowels);
vowelMap.put("Apple", 5);
vowelMap.put("Banana", 3);
vowelMap.put("Grape", 2);
vowelMap.put("Kiwi", 4);
System.out.println(vowelMap);  // {Grape=2, Kiwi=4, Apple=5, Banana=3}


Comparator<String> numericKeys = (s1, s2) -> {
    try {
        return Integer.compare(Integer.parseInt(s1), Integer.parseInt(s2));
    } catch (NumberFormatException e) {
        return s1.compareTo(s2);
    }
};

TreeMap<String, Integer> numericMap = new TreeMap<>(numericKeys);
numericMap.put("100", 1);
numericMap.put("20", 2);
numericMap.put("500", 3);
numericMap.put("abc", 4);
System.out.println(numericMap);  // {20=2, 100=1, 500=3, abc=4}


Comparator<String> byLastChar = (s1, s2) -> {
    char last1 = s1.charAt(s1.length() - 1);
    char last2 = s2.charAt(s2.length() - 1);
    return Character.compare(last1, last2);
};

TreeMap<String, Integer> lastCharMap = new TreeMap<>(byLastChar);
lastCharMap.put("Apple", 1);
lastCharMap.put("Banana", 2);
lastCharMap.put("Cherry", 3);
lastCharMap.put("Date", 4);
System.out.println(lastCharMap);  // {Banana=2, Apple=1, Date=4, Cherry=3}

```

-> Set comparators

```java
TreeSet<String> caseInsensitive = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
caseInsensitive.add("Apple");
caseInsensitive.add("banana");
caseInsensitive.add("apple");
System.out.println(caseInsensitive); // [Apple, banana] (only one "apple")

Comparator<String> byLength = (s1, s2) -> {
    int lenCompare = Integer.compare(s1.length(), s2.length());
    return lenCompare != 0 ? lenCompare : s1.compareTo(s2);
};

TreeSet<String> words = new TreeSet<>(byLength);
words.add("apple");
words.add("banana");
words.add("kiwi");
words.add("pear");
System.out.println(words); // [kiwi, pear, apple, banana]

TreeSet<Integer> numbers = new TreeSet<>(Comparator.reverseOrder());
numbers.add(5);
numbers.add(2);
numbers.add(8);
System.out.println(numbers); // [8, 5, 2] (descending order)

```
-> Linked Hash Map iteration

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // LinkedHashMap example (insertion order)
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Apple", 1);
        linkedHashMap.put("Banana", 2);
        linkedHashMap.put("Cherry", 3);
        linkedHashMap.put("Date", 4);
        
        System.out.println("\nLinkedHashMap elements (insertion order):");
        
        // Method 1: Classic iterator
        Iterator<Map.Entry<String, Integer>> it = linkedHashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            System.out.println(entry.getKey() + " :: " + entry.getValue());
        }
        
    }
}
```

-> TreeMap comparing by keys

```java
Example 2: Custom Key Comparator (Descending Order)
TreeMap<String, Integer> map = new TreeMap<>(Comparator.reverseOrder());
map.put("Orange", 2);
map.put("Apple", 1);
map.put("Banana", 3);

System.out.println(map); // {Orange=2, Banana=3, Apple=1} (reverse alphabetical)


Example 3: Custom Key Comparator (By String Length)
Comparator<String> byLength = Comparator.comparingInt(String::length);
TreeMap<String, Integer> map = new TreeMap<>(byLength);
map.put("Kiwi", 4);
map.put("Apple", 1);
map.put("Banana", 3);

System.out.println(map); // {Kiwi=4, Apple=1, Banana=3} (sorted by word length)
```

-> TreeMap (implements SortedMap/NavigableMap)
Implementation: Uses Red-Black tree (self-balancing binary search tree)

Ordering: Maintains elements in sorted order (natural ordering or via Comparator)

-> Classes that implement Comparable (like String, Integer, etc.) define their own natural order
Examples:
String: alphabetical order ("Apple" < "Banana")
Integer: numerical order (1 < 2 < 3)
Date: chronological order

-> No, a TreeMap does NOT maintain insertion order - it sorts elements either by their natural ordering (if keys implement Comparable) or by a custom Comparator you provide.

-> Sorting TreeMap by Values (Tricky)
TreeMap is designed to sort by keys, not values. To sort by values, you need to:

Option : Create a List of Entries and Sort


```java
TreeMap<String, Integer> map = new TreeMap<>();
map.put("Orange", 2);
map.put("Apple", 1);
map.put("Banana", 3);

// Convert to list and sort by value
List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
entries.sort(Map.Entry.comparingByValue());

System.out.println(entries); // [Apple=1, Orange=2, Banana=3]

Example 1: Basic Value Sorting (Ascending Order)
java
TreeMap<String, Integer> fruitPrices = new TreeMap<>();
fruitPrices.put("Orange", 4);
fruitPrices.put("Apple", 2);
fruitPrices.put("Banana", 3);
fruitPrices.put("Mango", 5);

// Convert to list and sort by value (ascending)
List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(fruitPrices.entrySet());
sortedEntries.sort(Map.Entry.comparingByValue());

System.out.println("Sorted by value (ascending):");
sortedEntries.forEach(entry -> 
    System.out.println(entry.getKey() + ": " + entry.getValue()));


Example 2: Value Sorting (Descending Order)
java
// Using the same map from Example 1
List<Map.Entry<String, Integer>> sortedEntriesDesc = new ArrayList<>(fruitPrices.entrySet());
sortedEntriesDesc.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

System.out.println("\nSorted by value (descending):");
sortedEntriesDesc.forEach(entry -> 
    System.out.println(entry.getKey() + ": " + entry.getValue()));


Example 3: Sorting by Value then by Key
java
TreeMap<String, Integer> cityPopulations = new TreeMap<>();
cityPopulations.put("New York", 8419000);
cityPopulations.put("Los Angeles", 3971000);
cityPopulations.put("Chicago", 2716000);
cityPopulations.put("Houston", 2328000);
cityPopulations.put("Phoenix", 1680000);

List<Map.Entry<String, Integer>> sortedCities = new ArrayList<>(cityPopulations.entrySet());
sortedCities.sort(
    Map.Entry.<String, Integer>comparingByValue().reversed()  // First by population (desc)
    .thenComparing(Map.Entry.comparingByKey())               // Then by city name (asc)
);

```
----------------------------------------------------------
