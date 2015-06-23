package kitapp.hska.de.kitapp.util;

/**
 * Created by bwpc on 23.06.2015.
 */
public enum SortSpinnerEnum {

    DIST_ASC("Entfernung(Aufsteigend)"), DIST_DES("Entfernung(Absteigend)"), COST_ASC("Kosten (Aufsteigend)"), COST_DES("Kosten (Absteigend)");

    private String compareTo;

    SortSpinnerEnum(String compareTo) {
        this.compareTo = compareTo;
    }

    public String getCompareTo() {
        return compareTo;
    }
}
