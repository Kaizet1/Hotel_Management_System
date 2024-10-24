package entity;

public class Phong {
    private String maPhong;
    private String tenPhong;
    private double giaPhong;
    private String loaiPhong;
    private int trangThai;
    private String moTa;
    private int soNguoi;

    public Phong(String maPhong, String tenPhong, double giaPhong, String loaiPhong, int trangThai, String moTa,
                 int soNguoi) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.giaPhong = giaPhong;
        this.loaiPhong = loaiPhong;
        this.trangThai = trangThai;
        this.moTa = moTa;
        this.soNguoi = soNguoi;
    }

    public Phong(String maPhong) {
        this(maPhong, "tenPhong", 0.0, "loaiPhong", 0, "moTa", 0);
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public double getGiaPhong() {
        return giaPhong;
    }

    public void setGiaPhong(double giaPhong) {
        this.giaPhong = giaPhong;
    }

    public String getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(String loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public String getTrangThai() {
        String trangThaiStr = "";
        switch (trangThai) {
            case 0:
                trangThaiStr = "Còn trống";
                break;
            case 1:
                trangThaiStr = "Đã đặt trước";
                break;
            case 2:
                trangThaiStr = "Đang sử dụng";
                break;
            case 3:
                trangThaiStr = "Đang sửa chữa";
                break;
            default:
                break;
        }
        return trangThaiStr;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoNguoi() {
        return soNguoi;
    }

    public void setSoNguoi(int soNguoi) {
        this.soNguoi = soNguoi;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maPhong == null) ? 0 : maPhong.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Phong other = (Phong) obj;
        if (maPhong == null) {
            if (other.maPhong != null)
                return false;
        } else if (!maPhong.equals(other.maPhong))
            return false;
        return true;
    }


}
