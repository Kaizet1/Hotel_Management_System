package dao;

import connectDB.ConnectDB;
import entity.KhachHang;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class PhieuDatPhong_DAO {
    private ArrayList<PhieuDatPhong> dsPhieuDatPhong;
    private ArrayList<PhieuDatPhong> dsPhieuDatPhongDangCho;
    public PhieuDatPhong_DAO() {
        dsPhieuDatPhong = new ArrayList<>();
        dsPhieuDatPhongDangCho = new ArrayList<>();
    }
    public ArrayList<PhieuDatPhong> getDSPhieuDatPhongDangCho() {
        try{
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from PhieuDatPhong where tinhTrangPDP = 0";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String maPDP = rs.getString("maPDP");
                String maPhong = rs.getString("maPhong");
                Phong p = new Phong(maPhong);
                String maNV = rs.getString("maNV");
                NhanVien nv = new NhanVien(maNV);
                String maKH = rs.getString("maKH");
                KhachHang kh = new KhachHang(maKH);
                Date ngayDen = rs.getTimestamp("ngayDen");
                Date ngayDi = rs.getTimestamp("ngayDi");
                Date ngayDat = rs.getDate("ngayDat");
                int tinhTrangPDP = rs.getInt("tinhTrangPDP");
                PhieuDatPhong phieuDatPhong= new PhieuDatPhong(maPDP, p, nv, kh, ngayDi, ngayDen, ngayDat, tinhTrangPDP);
                dsPhieuDatPhongDangCho.add(phieuDatPhong);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhieuDatPhongDangCho;
    }

    public ArrayList<PhieuDatPhong> getDSPhieuDatPhong() {
        try{
            Connection con = ConnectDB.getInstance().getConnection();
            String sql = "select * from PhieuDatPhong where tinhTrangPDP <> 2";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String maPDP = rs.getString("maPDP");
                String maPhong = rs.getString("maPhong");
                Phong p = new Phong(maPhong);
                String maNV = rs.getString("maNV");
                NhanVien nv = new NhanVien(maNV);
                String maKH = rs.getString("maKH");
                KhachHang kh = new KhachHang(maKH);
                Date ngayDen = rs.getTimestamp("ngayDen");
                Date ngayDi = rs.getTimestamp("ngayDi");
                Date ngayDat = rs.getDate("ngayDat");
                int tinhTrangPDP = rs.getInt("tinhTrangPDP");
                PhieuDatPhong phieuDatPhong= new PhieuDatPhong(maPDP, p, nv, kh, ngayDi, ngayDen, ngayDat, tinhTrangPDP);
                dsPhieuDatPhong.add(phieuDatPhong);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return dsPhieuDatPhong;
    }

    public ArrayList<String> getSoPhongDaDat(Date ngayDen, Date ngayDi) {
        ArrayList<String> dsSoPhong = new ArrayList<>();

        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "SELECT maPhong FROM PhieuDatPhong WHERE ((ngayDen <= ? AND ngayDi >= ?) OR (ngayDen <= ? AND ngayDi >= ?) OR (ngayDen >= ? AND ngayDi <= ?) OR (ngayDen <= ? AND ngayDi >= ?)) AND tinhTrangPDP <> 2";

        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setTimestamp(1, new java.sql.Timestamp(ngayDi.getTime()));
            stmt.setTimestamp(2, new java.sql.Timestamp(ngayDen.getTime()));
            stmt.setTimestamp(3, new java.sql.Timestamp(ngayDi.getTime()));
            stmt.setTimestamp(4, new java.sql.Timestamp(ngayDen.getTime()));
            stmt.setTimestamp(5, new java.sql.Timestamp(ngayDen.getTime()));
            stmt.setTimestamp(6, new java.sql.Timestamp(ngayDi.getTime()));
            stmt.setTimestamp(7, new java.sql.Timestamp(ngayDen.getTime()));
            stmt.setTimestamp(8, new java.sql.Timestamp(ngayDi.getTime()));

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                dsSoPhong.add(rs.getString("maPhong"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dsSoPhong;
    }


    public boolean huyDatPhong(String maPDP) {
            try {
                Connection con = ConnectDB.getInstance().getConnection();
                String sql = "UPDATE PhieuDatPhong SET tinhTrangPDP = 2 WHERE maPDP = ?";
                PreparedStatement stmt = con.prepareStatement(sql);
                stmt.setString(1, maPDP);
                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }

    }

    public boolean datPhong(PhieuDatPhong phieuDatPhong) {
        try {
            Connection con = ConnectDB.getInstance().getConnection();

            String checkSql = "SELECT diaChi, SDT, email FROM KhachHang WHERE maKH = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkSql);
            checkStatement.setString(1, phieuDatPhong.getKhachHang().getMaKH());
            ResultSet rs = checkStatement.executeQuery();

            if (rs.next()) {
                boolean update = false;
                if (!rs.getString("diaChi").equals(phieuDatPhong.getKhachHang().getDiaChi())) {
                    update = true;
                }
                if (!rs.getString("SDT").equals(phieuDatPhong.getKhachHang().getSdt())) {
                    update = true;
                }
                if (!rs.getString("email").equals(phieuDatPhong.getKhachHang().getEmail())) {
                    update = true;
                }

                if (update) {
                    String updateSql = "UPDATE KhachHang SET diaChi = ?, SDT = ?, email = ? WHERE maKH = ?";
                    PreparedStatement updateStatement = con.prepareStatement(updateSql);
                    updateStatement.setString(1, phieuDatPhong.getKhachHang().getDiaChi());
                    updateStatement.setString(2, phieuDatPhong.getKhachHang().getSdt());
                    updateStatement.setString(3, phieuDatPhong.getKhachHang().getEmail());
                    updateStatement.setString(4, phieuDatPhong.getKhachHang().getMaKH());
                    updateStatement.executeUpdate();
                }
            } else {
                // Tao moi
                String insertKhachHangSql = "INSERT INTO KhachHang (maKH, hoTen, diaChi, SDT, soCCCD, email, ngaySinh) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertKhachHangSql);
                insertStatement.setString(1, phieuDatPhong.getKhachHang().getMaKH());
                insertStatement.setString(2, phieuDatPhong.getKhachHang().getHoTen());
                insertStatement.setString(3, phieuDatPhong.getKhachHang().getDiaChi());
                insertStatement.setString(4, phieuDatPhong.getKhachHang().getSdt());
                insertStatement.setString(5, phieuDatPhong.getKhachHang().getcCCD());
                insertStatement.setString(6, phieuDatPhong.getKhachHang().getEmail());

                insertStatement.executeUpdate();
            }
            String sql = "INSERT INTO PhieuDatPhong (maPDP, maPhong, maNV, maKH, ngayDat, ngayDen, ngayDi, tinhTrangPDP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, phieuDatPhong.getMaPDP());
            preparedStatement.setString(2, phieuDatPhong.getPhong().getMaPhong());
            preparedStatement.setString(3, phieuDatPhong.getNhanVien().getMaNV());
            preparedStatement.setString(4, phieuDatPhong.getKhachHang().getMaKH());
            preparedStatement.setDate(5, new java.sql.Date(phieuDatPhong.getNgayDat().getTime()));
            preparedStatement.setTimestamp(6, new java.sql.Timestamp(phieuDatPhong.getNgayDen().getTime()));
            preparedStatement.setTimestamp(7, new java.sql.Timestamp(phieuDatPhong.getNgayDi().getTime()));
            preparedStatement.setInt(8, 0);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
