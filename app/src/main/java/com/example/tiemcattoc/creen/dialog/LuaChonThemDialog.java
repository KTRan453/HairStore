package com.example.tiemcattoc.creen.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.DanhSachKhachHangActivity;

public class LuaChonThemDialog extends Dialog {
    DanhSachKhachHangActivity ds;
    public LuaChonThemDialog(@NonNull Context context) {
        super(context);
        this.ds=(DanhSachKhachHangActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dg_lua_chon_them);
        TextView txvThemKH=findViewById(R.id.txvThemKH);
        TextView txvThemThoCat=findViewById(R.id.txvThemThoCat);
        txvThemKH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.chuyenDenManThemKhachHang();
                dismiss();
            }
        });
        txvThemThoCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ds.themThoCat();
                dismiss();
            }
        });

        }

}

