package kitapp.hska.de.kitapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.domain.Evaluation;
import kitapp.hska.de.kitapp.domain.Kita;


public class WriteCommentActivity extends ActionBarActivity {

    public void sendComment(View button) {
        String name = "user";
        String text = editTextComment.getText().toString();
        Date date = new Date();
        Double evaluation = (double) ratingBarEvaluation.getRating();

        Evaluation query = new Evaluation(text,new AppUser(null,name,null,null,null), date, evaluation);

        Intent myIntent = new Intent(this, KitaDetailsActivity.class);
        startActivity(myIntent);
    }

    private TextView textViewName;
    private EditText editTextComment;
    private RatingBar ratingBarEvaluation;

    private void initViews() {

        this.textViewName = (TextView) findViewById(R.id.comment_write_textview_name);
        this.editTextComment = (EditText) findViewById(R.id.comment_write_edittext_text);
        this.ratingBarEvaluation = (RatingBar) findViewById(R.id.comment_write_ratingbar_rating);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        initViews();

        Kita kita = (Kita) this.getIntent().getExtras().get("kita");

        if(kita == null) {
            toast("No KITA to evaluate");
            Intent i= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }

        this.textViewName.setText(kita.getName());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_write_comment, menu);
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
    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
