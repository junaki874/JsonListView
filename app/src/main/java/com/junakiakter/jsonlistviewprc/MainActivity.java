package com.junakiakter.jsonlistviewprc;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

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
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.listvw);
        Jsontask jstl=new Jsontask();
        jstl.execute();

    }
    public class Jsontask extends AsyncTask<String,String, List<emplydemo>>{
          HttpURLConnection httpURLConnection=null;
           BufferedReader bufferedReader=null;

        @Override
        protected List<emplydemo> doInBackground(String... strings) {
            try {
                URL url= new URL("https://api.myjson.com/bins/ijl22");
                httpURLConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream= httpURLConnection.getInputStream();
                bufferedReader=new BufferedReader(new InputStreamReader(inputStream));
                StringBuffer stringBuffer= new StringBuffer();
                String line=" ";
                while((line=bufferedReader.readLine())!=null){
                    stringBuffer.append(line);
                }
                String jsonfile=stringBuffer.toString();
                JSONObject jsonObject= new JSONObject(jsonfile);
                JSONArray emplyarray=jsonObject.getJSONArray("Employee");
                List<emplydemo>demo= new ArrayList<>();

                for(int i=0; i<emplyarray.length(); i++) {
                    JSONObject arrayobject = emplyarray.getJSONObject(i);
                    emplydemo obj=new emplydemo();

                    obj.setName(arrayobject.getString("name"));
                    obj.setAge(arrayobject.getInt("age"));
                    obj.setPosition(arrayobject.getString("position"));
                    demo.add(obj);


                    //  return  stringBuffer.toString();
                }
                return demo;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }finally {

                try {
                    httpURLConnection.disconnect();
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<emplydemo> s) {
            super.onPostExecute(s);

            CustomAdapter customAdapter= new CustomAdapter(getApplicationContext(),R.layout.sample,s);
            listView.setAdapter(customAdapter);
        }
    }
}
