package com.example.tiemcattoc.creen.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.CatTocChoKhachHangActivity;
import com.example.tiemcattoc.creen.DanhSachKhachHangActivity;
import com.example.tiemcattoc.creen.Data;
import com.example.tiemcattoc.creen.ThemKhachHangActivity;
import com.example.tiemcattoc.creen.api.RunSQL;
import com.example.tiemcattoc.creen.object.KhachHang;

public class LuaChonKhachHang extends Dialog {
    KhachHang ks;
    DanhSachKhachHangActivity ds;

    public LuaChonKhachHang(@NonNull Context context, KhachHang khachHang) {
        super(context);

        this.ks=khachHang;
        this.ds=(DanhSachKhachHangActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.dg_lua_chon_khach_hang);
//anh xa

        TextView txvTenKhachHang=findViewById(R.id.txvTenKhachHang);
        TextView txvSuaThongTinKhachHang=findViewById(R.id.txvSuaThongTinKhachHang);
        TextView txvXoaKhachHang=findViewById(R.id.txvXoaKhachHang);
        TextView txvCatTocChoKhach=findViewById(R.id.txvCatTocChoKhach);

        //set thong tin
        txvTenKhachHang.setText(ks.ten);
        txvSuaThongTinKhachHang.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Data.getData().idKhachHangCanSua=ks.id;
                ds.startActivityForResult(new Intent(ds, ThemKhachHangActivity.class),ds.ID_MAN_SUA_KHACH_HANG);
                dismiss();
            }
        });
        txvXoaKhachHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sql="DELETE FROM `khachhang` WHERE `khachhang`.`id` = "+ks.id;
                new RunSQL(sql,ds).execute();
                dismiss();
            }
        });
        txvCatTocChoKhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data.getData().idKhachHangCanSua=ks.id;
                ds.startActivityForResult(new Intent(ds, CatTocChoKhachHangActivity.class),ds.ID_MAN_CAT_KHACH_HANG);
                dismiss();
            }
        });
        }

}

