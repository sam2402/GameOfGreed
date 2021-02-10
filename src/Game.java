import java.util.ArrayList;
import java.util.Optional;

public class Game {

    private final ArrayList<Player> players = new ArrayList<>();
    private Optional<Player> winningPlayer;

    public Game(ArrayList<String> playerNames) {
        for (String name: playerNames) {
            players.add(new Player(name));
        }
        winningPlayer = Optional.empty();
    }

    public void playGame() {
        while (winningPlayer.isEmpty()) {
            for (Player player: players) {
                player.playTurn();
            }
            updateWinningPlayer();
        }
        System.out.println("Congratulations " + winningPlayer.get().getName() + ", you've won!!");

    }

    private void updateWinningPlayer() {
        for (Player player: players) {
            if (player.getScore() > 5000 && player.getScore() == getMaxScore()) {
                winningPlayer = Optional.of(player);
            }
        }
    }

    private int getMaxScore() {
        int maxScore = 0;
        for (Player player: players) {
            if (player.getScore() > maxScore) {
                maxScore = player.getScore();
            }
        }
        return maxScore;
    }

    public static void main(String[] args) {
        System.out.println("The Game of Greed\n");
        int playerCount = getInt("Enter the number of players: ");
        ArrayList<String> names = getPlayerNames(playerCount);
        Game game = new Game(names);
        game.playGame();
        System.out.println("\nThank you for playing \"The Game of Greed\"");
        System.exit(0);
    }


    /**
     * Functions for handling input
      */
    private static ArrayList<String> getPlayerNames(int playerCount) {
        ArrayList<String> playerNames = new ArrayList<>();
        for (int i = 1; i<=playerCount; i++) {
            String name = getString("Enter the name of player " + i + ": ");
            playerNames.add(name);
        }
        return playerNames;
    }

    public static String getString(String prompt) {
        Input in = new Input();
        while (true) {
            System.out.print(prompt);
            if (in.hasNextLine()) {
                return in.nextLine();
            } else {
                System.out.println("Please enter a string of characters");
            }
        }
    }

    private static int getInt(String prompt) {
        Input in = new Input();
        while (true) {
            System.out.print(prompt);
            if (in.hasNextInt()) {
                return in.nextInt();
            } else {
                in.nextLine();
                System.out.println("Please enter a whole number");
            }
        }
    }

}
