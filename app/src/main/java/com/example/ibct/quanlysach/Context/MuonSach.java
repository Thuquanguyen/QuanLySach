package com.example.ibct.quanlysach.Context;

import java.util.Date;

public class MuonSach {
    private int MaSach;
    private int MaDocGia;
    private String NgayMuon;
    private String NgayTra;
    private Boolean TinhTrang;
    private Boolean XacNhan;
    private String GhiChu;

    public MuonSach() {
    }

    public MuonSach(int maSach, int maDocGia, String ngayMuon, String ngayTra, Boolean tinhTrang, Boolean xacNhan, String ghiChu) {
        MaSach = maSach;
        MaDocGia = maDocGia;
        NgayMuon = ngayMuon;
        NgayTra = ngayTra;
        TinhTrang = tinhTrang;
        XacNhan = xacNhan;
        GhiChu = ghiChu;
    }

    public int getMaSach() {
        return MaSach;
    }

    public void setMaSach(int maSach) {
        MaSach = maSach;
    }

    public int getMaDocGia() {
        return MaDocGia;
    }

    public void setMaDocGia(int maDocGia) {
        MaDocGia = maDocGia;
    }

    public String getNgayMuon() {
        return NgayMuon;
    }

    public void setNgayMuon(String ngayMuon) {
        NgayMuon = ngayMuon;
    }

    public String getNgayTra() {
        return NgayTra;
    }

    public void setNgayTra(String ngayTra) {
        NgayTra = ngayTra;
    }

    public Boolean getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(Boolean tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public Boolean getXacNhan() {
        return XacNhan;
    }

    public void setXacNhan(Boolean xacNhan) {
        XacNhan = xacNhan;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }
}
