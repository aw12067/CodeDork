import java.util.*;

class Main
{
  static int count_of_accident(int n,int m)
  {
    if (n > m)
    {
      return (m * (m + 1)) / 2;
    }
    else // when n<m or n==m
    {
      return (n * (n + 1))/ 2+ (m - n) * n;
    }
  }
  
static double count_of_collision(String a,String b)
{
    int n = a.length(), m = b.length();

    double answer = 0;

    int truck_b = 0;
    for (int i = 0; i < m; i++)
    {
      if (b.charAt(i) == 'T')
      {
        truck_b++;
      }
    }

    // Count total number of collisions
    // while traversing the String a
    for (int i = 0; i < n && i < m; i++)
    {
      if (a.charAt(i) == 'T')
      {
        answer+= truck_b;
      }

      if (b.charAt(i) == 'T')
      {
        truck_b--;
      }
    }
    return answer;
  }
  
  static void findProbability(String a,String b)
  {
    int accidents= count_of_accident(a.length(), b.length());

    double collisions= count_of_collision(a, b);

    System.out.println(collisions/ accidents);
  }
  public static void main(String[] args)
  {
    String S = "TCCBCTTB", T = "BTCCBBTT";

    findProbability(S, T);
  }
}

// This code is contributed by 29AjayKumar
