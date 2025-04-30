**insert**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static Node insert(Node root,int val){
      if(root==null){
          root=new Node(val);
          return root;
      }
      
      if(root.data>val){
          root.left=insert(root.left);
      }
      else{
          root.right=insert(root.right);
      }
      
      return root;
  }
  
  public static void main(String args[]){
      Node root=null;
      for(int i=0;i<arr.length;i++){
          root=insert(root,arr[i]);
      }
  }
}
```
**search**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static boolean search(Node root,int key){
      if(root==null){
          return false;
      }
      if(root.data>key){
          return search(root.left,key);
      }
      else if(root.data==key){
          return true;
      }
      else{
          return search(roo.right,key);
      }
  }
}
```
**delete**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
   public static Node delete(Node root,int val){
       if(root.data>val){
           root.left=delete(root.left,val);
       }
       else if(root.data<val){
           root.right=delete(root.right,val);
       }
       else{
           if(root.left==null&&root.right==null){
               return null;
           }
           if(root.left==null){
               return root.right;
           }
           if(root.right==null){
               return root.left;
           }
           
           Node IS=inorderSuccessor(root.right);
           root.data=IS.data;
           root.right=delete(root.right,IS.data);
       }
       
       return root;
   }
   
   public static Node inorderSuccessor(Node root){
       while(root.left!=null){
           root=root.left;
       }
       return root;
   }
}
```
**print in range**
```java
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
   public static void printInRange(Node root,int X,int Y){
       if(root==null){
           return;
       }
       if(root.data>=X&&root.data<=Y){
           printInRange(root.left,X,Y);
           System.out.println(root.data+" ");
           printInRange(root.right,X,Y);
       }
       else if(root.data<=X){
           printInRange(root.right,X,Y);
       }
       else{
           printInRange(root.left,X,Y);
       }
   }
}
```
**print path**
```
/******************************************************************************

Welcome to GDB Online.
GDB online is an online compiler and debugger tool for C, C++, Python, Java, PHP, Ruby, Perl,
C#, OCaml, VB, Swift, Pascal, Fortran, Haskell, Objective-C, Assembly, HTML, CSS, JS, SQLite, Prolog.
Code, Compile, Run and Debug online from anywhere in world.

*******************************************************************************/
public class Main
{
  public static void printPath(ArrayList<Integer> path){
      for(int i=0;i<path.size();i++){
          System.out.print(path.get(i)+" ");
      }
      System.out.println();
  }
  public static void printRoot2Leaf(Node root,ArrayList<Integer> path){
      if(root==null){
          return;
      }
      path.add(root.data);
      if(root.left==null&&root.right==null){
          printPath(path);
      }else{
          printRoot2Leaf(root.left,path);
          printRoot2Leaf(root.right,path);
      }
      path.remove(path.size()-1);
  }
}
```
