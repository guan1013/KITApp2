package kitapp.hska.de.kitapp;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;


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


    public void sendSearch(View button) {

        String city = editTextCity.getText().toString();
        String circuit = editTextCircuit.getText().toString();

    }

    private void initViews() {
        this.editTextCity = (EditText) findViewById(R.id.search_edittext_city);
        this.seekBarCircuit = (SeekBar) findViewById(R.id.search_seekbar_circuit);
        this.editTextCircuit = (EditText) findViewById(R.id.search_edittext_circuit);

    }

    public void onToggle(View button) {

    }
}
