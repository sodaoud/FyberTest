package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class FyberResponse implements Serializable{
    private String code;
    private String message;
    private int count;
    private int pages;
    private Information information;
    private Offer[] offers;

    public FyberResponse() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    public Offer[] getOffers() {
        return offers;
    }

    public void setOffers(Offer[] offers) {
        this.offers = offers;
    }
}
