package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class Time implements Serializable {
    private int amount;
    private String readable;

    public Time() {
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
