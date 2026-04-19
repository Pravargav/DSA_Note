
# -> Let’s consider below code:

```java
interface Cookable {
    public void cook();
}

class Food {
    Cookable c = new Cookable() {
     public void cook() {
        System.out.println("anonymous cookable implementer");
         }
     };
 }
```
 
The preceding code creates an instance of an anonymous inner class, but here, the new just-in-time class is an implementer of the Cookable interface. And note that this is the only time you will ever see the syntax:

```java
 new Cookable()

```

where Cookable is an interface rather than a nonabstract class type. Think about it: You can’t instantiate an interface, yet that’s what the code looks like it’s doing. But, of course, it’s not instantiating a Cookable object-- it's creating an instance of a new anonymous implementer of Cookable.

You can read this line:
```java
 Cookable c = new Cookable(){}
```
as “Declare a reference variable of type Cookable that, obviously, will refer to an object from a class that implements the Cookable interface. But, oh yes, we don’t yet have a class that implements Cookable, so we’re going to make one right here, right now. We don’t need a name for the class, but it will be a class that implements Cookable, and this curly brace starts the definition of the new implementing class.”

Important to remember for anonymous interface implementers — they can implement only one interface. There simply isn’t any mechanism to say that your anonymous inner class is going to implement multiple interfaces. In fact, an anonymous inner class can’t even extend a class and implement an interface at the same time. The inner class has to choose either to be a subclass of a named class and not directly implement any interface at all or to implement a single interface.

So don’t be fooled by any attempts to instantiate an interface except in the case of an anonymous inner class. The following is not legal:
```java
 Runnable r = new Runnable(); // can't instantiate interface
```
whereas the following is legal, because it’s instantiating an implementer of the Runnable interface(an anonymous implementation class):

```java
Runnable r = new Runnable() { 
   public void run(){ }
};
```

So, we can’t actually instantiate an interface in Java. Thanks for reading :) .
