package kitapp.hska.de.kitapp.domain;

/**
 * Created by bwpc on 20.06.2015.
 */
public class SearchQuery {

    private String city;
    private int circuit;
    private int minAge;
    private int maxAge;
    private int cost;
    private String open;
    private Float evaluation;
    private int size;
    private int closing;


    public SearchQuery(String city, int circuit, int minAge, int maxAge, int cost, String open, Float evaluation, int size, int closing) {
        this.city = city;
        this.circuit = circuit;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.cost = cost;
        this.open = open;
        this.evaluation = evaluation;
        this.size = size;
        this.closing = closing;
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

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public Float getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(Float evaluation) {
        this.evaluation = evaluation;
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

    public String getCity() {

        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
