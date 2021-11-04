import java.lang.*;
import java.util.*;

public class Main
{
	public static int dp[][] = new int[3000][3000];
    public static int helper(int arr[],int start,int end)
    {
      if(start>end)
      {
          return 0;
      }
       if(dp[start][end]!=-1)
      {
          return dp[start][end];
      }
        else if(start==end)
        {
            return dp[start][start];
        }
        else if(start+1==end)
        {
            return Math.max(arr[start],arr[end]);
        }
        else
        {
            int val1 = arr[start];
            if(arr[end]>arr[start+1])
            {
                val1+=helper(arr,start+1,end-1);
            }
            else
            {
                val1+=helper(arr,start+2,end);
            }

            int val2 = arr[end];
            if(arr[end-1]>arr[start])
            {
                val2+=helper(arr,start,end-2);
            }
            else
            {
                val2+=helper(arr,start+1,end-1);
            }

            dp[start][end] = Math.max(val1,val2);
        }
        return dp[start][end];
    }
    public static void main(String args[] ) throws java.lang.Exception
    {
        Scanner sc = new Scanner(System.in);
        int t = 1;
        boolean flag = true;
        while(flag)
        {
            int n = sc.nextInt();
            if(n==0)
            {
            	flag = false;
            	break;
            }
            int arr[] = new int[n];
            int sum = 0;
            for(int i=0;i<n;i++)
            {
                arr[i] = sc.nextInt();
                sum+=arr[i];
            }
            for(int temp[]:dp)
            {
                Arrays.fill(temp,-1);
            }
            for(int i=0;i<n;i++)
            {
                dp[i][i]=arr[i];
            }
            helper(arr,0,n-1);
            int ans = (2*dp[0][n-1])-sum;
            System.out.println("In game "+ t++ +", the greedy strategy might lose by as many as "+ans+" points.");
        }
    }
}
