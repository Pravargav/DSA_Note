import java.util.* ;
import java.io.*; 
import java.util.ArrayList;

public class Solution{
    public static int maximumProfit(ArrayList<Integer> prices){
        // Write your code here.
        return findMaxProfit(prices,0,0,0);
    }
    private static int findMaxProfit(ArrayList<Integer> prices, int i,int j,int p) {
     if(i==prices.size()){
         return p;
     }
     if(j==prices.size()){
         
         if(j<prices.size()){
         p=Math.max(prices.get(j)-prices.get(i),p);
         }
         return findMaxProfit(prices, i+ 1,i+ 2,p);
     }
     
     
     p=Math.max(prices.get(j)-prices.get(i),p);
    
     return findMaxProfit(prices,i,j+ 1,p);
    }
}
