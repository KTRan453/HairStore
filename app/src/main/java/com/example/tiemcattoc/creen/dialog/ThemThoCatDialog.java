package com.example.tiemcattoc.creen.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.DanhSachKhachHangActivity;
import com.example.tiemcattoc.creen.api.RunSQL;

public class ThemThoCatDialog extends Dialog {
    DanhSachKhachHangActivity ds;
    EditText edtTenThoCatToc;
    public ThemThoCatDialog(@NonNull Context context) {
        super(context);
        this.ds=(DanhSachKhachHangActivity)context;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dg_them_tho_cat);

        TextView txvThemThoCatToc=findViewById(R.id.txvThemThoCatToc);
        edtTenThoCatToc=findViewById(R.id.edtTenThoCatToc);
        //TextView edtTenThoCatToc=findViewById(R.id.edtTenThoCatToc);


        txvThemThoCatToc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=""+edtTenThoCatToc.getText().toString() ;
                if (name.length()==0) {
                    Toast.makeText(ds, "Thieu Ten Tho Cat Toc", Toast.LENGTH_SHORT).show();
                    return;
                }
                String sql="INSERT INTO `tho` (`id`, `ten`) VALUES (NULL, '" +
                        name +
                        "')";
                new RunSQL(sql,ds).execute();
                dismiss();
            }
        });
        }

}

