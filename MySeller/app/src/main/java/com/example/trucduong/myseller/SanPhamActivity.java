package com.example.trucduong.myseller;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.trucduong.myseller.dao.DatabaseHelper;
import com.example.trucduong.myseller.dao.NhomSanPhamDao;
import com.example.trucduong.myseller.dao.SanPhamDao;
import com.example.trucduong.myseller.dto.NhomSanPham;
import com.example.trucduong.myseller.dto.SanPham;

import java.util.Arrays;
import java.util.List;

public class SanPhamActivity extends AppCompatActivity {

    private SanPhamDao dao;
    private DatabaseHelper dbHelper;
    private ListView lstSanPham;

    private Spinner cmbNhomSP;
    private MySpinnerHelper<Integer, NhomSanPham> cmbNhomSPHelper;

    private int selectedNhomSP = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        setTitle("San pham");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDetail(-1);
            }
        });

        dao = new SanPhamDao();
        dbHelper = new DatabaseHelper(this);

        lstSanPham = (ListView) findViewById(R.id.lst_SanPham);
        this.cmbNhomSP = (Spinner) findViewById(R.id.cmb_NhomSP);

        NhomSanPhamDao nhomSanPhamDao = new NhomSanPhamDao();
        List<NhomSanPham> items = nhomSanPhamDao.getAll(dbHelper.getReadableDatabase());
        NhomSanPham first = new NhomSanPham();
        first.setId(-1);
        first.setName("Tat ca");
        items.add(0, first);
        cmbNhomSPHelper = new MySpinnerHelper<Integer, NhomSanPham>(this, cmbNhomSP, items);

        cmbNhomSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedNhomSP = cmbNhomSPHelper.getSelectedValue();
                onCreateContent();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        onCreateContent();
    }

    private void showDetail(int id) {
        Intent intent = new Intent(SanPhamActivity.this, ThemSanPhamActivity.class);
        intent.putExtra("item_id", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        onCreateContent();
    }

    protected void onCreateContent() {
        List<SanPham> sanPhams = null;
        if (selectedNhomSP == -1) {
            sanPhams = dao.getAll(dbHelper.getReadableDatabase());
        } else {
            sanPhams = dao.findByNhom(dbHelper.getReadableDatabase(), selectedNhomSP);
        }
        SanPhamAdapter adapter = new SanPhamAdapter(this, sanPhams) {
            @Override
            public void onDelete(SanPham item) {
                delete(item);
            }

            @Override
            public void onItemSelected(SanPham item) {
                showDetail(item.getId());
            }
        };
        lstSanPham.setAdapter(adapter);
        //setListAdapter(adapter);
    }

    private void delete(final SanPham item) {
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
