package com.example.ibct.quanlysach.Activity;

import android.app.TabActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.TabHost;

import com.example.ibct.quanlysach.Adapter.Adapter_DaTra;
import com.example.ibct.quanlysach.Adapter.Adapter_MuonSach;
import com.example.ibct.quanlysach.Adapter.DocGiaAdapter;
import com.example.ibct.quanlysach.Adapter.NhanVienAdapter;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class Activity_ThongKe extends TabActivity {
    ListView lstView;
    private ArrayList<MuonSach> list_chuaTra;
    private ArrayList<MuonSach> list_DaTra;
    private ArrayList<DocGia> list_DocGia;
    private ArrayList<Sach> list_Sach;
    private Adapter_MuonSach adapter_muonSach;
    private Adapter_DaTra adapter_datra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        list_chuaTra = new ArrayList<>();
        list_DaTra = new ArrayList<>();
        list_DocGia = new ArrayList<>();
        list_Sach = new ArrayList<>();


        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(Activity_ThongKe.this);
        databaseAccsess.open();
        list_chuaTra = databaseAccsess.getConten_MuonSach(false, true);
        list_DaTra = databaseAccsess.getConten_MuonSach(true, true);
        list_DocGia = databaseAccsess.getConten_DocGia();
        list_Sach = databaseAccsess.getConten_Sach();
        databaseAccsess.close();
        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity__thong_ke, tabHost.getTabContentView(), true);
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("Chưa Trả").setContent(R.id.list_thongke));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("Đã trả").setContent(R.id.list_thongke));
        lstView = (ListView) tabHost.findViewById(R.id.list_thongke);
        adapter_muonSach = new Adapter_MuonSach(this, R.layout.actitivy_custom_muonsach, list_chuaTra, list_DocGia, list_Sach);
        lstView.setAdapter(adapter_muonSach);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if (tabId.equals("tab1")) {
                    adapter_muonSach = new Adapter_MuonSach(Activity_ThongKe.this, R.layout.actitivy_custom_muonsach, list_chuaTra, list_DocGia, list_Sach);
                    lstView.setAdapter(adapter_muonSach);
                } else if (tabId.equals("tab2")) {
                    adapter_datra = new Adapter_DaTra(Activity_ThongKe.this, R.layout.actitivy_custom_muonsach, list_DaTra, list_DocGia, list_Sach);
                    lstView.setAdapter(adapter_datra);
                }
            }
        });
    }
}
