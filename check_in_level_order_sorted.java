// Java implementation of the approach
import java.util.*;
import java.io.*;

/* Class containing left and right
child of current node and key value*/
class Node {
	int data;
	Node left, right;

	public Node(int item)
	{
		data = item;
		left = right = null;
	}
}

class Main {

	/* Function to locate which level to check
	for the existence of key. */
	public static int findLevel(Node root, int data)
	{

		// If the key is less than the root,
		// it will certainly not exist in the tree
		// because tree is level-order sorted
		if (data < root.data)
			return -1;

		// If the key is equal to the root then
		// simply return 0 (zero'th level)
		if (data == root.data)
			return 0;

		int cur_level = 0;

		while (true) {
			cur_level++;
			root = root.left;

			// If the key is found in any leftmost element
			// then simply return true
			// No need for any extra searching
			if (root.data == data)
				return -2;

			// If key lies between the root data and
			// the left child's data OR if key is greater
			// than root data and there is no level
			// underneath it, return the current level
			if (root.data < data
				&& (root.left == null
					|| root.left.data > data)) {
				break;
			}
		}

		return cur_level;
	}

	/* Function to traverse a binary
	encoded path and return the value
	encountered after traversal. */
	public static int traversePath(Node root,
								ArrayList<Integer> path)
	{
		for (int i = 0; i < path.size(); i++) {
			int direction = path.get(i);

			// Go left
			if (direction == 0) {

				// Incomplete path
				if (root.left == null)
					return -1;
				root = root.left;
			}

			// Go right
			else {

				// Incomplete path
				if (root.right == null)
					return -1;
				root = root.right;
			}
		}

		// Return the data at the node
		return root.data;
	}

	/* Function to generate gray code of
	corresponding binary number of integer i */
	static ArrayList<Integer> generateGray(int n, int x)
	{

		// Create new arraylist to store
		// the gray code
		ArrayList<Integer> gray = new ArrayList<Integer>();

		int i = 0;
		while (x > 0) {
			gray.add(x % 2);
			x = x / 2;
			i++;
		}

		// Reverse the encoding till here
		Collections.reverse(gray);

		// Leftmost digits are filled with 0
		for (int j = 0; j < n - i; j++)
			gray.add(0, 0);

		return gray;
	}

	/* Function to search the key in a
	particular level of the tree. */
	public static boolean binarySearch(Node root,
									int start,
									int end,
									int data,
									int level)
	{
		if (end >= start) {

			// Find the middle index
			int mid = (start + end) / 2;

			// Encode path from root to this index
			// in the form of 0s and 1s where
			// 0 means LEFT and 1 means RIGHT
			ArrayList<Integer> encoding = generateGray(level, mid);

			// Traverse the path in the tree
			// and check if the key is found
			int element_found = traversePath(root, encoding);

			// If path is incomplete
			if (element_found == -1)

				// Check the left part of the level
				return binarySearch(root, start, mid - 1, data, level);

			if (element_found == data)
				return true;

			// Check the right part of the level
			if (element_found < data)
				return binarySearch(root, mid + 1, end, data, level);

			// Check the left part of the level
			else
				return binarySearch(root, start, mid - 1, data, level);
		}

		// Key not found in that level
		return false;
	}

	// Function that returns true if the
	// key is found in the tree
	public static boolean findKey(Node root, int data)
	{
		// Find the level where the key may lie
		int level = findLevel(root, data);

		// If level is -1 then return false
		if (level == -1)
			return false;

		// If level is -2 i.e. key was found in any
		// leftmost element then simply return true
		if (level == -2)
			return true;

		// Apply binary search on the elements
		// of that level
		return binarySearch(root, 0, (int)Math.pow(2, level) - 1, data, level);
	}

	// Driver code
	public static void main(String[] args)
	{
		/* Consider the following level-order sorted tree
		
						5
					/ \
					8	 10
					/ \ / \
				13 23 25 30
				/ \ /
				32 40 50
		*/

		Node root = new Node(5);
		root.left = new Node(8);
		root.right = new Node(10);
		root.left.left = new Node(13);
		root.left.right = new Node(23);
		root.right.left = new Node(25);
		root.right.right = new Node(30);
		root.left.left.left = new Node(32);
		root.left.left.right = new Node(40);
		root.left.right.left = new Node(50);

		// Keys to be searched
		int arr[] = { 5, 8, 9 };
		int n = arr.length;

		for (int i = 0; i < n; i++) {
			if (findKey(root, arr[i]))
				System.out.println("Yes");
			else
				System.out.println("No");
		}
	}
}
