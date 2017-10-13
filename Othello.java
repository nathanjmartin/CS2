/* Name: Nathan Martin
 * Professor: Malasri
 * Date: 10/04/2017
 * Project: Othello
 */
import java.util.Scanner;

public class Othello {
    private static GameBoard Othello; //instance variable to hold the gameboard object
    static Scanner input; //instance variable for the scanner object
    public static void main(String[] args) {
        input = new Scanner(System.in); //assigning the scanner a reference variable

        System.out.println("Enter the dimensions of your Othello game! "); //prompting the user for the size of the board they would like
        int r = input.nextInt(); //variable to hold the rows the user enters
        int c = input.nextInt(); //variable to hold the columns the user enters
        input.nextLine(); //moves down to the next line

        Othello = new GameBoard(r, c); //passes r and c to the GameBoard constructor to create the board

        boolean go = true; //boolean value for my while loop
        String turn = "b"; //String value to hold whose turn it is
        
        while(go) {
            String str = "Black"; //Variable used to print out to the user whose turn it is
            if(turn.equals("w"))
                str = "White";
            System.out.println(Othello); //prints out the table after every move is made
            System.out.println(str + ",enter two integers in the form of an ordered pair. Ex: 1,2"); //prompts the user to enter where to place the tile
            System.out.println("You may type 'exit' to end the game."); //tells the user what to type if they want to end the game early
            String s = input.nextLine(); //allows the user to type in a String for their input

            if(s.toLowerCase().equals("exit")) { //converts strings to lowercase so that they can type exit any way they possibly need to
                return;
            }

            String[] sp = s.split(","); //splits the string around commas

            if(!(sp.length == 2)) {
                System.out.println("You need to enter exactly two integers"); //checks to make sure the length of the split string is not more than a length of 2
                continue; 
            }
            int num1 = 0; //variable to hold the first number in the String
            int num2 = 0; //variable to hold the second number in the String
            try { //try and catch to catch an incorrect input type
                num1 = Integer.parseInt(sp[0].trim()); //trims the String array sp and converts that element into an integer
                num2 = Integer.parseInt(sp[1].trim()); //^^
            }
            catch (NumberFormatException e) { //catches a NumberFormatException and informs the user to enter two integers and nothing else
                System.out.println("Please enter two integers, nothing else.");
                continue;
            }
            if(!(Othello.placeTile(num1, num2, turn))) { //this checks to make sure where the user is placing the tile is a valid move or not, if not the print statement is executed
                System.out.println("That is not a valid move.");
                continue;
            }
            if(Othello.currentState().equals("The game is currently being played!")) { //checks to see if the game is currently in progress, if true the turns alternate between black and white
                if(turn.equals("b") && Othello.isValid("w")) ///makes sure that it is blacks turn and that white has a valid move, if white does not have a valid move, the turn is black's again
                    turn = "w";
                else if(turn.equals("w") && Othello.isValid("b")) //makes sure that it is whites turn and that black has a valid mvoe, if black does not have a valid move, the turn is white's again
                    turn = "b";
            }
            else {
                
                go = false;
            }
        }
        System.out.println(Othello); //prints the board one more time
        System.out.println(Othello.currentState() + "\nWhite: " + Othello.countPieces("w") + "\nBlack: " + Othello.countPieces("b")); //prints out the winner of the game along with the scores

    }
}