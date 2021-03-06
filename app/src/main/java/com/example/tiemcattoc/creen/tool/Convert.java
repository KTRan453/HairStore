package com.example.tiemcattoc.creen.tool;

import com.example.tiemcattoc.creen.object.KhachHang;
import com.example.tiemcattoc.creen.object.ThoCatToc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Convert {
    public ArrayList<KhachHang> jsonToKhachHang(String data){
        ArrayList<KhachHang> arr=new ArrayList<>();
        try{
            JSONArray array=new JSONArray(data);
            for (int i=0;i<array.length();i++){
                JSONObject oj=array.getJSONObject(i);
                KhachHang kh=new KhachHang();
                kh.id= Integer.parseInt(oj.getString("id"));
                kh.ten=oj.getString("ten");
                kh.ngaySInh=oj.getString("ngaysinh");
                kh.sdt=oj.getString("sdt");
                kh.diachi=oj.getString("diachi");
                kh.moTa=oj.getString("mota");
                String gt =oj.getString("gt");
                if(gt.equals("1")){
                    kh.gt=true;
                }else
                {
                    kh.gt=false;
                }
                arr.add(kh);
            }
        }catch (JSONException e){

        }return arr;
    }
    public ArrayList<ThoCatToc> jsonToThoCatToc(String data){
        ArrayList<ThoCatToc> arr=new ArrayList<>();
        try{
            JSONArray array=new JSONArray(data);
            for (int i=0;i<array.length();i++){
                JSONObject oj=array.getJSONObject(i);
                ThoCatToc tho=new ThoCatToc();
                tho.id= Integer.parseInt(oj.getString("id"));
                tho.ten=oj.getString("ten");
                arr.add(tho);
            }
        }catch (JSONException e){

        }
        return arr;
    }
}
