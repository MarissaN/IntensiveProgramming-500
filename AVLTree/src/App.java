/*CS 500 - HW 5 - AVL Tree 
 *
 * Receives a sentence from user, breaks down to words and store in an AVL tree. Prints the words in Inorder, Preorder and Postorder
 * 
 * Marissa Norris
 */

import java.util.Scanner;

public class App {

    public class AVLTree {

        class Node {
            private String word;
            private int height;
            Node left;
            Node right;

            public Node(String word){
                this.word = word;
                height = 1;
                left = right = null;
            }

        // Compare nodes based on name
         public int compareTo(Node other) {
            return this.word.compareTo(other.word);
        }
        }

        Node root;

        public boolean isEmpty() {
            return root == null;
        }

        public AVLTree() {
            root = null;
        }

        void updateHeight(Node n) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }
    
        int height(Node n) {
            return n == null ? -1 : n.height;
        }
    
        int getBalance(Node n) {
            return (n == null) ? 0 : height(n.right) - height(n.left);
        }

        Node rotateRight(Node y) {
            Node x = y.left;
            if (x == null)
                return y;
            Node z = x.right;
            x.right = y;
            y.left = z;
            updateHeight(y);
            updateHeight(x);
            return x;
        }

        Node rotateLeft(Node y) {
            Node x = y.right;
            if (x == null)
                return y;
            Node z = x.left;
            x.left = y;
            y.right = z;
            updateHeight(y);
            updateHeight(x);
            return x;
        }

        Node rebalance(Node z) {
            updateHeight(z);
            int balance = getBalance(z);
            if (balance > 1) {
                if (height(z.right.right) > height(z.right.left)) {
                    z = rotateLeft(z);
                } else {
                    z.right = rotateRight(z.right);
                    z = rotateLeft(z);
                }
            } else if (balance < -1) {
                if (height(z.left.left) > height(z.left.right))
                    z = rotateRight(z);
                else {
                    z.left = rotateLeft(z.left);
                    z = rotateRight(z);
                }
            }
            return z;
        }

        Node insert(Node newNode, String word) {
            if (newNode == null) {
                return new Node(word);
            } else if (word.compareTo(newNode.word) < 0) {
                newNode.left = insert(newNode.left, word);
            } else if (word.compareTo(newNode.word) > 0) {
                newNode.right = insert(newNode.right, word);
            } else {
                throw new RuntimeException("Duplicate Key!");
            }
            newNode.height = 1 + Math.max(height(newNode.left), height(newNode.right));
            return rebalance(newNode);
        }
        
        public void insert(String word) {
            root = insert(root, word);
        }

        // Preorder traversal
        void preorder(Node node) {
            if (node != null) {
                System.out.print(node.word + " ");
                preorder(node.left);
                preorder(node.right);
            }
        }

        // Inorder traversal
        void inorder(Node node) {
            if (node != null) {
                inorder(node.left);
                System.out.print(node.word + " ");
                inorder(node.right);
            }
        }

        // Postorder traversal
        void postorder(Node node) {
            if (node != null) {
                postorder(node.left);
                postorder(node.right);
                System.out.print(node.word + " ");
            }
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        App appInstance = new App();
        AVLTree tree = appInstance.new AVLTree();
    
        System.out.println("Enter a sentence:");
        String input = scanner.nextLine();
    
        String[] words = input.split("\\s+");
    
        for (String word : words){
            tree.insert(word);
        }
    
        // Print tree values in different orders
        System.out.println("\nPreorder traversal:");
        tree.preorder(tree.root);
    
        System.out.println("\nInorder traversal:");
        tree.inorder(tree.root);
    
        System.out.println("\nPostorder traversal:");
        tree.postorder(tree.root);
    
        scanner.close();
    }
    
}
