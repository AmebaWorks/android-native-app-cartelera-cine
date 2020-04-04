package com.example.cristina.a5listview1.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.example.cristina.a5listview1.activities.A1_Splash;
import com.example.cristina.a5listview1.dataModel.Movie;
import com.google.gson.Gson;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class AsynkGet extends AsyncTask<Object,Object,String> {

    private HttpURLConnection urlConnection;
    private String URL;
    private String data = null;

    public AsynkGet(String url){
        this.URL = url;
    }

    public AsynkGet(String url, String data){
        this.URL = url;
        this.data = data;
    }

    @Override
    abstract protected void onPostExecute(String string);

    @Override
    protected String doInBackground(Object... params) {

        try {

            java.net.URL url = new URL(URL);
            urlConnection = (HttpURLConnection) url.openConnection();

            if(data != null)
            {
                urlConnection.setDoOutput(true);
                urlConnection.setFixedLengthStreamingMode(data.length());
                writeStream(urlConnection.getOutputStream(), this.data);
            }

            String responseString = readStream(urlConnection.getInputStream());
            System.out.print(responseString);
            return  responseString;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
        }
        return null;
    }

    private void writeStream(OutputStream out, String data)
    {
        try {
            out.write(data.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String readStream(InputStream is) {

        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = br.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return total.toString();
    }
}


