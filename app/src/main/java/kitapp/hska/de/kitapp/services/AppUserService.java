package kitapp.hska.de.kitapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.domain.Kita;

/**
 * Created by Amin on 21.06.2015.
 */
public class AppUserService extends Service {

    private final static String BACKEND_URL = "http://ebusiness-kitapp-backend.herokuapp.com/";
    private final static String APP_USER_PARAM = "appuser";
    private final static String PATH_LOGIN = "/server";

    private AppUserServiceBinder binder = new AppUserServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class AppUserServiceBinder extends Binder {

        private boolean isLoggedIn = false;



        public void createAppUser(AppUser appUser) throws InterruptedException, ExecutionException, TimeoutException {

            AsyncTask<AppUser, Void, String> appUserTask = new AsyncTask<AppUser, Void, String>() {
                @Override
                protected String doInBackground(AppUser... params) {

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

                        HttpClient httpClient = new DefaultHttpClient();
                        HttpPost postAction = new HttpPost(BACKEND_URL + APP_USER_PARAM);
                        postAction.addHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                        postAction.setEntity(new StringEntity(request));
                        String text = null;

                        HttpResponse response = httpClient.execute(postAction);
                        HttpEntity entity = response.getEntity();

                        if(response.getStatusLine().getStatusCode() == 200) {

                        }
                        text = getASCIIContentFromEntity(entity);

                        System.out.println(text);

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                    return null;
                }
            };

            appUserTask.execute(appUser);
            appUserTask.get(5L, TimeUnit.SECONDS);
        }

        public StatusLine login(AppUser appUser) {

            AsyncTask<AppUser, Void, StatusLine> appUserTask = new AsyncTask<AppUser, Void, StatusLine>() {
                @Override
                protected StatusLine doInBackground(AppUser... params) {

                    if (params == null || params.length == 0) {
                        return null;
                    }

                    AppUser appUser = params[0];

                    System.out.println("Try to login user: " + appUser);


                    try {
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpContext localContext = new BasicHttpContext();

                        String url = BACKEND_URL + PATH_LOGIN;
                        HttpGet httpGet = new HttpGet(url);

                        httpGet.setHeader("Authorization", getB64Auth(appUser.getEmail(),appUser.getPassword()));
                        String text = null;

                        System.out.println(getB64Auth(appUser.getEmail(),appUser.getPassword()));

                        HttpResponse response = httpClient.execute(httpGet, localContext);

                        HttpEntity entity = response.getEntity();

                        System.out.println("RESPONSE:");
                        System.out.println(response.getStatusLine().getStatusCode() + ": " + response.getStatusLine().getReasonPhrase());

                        return response.getStatusLine();


                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };

            try {
                appUserTask.execute(appUser);
                StatusLine result = appUserTask.get(5L, TimeUnit.SECONDS);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        private String getB64Auth (String login, String pass) {
            String source=login+":"+pass;
            String ret="Basic "+Base64.encodeToString(source.getBytes(),Base64.URL_SAFE|Base64.NO_WRAP);
            return ret;
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
