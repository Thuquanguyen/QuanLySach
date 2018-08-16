package com.example.ibct.quanlysach.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.ibct.quanlysach.Context.DocGia;
import com.example.ibct.quanlysach.Context.LoaiSach;
import com.example.ibct.quanlysach.Context.MuonSach;
import com.example.ibct.quanlysach.Context.NhanVien;
import com.example.ibct.quanlysach.Context.Sach;

import java.util.ArrayList;

public class DatabaseAccsess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccsess instance;
    Cursor c = null;

    private DatabaseAccsess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);

    }

    public static DatabaseAccsess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccsess(context);
        }
        return instance;
    }

    //open database
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    //close database
    public void close() {
        if (db != null) {
            this.db.close();
        }
    }

    //=========================================================================================================
    //Lấy thông tin doc gia
    public ArrayList<DocGia> getConten_DocGia() {
        ArrayList<DocGia> list = new ArrayList<>();
        c = db.rawQuery("select * from tbl_docgia", new String[]{});
        while (c.moveToNext()) {
            DocGia docGia = new DocGia(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4)
                    , Boolean.parseBoolean(c.getString(5)), c.getString(6), c.getString(7), c.getInt(8));
            list.add(docGia);
        }
        return list;
    }

    //Thêm thông tin độc giả
    public void ThemThongTin_DocGia(DocGia docGia) {
        String sql = "insert into tbl_docgia(HoVaTen,DiaChi,NamSinh,SoDienThoai,GioiTinh,TenDangNhap,MatKhau,MaQuyen) values('" + docGia.getHoVaTen() + "','" + docGia.getDiaChi() + "','" +
                docGia.getNamSinh() + "','" + docGia.getSoDienThoai() + "','" + docGia.getGioiTinh() + "','" + docGia.getTenDangNhap() + "','" + docGia.getMatKhau() + "','" + docGia.getMaQuyen() + "')";
        db.execSQL(sql);
    }

    //Sửa thông tin độc giả
    public void SuaThongTin_DocGia(DocGia docGia) {
        String sql = "update tbl_docgia set HoVaTen = '" + docGia.getHoVaTen() + "',DiaChi = '" + docGia.getDiaChi() + "',NamSinh = '" +
                docGia.getNamSinh() + "', SoDienThoai = '" + docGia.getSoDienThoai() + "',GioiTinh = '" + docGia.getGioiTinh() + "',TenDangNhap = '" + docGia.getTenDangNhap() + "',MatKhau = '" + docGia.getMatKhau() + "',MaQuyen = '" + docGia.getMaQuyen() + "' where MaDocGia = '" + docGia.getMaDocGia() + "'";
        db.execSQL(sql);
    }

    //Xóa thông tin độc giả
    public void XoaThongTin_DocGia(int id) {
        String sql = "delete from tbl_docgia where MaDocGia = '" + id + "'";
        db.execSQL(sql);
    }

    public String getContenDocGia(int id) {
        String docGia = null;
        c = db.rawQuery("select HoVaTen from tbl_docgia where MaDocGia = '" + id + "'", new String[]{});
        while (c.moveToNext()) {
            docGia = c.getString(1);
        }
        return docGia;
    }

    public int getUserCount(String tendangnhap) {
        String countQuery = "SELECT  * FROM tbl_docgia where TenDangNhap = '" + tendangnhap + "'";
        openHelper.getReadableDatabase();
        c = db.rawQuery(countQuery, null);
        return c.getCount();
    }

    //==================================================================================================================
    //Lấy thông tin loại sách
    public ArrayList<LoaiSach> getConten_LoaiSach() {
        ArrayList<LoaiSach> list = new ArrayList<>();
        c = db.rawQuery("select * from tbl_loai", new String[]{});
        while (c.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach(c.getInt(0), c.getString(1));
            list.add(loaiSach);
        }
        return list;
    }

    //Thêm thông tin loại sách
    public void ThemThongTin_LoaiSach(LoaiSach loaiSach) {
        String sql = "insert into tbl_loai(TenLoai) values('" + loaiSach.getTenLoai() + "')";
        db.execSQL(sql);
    }

    //Sửa thông tin loại sách
    public void SuaThongTin_LoaiSach(LoaiSach loaiSach) {
        String sql = "update tbl_loai set TenLoai = N'" + loaiSach.getTenLoai() + "' where MaLoai = '" + loaiSach.getMaLoai() + "'";
        db.execSQL(sql);
    }

    //Xóa thông tin loại sách
    public void XoaThongTin_LoaiSach(int id) {
        String sql = "delete from tbl_loai where MaLoai = '" + id + "'";
        db.execSQL(sql);
    }

    //===================================================================================================
    //Lấy thông tin mượn sách
    public ArrayList<MuonSach> getConten_MuonSach(Boolean tinhTrang,Boolean xacnhan) {
        ArrayList<MuonSach> list = new ArrayList<>();
        c = db.rawQuery("select * from tbl_muonsach where TinhTrang ='" + tinhTrang + "' and XacNhan = '"+xacnhan+"'", new String[]{});
        while (c.moveToNext()) {
            MuonSach muonSach = new MuonSach(c.getInt(0), c.getInt(1), c.getString(2), c.getString(3)
                    , Boolean.parseBoolean(c.getString(4)), Boolean.parseBoolean(c.getString(5)), c.getString(6));
            list.add(muonSach);
        }
        return list;
    }

    //Thêm thông tin mượn sách
    public void ThemThongTin_MuonSach(MuonSach muonSach) {
        String sql = "insert into tbl_muonsach values('" + muonSach.getMaSach() + "','" + muonSach.getMaDocGia() + "','" + muonSach.getNgayMuon() + "','" + muonSach.getNgayTra() + "','" + muonSach.getTinhTrang() + "','" + muonSach.getXacNhan() + "','" + muonSach.getGhiChu() + "')";
        db.execSQL(sql);
    }
    //Sửa thông tin loại mượn sách
    public void SuaThongTin_MuonSach(LoaiSach loaiSach) {
        String sql = "update tbl_loai set TenLoai = N'" + loaiSach.getTenLoai() + "' where MaLoai = '" + loaiSach.getMaLoai() + "'";
        db.execSQL(sql);
    }
    //Xóa thông tin mượn sách
    public void XoaThongTin_MuonSach(int maDocGia, int MaSach) {
        String sql = "delete from tbl_muonsach where MaSach = '" + MaSach + "' and MaDocGia = '" + maDocGia + "'";
        db.execSQL(sql);
    }

    //=============================================================================================================
    //Lấy thông tin nhân viên
    public ArrayList<NhanVien> getConten_NhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        c = db.rawQuery("select * from tbl_nhanvien", new String[]{});
        while (c.moveToNext()) {
            NhanVien nhanVien = new NhanVien(c.getInt(0), c.getString(1), c.getString(2), c.getInt(3), c.getString(4)
                    , Boolean.parseBoolean(c.getString(5)), c.getString(6), c.getString(7), c.getInt(0));
            list.add(nhanVien);
        }
        return list;
    }

    //Thêm thông tin nhân viên
    public void ThemThongTin_NhanVien(NhanVien nhanVien) {
        String sql = "insert into tbl_nhanvien(HoVaTen,DiaChi,NamSinh,SoDienThoai,GioiTinh,TenDangNhap,MatKhau,MaQuyen) values('" + nhanVien.getHoVaTen() + "','" + nhanVien.getDiaChi() + "','" +
                nhanVien.getNamSinh() + "','" + nhanVien.getSoDienThoai() + "','" + nhanVien.getGioiTinh() + "','" + nhanVien.getTenDangNhap() + "','" + nhanVien.getMatKhau() + "','" + nhanVien.getMaQuyen() + "')";
        db.execSQL(sql);
    }

    //Sửa thông tin nhân viên
    public void SuaThongTin_NhanVien(NhanVien nhanVien) {
        String sql = "update tbl_nhanvien set HoVaTen = '" + nhanVien.getHoVaTen() + "',DiaChi = '" + nhanVien.getDiaChi() + "',NamSinh = '" +
                nhanVien.getNamSinh() + "', SoDienThoai = '" + nhanVien.getSoDienThoai() + "',GioiTinh = '" + nhanVien.getGioiTinh() + "',TenDangNhap = '" + nhanVien.getTenDangNhap() + "'" +
                ",MatKhau = '" + nhanVien.getMatKhau() + "',MaQuyen = '" + nhanVien.getMaQuyen() + "' where MaNV = '" + nhanVien.getMaNV() + "'";
        db.execSQL(sql);
    }

    //Xóa thông tin nhân viên
    public void XoaThongTin_NhanVien(int id) {
        String sql = "delete from tbl_docgia where MaNV = '" + id + "'";
        db.execSQL(sql);
    }

    public String getContenNhanVien(int id) {
        String nhanVien = null;
        c = db.rawQuery("select HoVaTen from tbl_nhanvien where MaNV = '" + id + "'", new String[]{});
        while (c.moveToNext()) {
            nhanVien = c.getString(1);
        }
        return nhanVien;
    }

    public int getNhanVienCount(String tendangnhap) {
        String countQuery = "SELECT  * FROM tbl_nhanvien where TenDangNhap = '" + tendangnhap + "'";
        openHelper.getReadableDatabase();
        c = db.rawQuery(countQuery, null);
        return c.getCount();
    }

    //================================================================================================================
    //Lấy thông tin sách
    public ArrayList<Sach> getConten_Sach() {
        ArrayList<Sach> list = new ArrayList<>();
        c = db.rawQuery("select * from tbl_sach", new String[]{});
        while (c.moveToNext()) {
            Sach user = new Sach(c.getInt(0), c.getString(1), c.getInt(2), c.getString(3)
                    , c.getString(4), c.getInt(5), c.getInt(6), c.getString(7), c.getString(8));
            list.add(user);
        }
        return list;
    }

    //Thêm thông tin người dùng
    public void ThemThongTin_Sach(Sach sach) {
        String sql = "insert into tbl_sach(TenSach,MaLoai,TacGia,HinhAnh,NamXuatBan,SoLuong,NoiDung,GhiChu) values('" + sach.getTenSach() + "','" + sach.getMaLoai() + "','" +
                sach.getTacGia() + "','" + sach.getHinhAnh() + "','" + sach.getNamXuatBan() + "','" + sach.getSoLuong() + "','" + sach.getNoiDung() + "','" + sach.getGhiChu() + "')";
        db.execSQL(sql);
    }

    //Sửa thông tin người dùng
    public void SuaThongTin_Sach(Sach sach) {
        String sql = "update tbl_sach set TenSach = '" + sach.getTenSach() + "',MaLoai = '" + sach.getMaLoai() + "',TacGia = '" + sach.getTacGia() + "',HinhAnh = '" + sach.getHinhAnh() + "',NamXuatBan = '" + sach.getNamXuatBan() + "',SoLuong = '" + sach.getSoLuong() + "',NoiDung = '" + sach.getNoiDung() + "',GhiChu = '" + sach.getGhiChu() + "' where MaSach = '" + sach.getMaSach() + "'";
        db.execSQL(sql);
    }

    //Xóa thông tin người dùng
    public void XoaThongTin_Sach(int id) {
        String sql = "delete from tbl_sach where MaSach = '" + id + "'";
        db.execSQL(sql);
    }

    public String getContenSach(int id) {
        String sach = null;
        c = db.rawQuery("select TenSach from tbl_sach where MaSach = '" + id + "'", new String[]{});
        while (c.moveToNext()) {
            sach = c.getString(1);
        }
        return sach;
    }
}
