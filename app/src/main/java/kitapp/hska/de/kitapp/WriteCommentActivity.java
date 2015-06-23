package kitapp.hska.de.kitapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.domain.Evaluation;
import kitapp.hska.de.kitapp.domain.Kita;
import kitapp.hska.de.kitapp.services.EvaluationService;
import kitapp.hska.de.kitapp.services.KitaService;
import kitapp.hska.de.kitapp.util.Constants;
import kitapp.hska.de.kitapp.util.LoginResult;


public class WriteCommentActivity extends ActionBarActivity {

    private EvaluationService.EvaluationServiceBinder evaluationServiceBinder;

    private Kita kitaToEvaluate = null;

    private LoginResult loggedInUser = null;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            WriteCommentActivity.this.evaluationServiceBinder = ((EvaluationService.EvaluationServiceBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        Intent i = new Intent(this, EvaluationService.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
        super.onStart();
    }

    public void sendComment(View button) {

        String text = editTextComment.getText().toString();
        Double rating = (double) ratingBarEvaluation.getRating();

        Evaluation query = new Evaluation();
        query.setAuthorIdx(loggedInUser.getAppUser().getId());
        query.setKitaIdx(kitaToEvaluate.getId());
        query.setText(text);
        query.setRating(rating);

        Intent myIntent = new Intent(this, KitaDetailsActivity.class);
        startActivity(myIntent);
    }

    private TextView textViewName;
    private EditText editTextComment;
    private RatingBar ratingBarEvaluation;
    private Button send;

    private void initViews() {

        this.textViewName = (TextView) findViewById(R.id.comment_write_textview_name);
        this.editTextComment = (EditText) findViewById(R.id.comment_write_edittext_text);
        this.ratingBarEvaluation = (RatingBar) findViewById(R.id.comment_write_ratingbar_rating);
        this.send = (Button) findViewById(R.id.commentWriteButton);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);

        initViews();


        kitaToEvaluate = (Kita) this.getIntent().getExtras().get("kita");
        loggedInUser = (LoginResult) this.getIntent().getExtras().get(Constants.EXTRAS_KEY_LOGIN);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = editTextComment.getText().toString();
                Double rating = (double) ratingBarEvaluation.getRating();

                Evaluation query = new Evaluation();

                query.setAuthorIdx(loggedInUser.getAppUser().getId());
                query.setKitaIdx(kitaToEvaluate.getId());
                query.setText(text);
                query.setRating(rating);
                query.setAuthorName(loggedInUser.getAppUser().getName());

                try {
                    evaluationServiceBinder.createEvaluation(query);

                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    myIntent.putExtra("kita", kitaToEvaluate);
                    myIntent.putExtra(Constants.EXTRAS_KEY_RELOAD_KITA, Boolean.TRUE);
                    myIntent.putExtra(Constants.EXTRAS_KEY_LOGIN, loggedInUser);
                    startActivity(myIntent);
                } catch (Exception e) {
                    e.printStackTrace();
                    toast(e.getClass().getName());
                }
            }
        });


        if (kitaToEvaluate == null) {
            toast("No KITA to evaluate");
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }

        if (loggedInUser == null) {
            toast("You are not logged in");
            Intent i = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(i);
        }

        this.textViewName.setText(kitaToEvaluate.getName());
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
