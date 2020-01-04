package com.monitor.exchange;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyAdapter extends BaseAdapter {

  // 将实例变量currencies设为私有，避免与MainActivity的currencies命名冲突
  private List<HashMap<String, String>> currencies;
  private LayoutInflater inflater;

  CurrencyAdapter(Context context, List<HashMap<String, String>> currencies) {
    // 构造方法会隐式地调用super。个别需要super的构造方法Overloading时才需要写super
    this.currencies = currencies;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return currencies.size();
  }

  @Override
  public Object getItem(int index) {
    return currencies.get(index);
  }

  @Override
  public long getItemId(int index) {
    return index;
  }

  @Override
  public View getView(int index, View convertView, ViewGroup parent) {
    // TODO 解决inflater.inflate的参数警告
    View listItem = inflater.inflate(R.layout.currency_row, null);

    ImageView currencyIcon = listItem.findViewById(R.id.currencyIcon);
    TextView currencyCode = listItem.findViewById(R.id.currnecyCode);
    TextView currencyID = listItem.findViewById(R.id.currnecyID);
    Map<String, String> currency = currencies.get(index);

    // 将奇数列的图标改为monitor
    if (index % 2 == 0) {
      currencyIcon.setImageResource(R.mipmap.monitor_icon);
    }

    try {
      InputStream inputStream = new URL(currency.get("iconUrl")).openStream();
      Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
      currencyIcon.setImageBitmap(bitmap);
    } catch (Exception e) {
      e.printStackTrace();
    }

    currencyCode.setText(currency.get("code"));
    currencyID.setText(currency.get("id"));

    return listItem;
  }
}
