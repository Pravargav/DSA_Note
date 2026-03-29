

## 🔤 Character Methods

```java
Character.isDigit(ch);        // Check if character is a digit (0-9)
Character.isLetter(ch);       // Check if character is a letter (a-z or A-Z)
Character.isLowerCase(ch);    // Check if character is lowercase
Character.isUpperCase(ch);    // Check if character is uppercase
```

## 🔢 ASCII Ranges

| Character | Range  |
| --------- | ------ |
| a–z       | 97–122 |
| A–Z       | 65–90  |
| 0–9       | 48–57  |

---

## 🔁 Character ↔ Integer Conversion

```java
char c = '9';
int num = Integer.parseInt(String.valueOf(c));  // '9' → 9

char c = '7';
int num = c - '0';  // '7' → 7

int num = 65;
char c = (char)num;  // 65 → 'A'

int num = 7;
char c = (char)(num + '0');  // 7 → '7'
```

---

## 🔠 Toggle Case of Characters in a String

```java
String s1 = "";
for (int i = 0; i < s.length(); i++) {
    if (Character.isUpperCase(s.charAt(i)))
        s1 += Character.toLowerCase(s.charAt(i));
    else
        s1 += Character.toUpperCase(s.charAt(i));
}
```

---

## 🔤 Remove Vowels from a String

```java
String s = "prepinsta";
String s1 = s.replaceAll("[aeiou]", "");
```

---

## 🚫 Remove Non-Alphabetic Characters from String

```java
for (int i = 0; i < s.length(); i++) {
    if ((s.charAt(i) < 'A' || s.charAt(i) > 'Z') &&
        (s.charAt(i) < 'a' || s.charAt(i) > 'z')) {
        s = s.substring(0, i) + s.substring(i + 1);
        i--;
    }
}
```

---

## 🧱 StringBuilder in Java

### 🔨 Common Operations

```java
StringBuilder sb = new StringBuilder("Java");
String str = String.valueOf(sb);  // Convert to String

StringBuilder sb = new StringBuilder("Hello");
String str = sb.toString();       // Convert to String

String str = "Hello World";
StringBuilder sb = new StringBuilder(str);  // From existing string

sb.append(" World");              // Append string to StringBuilder

String s = "(a+b)=c";
String result = s.replaceAll("[(){}]", "");  // Remove brackets
```

### 🧰 StringBuilder Methods Summary

| Method                                  | Description                   |
| --------------------------------------- | ----------------------------- |
| `append(String s)`                      | Appends to the end            |
| `insert(int offset, String s)`          | Inserts string at offset      |
| `replace(int start, int end, String s)` | Replaces substring in range   |
| `delete(int start, int end)`            | Deletes characters from range |
| `reverse()`                             | Reverses the string           |
| `capacity()`                            | Returns current capacity      |
| `ensureCapacity(int min)`               | Ensures minimum capacity      |
| `charAt(int index)`                     | Returns character at index    |
| `length()`                              | Returns current length        |
| `substring(int start)`                  | Substring from start to end   |
| `substring(int start, int end)`         | Substring in range            |
| `indexOf(String str)`                   | First occurrence index        |
| `lastIndexOf(String str)`               | Last occurrence index         |
| `trimToSize()`                          | Reduces capacity to length    |



---

## 🧱 StringBuilder Operations

```java
StringBuilder sb = new StringBuilder("Hello World");

// Insert
sb.insert(5, " Geeks");  // Inserts " Geeks" at index 5

// Replace
sb.replace(6, 11, "Geeks");  // Replaces characters from 6 to 10 with "Geeks"

// Delete
sb.delete(5, 11);  // Deletes characters from index 5 to 10

// Reverse
sb.reverse();  // Reverses the sequence

// Capacity and Length
int cap = sb.capacity();  // Current capacity
int len = sb.length();    // Length of string

// Access and Modify Characters
char ch = sb.charAt(4);        // Character at index 4
sb.setCharAt(0, 'G');          // Set character at index 0 to 'G'

// Substring
String sub = sb.substring(0, 5);  // Returns substring from index 0 to 4

// Ensure Capacity
sb.ensureCapacity(50);  // Ensures capacity of at least 50

// Delete Character at Index
sb.deleteCharAt(3);  // Deletes character at index 3

// Index Of
int idx1 = sb.indexOf("Geeks");      // First index of "Geeks"
int idx2 = sb.lastIndexOf("Geeks");  // Last index of "Geeks"

// Convert to String
String result = sb.toString();
```

---

## 🔠 String Splitting

```java
String s = "geeks@for@geeks";
```

### Split with limit

```java
String[] arr1 = s.split("@", 2);
// Output: ["geeks", "for@geeks"]

String[] arr2 = s.split("@", 5);
// Output: ["geeks", "for", "geeks"]

String[] arr3 = s.split("@", -2);
// Output: ["geeks", "for", "geeks"]

String[] arr4 = s.split("s", 0);
// Output: ["geek", "@for@geek"]
```

---

### Split using Colon

```java
String s = "GeeksforGeeks:A Computer Science Portal";
String[] arr = s.split(":");
// Output: ["GeeksforGeeks", "A Computer Science Portal"]
```

---

### Split using a Specific Substring

```java
String s = "GeeksforGeeksforStudents";
String[] arr = s.split("for");
// Output: ["Geeks", "Geeks", "Students"]
```

---

### Split on Whitespace

```java
String s = "Geeks for Geeks";
String[] arr = s.split(" ");
// Output: ["Geeks", "for", "Geeks"]
```

---

### Split using Dot ('.') — Needs Escaping

```java
String s = "Geeks.for.Geeks";
String[] arr = s.split("[.]");
// Output: ["Geeks", "for", "Geeks"]
```

---

### Split Using Regex

```java
String s = "w1, w2@w3?w4.w5";
String[] arr = s.split("[, ?.@]+");
// Output: ["w1", "w2", "w3", "w4", "w5"]
```

---

## 🔤 char to String Conversion

```java
char ch1 = 'A';
String str1 = String.valueOf(ch1);

char ch2 = 'B';
String str2 = Character.toString(ch2);

char ch3 = 'C';
String str3 = "" + ch3;
```

---

## 🔢 String to char and int

```java
String str = "Hello";
char firstChar = str.charAt(0);  // 'H'

String numberStr1 = "123";
int number1 = Integer.parseInt(numberStr1);  // 123

String numberStr2 = "456";
int number2 = Integer.parseInt(numberStr2);  // 456
```


---

## 🔁 String ↔ Integer Conversion

```java
String numberStr = "123";
Integer numberObj = Integer.valueOf(numberStr);  // String to Integer

int number1 = 42;
String str1 = String.valueOf(number1);           // Integer to String

int number2 = 99;
String str2 = Integer.toString(number2);         // Integer to String

int number3 = 7;
String str3 = "" + number3;                      // Integer to String
```

---

## 🔁 String ↔ char\[]

```java
String str = "Hello";
char[] charArray1 = str.toCharArray();  // String to char array

char[] charArray2 = {'J', 'a', 'v', 'a'};
String strFromArray1 = new String(charArray2);  // char array to String

char[] charArray3 = {'H', 'e', 'l', 'l', 'o'};
String strFromArray2 = String.valueOf(charArray3);  // char array to String
```

---

## 🧵 Common `String` Methods

### 🔹 Length & Character Access

```java
int len = str.length();          // 1. Length of string
char ch = str.charAt(0);         // 2. Char at index
```

### 🔹 Substring

```java
String sub1 = str.substring(3);      // 3. From index 3 to end
String sub2 = str.substring(3, 7);   // 4. From index 3 to 6
```

### 🔹 Concatenation

```java
str.concat(" World");           // 5. Appends string
```

### 🔹 Index Searching

```java
str.indexOf("abc");             // 6. First index of substring
str.indexOf("abc", 5);          // 7. From index 5
str.lastIndexOf("abc");         // 8. Last index of substring
```

### 🔹 Comparison

```java
str.equals(other);              // 9. Exact match
str.equalsIgnoreCase(other);    // 10. Match ignoring case

str.compareTo(other);           // 11. Lexicographical comparison
str.compareToIgnoreCase(other); // 12. Lexicographical ignoring case
```

### 🔹 Case Conversion

```java
str.toLowerCase();              // 13. Convert to lowercase
str.toUpperCase();              // 14. Convert to uppercase
```

### 🔹 Trimming and Replacing

```java
str.trim();                              // 15. Trim leading/trailing spaces
str.replace('a', 'b');                   // 16. Replace char
str.replace("old", "new");              // 16. Replace substring
str.replaceAll(regex, replacement);      // Regex-based replace
str.replaceFirst(regex, replacement);    // First match only
```

### 🔹 Other Useful Methods

```java
str.contains("abc");            // 17. Contains substring
str.toCharArray();              // 18. Convert to char array
str.startsWith("Java");         // 19. Starts with prefix
str.startsWith("Java", 5);      // Starts with prefix from offset
```

---

## 🔍 Examples

```java
String text = "Java programming is fun";

System.out.println(text.contains("Java"));    // true
System.out.println(text.contains("java"));    // false (case-sensitive)
System.out.println(text.contains("gram"));    // true
System.out.println(text.contains("Python"));  // false
```

```java
String str1 = "Hello";
String str2 = "hello";
String str3 = "Hello";

System.out.println(str1.equals(str2));  // false
System.out.println(str1.equals(str3));  // true
```

---




## 🔹 `startsWith()` Method

```java
String str = "JavaProgramming";

System.out.println(str.startsWith("Java"));     // true
System.out.println(str.startsWith("Prog"));     // false
System.out.println(str.startsWith("Prog", 4));  // true (starts at index 4)
```

---

## 🔹 String Immutability Example

```java
String s = "Sachin";
s.concat(" Tendulkar");         // Strings are immutable

System.out.println(s);          // Output: Sachin
```

### ✅ If you want to actually append:

```java
String name = "Sachin";
name = name.concat(" Tendulkar");

System.out.println(name);       // Output: Sachin Tendulkar
```

---

## 🔹 Java Regex Patterns – Character Classes

| Pattern           | Meaning                               |
| ----------------- | ------------------------------------- |
| `[xyz]`           | Match either x, y, or z               |
| `[^xyz]`          | Match any character except x, y, or z |
| `[a-zA-Z]`        | Match lowercase and uppercase letters |
| `[a-f[m-t]]`      | Union of a-f and m-t                  |
| `[a-z && p-y]`    | Intersection: characters from p to y  |
| `[a-z && [^bc]]`  | a to z except b and c                 |
| `[a-z && [^m-p]]` | a to z except characters from m to p  |

---

## 🔹 Regex Quantifiers

| Quantifier | Description                                           |
| ---------- | ----------------------------------------------------- |
| `X?`       | X appears **zero or one time**                        |
| `X+`       | X appears **one or more times**                       |
| `X*`       | X appears **zero or more times**                      |
| `X{n}`     | X appears **exactly n times**                         |
| `X{n,}`    | X appears **n or more times**                         |
| `X{n,m}`   | X appears **between n (inclusive) and m (exclusive)** |

---

## 🔹 Regex Examples using `Pattern.matches()`

```java
System.out.println(Pattern.matches("[a-z]", "g"));             // true
System.out.println(Pattern.matches("[a-zA-Z]", "Gfg"));        // false, "Gfg" has 3 characters

System.out.println(Pattern.matches("[b-z]?", "a"));            // false
System.out.println(Pattern.matches("[a-zA-Z]+", "GfgTest"));   // true

System.out.println(Pattern.matches("[^a-z]?", "g"));           // false
System.out.println(Pattern.matches("[geks]*", "geeksgeeks")); // true
```

---

## 🔹 Predefined Regex Character Classes

| Pattern | Meaning                                |
| ------- | -------------------------------------- |
| `.`     | Any character                          |
| `\d`    | Digit, same as `[0-9]`                 |
| `\D`    | Non-digit, same as `[^0-9]`            |
| `\s`    | Whitespace (`space`, `\t`, `\n`, etc.) |
| `\S`    | Non-whitespace                         |
| `\w`    | Word character `[a-zA-Z_0-9]`          |
| `\W`    | Non-word character                     |
| `\b`    | Word boundary                          |
| `\B`    | Non-word boundary                      |

---

## 🔹 Examples

```java
System.out.println(Pattern.matches("\\d+", "1234"));      // true (digits only)
System.out.println(Pattern.matches("\\D+", "1234"));      // false (digits present)
System.out.println(Pattern.matches("\\D+", "Gfg"));       // true (non-digits only)
System.out.println(Pattern.matches("\\S+", "gfg"));       // true (no whitespace)
```

---



