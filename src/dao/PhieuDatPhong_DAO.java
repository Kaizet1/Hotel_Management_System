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
    public PhieuDatPhong_DAO() {
        dsPhieuDatPhong = new ArrayList<>();
    }
    public ArrayList<PhieuDatPhong> getDSPhieuDatPhong() {
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
                Date ngayDen = rs.getDate("ngayDen");
                Date ngayDi = rs.getDate("ngayDi");
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

            // Kiểm tra mã khách hàng có tồn tại hay không
            String checkSql = "SELECT COUNT(*) FROM KhachHang WHERE maKH = ?";
            PreparedStatement checkStatement = con.prepareStatement(checkSql);
            checkStatement.setString(1, phieuDatPhong.getKhachHang().getMaKH());

            ResultSet rs = checkStatement.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                // Nếu không tồn tại, chèn khách hàng mới
                String insertKhachHangSql = "INSERT INTO KhachHang (maKH, hoTen, diaChi, SDT, email, soCCCD) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStatement = con.prepareStatement(insertKhachHangSql);
                insertStatement.setString(1, phieuDatPhong.getKhachHang().getMaKH());
                insertStatement.setString(2, phieuDatPhong.getKhachHang().getHoTen());
                insertStatement.setString(3, "");
                insertStatement.setString(4, phieuDatPhong.getKhachHang().getSdt());
                insertStatement.setString(5, phieuDatPhong.getKhachHang().getEmail());
                insertStatement.setString(6, phieuDatPhong.getKhachHang().getcCCD());
//                insertStatement.setDate(6, new java.sql.Date(new Date().getTime()));

                insertStatement.executeUpdate();
            }

            // Sau khi đảm bảo khách hàng đã tồn tại, thực hiện chèn thông tin đặt phòng
            String sql = "INSERT INTO PhieuDatPhong (maPDP, maPhong, maNV, maKH, ngayDat, ngayDen, ngayDi, tinhTrangPDP) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, phieuDatPhong.getMaPDP());
            preparedStatement.setString(2, phieuDatPhong.getPhong().getMaPhong());
            preparedStatement.setString(3, phieuDatPhong.getNhanVien().getMaNV());
            preparedStatement.setString(4, phieuDatPhong.getKhachHang().getMaKH());
            preparedStatement.setDate(5, new java.sql.Date(phieuDatPhong.getNgayDat().getTime()));
            preparedStatement.setDate(6, new java.sql.Date(phieuDatPhong.getNgayDen().getTime()));
            preparedStatement.setDate(7, new java.sql.Date(phieuDatPhong.getNgayDi().getTime()));
            preparedStatement.setInt(8, 0);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


}
