package kitapp.hska.de.kitapp.model;

import android.provider.ContactsContract;

public class KitaContacts {

    public KitaContacts(String tel, ContactsContract.CommonDataKinds.Email email) {
        this.tel = tel;
        this.email = email;
    }

    private String tel;
    private ContactsContract.CommonDataKinds.Email email;

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public ContactsContract.CommonDataKinds.Email getEmail() {
        return email;
    }

    public void setEmail(ContactsContract.CommonDataKinds.Email email) {
        this.email = email;
    }
}
