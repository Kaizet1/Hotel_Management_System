package dao;

import java.util.ArrayList;

import entity.KhachHang;

public class KhachHang_DAO {
private ArrayList<KhachHang> dsKH;

public KhachHang_DAO() {
    dsKH = new ArrayList<KhachHang> (); 
}

public boolean themKH(KhachHang kh) {
if(dsKH.contains(kh)) {
    return false;
}
 dsKH.add(kh);
 return true;
}
public boolean xoaKH(String maKH){
    KhachHang kh = new KhachHang(maKH);
    if(dsKH.contains(kh)) {
        dsKH.remove(maKH);
        return true;
    } return false;
}
public KhachHang timKiem(String maKH){
    KhachHang kh = new KhachHang(maKH);
    if(dsKH.contains(maKH)){
        return dsKH.get(dsKH.indexOf(maKH));
    } return null;
}

public boolean suaKH(String maKH, KhachHang khNew){
    KhachHang khachHang = new KhachHang(maKH);
    if(dsKH.contains(khachHang)){
        dsKH.set(dsKH.indexOf(khachHang), khNew);
        khachHang.setHoTen(khNew.getHoTen());
        khachHang.setDiaChi(khNew.getDiaChi());
        khachHang.setSdt(khNew.getSdt());
        khachHang.setEmail(khNew.getEmail());
        khachHang.setcCCD(khNew.getcCCD());
        return true;
    } return false;
}
public ArrayList<KhachHang> layDSKH(){
    return dsKH;
}
public KhachHang GetKH(int i){
    if(i>=0 && i<dsKH.size()){
        return dsKH.get(i);
    } return null;
}
}
