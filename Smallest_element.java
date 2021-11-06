class Main
{
	static void solve(int[] a, int n)
	{
		
		int[] min = new int[n];

		suffix_min[n - 1] = a[n - 1];
		for (int i = n - 2; i >= 0; i--) 
    {
			min[i]= Math.min(min[i + 1], a[i]);
		}

		for (int i = 0; i < n; i++) 
    {
			int low = i + 1, high = n - 1, ans = -1;

			while (low <= high) 
      {
				int mid = (low + high) / 2;
        
				if (min[mid] < a[i]) 
        {
					ans = mid;
					low = mid + 1;
				}
				else
					high = mid - 1;
			}
			System.out.print(ans + " ");
		}
	}
	public static void main(String[] args)
	{
		int[] a = { 3, 1, 5, 2, 4 };
		int n = a.length;

		solve(a, n);
	}
}
