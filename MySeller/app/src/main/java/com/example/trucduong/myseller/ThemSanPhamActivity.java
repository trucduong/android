package com.example.trucduong.myseller;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.example.trucduong.myseller.dao.DatabaseHelper;
import com.example.trucduong.myseller.dao.NhomSanPhamDao;
import com.example.trucduong.myseller.dao.SanPhamDao;
import com.example.trucduong.myseller.dto.NhomSanPham;
import com.example.trucduong.myseller.dto.SanPham;

import java.util.HashMap;
import java.util.Map;

public class ThemSanPhamActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtCount;
    private EditText edtInPrice;
    private EditText edtOutPrice;
    private EditText edtNote;
    private Spinner cmbNhomSP;
    private MySpinnerHelper<Integer, NhomSanPham> cmbNhomSPHelper;

    private DatabaseHelper dbHelper;
    private boolean isEditing = false;
    private SanPham item;

    SanPhamDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        Button btnAdd = (Button) findViewById(R.id.btn_Save);
        btnAdd.setOnClickListener(new SaveAction(this));

        this.edtName = (EditText) findViewById(R.id.edt_TenSP);
        this.edtCount = (EditText) findViewById(R.id.edt_SoLuong);
        this.edtInPrice = (EditText) findViewById(R.id.edt_GiaMua);
        this.edtOutPrice = (EditText) findViewById(R.id.edt_GiaBan);
        this.edtNote = (EditText) findViewById(R.id.edt_GhiChu);
        this.cmbNhomSP = (Spinner) findViewById(R.id.cmb_NhomSP);

        dbHelper = new DatabaseHelper(this);
        dao = new SanPhamDao();

        Intent intent = getIntent();
        String message = intent.getStringExtra("item_id");
        if (!"-1".equals(message)) {
            item = dao.findById(dbHelper.getReadableDatabase(), Integer.parseInt(message));
        }

        if(item == null) {
            item = new SanPham();
            isEditing = false;
            setTitle("Them san pham");
        } else {
            isEditing = true;
            setTitle("cap nhat san pham");
        }

        // Load CMB nhom sp
        NhomSanPhamDao nhomSanPhamDao = new NhomSanPhamDao();
        cmbNhomSPHelper = new MySpinnerHelper<Integer, NhomSanPham>(this, cmbNhomSP, nhomSanPhamDao.getAll(dbHelper.getReadableDatabase()));

        bind(item);
    }

    public void bind(SanPham sanPham) {
        edtName.setText(sanPham.getName());
        edtCount.setText(String.valueOf(sanPham.getCount()));
        edtInPrice.setText(String.valueOf(sanPham.getInPrice()));
        edtOutPrice.setText(String.valueOf(sanPham.getOutPrice()));
        edtNote.setText(sanPham.getNote());
        cmbNhomSPHelper.setSelectedValue(sanPham.getNhomSP());
    }

    public void unBind(SanPham sanPham) {
        sanPham.setName(ValueUtils.getString(edtName));
        sanPham.setCount(ValueUtils.getInt(edtCount));
        sanPham.setInPrice(ValueUtils.getInt(edtInPrice));
        sanPham.setOutPrice(ValueUtils.getInt(edtOutPrice));
        sanPham.setNote(ValueUtils.getString(edtNote));
        sanPham.setNhomSP(cmbNhomSPHelper.getSelectedValue());
    }

    class SaveAction implements View.OnClickListener {
        ThemSanPhamActivity activity;

        public SaveAction(ThemSanPhamActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onClick(View v) {
            unBind(item);
            if (isEditing) {
                dao.update(dbHelper.getWritableDatabase(), item);
            } else {
                dao.create(dbHelper.getWritableDatabase(), item);
            }

            activity.finish();
        }
    }
}
