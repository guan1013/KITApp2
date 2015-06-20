package kitapp.hska.de.kitapp;

import android.location.LocationListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.CommentsAdapter;
import kitapp.hska.de.kitapp.domain.Comment;
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
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kita_details);
        initViews();

        ListView listViewResult = (ListView) findViewById(R.id.result_listview);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateInString = "07/06/2013";

        Date date = null;

        try {

            date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Comment comment1 = new Comment("Hans Peter", "Diese Kita is definitiv zu empfehlen! Super freundliche Erzieherinnen mit kurzen Miniröcken. Da holt Mann sein Kind gerne ab!", date);
        Comment comment2 = new Comment("Franziska Hart", "Geht mal garnicht. Überall ist es dreckig und es stinkt. Auf keinen Fall würde ich mein Kind in so eine Müllhalde schicken.", date);
        Comment comment3 = new Comment("Hans Peter", "Diese Kita is definitiv zu empfehlen! Super freundliche Erzieherinnen mit kurzen Miniröcken. Da holt Mann sein Kind gerne ab!", date);

        List<Comment> comments = new ArrayList<>();

        comments.add(comment1);
        comments.add(comment2);
        comments.add(comment3);

        CommentsAdapter resultAdapter = new CommentsAdapter(this, R.layout.comment_item_layout, comments);

        listViewResult.setAdapter(resultAdapter);

        //////////////////////////////////////////////////

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
        kitaDetailsCostsData = (TextView) findViewById(R.id.KitaDetailsCostsData);
        kitaDetailsOpenhours = (TextView) findViewById(R.id.KitaDetailsOpenhours);
        kitaDetailsManagement = (TextView) findViewById(R.id.KitaDetailsManagementData);
        kitaDetailsConfessionData = (TextView) findViewById(R.id.KitaDetailsConfessionData);
        kitaDetailsAgeData = (TextView) findViewById(R.id.KitaDetailsAgeData);
        kitaDetailsAboutData = (TextView) findViewById(R.id.KitaDetailsAboutData);

        kitaDetailsRatingbar = (RatingBar) findViewById(R.id.KitaDetailsRatingBar);

        //TODO: setText() für alle Attribute
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
