package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Yannick on 20.06.2015.
 */
public class Kita implements Serializable {


    /*
    <======================= ATTRIBUTES =======================>
    */
    private Long id;
    private String name;
    private Date dateCreated;
    private List<News> news;
    private List<Evaluation> evaluations;
    private Double longitude;
    private Double latitude;
    private Double costs;
    private Integer minAge;
    private Integer maxAge;
    private Integer maxGroupSize;
    private Integer closingDays;
    private Confession confession;
    private OpeningHours openingHours;
    private String management;
    private String about;
    private Address address;
    private Double avgRating;

    /*
    <======================= CONSTRUCTORS =======================>
     */
    public Kita() {

    }

    public Kita(String name, Date dateCreated, List<News> news, List<Evaluation> evaluations, Double longitude, Double latitude, Double costs, Integer minAge, Integer maxAge, Integer maxGroupSize, Integer closingDays, Confession confession, OpeningHours openingHours, String management, String about, Address address, Double avgRating) {
        this.name = name;
        this.dateCreated = dateCreated;
        this.news = news;
        this.evaluations = evaluations;
        this.longitude = longitude;
        this.latitude = latitude;
        this.costs = costs;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.maxGroupSize = maxGroupSize;
        this.closingDays = closingDays;
        this.confession = confession;
        this.openingHours = openingHours;
        this.management = management;
        this.about = about;
        this.address = address;
        this.avgRating = avgRating;
    }

    /*
    <======================= ENUMERATIONS =======================>
     */

    public enum Confession {

        KATHOLIC("Katholisch"), ISLAMIC("Islamisch"), BUDDHISTIC("Buddhistisch"), EVANGELIC(
                "Evangelisch"), NO_CONFESSION("Keine Konfession");

        private String text;

        Confession(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }

    }

    public enum OpeningHours {

        HALF("Halbtags"), LONGER("Verlängerte Öffnungszeiten"), FULL("Ganztags");

        private String text;

        OpeningHours(String text) {
            this.text = text;
        }

        @Override
        public String toString() {
            return text;
        }
    }

    /*
    <======================= OVERRIDES =======================>
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Kita)) return false;

        Kita kita = (Kita) o;

        if (getId() != null ? !getId().equals(kita.getId()) : kita.getId() != null) return false;
        if (getName() != null ? !getName().equals(kita.getName()) : kita.getName() != null)
            return false;
        if (getDateCreated() != null ? !getDateCreated().equals(kita.getDateCreated()) : kita.getDateCreated() != null)
            return false;
        if (getNews() != null ? !getNews().equals(kita.getNews()) : kita.getNews() != null)
            return false;
        if (getEvaluations() != null ? !getEvaluations().equals(kita.getEvaluations()) : kita.getEvaluations() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(kita.getLongitude()) : kita.getLongitude() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(kita.getLatitude()) : kita.getLatitude() != null)
            return false;
        if (getCosts() != null ? !getCosts().equals(kita.getCosts()) : kita.getCosts() != null)
            return false;
        if (getMinAge() != null ? !getMinAge().equals(kita.getMinAge()) : kita.getMinAge() != null)
            return false;
        if (getMaxAge() != null ? !getMaxAge().equals(kita.getMaxAge()) : kita.getMaxAge() != null)
            return false;
        if (getMaxGroupSize() != null ? !getMaxGroupSize().equals(kita.getMaxGroupSize()) : kita.getMaxGroupSize() != null)
            return false;
        if (getClosingDays() != null ? !getClosingDays().equals(kita.getClosingDays()) : kita.getClosingDays() != null)
            return false;
        if (getConfession() != kita.getConfession()) return false;
        if (getOpeningHours() != kita.getOpeningHours()) return false;
        if (getManagement() != null ? !getManagement().equals(kita.getManagement()) : kita.getManagement() != null)
            return false;
        if (getAbout() != null ? !getAbout().equals(kita.getAbout()) : kita.getAbout() != null)
            return false;
        if (getAddress() != null ? !getAddress().equals(kita.getAddress()) : kita.getAddress() != null)
            return false;
        return !(getAvgRating() != null ? !getAvgRating().equals(kita.getAvgRating()) : kita.getAvgRating() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getDateCreated() != null ? getDateCreated().hashCode() : 0);
        result = 31 * result + (getNews() != null ? getNews().hashCode() : 0);
        result = 31 * result + (getEvaluations() != null ? getEvaluations().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getCosts() != null ? getCosts().hashCode() : 0);
        result = 31 * result + (getMinAge() != null ? getMinAge().hashCode() : 0);
        result = 31 * result + (getMaxAge() != null ? getMaxAge().hashCode() : 0);
        result = 31 * result + (getMaxGroupSize() != null ? getMaxGroupSize().hashCode() : 0);
        result = 31 * result + (getClosingDays() != null ? getClosingDays().hashCode() : 0);
        result = 31 * result + (getConfession() != null ? getConfession().hashCode() : 0);
        result = 31 * result + (getOpeningHours() != null ? getOpeningHours().hashCode() : 0);
        result = 31 * result + (getManagement() != null ? getManagement().hashCode() : 0);
        result = 31 * result + (getAbout() != null ? getAbout().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getAvgRating() != null ? getAvgRating().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Kita{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateCreated=" + dateCreated +
                ", news=" + news +
                ", evaluations=" + evaluations +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", costs=" + costs +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", maxGroupSize=" + maxGroupSize +
                ", closingDays=" + closingDays +
                ", confession=" + confession +
                ", openingHours=" + openingHours +
                ", management='" + management + '\'' +
                ", about='" + about + '\'' +
                ", address=" + address +
                ", avgRating=" + avgRating +
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

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }

    public void setEvaluations(List<Evaluation> evaluations) {
        this.evaluations = evaluations;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getCosts() {
        return costs;
    }

    public void setCosts(Double costs) {
        this.costs = costs;
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

    public Integer getMaxGroupSize() {
        return maxGroupSize;
    }

    public void setMaxGroupSize(Integer maxGroupSize) {
        this.maxGroupSize = maxGroupSize;
    }

    public Integer getClosingDays() {
        return closingDays;
    }

    public void setClosingDays(Integer closingDays) {
        this.closingDays = closingDays;
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

    public String getManagement() {
        return management;
    }

    public void setManagement(String management) {
        this.management = management;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Double getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(Double avgRating) {
        this.avgRating = avgRating;
    }
}
