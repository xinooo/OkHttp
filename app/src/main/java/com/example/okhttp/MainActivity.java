package com.example.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    // 建立OkHttpClient3
    OkHttpClient client = new OkHttpClient().newBuilder().build();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 建立Request，設置連線資訊
        Request request = new Request.Builder().url("https://jsonplaceholder.typicode.com/posts/1").build();
        // 建立Call
        Call call = client.newCall(request);
        // 執行Call連線到網址
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // 連線失敗
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                // 連線成功，自response取得連線結果
                String result = response.body().string();
                Log.d("OkHttp result",result);

//                try {
//                    parseJSON(result);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }

    private void parseJSON(String s) throws JSONException {
        JSONArray jsonArray = new JSONArray(s);
        for (int i=0;i<jsonArray.length();i++){
            String ss = jsonArray.getJSONObject(i).getString("title");
            Log.d("title",ss);
        }
    }
}
