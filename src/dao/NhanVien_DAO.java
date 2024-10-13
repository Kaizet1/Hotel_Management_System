package dao;

import entity.NhanVien;

import java.util.ArrayList;

public class NhanVien_DAO {
    private ArrayList<NhanVien> dsNV;
    public NhanVien_DAO (){
        dsNV = new ArrayList<NhanVien>();
    }
    //them mot nhan vien
    public boolean themNV(NhanVien nv) {
        if (dsNV.contains(nv)) {
            return false;
        }
        return dsNV.add(nv);
    }
    //xoa mot nhan vien
    public boolean xoaNV(int i) {
        if (i >= 0 && i < dsNV.size()) {
            dsNV.remove(i);
            return true;
        }
        return false;
    }
    public ArrayList<NhanVien> getDsNV() {
        return dsNV;
    }
    public NhanVien GetNV(int i) {
        if (i < 0 || i > dsNV.size()) {
            return null;
        }
        return dsNV.get(i);
    }
    //tim kiem NV
    public NhanVien timKiem (String maNV) {
        NhanVien nv = new NhanVien(maNV);
        if (dsNV.contains(nv))
            return dsNV.get(dsNV.indexOf(nv));
        return null;
    }
    //cap nhat nhan vien
    public boolean capNhatNhanVien(String maNVOld, NhanVien nvNew) {
        NhanVien nvOld = new NhanVien(maNVOld);
        if (dsNV.contains(nvOld)) {
            nvOld = dsNV.get(dsNV.indexOf(nvOld));
            nvOld.setMaNV(nvNew.getMaNV());
            nvOld.setHoTen(nvNew.getHoTen());
            nvOld.setChucVu(nvNew.getChucVu());
            nvOld.setSoDT(nvNew.getSoDT());
            nvOld.setDiaChi(nvNew.getDiaChi());
            nvOld.setEmail(nvNew.getEmail());
            nvOld.setNgaySinh(nvNew.getNgaySinh());
            nvOld.setNgayVaoLam(nvNew.getNgayVaoLam());
            nvOld.setLuongCoBan(nvNew.getLuongCoBan());
            nvOld.setHeSoLuong(nvNew.getHeSoLuong());
            return true;
        }
        return false;
    }
}
