package kitapp.hska.de.kitapp.util;

/**
 * This is a utility class which holds all constant values.
 *
 * @author Andreas G�ntzel
 */
public class Constants {

    // URL OF THE BACKEND SERVER
    public final static String BACKEND_URL = "http://ebusiness-kitapp-backend.herokuapp.com";

    // PATHS OF THE REST-ENDPOINTS
    public final static String PATH_APP_USER = "/appuser";
    public final static String PATH_APP_USER_ID = "/id";
    public final static String PATH_KITAS = "/kitas";
    public final static String PATH_KITA = "/kita";
    public final static String PATH_LOGIN = "/server";
    public final static String PATH_EVALUATION = "/evaluation";
    public final static String PATH_SEARCH = "/search";

    // QUERY PARAMETER FOR REST-ENDPOINTS
    public final static String PARAM_APP_USER_ID = "email=";
    public final static String PARAM_KITA_ID = "id=";
    public final static String PARAM_KITA_CIY = "city=";

    // KEYS FOR THE EXTRA BUNDLE
    public final static String EXTRAS_KEY_LOGIN = "login";
    public final static String EXTRAS_KEY_RELOAD_KITA = "reload";

    // GLOBAL CONFIGURATIONS
    public final static Long TIME_OUT = 20L;

    // OTHER
    public final static String[] OPENING_HOURS = new String[]{"Halbtags", "Verlänger", "Ganztags"};
    public final static String[] CONFESSIONS = new String[]{"Katholisch", "Islamisch", "Buddisthisch", "Evangelisch", "Keine Konfesseion"};

}
