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
                ", authorId=" + authorIdx +
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

    public Long getAuthorIdx() {
        return authorIdx;
    }

    public void setAuthorIdx(Long authorId) {
        this.authorIdx = authorId;
    }


    public Long getKitaIdx() {
        return kitaIdx;
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
