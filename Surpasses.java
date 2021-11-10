
import java.util.Arrays;
import java.util.*;
public class Main 
{
	private static void mergeAndCount(int[] arr, int l,int m, int r,HashMap<Integer,Integer> map)
	{

		// Left subarray
		int[] left = Arrays.copyOfRange(arr, l, m + 1);

		// Right subarray
		int[] right = Arrays.copyOfRange(arr, m + 1, r + 1);

		int i = 0, j = 0, k = l, count = 0;

		while (i < left.length && j < right.length) 
		{
			    
			if (left[i] <= right[j])
			{
			    map.put(left[i],map.getOrDefault(left[i],0)+count);
				arr[k++] = left[i++];
			}
			else 
			{
				arr[k++] = right[j++];
				count++;
			}
		}
		while (i < left.length)
		{
		    map.put(left[i],map.getOrDefault(left[i],0)+count);
			arr[k++] = left[i++];
		}
		while (j < right.length)
			arr[k++] = right[j++];
	}
	public  static void mergeSortAndCount(int[] arr, int l,int r,HashMap<Integer,Integer> map )
	{
		if (l < r) 
    {
			int m = l + (r - l) / 2;


			// Left subarray 
			mergeSortAndCount(arr, l, m,map);

			// Right subarray 
			mergeSortAndCount(arr, m + 1, r,map);

			// Merge 
			mergeAndCount(arr, l, m, r,map);
		}

	}
    public static HashMap<Integer,Integer> find(int arr[],int n)
    {
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        int dup[] = new int[n];
        for(int i=0;i<n;i++)
        {
            map.put(arr[i],0);
            dup[i] = arr[i];
        }
        mergeSortAndCount(dup,0,n-1,map);
        return map;
    }
	public static void main(String[] args)
	{
		int[] arr = {  4, 6, 3, 9, 7, 10  };
    HashMap<Integer,Integer> map = find(arr, arr.length );
		int n = arr.length;
		for(int i=0;i<arr.length;i++)
		{
		    System.out.print((n-1)-i-map.get(arr[i])+" ");
		}
	}
}
