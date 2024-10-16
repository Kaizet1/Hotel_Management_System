package entity;

public class KhachHang {
    private String maKH;
    private String hoTen;
    private String diaChi;
    private String sdt;
    private String email;
    private String cCCD;
    //
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getcCCD() {
        return cCCD;
    }

    public void setcCCD(String cCCD) {
        this.cCCD = cCCD;
    }

    public KhachHang(String maKH, String hoTen, String diaChi, String sdt, String email, String cCCD) {

        this.maKH = maKH;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.cCCD = cCCD;
    }

    public KhachHang (String maKH){
        this(maKH,"hoTen","diaChi","sdt","email","cCCD");  
    }

public KhachHang() {
        //TODO Auto-generated constructor stub
    }

@Override
public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((maKH == null) ? 0 : maKH.hashCode());
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
    KhachHang other = (KhachHang) obj;
    if (maKH == null) {
        if (other.maKH != null)
            return false;
    } else if (!maKH.equals(other.maKH))
        return false;
    return true;
}

}
