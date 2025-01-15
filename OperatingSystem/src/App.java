/*CS 500 - HW 3.1 - Operating System
 *
 * This program is a process management module of an Operating System. It recieves process and priority (1-9) from the user. Class object 
 * contains the process name and priority value. Each process is saved in a built-in Min Priority Queue that use Min Heap data structure
 * to order the priority by smallest to largest. Provides options to insert, process and display.
 * 
 * Marissa Norris
 */

import java.util.PriorityQueue;
import java.util.Scanner;

//Instances of the Process class can be compared to each other, allowing them to be sorted based on their natural order.
class Process implements Comparable<Process> {
    private String name;
    private int priority;

    public Process(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }
    //Method compares the priority of each process
    @Override
    public int compareTo(Process other) {
        return Integer.compare(this.priority, other.priority);
    }
}


public class App {

    private static void insertProcess(Scanner scanner, PriorityQueue<Process> processQueue) {
        System.out.print("Enter process name: ");
        String name = scanner.next();
        System.out.print("Enter process priority (1-9): ");
        int priority = scanner.nextInt();

        if (priority < 1|| priority > 9) {
            System.out.println("Priority must be between 1 and 9.");
            return;
        }
        //Inserts process into the queue and uses min heap data structure to organize the queue
        processQueue.offer(new Process(name, priority));
        System.out.println("Successfully!");
    }

    private static void assignProcess(PriorityQueue<Process> processQueue) {
        if (processQueue.isEmpty()) {
            System.out.println("Sorry, no process in the queue to assign.");
            
        } else {
            //Returns the element at the head of the queue and prints the process name
            Process process = processQueue.poll();
            System.out.println("Process " + process.getName() + " is assigned to the processor");
        }
    }

    
    private static void displayProcesses(PriorityQueue<Process> processQueue) {
        if (processQueue.isEmpty()) {
            System.out.println("Sorry, queue is empty.");
           
        } else {
            //Loops through the queue
            for (Process process : processQueue) {
                System.out.println("Process Name: " + process.getName() + " Priority: " + process.getPriority());
            }
        }
    }

    public static void main(String[] args) throws Exception {
      
        Scanner scanner = new Scanner(System.in);
        PriorityQueue<Process> processQueue = new PriorityQueue<>();

        System.out.println("1. Insert a process into the process queue");
        System.out.println("2. Assign a process to the processor");
        System.out.println("3. Show the process queue");
        System.out.println("4. Exit");

        //Infinite loop that terminate when the user select 'Exit'
        while(true){
            System.out.println("\n Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    insertProcess(scanner, processQueue);
                    break;
                case 2:
                    assignProcess(processQueue);
                    break;
                case 3:
                    displayProcesses(processQueue);
                    break;
                case 4:
                    System.out.println("Thank you!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }

        }
    }
    
}
