import java.util.Random;

public class Die {

    Random random = new Random();
    int value;

    public void roll() {
        value = random.nextInt(6) + 1;
    }

    public int getValue() {
        return value;
    }

    public String getStringValue() {
        return String.valueOf(value);
    }
}
