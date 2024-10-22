package entity;

import java.util.Date;

public class PhieuDatPhong {
    private String maPDP;
    private int tinhTrangPDP;
    private Date ngayDat;
    private Date ngayDen;
    private Date ngayDi;
    private KhachHang khachHang;
    private NhanVien nhanVien;
    private Phong phong;

    public PhieuDatPhong(String maPDP) {
    }

    public PhieuDatPhong(String maPDP, Phong phong, NhanVien nhanVien, KhachHang khachHang, Date ngayDi, Date ngayDen, Date ngayDat, int tinhTrangPDP) {
        this.maPDP = maPDP;
        this.phong = phong;
        this.nhanVien = nhanVien;
        this.khachHang = khachHang;
        this.ngayDi = ngayDi;
        this.ngayDen = ngayDen;
        this.ngayDat = ngayDat;
        this.tinhTrangPDP = tinhTrangPDP;
    }

    public String getMaPDP() {
        return maPDP;
    }

    public void setMaPDP(String maPDP) {
        this.maPDP = maPDP;
    }

    public String getTinhTrangPDP() {
        String tinhTrangStr = "";
        switch (tinhTrangPDP) {
            case 0:
                tinhTrangStr =  "Đặt trước";
            break;
            case 1:
                tinhTrangStr =  "Đã nhận phòng";
            break;
            case 2:
                tinhTrangStr  =  "Đã hủy";
            break;
            default:
                break;
        }
        return tinhTrangStr;
    }

    public void setTinhTrangPDP(int tinhTrangPDP) {
        this.tinhTrangPDP = tinhTrangPDP;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public Date getNgayDen() {
        return ngayDen;
    }

    public void setNgayDen(Date ngayDen) {
        this.ngayDen = ngayDen;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }
}
