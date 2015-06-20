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
}
