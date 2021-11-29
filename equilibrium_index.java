import java.util.*;
 
class Main
{
    // Optimized method to find the equilibrium index of an array
    public static void findEquilibriumIndex(int[] A)
    {
        // `total` stores the sum of all the array elements
        int total = 0;
        for(int i:A)
        {
          total+=i;
        }
 
        
        int left = 0;
 
        // maintain a list of indices
        List<Integer> indices = new ArrayList<>();
 
       
        for (int i = 0; i <A.length; i++)
        {
            if (left == total - (A[i] + left)) {
                indices.add(i);
            }
          
            left += A[i];
        }
 
        System.out.println("Equilibrium Index found at " + indices);
    }
 
    public static void main (String[] args)
    {
        int[] A = { 0, -3, 5, -4, -2, 3, 1, 0 };
 
        findEquilibriumIndex(A);
    }
}
