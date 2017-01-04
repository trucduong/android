package com.example.trucduong.myseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.trucduong.myseller.dao.DatabaseHelper;
import com.example.trucduong.myseller.dao.NhomSanPhamDao;
import com.example.trucduong.myseller.dao.SanPhamDao;
import com.example.trucduong.myseller.dto.NhomSanPham;
import com.example.trucduong.myseller.dto.SanPham;

public class ThemNhomSanPhamActivity extends AppCompatActivity {

    private EditText edtName;
    private EditText edtNote;

    private DatabaseHelper dbHelper;
    private boolean isEditing = false;
    private NhomSanPham item;

    NhomSanPhamDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhom_san_pham);

        Button btnAdd = (Button) findViewById(R.id.btn_Save);
        btnAdd.setOnClickListener(new SaveAction(this));

        this.edtName = (EditText) findViewById(R.id.edt_Ten);
        this.edtNote = (EditText) findViewById(R.id.edt_GhiChu);

        dbHelper = new DatabaseHelper(this);
        dao = new NhomSanPhamDao();

        Intent intent = getIntent();
        String message = intent.getStringExtra("item_id");
        if (!"-1".equals(message)) {
            item = dao.findById(dbHelper.getReadableDatabase(), Integer.parseInt(message));
        }

        if(item == null) {
            item = new NhomSanPham();
            isEditing = false;
            setTitle("Them moi");
        } else {
            isEditing = true;
            setTitle("Cap nhat");
        }

        bind(item);
    }

    public void bind(NhomSanPham sanPham) {
        edtName.setText(sanPham.getName());
        edtNote.setText(sanPham.getNote());
    }

    public void unBind(NhomSanPham sanPham) {
        sanPham.setName(ValueUtils.getString(edtName));
        sanPham.setNote(ValueUtils.getString(edtNote));
    }

    class SaveAction implements View.OnClickListener {
        ThemNhomSanPhamActivity activity;

        public SaveAction(ThemNhomSanPhamActivity activity) {
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
