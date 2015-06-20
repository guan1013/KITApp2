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

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private final KitaServiceBinder binder = new KitaServiceBinder();

   public class KitaServiceBinder extends Binder {
       public Kita getKitaByCity(String city) {
           // Do the shit here
           return null;
       }
   }
}
