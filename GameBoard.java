/* Name: Nathan Martin
 * Professor: Malasri
 * Date: 10/04/2017
 * Project: Othello
 */
public class GameBoard {
    //instance variables for rows/columns/and a 2d array of tile objects
    private int rows;
    private int columns;
    Tile[][] board;

    public int countPieces(String s) { //this method accepts a String of either "w" or "b" and returns an integer of how many of said String there is.
        int counter = 0; //counter to keep count of the number of pieces
        for(int i = 0; i < rows; i++) { //nested for loop to go through every possible element in the 2d tile array
            for(int j = 0; j < columns; j++) {
                if(board[i][j].toString().equals(s)) { //checks to see if an element matches the string that was passed to this method
                    counter++; //if it matches, increments counter by 1
                }
            }
        }
        return counter; //returns the variable counter
    }

    public GameBoard(int r, int c) { //gameboard constructor
        rows = r; //sets rows equal to r, which is whatever the user inputs. (this code is located in the Othello class)
        columns = c; //sets columns equal to c, which is what the user decides (also in the Othello class)

        board = new Tile[rows][columns]; //creates a new Tile object named board with the size of rows and columns
        for(int i = 0; i < r; i++) { //nested for loop that visits each element within board and makes sure it is assigned as a new tile
            for(int j = 0; j < c; j++) {
                board[i][j] = new Tile();
            }
        }
        //all of these are used to place the original 4 pieces in the center of the board regardless of size.
        board[(board.length / 2)][board.length / 2 -1].setState("b");
        board[(board.length / 2)][board.length / 2].setState("w");
        board[(board.length / 2) - 1][board.length / 2 -1].setState("w");
        board[(board.length / 2 - 1)][board.length / 2].setState("b");

    }

    public String toString() {
        String s = ""; 
        for(int i = 0; i < rows; i++) { //goes through all the rows and basically resets the values of s1 and s2 everytime to create the multiple lines
            String s1 = "";
            String s2 = "";

            for(int j = 0; j < columns; j++) { //goes through each column and assigns the specific tile to the variable stat using tiles toString method
                String stat = board[i][j].toString();
                s1 += "+---";
                s2 += "| "+ stat + " ";

            }
            s += s1 + "+" + "\n" + s2 + "|" + "\n"; //connects all the strings together to complete the table

        }
        for(int i = 0; i < rows; i++) {
            s += "+---"; //this adds in the very bottom row as the above for loop does not finish it
        }
        s += "+"; //this adds a + to the very bottom right corner
        return s; //returns the entire table
    }

    public boolean placeTile(int x, int y, String s) {
        boolean[] test = {true, true, true, true, true, true, true, true}; //this boolean array is for the 8 directions so that they are all true
        int[] array = {0, -1, 1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0, -1, -1}; //used this array by looking at it as if ordered pairs for possible directions
        int counter = 0; //counter variable
        int max = rows; //sets max = to the number of rows
        try{
            if(!(board[y][x].toString().equals(" "))) //this checks to make sure that if there is something on a tile, to return false so that current pieces cannot be overidden.
                return false;
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter something that is on the board");
        }
        if(columns > max) 
            max = columns;
        for(int i = 1; i < max; i++) {
            for(int j = 0; j < 8; j++) {
                if(test[j]) {
                    int newx = x + i * array[j*2]; //looks at all possible even numbers in the array
                    int newy = y + i * array[j*2+1]; //looks at all possible odd number in the array
                    while(newx < 0 || newx >= columns || newy < 0 || newy >= rows || !(test[j])) { //conditionals to check and make sure at least one is true
                        test[j] = false;
                        j++; //increments j to go to the next direction
                        if(j >= 8) //if j is larger than 8, exit the while loop
                            break;
                        newx = x + i * array[j*2]; //used to identify where the next piece is being placed
                        newy = y + i * array[j*2+1]; //""

                    }
                    if(j >= 8) //if j is larger than 8 break the if statement
                        break;
                    if(board[newy][newx].toString().equals(" "))
                        test[j] = false;
                    else if(board[newy][newx].toString().equals(s)) {
                        for(int k = 1; k < i; k++) {
                            board[y + k * array[j*2 + 1]][x + k * array[j*2]].flip(); //flips the pieces if everything is true
                            counter++; //increments counter
                        }
                        test[j] = false;
                    }
                }
            }
        }
        if(counter > 0) {
            board[y][x].setState(s); //sets the state to the new tile as long as counter > 0, which will always happen if a valid move is placed
            return true;
        }
        return false; 
    }

    public boolean isValid(String s) {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(check(j, i, s)) //looks through the entire 2d array and calls my check function to my index j and index i, as wella s the String s
                    return true;
            }
        }
        return false;
    }

    //my check function is basically the exact same as my placeTile function, however I removed some code as it does not need to set state, or flip any items, as it just
    //needs to check if there is a valid move or not.
    public boolean check(int x, int y, String s) { 
        boolean[] test = {true, true, true, true, true, true, true, true};
        int[] array = {0, -1, 1, -1, 1, 0, 1, 1, 0, 1, -1, 1, -1, 0, -1, -1};
        int counter = 0;
        int max = rows;
        if(!(board[y][x].toString().equals(" ")))
            return false;
        if(columns > max) 
            max = columns;
        for(int i = 1; i < max; i++) {
            for(int j = 0; j < 8; j++) {
                if(test[j]) {
                    int newx = x + i * array[j*2];
                    int newy = y + i * array[j*2+1];
                    while(newx < 0 || newx >= columns || newy < 0 || newy >= rows || !(test[j])) {
                        test[j] = false;
                        j++;
                        if(j >= 8)
                            break;
                        newx = x + i * array[j*2];
                        newy = y + i * array[j*2+1];
                    }
                    if(j >= 8) 
                        break;
                    if(board[newy][newx].toString().equals(" "))
                        test[j] = false;
                    else if(board[newy][newx].toString().equals(s)) {
                        for(int k = 1; k < i; k++) {
                            counter++;
                        }
                        test[j] = false;
                    }
                }
            }
        }
        if(counter > 0) {
            return true;
        }
        return false;
    }

    public String currentState() { //method to determine the current state of the game
        int white = countPieces("w"); //variables to hold the method call of countPieces to make the code shorter
        int black = countPieces("b"); //""
        String s = ""; //String variable to change the state of the game as this string is what is going to be returned
        if(isValid("w") || isValid("b")) { //checks to make sure either white or black have a valid move, if so the game is still being played
            s = "The game is currently being played!";
        }
        else if(white > black) { //after making sure there are no more valid moves for either side, checks if white has more pieces that black
            s = "White wins!";
        }
        else if(white < black) { //after making sure there are no more valid moves for either side, checks if black has more pieces than white
            s = "Black wins!";
        }
        else //if everything ends up being false, there is a tie
            s ="There is a tie!";
        return s; //returns the value of s
    }
}