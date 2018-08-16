package com.example.ibct.quanlysach.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.Toast;

import com.example.ibct.quanlysach.Activity.ActivityLogin;
import com.example.ibct.quanlysach.Activity.Activity_KhoSach;
import com.example.ibct.quanlysach.Activity.Activity_ThemSach;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context mContext;
    private int id;
    private List<Sach> listSach = null;
    private ArrayList<Sach> arraylist;

    public SachAdapter(@NonNull Context context, int resource, @NonNull List<Sach> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.id = resource;
        this.listSach = objects;
        this.arraylist = new ArrayList<Sach>();
        this.arraylist.addAll(listSach);
    }

    @Override
    public int getCount() {
        return listSach.size();
    }

    @Override
    public Sach getItem(int position) {
        return listSach.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
            viewHolder.img_hinhanh = (ImageView) view.findViewById(R.id.imag_hinhanh);
            viewHolder.txtNoiDung = (TextView) view.findViewById(R.id.txtNoiDung);
            viewHolder.txtSoLuong = (TextView) view.findViewById(R.id.txtSoLuong);
            viewHolder.btnNoiDung = (Button) view.findViewById(R.id.btnNoiDung);
            viewHolder.btnMuon = (Button) view.findViewById(R.id.btnMuon);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        if (arraylist.get(position).getSoLuong() > 0) {
            if (listSach.get(position).getHinhAnh() != "") {
                File imgFile = new File(listSach.get(position).getHinhAnh());
                if (imgFile.exists()) {
                    Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                    viewHolder.img_hinhanh.setImageBitmap(myBitmap);
                }
            }
            viewHolder.txtNoiDung.setText(listSach.get(position).getTenSach());
            viewHolder.txtSoLuong.setText("Còn lại : " + String.valueOf(listSach.get(position).getSoLuong()));
            viewHolder.btnNoiDung.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Activity_ThemSach.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("tensach", listSach.get(position).getTenSach());
                    bundle.putInt("maloai", position);
                    bundle.putInt("masach", listSach.get(position).getMaSach());
                    bundle.putString("tacgia", listSach.get(position).getTacGia());
                    bundle.putString("hinhanh", listSach.get(position).getHinhAnh());
                    bundle.putInt("namxuatban", listSach.get(position).getNamXuatBan());
                    bundle.putInt("soluong", listSach.get(position).getSoLuong());
                    bundle.putString("noidung", listSach.get(position).getNoiDung());
                    bundle.putString("ghichu", listSach.get(position).getGhiChu());
                    bundle.putInt("check", 1);
                    intent.putExtra("data", bundle);
                    mContext.startActivity(intent);
                }
            });
            viewHolder.btnMuon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ActivityLogin.maquyen == 2) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        String currentDateandTime = sdf.format(new Date());
                        DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(mContext);
                        databaseAccsess.open();
                        MuonSach muonSach = new MuonSach(listSach.get(position).getMaSach(), ActivityLogin.madocgia, currentDateandTime, "", false, false, "");
                        databaseAccsess.ThemThongTin_MuonSach(muonSach);
                        Toast.makeText(mContext, "Đang chờ xử lý", Toast.LENGTH_SHORT).show();
                        mContext.startActivity(new Intent(mContext,Activity_KhoSach.class));
                    }
                }
            });
        }

        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        listSach.clear();
        if (charText.length() == 0) {
            listSach.addAll(arraylist);
        } else {
            for (Sach wp : arraylist) {
                if (wp.getTenSach().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listSach.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private class ViewHolder {
        ImageView img_hinhanh;
        TextView txtNoiDung;
        TextView txtSoLuong;
        Button btnNoiDung;
        Button btnMuon;
    }
}
