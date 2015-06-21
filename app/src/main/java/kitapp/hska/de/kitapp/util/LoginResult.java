package kitapp.hska.de.kitapp.util;

import org.apache.http.StatusLine;

import kitapp.hska.de.kitapp.domain.AppUser;

/**
 * Created by Amin on 21.06.2015.
 */
public class LoginResult {

    private StatusLine statusLine;

    private String cookie;

    private AppUser appUser;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginResult that = (LoginResult) o;

        if (statusLine != null ? !statusLine.equals(that.statusLine) : that.statusLine != null)
            return false;
        return !(cookie != null ? !cookie.equals(that.cookie) : that.cookie != null);

    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    @Override
    public int hashCode() {
        int result = statusLine != null ? statusLine.hashCode() : 0;
        result = 31 * result + (cookie != null ? cookie.hashCode() : 0);
        return result;
    }

    public StatusLine getStatusLine() {

        return statusLine;
    }

    public void setStatusLine(StatusLine statusLine) {
        this.statusLine = statusLine;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }
}
