package kitapp.hska.de.kitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;

import java.util.ArrayList;
import java.util.List;

import kitapp.hska.de.kitapp.adapter.KitaResultAdapter;
import kitapp.hska.de.kitapp.domain.Kita;
import kitapp.hska.de.kitapp.util.Constants;
import kitapp.hska.de.kitapp.util.LoginResult;


public class ResultActivity extends ActionBarActivity implements OnMapReadyCallback {

    /*
    <======================= CONSTANTS =======================>
     */
    private final static String KITAS_BUNDLE_KEY = "kitas";
    private final static String KITA_BUNDLE_KEY = "kita";

    /*
    <======================= VIEW ATTRIBUTES =======================>
     */
    private ListView resultListView;
    private LinearLayout resultLinearLayoutList;
    private LinearLayout resultLinearLayoutMap;
    private MapFragment mapFragment;
      /*
    <======================= PUBLIC METHODS =======================>
     */

    public void onToggle(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());
        try {
            if (((ToggleButton) button).getText().toString().equals(getString(R.string.resultToggleButtonMap))) {
                resultLinearLayoutMap.setVisibility(View.VISIBLE);
                resultLinearLayoutList.setVisibility(View.GONE);

            } else {
                resultLinearLayoutMap.setVisibility(View.GONE);
                resultLinearLayoutList.setVisibility(View.VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    <======================= PRIVATE METHODS =======================>
     */

    private void initViews() {
        resultListView = (ListView) findViewById(R.id.result_listview);
        resultLinearLayoutList = (LinearLayout) findViewById(R.id.result_linarlayout_list);
        resultLinearLayoutMap = (LinearLayout) findViewById(R.id.result_linarlayout_map);
        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.result_fragment_map);
        mapFragment.getMapAsync(this);
    }

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void showDetail(Kita kita) {

        Bundle bundle = this.getIntent().getExtras();

        LoginResult loggedInUser = null;
        if (bundle != null) {
            try {
                loggedInUser = (LoginResult) bundle.get(Constants.EXTRAS_KEY_LOGIN);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        Intent intent = new Intent(this, KitaDetailsActivity.class);
        if (loggedInUser == null) {
            intent.putExtra(Constants.EXTRAS_KEY_LOGIN, loggedInUser);
        }
        intent.putExtra(KITA_BUNDLE_KEY, kita);
        startActivity(intent);
    }

    private List<Kita> getKitaList() {

        Bundle bundle = this.getIntent().getExtras();

        List<Kita> kitas = new ArrayList<>();
        if (bundle != null) {
            try {
                kitas = (ArrayList<Kita>) bundle.get(KITAS_BUNDLE_KEY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return kitas;

    }

    private void addResultListAdapter() {

        final List<Kita> kitas = getKitaList();
        KitaResultAdapter resultAdapter = new KitaResultAdapter(this, R.layout.kita_result_item_layout, kitas);
        resultListView.setAdapter(resultAdapter);
        resultListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showDetail(kitas.get(position));
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

    private void configGoogleMaps() {
    }
    /*
    <======================= OVERRIDE METHODS =======================>
     */

    @Override
    public void onMapReady(GoogleMap map) {
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.setMyLocationEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        initViews();
        resultListView = (ListView) findViewById(R.id.result_listview);
        addResultListAdapter();
        setOnCheckedListener(R.id.result_radiogroup_buttons);
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
        if (id == R.id.action_login) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_home) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_search) {
            Intent myIntent = new Intent(this, SearchActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_favorites) {

        } else if (id == R.id.action_news) {

        }
        return super.onOptionsItemSelected(item);
    }
}
