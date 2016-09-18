package sodaoud.github.io.fyber.data;

import java.io.Serializable;

/**
 * Created by sofiane on 9/17/16.
 */
public class Information implements Serializable{
    private String app_name;
    private int appid;
    private String virtual_currency;
    private String virtual_currency_sale_enabled;
    private String country;
    private String language;
    private String support_url;

    public Information() {
    }

    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getVirtual_currency() {
        return virtual_currency;
    }

    public void setVirtual_currency(String virtual_currency) {
        this.virtual_currency = virtual_currency;
    }

    public String getVirtual_currency_sale_enabled() {
        return virtual_currency_sale_enabled;
    }

    public void setVirtual_currency_sale_enabled(String virtual_currency_sale_enabled) {
        this.virtual_currency_sale_enabled = virtual_currency_sale_enabled;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupport_url() {
        return support_url;
    }

    public void setSupport_url(String support_url) {
        this.support_url = support_url;
    }
}
