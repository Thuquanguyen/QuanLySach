package com.example.ibct.quanlysach.Activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.ibct.quanlysach.Adapter.GetFilePathFromDevice;
import com.example.ibct.quanlysach.Context.LoaiSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Activity_ThemSach extends AppCompatActivity implements View.OnClickListener {
    EditText edt_tenSach, edt_TacGia, edtNamXuatBan, edtSoLuong, edtNoiDung, edtghiChu;
    Spinner spnMaLoai;
    Button btnChonAnh;
    Button btnThemSach;
    Button btnXoaSach;
    ArrayList<LoaiSach> array_loai;
    public static final int PICK_IMAGE = 1;
    String linkAnh = "";
    int maloai;
    DatabaseAccsess databaseAccsess;
    String tenSach, tacGia, noiDung, ghiChu;
    int namXuatBan, soLuong;
    int kiemtra, maSach;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__them_sach);


        edt_tenSach = (EditText) findViewById(R.id.edt_tensach);
        edt_TacGia = (EditText) findViewById(R.id.edt_tacgia);
        edtNamXuatBan = (EditText) findViewById(R.id.edt_namxuatban);
        edtSoLuong = (EditText) findViewById(R.id.edt_SoLuong);
        edtNoiDung = (EditText) findViewById(R.id.edt_NoiDung);
        edtghiChu = (EditText) findViewById(R.id.edt_GhiChu);
        spnMaLoai = (Spinner) findViewById(R.id.spn_maloai);
        btnChonAnh = (Button) findViewById(R.id.btn_ChonAnh);
        btnThemSach = (Button) findViewById(R.id.btnThemsach);
        btnXoaSach = (Button) findViewById(R.id.btnXoaSach);


        array_loai = new ArrayList<>();
        databaseAccsess = DatabaseAccsess.getInstance(Activity_ThemSach.this);
        databaseAccsess.open();
        array_loai = databaseAccsess.getConten_LoaiSach();

        ArrayAdapter userAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, array_loai);
        spnMaLoai.setAdapter(userAdapter);

        btnChonAnh.setOnClickListener(this);
        btnThemSach.setOnClickListener(this);
        btnXoaSach.setOnClickListener(this);

        spnMaLoai.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maloai = array_loai.get(position).getMaLoai();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        kiemtra = bundle.getInt("check");
        if (kiemtra == 1) {
            btnXoaSach.setVisibility(View.VISIBLE);
            btnThemSach.setText("Sửa Sách");
            maSach = bundle.getInt("masach");
            tenSach = bundle.getString("tensach");
            maloai = bundle.getInt("maloai");
            tacGia = bundle.getString("tacgia");
            linkAnh = bundle.getString("hinhanh");
            namXuatBan = bundle.getInt("namxuatban");
            soLuong = bundle.getInt("soluong");
            noiDung = bundle.getString("noidung");
            ghiChu = bundle.getString("ghichu");

            edt_tenSach.setText(tenSach);
            spnMaLoai.setSelection(maloai);
            edt_TacGia.setText(tacGia);
            btnChonAnh.setText(linkAnh);
            edtNamXuatBan.setText(String.valueOf(namXuatBan));
            edtSoLuong.setText(String.valueOf(soLuong));
            edtNoiDung.setText(noiDung);
            edtghiChu.setText(ghiChu);
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_ChonAnh:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                break;
            case R.id.btnThemsach:
                if (kiemtra == 0) {
                    String tenSach = edt_tenSach.getText().toString();
                    String tacgia = edt_TacGia.getText().toString();
                    int namxuatban = Integer.parseInt(edtNamXuatBan.getText().toString());
                    int soluong = Integer.parseInt(edtSoLuong.getText().toString());
                    String noidung = edtNoiDung.getText().toString();
                    String ghiChu = edtghiChu.getText().toString();
                    Sach sach = new Sach(tenSach, maloai, tacgia, linkAnh, namxuatban, soluong, noidung, ghiChu);
                    databaseAccsess.ThemThongTin_Sach(sach);
                    startActivity(new Intent(Activity_ThemSach.this, MainActivity.class));
                } else {

                    String tenSach = edt_tenSach.getText().toString();
                    String tacgia = edt_TacGia.getText().toString();
                    int namxuatban = Integer.parseInt(edtNamXuatBan.getText().toString());
                    int soluong = Integer.parseInt(edtSoLuong.getText().toString());
                    String noidung = edtNoiDung.getText().toString();
                    String ghiChu = edtghiChu.getText().toString();
                    String hinhAnh = btnChonAnh.getText().toString();
                    Sach sach = new Sach(maSach, tenSach, maloai, tacgia, hinhAnh, namxuatban, soluong, noidung, ghiChu);
                    databaseAccsess.SuaThongTin_Sach(sach);
                    startActivity(new Intent(Activity_ThemSach.this, MainActivity.class));
                }
                break;
            case R.id.btnXoaSach:
                databaseAccsess.XoaThongTin_Sach(maSach);
                startActivity(new Intent(Activity_ThemSach.this, MainActivity.class));
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            linkAnh = GetFilePathFromDevice.getPath(this, data.getData());
            btnChonAnh.setText(linkAnh);
        }
    }

}
