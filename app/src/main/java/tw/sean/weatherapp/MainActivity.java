package tw.sean.weatherapp;
/*
 *  getJson(View view)是取得台中市未來36小時天氣預告的Method
 *
 *
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


public class MainActivity extends AppCompatActivity {
    private TextView getJsonMesg;
    private String readLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getJsonMesg = findViewById(R.id.jsonTextView);

    }

    public void getJson(View view) {
        new Thread(){
            @Override
            public void run() {
                super.run();
                Log.v("sean","getJsonMesg : 台中市36小時天氣預測");
                try {
                    //建立傳輸資料的管路
                    URL url = new URL("https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-95628C85-DC04-4D02-A8D8-D8B324F58F88&format=JSON&locationName=%E8%87%BA%E4%B8%AD%E5%B8%82");
                    HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
                    conn.setDoInput(true);

                    conn.connect();

                    //建立接收資料的池子
                    InputStream inputStream = conn.getInputStream();
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    readLine = null; //清空
                    while ((readLine = bufferedReader.readLine()) != null) {
                        Log.v("sean", "=>" + readLine);
                    }
                    bufferedReader.close();
                    Log.v("sean", "getJson OK");

                    //Log.v("sean", "parseJSON OK");


                }catch (MalformedURLException e) {
                    Log.v("sean", "URL Exception : " + e.toString());
                }catch (IOException e){
                    Log.v("sean", "IO Exception : " + e.toString());
                }
            }
        }.start();

    }

//    private static void parseJSON (String readLine){
//
//        try{
//            JSONObject jO01 = new JSONObject(readLine);
//
//        }catch (Exception e){
//
//        }
//
//    }

}
