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
        Connection con = null;
        Statement st = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            String sql = String.format("INSERT INTO NhanVien (maNV, hoTen, chucVu, SDT, diaChi, email, ngaySinh, ngayVaoLam, luongCoBan, heSoLuong) " +
                            "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', %f, %f)",
                    nv.getMaNV(), nv.getHoTen(), nv.getChucVu(), nv.getSoDT(), nv.getDiaChi(), nv.getEmail(),
                    new java.sql.Date(nv.getNgaySinh().getTime()), new java.sql.Date(nv.getNgayVaoLam().getTime()),
                    nv.getLuongCoBan(), nv.getHeSoLuong());

            st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);

            if (rowsAffected > 0) {
                dsNV.add(nv);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //xoa mot nhan vien
    public boolean xoaNV(String maNV) {
        Connection con = null;
        Statement st = null;

        try {
            con = ConnectDB.getInstance().getConnection();
            NhanVien nvToRemove = timKiem(maNV);
            if (nvToRemove == null) {
                return false;
            }
            String sql = "DELETE FROM NhanVien WHERE maNV = '" + maNV + "'";
            st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);

            if (rowsAffected > 0) {
                dsNV.remove(nvToRemove);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        Connection con = null;
        Statement st = null;

        try {
            con = ConnectDB.getInstance().getConnection();

            // Tìm nhân viên cũ trong danh sách
            NhanVien nvOld = timKiem(maNVOld);
            if (nvOld == null) {
                return false; // Nhân viên không tồn tại
            }

            // Cập nhật thông tin trong danh sách
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

            String sql = String.format("UPDATE NhanVien SET maNV = '%s', hoTen = '%s', chucVu = '%s', SDT = '%s', diaChi = '%s', email = '%s', ngaySinh = '%s', ngayVaoLam = '%s', luongCoBan = %f, heSoLuong = %f WHERE maNV = '%s'",
                    nvNew.getMaNV(), nvNew.getHoTen(), nvNew.getChucVu(), nvNew.getSoDT(), nvNew.getDiaChi(), nvNew.getEmail(),
                    new java.sql.Date(nvNew.getNgaySinh().getTime()), new java.sql.Date(nvNew.getNgayVaoLam().getTime()),
                    nvNew.getLuongCoBan(), nvNew.getHeSoLuong(), maNVOld);

            st = con.createStatement();
            int rowsAffected = st.executeUpdate(sql);

            if (rowsAffected > 0) {
                dsNV.remove(nvOld);
                dsNV.add(nvNew);
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
