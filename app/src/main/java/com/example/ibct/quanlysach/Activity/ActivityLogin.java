package com.example.ibct.quanlysach.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.NhanVien;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<DocGia> list_User;
    private ArrayList<NhanVien> list_NhanVien;
    EditText edt_taikhoan, edt_matkhau;
    Button btnDangNhap;
    TextView txtDangKy;
    public static int madocgia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edt_taikhoan = (EditText) findViewById(R.id.edt_taikhoan);
        edt_matkhau = (EditText) findViewById(R.id.edt_matkhau);
        txtDangKy = (TextView) findViewById(R.id.txtDangKy);
        btnDangNhap = (Button) findViewById(R.id.btn_dangNhap);

        txtDangKy.setOnClickListener(this);
        btnDangNhap.setOnClickListener(this);
        list_User = new ArrayList<>();
        list_NhanVien = new ArrayList<>();
        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(ActivityLogin.this);
        databaseAccsess.open();
        list_User = databaseAccsess.getConten_DocGia();
        list_NhanVien = databaseAccsess.getConten_NhanVien();
        databaseAccsess.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        list_User.clear();
        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(ActivityLogin.this);
        databaseAccsess.open();
        list_User = databaseAccsess.getConten_DocGia();
        list_NhanVien = databaseAccsess.getConten_NhanVien();
        databaseAccsess.close();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_dangNhap:
                String taikhoan = edt_taikhoan.getText().toString();
                String matkhau = edt_matkhau.getText().toString();

                if (list_User.size() > 0 || list_NhanVien.size() > 0) {

                    for (int i = 0; i < list_User.size(); i++) {
                        if (!taikhoan.equals("") && !matkhau.equals("")) {
                            if ((taikhoan.equals(list_User.get(i).getTenDangNhap()) && matkhau.equals(list_User.get(i).getMatKhau())) || (taikhoan.equals(list_NhanVien.get(i).getTenDangNhap()) && matkhau.equals(list_NhanVien.get(i).getMatKhau()))) {
                                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                                if (list_User.get(i).getTenDangNhap() != null) {
                                    madocgia = list_User.get(i).getMaDocGia();
                                } else {
                                    madocgia = list_NhanVien.get(i).getMaNV();
                                }
                                startActivity(new Intent(ActivityLogin.this, MainActivity.class));
                                break;
                            } else {
                                Toast.makeText(this, "Tài khoản hoặc mật khẩu chưa đúng vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(this, "Kiểm tra dữ liệu trống!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            case R.id.txtDangKy:
                Intent intent = new Intent(ActivityLogin.this, Activity_ThemNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("check", 2);
                intent.putExtra("data", bundle);
                startActivity(intent);
                break;

        }
    }
}
