package com.monitor.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    /* 初始化底部导航栏 */
    BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation_menu);
    // menu.xml中的id必须与navigation_graph.xml中的ID保持一致
    AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.navigation_home,
      R.id.navigation_monitor, R.id.navigation_currencies, R.id.navigation_mine).build();
    NavController navController = Navigation.findNavController(this, R.id.navigation_host_fragment);
    NavigationUI.setupWithNavController(bottomNavigation, navController);
  }

}
