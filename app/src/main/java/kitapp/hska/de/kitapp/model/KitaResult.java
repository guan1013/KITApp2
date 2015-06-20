package kitapp.hska.de.kitapp.model;

import android.media.Image;
import android.provider.ContactsContract;

/**
 * Created by bwpc on 20.06.2015.
 */
public class KitaResult {

    public KitaResult(String name, Float evaluation, Double distance, String tel, String email) {
        this.name = name;
        this.evaluation = evaluation;
        this.distance = distance;
        this.contacts = new KitaContacts(tel, email);
    }

    private String name;
    private Float evaluation;
    private Double distance;
    private Image image;
    private KitaContacts contacts;

    public Float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Float evaluation) {
        this.evaluation = evaluation;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public KitaContacts getContacts() {
        return contacts;
    }

    public void setContacts(KitaContacts contacts) {
        this.contacts = contacts;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
