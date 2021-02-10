import java.util.ArrayList;
import java.util.Optional;

public class Scorer {

    private final ArrayList<Integer> values;

    public Scorer(Cup cup) {
        this.values = cup.getValues();
    }

    public int scoreCup() {
        int score = 0;
        Optional<Integer> tripleVal = tripleCharacter();

        //triples
        if (tripleVal.isPresent()) {
            removeTriplesValues(tripleVal.get());
            score += tripleScore(tripleVal.get());
        }

        //1s or 5s
        for (int value: values) {
            if (value == 1) {
                score += 100;
            }
            else if (value == 5) {
                score += 50;
            }
        }

        return score;
    }

    public int getScoringDiceCount() {
        int count = 0;
        Optional<Integer> tripleVal = tripleCharacter();
        if (tripleVal.isPresent()) {
            removeTriplesValues(tripleVal.get());
            count += 3;
        }

        for (int value: values) {
            if (value == 1) {
                count++;
            }
            else if (value == 5) {
                count++;
            }
        }
        return count;

    }

    private int tripleScore(int tripleVal) {
        if (tripleVal == 1) {
            return 1000;
        } else {
            return tripleVal*100;
        }
    }

    private void removeTriplesValues(int tripleVal) {
        for (int i = 0; i<3; i++) {
            values.remove(Integer.valueOf(tripleVal));
        }
    }

    private Optional<Integer> tripleCharacter() {
        for (Integer dieValue: values) {
            int count = 0;
            for (Integer value: values) {
                if (value.equals(dieValue)) { count++; }
                if (count == 3) {
                    return Optional.of(dieValue);
                }
            }
        }
        return Optional.empty();
    }

}
