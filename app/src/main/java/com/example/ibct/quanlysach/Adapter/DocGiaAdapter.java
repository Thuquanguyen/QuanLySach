package com.example.ibct.quanlysach.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ibct.quanlysach.Activity.Activity_ThemNhanVien;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DocGiaAdapter extends ArrayAdapter<DocGia> {
    private Context mContext;
    private int id;
    private ArrayList<DocGia> arraylist;

    public DocGiaAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DocGia> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.id = resource;
        this.arraylist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
            viewHolder.txtHoTen = (TextView) view.findViewById(R.id.txtHoTen);
            viewHolder.txtSoDienThoai = (TextView) view.findViewById(R.id.txtSoDienThoai);
            viewHolder.txtGioiTinh = (TextView) view.findViewById(R.id.txtGioiTinh);
            viewHolder.btnChiTiet = (Button) view.findViewById(R.id.btn_ChiTiet);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.txtHoTen.setText(arraylist.get(position).getHoVaTen());
        viewHolder.txtSoDienThoai.setText(arraylist.get(position).getSoDienThoai());
        if (arraylist.get(position).getGioiTinh() == true) {
            viewHolder.txtGioiTinh.setText("Nam");
        } else {
            viewHolder.txtGioiTinh.setText("Nữ");
        }
        viewHolder.btnChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Activity_ThemNhanVien.class);
                Bundle bundle = new Bundle();
                bundle.putInt("manhanvien", arraylist.get(position).getMaDocGia());
                bundle.putString("tendangnhap", arraylist.get(position).getTenDangNhap());
                bundle.putString("matkhau", arraylist.get(position).getMatKhau());
                bundle.putString("hovaten", arraylist.get(position).getHoVaTen());
                bundle.putString("diachi", arraylist.get(position).getDiaChi());
                bundle.putInt("namsinh", arraylist.get(position).getNamSinh());
                bundle.putString("sodienthoai", arraylist.get(position).getSoDienThoai());
                if (arraylist.get(position).getGioiTinh() == true) {
                    bundle.putBoolean("gioitinh", true);
                } else {
                    bundle.putBoolean("gioitinh", false);
                }
                if (arraylist.get(position).getMaQuyen() == 0) {
                    bundle.putInt("quyen", 0);

                } else if (arraylist.get(position).getMaQuyen() == 1) {
                    bundle.putInt("quyen", 1);

                } else if (arraylist.get(position).getMaQuyen() == 2) {
                    bundle.putInt("quyen", 2);
                }
                bundle.putInt("check", 4);
                intent.putExtra("data", bundle);
                mContext.startActivity(intent);
            }
        });


        return view;
    }


    private class ViewHolder {
        TextView txtHoTen;
        TextView txtSoDienThoai;
        TextView txtGioiTinh;
        Button btnChiTiet;

    }

}
