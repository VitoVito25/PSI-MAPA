import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToe {

    private char[][] board;
    private char marker;

    public TicTacToe() {
        board = new char[3][3];
        marker = 'X';
    }

    public void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(" " + board[i][j] + " ");

                if(j < 2) {
                    System.out.print("|");
                }
            }
            if(i < 2) {
                System.out.println();
                System.out.println("-----------"); // Pula uma linha
            } 
        }
    }

    public void resetBoard(){

        char resetIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = resetIcon;
            } 
        }
    }

    public void setPositionsBoard(){

        int positionNumber = 1;
        char positionIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                positionIcon = (char) (positionNumber + '0'); 
                board[i][j] = positionIcon;
                positionNumber++;
            } 
        }
    }

    public void printFirstMenu() {

        System.err.println("#-- BEM VINDO AO JOGO DA VELHA EM JAVA --# \n");
        this.printBoard();
        System.err.println("\n \nAcima você pode observar as possiveis possições de inclusão dos marcadores ");

    }

    public void changeMarker() {
        if(this.marker == 'X') {
            this.marker = 'O';
        } else {
            this.marker = 'X';
        }
    }


    
    public void startTurn(Scanner scanner){

        boolean turnRuning = true;

        do{
            try{
                this.printBoard();
                System.err.println("\n \nInsira a posição em que você deseja colocar o marcador: " + this.marker);

                int position = scanner.nextInt();

            }catch (InputMismatchException e) {
                System.err.println("Você inseriu um caracter invalido, insira um numero para validar a posição!");
                scanner.nextLine();
            }
        }while(turnRuning == false);
    }

}