package com.example.trucduong.myseller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.trucduong.myseller.dao.DatabaseHelper;
import com.example.trucduong.myseller.dao.NhomSanPhamDao;
import com.example.trucduong.myseller.dto.NhomSanPham;

import java.util.List;

public class NhomSanPhamActivity extends AppCompatActivity {

    private NhomSanPhamDao dao;
    private DatabaseHelper dbHelper;
    private ListView lstNhomSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhom_san_pham);
        setTitle("Nhom San pham");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail(-1);
            }
        });

        dao = new NhomSanPhamDao();
        dbHelper = new DatabaseHelper(this);

        lstNhomSanPham = (ListView) findViewById(R.id.lst_NhomSanPham);

        onCreateContent();
    }

    private void showDetail(int id) {
        Intent intent = new Intent(this, ThemNhomSanPhamActivity.class);
        intent.putExtra("item_id", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        onCreateContent();
    }

    protected void onCreateContent() {
        List<NhomSanPham> items = dao.getAll(dbHelper.getReadableDatabase());
        NhomSanPhamAdapter adapter = new NhomSanPhamAdapter(this, items) {
            @Override
            public void onDelete(NhomSanPham item) {
                delete(item);
            }

            @Override
            public void onItemSelected(NhomSanPham item) {
                showDetail(item.getId());
            }
        };
        lstNhomSanPham.setAdapter(adapter);
        //setListAdapter(adapter);
    }

    private void delete(final NhomSanPham item) {
        AlertDialog myDeleteDialog =new AlertDialog.Builder(this)
            //set message, title, and icon
            .setTitle("Delete")
            .setMessage("Do you want to Delete: " + item.getName())

            .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {

                    dao.delete(dbHelper.getWritableDatabase(), item.getId());
                    //your deleting code
                    dialog.dismiss();
                    onCreateContent();
                }

            })

            .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            })
            .create();

        myDeleteDialog.show();
    }
}
