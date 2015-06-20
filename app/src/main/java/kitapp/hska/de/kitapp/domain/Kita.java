package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Yannick on 20.06.2015.
 */
public class Kita implements Serializable {

    public Kita() {

    }

    public Kita(String name, Date dateCreated, Double cost, Integer minAge, Integer maxAge, Confession confession, Float evaluation, String management, List<Comment> ratings, String about, OpeningHours openingHours) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.cost = cost;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.confession = confession;
        this.evaluation = evaluation;
        this.management = management;
        this.ratings = ratings;
        this.about = about;
        this.openingHours = openingHours;
    }

    private Long id;

    private String name;

    private Date dateCreated;

    private Double cost;

    private Integer minAge;

    private Integer maxAge;

    private Confession confession;

    private Float evaluation;

    private String management;

    private List<Comment> ratings;

    private String about;

    private OpeningHours openingHours;

    public enum Confession {

        KATHOLIC("Katholisch"), ISLAMIC("Islamisch"), BUDDHISTIC("Buddhistisch"), EVANGELIC(
                "Evangelisch"), NO_CONFESSION("Keine Konfession");

        private String text;

        Confession(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

    }


    public enum OpeningHours {

        HALF("Halbtags"), LONGER("Verlängerte Öffnungszeiten"), FULL("Ganztags");

        private String text;

        OpeningHours(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    @Override
    public String toString() {
        return "Kita{" +
                "cost=" + cost +
                ", confession=" + confession +
                ", openingHours=" + openingHours +
                ", maxAge=" + maxAge +
                ", minAge=" + minAge +
                ", dateCreated=" + dateCreated +
                ", name='" + name + '\'' +
                '}';
    }

    public void addRating(Comment rating) {
        this.ratings.add(rating);
    }

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public void setMinAge(Integer minAge) {
        this.minAge = minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Integer maxAge) {
        this.maxAge = maxAge;
    }

    public Confession getConfession() {
        return confession;
    }

    public void setConfession(Confession confession) {
        this.confession = confession;
    }

    public OpeningHours getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public Float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Float evaluation) {
        this.evaluation = evaluation;
    }

    public List<Comment> getRatings() {
        return ratings;
    }

    public void setRatings(List<Comment> ratings) {
        this.ratings = ratings;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }
}
