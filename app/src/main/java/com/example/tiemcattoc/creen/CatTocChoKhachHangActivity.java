package com.example.tiemcattoc.creen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.adapter.AnhKhachHangAdapter;
import com.example.tiemcattoc.creen.object.KhachHang;
import com.example.tiemcattoc.creen.object.ThoCatToc;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class CatTocChoKhachHangActivity extends AppCompatActivity {
 KhachHang khachHang;
 TextView txvTenKhachHang;
 Spinner spnThoCatToc;
 ArrayList<Bitmap> bitmaps;
 AnhKhachHangAdapter adapter;
 GridView gdvAnhCuaKhach;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_toc_cho_khach_hang);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
            for(KhachHang k :Data.getData().arrKhacHang){
                if (k.id==Data.getData().idKhachHangCanSua){
                    khachHang=k;
                    break;
                }
            }
            bitmaps=new ArrayList<>();
            adapter=new AnhKhachHangAdapter(this,0,bitmaps);
        }
    private void anhXa(){
        txvTenKhachHang=findViewById(R.id.txvTenKhachHang);
        spnThoCatToc=findViewById(R.id.spnThoCatToc);
        gdvAnhCuaKhach=findViewById(R.id.gdvAnhCuaKhach);
    }
    private void setUp(){
        txvTenKhachHang.setText(khachHang.ten);
        spnThoCatToc.setAdapter(new ArrayAdapter<ThoCatToc>(this,R.layout.item_text_tho_cat,Data.getData().arrThoCatToc));
        gdvAnhCuaKhach.setAdapter(adapter);
        }
    private void setClick(){}
    public static  int GET_ANH_TU_THU_VIEN=999;
    public void themAnhThuVien(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,GET_ANH_TU_THU_VIEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK && requestCode==GET_ANH_TU_THU_VIEN){
            if(data!=null){
                Uri umgUri=data.getData();
                try {

                    InputStream inputStream = getContentResolver().openInputStream(umgUri);
                    Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                    bitmaps.add(bitmap);
                    adapter.setMyArr(bitmaps);
                } catch(FileNotFoundException e){
                e.printStackTrace();
            }
        }
    }
}
}
