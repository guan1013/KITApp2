package kitapp.hska.de.kitapp.client;

import android.util.Base64;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;

import kitapp.hska.de.kitapp.exceptions.WebserviceCallException;
import kitapp.hska.de.kitapp.util.LoginResult;

/**
 * Created by Amin on 22.06.2015.
 */
public class WebserviceClient {

    private static final String HEADER_CONTENT_TYPE = "Content-Type";

    private static final String HEADER_AUTHORIZATION = "Authorization";

    private static final String HEADER_COOKIE = "Cookie";

    private static final String HEADER_COOKIE_VALUE_PREFIX = "JSESSIONID=";

    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    private LoginResult loginResult;

    private String entity = null;

    public HttpResponse callWebservice(String url, HttpMethod method) throws IOException, WebserviceCallException {
        return callWebservice(url, method, Authentication.NONE);
    }

    public HttpResponse callWebservice(String url, HttpMethod method, Authentication authentication) throws IOException, WebserviceCallException {

        // Initialize http client and prepare HTTP-Request
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();

        // Request which will be executed
        HttpUriRequest request = null;

        // Specify request
        if (method == HttpMethod.GET) {
            request = new HttpGet(url);
        } else if (method == HttpMethod.POST) {
            request = new HttpPost(url);
            if (entity != null) {
                HttpPost httpPost = (HttpPost) request;
                httpPost.setEntity(new StringEntity(entity));
                httpPost.setHeader(HEADER_CONTENT_TYPE, DEFAULT_CONTENT_TYPE);
            }

        } else {
            throw new WebserviceCallException("HTTP-Method " + method + " is not configured to use.");
        }

        if (request != null) {

            // Set header for basic authentication
            if (authentication == Authentication.BASIC) {
                request.setHeader(HEADER_AUTHORIZATION, getB64Auth(loginResult.getAppUser().getEmail(), loginResult.getAppUser().getPassword()));
            }

            // Set header for using session cookie authentication
            if (authentication == Authentication.COOKIE) {
                request.setHeader(HEADER_COOKIE, HEADER_COOKIE_VALUE_PREFIX + loginResult.getCookie());
            }

            // EXECUTE THE REQUEST
            HttpResponse response = httpClient.execute(request);

            // Log TODO: Remove std output and add logger
            System.out.println("HTTP-REQUEST ("
                    + method
                    + ") FOR "
                    + url
                    + " (Response: "
                    + response.getStatusLine().getReasonPhrase()
                    + " (" + response.getStatusLine().getStatusCode()
                    + "))");

            if (entity != null && method == HttpMethod.POST) {
                System.out.println(" - ENTITY: " + entity);
            }

            return response;

        } else {
            throw new WebserviceCallException("Request to execute was null");
        }
    }

    public enum HttpMethod {
        GET, POST
    }

    public enum Authentication {
        NONE, BASIC, COOKIE
    }

    private String getB64Auth(String login, String pass) {
        String source = login + ":" + pass;
        String ret = "Basic " + Base64.encodeToString(source.getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
        return ret;
    }

    public static WebserviceClient create() {
        return new WebserviceClient();
    }

    public WebserviceClient setLoginResult(LoginResult loginResult) {
        this.loginResult = loginResult;
        return this;
    }

    public WebserviceClient setEntity(String entity) {
        this.entity = entity;
        return this;
    }
}
