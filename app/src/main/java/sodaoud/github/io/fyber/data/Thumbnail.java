package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class Thumbnail implements Serializable {
    private String lowres;
    private String hires;

    public Thumbnail() {
    }

    public String getLowres() {
        return lowres;
    }

    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }
}
