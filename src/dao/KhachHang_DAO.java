package dao;

import java.sql.*;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;


public class KhachHang_DAO {
    private Connection conn;
    private ArrayList<KhachHang> dsKH;

    public KhachHang_DAO() {
        dsKH = new ArrayList<KhachHang>();
        // Lấy kết nối từ lớp ConnectDB
        this.conn = ConnectDB.getInstance().getConnection();
        dsKH = new ArrayList<>();
    }

    public ArrayList<KhachHang> getDSKhachHang() {
        ArrayList<KhachHang> dsKH = new ArrayList<>();
        try {
            // Lấy kết nối đến cơ sở dữ liệu
            Connection con = ConnectDB.getInstance().getConnection();
            // Câu truy vấn chỉ lấy khách hàng có trạng thái khác 0
            String sql = "SELECT * FROM KhachHang WHERE TrangThai = 1";
            // Sử dụng PreparedStatement để tăng tính an toàn và hiệu suất
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                // Lấy thông tin từ ResultSet
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String SDT = rs.getString("SDT");
                String soCCCD = rs.getString("soCCCD");
                String email = rs.getString("email");
                int trangThai = rs.getInt("trangThai");
                // Khởi tạo đối tượng KhachHang và thêm vào danh sách
                KhachHang kh = new KhachHang(maKH, tenKH, diaChi, SDT, email, soCCCD, trangThai);
                dsKH.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // In lỗi nếu có
        }
        return dsKH; // Trả về danh sách khách hàng
    }


    public boolean themKhachHang(KhachHang kh) throws SQLException {

        int kq=0;
        try  {
            String query = "INSERT INTO KhachHang (maKH, hoTen, diaChi, SDT, soCCCD, email, ngaySinh) VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kh.getMaKH());
            stmt.setString(2, kh.getHoTen());
            stmt.setString(3, kh.getDiaChi());
            stmt.setString(4, kh.getSdt());
            stmt.setString(5, kh.getEmail());
           stmt.setInt(6, kh.getTrangThai());
            kq = stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return kq>0;
    }

    public boolean xoaKH(String maKH) throws SQLException {
        String query = "UPDATE KhachHang SET trangThai = 0 WHERE maKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maKH);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public boolean khoiPhucKhachHang(String maKH) throws SQLException {
        String query = "UPDATE KhachHang SET trangThai = 1 WHERE maKH = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, maKH);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public KhachHang timKiem(String maKH) {
        for (KhachHang kh : dsKH) {
            if (kh.getMaKH().equalsIgnoreCase(maKH)) {

                return kh;
            }
        }
        return null;
    }

    public KhachHang searchKhachHangBangCCCD(String cccd) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soCCCD = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cccd);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String maKH = rs.getString("maKH");
                String tenKH = rs.getString("hoTen");
                String diaChi = rs.getString("diaChi");
                String email = rs.getString("email");
                String sdt = rs.getString("SDT");
                int trangThai = rs.getInt("trangThai");
                kh = new KhachHang(maKH, tenKH, diaChi, sdt, email, cccd, trangThai);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kh;
    }

    public boolean isKhachHangTonTai(String cccd) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "SELECT * FROM KhachHang WHERE soCCCD = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cccd);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
       return false;
    }


    public boolean suaKhachHang(KhachHang kh) throws SQLException {
        int kq=0;

        try  {
            String query = "UPDATE KhachHang SET hoTen=?, diaChi=?, SDT=?, soCCCD=?, email=? WHERE maKH = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, kh.getHoTen());
            stmt.setString(2, kh.getDiaChi());
            stmt.setString(3, kh.getSdt());
            stmt.setString(4,kh.getcCCD());
            stmt.setString(5, kh.getEmail());
            stmt.setString(6, kh.getMaKH());
            kq=stmt.executeUpdate();
        }catch (SQLException e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return kq>0;
    }

    public KhachHang GetKH(int i) {
        if (i >= 0 && i < dsKH.size()) {
            return dsKH.get(i);
        }
        return null;
    }
}
