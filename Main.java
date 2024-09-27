import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    
    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para limpar o console no Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para limpar o console no Unix/Linux/MacOS
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Erro ao limpar o console.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);  // Inicia o Scanner para leitura do teclado
        TicTacToe game = new TicTacToe(); // Inicia uma instancia do Jogo
        boolean gameFinished = false;
        game.setPositionsBoard();

        clearConsole();
        game.printFirstMenu(); // Mostra o menu principal
        scanner.nextLine(); // Aguarda o usuario a clicar ENTER
        clearConsole();

        game.resetBoard();

        do{
            game.startTurn(scanner);
            clearConsole();
            gameFinished = game.verifyWin();
        } while(gameFinished == false);        

        scanner.close(); // Fecha o Scanner

    }
}
