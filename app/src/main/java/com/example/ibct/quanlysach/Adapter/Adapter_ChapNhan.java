package com.example.ibct.quanlysach.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.ibct.quanlysach.Activity.ActivityLogin;
import com.example.ibct.quanlysach.Activity.MainActivity;
import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;
import com.example.ibct.quanlysach.database.DatabaseAccsess;

import java.util.ArrayList;

public class Adapter_ChapNhan extends ArrayAdapter<MuonSach> {
    private Context mContext;
    private int id;
    private ArrayList<MuonSach> arraylist;
    private ArrayList<DocGia> arraylist_DocGia;
    private ArrayList<Sach> arraylist_Sach;

    public Adapter_ChapNhan(@NonNull Context context, int resource, @NonNull ArrayList<MuonSach> objects, @NonNull ArrayList<DocGia> objects_1, @NonNull ArrayList<Sach> objects_2) {
        super(context, resource, objects);
        this.mContext = context;
        this.id = resource;
        this.arraylist = objects;
        this.arraylist_DocGia = objects_1;
        this.arraylist_Sach = objects_2;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
            viewHolder.txtTenDocGia = (TextView) view.findViewById(R.id.txtTenDocGia);
            viewHolder.txtTenSach = (TextView) view.findViewById(R.id.txttenSach);
            viewHolder.txtNgayMuon = (TextView) view.findViewById(R.id.txtNgayMuon);
            viewHolder.btnChapnhan = (Button) view.findViewById(R.id.btn_ChapNhan);
            viewHolder.btnKhongChapnhan = (Button) view.findViewById(R.id.btn_KhongChapNhan);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (arraylist_DocGia.size() > 0)
            for (int i = 0; i < arraylist_DocGia.size(); i++) {
                if (arraylist.get(position).getMaDocGia() == arraylist_DocGia.get(i).getMaDocGia()) {
                    viewHolder.txtTenDocGia.setText(arraylist_DocGia.get(i).getHoVaTen());
                    break;
                }
            }

        if (arraylist_Sach.size() > 0)
            for (int i = 0; i < arraylist_Sach.size(); i++) {
                if (arraylist.get(position).getMaDocGia() == arraylist_Sach.get(i).getMaSach()) {
                    viewHolder.txtTenSach.setText(arraylist_Sach.get(i).getTenSach());
                    break;
                }
            }
        viewHolder.txtNgayMuon.setText(arraylist.get(position).getNgayMuon());
        viewHolder.btnChapnhan.setText("Chấp nhận");
        viewHolder.btnChapnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(mContext);
                databaseAccsess.open();
                MuonSach muonSach=new MuonSach(arraylist.get(position).getMaSach(),ActivityLogin.madocgia,arraylist.get(position).getNgayMuon(),"",false,true,"chưa có");
                databaseAccsess.XoaThongTin_MuonSach(arraylist.get(position).getMaDocGia(), arraylist.get(position).getMaSach());
                mContext.startActivity(new Intent(mContext, MainActivity.class));
            }
        });
        viewHolder.btnKhongChapnhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccsess databaseAccsess = DatabaseAccsess.getInstance(mContext);
                databaseAccsess.open();
                databaseAccsess.XoaThongTin_MuonSach(arraylist.get(position).getMaDocGia(), arraylist.get(position).getMaSach());
                mContext.startActivity(new Intent(mContext, MainActivity.class));
            }
        });
        return view;
    }


    private class ViewHolder {
        TextView txtTenDocGia;
        TextView txtTenSach;
        TextView txtNgayMuon;
        Button btnChapnhan;
        Button btnKhongChapnhan;
    }
}
