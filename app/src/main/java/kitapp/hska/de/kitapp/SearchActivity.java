package kitapp.hska.de.kitapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.ToggleButton;

import kitapp.hska.de.kitapp.domain.SearchQuery;


public class SearchActivity extends ActionBarActivity {

    /*
    <======================= VIEW ATTRIBUTES =======================>
     */
    private EditText editTextCity;
    private SeekBar seekBarCircuit;
    private EditText editTextCircuit;
    private ToggleButton toggleButtonAge1;
    private ToggleButton toggleButtonAge2;
    private ToggleButton toggleButtonAge3;
    private RatingBar ratingBarEvaluation;

    /*
    <======================= CLASS ATTRUIBUTES =======================>
     */
    private int cost;
    private String open;
    private int size;
    private int closing;

    /*
    <======================= PUBLIC METHODS =======================>
     */
    public void sendSearch(View button) {
        // Get UI input
        String city = editTextCity.getText().toString();
        int circuit = 0;

        try {

            circuit = Integer.parseInt(editTextCircuit.getText().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Integer[] minMaxAge = getMinMaxAge();
        int minAge = minMaxAge[0];
        int maxAge = minMaxAge[1];
        int cost = this.cost;
        String open = this.open;
        Double rating = 0.0;
        try {
            rating = (double) this.ratingBarEvaluation.getRating();
        } catch (Exception e) {
            e.printStackTrace();
        }
        int size = this.size;
        int closing = this.closing;

        SearchQuery query = new SearchQuery(city, circuit, minAge, maxAge, cost, open, rating, size, closing);

        Intent myIntent = new Intent(this, ResultActivity.class);
        startActivity(myIntent);

    }

    public void onToggleCost(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        String costString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText1))) {
            costString = getString(R.string.searchCostButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText2))) {
            costString = getString(R.string.searchCostButtonValue2);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchCostButtonText3))) {
            costString = getString(R.string.searchCostButtonValue3);
        }


        try {

            cost = Integer.parseInt(costString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onToggleSize(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        String sizeString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText1))) {
            sizeString = getString(R.string.searchSizeButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText2))) {
            sizeString = getString(R.string.searchSizeButtonValue2);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchSizeButtonText3))) {
            sizeString = getString(R.string.searchSizeButtonValue3);
        }

        try {
            size = Integer.parseInt(sizeString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onToggleClosing(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());

        String closingString = "0";
        if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchClosingButtonText1))) {
            closingString = getString(R.string.searchClosingButtonValue1);
        } else if (((ToggleButton) button).getText().toString().equals(getString(R.string.searchClosingButtonText2))) {
            closingString = getString(R.string.searchClosingButtonValue2);
        }

        try {
            closing = Integer.parseInt(closingString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void onToggleOpen(View button) {
        ((RadioGroup) button.getParent()).check(button.getId());
        open = ((ToggleButton) button).getText().toString();
    }

    /*
    <======================= PRIVATE METHODS =======================>
     */
    private Integer[] getMinMaxAge() {

        Integer[] minMaxAge = new Integer[2];

        Boolean age1 = this.toggleButtonAge1.isChecked();
        Boolean age2 = this.toggleButtonAge2.isChecked();
        Boolean age3 = this.toggleButtonAge3.isChecked();

        int minAge = 0;
        int maxAge = 99;
        if (age1) {


            String[] values = getString(R.string.searchAgeButtonValue1).split(",");

            try {
                minAge = Integer.parseInt(values[0]);
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (age2) {
            String[] values = getString(R.string.searchAgeButtonValue2).split(",");
            try {
                if (!age1) {
                    minAge = Integer.parseInt(values[0]);
                }
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (age3) {
            String[] values = getString(R.string.searchAgeButtonValue3).split(",");
            try {
                if (!age1 && !age2) {
                    minAge = Integer.parseInt(values[0]);
                }
                maxAge = Integer.parseInt(values[1]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        minMaxAge[0] = minAge;
        minMaxAge[1] = maxAge;

        return minMaxAge;
    }

    private void initViews() {
        this.editTextCity = (EditText) findViewById(R.id.search_edittext_city);
        this.seekBarCircuit = (SeekBar) findViewById(R.id.search_seekbar_circuit);
        this.editTextCircuit = (EditText) findViewById(R.id.search_edittext_circuit);
        this.toggleButtonAge1 = (ToggleButton) findViewById(R.id.search_togglebutton_age_1);
        this.toggleButtonAge2 = (ToggleButton) findViewById(R.id.search_togglebutton_age_2);
        this.toggleButtonAge3 = (ToggleButton) findViewById(R.id.search_togglebutton_age_3);
        this.ratingBarEvaluation = (RatingBar) findViewById(R.id.search_ratingbar_rating);

    }

    /*
    <======================= OVERRIDE METHODS =======================>
     */

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

        ((RadioGroup) findViewById(R.id.search_radiogroup_size)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });

        ((RadioGroup) findViewById(R.id.search_radiogroup_closing)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });

        ((RadioGroup) findViewById(R.id.search_radiogroup_open)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(final RadioGroup radioGroup, final int i) {
                for (int j = 0; j < radioGroup.getChildCount(); j++) {
                    final ToggleButton view = (ToggleButton) radioGroup.getChildAt(j);
                    view.setChecked(view.getId() == i);
                }
            }
        });
        cost = 0;
        open = "";
        size = 0;
        closing = 0;

        //this.searchButton.bringToFront();
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
        if (id == R.id.action_login) {
            Intent myIntent = new Intent(this, LoginActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_home) {
            Intent myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            return true;
        } else if (id == R.id.action_favorites) {

        } else if (id == R.id.action_news) {

        }


        return super.onOptionsItemSelected(item);
    }

}
