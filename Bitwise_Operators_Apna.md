
## Bitwise NOT `~`

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

| Operator | Description                    | Example      | Result                          |
|----------|-------------------------------|--------------|----------------------------------|
| `~`      | Bitwise NOT (one’s complement) | `~5`         | `-6` (invert all bits)          |
| `<<`     | Left shift                     | `3 << 2`     | `12` (0011 << 2 = 1100)         |
| `>>`     | Right shift (signed)           | `12 >> 2`    | `3` (1100 >> 2 = 0011)          |
| `>>>`    | Right shift (unsigned)         | `-1 >>> 1`   | Large positive number           |

---
