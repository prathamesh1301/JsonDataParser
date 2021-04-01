package com.example.jsonparser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> colors;
    private List<String> category;
    private List<String> type;
    private List<String> rgba;
    private List<String> hex_code;
    private RecyclerAdapter adapter;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors=new ArrayList<>();
        category=new ArrayList<>();
        type=new ArrayList<>();
        rgba=new ArrayList<>();
        hex_code=new ArrayList<>();

        try{

            JSONObject obj=new JSONObject(loadJSONFromAsset());
            JSONArray jsonArray=obj.getJSONArray("colors");
            for(int i=0;i<jsonArray.length();i++){
                JSONObject current_obj=jsonArray.getJSONObject(i);
                colors.add(current_obj.getString("color"));
                category.add(current_obj.getString("category"));
                type.add(current_obj.getString("type"));
                JSONObject code_obj=current_obj.getJSONObject("code");
                rgba.add(code_obj.getString("rgba"));
                hex_code.add(code_obj.getString("hex"));
                Log.d("success",String.valueOf(i));


            }

        }catch (Exception e){
            e.printStackTrace();
            Log.d("Error",e.getMessage());
        }
        recyclerView=findViewById(R.id.recyclerView);
        adapter=new RecyclerAdapter(colors,category,type,rgba,hex_code,MainActivity.this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}