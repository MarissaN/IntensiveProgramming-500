/*CS 500 - HW 4.1 - Best Fight Aviation
 *
 * This program uses the Binary Search Tree data structure to store Best Flight Aviation Elite members' name and miles. 
 * The user can input members, get the list of members, modify miles and delete members
 * 
 * Marissa Norris
 */

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public Scanner scanner;

    public class BinarySearchTree {

        class Node {
            Node left, right;
            private String name;
            private int miles;
        
            public Node(String name, int miles) {
                this.name = name;
                this.miles = miles;
                left = right = null;
            }

        
        public void setMiles(int miles) {
            this.miles = miles;
        }

        // Compare nodes based on name
        public int compareTo(Node other) {
            return this.name.compareTo(other.name);
        }
        }
       
        Node root;
    
        public boolean isEmpty() {
            return root == null;
        }

        public BinarySearchTree() {
            root = null;
        }

        // Insert a member into the BST
        public void insert(String name, int miles) {
            root = insertRec(root, new Node(name, miles));
        }

        // A recursive function to insert a node into BST
        private Node insertRec(Node root, Node newNode) {
            if (root == null)
                return newNode;

            // Recur down the tree
            if (newNode.compareTo(root) < 0)
                root.left = insertRec(root.left, newNode);
            else if (newNode.compareTo(root) > 0)
                root.right = insertRec(root.right, newNode);

            return root;
        }

    
        public Node search(String name) {
            return searchRec(root, name);
        }

        private Node searchRec(Node root, String name) {
            if (root == null || root.name.equals(name)) {
                return root;
            }
            if (name.compareTo(root.name) < 0) {
                return searchRec(root.left, name);
            } else {
                return searchRec(root.right, name);
            } 
        }

        public void HighestMiles() {
            Node node = HighestMilesRec(root);
            if (node != null)
                System.out.println(node.name + " is eligible to win a free first class ticket.");
            else
                System.out.println("No elite members found.");
        }

        private Node HighestMilesRec(Node root) {
            if (root == null)
                return null;

            Node left = HighestMilesRec(root.left);
            Node right = HighestMilesRec(root.right);

            // Find the maximum of left, right, and root
            Node maxNode = root;
            if (left != null && left.miles > maxNode.miles)
                maxNode = left;
            if (right != null && right.miles > maxNode.miles)
                maxNode = right;

            return maxNode;
        }


            public void modify(Scanner scanner, String name) {     

                Node node = search(name);
                if (node == null) {
                    System.out.println("Member " + name + " not found");
                } else {
                    System.out.println("Enter the airline earned miles of member:");
                    int AddMiles = scanner.nextInt();
                    node.miles += AddMiles;
                    node.setMiles(node.miles);
                    System.out.println("Member " + name + "'s new miles are " + node.miles);
                }
            }

       
        public void delete(String name) {
            root = deleteNode(root, name);
        }
        
        private Node deleteNode(Node root, String name) {
            if (root == null){ // If the tree is empty or the key is not found
                System.out.println("Member " + name + " not found");
                return root;
            }
            // Search for the name of the member to be deleted
            if (name.compareTo(root.name) < 0)
                root.left = deleteNode(root.left, name);
            else if (name.compareTo(root.name) > 0)
                root.right = deleteNode(root.right, name);
            else { 
        
                // Case 1: Node to be deleted has no children (it's a leaf node) or only one child
                if (root.left == null)
                    return root.right;
                else if (root.right == null)
                    return root.left;
        
                // Case 2: Node to be deleted has two children
                // Get the inorder successor (smallest node in the right subtree)
                root.name = minValueNode(root.right);
        
                // Delete the inorder successor
                root.right = deleteNode(root.right, root.name);
            }
            System.out.println("Member " + name + " deleted successfully");
            return root;
        }
        
        private String minValueNode(Node root) {
            String minVal = root.name;
            while (root.left != null) {
                minVal = root.left.name;
                root = root.left;
            }
            return minVal;
        }
    
        // Method to print inorder traversal of BST
        public void inorder() {
            inorderRec(root);
        }
    
        // A utility function to do inorder traversal of BST
        private void inorderRec(Node root) {
            if (root != null) {
                inorderRec(root.left);
                System.out.println(root.name + " : " + root.miles);
                inorderRec(root.right);
            }
          
        }
    
    }

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        App appInstance = new App(); // Create an instance of the enclosing class
        BinarySearchTree BST = appInstance.new BinarySearchTree();

        System.out.println("1. Insert an elite member");
        System.out.println("2. Show the list of elite members");
        System.out.println("3. Show the winner of the free-class ticket");
        System.out.println("4. Modify the air miles");
        System.out.println("5. Delete an elite member");
        System.out.println("6. Exit");

         //Infinite loop that terminate when the user select 'Exit'
         while(true){
            try{
                System.out.println("\n Enter your choice:");
                int choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("\nEnter the name of the member:");
                        String name = scanner.next();
                        System.out.println("Enter the air miles of member:");
                        int miles = scanner.nextInt();
                        while(miles < 100000)
                        {
                            System.out.println("\nSorry, " + name + " is not eligible to enter the elite member group");
                            System.out.println("Must have miles over 10,000");
                            System.out.println("\nEnter the name of the member:");
                            name = scanner.next();
                            System.out.println("Enter the air miles of member:");
                            miles = scanner.nextInt();
                        }
                        BST.insert(name, miles);
                        break;
                    case 2:
                        if(BST.isEmpty()){
                            System.out.println("\nSorry, list is empty");
                        }else{
                            System.out.println("\nThe list of Elite members:");
                            BST.inorder();
                        }
                            break;
                    case 3:
                        if(BST.isEmpty()){
                            System.out.println("\nSorry, list is empty");
                        }else
                            BST.HighestMiles();
                            break;
                    case 4:
                        if(BST.isEmpty()){
                            System.out.println("\nSorry, list is empty");
                        }else{
                            System.out.println("\nEnter the name of the member:");
                            String name2 = scanner.next();
                            BST.modify(scanner,name2);
                        }
                        break;
                    case 5:
                        if (BST.root == null) {
                            System.out.println("\nSorry, the list is empty");
                        } else {
                            System.out.println("\nEnter the name:");
                            String deleteName = scanner.next();
                            BST.delete(deleteName);
                        }
                        break;
                    case 6:
                        System.out.println("\nThank you!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter a valid option.");
                }
            } catch (InputMismatchException e) {
                // Handle the case where the user inputs something other than an integer
                System.out.println("\nInvalid input. Please enter a valid integer choice.");
                scanner.next(); 
            }
        }
       
    }
    
}
