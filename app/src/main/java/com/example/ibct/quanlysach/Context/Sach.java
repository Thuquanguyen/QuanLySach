package com.example.ibct.quanlysach.Context;

public class Sach {
    private int MaSach;
    private String TenSach;
    private int MaLoai;
    private String TacGia;
    private String HinhAnh;
    private int NamXuatBan;
    private int SoLuong;
    private String NoiDung;
    private String GhiChu;

    public Sach(int maSach, String tenSach, int maLoai, String tacGia, String hinhAnh, int namXuatBan, int soLuong, String noiDung, String ghiChu) {
        MaSach = maSach;
        TenSach = tenSach;
        MaLoai = maLoai;
        TacGia = tacGia;
        HinhAnh = hinhAnh;
        NamXuatBan = namXuatBan;
        SoLuong = soLuong;
        NoiDung = noiDung;
        GhiChu = ghiChu;
    }

    public Sach(String tenSach, int maLoai, String tacGia, String hinhAnh, int namXuatBan, int soLuong, String noiDung, String ghiChu) {
        TenSach = tenSach;
        MaLoai = maLoai;
        TacGia = tacGia;
        HinhAnh = hinhAnh;
        NamXuatBan = namXuatBan;
        SoLuong = soLuong;
        NoiDung = noiDung;
        GhiChu = ghiChu;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public int getMaLoai() {
        return MaLoai;
    }

    public void setMaLoai(int maLoai) {
        MaLoai = maLoai;
    }

    public String getTacGia() {
        return TacGia;
    }

    public void setTacGia(String tacGia) {
        TacGia = tacGia;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        NamXuatBan = namXuatBan;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
