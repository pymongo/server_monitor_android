package com.monitor.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends AppCompatActivity {

  final static String TAG = "monitorLog";

  RequestQueue requestQueue;
  // can't do nested upcasting ArrayList<HashMap> to List<Map>
  List<HashMap<String, String>> currencies;
  ListView listView;

  public void sendToast(String Message) {
    Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
    // menu.xml中的id必须与navigation_graph.xml中的ID保持一致
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,
      R.id.navigation_monitor, R.id.navigation_currencies, R.id.navigation_mine).build();
    NavController navController = Navigation.findNavController(this, R.id.navigation_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    NavigationUI.setupWithNavController(bottomNavigation, navController);
  }

  void temp() {
    currencies = new ArrayList<>();
    requestQueue = Volley.newRequestQueue(this);
    // [codeRiver]: 就算一行很长，方法的第一个参数也要写在第一行
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
      Constant.HOME_API, null, new Response.Listener<JSONObject>() {

      @Override
      public void onResponse(JSONObject response) {
        try {
          JSONArray jsonArray = response.getJSONArray("all_currencies");
          // [codeRiver]: 不要为了醒目而使用log.e
          Log.i(TAG, response.toString(2));
          for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currencyHash = jsonArray.getJSONObject(i);
            HashMap<String, String> map = new HashMap<>();
            map.put("id", currencyHash.getString("id"));
            map.put("code", currencyHash.getString("code"));
            map.put("iconUrl", currencyHash.getString("icon"));
            currencies.add(map);
          }
//          listView = findViewById(R.id.listView);
//          listView.setAdapter(new CurrencyAdapter(getApplicationContext(), currencies));
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
