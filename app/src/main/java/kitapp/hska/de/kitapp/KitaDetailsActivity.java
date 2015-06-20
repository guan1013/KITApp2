package kitapp.hska.de.kitapp;

import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import kitapp.hska.de.kitapp.domain.Kita;

public class KitaDetailsActivity extends AppCompatActivity {


    TextView kitaDetailsName;
    TextView kitaDetailsEvaluation;
    TextView kitaDetailsCosts;



    RatingBar kitaDetailsRatingbar;

    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kita_details);

        Kita kita = new Kita();

        kita.setName("Dunkle Seitenstraße");
        kita.setConfession(Kita.Confession.NO_CONFESSION);
        kita.setCost(666.66);
        kita.setMaxAge(99);
        kita.setMinAge(66);
        kita.setOpeningHours(Kita.OpeningHours.FULL);
        kita.setId(666L);

        kitaDetailsName = (TextView) findViewById(R.id.KitaDetailsName);
        kitaDetailsEvaluation = (TextView) findViewById(R.id.KitaDetailsEvaluation);
        kitaDetailsCosts = (TextView) findViewById(R.id.KitaDetailsCosts);


        kitaDetailsRatingbar = (RatingBar) findViewById(R.id.KitaDetailsRatingBar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kita_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
