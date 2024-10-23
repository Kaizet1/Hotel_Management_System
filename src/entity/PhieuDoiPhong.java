package entity;

public class PhieuDoiPhong {
    private int maPDoiP;
    private KhachHang khachHang;
    private Phong phong;
    private String lyDo;

    public PhieuDoiPhong(int maPDoiP, KhachHang khachHang, Phong phong, String lyDo) {
        this.maPDoiP = maPDoiP;
        this.khachHang = khachHang;
        this.phong = phong;
        this.lyDo = lyDo;
    }

    public int getMaPDoiP() {
        return maPDoiP;
    }

    public void setMaPDoiP(int maPDoiP) {
        this.maPDoiP = maPDoiP;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public Phong getPhong() {
        return phong;
    }

    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
