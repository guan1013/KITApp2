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
import android.widget.Toast;

import org.apache.http.StatusLine;

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.services.AppUserService;
import kitapp.hska.de.kitapp.util.LoginResult;


public class LoginActivity extends ActionBarActivity {

    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonLogin;
    private Button buttonRegister;

    private AppUserService.AppUserServiceBinder appUserServiceBinder;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            LoginActivity.this.appUserServiceBinder = ((AppUserService.AppUserServiceBinder) service);
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
        setContentView(R.layout.activity_login);

        editTextEmail = (EditText) findViewById(R.id.editTextLoginEmail);
        editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

        buttonRegister = (Button) findViewById(R.id.buttonLoginRegister);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppUser userToLogin = new AppUser();
                userToLogin.setEmail(editTextEmail.getText().toString());
                userToLogin.setPassword(editTextPassword.getText().toString());

                LoginResult status = appUserServiceBinder.login(userToLogin);

                if(status != null) {
                    toast("Login result: " + status.getReasonPhrase() + " (" + status.getStatusCode() + ")");

                    System.out.println(status.getAppUser());

                    if(status.getStatusCode() == 200) {
                        if(status.getAppUser() != null)
                        toast("Hallo, " + status.getAppUser().getName() + "!");
                        Intent i = new Intent(getApplicationContext(),MainActivity.class);
                        i.putExtra("login",status);
                        startActivity(i);
                    }
                }
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    private void toast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
