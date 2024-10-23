package dao;

import connectDB.ConnectDB;
import entity.DichVu;
import entity.KhachHang;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class DichVu_DAO {
private ArrayList<DichVu> dsdv;
public DichVu_DAO() {
    dsdv = new ArrayList<DichVu>();
}
public ArrayList<DichVu> getDSDichVu() {
    try{
        Connection con = ConnectDB.getInstance().getConnection();
        String sql = "select * from DichVu";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while (rs.next()){
            String maDV = rs.getString("maDV");
            String tenDV = rs.getString("tenDV");
            double giaDV = rs.getDouble("giaDV");
            String moTa = rs.getString("moTa");
            DichVu dv= new DichVu(maDV, tenDV, giaDV, moTa);
            dsdv.add(dv);
        }

    }catch (SQLException e){
        e.printStackTrace();
    }
    return dsdv;
}
}
