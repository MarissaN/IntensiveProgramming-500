/*CS 500 - HW 2.1 - Circular Linked List
 * Items are sold to clients stored in a Circular Linked List. User provides the number and name of clients, number of items sold at a time
 * and the wait time. Programs loops through Linked List till all items for all clients are sold
 * 
 * Marissa Norris
 */
import java.util.Scanner;

public class App {
    static class Client {
        String name;
        int requirement;
        int remainingItems;
        int waitingTime;

        public Client(String name, int requirement, int waitingTime) {
            this.name = name;
            this.requirement = requirement;
            this.remainingItems = requirement;
            this.waitingTime = waitingTime;
        }
    }

    static class SCircularLinkedList<E> {
        private class Node {
            Client client;
            private Node next;

            public Node(Client client, Node n) {
                this.client = client;
                next = n;
            }

            public Client getClient() {
                return client;
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

        public SCircularLinkedList() {
        }

        public int size() {
            return size;
        }

        public void add(Client client) {
            Node n = new Node(client, null);
            if (head == null) {
                head = n;
                tail = head;
                tail.setNext(head);
            } else {
                tail.setNext(n);
                tail = n;
                tail.setNext(head);
            }
            size++;
        }
        //Move the node at position head to position tail
        public void moveHead2Tail() {
            if (head != null && head != tail) {
                Node temp = head;
                head = head.getNext();
                tail.setNext(temp);
                temp.setNext(head);
                tail = temp;
            }
        }

        public void delete() {
            size--;
            if (size == 0)
                tail = head = null;
            else {
                Node temp = head;
                while (temp.getNext() != tail)
                    temp = temp.getNext();
                tail = temp;
                tail.setNext(head);
            }
        }
        
        public void sellItem(int n, int w) {
            Node current = tail;
            //Checks if client has items remaining
            if (current.client.remainingItems == 0) {
                System.out.println("\nThe client " + current.client.name + " has received all of its required items, and its total waiting time is " + current.client.waitingTime + " days.");
                delete();
            } else {
                //Calculate how many items remain
                int itemsSold = Math.min(n, current.client.remainingItems);
                current.client.remainingItems -= itemsSold;
                current.client.waitingTime += w;
                if (current.client.remainingItems == 0) {
                    System.out.println("\nThe client " + current.client.name + " has received all of its required items, and its total waiting time is " + current.client.waitingTime + " days.");
                    delete();
                }
                moveHead2Tail();
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        App app = new App();
        SCircularLinkedList<Client> list = new SCircularLinkedList<>();

        System.out.print("Enter the number of items to be sold to each client at a time: ");
        int itemNum = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the waiting time in the list: ");
        int waitingTime = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter number of clients: ");
        int clientNum = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= clientNum; i++) {
            System.out.print("\nEnter the name of the client " + i + ": ");
            String name = scanner.nextLine();

            System.out.print("Enter total requirement: ");
            int requirement = scanner.nextInt();
            scanner.nextLine();

            list.add(new Client(name, requirement, 0));
        }
        //Runs till the list is empty
        while (list.size() != 0) {
            list.sellItem(itemNum, waitingTime);
        }

        scanner.close();
    }
}

