package com.monitor.exchange.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.monitor.exchange.R;

import java.util.ArrayList;

public class DetailAdapter extends BaseAdapter {

  private ArrayList<ArrayList<String>> data;
  private LayoutInflater inflater;

  public DetailAdapter(Context context, ArrayList<ArrayList<String>> data) {
    this.data = data;
    this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
  }

  @Override
  public int getCount() {
    return data.size();
  }

  @Override
  public Object getItem(int index) {
    return data.get(index);
  }

  @Override
  public long getItemId(int index) {
    return index;
  }

  public void updateCurrencies(ArrayList<ArrayList<String>> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  @Override
  public View getView(int index, View convertView, ViewGroup parent) {
    if (convertView == null) {
      convertView = inflater.inflate(R.layout.item_detail, parent, false);
    }
    TextView key = convertView.findViewById(R.id.detail_key);
    TextView value = convertView.findViewById(R.id.detail_value);
    ArrayList<String> detail = data.get(index);
    key.setText(detail.get(0));
    value.setText(detail.get(1));

    return convertView;
  }
}
