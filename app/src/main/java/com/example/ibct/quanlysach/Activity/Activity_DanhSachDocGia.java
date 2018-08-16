package com.example.ibct.quanlysach.Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.ibct.quanlysach.Adapter.DocGiaAdapter;
import com.example.ibct.quanlysach.Adapter.NhanVienAdapter;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.NhanVien;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class Activity_DanhSachDocGia extends TabActivity {
    private ArrayList<DocGia> list_DocGia;
    private ArrayList<NhanVien> list_NhanVien;
    ListView lst_danhsach;
    DocGiaAdapter docGiaAdapter;
    NhanVienAdapter nhanVienAdapter;
    int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list_DocGia = new ArrayList<>();
        list_NhanVien = new ArrayList<>();

        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(Activity_DanhSachDocGia.this);
        databaseAccsess.open();
        list_DocGia = databaseAccsess.getConten_DocGia();
        list_NhanVien = databaseAccsess.getConten_NhanVien();
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity__danh_sach_doc_gia, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Độc giả").setContent(R.id.listView_DocGia));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Nhân viên").setContent(R.id.listView_DocGia));
        lst_danhsach = (ListView) tabHost.findViewById(R.id.listView_DocGia);
        docGiaAdapter = new DocGiaAdapter(Activity_DanhSachDocGia.this, R.layout.custom_thongtin_docgia, list_DocGia);
        lst_danhsach.setAdapter(docGiaAdapter);
        a = 0;
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {
                    docGiaAdapter = new DocGiaAdapter(Activity_DanhSachDocGia.this, R.layout.custom_thongtin_docgia, list_DocGia);
                    lst_danhsach.setAdapter(docGiaAdapter);
                    a = 0;
                } else if (tabId.equals("tab2")) {
                    nhanVienAdapter = new NhanVienAdapter(Activity_DanhSachDocGia.this, R.layout.custom_thongtin_docgia, list_NhanVien);
                    lst_danhsach.setAdapter(nhanVienAdapter);
                    a = 1;
                }
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_DanhSachDocGia.this, Activity_ThemNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("check", a);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }
}
