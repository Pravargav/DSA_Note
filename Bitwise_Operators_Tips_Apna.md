
#### Tips & Tricks 

#### 1. Check if a number is **odd** or **even**

```java
if ((n & 1) == 1) {
    // n is odd
} else {
    // n is even
}
```

*Faster than `% 2` and frequently used.*

---

#### 2. Multiply or divide by powers of two

```java
int x = n << 3; // n * 8
int y = n >> 2; // n / 4 (for positive n)
```

---

#### 3. Swap two numbers without a temporary variable

```java
a = a ^ b;
b = a ^ b; // now b = original a
a = a ^ b; // now a = original b
```

---

#### 4. Check if a number is a **power of two**

```java
boolean isPowerOfTwo = (n > 0) && ((n & (n - 1)) == 0);
```

*Because powers of two have exactly one bit set.*

---

#### 5. Get the **rightmost set bit** (lowest set bit)

```java
int rightmostSetBit = n & (-n);
```

*Useful in algorithms like finding subsets or bit masks.*

---

#### 6. Turn off the **rightmost set bit**

```java
int newNum = n & (n - 1);
```

*Common in counting set bits.*

---



