import java.util.ArrayList;

public class Main {

    public static int maximumProfit(ArrayList<Integer> prices) {
        // Helper function to handle recursion
        return findMaxProfit(prices, 0, 0,0);
    }

    private static int findMaxProfit(ArrayList<Integer> prices, int i,int j,int p) {
     if(i==prices.size()){
         return p;
     }
     if(j==prices.size()){
         System.out.println(i+" "+j+" "+p);
         if(j<prices.size()){
         p=prices.get(j)-prices.get(i);
         }
         return findMaxProfit(prices, i+ 1,i+ 2,p);
     }
     System.out.println(i+" "+j+" "+p);
     
     p=prices.get(j)-prices.get(i);
         
     return findMaxProfit(prices,i,j+ 1,p);
    }

    public static void main(String[] args) {
        ArrayList<Integer> prices = new ArrayList<>();
        prices.add(7);
        prices.add(1);
        prices.add(5);
        prices.add(3);
        prices.add(6);
        prices.add(4);

        System.out.println("Maximum Profit: " + maximumProfit(prices));
    }
}
