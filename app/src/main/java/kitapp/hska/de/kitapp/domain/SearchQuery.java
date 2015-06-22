package kitapp.hska.de.kitapp.domain;

import java.io.Serializable;

/**
 * Created by bwpc on 20.06.2015.
 */
public class SearchQuery implements Serializable {

    /*
    <======================= ATTRIBUTES =======================>
    */
    private Long id;
    private String city;
    private int circuit;
    private int minAge;
    private int maxAge;
    private int cost;
    private int open;
    private Double rating;
    private int size;
    private int closing;
    private int confession;
    private Double longitude;
    private Double latitude;

    /*
    <======================= CONSTRUCTORS =======================>
     */
    public SearchQuery() {

    }

    public SearchQuery(String city, int circuit, int minAge, int maxAge, int cost, int open, Double rating, int size, int closing, int confession, Double latitude, Double longitude) {
        this.city = city;
        this.circuit = circuit;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.cost = cost;
        this.open = open;
        this.rating = rating;
        this.size = size;
        this.closing = closing;
        this.confession = confession;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /*
    <======================= OVERRIDES =======================>
     */

    @Override
    public String toString() {
        return "SearchQuery{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", circuit=" + circuit +
                ", minAge=" + minAge +
                ", maxAge=" + maxAge +
                ", cost=" + cost +
                ", open=" + open +
                ", rating=" + rating +
                ", size=" + size +
                ", closing=" + closing +
                ", confession=" + confession +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SearchQuery)) return false;

        SearchQuery that = (SearchQuery) o;

        if (getCircuit() != that.getCircuit()) return false;
        if (getMinAge() != that.getMinAge()) return false;
        if (getMaxAge() != that.getMaxAge()) return false;
        if (getCost() != that.getCost()) return false;
        if (getOpen() != that.getOpen()) return false;
        if (getSize() != that.getSize()) return false;
        if (getClosing() != that.getClosing()) return false;
        if (getConfession() != that.getConfession()) return false;
        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null)
            return false;
        if (getRating() != null ? !getRating().equals(that.getRating()) : that.getRating() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(that.getLongitude()) : that.getLongitude() != null)
            return false;
        return !(getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null);

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + getCircuit();
        result = 31 * result + getMinAge();
        result = 31 * result + getMaxAge();
        result = 31 * result + getCost();
        result = 31 * result + getOpen();
        result = 31 * result + (getRating() != null ? getRating().hashCode() : 0);
        result = 31 * result + getSize();
        result = 31 * result + getClosing();
        result = 31 * result + getConfession();
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCircuit() {
        return circuit;
    }

    public void setCircuit(int circuit) {
        this.circuit = circuit;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getClosing() {
        return closing;
    }

    public void setClosing(int closing) {
        this.closing = closing;
    }

    public int getConfession() {
        return confession;
    }

    public void setConfession(int confession) {
        this.confession = confession;
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
}
