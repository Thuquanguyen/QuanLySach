package com.example.ibct.quanlysach.Context;

public class NhanVien {
    private int MaNV;
    private String HoVaTen;
    private String DiaChi;
    private int NamSinh;
    private String SoDienThoai;
    private Boolean GioiTinh;
    private String TenDangNhap;
    private String MatKhau;
    private int MaQuyen;

    public NhanVien() {
    }

    public NhanVien(String hoVaTen, String diaChi, int namSinh, String soDienThoai, Boolean gioiTinh, String tenDangNhap, String matKhau, int maQuyen) {
        HoVaTen = hoVaTen;
        DiaChi = diaChi;
        NamSinh = namSinh;
        SoDienThoai = soDienThoai;
        GioiTinh = gioiTinh;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        MaQuyen = maQuyen;
    }

    public NhanVien(int maNV, String hoVaTen, String diaChi, int namSinh, String soDienThoai, Boolean gioiTinh, String tenDangNhap, String matKhau, int maQuyen) {
        MaNV = maNV;
        HoVaTen = hoVaTen;
        DiaChi = diaChi;
        NamSinh = namSinh;
        SoDienThoai = soDienThoai;
        GioiTinh = gioiTinh;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        MaQuyen = maQuyen;
    }

    public int getMaNV() {
        return MaNV;
    }

    public void setMaNV(int maNV) {
        MaNV = maNV;
    }

    public String getHoVaTen() {
        return HoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        HoVaTen = hoVaTen;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public int getNamSinh() {
        return NamSinh;
    }

    public void setNamSinh(int namSinh) {
        NamSinh = namSinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public Boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Boolean gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public int getMaQuyen() {
        return MaQuyen;
    }

    public void setMaQuyen(int maQuyen) {
        MaQuyen = maQuyen;
    }
}
