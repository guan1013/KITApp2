package kitapp.hska.de.kitapp.services;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.HandlerThread;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import kitapp.hska.de.kitapp.MainActivity;
import kitapp.hska.de.kitapp.domain.Kita;

/**
 *
 */

public class KitaService extends Service {

    private final KitaServiceBinder binder = new KitaServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

   public class KitaServiceBinder extends Binder {
       public Kita getKitaByCity(String city) {

           AsyncTask<String,Void,Kita> kitaFinder = new AsyncTask<String, Void, Kita>() {
               @Override
               protected Kita doInBackground(String... params) {
                   return null;
               }
           };



           // Do the shit here
           return null;
       }
   }
}
