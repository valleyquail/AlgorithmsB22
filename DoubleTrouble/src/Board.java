public class Board {
//Instantiates the pieces and quantities
    public Piece green = new Piece(3);
    public Piece red = new Piece(5);
    public Piece yellow = new Piece(7);

    //Default Constructor
    public Board(){
            }
    //Number of game pieces left in play
    private int gamePiecesLeft = 15;
//Returns the number of game pieces left in play
    public int getGamePiecesLeft() {
        return gamePiecesLeft;
    }
//Updates how many pieces are left in the game
    public void updateBoard(){
        gamePiecesLeft = green.getNumLeft() + red.getNumLeft() + yellow.getNumLeft();
    }
//Prints the board in an easy-to-read format. With more effort, the game would have been printed in a pyramid format
    public void printBoard() {

        System.out.println("Green:\t" + green.getNumLeft());
        System.out.println("Red:\t" + red.getNumLeft());
        System.out.println(("Yellow:\t" + yellow.getNumLeft()) + "\n");
    }
}
