package kitapp.hska.de.kitapp.services;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;

import com.google.gson.Gson;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import kitapp.hska.de.kitapp.client.WebserviceClient;
import kitapp.hska.de.kitapp.domain.Evaluation;
import kitapp.hska.de.kitapp.exceptions.WebserviceCallException;
import kitapp.hska.de.kitapp.util.Constants;

/**
 * Created by Amin on 22.06.2015.
 */
public class EvaluationService extends Service {

    private EvaluationServiceBinder binder = new EvaluationServiceBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class EvaluationServiceBinder extends Binder {

        public  void createEvaluation(Evaluation evaluation) throws InterruptedException, ExecutionException, TimeoutException {

            AsyncTask<Evaluation,Void,Void> evaluationTask = new AsyncTask<Evaluation, Void, Void>() {
                @Override
                protected Void doInBackground(Evaluation... params) {

                    if (params == null || params.length == 0) {
                        return null;
                    }

                    // Get evaluation from the params
                    Evaluation evaluation1 = params[0];
                    Gson gson = new Gson();
                    String evaluationJson = gson.toJson(evaluation1);



                    // Build URL for webservice call
                    String url = Constants.BACKEND_URL + Constants.EVALUATION;

                    // Execute webservice call
                    try {
                        HttpResponse response = WebserviceClient
                                .create()
                                .setEntity(evaluationJson)
                                .callWebservice(url, WebserviceClient.HttpMethod.POST);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (WebserviceCallException e) {
                        e.printStackTrace();
                    }


                    return null;
                }
            };

            evaluationTask.execute(evaluation);
            evaluationTask.get(Constants.TIME_OUT, TimeUnit.SECONDS);
        }
    }
}
