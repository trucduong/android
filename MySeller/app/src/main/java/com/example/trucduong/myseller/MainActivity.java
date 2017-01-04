package com.example.trucduong.myseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.trucduong.myseller.dao.DatabaseHelper;
import com.example.trucduong.myseller.dao.SanPhamDao;
import com.example.trucduong.myseller.dto.SanPham;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnClear;
    private EditText edtSearch;
    private ListView lstSanPham;

    private DatabaseHelper dbHelper;
    private SanPhamDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Phan mem ban hang");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, BanHangActivity.class);
//                startActivity(intent);
//            }
//        });

        btnClear = (Button) findViewById(R.id.btn_Clear);
        edtSearch = (EditText) findViewById(R.id.edt_Search);
        lstSanPham = (ListView) findViewById(R.id.lst_SanPham);

        dbHelper = new DatabaseHelper(this);
        dao = new SanPhamDao();

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClear();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                onSearch();
            }
        });
    }

    private void onClear() {
        edtSearch.setText("");
    }

    private void showDetail(int id) {
        Intent intent = new Intent(this, ThemSanPhamActivity.class);
        intent.putExtra("item_id", String.valueOf(id));
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        onSearch();
    }

    protected void onSearch() {
        String searchText = edtSearch.getText().toString();
        List<SanPham> sanPhams = dao.findByName(dbHelper.getReadableDatabase(), searchText);
        SearchSanPhamAdapter adapter = new SearchSanPhamAdapter(this, sanPhams) {
            @Override
            public void onItemSelected(SanPham item) {
                showDetail(item.getId());
            }
        };
        lstSanPham.setAdapter(adapter);
        //setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_SanPham) {
            Intent intent = new Intent(this, SanPhamActivity.class);
            startActivity(intent);
        } else if (id == R.id.action_NhomSanPham) {
            Intent intent = new Intent(this, NhomSanPhamActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
