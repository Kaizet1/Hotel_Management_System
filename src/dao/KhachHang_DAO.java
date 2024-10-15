package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import connectDB.ConnectDB;
import entity.KhachHang;
import java.util.*;
import java.sql.PreparedStatement;

public class KhachHang_DAO {
 private ArrayList<KhachHang> dsKhachHang;
 private KhachHang khachHang;
public KhachHang_DAO() {
    dsKhachHang = new ArrayList<KhachHang>();
    khachHang = new KhachHang();
}
public ArrayList<KhachHang> getDsKhachHang() {
    try {
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "select * from KhachHang";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()) {
            String maKH = rs.getString(1);
            String hoTen = rs.getString(2);
            String diaChi = rs.getString(3);
            String sdt = rs.getString(4);
            String email = rs.getString(5);
            String cCCD = rs.getString(6);
            KhachHang kh = new KhachHang(maKH, hoTen, diaChi, sdt, email, cCCD);
            dsKhachHang.add(kh);
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKhachHang;
    }
public boolean createKhachHang(KhachHang kh) {
    ConnectDB.getInstance();
    Connection con = ConnectDB.getConnection();
    PreparedStatement ps = null;
    int n = 0;
    try{
        ps = con.prepareStatement("insert into" + " KhachHang values(?,?,?,?,?,?)");
        ps.setString(1, kh.getMaKH());
        ps.setString(2, kh.getHoTen());
        ps.setString(3, kh.getDiaChi());
        ps.setString(4, kh.getSdt());
        ps.setString(5, kh.getEmail());
        ps.setString(6, kh.getcCCD());
        n = ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return n > 0;
    }
public boolean updateKhachHang(KhachHang kh) {
    ConnectDB.getInstance();
    Connection con = ConnectDB.getConnection();
    PreparedStatement ps = null;
    int n = 0;
    try{
        ps = con.prepareStatement("update KhachHang set HoTen = ?, DiaChi = ?, SDT = ?, Email = ?, CCCD = ? where MaKH = ?");
        ps.setString(1, kh.getHoTen());
        ps.setString(2, kh.getDiaChi());
        ps.setString(3, kh.getSdt());
        ps.setString(4, kh.getEmail());
        ps.setString(5, kh.getcCCD());
        ps.setString(6, kh.getMaKH());
        n = ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return n > 0;
    }
public boolean deleteKhachHang(String maKH) {
    ConnectDB.getInstance().connect();
    PreparedStatement ps = null;
    int n = 0;
    try{
        ps = ConnectDB.getConnection().prepareStatement("delete from KhachHang where MaKH = ?");
        ps.setString(1, maKH);
        n = ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return n > 0;
    }
public KhachHang searchKhachHang(String maKH) {
        ConnectDB.getInstance().connect();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            ps = ConnectDB.getConnection().prepareStatement("select * from KhachHang where MaKH = ?");
            ps.setString(1, maKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hoTen = rs.getString(2);
                String diaChi = rs.getString(3);
                String sdt = rs.getString(4);
                String email = rs.getString(5);
                String cCCD = rs.getString(6);
                khachHang = new KhachHang(maKH, hoTen, diaChi, sdt, email, cCCD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachHang;
    }
}
