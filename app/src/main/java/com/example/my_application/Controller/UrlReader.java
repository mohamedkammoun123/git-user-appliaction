package com.example.my_application.Controller;
import java.util.logging.Logger;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlReader extends AsyncTask<String, Void,String> {
    Logger LOGGER = Logger.getLogger(UrlReader.class.getName());
    private String jsonGetRequest(String urlQueryString)  {
        String data ="";
        URL url = null;
        try {
            url = new URL(urlQueryString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = bufferedReader.readLine() )!= null){
                data = data + line;
            }
            if (!data.isEmpty()){
                JSONObject jsonObject = new JSONObject(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("response from url = "+urlQueryString+" is empty");
        }
        System.out.println("got non empty json response from url = "+urlQueryString);
        return data;

    }
    @Override
    protected String doInBackground(String... strings) {
        return jsonGetRequest(strings[0]);
    }

    public JSONObject getJsonObject(String url) throws JSONException {
        String data = doInBackground(url);
        if (data.isEmpty()){
            return null;
        }
        return new JSONObject(data);
    }

    public void getArrayJsonObject(String url){
        String data = doInBackground(url);
        LOGGER.info("List of jsons as string =");
        LOGGER.info(data);
    }
}

