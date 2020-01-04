package com.monitor.exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  final static String TAG = "== ";
  final static String API_URL = "http://v-api.testcadae.top/mobile_api/v2/home/initialize";

  RequestQueue requestQueue;
  // can't do nested upcasting ArrayList<HashMap> to List<Map>
  List<HashMap<String, String>> currencies;
  ListView listView;

  public void sendToast(CharSequence Message) {
    Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    currencies = new ArrayList<>();
    requestQueue = Volley.newRequestQueue(this);
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
      (Request.Method.GET, API_URL, null, new Response.Listener<JSONObject>() {

        @Override
        public void onResponse(JSONObject response) {
          try {
            JSONArray jsonArray = response.getJSONArray("all_currencies");
            Log.e(TAG, response.toString(2));
            for (int i = 0; i < jsonArray.length(); i++) {
              JSONObject currencyHash = jsonArray.getJSONObject(i);
              HashMap<String, String> map = new HashMap<>();
              map.put("id", currencyHash.getString("id"));
              map.put("code", currencyHash.getString("code"));
              map.put("iconUrl", currencyHash.getString("icon"));
              currencies.add(map);
            }
            listView = findViewById(R.id.listView);
            CurrencyAdapter currencyAdapter = new CurrencyAdapter(getApplicationContext(), currencies);
            listView.setAdapter(currencyAdapter);
          } catch (JSONException e) {
            e.printStackTrace();
            requestQueue.stop();
          }
        }
      }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
          // TODO: Handle error
        }
      });
    requestQueue.add(jsonObjectRequest);
  }
}
