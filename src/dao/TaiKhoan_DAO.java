package dao;

import entity.TaiKhoan;

import java.util.ArrayList;

public class TaiKhoan_DAO {
    private ArrayList<TaiKhoan> dsTK;
    public TaiKhoan_DAO() {
        dsTK = new ArrayList<TaiKhoan>();
    }
    //them mot tai khoan
    public boolean themTK(TaiKhoan tk) {
        if (dsTK.contains(tk)) {
            return false;
        }
        return dsTK.add(tk);
    }
    //xoa mot tai khoan
    public boolean xoaTK(int i) {
        if (i >= 0 && i < dsTK.size()) {
            dsTK.remove(i);
            return true;
        }
        return false;
    }
    public ArrayList<TaiKhoan> getDSTK() {
        return dsTK;
    }
    public TaiKhoan GetTK(int i) {
        if (i < 0 || i > dsTK.size()) {
            return null;
        }
        return dsTK.get(i);
    }
    //tim kiem tai khoan
    public TaiKhoan timKiem (String tenDN) {
        TaiKhoan tk = new TaiKhoan(tenDN);
        if (dsTK.contains(tk))
            return dsTK.get(dsTK.indexOf(tk));
        return null;
    }
    //cap nhat nhan vien
    public boolean capNhatTaiKhoan (String tenDNOld, TaiKhoan tkNew) {
        TaiKhoan tkOld = new TaiKhoan(tenDNOld);
        if (dsTK.contains(tenDNOld)) {
            tkOld = dsTK.get(dsTK.indexOf(tkOld));
            tkOld.setTenDN(tkNew.getTenDN());
            tkOld.setMatKhau(tkNew.getMatKhau());
            return true;
        }
        return false;
    }
}
