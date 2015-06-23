package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sput on 20.06.2015.
 */
public class Evaluation implements Serializable {


    /*
    <======================= ATTRIBUTES =======================>
    */
    private Long id;
    private String text;
    private Long authorIdx;
    private String authorName;
    private Date date;
    private Double rating;
    private Long kitaIdx;

    /*
    <======================= CONSTRUCTORS =======================>
     */
    public Evaluation() {

    }


    /*
    <======================= OVERRIDES =======================>
     */


    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", authorIdx=" + authorIdx +
                ", authorName='" + authorName + '\'' +
                ", date=" + date +
                ", rating=" + rating +
                ", kitaIdx=" + kitaIdx +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;

        Evaluation that = (Evaluation) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getText() != null ? !getText().equals(that.getText()) : that.getText() != null)
            return false;
        if (getAuthorIdx() != null ? !getAuthorIdx().equals(that.getAuthorIdx()) : that.getAuthorIdx() != null)
            return false;
        if (getAuthorName() != null ? !getAuthorName().equals(that.getAuthorName()) : that.getAuthorName() != null)
            return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null)
            return false;
        if (getRating() != null ? !getRating().equals(that.getRating()) : that.getRating() != null)
            return false;
        return !(getKitaIdx() != null ? !getKitaIdx().equals(that.getKitaIdx()) : that.getKitaIdx() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getAuthorIdx() != null ? getAuthorIdx().hashCode() : 0);
        result = 31 * result + (getAuthorName() != null ? getAuthorName().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        result = 31 * result + (getKitaIdx() != null ? getKitaIdx().hashCode() : 0);
        return result;
    }

    /*
    <======================= GETS & SETS =======================>
    */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getAuthorIdx() {
        return authorIdx;
    }

    public void setAuthorIdx(Long authorId) {
        this.authorIdx = authorId;
    }


    public Long getKitaIdx() {
        return kitaIdx;
    }

    public String getAuthorName() {

        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setKitaIdx(Long kitaId) {
        this.kitaIdx = kitaId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
