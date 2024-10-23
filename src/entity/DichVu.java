package entity;

import java.util.Objects;

public class DichVu {
private String maDV;
private String tenDV;
private double giaDV;
private String moTa;

    public DichVu(String maDV, String tenDV, double giaDV, String moTa) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaDV = giaDV;
        this.moTa = moTa;
    }

    public DichVu() {
    }
    public DichVu(String maDV) {
        this(maDV, "", 0.0, "");
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGiaDV() {
        return giaDV;
    }

    public void setGiaDV(double giaDV) {
        this.giaDV = giaDV;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((maDV == null) ? 0 : maDV.hashCode());
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
       DichVu other = (DichVu) obj;
        if (maDV == null) {
            if (other.maDV != null)
                return false;
        } else if (!maDV.equals(other.maDV))
            return false;
        return true;
    }

}
