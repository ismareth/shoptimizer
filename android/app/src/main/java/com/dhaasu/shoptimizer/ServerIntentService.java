package com.dhaasu.shoptimizer;

import android.app.IntentService;
import android.content.Intent;
import android.content.IntentSender;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by ramitsuri on 9/25/16.
 */
public class ServerIntentService extends IntentService {

    public final static String MODE = "mode";
    public final static String OBJECT = "object";

    private String serviceURL = "70.171.44.98:1399/shoppinglist";

    public ServerIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        int mode = intent.getIntExtra(MODE, 1);
        String json = intent.getStringExtra(OBJECT);
        execute1(json);
    }

    private String execute1(String json) {

        try {
            URL url = new URL(serviceURL);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);

            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("Accept", "application/json");

            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));

            writer.write(json);
            writer.close();

            urlConnection.connect();
            InputStream stream = urlConnection.getInputStream();
            StringBuilder stringBuffer = new StringBuilder();
            if (stream == null) {
                return null;
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
            String inputLine;
            while ((inputLine = bufferedReader.readLine()) != null)
                stringBuffer.append(inputLine).append("\n");
            if (stringBuffer.length() == 0) {
                return null;
            }
            return stringBuffer.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
