package kitapp.hska.de.kitapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.GoogleMap;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import kitapp.hska.de.kitapp.domain.Kita;
import kitapp.hska.de.kitapp.domain.SearchQuery;
import kitapp.hska.de.kitapp.services.KitaService;


public class SearchActivity extends ActionBarActivity implements LocationListener {

    /*
    <======================= CONSTANTS =======================>
     */
    private final static String KITAS_BUNDLE_KEY = "kitas";

    /*
    <======================= VIEW ATTRIBUTES =======================>
     */
    private EditText editTextCity;
    private SeekBar seekBarCircuit;
    private EditText editTextCircuit;
    private ToggleButton toggleButtonAge1;
    private ToggleButton toggleButtonAge2;
    private ToggleButton toggleButtonAge3;
    private RatingBar ratingBarEvaluation;
    private Spinner spinnerConfession;
    private ImageButton buttonLocation;
    private GoogleMap map;

    /*
    <======================= CLASS ATTRUIBUTES =======================>
     */
    private int costs;
    private int open;
    private int size;
    private int closing;

    private LocationManager locationManager;
    private String provider;
    private KitaService.KitaServiceBinder kitaServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SearchActivity.this.kitaServiceBinder = ((KitaService.KitaServiceBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /*
    <======================= PUBLIC METHODS =======================>
     */
    public void sendSearch(View button) {
        // Get UI input
        String city = editTextCity.getText().toString();
        int circuit = getCircuit();
        Integer[] minMaxAge = getMinMaxAge();
        int minAge = minMaxAge[0];
        int maxAge = minMaxAge[1];
        int cost = this.costs;
        int open = this.open;
        Double rating = getRating();
        int size = this.size;
        int closing = this.closing;
        int confession = getConfessionValue();

        SearchQuery query = new SearchQuery(city, circuit, minAge, maxAge, cost, open, rating, size, closing, confession);

        sendQuery(query);

    }

    public void onToggleCost(View button) {

        ((RadioGroup) button.getParent()).check(button.getId());

        String costString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText1))) {
            costString = getString(R.string.searchCostButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText2))) {
            costString = getString(R.string.searchCostButtonValue2);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText3))) {
            costString = getString(R.string.searchCostButtonValue3);
        }

        try {
            costs = Integer.parseInt(costString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onToggleSize(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        String sizeString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText1))) {
            sizeString = getString(R.string.searchSizeButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText2))) {
            sizeString = getString(R.string.searchSizeButtonValue2);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText3))) {
            sizeString = getString(R.string.searchSizeButtonValue3);
        }

        try {
            size = Integer.parseInt(sizeString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onToggleClosing(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        String closingString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchClosingButtonText1))) {
            closingString = getString(R.string.searchClosingButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchClosingButtonText2))) {
            closingString = getString(R.string.searchClosingButtonValue2);
        }

        try {
            closing = Integer.parseInt(closingString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onToggleOpen(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchOpenButtonValue1))) {
            open = 0;
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchOpenButtonValue2))) {
            open = 1;
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchOpenButtonValue3))) {
            open = 2;
        }

    }

    /*
    <======================= PRIVATE METHODS =======================>
     */

    private void sendQuery(SearchQuery query) {

        Kita[] kitas;
        try {
            kitas = kitaServiceBinder.getKitaBySearchQuery(query);

            if (kitas != null && kitas.length > 0) {
                Intent intent = new Intent(this, ResultActivity.class);
                intent.putExtra(KITAS_BUNDLE_KEY, new ArrayList<>(Arrays.asList(kitas)));
                startActivity(intent);
            } else {
                toast(getString(R.string.noKitasFound));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }

    private Integer getCircuit() {
        int circuit = 0;

        try {
            circuit = Integer.parseInt(editTextCircuit.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return circuit;
    }

    private Double getRating() {
        Double rating = 0.0;

        try {
            rating = (double) this.ratingBarEvaluation.getRating();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rating;
    }

    private int getConfessionValue() {

        String confessionString = spinnerConfession.getSelectedItem().toString();
        int confession = 0;

        if (confessionString.equals(Kita.Confession.KATHOLIC.toString())) {
            confession = 0;
        } else if (confessionString.equals(Kita.Confession.ISLAMIC.toString())) {
            confession = 1;
        } else if (confessionString.equals(Kita.Confession.BUDDHISTIC.toString())) {
            confession = 2;
        } else if (confessionString.equals(Kita.Confession.EVANGELIC.toString())) {
            confession = 3;
        } else if (confessionString.equals(Kita.Confession.NO_CONFESSION.toString())) {
            confession = 4;
        }

        return confession;
    }

    private Integer[] getMinMaxAge() {

        Integer[] minMaxAge = new Integer[2];

        Boolean age1 = this.toggleButtonAge1.isChecked();
        Boolean age2 = this.toggleButtonAge2.isChecked();
        Boolean age3 = this.toggleButtonAge3.isChecked();

        int minAge = 0;
        int maxAge = 99;
        if (age1) {


            String[] values = getString(R.string.searchAgeButtonValue1).split(",");

            try {
                minAge = Integer.parseInt(values[0]);
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (age2) {
            String[] values = getString(R.string.searchAgeButtonValue2).split(",");
            try {
                if (!age1) {
                    minAge = Integer.parseInt(values[0]);
                }
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (age3) {
            String[] values = getString(R.string.searchAgeButtonValue3).split(",");
            try {
                if (!age1 && !age2) {
                    minAge = Integer.parseInt(values[0]);
                }
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        minMaxAge[0] = minAge;
        minMaxAge[1] = maxAge;

        return minMaxAge;
    }

    private void initViews() {
        this.editTextCity = (EditText) findViewById(R.id.search_edittext_city);
        this.seekBarCircuit = (SeekBar) findViewById(R.id.search_seekbar_circuit);
        this.editTextCircuit = (EditText) findViewById(R.id.search_edittext_circuit);
        this.toggleButtonAge1 = (ToggleButton) findViewById(R.id.search_togglebutton_age_1);
        this.toggleButtonAge2 = (ToggleButton) findViewById(R.id.search_togglebutton_age_2);
        this.toggleButtonAge3 = (ToggleButton) findViewById(R.id.search_togglebutton_age_3);
        this.ratingBarEvaluation = (RatingBar) findViewById(R.id.search_ratingbar_rating);
        this.spinnerConfession = (Spinner) findViewById(R.id.search_spinner_confession);
        this.buttonLocation = (ImageButton) findViewById(R.id.search_imagebutton_location);

    }


    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void setSeekBarCircuitChangeListener() {

        editTextCircuit.setText("0");
        seekBarCircuit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Integer progresValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresValue = progress;
                editTextCircuit.setText(progresValue.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void setOnCheckedListener(int id) {
        ((RadioGroup) findViewById(id)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });
    }

    private void setSpinnerConfessionListener() {
        spinnerConfession.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Kita.Confession.values()));
    }

    private void setButtonLocationListener() {
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

                // check if enabled and if not send user to the GSP settings
                // Better solution would be to display a dialog and suggesting to
                // go to the settings
                if (!enabled) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }

                // Get location mangager
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // Define the criteria how to select the locatioin provider -> use
                // default
                Criteria criteria = new Criteria();
                provider = locationManager.getBestProvider(criteria, false);
                Location location = locationManager.getLastKnownLocation(provider);

                // Initialze location field
                if (location != null) {
                    System.out.println("Provider " + provider + " has been selected.");
                    onLocationChanged(location);
                } else {
                    editTextCity.setText(null);
                    editTextCity.setHint("Location not available");
                }

            }
        });
    }

    public String getLocationName(double lattitude, double longitude) {

        String cityName = "Not Found";
        Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
        try {

            List<Address> addresses = gcd.getFromLocation(lattitude, longitude,
                    10);

            for (Address adrs : addresses) {
                if (adrs != null) {

                    String city = adrs.getLocality();
                    if (city != null && !city.equals("")) {
                        cityName = city;
                        System.out.println("city ::  " + cityName);
                    } else {

                    }
                    // // you should also try with addresses.get(0).toSring();

                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityName;

    }


    private void initValues() {
        costs = 200;
        open = 0;
        size = 15;
        closing = 0;
    }

    /*
    <======================= OVERRIDE METHODS =======================>
     */

    @Override
    protected void onStart() {
        Intent intent = new Intent(this, KitaService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unbindService(serviceConnection);
        super.onStop();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();
        setSeekBarCircuitChangeListener();
        setOnCheckedListener(R.id.search_radiogroup_cost);
        setOnCheckedListener(R.id.search_radiogroup_size);
        setOnCheckedListener(R.id.search_radiogroup_closing);
        setOnCheckedListener(R.id.search_radiogroup_open);
        setSpinnerConfessionListener();
        setButtonLocationListener();
        initValues();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_home) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_favorites) {

        } else if (id == R.id.action_news) {

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());

        editTextCity.setText(getLocationName(lat, lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


}