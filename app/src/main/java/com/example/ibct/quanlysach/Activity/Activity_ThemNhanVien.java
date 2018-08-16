package com.example.ibct.quanlysach.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.NhanVien;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

public class Activity_ThemNhanVien extends AppCompatActivity implements View.OnClickListener {
    EditText edt_taikhoan, edt_matkhau, edt_nhaplaimatkhau, edt_hovaten, edt_diachi, edt_namsinh, edt_sodienthoai;
    RadioButton radio_nam, radio_nu, radio_nhanvien, radio_quantri;
    Button btnDangKy;
    Button btnXoa;
    DatabaseAccsess databaseAccsess;
    int check = 0;
    int manhanvien;
    TextView txtQuyen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__them_nhan_vien);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        check = bundle.getInt("check");

        edt_hovaten = (EditText) findViewById(R.id.edt_hovaten);
        edt_diachi = (EditText) findViewById(R.id.edt_diachi);
        edt_namsinh = (EditText) findViewById(R.id.edt_namsinh);
        edt_sodienthoai = (EditText) findViewById(R.id.edt_sodienthoai);
        txtQuyen = (TextView) findViewById(R.id.txtQuyen);
        edt_taikhoan = (EditText) findViewById(R.id.edt_taikhoan);

        edt_matkhau = (EditText) findViewById(R.id.edt_matkhau);
        edt_nhaplaimatkhau = (EditText) findViewById(R.id.edt_nhaplaimatkhau);

        radio_nam = (RadioButton) findViewById(R.id.radio_nam);
        radio_nu = (RadioButton) findViewById(R.id.radio_nu);
        radio_nhanvien = (RadioButton) findViewById(R.id.radio_nhanvien);
        radio_quantri = (RadioButton) findViewById(R.id.radio_quantri);

        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        btnXoa = (Button) findViewById(R.id.btnXoa);
        btnDangKy.setOnClickListener(this);
        btnXoa.setOnClickListener(this);
        databaseAccsess = DatabaseAccsess.getInstance(Activity_ThemNhanVien.this);
        databaseAccsess.open();

        txtQuyen.setVisibility(View.GONE);
        radio_quantri.setVisibility(View.GONE);
        radio_nhanvien.setVisibility(View.GONE);
        if (check == 3 || check == 4) {
            btnXoa.setVisibility(View.VISIBLE);
            btnDangKy.setText("Sửa");
            if (check == 4)
                manhanvien = bundle.getInt("manhanvien");
            else if (check == 3) {
                manhanvien = bundle.getInt("manhanvien");
            }
            edt_taikhoan.setText(bundle.getString("tendangnhap"));
            edt_matkhau.setText(bundle.getString("matkhau"));
            edt_nhaplaimatkhau.setText(bundle.getString("matkhau"));
            edt_hovaten.setText(bundle.getString("hovaten"));
            edt_diachi.setText(bundle.getString("diachi"));
            edt_namsinh.setText(String.valueOf(bundle.getInt("namsinh")));
            edt_sodienthoai.setText(bundle.getString("sodienthoai"));

            if (bundle.getBoolean("gioitinh") == true) {
                radio_nam.setChecked(true);
                radio_nu.setChecked(false);
            } else {
                radio_nu.setChecked(true);
                radio_nam.setChecked(false);
            }
            if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                if (bundle.getInt("quyen") == 0) {
                    radio_nhanvien.setChecked(false);
                    radio_quantri.setChecked(true);
                } else if (bundle.getInt("quyen") == 1) {
                    radio_quantri.setChecked(false);
                    radio_nhanvien.setChecked(true);
                }
                txtQuyen.setVisibility(View.VISIBLE);
                radio_quantri.setVisibility(View.VISIBLE);
                radio_nhanvien.setVisibility(View.VISIBLE);
            } else {
                txtQuyen.setVisibility(View.GONE);
                radio_quantri.setVisibility(View.GONE);
                radio_nhanvien.setVisibility(View.GONE);

            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btnDangKy:
                if (check == 0 || check == 2 || check == 4) {
                    String hoten = edt_hovaten.getText().toString();
                    String diachi = edt_diachi.getText().toString();
                    int namsinh = Integer.parseInt(edt_namsinh.getText().toString());
                    String sodienthoai = edt_sodienthoai.getText().toString();
                    String taikhoan = edt_taikhoan.getText().toString();
                    String matkhau = edt_matkhau.getText().toString();
                    String nhaplai = edt_nhaplaimatkhau.getText().toString();
                    Boolean gioitinh;
                    int maquyen = 0;
                    if (radio_nam.isChecked()) {
                        gioitinh = true;
                    } else {
                        gioitinh = false;
                    }
                    if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                        if (radio_nhanvien.isChecked()) {
                            maquyen = 1;
                        } else {
                            maquyen = 0;
                        }
                    } else {
                        maquyen = 2;
                    }
                    if (!taikhoan.equals("") && !matkhau.equals("") && !nhaplai.equals("") && matkhau.equals(nhaplai)) {

                        if (check == 0 || check == 2) {
                            if (databaseAccsess.getUserCount(taikhoan) == 0) {
                                DocGia user;
                                if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                                    user = new DocGia(hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, maquyen);
                                } else {
                                    user = new DocGia(hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, 2);
                                }

                                databaseAccsess.ThemThongTin_DocGia(user);
                                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                if (check == 2) {
                                    startActivity(new Intent(Activity_ThemNhanVien.this, ActivityLogin.class));
                                } else {
                                    startActivity(new Intent(Activity_ThemNhanVien.this, Activity_DanhSachDocGia.class));
                                }
                            } else {
                                Toast.makeText(this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (check == 4) {
                            DocGia user;
                            if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                                user = new DocGia(manhanvien, hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, maquyen);
                            } else {
                                user = new DocGia(manhanvien, hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, 2);
                            }
                            databaseAccsess.SuaThongTin_DocGia(user);
                            Toast.makeText(this, "Sửa thành công!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Activity_ThemNhanVien.this, Activity_DanhSachDocGia.class));
                        }
                        break;
                    } else {
                        Toast.makeText(this, "Mời bạn kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                } else if (check == 1 || check == 3) {
                    String hoten = edt_hovaten.getText().toString();
                    String diachi = edt_diachi.getText().toString();
                    int namsinh = Integer.parseInt(edt_namsinh.getText().toString());
                    String sodienthoai = edt_sodienthoai.getText().toString();
                    String taikhoan = edt_taikhoan.getText().toString();
                    String matkhau = edt_matkhau.getText().toString();
                    String nhaplai = edt_nhaplaimatkhau.getText().toString();
                    Boolean gioitinh;
                    int maquyen;
                    if (radio_nam.isChecked()) {
                        gioitinh = true;
                    } else {
                        gioitinh = false;
                    }

                    if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                        if (radio_nhanvien.isChecked()) {
                            maquyen = 1;
                        } else {
                            maquyen = 0;
                        }
                    } else {
                        maquyen = 2;
                    }
                    if (!taikhoan.equals("") && !matkhau.equals("") && !nhaplai.equals("") && matkhau.equals(nhaplai)) {
                        if (check == 1) {
                            if (databaseAccsess.getNhanVienCount(taikhoan) == 0) {
                                NhanVien user;
                                if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                                    user = new NhanVien(hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, maquyen);
                                } else {
                                    user = new NhanVien(hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, 2);
                                }
                                databaseAccsess.ThemThongTin_NhanVien(user);
                                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                            }
                        } else if (check == 3) {
                            NhanVien user;
                            if (ActivityLogin.maquyen == 0 || ActivityLogin.maquyen == 1) {
                                user = new NhanVien(manhanvien, hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, maquyen);
                            } else {
                                user = new NhanVien(manhanvien, hoten, diachi, namsinh, sodienthoai, gioitinh, taikhoan, matkhau, 2);
                            }
                            databaseAccsess.SuaThongTin_NhanVien(user);
                        }
                        startActivity(new Intent(Activity_ThemNhanVien.this, Activity_DanhSachDocGia.class));

                        break;
                    } else {
                        Toast.makeText(this, "Mời bạn kiểm tra lại!", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btnXoa:
                if (check == 3) {
                    databaseAccsess.XoaThongTin_DocGia(manhanvien);
                    startActivity(new Intent(Activity_ThemNhanVien.this, Activity_DanhSachDocGia.class));

                } else if (check == 4) {
                    databaseAccsess.XoaThongTin_DocGia(manhanvien);
                    startActivity(new Intent(Activity_ThemNhanVien.this, Activity_DanhSachDocGia.class));

                }
                break;
        }
    }
}
