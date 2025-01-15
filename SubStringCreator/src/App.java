/*CS 500 - HW 3.2 - Substring Creator
 *
 * This program reads a random string with maximum length of 10 and a integer which will me used to calulate the length of the substring.
 * Prints the lexicographically largest string among the created substrings. Strings are stored in Double-ended Queue.
 * 
 * Marissa Norris
 */

import java.util.Scanner;
import java.util.Deque;
import java.util.LinkedList;

public class App {
    private static void SubstringCreator(String S, Integer N){
        Deque<Character> deque = new LinkedList<>();

        // Evaluate each character in the string
        for (int i = 0; i < S.length(); i++) {
            char currentChar = S.charAt(i);
            
             // Runs loop while deque is not empty, the last character in deque is less than current character
             // and N > 0
             while (!deque.isEmpty() && deque.peekLast() < currentChar && N > 0) {
                // Remove the last character from deque, reduce the value of n by 1
                deque.removeLast();
                N--;
            }

            // Add the character to the rear position of deque
            deque.addLast(currentChar);
         }

        //Remove the last n characters from deque if N > 0
        while (N > 0 && !deque.isEmpty()) {
            deque.removeLast();
            N--;
        }

        // Remove all elements from the front of deque and add them to the output string 
        StringBuilder outputString = new StringBuilder();
        while (!deque.isEmpty()) {
            outputString.append(deque.removeFirst());
        }

        System.out.println(" The lexicographically largest substring is " + outputString.toString());

    }
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter a string: ");
        String S = scanner.nextLine();
        if (S.length()> 10){
            System.out.println("Sorry, string can not be longer than 10 characters");
            System.out.print("\nEnter a string: ");
            S = scanner.nextLine();
        }


        System.out.println("Enter the value of n: ");
        int n = scanner.nextInt();
        if (n >= S.length()){
            System.out.println("Sorry, the value of n needs to be less than the size of the string" );
            System.out.println("\nEnter the value of n: ");
            n = scanner.nextInt();
        }
        SubstringCreator(S, n);
       
        scanner.close();
    }
}
