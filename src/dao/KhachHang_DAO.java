package dao;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.Phong;

public class KhachHang_DAO {
    private ArrayList<KhachHang> dsKH;

    public KhachHang_DAO() {
        dsKH = new ArrayList<KhachHang>();
    }

    public ArrayList<KhachHang> getDSKhachHang() {
        try{
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from KhachHang";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String SDT = rs.getString("SDT");
                String soCCCD = rs.getString("soCCCD");
                String email = rs.getString("email");
                KhachHang kh= new KhachHang(maKH, tenKH, diaChi, SDT, email, soCCCD);
                dsKH.add(kh);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsKH;
    }

    public boolean themKH(KhachHang kh) {
        if (dsKH.contains(kh)) {
            return false;
        }
        dsKH.add(kh);
        return true;
    }

    public boolean xoaKH(String maKH) {
        KhachHang kh = new KhachHang(maKH);
        if (dsKH.contains(kh)) {
            dsKH.remove(maKH);
            return true;
        }
        return false;
    }

    public KhachHang timKiem(String maKH) {
        KhachHang kh = new KhachHang(maKH);
        if (dsKH.contains(kh)) {
            return dsKH.get(dsKH.indexOf(kh));
        }
        return null;
    }

    public KhachHang searchKhachHangBangSDT(String SDT) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE SDT = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, SDT);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String soCCCD = rs.getString("soCCCD");
                kh = new KhachHang(maKH, tenKH, diaChi, SDT, email, soCCCD);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }


    public boolean suaKH(String maKH, KhachHang khNew) {
        KhachHang khachHang = new KhachHang(maKH);
        if (dsKH.contains(khachHang)) {
            dsKH.set(dsKH.indexOf(khachHang), khNew);
            khachHang.setHoTen(khNew.getHoTen());
            khachHang.setDiaChi(khNew.getDiaChi());
            khachHang.setSdt(khNew.getSdt());
            khachHang.setEmail(khNew.getEmail());
            khachHang.setcCCD(khNew.getcCCD());
            return true;
        }
        return false;
    }

    public KhachHang GetKH(int i) {
        if (i >= 0 && i < dsKH.size()) {
            return dsKH.get(i);
        }
        return null;
    }
}
