package com.example.tiemcattoc.creen;

import com.example.tiemcattoc.creen.object.KhachHang;
import com.example.tiemcattoc.creen.object.ThoCatToc;

import java.util.ArrayList;

public class Data {
    static Data data;
    static {
        data=new Data();
        // return data;
    }
    public  static  Data getData(){
        return data;
    }
    public ArrayList<KhachHang> arrKhacHang=new ArrayList<>();
// -1 la chuyen sang man de them khach hang
    // != -1 sua thong tin khach hang
    public int idKhachHangCanSua=-1;

    public ArrayList<ThoCatToc> arrThoCatToc=new ArrayList<>();
}
