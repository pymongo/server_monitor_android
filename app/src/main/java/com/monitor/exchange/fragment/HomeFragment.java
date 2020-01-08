package com.monitor.exchange.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.monitor.exchange.Constant;
import com.monitor.exchange.R;
import com.monitor.exchange.adapter.DetailAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class HomeFragment extends Fragment {

  private final static String TAG = "HomeFragment";
  private ArrayList<ArrayList<String>> data;
  private DetailAdapter adapter;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.items_container, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    final Activity activity = getActivity();
    assert activity != null;
    Context context = activity.getApplicationContext();
    data = new ArrayList<>();
    adapter = new DetailAdapter(context, data);
    TextView banner = view.findViewById(R.id.banner);
    banner.setText(getResources().getString(R.string.title_production_server));
    ListView listView = view.findViewById(R.id.list_view);
    listView.setAdapter(adapter);

    OkHttpClient httpClient = new OkHttpClient.Builder()
      .connectTimeout(Constant.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
      .writeTimeout(Constant.IO_TIMEOUT, TimeUnit.SECONDS)
      .readTimeout(Constant.IO_TIMEOUT, TimeUnit.SECONDS)
      .build();
    Request request = new Request.Builder()
      .url(Constant.PRODUCTION_SERVER_MONITOR_API)
      .build();

    try {
      httpClient.newCall(request).enqueue(new Callback() {
        @Override
        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
          try {
            String responseString = Objects.requireNonNull(response.body()).string();
            JSONObject JsonResponse = new JSONObject(responseString);
            Log.i(TAG, JsonResponse.toString(2));
            JSONArray jsonArray = JsonResponse.getJSONArray("data");
            Log.i(TAG, jsonArray.toString(2));
            for (int i = 0; i < jsonArray.length(); i++) {
              JSONArray jsonArrayInner = jsonArray.getJSONArray(i);
              ArrayList<String> detail = new ArrayList<>();
              detail.add(jsonArrayInner.getString(0));
              detail.add(jsonArrayInner.getString(1));
              data.add(detail);
            }
            // Only the original thread can update view
            activity.runOnUiThread(new Runnable() {
              @Override
              public void run() {
                adapter.updateCurrencies(data);
              }
            });
          } catch (Exception e) {
            e.printStackTrace();
          }
        }

        @Override
        public void onFailure(@NotNull Call call, @NotNull IOException e) {

        }

      });
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
