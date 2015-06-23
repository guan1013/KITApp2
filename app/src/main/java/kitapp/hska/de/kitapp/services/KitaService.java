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
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
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
import kitapp.hska.de.kitapp.client.WebserviceClient;
import kitapp.hska.de.kitapp.domain.Kita;
import kitapp.hska.de.kitapp.domain.SearchQuery;
import kitapp.hska.de.kitapp.util.Constants;
import kitapp.hska.de.kitapp.util.LoginResult;

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

                    if (params == null || params.length == 0) {
                        return null;
                    }

                    String city = params[0];

                    try {

                        String url = Constants.BACKEND_URL + Constants.PATH_KITAS + "?" + Constants.PARAM_KITA_CIY + URLEncoder.encode(city, "UTF-8");

                        HttpResponse response = WebserviceClient.create().callWebservice(url, WebserviceClient.HttpMethod.GET);

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
                        Kita[] kitas = gson.fromJson(new InputStreamReader(entity.getContent()), Kita[].class);

                        return kitas;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };

            kitaFinderTask.execute(city);
            Kita[] kitas = kitaFinderTask.get(5L, TimeUnit.SECONDS);

            // Shit is done here
            return kitas;
        }

        public Kita[] getKitaBySearchQuery(SearchQuery query) throws InterruptedException, ExecutionException, TimeoutException {

            AsyncTask<SearchQuery, Void, Kita[]> kitaFinderTask = new AsyncTask<SearchQuery, Void, Kita[]>() {
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                }

                @Override
                protected void onPostExecute(Kita[] kitas) {
                    super.onPostExecute(kitas);
                }

                @Override
                protected Kita[] doInBackground(SearchQuery... params) {

                    if (params == null || params.length == 0) {
                        return null;
                    }

                    // Creates the json object which will manage the information received
                    GsonBuilder builder = new GsonBuilder();

                    // Register an adapter to manage the date types as long values
                    builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                        public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return new Date(json.getAsJsonPrimitive().getAsLong());
                        }
                    });

                    Gson gson = builder.create();

                    String request = gson.toJson(params[0]);

                    try {

                        String url = Constants.BACKEND_URL + Constants.PATH_KITAS + Constants.PATH_SEARCH;

                        HttpResponse response = WebserviceClient
                                .create()
                                .setEntity(request)
                                .callWebservice(url, WebserviceClient.HttpMethod.POST);
                        HttpEntity entity = response.getEntity();


                        Kita[] kitas = gson.fromJson(new InputStreamReader(entity.getContent()), Kita[].class);

                        return kitas;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };

            kitaFinderTask.execute(query);
            Kita[] kitas = kitaFinderTask.get(5L, TimeUnit.SECONDS);

            // Shit is done here
            return kitas;
        }

        public Kita getKityById(Long id) throws InterruptedException, ExecutionException, TimeoutException {
            AsyncTask<Long, Void, Kita> kitaFinderTask = new AsyncTask<Long, Void, Kita>() {
                @Override
                protected Kita doInBackground(Long... params) {

                    Long id = params[0];

                    String url = Constants.BACKEND_URL + Constants.PATH_KITAS + Constants.PATH_KITA + "?" + Constants.PARAM_KITA_ID + id;

                    try {
                        HttpResponse response = WebserviceClient.create().callWebservice(url, WebserviceClient.HttpMethod.GET);
                        if (response.getStatusLine().getStatusCode() == 200) {

                            // Creates the json object which will manage the information received
                            GsonBuilder builder = new GsonBuilder();

                            // Register an adapter to manage the date types as long values
                            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                    return new Date(json.getAsJsonPrimitive().getAsLong());
                                }
                            });

                            Gson gson = builder.create();

                            Kita kita = gson.fromJson(new InputStreamReader(response.getEntity().getContent()), Kita.class);

                            return kita;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return null;
                }
            };

            kitaFinderTask.execute(id);
            Kita kita = kitaFinderTask.get(Constants.TIME_OUT, TimeUnit.SECONDS);
            return kita;
        }
    }

}