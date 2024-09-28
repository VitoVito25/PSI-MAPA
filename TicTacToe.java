import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe do jogo da velha
 * 
 * @author Victor Renaud
 * @version 1.0
 */
public class TicTacToe {

    public char[][] board;
    public char marker;

    public TicTacToe() {
        board = new char[3][3];
        marker = 'X';
    }

         /**
         * Função para imprimir o tabuleiro atual na tela
         * 
         * @author Victor Renaud
         * @version 1.0
         */
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

    /**
     * Função para reiniciar todas as posições do tabuleiro
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public void resetBoard(){

        char resetIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.board[i][j] = resetIcon;
            } 
        }
    }

    /**
     * Função para verificar quais as entradas devem ser colocadas para inserir um marcador em determinada posição
     * 
     * @author Victor Renaud
     * @version 1.0
     */
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

    /**
     * Função para imprimir o primeiro menu do jogo
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public void printFirstMenu() {

        System.err.println("#-- BEM VINDO AO JOGO DA VELHA EM JAVA --# \n");
        this.printBoard();
        System.err.println("\n \nAcima você pode observar as possiveis posições de inclusão dos marcadores ");
        System.err.println("Pressione ENTER para iniciar o jogo!");

    }

    /**
     * Função para trocar o marcador
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public void changeMarker() {

        if(this.marker == 'X') {
            this.marker = 'O';
        } else {
            this.marker = 'X';
        }
    }

    /**
     * Função para trocas as posições vazias por numeros
     * 
     * @author Victor Renaud
     * @version 1.0
     */
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

    /**
     * Função para voltar as posições vazias de numeros para "-"
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public void setRuningBoard() {

        char positionIcon = '-';

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] != 'X' && this.board[i][j] != 'O') {
                    this.board[i][j] = positionIcon;
                }
            } 
        }
    }

    /**
     * Função para verificar se a posição selecionada pelo usuario é valida
     * 
     * @return Retorna true se a posição esta livre ou false se nao
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public boolean checkPosition(char position) {

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

    /**
     * Função para colocar o marcador na posição selecionada
     * 
     * @param position A posição onde o usuario deseja inserir o marcador
     * @return Retorna true se o marcador foi inserido com sucesso ou false caso nao
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public boolean setMarker(char position) {

        boolean successInsert = false;
        boolean avaiblePosition = this.checkPosition(position);

        if(avaiblePosition == true) {
            this.showAvaiblePositions(); // Seta as posições apenas para os locais onde nao tem marcadores

            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if(this.board[i][j] == position) {
                        this.board[i][j] = this.marker;
                        successInsert = true;
                    }
                } 
            }

        } else {
            Main.clearConsole();
            System.err.println("Você inseriu uma posição que ja esta ocupada, insira uma nova posição!");
            successInsert = false;
        }

        this.setRuningBoard();
        return successInsert;
        
    }

    /**
     * Função para iniciar um novo turno no jogo
     * 
     * @param scanner Scanner para leitura de entrada de dados
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public void startTurn(Scanner scanner){

        boolean turnRunning = true;

        do{
            try{
                this.printBoard();
                System.err.println("\n \nInsira a posição em que você deseja colocar o marcador: " + this.marker);

                int positionInt = scanner.nextInt();
                char position = (char) ('0' + positionInt); // Converte o inteiro para char

                boolean successInsert = this.setMarker(position);

                if(successInsert == true) {
                    turnRunning = false;
                    this.changeMarker(); // Muda o marcardor
                } else {
                    scanner.nextLine();
                }

            }catch (InputMismatchException e) {
                Main.clearConsole();
                System.err.println("Você inseriu um caracter invalido, insira um numero para validar a posição!");
                scanner.nextLine();
            }
        }while(turnRunning == true);
    }

    /**
     * Função para verificar se o usuario ganhou o jogo
     * 
     * @return Retorna true se deu vitoria ou false se ainda nao
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public boolean verifyWin() {

        boolean gameFinished = false;
    
        if ((this.board[0][0] == this.board[1][0] && this.board[0][0] == this.board[2][0] && this.board[0][0] != '-') ||    // 1ª coluna
            (this.board[0][1] == this.board[1][1] && this.board[0][1] == this.board[2][1] && this.board[0][1] != '-') ||    // 2ª coluna 
            (this.board[0][2] == this.board[1][2] && this.board[0][2] == this.board[2][2] && this.board[0][2] != '-') ||    // 3ª coluna
    
            (this.board[0][0] == this.board[0][1] && this.board[0][0] == this.board[0][2] && this.board[0][0] != '-') ||    // 1ª linha
            (this.board[1][0] == this.board[1][1] && this.board[1][0] == this.board[1][2] && this.board[1][0] != '-') ||    // 2ª linha
            (this.board[2][0] == this.board[2][1] && this.board[2][0] == this.board[2][2] && this.board[2][0] != '-') ||    // 3ª linha
    
            (this.board[0][0] == this.board[1][1] && this.board[0][0] == this.board[2][2] && this.board[0][0] != '-') ||    // Diagonal \
            (this.board[0][2] == this.board[1][1] && this.board[0][2] == this.board[2][0] && this.board[0][2] != '-')       // Diagonal /
        ) {
            gameFinished = true;
        }
    
        return gameFinished;
    }

    /**
     * Função para verificar empate
     * 
     * @return Retorna true se deu empate ou false se ainda nao
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public boolean verifyDraw() {

        int markerCount = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(this.board[i][j] != '-') {
                    markerCount++;
                }
            } 
        }

        if(markerCount == 9) {
            return true;
        }

        return false;

    }
}