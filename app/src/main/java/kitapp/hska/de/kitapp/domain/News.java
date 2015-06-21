package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by bwpc on 21.06.2015.
 */
public class News implements Serializable {

    /*
    <======================= ATTRIBUTES =======================>
    */
    private Long id;
    private String text;
    private Date date;
    private AppUser author;

    /*
    <======================= CONSTRUCTORS =======================>
     */
    public News() {

    }

    public News(String text, Date date, AppUser author) {
        this.text = text;
        this.date = date;
        this.author = author;
    }

     /*
    <======================= OVERRIDES =======================>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof News)) return false;

        News news = (News) o;

        if (getId() != null ? !getId().equals(news.getId()) : news.getId() != null) return false;
        if (getText() != null ? !getText().equals(news.getText()) : news.getText() != null)
            return false;
        if (getDate() != null ? !getDate().equals(news.getDate()) : news.getDate() != null)
            return false;
        return !(getAuthor() != null ? !getAuthor().equals(news.getAuthor()) : news.getAuthor() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getText() != null ? getText().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", author=" + author +
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AppUser getAuthor() {
        return author;
    }

    public void setAuthor(AppUser author) {
        this.author = author;
    }
}
