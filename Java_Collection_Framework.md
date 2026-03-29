----

->(ArrayList) Definition: A dynamic array is a resizable array that grows or shrinks in size as elements are added or removed.
Memory Allocation: Elements are stored in a contiguous block of memory.

->(LinkedList) Definition: A doubly linked list is a linear data structure where each element (node) contains a value and two pointers: one to the previous node and one to the next node.
Memory Allocation: Elements are stored in non-contiguous memory locations, with each node dynamically allocated.

->Methods Specific to ArrayList

1)void ensureCapacity(int minCapacity)
2)void trimToSize()
3)void removeRange(int fromIndex, int toIndex)


->The subList method provides a view of a portion of the original list.
Changes to the sublist are reflected in the original list, and vice versa.

The sublist is backed by the original list, so structural modifications (e.g., adding or removing elements) to the original list outside the sublist range can cause the sublist to become invalid.


->The Iterator interface in Java (java.util.Iterator) is used to traverse collections such as List, Set, and Queue in a forward-only direction. It works with any collection that implements the Iterable interface and allows sequential access to elements. One of the key advantages of using an iterator is that it supports the safe removal of elements during iteration without causing ConcurrentModificationException, provided that the removal is done through the iterator's remove() method. The main methods in the Iterator interface include hasNext(), which checks if there are more elements to iterate over; next(), which returns the next element in the sequence; and remove(), which removes the last element returned by next() from the underlying collection. This makes Iterator a powerful and flexible tool for safely iterating and modifying collections in Java.


->The ListIterator interface in Java (java.util.ListIterator) is a specialized iterator that allows traversal of List collections in both forward and backward directions. Unlike the regular Iterator, which only supports forward movement, ListIterator enables more powerful navigation and modification during iteration. It can be used only with classes that implement the List interface, such as ArrayList or LinkedList. In addition to traversing, it allows you to add, remove, and replace elements while iterating. Key methods for forward traversal include hasNext(), next(), and nextIndex(), while backward traversal is supported using hasPrevious(), previous(), and previousIndex(). For modification, it provides add(E e) to insert elements, remove() to delete the last returned element, and set(E e) to replace it. This makes ListIterator ideal for complex list operations during iteration.



This table includes **`Deque`-specific methods** not found in other collection types, making it ideal for **double-ended queue** and **stack-like** behavior.




## ✅ 1. **LinkedList as a List**



## ✅ 2. **LinkedList as a Deque**



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


https://medium.com/@reetesh043/java-collection-map-interface-1881d9294a8d

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
TreeMap is designed to sort by keys, not values. To sort by values, you need to..

Option : Create a List of Entries and Sort

->  TreeMap - Maintains keys in sorted order

-> LinkedHashMap - Maintains insertion order by default
