/*CS 500
Question 2 - Create a linked list. User provides the number of nodes, the elements to insert and their positions, as well as the elements
to be deleted

Marissa Norris*/

import java.util.Scanner;

public class App {
    //Linked list class
    public class SinglyLinkedList<E> {
        private class Node {
            private int element;
            private Node next;

            public Node(int e, Node n) {
                element = e;
                next = n;
            }

            public int getElement() {
                return element;
            }

            public Node getNext() {
                return next;
            }

            public void setNext(Node n) {
                next = n;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int size = 0;

        public SinglyLinkedList() {}

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }
        //Add element at begining of the list
        public void addFirst(int e) {
            head = new Node(e, head);
            if (size == 0)
                tail = head;
            size++;
        }
        //Add element at the end of the list
        public void addLast(int e) {
            Node newest = new Node(e, null);
            if (size == 0)
                head = newest;
            else
                tail.setNext(newest);
            tail = newest;
            size++;
        }
        //Insert element at given location
        public void insert(int e, int pos) {
            if (pos < 1 || pos > (size + 1)) {
                System.out.println("Wrong position!");
                return;
            }
            if (pos == 1)
                addFirst(e);
            else if (pos == (size + 1))
                addLast(e);
            else {
                Node temp = head;
                for (int i = 1; i < pos - 1; i++)
                    temp = temp.getNext();
                Node newest = new Node(e, temp.getNext());
                temp.setNext(newest);
                size++;
            }
           
        }
        //Delete the first element
        public int removeFirst() {
            if (isEmpty())
                return -999;
            int data = head.getElement();
            head = head.getNext();
            size--;
            if (size == 0)
                tail = null;
            return data;
        }
        //Delete the last element
        public int removeLast() {
            if (isEmpty())
                return -999;
            int data = tail.getElement();
            size--;
            if (size == 0)
                tail = head = null;
            else {
                Node temp = head;
                while (temp.getNext() != tail)
                    temp = temp.getNext();
                tail = temp;
                tail.setNext(null);
            }
            return data;
        }
        //Delete element at given location
        public int delete(int pos) {
            if (isEmpty())
                return -999;
            if (pos < 1 || pos > size) {
                System.out.println("Deletion not possible!");
                return -999;
            }
            if (pos == 1)
                return removeFirst();
            else if (pos == size)
                return removeLast();
            else {
                Node temp = head;
                for (int i = 1; i < pos - 1; i++)
                    temp = temp.getNext();
                int data = temp.getNext().getElement();
                //Set next location to the node after the deleted node
                temp.setNext(temp.getNext().getNext());
                size--;
                return data;
            }
        }
        //Search for element in list
        public int search(int n){
            Node temp = head;
            int pos = 1;
            while (temp != null){
                if (temp.getElement() == n){
                    return pos;
                }
                temp = temp.getNext();
                pos++;
            }
           
            return -999;

        }
        //Display elements in the list
        public void display()
        {
        System.out.print("The elements in the linked list: ");
        Node temp = head;
        for(int i = 0; i < size; i++)
        {
        System.out.print(temp.getElement() + " ");
        temp = temp.getNext();
        }
        System.out.println(); //Prints a blank line
        }


    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        App app = new App();
        SinglyLinkedList<Integer> list = app.new SinglyLinkedList<>();

        // Ask the user for the number of nodes to be inserted
        System.out.print("Enter the number of nodes: ");
        int n = scanner.nextInt();
        //Ask for first element and enter it in the first location
        System.out.print("Enter an element:");
        int Fdata = scanner.nextInt();
        list.addFirst(Fdata);

        //Ask the user for the rest of the elements and their location
        for (int i = 1; i < n; i++) {
            System.out.print("Enter an element:");
            int data = scanner.nextInt();
            System.out.print("Enter the position:");
            int num = scanner.nextInt();
            if(num > n + 1)
            System.out.println("Wrong position!");
            else
            list.insert(data, num);
        }
        list.display();
        //Ask user for element to delete
        System.out.print("Enter number to delete:");
        int dataDel = scanner.nextInt();
        //Loops till element is found
        while(list.search(dataDel) == -999){
            System.out.println("Number is not found!");
            System.out.print("Enter number to delete:");
            dataDel = scanner.nextInt();
        }
        //Loops till all very occurrence of the element is deleted
        while(list.search(dataDel) != -999){
             list.delete((list.search(dataDel)));
        }
        list.display();

        scanner.close();
    }
}