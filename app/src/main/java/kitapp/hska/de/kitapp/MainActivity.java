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
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Element;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import kitapp.hska.de.kitapp.domain.Kita;
import kitapp.hska.de.kitapp.services.KitaService;


/**
 * Main Activity of the app. It's the home screen where the user can do a simple
 * search by entering a city.
 */
public class MainActivity extends ActionBarActivity implements LocationListener {

    private KitaService.KitaServiceBinder kitaServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MainActivity.this.kitaServiceBinder = ((KitaService.KitaServiceBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    /**
     * Textfield for entering location for search
     */
    EditText editTextLocation;

    /**
     * Button for Location Service
     */
    ImageButton buttonLocation;

    Button buttonSuche;

    /**
     * Locationmanager for getting user's gps location
     */
    private LocationManager locationManager;

    private String provider;

    @Override
    protected void onStart() {
        Intent i = new Intent(this, KitaService.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
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
        setContentView(R.layout.activity_main);


        // Set references for ui objects
        buttonLocation = (ImageButton) findViewById(R.id.imageButtonLocation);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        buttonSuche = (Button) findViewById(R.id.buttonHomeSearch);

        buttonSuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the city entered by the user
                String city = editTextLocation.getText().toString();

                // Result of the search
                Kita[] kitas = null;

                // Call the service and perform the search
                try {
                    kitas = kitaServiceBinder.getKitaByCity(city);

                } catch (Exception e) {
                    e.printStackTrace();
                    toast(e.getClass().getName());
                    return;
                }

                // If there are no search results, return
                if (kitas == null) {
                    toast("Found 0 Kitas (kitas=null)");
                    return;
                } else {
                    toast("Found " + kitas.length + " Kitas");

                    if (kitas.length == 0) {
                        return;
                    }
                }

                // Send search result to ResultActivity
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("kitas", new ArrayList<Kita>(Arrays.asList(kitas)));
                startActivity(intent);

            }
        });

        // Set on click listener for GPS button
        buttonLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
                boolean enabled = service
                        .isProviderEnabled(LocationManager.GPS_PROVIDER);

                // check if enabled and if not send user to the GSP settings
                // Better solution would be to display a dialog and suggesting to
                // go to the settings
                if (!enabled) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
                // Get the location manager
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                // Define the criteria how to select the locatioin provider -> use
                // default
                Criteria criteria = new Criteria();
                provider = locationManager.getBestProvider(criteria, false);
                Location location = locationManager.getLastKnownLocation(provider);

                // Initialize the location fields
                if (location != null) {
                    System.out.println("Provider " + provider + " has been selected.");
                    onLocationChanged(location);
                } else {
                    editTextLocation.setText("Location not available");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {

            Intent myIntent = new Intent(this, SearchActivity.class);
            startActivity(myIntent);
            return true;
        }

        if(id == R.id.action_news) {
            Intent myIntent = new Intent(this,NewsActivity.class);
            startActivity(myIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = (double) (location.getLatitude());
        double lng = (double) (location.getLongitude());

        editTextLocation.setText(getLocationName(lat, lng));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
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

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}


