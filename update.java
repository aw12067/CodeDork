/ A class to store a BST node
class Node
{
    int data;
    Node left, right;
 
    Node(int data) {
        this.data = data;
    }
}
 
class Main
{
  public static int sum = 0;
    // Function to perform inorder traversal on the tree
    public static void inorder(Node root)
    {
        if (root == null) {
            return;
        }
 
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
 
    // Recursive function to insert a key into a BST
    public static Node insert(Node root, int key)
    {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new Node(key);
        }
 
        // if the given key is less than the root node, recur for the left subtree
        if (key < root.data) {
            root.left = insert(root.left, key);
        }
        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, key);
        }
 
        return root;
    }
 
    // Helper function to return the sum of all nodes present in a binary tree
   public static void change(Node root)
   {
     if(root!=null)
     {
       change(root.right);
       root.data+=sum;
       sum = root.data;
       change(root.left);
     }
   }
    public static void main(String[] args)
    {
        int[] keys = { 5, 3, 2, 4, 6, 8, 10 };
 
        /* Construct the following tree
                   5
                 /   \
                /     \
               3       8
              / \     / \
             /   \   /   \
            2    4  6     10
        */
 
        Node root = null;
        for (int key: keys) {
            root = insert(root, key);
        }
      change(root);
      inorder(root);
    }
}
