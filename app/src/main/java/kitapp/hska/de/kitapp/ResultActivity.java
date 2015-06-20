package kitapp.hska.de.kitapp;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.KitaResultAdapter;
import kitapp.hska.de.kitapp.model.KitaResult;


public class ResultActivity extends ActionBarActivity {

    private ListView resultListView;
    private LinearLayout resultLinearLayoutList;
    private LinearLayout resultLinaerLayoutMap;

    private void initViews() {
        resultListView = (ListView) findViewById(R.id.result_listview);
        resultLinearLayoutList = (LinearLayout) findViewById(R.id.result_linarlayout_list);
        resultLinaerLayoutMap = (LinearLayout) findViewById(R.id.result_linarlayout_map);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();

        resultListView = (ListView) findViewById(R.id.result_listview);

        KitaResult kita1 = new KitaResult("kita1", 3.5f, 13.0, "0172/30", "test1@email.com");
        KitaResult kita2 = new KitaResult("kita2", 5.0f, 50.2, "0173/40", "test2@mail.com");

        List<KitaResult> kitas = new ArrayList<>();

        kitas.add(kita1);
        kitas.add(kita2);
        KitaResultAdapter resultAdapter = new KitaResultAdapter(this, R.layout.kita_result_item_layout, kitas);
        resultListView.setAdapter(resultAdapter);


        ((RadioGroup) findViewById(R.id.result_radiogroup_buttons)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });


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

    public void onToggle(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());
        try {
            if (((ToggleButton) button).getText().toString().equals(getString(R.string.resultToggleButtonMap))) {
                resultLinaerLayoutMap.setVisibility(View.VISIBLE);
                resultLinearLayoutList.setVisibility(View.GONE);
            } else {
                resultLinaerLayoutMap.setVisibility(View.GONE);
                resultLinearLayoutList.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
