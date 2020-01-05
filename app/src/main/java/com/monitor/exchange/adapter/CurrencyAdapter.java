package com.monitor.exchange.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.monitor.exchange.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurrencyAdapter extends BaseAdapter {

  // 将实例变量currencies设为私有，避免与MainActivity的currencies命名冲突
  private List<HashMap<String, String>> currencies;
  private LayoutInflater inflater;

  public CurrencyAdapter(Context context, List<HashMap<String, String>> currencies) {
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

  public void updateData(List<HashMap<String, String>> currencies) {
    this.currencies = currencies;
    notifyDataSetChanged();
  }

  @Override
  public View getView(int index, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.listitem_currency, parent, false);
    }

    ImageView currencyIcon = convertView.findViewById(R.id.currencyIcon);
    TextView currencyCode = convertView.findViewById(R.id.currnecyCode);
    TextView currencyID = convertView.findViewById(R.id.currnecyID);
    Map<String, String> currency = currencies.get(index);

    // TODO 使用glide库下载币种图片
    currencyCode.setText(currency.get("code"));
    currencyID.setText(currency.get("id"));

    return convertView;
  }
}
