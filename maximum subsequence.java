class Main
{
    // Find the index of 0 to replace with 1 to get the maximum sequence
    // of continuous 1's
    public static int findIndexofZero(int[] A)
    {
        // base case
        if (A.length == 1) {
            return (A[0] == 0 ? 0 : -1);
        }
 
        // A[i] now stores the length of consecutive 1's ending at `A[i]`
        for (int i = 1; i < A.length; i++)
        {
            if (A[i] == 1) {
                A[i] += A[i - 1];
            }
        }
 
        int count = 0;
 
        // traverse the array from right to left
        for (int i = A.length - 1; i >= 0; i--)
        {
            // update the count to the number of adjacent 1's, which includes the
            // current element
            count = Math.max(A[i], count);
 
            // update the array with the count of adjacent 1's for each
            // non-zero element
            if (A[i] != 0) {
                A[i] = count;
            }
            else {
                // reset the count if the current element is 0
                count = 0;
            }
        }
 
        int max_count = 0;      // stores the maximum number of 1's (including 0)
        int max_index = -1;     // stores the index of 0 to be replaced
 
        // consider each index `i` in the array
        for (int i = 0; i < A.length; i++)
        {
            // if the current element is 0
            if (A[i] == 0)
            {
                // update maximum count and index of 0 to be replaced if
                // required by taking a left and right element count
 
                if (i == 0)
                {
                    if (max_count < A[i + 1] + 1)
                    {
                        max_count = A[i + 1] + 1;
                        max_index = i;
                    }
                }
 
                else if (i == A.length - 1)
                {
                    if (max_count < A[i - 1] + 1) {
                        max_count = A[i - 1] + 1;
                        max_index = i;
                    }
                }
 
                else if (max_count < A[i - 1] + A[i + 1] + 1)
                {
                    max_count = A[i - 1] + A[i + 1] + 1;
                    max_index = i;
                }
            }
        }
 
        // restore the original array
        for (int i = 1; i < A.length; i++)
        {
            if (A[i] != 0) {
                A[i] = 1;
            }
        }
 
        // return the index of 0 to be replaced, or -1 if the array contains all 1's
        return max_index;
    }
 
    public static void main (String[] args)
    {
        int[] A = { 0, 0, 1, 0, 1, 1, 1, 0, 1, 1 };
 
        int index = findIndexofZero(A);
 
        if (index != -1) {
            System.out.print("Index to be replaced is " + index);
        }
        else {
            System.out.print("Invalid input");
        }
    }
}
