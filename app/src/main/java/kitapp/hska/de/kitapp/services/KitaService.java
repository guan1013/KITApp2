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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        public Kita[] getKitaByCity(String city) throws InterruptedException, ExecutionException, TimeoutException {

            AsyncTask<String, Void, Kita[]> kitaFinderTask = new AsyncTask<String, Void, Kita[]>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(Kita[] kitas) {
                    super.onPostExecute(kitas);
                }

                @Override
                protected Kita[] doInBackground(String... params) {

                    if(params == null || params.length == 0) {
                        return null;
                    }

                    String city = params[0];


                    System.out.print("Search for city: " +city);


                    try {

                        HttpClient httpClient = new DefaultHttpClient();
                        HttpContext localContext = new BasicHttpContext();
                        String url = "http://ebusiness-kitapp-backend.herokuapp.com/kitas?city=" + URLEncoder.encode(city, "UTF-8");
                        HttpGet httpGet = new HttpGet(url);
                        String text = null;

                        HttpResponse response = httpClient.execute(httpGet, localContext);

                        HttpEntity entity = response.getEntity();
                        // Creates the json object which will manage the information received
                        GsonBuilder builder = new GsonBuilder();

                        // Register an adapter to manage the date types as long values
                        builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                            public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                return new Date(json.getAsJsonPrimitive().getAsLong());
                            }
                        });

                        Gson gson = builder.create();

                        text = getASCIIContentFromEntity(entity);

                        Kita[] kitas = gson.fromJson(text, Kita[].class);

                        System.out.println(text);

                        return kitas;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };

            kitaFinderTask.execute(city);
            Kita[] kitas = kitaFinderTask.get(5L, TimeUnit.SECONDS);

            // Do the shit here
            return kitas;
        }

        protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {

            InputStream in = entity.getContent();

            StringBuffer out = new StringBuffer();
            int n = 1;
            while (n > 0) {
                byte[] b = new byte[4096];

                n = in.read(b);

                if (n > 0) out.append(new String(b, 0, n));

            }

            return out.toString();

        }
    }


}