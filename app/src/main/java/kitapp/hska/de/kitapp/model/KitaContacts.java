package kitapp.hska.de.kitapp.model;

import android.provider.ContactsContract;

public class KitaContacts {

    public KitaContacts(String tel, String email) {
        this.tel = tel;
        this.email = email;
    }

    private String tel;
    private String email;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
