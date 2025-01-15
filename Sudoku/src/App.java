/*CS 500
Question 1 - Create a 9X9 Sudoku matrix where amount of empty spaces per row are given by the user and 
represented by zeros. There are no duplicate random integers (1-9) in the rows and columns.

Marissa Norris
*/
import java.util.Scanner;
import java.util.Random;

public class App {
    //Boolean method to test whether or not the current value is already in the row or col
    public static boolean isValid(int[][] sudokuGrid, int row, int col, int temp) {
        // Check if current value is in the row or column
        for (int i = 0; i < 9; i++) {
            if (sudokuGrid[row][i] == temp || sudokuGrid[i][col] == temp) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int [][] sudokuGrid = new int[9][9];

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        //Initalize matrix with -1
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                sudokuGrid [row][col] = -1;
            }
           
        }

        System.out.println("Enter the empty cell number from 4 to 6 for each row");
       
        //Loop till input all the zero values 
        for (int row = 0; row < 9; row++) {
            System.out.println("Enter the number of empty cells for row " + (row +1) + ":");
            int userNum = scanner.nextInt();
            for(int i = 0; i < userNum; i++)
            {
                int col = random.nextInt(8);
                sudokuGrid [row][col] = 0;
            }
        }
     
        //Generate values for first row
        for (int col = 0; col < 9; col++) {
            if (sudokuGrid[0][col] != 0) {
                sudokuGrid[0][col] = random.nextInt(9) + 1;
            }
        }
  
        //Generate values for first collumn
         for (int row = 0; row < 9; row++) {
            if (sudokuGrid[row][0] != 0) {
                sudokuGrid[row][0] = random.nextInt(9) + 1;
            }
        }
        
        //Loop through the matrix to input the remainding random values while comparing them 
        //to the first row and column to avoid duplicates
        for (int row = 1; row < 9; row++) {
            for (int col = 1; col < 9; col++) {
                // Generates random integers from 0 to 9 if there is not a 0 value in the location
                if (sudokuGrid [row][col] != 0){
                    int temp = random.nextInt(9) + 1;
                    // Check if there are duplicates 
                    while (isValid(sudokuGrid, row, col, temp) == false){
                        temp = random.nextInt(9) + 1;
                     }
                    sudokuGrid [row][col] = temp;
                   
                }
            }
        }
        
        //Print matrix
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                System.out.print(sudokuGrid [row][col] + " ");
            }
            System.out.println();
        }
        scanner.close();  
    }
   
  }