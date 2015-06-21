package kitapp.hska.de.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.CommentsAdapter;
import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.domain.Evaluation;
import kitapp.hska.de.kitapp.domain.Kita;

public class KitaDetailsActivity extends AppCompatActivity {


    TextView kitaDetailsName;
    TextView kitaDetailsEvaluation;
    TextView kitaDetailsCostsData;
    TextView kitaDetailsOpenhours;
    TextView kitaDetailsManagement;
    TextView kitaDetailsConfessionData;
    TextView kitaDetailsAgeData;
    TextView kitaDetailsAboutData;

    RatingBar kitaDetailsRatingbar;

    private ListView resultListView;

    private void initViews() {
        resultListView = (ListView) findViewById(R.id.result_listview);
        kitaDetailsName = (TextView) findViewById(R.id.KitaDetailsName);
        kitaDetailsEvaluation = (TextView) findViewById(R.id.KitaDetailsEvaluation);
        kitaDetailsCostsData = (TextView) findViewById(R.id.KitaDetailsCostsData);
        kitaDetailsOpenhours = (TextView) findViewById(R.id.KitaDetailsOpenhours);
        kitaDetailsManagement = (TextView) findViewById(R.id.KitaDetailsManagementData);
        kitaDetailsConfessionData = (TextView) findViewById(R.id.KitaDetailsConfessionData);
        kitaDetailsAgeData = (TextView) findViewById(R.id.KitaDetailsAgeData);
        kitaDetailsAboutData = (TextView) findViewById(R.id.KitaDetailsAboutData);
        kitaDetailsRatingbar = (RatingBar) findViewById(R.id.KitaDetailsRatingBar);
        resultListView = (ListView) findViewById(R.id.result_listview);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kita_details);
        initViews();

        Bundle b = this.getIntent().getExtras();

        if (b != null) {
            Kita kita = (Kita)b.get("kita");
            displayKita(kita);
        }
    }

    private void displayKita(Kita kita) {


        if (kita == null) {
            return;
        }

        kitaDetailsName.setText(kita.getName());

        if (kita.getAvgRating() != null) {
            kitaDetailsEvaluation.setText(kita.getAvgRating().toString());
        } else {
            kitaDetailsEvaluation.setText("");
        }

        if(kita.getCosts() != null) {
            kitaDetailsCostsData.setText(kita.getCosts().toString());
        } else
        {
            kitaDetailsCostsData.setText("");
        }

        if(kita.getOpeningHours() != null) {
            kitaDetailsOpenhours.setText(kita.getOpeningHours().getText());
        } else

        {
            kitaDetailsOpenhours.setText("");
        }


        kitaDetailsManagement.setText(kita.getManagement());
        kitaDetailsConfessionData.setText(kita.getConfession().getText());
        kitaDetailsAgeData.setText(kita.getMinAge() + " - " + kita.getMaxAge());
        kitaDetailsAboutData.setText(kita.getAbout());
    }

    //////////////////////////////////////////////////

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
