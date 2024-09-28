import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Classe Main para rodar o jogo
 * 
 * 
 * @author Victor Renaud
 * @version 1.0
 */
public class Main {
    
    /**
     * Função para limpar o console
     * 
     * 
     * @author Victor Renaud
     * @version 1.0
     */
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

    /**
     * Função main para rodar o jogo
     * 
     * 
     * @author Victor Renaud
     * @version 1.0
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);  // Inicia o Scanner para leitura do teclado
        boolean loopGame = true;

        do{           
            TicTacToe game = new TicTacToe(); // Inicia uma instancia do Jogo
            boolean gameFinished = false, gameFinishedDraw = false;   
            int inGameNumber = 0;
            game.setPositionsBoard();

            //Primeiro Menu
            clearConsole();
            game.printFirstMenu(); // Mostra o menu principal
            scanner.nextLine(); // Aguarda o usuario a clicar ENTER
            clearConsole();

            game.resetBoard();

            // Loop do jogo
            do{
                game.startTurn(scanner);
                clearConsole();
                gameFinished = game.verifyWin();

                if(gameFinished == false) {
                    gameFinishedDraw = game.verifyDraw();
                }       

            } while(gameFinished == false && gameFinishedDraw == false);  
            
            // Indica o final do jogo
            if(gameFinished == true) {
                game.printBoard();
                game.changeMarker();
                System.err.println("\n \nO jogador com o marcador " + game.marker + " ganhou a partida! ");
            }else if(gameFinishedDraw == true) {
                game.printBoard();
                System.out.println("\n \nO jogo deu empate!");
            }

            // Pergunta ao usuario se quer jogar novamente
            try {
                System.out.println("\nPressione 1 para jogar novamente ou 9 para sair");
                inGameNumber = scanner.nextInt();
            }catch (InputMismatchException e) {
                System.out.println("Você inseriou um caracter invalido, pressione ENTER para reiniciar o jogo...");
                scanner.nextLine();
            }
            
            // Verifica a saida
            if(inGameNumber == 9) {
                loopGame = false;
            }else {
                loopGame = true;
            }
        }while(loopGame == true);

        scanner.close(); // Fecha o Scanner

    }
}
