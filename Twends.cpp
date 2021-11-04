#include <iostream>
using namespace std;
int dp[3000][3000];

int  helper(int arr[],int start,int end)
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
		return max(arr[start],arr[end]);
	}
	else
	{
		int value1 = arr[start];
		if(arr[end]>arr[start+1])
		{
			value1+=helper(arr,start+1,end-1);
		}
		else
		{
			value1+=helper(arr,start+2,end);
		}


		int value2 = arr[end];
		if(arr[end-1]>arr[start])
		{
			value2+=helper(arr,start,end-2);
		}
		else
		{
			value2+=helper(arr,start+1,end-1);
		}

		dp[start][end] = max(value1,value2);
	}
	return dp[start][end];
}

int main()
{
	int n;
	int arr[n];
	int t = 1;
	while(1)
	{
		cin >> n;
		int sum = 0;
		if(n==0)
		{
			return 0;
		}
		else
		{
			for(int i=0;i<n;i++)
			{
				cin>>arr[i];
				sum+=arr[i];
			}

			for(int i=0;i<n;i++)
			{
				for(int j=0;j<n;j++)
				{
					dp[i][j]=-1;
				}
			}

			for(int i=0;i<n;i++)
			{
				dp[i][i]=arr[i];
			}

			helper(arr,0,n-1);

			int smart = dp[0][n-1];
			int greedy = sum-smart;

			cout<<"In game "<<t<<", the greedy strategy might lose by as many as "<<smart-greedy<<" points."<<endl;
			t++;
		}
	}
	return 0;
}
