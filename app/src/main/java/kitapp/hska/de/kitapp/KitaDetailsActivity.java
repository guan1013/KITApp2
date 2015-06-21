package kitapp.hska.de.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Comment;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.EvaluationsAdapter;
import kitapp.hska.de.kitapp.domain.Evaluation;
import kitapp.hska.de.kitapp.domain.Kita;

public class KitaDetailsActivity extends AppCompatActivity {


    private final static String EVALUATIONS_BUNDLE_KEY = "evaluations";

    TextView kitaDetailsName;
    TextView kitaDetailsAddressData;
    TextView kitaDetailsCostsData;
    TextView kitaDetailsOpenhours;
    TextView kitaDetailsManagement;
    TextView kitaDetailsConfessionData;
    TextView kitaDetailsAgeData;
    TextView kitaDetailsAboutData;
    RatingBar kitaRatingBar;

    RatingBar kitaDetailsRatingbar;

    private ListView evaluationListView;

    private void initViews() {
        evaluationListView = (ListView) findViewById(R.id.evaluation_ListView);
        kitaDetailsAddressData = (TextView) findViewById(R.id.KitaDetailsAddressData);
        kitaDetailsName = (TextView) findViewById(R.id.KitaDetailsName);
        kitaDetailsCostsData = (TextView) findViewById(R.id.KitaDetailsCostsData);
        kitaDetailsOpenhours = (TextView) findViewById(R.id.KitaDetailsOpenhours);
        kitaDetailsManagement = (TextView) findViewById(R.id.KitaDetailsManagementData);
        kitaDetailsConfessionData = (TextView) findViewById(R.id.KitaDetailsConfessionData);
        kitaDetailsAgeData = (TextView) findViewById(R.id.KitaDetailsAgeData);
        kitaDetailsAboutData = (TextView) findViewById(R.id.KitaDetailsAboutData);
        kitaDetailsRatingbar = (RatingBar) findViewById(R.id.KitaDetailsRatingBar);
        kitaRatingBar = (RatingBar) findViewById(R.id.KitaDetailsRatingBar);

    }


    private List<Evaluation> getEvaluationList() {

        Bundle bundle = this.getIntent().getExtras();

        List<Evaluation> evaluations = new ArrayList<>();
        if (bundle != null) {
            try {
                evaluations = (ArrayList<Evaluation>) bundle.get(EVALUATIONS_BUNDLE_KEY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return evaluations;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kita_details);
        initViews();

        evaluationListView = (ListView) findViewById(R.id.evaluation_ListView);

        final List<Evaluation> evaluations = getEvaluationList();

        EvaluationsAdapter evaluationsAdapter = new EvaluationsAdapter(this,R.layout.evaluation_item_layout, evaluations);

        evaluationListView.setAdapter(evaluationsAdapter);

        Bundle b = this.getIntent().getExtras();

        if (b != null) {
            Kita kita = (Kita) b.get("kita");

            displayKita(kita);
        }
    }

    private void cleanUi() {
        kitaDetailsName.setText("");
        kitaDetailsAddressData.setText("");
        kitaDetailsOpenhours.setText("");
        kitaDetailsManagement.setText("");
        kitaDetailsConfessionData.setText("");
        kitaDetailsAgeData.setText("");
        kitaDetailsAboutData.setText("");
        kitaRatingBar.setRating(0.0f);
    }

    private void displayKita(Kita kita) {

        cleanUi();

        if (kita == null) {
            return;
        }

        // Set NAME of kita on ui
        kitaDetailsName.setText(kita.getName());

        // Set ADDRESS of kita on ui
        if (kita.getAddress() != null) {
            kitaDetailsAddressData.setText(
                    kita.getAddress().getStreet() + "\n" +
                            kita.getAddress().getZipcode() + " " +
                            kita.getAddress().getCity() + "\n" +
                            kita.getAddress().getPhone() + "\n" +
                            kita.getAddress().getEmail());
        }

        if (kita.getAvgRating() != null) {
            //kitaDetailsEvaluation.setText(kita.getAvgRating().toString());
            kitaRatingBar.setRating(kita.getAvgRating().floatValue());
        }

        if (kita.getCosts() != null) {
            kitaDetailsCostsData.setText(kita.getCosts().toString());
        }

        if (kita.getOpeningHours() != null) {
            kitaDetailsOpenhours.setText(kita.getOpeningHours().getText());
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
