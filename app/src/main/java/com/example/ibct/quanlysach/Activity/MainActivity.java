package com.example.ibct.quanlysach.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibct.quanlysach.Adapter.SachAdapter;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {
    private ArrayList<Sach> list_Sach;
    private SearchView searchView;
    ListView lst_Sach;
    SachAdapter adapter;
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list_Sach = new ArrayList<>();
        lst_Sach = (ListView) findViewById(R.id.list_Sach);
        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(MainActivity.this);
        databaseAccsess.open();
        list_Sach = databaseAccsess.getConten_Sach();
        databaseAccsess.close();
        if (list_Sach.size() != 0) {
            adapter = new SachAdapter(MainActivity.this, R.layout.activity_custom_gridview_sach, list_Sach);
            lst_Sach.setAdapter(adapter);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_ThemSach.class);
                Bundle bundle = new Bundle();
                bundle.putInt("check", 0);
                intent.putExtra("data", bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem itemSearch = menu.findItem(R.id.search_view);
        searchView = (SearchView) itemSearch.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (list_Sach.size() != 0) {
            adapter = new SachAdapter(MainActivity.this, R.layout.activity_custom_gridview_sach, list_Sach);
            lst_Sach.setAdapter(adapter);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.btn_danhmucsach) {
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.btn_danhsachdocgia) {
            startActivity(new Intent(MainActivity.this, Activity_DanhSachDocGia.class));
        } else if (id == R.id.btn_thongke) {
            startActivity(new Intent(MainActivity.this, Activity_ThongKe.class));
        } else if (id == R.id.btn_dangxuat) {
            startActivity(new Intent(MainActivity.this, ActivityLogin.class));
        } else if (id == R.id.btn_khosach) {
            startActivity(new Intent(MainActivity.this, Activity_KhoSach.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            adapter.filter("");
            lst_Sach.clearTextFilter();
        } else {
            adapter.filter(newText.toString());
        }
        return true;
    }
}
