package com.example.ibct.quanlysach.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.Sach;
import com.example.ibct.quanlysach.R;

import java.util.ArrayList;

public class Adapter_DaTra extends ArrayAdapter<MuonSach> {
    private Context mContext;
    private int id;
    private ArrayList<MuonSach> arraylist;
    private ArrayList<DocGia> arraylist_DocGia;
    private ArrayList<Sach> arraylist_Sach;

    public Adapter_DaTra(@NonNull Context context, int resource, @NonNull ArrayList<MuonSach> objects, @NonNull ArrayList<DocGia> objects_1, @NonNull ArrayList<Sach> objects_2) {
        super(context, resource, objects);
        this.mContext = context;
        this.id = resource;
        this.arraylist = objects;
        this.arraylist_DocGia = objects_1;
        this.arraylist_Sach = objects_2;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(id, null);
            viewHolder.txtTenDocGia = (TextView) view.findViewById(R.id.txt_TenDocGia);
            viewHolder.txtTenSach = (TextView) view.findViewById(R.id.txtTenSach);
            viewHolder.txtNgayMuon = (TextView) view.findViewById(R.id.txtNgayMuon);
            viewHolder.txtNgayTra = (TextView) view.findViewById(R.id.txtTinhTrang);
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
        viewHolder.txtNgayTra.setText(arraylist.get(position).getNgayTra());
        return view;
    }


    private class ViewHolder {
        TextView txtTenDocGia;
        TextView txtTenSach;
        TextView txtNgayMuon;
        TextView txtNgayTra;
    }
}
