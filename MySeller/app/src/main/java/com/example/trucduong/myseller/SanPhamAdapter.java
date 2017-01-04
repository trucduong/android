package com.example.trucduong.myseller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.trucduong.myseller.dto.SanPham;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Created by trucduong on 31/08/2016.
 */
public abstract class SanPhamAdapter extends ArrayAdapter<SanPham> {
    private List<SanPham> values;
    private Context context;

    public SanPhamAdapter(Context context, List<SanPham> objects) {
        super(context, R.layout.content_sanpham_item, objects);
        values = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.content_sanpham_item, null);

        final SanPham item = values.get(position);
        TextView txtName = (TextView) rowView.findViewById(R.id.txt_item_TenSP);
        txtName.setText(item.getName());

        TextView txtGiaBan = (TextView) rowView.findViewById(R.id.txt_item_GiaBan);
        DecimalFormat formatter = new DecimalFormat("#,###,###");
        txtGiaBan.setText(formatter.format(item.getOutPrice()));

        Button btnDelete = (Button) rowView.findViewById(R.id.btn_item_Delete);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDelete(item);
            }
        });

        View panelContent = rowView.findViewById(R.id.panel_content);
        panelContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemSelected(item);
            }
        });

        return rowView;
    }

    public abstract void onDelete(SanPham item);

    public abstract void onItemSelected(SanPham item);
}
