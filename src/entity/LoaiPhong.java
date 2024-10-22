package entity;

public class LoaiPhong {
    private String tenLoaiPhong;
    private String moTa;

    public LoaiPhong(String tenLoaiPhong, String moTa) {
        this.tenLoaiPhong = tenLoaiPhong;
        this.moTa = moTa;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }
}
