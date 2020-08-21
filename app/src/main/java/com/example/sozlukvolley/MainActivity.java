package com.example.sozlukvolley;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity  implements SearchView.OnQueryTextListener {
    Toolbar toolbar;
    RecyclerView rv;
 private ArrayList<Kelimeler> kelimelerArrayList;
    private  KelimelerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        rv = findViewById(R.id.rv);

        toolbar.setTitle("Kelime Ara");
        setSupportActionBar(toolbar);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

tumKelimeler();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView =(SearchView) menuItem.getActionView();
searchView.setOnQueryTextListener(this);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

System.out.println("Burasi calisti");
kelimeAra(newText);
        return false;
    }

    public void tumKelimeler () {
        String url ="http://kasimadalan.pe.hu/sozluk/tum_kelimeler.php";

        StringRequest istek = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                kelimelerArrayList = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kelimeler = jsonObject.getJSONArray("kelimeler");

                    for (int i = 0 ; i<kelimeler.length() ; i++) {
                         JSONObject k = kelimeler.getJSONObject(i);

                         int kelime_id = k.getInt("kelime_id");
                         String ingilizce = k.getString("ingilizce");
                         String  turkce = k.getString("turkce");

                         Kelimeler kelimelerListe = new Kelimeler(kelime_id,ingilizce,turkce);

                        kelimelerArrayList.add(kelimelerListe);
                    }

                    adapter = new KelimelerAdapter(MainActivity.this,kelimelerArrayList);
                    rv.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });
        Volley.newRequestQueue(MainActivity.this).add(istek);
    }

    public void kelimeAra (final String kelime) {
        String url ="http://kasimadalan.pe.hu/sozluk/kelime_ara.php";

        StringRequest istek = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                kelimelerArrayList = new ArrayList<>();

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray kelimeler = jsonObject.getJSONArray("kelimeler");

                    for (int i = 0 ; i<kelimeler.length() ; i++) {
                        JSONObject k = kelimeler.getJSONObject(i);

                        int kelime_id = k.getInt("kelime_id");
                        String ingilizce = k.getString("ingilizce");
                        String  turkce = k.getString("turkce");

                        Kelimeler kelimelerListe = new Kelimeler(kelime_id,ingilizce,turkce);

                        kelimelerArrayList.add(kelimelerListe);
                    }

                    adapter = new KelimelerAdapter(MainActivity.this,kelimelerArrayList);
                    rv.setAdapter(adapter);



                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {



            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map map = new HashMap();
                map.put("ingilizce",kelime);
                return map;
            }
        };

        Volley.newRequestQueue(MainActivity.this).add(istek);
    }







}