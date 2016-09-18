package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class Offer implements Serializable {
    private String title;
    private String offer_id;
    private String teaser;
    private String required_actions;
    private String link;
    private OfferType[] offer_types;
    private int payout;
    private Time time_to_payout;
    private Thumbnail thumbnail;
    private String store_id;

    public Offer() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequired_actions() {
        return required_actions;
    }

    public void setRequired_actions(String required_actions) {
        this.required_actions = required_actions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public OfferType[] getOffer_types() {
        return offer_types;
    }

    public void setOffer_types(OfferType[] offer_types) {
        this.offer_types = offer_types;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    public Time getTime_to_payout() {
        return time_to_payout;
    }

    public void setTime_to_payout(Time time_to_payout) {
        this.time_to_payout = time_to_payout;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}
