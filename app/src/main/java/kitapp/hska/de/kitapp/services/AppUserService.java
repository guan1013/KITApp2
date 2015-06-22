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

import org.apache.http.Header;
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

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kitapp.hska.de.kitapp.client.WebserviceClient;
import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.util.Constants;
import kitapp.hska.de.kitapp.util.LoginResult;

/**
 * Created by Amin on 21.06.2015.
 */
public class AppUserService extends Service {


    private AppUserServiceBinder binder = new AppUserServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class AppUserServiceBinder extends Binder {

        private LoginResult currentLoginResult = null;


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

                        String url = Constants.BACKEND_URL + Constants.PATH_APP_USER;
                        HttpResponse response = WebserviceClient
                                .create()
                                .setEntity(request)
                                .callWebservice(url, WebserviceClient.HttpMethod.POST);


                        HttpEntity entity = response.getEntity();


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

        public LoginResult login(AppUser appUser) {

            AsyncTask<AppUser, Void, LoginResult> appUserTask = new AsyncTask<AppUser, Void, LoginResult>() {
                @Override
                protected LoginResult doInBackground(AppUser... params) {

                    if (params == null || params.length == 0) {
                        return null;
                    }

                    AppUser appUser = params[0];

                    System.out.println("Try to login user: " + appUser);


                    try {

                        // Prepare url for login
                        String url = Constants.BACKEND_URL + Constants.PATH_LOGIN;

                        // Prepare login result
                        LoginResult loginResult = new LoginResult();
                        loginResult.setCookie("");
                        loginResult.setAppUser(appUser);

                        // Call the webservice to login
                        HttpResponse response = WebserviceClient
                                .create()
                                .setLoginResult(loginResult)
                                .callWebservice(url, WebserviceClient.HttpMethod.GET, WebserviceClient.Authentication.BASIC);

                        // Expected HTTP Code 200 (OK) if the authentication was successfull
                        if (response.getStatusLine().getStatusCode() == 200) {

                            // Read out header for session id
                            Header[] cookieHeader = response.getHeaders("Set-Cookie");


                            if (cookieHeader != null && cookieHeader.length > 0) {
                                Pattern p = Pattern.compile("JSESSIONID=([a-zA-Z0-9]+)");
                                Matcher m = p.matcher(cookieHeader[0].getValue());
                                while (m.find()) {
                                    loginResult.setCookie(m.group(1));
                                }
                            }


                            // Get the logged in user from the server
                            String url2 = Constants.BACKEND_URL + Constants.PATH_APP_USER + Constants.PATH_APP_USER_ID + "?" + Constants.PARAM_APP_USER_ID + appUser.getEmail();
                            HttpResponse responseAppUser = WebserviceClient
                                    .create()
                                    .setLoginResult(loginResult)
                                    .callWebservice(url2, WebserviceClient.HttpMethod.GET, WebserviceClient.Authentication.COOKIE);

                            // Creates the json object which will manage the information received
                            GsonBuilder builder = new GsonBuilder();

                            // Register an adapter to manage the date types as long values
                            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                    return new Date(json.getAsJsonPrimitive().getAsLong());
                                }
                            });

                            Gson gson = builder.create();

                            AppUser loadedAppUser = gson.fromJson(new InputStreamReader(responseAppUser.getEntity().getContent()), AppUser.class);
                            loginResult.setAppUser(loadedAppUser);
                            currentLoginResult = loginResult;
                        }

                        loginResult.setStatusLine(response.getStatusLine());

                        return loginResult;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
            };

            try {
                appUserTask.execute(appUser);
                LoginResult result = appUserTask.get(5L, TimeUnit.SECONDS);
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public LoginResult getCurrentLoginResult() {
            return currentLoginResult;
        }

        private String getB64Auth(String login, String pass) {
            String source = login + ":" + pass;
            String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
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
