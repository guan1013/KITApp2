package kitapp.hska.de.kitapp.util;

import android.location.Location;

import java.util.Comparator;

import kitapp.hska.de.kitapp.domain.Kita;

/**
 * Created by bwpc on 23.06.2015.
 */
public class KitaComparer implements Comparator<Kita> {

    String compareBy;

    Location currentLocation;

    @Override
    public int compare(Kita kita1, Kita kita2) {

        if (compareBy.equalsIgnoreCase(SortSpinnerEnum.DIST_ASC.toString()))
            return compareFloatASC(calcLocationDis(kita1), calcLocationDis(kita2));
        else if (compareBy.equalsIgnoreCase(SortSpinnerEnum.DIST_DES.toString()))
            return compareFloatDES(calcLocationDis(kita1), calcLocationDis(kita2));
        else if (compareBy.equalsIgnoreCase(SortSpinnerEnum.COST_ASC.toString()))
            return compareDoubleASC(kita1.getCosts(), kita2.getCosts());
        else if (compareBy.equalsIgnoreCase(SortSpinnerEnum.COST_DES.toString()))
            return compareDoubleDES(kita1.getCosts(), kita2.getCosts());

        return 0;
    }

    private float calcLocationDis(Kita kita) {
        Location location1 = new Location(kita.getName());
        location1.setLongitude(kita.getLongitude());
        location1.setLatitude(kita.getLatitude());
        return currentLocation.distanceTo(location1);
    }

    private int compareFloatASC(float x, float y) {
        return x < y ? -1 : x > y ? 1 : 0;
    }

    private int compareFloatDES(float x, float y) {
        return x < y ? 1 : x > y ? -1 : 0;
    }

    private int compareDoubleASC(double x, double y) {
        return x < y ? -1 : x > y ? 1 : 0;
    }

    private int compareDoubleDES(double x, double y) {
        return x < y ? 1 : x > y ? -1 : 0;
    }

    public String getCompareBy() {
        return compareBy;
    }

    public void setCompareBy(String compareBy) {
        this.compareBy = compareBy;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
