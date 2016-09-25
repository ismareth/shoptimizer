package com.dhaasu.shoptimizer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.nanotasks.BackgroundWork;
import com.nanotasks.Completion;
import com.nanotasks.Tasks;

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
import java.util.ArrayList;

/**
 * Created by ramitsuri on 9/24/16.
 */
public class ServiceHelper {
    private Context context;

    public ServiceHelper(Context c){
        this.context = c;
    }

    private String serviceURL = "70.171.44.98:1399/shoppinglist";

    public String executeServiceCall(int mode, ArrayList<Item> items, String storeID) {
        final int newmode = mode;
        final ArrayList<Item> newItems = items;
        final String newStoreID = storeID;
        Tasks.executeInBackground(context, new BackgroundWork<String>() {
            @Override
            public String doInBackground() throws Exception {
                return execute(newmode, newItems, newStoreID); // expensive operation
            }
        }, new Completion<String>() {
            @Override
            public void onSuccess(Context context, String applications) {
                //add
            }

            @Override
            public void onError(Context context, Exception e) {

            }
        });

        return null;
    }

    private String execute(int newmode, ArrayList<Item> newItems, String newStoreID) {

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
            Object ob = new Object();
            ob.shoppingList = newItems;
            ob.storeID = newStoreID;
            Gson gson = new Gson();
            String json = gson.toJson(ob);
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

    public class Object{
        public ArrayList<Item> shoppingList;
        public String storeID;
    }
}
