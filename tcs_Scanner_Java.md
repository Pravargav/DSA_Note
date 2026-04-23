

####  Original Code — Using `hasNextInt()` + `nextLine()`

Reads number of strings, then reads full lines.

```java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScanListOfStrings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> strings = new ArrayList<>();

        try {
            System.out.print("Enter the number of strings: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter an integer.");
                return;
            }

            int n = sc.nextInt();
            sc.nextLine(); // consume newline

            if (n <= 0) {
                System.out.println("Number must be positive.");
                return;
            }

            System.out.println("Enter " + n + " strings:");

            for (int i = 0; i < n; i++) {
                String input = sc.nextLine().trim();

                if (input.isEmpty()) {
                    System.out.println("Empty string not allowed.");
                    i--;
                } else {
                    strings.add(input);
                }
            }

            System.out.println("\nScanned Strings:");
            for (String s : strings) {
                System.out.println(s);
            }

        } finally {
            sc.close();
        }
    }
}
```
####  Using hasNext() — Token-Based Input
Reads words separated by whitespace.
```java
import java.util.*;

public class ScanUsingHasNext {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> list = new ArrayList<>();

        System.out.println("Enter words (type stop to end):");

        while (sc.hasNext()) {
            String word = sc.next();

            if (word.equalsIgnoreCase("stop"))
                break;

            list.add(word);
        }

        System.out.println("\nWords:");
        list.forEach(System.out::println);

        sc.close();
    }
}
```
####  Using hasNextLine() — Line-Based Input
Reads full lines including spaces.
```java
import java.util.*;

public class ScanUsingHasNextLine {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<String> lines = new ArrayList<>();

        System.out.println("Enter lines (empty line to stop):");

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.isEmpty())
                break;

            lines.add(line);
        }

        System.out.println("\nLines:");

        sc.close();
    }
}
```
####  Character Scanning (No hasNextChar() in Java)
Java does NOT provide hasNextChar().
```java
import java.util.*;

public class ScanCharacters {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = sc.nextLine();

        for (char ch : input.toCharArray()) {
            System.out.println("'" + ch + "'");
        }

        sc.close();
    }
}
```
####  Detecting Space Characters
Counts spaces in a line.
```java
import java.util.*;

public class DetectSpaces {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String input = sc.nextLine();

        int count = 0;

        for (char ch : input.toCharArray()) {
            if (ch == ' ')
                count++;
        }

        System.out.println("Spaces: " + count);

        sc.close();
    }
}
```

####  Detecting Newline Behavior
Counts number of lines entered.
```java
import java.util.*;

public class CountLines {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter lines (type END to stop):");

        int lines = 0;

        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            if (line.equals("END"))
                break;

            lines++;
        }

        System.out.println("Total lines: " + lines);

        sc.close();
    }
}
```

####  Detecting Blank Lines (Only Enter Pressed)
```java
import java.util.*;

public class BlankLineDetector {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter text (STOP to end):");

        while (true) {
            String line = sc.nextLine();

            if (line.equals("STOP"))
                break;

            if (line.isEmpty())
                System.out.println("Blank line detected");
            else
                System.out.println("You typed: " + line);
        }

        sc.close();
    }
}
```
#### Important Scanner Notes

| Method         | Reads          | Stops at space | Keeps spaces |
|----------------|----------------|---------------|--------------|
| `next()`       | Word           | Yes           | No           |
| `nextLine()`   | Full line      | No            | Yes          |
| `hasNext()`    | Token available| —             | —            |
| `hasNextLine()`| Line available | —             | —            |
| `nextInt()`    | Integer        | —             | —            |

#### Important Trick
After using nextInt(), always call:
```java
sc.nextLine();
```
This consumes the leftover newline character before reading a full line.
