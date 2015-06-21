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
    private AppUser author;
    private Date date;
    private Double rating;

    /*
    <======================= CONSTRUCTORS =======================>
     */
    public Evaluation() {

    }

    public Evaluation(String text, AppUser author, Date date, Double rating) {
        this.text = text;
        this.author = author;
        this.date = date;
        this.rating = rating;

    }

    /*
    <======================= OVERRIDES =======================>
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evaluation)) return false;

        Evaluation that = (Evaluation) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getText() != null ? !getText().equals(that.getText()) : that.getText() != null)
            return false;
        if (getAuthor() != null ? !getAuthor().equals(that.getAuthor()) : that.getAuthor() != null)
            return false;
        if (getDate() != null ? !getDate().equals(that.getDate()) : that.getDate() != null)
            return false;
        return !(getRating() != null ? !getRating().equals(that.getRating()) : that.getRating() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Evaluation{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + author +
                ", date=" + date +
                ", rating=" + rating +
                '}';
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

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
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
