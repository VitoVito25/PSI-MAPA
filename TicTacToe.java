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
                System.out.print(" " + this.board[i][j] + " ");

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
                this.board[i][j] = resetIcon;
            } 
        }
    }

    public void setPositionsBoard(){

        int positionNumber = 1;
        char positionIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                positionIcon = (char) (positionNumber + '0'); 
                this.board[i][j] = positionIcon;
                positionNumber++;
            } 
        }
    }

    public void printFirstMenu() {

        System.err.println("#-- BEM VINDO AO JOGO DA VELHA EM JAVA --# \n");
        this.printBoard();
        System.err.println("\n \nAcima você pode observar as possiveis possições de inclusão dos marcadores ");
        System.err.println("Pressione ENTER para iniciar o jogo!");

    }

    public void changeMarker() {
        if(this.marker == 'X') {
            this.marker = 'O';
        } else {
            this.marker = 'X';
        }
    }

    public void showAvaiblePositions() {

        int positionNumber = 1;
        char positionIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] == '-') {
                    positionIcon = (char) (positionNumber + '0'); 
                    this.board[i][j] = positionIcon;
                }
                positionNumber++;
            } 
        }
    }

    public void setRuningBoard() {

        char positionIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] != 'X' || this.board[i][j] != 'O') {
                    this.board[i][j] = positionIcon;
                }
            } 
        }
    }

    public boolean checkPosition(int position) {

        boolean positionEmpty;

        this.showAvaiblePositions(); // Seta as posições apenas para os locais onde nao tem marcadores

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] == position) {
                    this.setRuningBoard();
                    positionEmpty = true;
                    return positionEmpty;
                }
            } 
        }

        this.setRuningBoard();
        positionEmpty = false;
        return positionEmpty;
    }

    public boolean setMarker(int position) {

        boolean successInsert;
        boolean avaiblePosition = this.checkPosition(position);

        if(avaiblePosition == true) {
            this.showAvaiblePositions(); // Seta as posições apenas para os locais onde nao tem marcadores

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(this.board[i][j] == position) {
                        this.board[i][j] = this.marker;
                        successInsert = true;
                        return successInsert;
                    }
                } 
            }

        }

        System.err.println("Você inseriu uma posição que ja esta ocupada, insira uma nova posição!");
        successInsert = false;
        return successInsert;

    }


    public void startTurn(Scanner scanner){

        boolean turnRuning = true;

        do{
            try{
                this.printBoard();
                System.err.println("\n \nInsira a posição em que você deseja colocar o marcador: " + this.marker);

                int position = scanner.nextInt();

                boolean successInsert = this.setMarker(position);

                if(successInsert == true) {
                    turnRuning = false;
                } else {
                    scanner.nextLine();
                }

            }catch (InputMismatchException e) {
                System.err.println("Você inseriu um caracter invalido, insira um numero para validar a posição!");
                scanner.nextLine();
            }
        }while(turnRuning == false);
    }

}