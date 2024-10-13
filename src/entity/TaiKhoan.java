package entity;

import java.util.Objects;

public class TaiKhoan {
    private String tenDN;
    private String matKhau;

    public TaiKhoan(String tenDN, String matKhau) {
        this.tenDN = tenDN;
        this.matKhau = matKhau;
    }
    public TaiKhoan(String tenDN) {
        this(tenDN, "matKhau");
    }

    public String getTenDN() {
        return tenDN;
    }

    public void setTenDN(String tenDN) {
        this.tenDN = tenDN;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TaiKhoan taiKhoan)) return false;
        return Objects.equals(getTenDN(), taiKhoan.getTenDN());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTenDN());
    }
}
