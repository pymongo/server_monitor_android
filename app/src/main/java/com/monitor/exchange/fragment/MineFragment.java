package com.monitor.exchange.fragment;

import android.annotation.SuppressLint;
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


public class MineFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.webview_container, container, false);
  }

  @SuppressLint("SetJavaScriptEnabled")
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    // 字节跳动官网需要js才能加载，墨刀和树莓派官网不需要
    WebView myWebView = Objects.requireNonNull(getView()).findViewById(R.id.webview);
    // myWebView.getSettings().setJavaScriptEnabled(true);
    myWebView.loadUrl("https://www.raspberrypi.org/");
  }
}
