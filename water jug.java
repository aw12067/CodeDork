import java.util.Arrays;
import java.util.Random;
 
class Main
{
    // Utility function to swap elements `A[i]` and `A[j]` in array `A`
    public static void swap(int[] A, int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
 
    // This method is similar to the partition routine of the Quicksort algorithm.
    // It partitions the specified array `A[low, high]` around `pivot`
    // and returns the partition index.
    private static int partition(int[] A, int low, int high, int pivot)
    {
        int pIndex = low;
        for (int j = low; j < high; j++)
        {
            if (A[j] < pivot)
            {
                swap(A, pIndex, j);
                pIndex++;
            }
            else if (A[j] == pivot)
            {
                swap(A, j, high);
                j--;
            }
        }
        swap(A, pIndex, high);
        return pIndex;
    }
 
    // Group the jugs into pairs of red and blue jugs that hold the same amount
    // of water. This method is similar to the Quicksort-like routine.
    private static void findMatchingPairs(int[] red, int[] blue, int low, int high)
    {
        // base case: if there is only one red jug and one blue jug,
        // they must be of the same size
        if (low >= high) {
            return;
        }
 
        // Randomly select a jug from either red or blue jugs
        int r = new Random().nextInt(high - low + 1) + low;
        int chosenJug = red[r];        // or use `blue[r]`
 
        // Partition the red jugs around the chosen jug
        int pivot = partition(red, low, high, chosenJug);
 
        // Now partition the blue jugs around the chosen jug
        partition(blue, low, high, chosenJug);
 
        // `pivot` now points to an index of the chosen jug in red/blue jugs.
 
        // Recur, once the red and blue jugs are divided into two groups
        // around the chosen jug
        findMatchingPairs(red, blue, low, pivot - 1);
        findMatchingPairs(red, blue, pivot + 1, high);
    }
 
    public static void main(String[] args)
    {
        int[] red = {6, 3, 2, 8, 7};
        int[] blue = {8, 6, 7, 2, 3};
 
        findMatchingPairs(red, blue, 0, red.length - 1);
 
        System.out.println("Red jugs : " + Arrays.toString(red));
        System.out.println("Blue jugs : " + Arrays.toString(blue));
    }
}
