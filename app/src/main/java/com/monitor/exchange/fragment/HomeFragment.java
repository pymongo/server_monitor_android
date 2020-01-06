package com.monitor.exchange.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.monitor.exchange.R;

import java.util.Objects;


public class HomeFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.webview_container, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    WebView myWebView = Objects.requireNonNull(getView()).findViewById(R.id.webview);
    myWebView.loadUrl("https://www.raspberrypi.org/");
  }

}
