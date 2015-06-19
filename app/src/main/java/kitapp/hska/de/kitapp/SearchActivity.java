package kitapp.hska.de.kitapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.ToggleButton;


public class SearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initViews();

        editTextCircuit.setText("0");
        seekBarCircuit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            Integer progresValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progresValue = progress;
                //Toast.makeText(getApplicationContext(), "Changing seekBarCircuit's progress", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Started tracking seekBarCircuit", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                editTextCircuit.setText(progresValue.toString());
                //Toast.makeText(getApplicationContext(), "Stopped tracking seekBarCircuit", Toast.LENGTH_SHORT).show();
            }
        });

        ((RadioGroup) findViewById(R.id.search_radiogroup_cost)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });

        cost = 0;


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private int circuiteValue;


    EditText editTextCity;
    SeekBar seekBarCircuit;
    EditText editTextCircuit;
    ToggleButton toggleButtonAge1;
    ToggleButton toggleButtonAge2;
    ToggleButton toggleButtonAge3;
    int cost;
    public void sendSearch(View button) {

        String city = editTextCity.getText().toString();
        String circuit = editTextCircuit.getText().toString();

        Boolean age1 = this.toggleButtonAge1.isChecked();
        Boolean age2 = this.toggleButtonAge2.isChecked();
        Boolean age3 = this.toggleButtonAge3.isChecked();

        int minAge = 0;
        int maxAge = 99;
        if (age1) {
            minAge = 0;
            maxAge = 1;
        }
        if (age2) {
            if (!age1) {
                minAge = 1;
            }
            maxAge = 3;
        }
        if (age3) {
            if(!age1 && !age2) {
                minAge = 3;
            }
            maxAge = 99;
        }

    }

    private void initViews() {
        this.editTextCity = (EditText) findViewById(R.id.search_edittext_city);
        this.seekBarCircuit = (SeekBar) findViewById(R.id.search_seekbar_circuit);
        this.editTextCircuit = (EditText) findViewById(R.id.search_edittext_circuit);
        this.toggleButtonAge1 = (ToggleButton) findViewById(R.id.search_togglebutton_age_1);
        this.toggleButtonAge2 = (ToggleButton) findViewById(R.id.search_togglebutton_age_2);
        this.toggleButtonAge3 = (ToggleButton) findViewById(R.id.search_togglebutton_age_3);

    }

    public void onToggle(View button) {
        ((RadioGroup)button.getParent()).check(button.getId());

        if(button.getId() == R.id.search_togglebutton_cost_1) {
            cost = 200;
        } else if(button.getId() == R.id.search_togglebutton_cost_2) {
            cost = 350;
        } else {
            cost = 0;
        }
    }
}
