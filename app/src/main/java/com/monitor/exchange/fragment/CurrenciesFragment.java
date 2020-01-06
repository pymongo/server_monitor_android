package com.monitor.exchange.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.monitor.exchange.Constant;
import com.monitor.exchange.R;
import com.monitor.exchange.adapter.CurrencyAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;


public class CurrenciesFragment extends Fragment {

  private CurrencyAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    // return super.onCreateView(inflater, container, savedInstanceState);
    return inflater.inflate(R.layout.items_container, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    // getView() works only after onCreateView()
    Activity activity = getActivity();
    assert activity != null;
    Context context = activity.getApplicationContext();
    List<HashMap<String, String>> currencies = new ArrayList<>();
    adapter = new CurrencyAdapter(context, currencies);
    ListView listView = Objects.requireNonNull(getView()).findViewById(R.id.list_view);
    listView.setAdapter(adapter);

    RequestQueue requestQueue = Volley.newRequestQueue(context);
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
      Constant.HOME_API, null, new Response.Listener<JSONObject>() {

      @Override
      public void onResponse(JSONObject response) {
        try {
          JSONArray jsonArray = response.getJSONArray("all_currencies");
          Log.i(Constant.LOG_TAG, jsonArray.toString(2));
          ArrayList<HashMap<String, String>> currenciesList = new ArrayList<>();
          for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject currencyHash = jsonArray.getJSONObject(i);
            HashMap<String, String> currencyMap = new HashMap<>();
            currencyMap.put("id", currencyHash.getString("id"));
            currencyMap.put("code", currencyHash.getString("code"));
            currencyMap.put("icon", currencyHash.getString("icon"));
            currenciesList.add(currencyMap);
          }
          adapter.updateCurrencies(currenciesList);
        } catch (JSONException e) {
          e.printStackTrace();
        }
      }
    }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError error) {
      }
    });
    requestQueue.add(jsonObjectRequest);
  }
}
