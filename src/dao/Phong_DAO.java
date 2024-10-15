package dao;
import entity.Phong;
import java.util.ArrayList;
public class Phong_DAO {
private ArrayList<Phong> dsPhong;

public Phong_DAO() {
    dsPhong = new ArrayList<Phong>();

}
public boolean themPong(Phong p){
    if(dsPhong.contains(p)){
        dsPhong.add(p);
        return true;
    }
    return false;
}
public boolean xoaPhong(String maPhong){
    Phong p = new Phong(maPhong);
    if(dsPhong.contains(p)){
        dsPhong.remove(p);
        return true;
    }
    return false;

}
public boolean suaPhong(String maPhong, Phong pNew){
    Phong p = new Phong(maPhong);
    if(dsPhong.contains(p)){
        dsPhong.set(dsPhong.indexOf(p), pNew);
        
}
