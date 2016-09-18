package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class OfferType implements Serializable {
    private int offer_type_id;
    private String readable;

    public OfferType() {
    }

    public int getOffer_type_id() {
        return offer_type_id;
    }

    public void setOffer_type_id(int offer_type_id) {
        this.offer_type_id = offer_type_id;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }
}
