package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class NhanVien_DAO {
    private ArrayList<NhanVien> dsNV;
    public NhanVien_DAO (){
        dsNV = new ArrayList<NhanVien>();
    }

    public ArrayList<NhanVien> getDSNhanVien() {
        try{
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from NhanVien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String maNV = rs.getString("maNV");
                String hoTen = rs.getString("hoTen");
                String chucVu = rs.getString("chucVu");
                Date ngaySinh = rs.getDate("ngaySinh");
                Date ngayVaoLam = rs.getDate("ngayVaoLam");
                String SDT = rs.getString("SDT");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                double luongCoBan = rs.getDouble("luongCoBan");
                double heSoLuong = rs.getDouble("heSoLuong");
                NhanVien nv= new NhanVien(maNV, hoTen, chucVu, SDT, diaChi, email, ngaySinh, ngayVaoLam, luongCoBan, heSoLuong);
                dsNV.add(nv);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsNV;
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
    public NhanVien timKiem(String maKH) {
        for (NhanVien nv : dsNV) {


            if (nv.getMaNV().equalsIgnoreCase(maKH)) {
                return nv;
            }
        }
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
