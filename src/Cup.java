import java.util.ArrayList;

public class Cup {

    ArrayList<Die> dice;

    public Cup(int diceCount) {
        this.dice = new ArrayList<>();
        for (int i = 0; i<diceCount; i++) {
            dice.add(new Die());
        }
    }

    public void roll() {
        for (Die die: dice) {
            die.roll();
        }
    }

    public ArrayList<Integer> getValues() {
        ArrayList<Integer> values = new ArrayList<>();
        for (Die die: dice) {
            values.add(die.getValue());
        }
        return values;
    }

    public String diceValuesString() {
        String str = "";
        for (Die die: dice) {
            str = str.concat(die.getStringValue() + ", ");
        }
        return str.substring(0, str.length()-2);
    }

    public int getDiceCount() {
        return dice.size();
    }

    public boolean isBust() {
        System.out.println(getScore());
        return getScore()==0;
    }

    public int getScore() {
        Scorer scorer = new Scorer(this);
        return scorer.scoreCup();
    }

    public void updateDiceCount() {

        int newDiceCount = getNewDiceCount();
        dice = new ArrayList<>();
        for (int i = 0; i<newDiceCount; i++) {
            dice.add(new Die());
        }

    }

    private int getNewDiceCount() {
        Scorer scorer = new Scorer(this);
        int scoringDiceCount = scorer.getScoringDiceCount();
        if (scoringDiceCount == getDiceCount()) {
            return scoringDiceCount;
        } else {
            return getDiceCount() - scoringDiceCount;
        }
    }

}
