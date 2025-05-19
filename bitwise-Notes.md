

## ✅ 1. Bitwise AND `&`

**Each bit of the result is 1 if both bits are 1.**

| A (bit) | B (bit) | A & B |
| ------- | ------- | ----- |
| 0       | 0       | 0     |
| 0       | 1       | 0     |
| 1       | 0       | 0     |
| 1       | 1       | 1     |

**Example (Java):**

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a & b; // 0001 = 1
```

---

## ✅ 2. Bitwise OR `|`

**Each bit of the result is 1 if at least one bit is 1.**

| A (bit) | B (bit) | A \| B |
| ------- | ------- | ------ |
| 0       | 0       | 0      |
| 0       | 1       | 1      |
| 1       | 0       | 1      |
| 1       | 1       | 1      |

**Example (Java):**

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a | b; // 0111 = 7
```

---

## ✅ 3. Bitwise XOR `^`

**Each bit of the result is 1 if only one bit is 1 (not both).**

| A (bit) | B (bit) | A ^ B |
| ------- | ------- | ----- |
| 0       | 0       | 0     |
| 0       | 1       | 1     |
| 1       | 0       | 1     |
| 1       | 1       | 0     |

**Example (Java):**

```java
int a = 5;  // 0101
int b = 3;  // 0011
int c = a ^ b; // 0110 = 6
```

---

## ✅ 4. Bitwise NOT `~`

**Flips all bits (1's complement).**

| A (bit) | \~A |
| ------- | --- |
| 0       | 1   |
| 1       | 0   |

In Java, integers are 32-bit signed.
So `~a = -a - 1`

**Example:**

```java
int a = 5;   //  00000000 00000000 00000000 00000101
int b = ~a;  //  11111111 11111111 11111111 11111010  (which is -6)
```

---

## ✅ 5. Left Shift `<<`

**Shifts bits left (multiplies by 2ⁿ).**

```java
int a = 5;      // 00000101
int b = a << 1; // 00001010 = 10
int c = a << 2; // 00010100 = 20
```

| Value | Binary   | Value << 1 | Result Binary | Result |
| ----- | -------- | ---------- | ------------- | ------ |
| 5     | 00000101 | 1          | 00001010      | 10     |

---

## ✅ 6. Signed Right Shift `>>`

**Shifts bits right (preserves sign).**

```java
int a = 8;     // 00001000
int b = a >> 1; // 00000100 = 4

int c = -8;    // 11111000 (in 2's comp)
int d = c >> 1; // 11111100 = -4
```

| Value | Binary   | Value >> 1 | Result Binary | Result |
| ----- | -------- | ---------- | ------------- | ------ |
| 8     | 00001000 | 1          | 00000100      | 4      |
| -8    | 11111000 | 1          | 11111100      | -4     |

---

## ✅ 7. Unsigned Right Shift `>>>`

**Shifts bits right (fills left with 0, always positive).**

```java
int a = -8;       // 11111111 11111111 11111111 11111000
int b = a >>> 1;  // 01111111 11111111 11111111 11111100 = 2147483644
```

| Value | Binary (32-bit)                     | Value >>> 1 | Result (Decimal) |
| ----- | ----------------------------------- | ----------- | ---------------- |
| -8    | 11111111 11111111 11111111 11111000 | 1           | 2147483644       |

---

Sure! Here’s a complete, handy guide on **Java bitwise operators** with tips, tricks, and important patterns that frequently come up in **DSA (Data Structures and Algorithms)** problems:

---

# Java Bitwise Operators Quick Reference

| Operator | Description                    | Example    | Result                   |     |           |              |
| -------- | ------------------------------ | ---------- | ------------------------ | --- | --------- | ------------ |
| `&`      | Bitwise AND                    | `5 & 3`    | `1` (0101 & 0011 = 0001) |     |           |              |
| \`       | \`                             | Bitwise OR | \`5                      | 3\` | `7` (0101 | 0011 = 0111) |
| `^`      | Bitwise XOR                    | `5 ^ 3`    | `6` (0101 ^ 0011 = 0110) |     |           |              |
| `~`      | Bitwise NOT (one’s complement) | `~5`       | `-6` (invert all bits)   |     |           |              |
| `<<`     | Left shift                     | `3 << 2`   | `12` (0011 << 2 = 1100)  |     |           |              |
| `>>`     | Right shift (signed)           | `12 >> 2`  | `3` (1100 >> 2 = 0011)   |     |           |              |
| `>>>`    | Right shift (unsigned)         | `-1 >>> 1` | Large positive number    |     |           |              |

---

# Tips & Tricks for DSA Using Bitwise Operators

### 1. Check if a number is **odd** or **even**

```java
if ((n & 1) == 1) {
    // n is odd
} else {
    // n is even
}
```

*Faster than `% 2` and frequently used.*

---

### 2. Multiply or divide by powers of two

```java
int x = n << 3; // n * 8
int y = n >> 2; // n / 4 (for positive n)
```

---

### 3. Swap two numbers without a temporary variable

```java
a = a ^ b;
b = a ^ b; // now b = original a
a = a ^ b; // now a = original b
```

---

### 4. Check if a number is a **power of two**

```java
boolean isPowerOfTwo = (n > 0) && ((n & (n - 1)) == 0);
```

*Because powers of two have exactly one bit set.*

---

### 5. Get the **rightmost set bit** (lowest set bit)

```java
int rightmostSetBit = n & (-n);
```

*Useful in algorithms like finding subsets or bit masks.*

---

### 6. Turn off the **rightmost set bit**

```java
int newNum = n & (n - 1);
```

*Common in counting set bits.*

---

### 7. Count the number of set bits in an integer (Brian Kernighan’s Algorithm)

```java
int countSetBits(int n) {
    int count = 0;
    while (n != 0) {
        n = n & (n - 1);
        count++;
    }
    return count;
}
```

*Each iteration removes the rightmost set bit.*

---

### 8. XOR properties for unique element problems

* `x ^ x = 0`
* `x ^ 0 = x`
* XOR all elements → duplicates cancel out, unique remains.

```java
int unique = 0;
for (int num : arr) {
    unique ^= num;
}
```

---

### 9. Find two unique numbers in an array where every other number appears twice

* XOR all numbers → result = XOR of two unique numbers
* Find rightmost set bit in result
* Partition array into two groups based on that bit and XOR separately to find unique numbers

---

### 10. Check if two numbers have opposite signs

```java
boolean oppositeSigns = ((a ^ b) < 0);
```

*Because sign bit differs.*

---

### 11. Set, clear, and toggle bits at specific positions

```java
// Set bit at pos (0-based from right)
n = n | (1 << pos);

// Clear bit at pos
n = n & ~(1 << pos);

// Toggle bit at pos
n = n ^ (1 << pos);

// Check if bit at pos is set
boolean isSet = (n & (1 << pos)) != 0;
```

---

### 12. Reverse bits of a number (common in bit manipulation problems)

```java
int reverseBits(int n) {
    int result = 0;
    for (int i = 0; i < 32; i++) {
        result <<= 1;
        result |= (n & 1);
        n >>= 1;
    }
    return result;
}
```

---

### 13. Use bit masks to generate all subsets (power set)

For a set with `n` elements, iterate from 0 to `2^n - 1` where each number is a subset mask.

```java
for (int mask = 0; mask < (1 << n); mask++) {
    for (int j = 0; j < n; j++) {
        if ((mask & (1 << j)) != 0) {
            // element j is included in this subset
        }
    }
}
```

---

### 14. Sign extension tricks (for example in shifting negative numbers)

* Use unsigned right shift `>>>` to avoid sign extension
* Use `<<` and `>>` carefully with signed integers

---

### 15. Quick absolute value (for int)

```java
int abs = (n ^ (n >> 31)) - (n >> 31);
```

---

# Summary

| Problem Type                     | Bitwise Trick Used                   |
| -------------------------------- | ------------------------------------ |
| Odd/even check                   | `n & 1`                              |
| Power of two check               | `n & (n-1) == 0`                     |
| Swap without temp variable       | XOR swap                             |
| Counting set bits                | `n & (n-1)` loop                     |
| Unique element in duplicates     | XOR all                              |
| Two unique elements              | XOR + partition by rightmost set bit |
| Subset generation                | Bit mask with `1 << pos`             |
| Multiply/divide by powers of two | Shift left/right                     |
| Toggle, set, clear bits          | Bit mask operations                  |


