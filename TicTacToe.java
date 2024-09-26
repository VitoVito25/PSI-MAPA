public class TicTacToe {

    private char[][] board;

    public TicTacToe() {
        board = new char[3][3];
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");

                if(j == 0 || j == 1 ) {
                    System.out.print("|");
                }
            }
            if(i == 0 || i == 1 ) {
                System.out.println();
                System.out.println("---------"); // Pula uma linha
            } 
        }
    }

}