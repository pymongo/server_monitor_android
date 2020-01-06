package com.monitor.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.webkit.WebView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    WebView myWebView = new WebView(getApplicationContext());
    setContentView(myWebView);
    myWebView.loadUrl("https://www.raspberrypi.org/");

    /* 初始化底部导航栏 */
//    BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
//    NavController navController = Navigation.findNavController(this, R.id.navigation_host_fragment);
//    NavigationUI.setupWithNavController(bottomNavigation, navController);
  }

}
