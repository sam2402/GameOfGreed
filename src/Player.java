public class Player {

    private final String name;
    private int score = 0;
    private boolean isFirstTurn = true;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int increment) {
        score += increment;
    }

    public void playTurn() {
        System.out.println("\n" + name + ", it is your turn");
        Cup cup = new Cup(6);
        int turnScore = 0;

        // first go
        if (getIsFirstTurn()) {
            boolean userWantsToRoll = askIfUserWantsToRoll();
            if (userWantsToRoll) {
                cup.roll();
                int rollScore = cup.getScore();
                System.out.println("You have rolled: " + cup.diceValuesString());
                System.out.println("You scored " + rollScore);
                if (rollScore < 300) {
                    System.out.println("You need to score over 300 to start");
                } else {
                    System.out.println("That's over 300. You can now start!");
                    isFirstTurn = false;
                    turnScore += rollScore;
                    cup.updateDiceCount();
                    System.out.println("There are now " + cup.getDiceCount() + " dice left in the cup");
                }
            }
        }

        while (!getIsFirstTurn()) {
            boolean userWantsToRoll = askIfUserWantsToRoll();
            if (!userWantsToRoll) {
                increaseScore(turnScore);
                System.out.println("\nYou scored " + turnScore + " that turn");
                System.out.println("Your total score is: " + score);
                break;
            }
            cup.roll();
            int rollScore = cup.getScore();
            System.out.println("You have rolled: " + cup.diceValuesString());

            if (cup.isBust()) {
                System.out.println("You didn't score anything. You've gone bust!");
                System.out.println("Total score: " + score);
                break;
            }
            turnScore += rollScore;
            cup.updateDiceCount();
            System.out.println("There are now " + cup.getDiceCount() + " dice left in the cup");
            System.out.println("\nThis roll's score: " + rollScore);
            System.out.println("This turns score: " + turnScore);
        }

    }

    private boolean askIfUserWantsToRoll() {
        while (true) {
            String input = Game.getString("Press \"y\" to roll or \"n\" to not roll: ");
            switch (input) {
                case "y": return true;
                case "n": return false;
                default:
                    System.out.println("Please enter \"y\" or \"n\"");
            }
        }
    }

    private boolean getIsFirstTurn() {
        return isFirstTurn;
    }
}
