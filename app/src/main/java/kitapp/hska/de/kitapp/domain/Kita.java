package kitapp.hska.de.kitapp.domain;

import java.util.Date;

/**
 * Created by Yannick on 20.06.2015.
 */
public class Kita {

    private Long id;

    private String name;

    private Date dateCreated;

    private Double cost;

    private Integer minAge;

    private Integer maxAge;

    private Confession confession;

    private enum Confession {

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

    private OpeningHours openingHours;

    private enum OpeningHours {

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
                ", id=" + id +
                '}';
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
}
