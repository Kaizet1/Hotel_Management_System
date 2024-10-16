package dao;


import connectDB.ConnectDB;
import entity.Phong;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.PreparedStatement;
public class Phong_DAO {
private ArrayList<Phong> dsPhong;
private Phong phong;
public Phong_DAO() {
    dsPhong = new ArrayList<>();
    phong = new Phong();
}
public ArrayList<Phong> getDsPhong() {
    try{
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "select * from Phong";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            String maPhong = rs.getString(1);
            String tenPhong = rs.getString(2);
            double giaPhong = rs.getDouble(3);
            String loaiPhong = rs.getString(4);
            String trangThai = rs.getString(5);
            String moTa = rs.getString(6);
            int soNguoi = rs.getInt(7);

            Phong p= new Phong(maPhong, tenPhong, giaPhong, loaiPhong, trangThai, moTa, soNguoi);
            dsPhong.add(p);
        }

    }catch (SQLException e){
        e.printStackTrace();
    }
    return dsPhong;
}
public boolean createPhong(Phong p){
    ConnectDB.getInstance();
    Connection con = ConnectDB.getConnection();
    PreparedStatement ps = null;
    int n = 0;
    try{
        ps = con.prepareStatement("insert into" + " Phong values(?,?,?,?,?,?,?)");
        ps.setString(1, p.getMaPhong());
        ps.setString(2, p.getTenPhong());
        ps.setDouble(3, p.getGiaPhong());
        ps.setString(4, p.getLoaiPhong());
        ps.setString(5, p.getTrangThai());
        ps.setString(6, p.getMoTa());
        ps.setInt(7, p.getSoNguoi());
        n = ps.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return n > 0;
}
    public boolean updatePhong(Phong p) {
        ConnectDB.getInstance();
        Connection con = ConnectDB.getConnection();
        PreparedStatement ps = null;
        int n = 0;
        try{
            ps = con.prepareStatement("update Phong set TenPhong = ?, GiaPhong = ?, LoaiPhong = ?, TrangThai = ?, MoTa = ?, SoNguoi = ? where MaPhong = ?");
            ps.setString(1, p.getTenPhong());
            ps.setDouble(2, p.getGiaPhong());
            ps.setString(3,p.getLoaiPhong());
            ps.setString(4, p.getTrangThai());
            ps.setString(5, p.getMoTa());
            ps.setInt(6, p.getSoNguoi());
            ps.setString(7, p.getMaPhong());
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public boolean deletePhong(String maPhong) {
        ConnectDB.getInstance().connect();
        PreparedStatement ps = null;
        int n = 0;
        try{
            ps = ConnectDB.getConnection().prepareStatement("delete from Phong where MaPhong = ?");
            ps.setString(1, maPhong);
            n = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return n > 0;
    }
    public  Phong searchPhong(String maPhong) {
    ConnectDB.getInstance().connect();
    PreparedStatement ps = null;
    ResultSet rs = null;
    try{
        ps = ConnectDB.getConnection().prepareStatement("select * from Phong where MaPhong = ?");
        ps.setString(1, maPhong);
        rs = ps.executeQuery();
        while (rs.next()){
            String tenPhong = rs.getString(2);
            double giaPhong = rs.getDouble(3);
            String loaiPhong = rs.getString(4);
            String trangThai = rs.getString(5);
            String moTa = rs.getString(6);
            int soNguoi = rs.getInt(7);
            phong = new Phong(maPhong, tenPhong, giaPhong, loaiPhong, trangThai, moTa, soNguoi);
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return phong;
    }
}
