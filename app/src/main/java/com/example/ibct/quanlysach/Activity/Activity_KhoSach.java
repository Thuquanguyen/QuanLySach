package com.example.ibct.quanlysach.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ibct.quanlysach.Adapter.Adapter_ChapNhan;
import com.example.ibct.quanlysach.Adapter.Adapter_MuonSach;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class Activity_KhoSach extends AppCompatActivity {
    ListView listView;
    private ArrayList<MuonSach> list_khosach;
    private ArrayList<DocGia> list_DocGia;
    private ArrayList<Sach> list_Sach;
    private Adapter_ChapNhan adapter_muonSach;
    DatabaseAccsess databaseAccsess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__kho_sach);
        list_khosach = new ArrayList<>();
        list_DocGia = new ArrayList<>();
        list_Sach = new ArrayList<>();

        listView = (ListView) findViewById(R.id.lst_khoSach);
        databaseAccsess = DatabaseAccsess.getInstance(Activity_KhoSach.this);
        databaseAccsess.open();
        if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
            list_khosach = databaseAccsess.getConten_MuonSach(false, false);
            list_DocGia = databaseAccsess.getConten_DocGia();
            list_Sach = databaseAccsess.getConten_Sach();
            adapter_muonSach = new Adapter_ChapNhan(this, R.layout.activity_custom_khosach, list_khosach, list_DocGia, list_Sach);
            listView.setAdapter(adapter_muonSach);
            adapter_muonSach.notifyDataSetChanged();
        } else if (ActivityLogin.maquyen == 2) {
            list_khosach = databaseAccsess.getConten_MuonSach(false, true);
            list_DocGia = databaseAccsess.getConten_DocGia();
            list_Sach = databaseAccsess.getConten_Sach();
            adapter_muonSach = new Adapter_ChapNhan(this, R.layout.activity_custom_khosach, list_khosach, list_DocGia, list_Sach);
            listView.setAdapter(adapter_muonSach);
            adapter_muonSach.notifyDataSetChanged();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        list_khosach = new ArrayList<>();
        list_DocGia = new ArrayList<>();
        list_Sach = new ArrayList<>();
        if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
            list_khosach = databaseAccsess.getConten_MuonSach(false, false);
            list_DocGia = databaseAccsess.getConten_DocGia();
            list_Sach = databaseAccsess.getConten_Sach();
            adapter_muonSach = new Adapter_ChapNhan(this, R.layout.activity_custom_khosach, list_khosach, list_DocGia, list_Sach);
            listView.setAdapter(adapter_muonSach);
            adapter_muonSach.notifyDataSetChanged();
        } else if (ActivityLogin.maquyen == 2) {
            list_khosach = databaseAccsess.getConten_MuonSach(false, true);
            list_DocGia = databaseAccsess.getConten_DocGia();
            list_Sach = databaseAccsess.getConten_Sach();
            adapter_muonSach = new Adapter_ChapNhan(this, R.layout.activity_custom_khosach, list_khosach, list_DocGia, list_Sach);
            listView.setAdapter(adapter_muonSach);
            adapter_muonSach.notifyDataSetChanged();
        }
    }
}
