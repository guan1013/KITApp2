package kitapp.hska.de.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class KitaDetailsActivity extends AppCompatActivity {

    ImageView im;

    public void biggerView(View v)
    {
        im=(ImageView)findViewById(R.id.selected);

        switch (v.getId())
        {
            case R.id.image1: im.setImageResource(R.drawable.im1);
                break;
            case R.id.image2: im.setImageResource(R.drawable.im2);
                break;
            case R.id.image3: im.setImageResource(R.drawable.im3);
                break;
            case R.id.image4: im.setImageResource(R.drawable.im4);
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kita_details);
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
