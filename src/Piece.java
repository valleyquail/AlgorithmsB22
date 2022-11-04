public class Piece {
//Number of pieces of this color
    private int numLeft;
//Constructor
    public Piece(int defaultNum){
        numLeft = defaultNum;
    }
//Returns the number of pieces of this color left in play
    public int getNumLeft(){
        return numLeft;
    }
    //Removes an arbitrary number of pieces from play but never more than the number of pieces left
    public boolean remove(int numToRemove){
        if(numToRemove <= numLeft){
            numLeft -= numToRemove;
            return true;
        }
        else{
            System.out.println(("You're asking to remove too many pieces. Here is the current board:\n"));
            return false;
        }
    }
    //Checks if there are any pieces left
    public boolean noneLeft(){
        return numLeft == 0;
    }
}
