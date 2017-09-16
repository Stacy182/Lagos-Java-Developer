package com.example.android.stacy;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String tag = MainActivity.class.getSimpleName();
    private static final String url = "https://api.github.com/search/users?q=language%3Ajava+location%3Alagos+followers:%3E5";
    private List<getData> list = new ArrayList<getData>();
    private ListView mListView;
    private ListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.listView);
        adapter = new ListViewAdapter(this, list);
        mListView.setAdapter(adapter);


        JsonObjectRequest jsonRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            try {

                                 response.getInt("total_count");
                                response.getBoolean("incomplete_results");
                                JSONArray jsonArray = response.getJSONArray("items");

                              for (int i = 0; i < jsonArray.length(); i++) {

                                  JSONObject jsonObject = jsonArray.getJSONObject(i);
                                  getData dataSet = new getData();

                                  dataSet.setName(jsonObject.getString("login"));
                                  dataSet.set_Id(jsonObject.getInt( "id"));
                                  dataSet.setImage(jsonObject.getString("avatar_url"));
                                  dataSet.setgravatar(jsonObject.getString( "gravatar_id"));
                                  dataSet.seturl(jsonObject.getString( "url"));
                                  dataSet.sethtml(jsonObject.getString(  "html_url"));
                                  list.add(dataSet);
                              }

                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AlertDialog.Builder add = new AlertDialog.Builder(MainActivity.this);
                add.setMessage(error.getMessage()).setCancelable(true);
                AlertDialog alert = add.create();
                alert.setTitle("Error!!!");
                alert.show();
            }
        });
        Controller.getInstance().addToRequestQueue(jsonRequest);







    }



}

