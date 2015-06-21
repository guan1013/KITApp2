package kitapp.hska.de.kitapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.services.AppUserService;
import kitapp.hska.de.kitapp.services.KitaService;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextEmail;
    private EditText editTextName;
    private EditText editPassword;
    private EditText editPasswordRepeat;

    private Button buttonRegister;

    private AppUserService.AppUserServiceBinder appUserServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            RegisterActivity.this.appUserServiceBinder = ((AppUserService.AppUserServiceBinder) service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onStart() {
        Intent i = new Intent(this, AppUserService.class);
        bindService(i, serviceConnection, BIND_AUTO_CREATE);
        super.onStart();
    }


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

                // Validate user input
                if(editTextName.getText().toString().equals(""))
                {
                    toast("Bitte Namen angeben!");
                    return;
                }

                if(editTextEmail.getText().toString().equals("")) {
                    toast("Bitte E-Mail angeben!");
                    return;
                }

                if (!editPassword.getText().toString().equals(editPasswordRepeat.getText().toString())) {
                    System.out.println(editPassword.getText());
                    System.out.println(editPasswordRepeat.getText());
                    toast("Passwörter stimmen nicht überein!");
                    return;
                }

                if(editPassword.getText().toString().length() < 8) {
                    toast("Passwort muss mindestens 8 Zeichen haben!");
                }


                AppUser user = new AppUser();

                user.setEmail(editTextEmail.getText().toString());
                user.setName(editTextName.getText().toString());
                user.setPassword(editPassword.getText().toString());

                try
                {
                    appUserServiceBinder.createAppUser(user);

                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                }catch (Exception e)
                {
                    toast(e.getClass().getName());
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
