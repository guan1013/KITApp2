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

import kitapp.hska.de.kitapp.domain.AppUser;
import kitapp.hska.de.kitapp.util.LoginResult;

/**
 * Created by Amin on 21.06.2015.
 */
public class AppUserService extends Service {

    private final static String BACKEND_URL = "http://ebusiness-kitapp-backend.herokuapp.com/";
    private final static String PATH_APP_USER = "appuser";
    private final static String PATH_LOGIN = "/server";
    private final static String PATH_APP_USER_ID = "/id";
    private final static String PARAM_APP_USER_ID = "email=";

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
                        HttpPost postAction = new HttpPost(BACKEND_URL + PATH_APP_USER);
                        postAction.addHeader(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
                        postAction.setEntity(new StringEntity(request));
                        String text = null;

                        HttpResponse response = httpClient.execute(postAction);
                        HttpEntity entity = response.getEntity();

                        if (response.getStatusLine().getStatusCode() == 200) {

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

                        // Prepare login for login
                        String url = BACKEND_URL + PATH_LOGIN;

                        // Initialize http client and prepare GET-Request
                        HttpClient httpClient = new DefaultHttpClient();
                        HttpContext localContext = new BasicHttpContext();
                        HttpGet httpGet = new HttpGet(url);

                        // Set seader for authorization. The value is base64-encoded email and password
                        httpGet.setHeader("Authorization", getB64Auth(appUser.getEmail(), appUser.getPassword()));

                        // Execute GET-request
                        HttpResponse response = httpClient.execute(httpGet, localContext);

                        LoginResult loginResult = new LoginResult();
                        loginResult.setCookie("");

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

                            /*
                            // Test login
                            HttpClient httpClientTest = new DefaultHttpClient();
                            HttpContext localContextTest = new BasicHttpContext();
                            HttpGet httpGetTest = new HttpGet(url);
                            HttpResponse responseTest = httpClientTest.execute(httpGetTest, localContextTest);
                            System.out.println("TEST LOGIN 1: " + responseTest.getStatusLine().getStatusCode() + " (Should be 403)");

                            HttpClient httpClientTest2 = new DefaultHttpClient();
                            HttpContext localContextTest2 = new BasicHttpContext();
                            HttpGet httpGetTest2 = new HttpGet(url);
                            httpGetTest2.setHeader("Cookie","JSESSIONID=" + loginResult.getCookie());
                            HttpResponse responseTest2 = httpClientTest2.execute(httpGetTest2, localContextTest2);
                            System.out.println("TEST LOGIN 2: " + responseTest2.getStatusLine().getStatusCode() + " (Should be 200)");

                            */

                            // Get the logged in user from the server
                            HttpClient httpClient2 = new DefaultHttpClient();
                            HttpContext httpContext = new BasicHttpContext();
                            String url2 = BACKEND_URL + PATH_APP_USER + PATH_APP_USER_ID + "?" + PARAM_APP_USER_ID + appUser.getEmail();
                            HttpGet httpGetAppUser = new HttpGet(url2);
                            httpGetAppUser.setHeader("Cookie","JSESSIONID=" + loginResult.getCookie());
                            HttpResponse responseAppUser = httpClient2.execute(httpGetAppUser, httpContext);

                            // Creates the json object which will manage the information received
                            GsonBuilder builder = new GsonBuilder();

                            // Register an adapter to manage the date types as long values
                            builder.registerTypeAdapter(Date.class, new JsonDeserializer<Date>() {
                                public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                                    return new Date(json.getAsJsonPrimitive().getAsLong());
                                }
                            });

                            Gson gson = builder.create();

                            AppUser loadedAppUser = gson.fromJson(new InputStreamReader(responseAppUser.getEntity().getContent()),AppUser.class);
                            loginResult.setAppUser(loadedAppUser);

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
