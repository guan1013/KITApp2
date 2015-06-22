package kitapp.hska.de.kitapp.util;

import org.apache.http.StatusLine;

import java.io.Serializable;

import kitapp.hska.de.kitapp.domain.AppUser;

/**
 * Created by Amin on 21.06.2015.
 */
public class LoginResult implements Serializable {

    private String cookie;

    private AppUser appUser;

    private int statusCode;

    private String reasonPhrase;

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public void setReasonPhrase(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
