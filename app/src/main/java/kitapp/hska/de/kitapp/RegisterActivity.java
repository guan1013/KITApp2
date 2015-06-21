package kitapp.hska.de.kitapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kitapp.hska.de.kitapp.domain.AppUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editPassword;
    private EditText editPasswordRepeat;

    private Button buttonRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextEmail = (EditText) findViewById(R.id.registerEditTextEmail);
        editTextName = (EditText) findViewById(R.id.registerEditTextName);
        editPassword = (EditText) findViewById(R.id.registerEditTextPassword);
        editPasswordRepeat = (EditText) findViewById(R.id.registerEditTextPasswordRepeat);

        buttonRegister = (Button) findViewById(R.id.registerButtonRegister);

        buttonRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!editPassword.getText().equals(editPasswordRepeat.getText())) {
                    toast("Passwörter stimmen nicht überein!");
                }
                else {
                    AppUser user = new AppUser();

                    user.setEmail(editTextEmail.getText().toString());
                    user.setName(editTextName.getText().toString());
                    user.setPassword(editPassword.getText().toString());

                    //TODO: User Objekt an backend schicken
                }
            }
        });


    }

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
