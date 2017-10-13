/* Name: Nathan Martin and Shashwat Patra
 * Professor: Malasri
 * Date: 10/04/2017
 * Project: Othello
 */

public class Tile {
    protected String state = " "; //instance variable to hold the state of a tile
    public void flip() { //flip method
        if(state.equals("b")) //checks to see if state is "b" and switches it with "w"
            state = "w";
        else if(state.equals("w")) //checks to see if state is "w" and switches it with "b"
        state = "b";
    }
    public String toString() { //this toString simply returns state, it basically does the exact same thing as a getter method which is why I did not add one into this code
        return state; 
    }
    public void setState(String s) { //allows the state to be set during the rest of the program
        state = s;
    }
}