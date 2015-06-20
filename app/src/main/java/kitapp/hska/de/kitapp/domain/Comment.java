package kitapp.hska.de.kitapp.domain;

import java.util.Date;

/**
 * Created by Sput on 20.06.2015.
 */
public class Comment {

    public Comment(String name, String text, Date date) {
        this.name = name;
        this.text = text;
        this.date = date;
    }

    private String name;
    private String text;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
