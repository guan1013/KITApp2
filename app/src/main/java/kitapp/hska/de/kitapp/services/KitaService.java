package kitapp.hska.de.kitapp.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 */
public class KitaService extends IntentService {

        public static final String SERVICE_NAME = "KITAService";
        int duration = Toast.LENGTH_SHORT;



        public KitaService() {
                super(SERVICE_NAME);
        }

        @Override
        protected void onHandleIntent(Intent intent) {

                System.out.println("Start..");

                Toast.makeText(getApplicationContext(),"KitaService start...",Toast.LENGTH_LONG).show();

                HttpClient client = new DefaultHttpClient();


        }
}
