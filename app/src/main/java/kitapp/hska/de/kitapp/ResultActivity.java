package kitapp.hska.de.kitapp;

import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.KitaResultAdapter;
import kitapp.hska.de.kitapp.model.KitaResult;


public class ResultActivity extends ActionBarActivity {

    private ListView resultListView;

    private void initViews() {
        resultListView = (ListView) findViewById(R.id.result_listview);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();

        ListView listViewResult = (ListView) findViewById(R.id.result_listview);

        KitaResult kita1 = new KitaResult("kita1", 3.5f, 13.0, "0172/30", "test1@email.com");
        KitaResult kita2 = new KitaResult("kita2", 5.0f, 50.2, "0173/40", "test2@mail.com");

        List<KitaResult> kitas = new ArrayList<>();

        kitas.add(kita1);
        kitas.add(kita2);

        KitaResultAdapter resultAdapter = new KitaResultAdapter(this, R.layout.kita_result_item_layout, kitas);

        listViewResult.setAdapter(resultAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
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
