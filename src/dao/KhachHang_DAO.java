package dao;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.Phong;

public class KhachHang_DAO {
    private ArrayList<KhachHang> dsKH;

    public KhachHang_DAO() {
        dsKH = new ArrayList<>();
    }

    public ArrayList<KhachHang> getDSKhachHang() {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String SDT = rs.getString("SDT");
                String soCCCD = rs.getString("soCCCD");
                String email = rs.getString("email");
                KhachHang kh = new KhachHang(maKH, tenKH, diaChi, SDT, email, soCCCD);
                dsKH.add(kh);
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dsKH;
    }

    public boolean deleteKhachHang(String maKH) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "DELETE FROM KhachHang WHERE maKH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maKH);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            return rowsAffected > 0; // Returns true if a row was deleted
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(String maKH, KhachHang khNew) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "UPDATE KhachHang SET hoTen = ?, diaChi = ?, SDT = ?, email = ?, soCCCD = ? WHERE maKH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, khNew.getHoTen());
            ps.setString(2, khNew.getDiaChi());
            ps.setString(3, khNew.getSdt());
            ps.setString(4, khNew.getEmail());
            ps.setString(5, khNew.getcCCD());
            ps.setString(6, maKH);
            int rowsAffected = ps.executeUpdate();
            ps.close();
            return rowsAffected > 0; // Returns true if a row was updated
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public ArrayList<KhachHang> timKiem(String maKH, String tenKH, String diaChi, String sdt, String email, String cccd) {
        ArrayList<KhachHang> result = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();

            // Tạo câu lệnh SQL với điều kiện tìm kiếm
            String sql = "SELECT * FROM KhachHang WHERE 1=1";
            if (!maKH.isEmpty()) sql += " AND maKH LIKE ?";
            if (!tenKH.isEmpty()) sql += " AND hoTen LIKE ?";
            if (!diaChi.isEmpty()) sql += " AND diaChi LIKE ?";
            if (!sdt.isEmpty()) sql += " AND SDT LIKE ?";
            if (!email.isEmpty()) sql += " AND email LIKE ?";
            if (!cccd.isEmpty()) sql += " AND soCCCD LIKE ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            // Gán giá trị cho các tham số tìm kiếm
            int index = 1;
            if (!maKH.isEmpty()) stmt.setString(index++, "%" + maKH + "%");
            if (!tenKH.isEmpty()) stmt.setString(index++, "%" + tenKH + "%");
            if (!diaChi.isEmpty()) stmt.setString(index++, "%" + diaChi + "%");
            if (!sdt.isEmpty()) stmt.setString(index++, "%" + sdt + "%");
            if (!email.isEmpty()) stmt.setString(index++, "%" + email + "%");
            if (!cccd.isEmpty()) stmt.setString(index++, "%" + cccd + "%");

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang(
                        rs.getString("maKH"),
                        rs.getString("hoTen"),
                        rs.getString("diaChi"),
                        rs.getString("SDT"),
                        rs.getString("email"),
                        rs.getString("soCCCD")
                );
                result.add(kh);
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public KhachHang GetKH(int i) {
        if (i >= 0 && i < dsKH.size()) {
            return dsKH.get(i);
        }
        return null;
    }
}
