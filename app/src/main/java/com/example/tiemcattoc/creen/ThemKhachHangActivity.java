package com.example.tiemcattoc.creen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.api.ApiRunSQL;
import com.example.tiemcattoc.creen.api.RunSQL;
import com.example.tiemcattoc.creen.object.KhachHang;

import java.util.Calendar;

public class ThemKhachHangActivity extends AppCompatActivity implements ApiRunSQL {
EditText edtTenKhachHang,edtMotaKhachHang,edtDiaChiKhachHang;
EditText edtNgaySinhKhachHang;
EditText edtSDTKhachHang;
TextView txvNam,txvNu;
boolean gt=true;
KhachHang khachHang=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_khach_hang);
        init();
        anhXa();
        setUp();
        setClick();
    }
    private void init(){
        if (Data.getData().idKhachHangCanSua==-1){
            //chuyen qua tu nut them
            ((TextView)findViewById(R.id.txvthemOrSua)).setText("Them");
            khachHang=null;
        }else {
            ((TextView)findViewById(R.id.txvthemOrSua)).setText("Sua");
            for(KhachHang k :Data.getData().arrKhacHang){
                if (k.id==Data.getData().idKhachHangCanSua){
                    khachHang=k;
                    break;
                }
            }
        }
    }
    private void anhXa(){
        edtTenKhachHang=findViewById(R.id.edtTenKhachHang);
        edtNgaySinhKhachHang=findViewById(R.id.edtNgaySinhKhachHang);
        edtSDTKhachHang=findViewById(R.id.edtSDTKhachHang);
        edtMotaKhachHang=findViewById(R.id.edtMoTaKhachHang);
        edtDiaChiKhachHang=findViewById(R.id.edtDiaChiKhachHang);
        txvNam=findViewById(R.id.txvNam);
        txvNu=findViewById(R.id.txvNu);
    }
    private void setUp(){
        if(khachHang!=null){
            edtTenKhachHang.setText(khachHang.ten);
            edtNgaySinhKhachHang.setText(khachHang.ngaySInh);
            edtSDTKhachHang.setText(khachHang.sdt);
            edtDiaChiKhachHang.setText(khachHang.diachi);
            edtMotaKhachHang.setText(khachHang.moTa);
            if (khachHang.gt){
                gt=true;
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            }else {
                gt=false;
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));
            }
        }
    }
    private void setClick(){
        txvNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gt=true;
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));

            }
        });
        txvNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gt=false;
                txvNu.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.vang));
                txvNu.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setTextColor(ThemKhachHangActivity.this.getResources().getColor(R.color.den));
                txvNam.setBackgroundColor(ThemKhachHangActivity.this.getResources().getColor(R.color.trang));

            }
        });

    }


    private boolean chekNullInfo(EditText e){
        String s=""+ e.getText();
        if(s.length()==0){
            return false;
        }else {
            return true;
        }
    }
    private  void thongBao(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    public void themKhachHang(View view) {
        if (!chekNullInfo(edtTenKhachHang)){
            thongBao("Bi Thieu Ten");
            return;
        }
        String ten=edtTenKhachHang.getText().toString();
        if (!chekNullInfo(edtNgaySinhKhachHang)){
            thongBao("Bi Thieu Ngay Sinh");
            return;
        }
        String ngaySinh=edtNgaySinhKhachHang.getText().toString();
        if (!chekNullInfo(edtSDTKhachHang)){
            thongBao("Bi Thieu SDT");
            return;
        }
        String sdt=edtSDTKhachHang.getText().toString();
        if (!chekNullInfo(edtDiaChiKhachHang)){
            thongBao("Bi Thieu Dia Chi");
            return;
        }
        String diachi=edtDiaChiKhachHang.getText().toString();
        if (!chekNullInfo(edtMotaKhachHang)){
            thongBao("Bi Thieu Mo Ta");
            return;
        }
        String mota=edtMotaKhachHang.getText().toString();
        String gt="1";
        if (!this.gt){
            gt="0";
        }
        String sql;
        if (khachHang==null) {
            sql = "INSERT INTO `khachhang` (`id`, `ten`, `ngaysinh`, `sdt`, `diachi`, `gt`, `mota`) VALUES (NULL, '" +
                    ten +
                    "', '" +
                    ngaySinh +
                    "', '" +
                    sdt +
                    "', '" +
                    diachi +
                    "', '" +
                    gt +
                    "', '" +
                    mota +
                    "')";
        }else{
           sql="UPDATE `khachhang` SET `" +
                   "ten` = '" +
                   ten +
                   "', `ngaysinh` = '" +
                   ngaySinh +
                   "', `sdt` = '" +
                   sdt +
                   "', `diachi` = '" +
                   diachi +
                   "', `gt` = '" +
                   gt +
                   "', `mota` = '" +
                   mota +
                   "' WHERE `khachhang`.`id` = "+khachHang.id;
        }
        new RunSQL(sql,this).execute();
    }

    public void chonNgaySinh(View view) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                String date= dayOfMonth+"/"+(monthOfYear)+"/"+year;
                edtNgaySinhKhachHang.setText(date);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    @Override
    public void batDauChayDauSQL() {
        thongBao("Bat Dau run Sql");
    }

    @Override
    public void ketThuc() {
        thongBao("Da ket thuc");
        Data.getData().arrKhacHang.clear();
        onBackPressed();
    }

    @Override
    public void biloi() {
        thongBao("Bi loi");

    }
}
