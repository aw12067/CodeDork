
import java.util.Arrays;
 
class Main
{
    public static void swap(int[] arr, int i, int j)
    {
        arr[i]^=arr[j];
        arr[j]^=arr[i];
        arr[i]^=arr[j];
    }
 
    public static void arrange(int[] arr, int[] pos)
    {
        for (int i = 0; i < arr.length; i++)
        {
            while (pos[i] != i)
            {
                swap(arr, pos[i], i);
                swap(pos, pos[i], i);
                System.out.println(Arrays.toString(arr)+" "+Arrays.toString(pos));
            }
            System.out.println();
        }
    }
 
    public static void main(String[] args)
    {
        int[] arr = { 1, 2, 3, 4, 5 };        // input array
        int[] pos = { 3, 2, 4, 0, 1 };        // position array
 
        arrange(arr, pos);
        System.out.println(Arrays.toString(arr));
    }
}
