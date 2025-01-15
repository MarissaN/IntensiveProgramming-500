/*CS 500 - HW 2.2 - Stack Data Structure
 * Text editor that allows user to delete, change, undo and redo operations of a word in the sentence that they provided.
 * project uses 1 Linked List to hold the sentence and 2 Stacks to store the undo and redo prompts
 * 
 * Marissa Norris
 */
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class App {

    static class Imports {
        private LinkedList<String> sentenceL;
        private Stack<Operation> undoS;
        private Stack<Operation> redoS;
        private Scanner scanner;

        public Imports() {
            sentenceL = new LinkedList<>();
            undoS = new Stack<>();
            redoS = new Stack<>();
            scanner = new Scanner(System.in);
        }
        //Enters the sentence into linked list one word at a time
        public void insertW(String sentence) {
            String[] words = sentence.split("\\s+");
            for (String w : words) {
                sentenceL.addLast(w);
            }
        }
        //Displays contents of linked list as sentence
        public void printSentence() {
            for (String word : sentenceL) {
                System.out.print(word + " ");
            }
            System.out.println();
        }
        //Receives the word to be changed and modifies it
        public void modify(String wordC) {
            //Loop through the list to find the word
            for (int i = 0; i < sentenceL.size(); i++) {
                String word = sentenceL.get(i);
                if (word.equals(wordC)) {
                    System.out.print("Enter the new word: ");
                    String newWord = scanner.nextLine();
                    Operation operation = new Operation(word, i, 'm');
                    //Push the modify operation into undo stack
                    undoS.push(operation);
                    //Save the change to the list
                    sentenceL.set(i, newWord);
                    System.out.println("\nWord is changed. The modified sentence is: ");
                    printSentence();
                    return;
                }
            }
            System.out.println("\nWord not found.");
        }
        //Receives the word to be deleted and performs the action
        public void delete(String wordD) {
            for (int i = 0; i < sentenceL.size(); i++) {
                String word = sentenceL.get(i);
                if (word.equals(wordD)) {
                    Operation operation = new Operation(word, i, 'd');
                    undoS.push(operation);
                    sentenceL.remove(i);
                    System.out.println("\nWord is removed.The modified sentence is: ");
                    printSentence();
                    return;
                }
            }
            System.out.println("\nWord not found.");     
        }
        //Method for undo operation
        public void undo() {
            if (!undoS.isEmpty()) {
                //Get the last operation done
                Operation operation = undoS.pop();
                redoS.push(operation);
                //If to change word
                if (operation.getType() == 'm') {
                    sentenceL.set(operation.getIndex(), operation.getword());
                //Else delete word    
                } else if (operation.getType() == 'd') {
                    sentenceL.add(operation.getIndex(), operation.getword());
                }
                System.out.println("\nUndo operation is done. The sentence:");
                printSentence();
            } else {
                System.out.println("\nSorry, can't undo any previous operation(s).");
            }
        }
        //Method for redo operation
        public void redo() {
            if (!redoS.isEmpty()) {
                Operation operation = redoS.pop();
                undoS.push(operation);
                if (operation.getType() == 'm') {
                    sentenceL.set(operation.getIndex(), operation.getword());
                } else if (operation.getType() == 'd') {
                    sentenceL.remove(operation.getIndex());
                }
                System.out.println("\nRedo operation is done. The sentence:");
                printSentence();
            } else {
                System.out.println("\nSorry, can't redo any previous operation(s).");
            }
        }
    }
    //Class holds the values needed to identify the operation done
    static class Operation {
        private String word;
        private int index;
        private char type;

        public Operation(String word, int index, char type) {
            this.word = word;
            this.index = index;
            this.type = type;
        }

        public String getword() {
            return word;
        }

        public int getIndex() {
            return index;
        }
     
        public char getType() {
            return type;
        }
    }
    
    public static void main(String[] args) throws Exception {
        App app = new App();
        Imports imports = new Imports();
        
        System.out.print("Enter the sentence: ");
        String sentence = imports.scanner.nextLine();
        imports.insertW(sentence);

        //Menu
        System.out.println("\nPress 1 to delete a word");
        System.out.println("Press 2 to change a word");
        System.out.println("Press 3 to undo an operation");
        System.out.println("Press 4 to redo an operation");
        System.out.println("Press 5 to exit");

        int option = 0;
        while (option != 5) { 
            System.out.print("\nEnter your choice: ");
            option = imports.scanner.nextInt();
            imports.scanner.nextLine(); 
            switch (option) {
                case 1:
                    System.out.print("Enter the word to delete: ");
                    String wordToDelete = imports.scanner.nextLine();
                    imports.delete(wordToDelete);
                    break;
                case 2:
                    System.out.print("Enter the word to modify: ");
                    String wordToChange = imports.scanner.nextLine();
                    imports.modify(wordToChange);
                    break;
                case 3:
                    imports.undo();
                    break;
                case 4:
                    imports.redo();
                    break;
                case 5:
                    System.out.println("Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } 
    } 
}