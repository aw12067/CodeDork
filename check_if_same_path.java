import java.util.HashMap;
import java.util.Map;
 
class Node
{
    int data;
    Node left, right;
 
    Node(int data)
    {
        this.data = data;
        this.left = this.right = null;
    }
}
 
class Main
{
    public static int preorderDFS(Node root,HashMap<Node, Integer> arrival, HashMap<Node, Integer> departure, int time) 
    {
        if (root == null) 
        {
            return time;
        }
 
        //arrival time
        arrival.put(root, ++time);
 
        //left
        time = preorderDFS(root.left, arrival, departure, time);
 
        //right
        time = preorderDFS(root.right, arrival, departure, time);
 
        //departure time
        departure.put(root, ++time);
 
        return time;
    }
 
    public static boolean Check(Node x, Node y, HashMap<Node, Integer> arrival, HashMap<Node, Integer> departure)
    {
        if (!arrival.containsKey(x) || !arrival.containsKey(y)) 
        {
            System.out.println("Node " + x.data + " or Node " + y.data +" is not the actual node in the tree");
            return false;
        }
 
        boolean isAncestor = arrival.get(x) < arrival.get(y) && departure.get(x) > departure.get(y);
 
        boolean isDescendant = arrival.get(y) < arrival.get(x) && departure.get(y) > departure.get(x);
 
        if (isAncestor) 
        {
            System.out.println("Node " + x.data + " is an ancestor of Node " + y.data);
        }
        else if (isDescendant) 
        {
            System.out.println("Node " + x.data + " is a direct descendant of Node " +y.data);
        }
        else 
        {
            System.out.println("Node " + x.data + " is a neither an ancestor nor a " +"descendant of Node " + y.data);
        }
 
        return isAncestor || isDescendant;
    }
 
    public static void main(String[] args)
    {
        // construct a binary tree as per the above diagram
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left.left = new Node(8);
        root.left.left.right = new Node(9);
        root.left.right.right = new Node(10);
        root.right.right.left = new Node(11);
        root.left.left.right.left = new Node(12);
        root.left.left.right.right = new Node(13);
        root.right.right.left.left = new Node(14);
 
        //arrival time storage
        HashMap<Node, Integer> arrival = new HashMap<Node, Integer>();
 
        //departure time storage
        HashMap<Node, Integer> departure = new HashMap<Node, Integer>();
 
        //starting from root as time =>0
        int time = 0;
 
        preorderDFS(root, arrival, departure, time);
 
        // printTree(root, arrival, departure);
 
        Check(root.right, root.right.right.left.left,arrival, departure);//3  14
 
        Check(root.left.left.right.left, root.left,arrival, departure);//12   2
 
        Check(root.left.left, root.left.right, arrival, departure);//4    5
 
        Check(new Node(root.left.left.data), root.left.right,arrival, departure);//new(4)   5
    }
}
