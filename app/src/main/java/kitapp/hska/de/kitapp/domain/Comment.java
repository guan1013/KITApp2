package kitapp.hska.de.kitapp.domain;

import java.util.Date;

/**
 * Created by Sput on 20.06.2015.
 */
public class Comment {

    public Comment(String name, String text, Date date, Float evaluation) {
        this.name = name;
        this.text = text;
        this.date = date;
        this.evaluation = evaluation;
    }

    private String name;
    private String text;
    private Date date;
    private Float evaluation;

    public Float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Float evaluation) {
        this.evaluation = evaluation;
    }

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
