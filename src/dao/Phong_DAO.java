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
    public Phong_DAO() {
        dsPhong = new ArrayList<>();

    }
    public ArrayList<Phong> getDSPhong() {
        try{
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from Phong";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String maPhong = rs.getString("maPhong");
                String tenPhong = rs.getString("tenPhong");
                String loaiPhong = rs.getString("maLoai");
                double giaPhong = rs.getDouble("giaPhong");
                int trangThai = rs.getInt("tinhTrang");
                int soNguoi = rs.getInt("soNguoi");
                String moTa = rs.getString("moTa");
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

    public ArrayList<Phong> searchPhong(String maPhong, String tenPhong, String loaiPhong, int soNguoi, int trangThai) throws SQLException {
        ArrayList<Phong> dsPhong = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String query = "SELECT * FROM Phong WHERE 1=1";

            if (maPhong != null && !maPhong.isEmpty()) {
                query += " AND maPhong LIKE ?";
            }
            if (tenPhong != null && !tenPhong.isEmpty()) {
                query += " AND tenPhong LIKE ?";
            }
            if (loaiPhong != null && !loaiPhong.equals("Tất cả")) {
                query += " AND maLoai = ?";
            }
            if (soNguoi != -1) {
                query += " AND soNguoi = ?";
            }
            if (trangThai != -1) {
                query += " AND tinhTrang = ?";
            }
            PreparedStatement ps = con.prepareStatement(query);

            int paramIndex = 1;

            // Thiết lập giá trị cho các điều kiện tìm kiếm
            if (maPhong != null && !maPhong.isEmpty()) {
                ps.setString(paramIndex++, "%" + maPhong + "%");
            }
            if (tenPhong != null && !tenPhong.isEmpty()) {
                ps.setString(paramIndex++, "%" + tenPhong + "%");
            }
            if (loaiPhong != null && !loaiPhong.equals("Tất cả")) {
                ps.setString(paramIndex++, loaiPhong);
            }
            if (soNguoi != -1) {
                ps.setInt(paramIndex++, soNguoi);
            }
            if (trangThai != -1) {
                ps.setInt(paramIndex++, trangThai);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("maPhong");
                String ten = rs.getString("tenPhong");
                String loai = rs.getString("maLoai");
                double gia = rs.getDouble("giaPhong");
                int tt = rs.getInt("tinhTrang");
                int soNg = rs.getInt("soNguoi");
                String moTa = rs.getString("moTa");

                Phong p = new Phong(ma, ten, gia, loai, tt, moTa, soNg);
                dsPhong.add(p);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dsPhong;
        }
}