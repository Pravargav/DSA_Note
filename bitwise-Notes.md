

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



