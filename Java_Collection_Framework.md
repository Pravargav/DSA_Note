---

Java Collections – Clean Notes

🔹 1. ArrayList vs LinkedList

ArrayList

Definition- A dynamic (resizable) array.

Memory- Stored in a contiguous block.

Key Points-

Fast random access (get(index))

Slower insert/delete (shifting required)



✅ LinkedList

Definition: A doubly linked list (each node has prev + next).

Memory: Stored in non-contiguous memory.

Key Points:

Faster insert/delete

Slower random access




---

🔹 2. ArrayList-Specific Methods

ensureCapacity(int minCapacity) → increases internal capacity

trimToSize() → reduces capacity to current size

removeRange(int fromIndex, int toIndex) → removes a range



---

🔹 3. subList()

Returns a view of part of the list.

Changes reflect in both:

original list ↔ sublist


⚠️ Warning:

Structural changes outside the range may cause issues.




---

🔹 4. Iterator vs ListIterator

✅ Iterator (java.util.Iterator)

Works with: List, Set, Queue

Direction: Forward only

Methods:

hasNext()

next()

remove() (safe removal)




---

✅ ListIterator (java.util.ListIterator)

Works only with: List

Direction: Forward + Backward

Extra Features:

hasPrevious(), previous()

add(E e), set(E e)




---

🔹 5. LinkedList as List vs Deque

Feature As List As Deque

Index access ✅ Yes ❌ No
Add/remove ends ❌ Not ideal ✅ Efficient
Order ✅ Maintains ✅ Maintains
Usage General list Queue / Stack



---

🔹 6. List vs Set

✅ List

Maintains insertion order

Allows duplicates

Supports indexing


✅ Set

No duplicates

No index access

Order depends on implementation:

HashSet → unordered

LinkedHashSet → insertion order

TreeSet → sorted




---

🔹 7. Set Interface

Does not define new methods

Ensures uniqueness of elements

Implementations add behavior



---

🔹 8. HashSet vs LinkedHashSet

✅ HashSet

Uses hash table

No order

O(1) operations


✅ LinkedHashSet

Extends HashSet

Maintains insertion order

Uses:

Hash table + Linked list



🔸 Important Clarification

✔ LinkedHashSet extends HashSet

❌ They are NOT separate unrelated classes



---

🔹 9. SortedSet / TreeSet

Uses Red-Black Tree

Time Complexity: O(log n)

Maintains sorted order



---

🔹 10. Comparable vs Comparator

✅ Comparable

Defines natural ordering

Method: compareTo()

Example:

String → alphabetical

Integer → numeric



✅ Comparator

Defines custom sorting

Supports:

Multiple comparators

Chaining



⚠️ Without either:

Sorting → ❌ ClassCastException



---

🔹 11. equals() and hashCode()

Required for:

HashSet

HashMap


Ensures:

Correct comparison

Proper hashing




---

🔹 12. Map vs Map.Entry

Feature Map Map.Entry

Purpose Collection of key-value pairs Single key-value pair
Scope Top-level interface Nested inside Map
Methods put, get, remove getKey, getValue
Relationship Contains entries Represents one entry



---

🔹 13. TreeMap vs LinkedHashMap

✅ TreeMap

Sorted by keys

Uses Red-Black Tree

❌ No insertion order


✅ LinkedHashMap

Maintains insertion order



---

🔹 14. Sorting TreeMap by Values (Important)

TreeMap sorts by keys only

To sort by values:

Convert to List of entries

Sort using Comparator




---
