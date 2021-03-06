package com.example.tiemcattoc.creen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.adapter.DanhSachKHAdapter;
import com.example.tiemcattoc.creen.api.ApiGetDataFromTableRun;
import com.example.tiemcattoc.creen.api.ApiRunSQL;
import com.example.tiemcattoc.creen.api.GetDataFromTable;
import com.example.tiemcattoc.creen.dialog.LuaChonKhachHang;
import com.example.tiemcattoc.creen.dialog.LuaChonThemDialog;
import com.example.tiemcattoc.creen.dialog.ThemThoCatDialog;
import com.example.tiemcattoc.creen.object.KhachHang;
import com.example.tiemcattoc.creen.tool.Convert;

import java.util.ArrayList;

public class DanhSachKhachHangActivity extends AppCompatActivity implements ApiGetDataFromTableRun, ApiRunSQL {
    Convert convert = new Convert();
    DanhSachKHAdapter danhSachKHAdapter;
    ListView lsvDSKhachHang;
    EditText edtTenKhachHang;
    String nameKhachHang = "";

    EditText edtSDT;
    String sdt = "";
    TextView txvGtAll, txvGtNam, txvGtNu;
    TextView arrTxvGt[];
    int chonGt = 0;
    int thucHienCauLenh = 0;

    //0 thuc hien cau lenh them tho cat toc
    //1 thuc hien cau lenh xoa khach hang
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_khach_hang);
        init();
        anhXa();
        setUp();
        setClick();
        String s = checkDatainData();
        if (s.length() > 0) {
            new GetDataFromTable(s, this).execute();

        }
    }

    public void init() {

    }

    public void anhXa() {
        lsvDSKhachHang = findViewById(R.id.lsvDSKhachHang);
        edtTenKhachHang = findViewById(R.id.edtTenKhachHang);
        edtSDT = findViewById(R.id.edtSDT);

        txvGtAll = findViewById(R.id.txvGtAll);
        txvGtNam = findViewById(R.id.txvGtNam);
        txvGtNu = findViewById(R.id.txvGtNu);
        ///arrTxvGt= new TextView[]{txvGtAll,txvGtNam,txvGtNu};
    }

    public void setUp() {
        arrTxvGt = new TextView[]{txvGtAll, txvGtNam, txvGtNu};
        setUpGt();
    }

    public void setClick() {
        edtTenKhachHang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtTenKhachHang.getText().toString();
                if (s.length() >= 2) {
                    s = s.toLowerCase();
                    nameKhachHang = s;
                    ///tienHanhKiemTra();
                } else {
                    nameKhachHang = "";
                    /// danhSachKHAdapter.updateKhachHang(Data.getData().arrKhacHang);
                }
                tienHanhKiemTra();
            }
        });

        edtSDT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s = edtSDT.getText().toString();
                if (s.length() == 3) {
                    sdt = s;

                } else {
                    sdt = "";
                }
                tienHanhKiemTra();

            }
        });
        txvGtAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 0;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        txvGtNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 1;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        txvGtNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chonGt = 2;
                setUpGt();
                tienHanhKiemTra();
            }
        });
        lsvDSKhachHang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                thucHienCauLenh=1;
                new LuaChonKhachHang(DanhSachKhachHangActivity.this, danhSachKHAdapter.myArr.get(i)).show();
            }
        });
    }

    private void tienHanhKiemTra() {

        ArrayList<KhachHang> arrTen;
        if (nameKhachHang.length() >= 2) {
            arrTen = new ArrayList<>();
            for (KhachHang k : Data.getData().arrKhacHang) {
                String tenKhach = k.ten.toLowerCase();
                if (tenKhach.indexOf(nameKhachHang) >= 0) {
                    arrTen.add(k);
                }
            }
        } else {
            arrTen = Data.getData().arrKhacHang;
        }

        //Ktra SDT
        ArrayList<KhachHang> arrSDT;
        if (sdt.length() == 3) {
            arrSDT = new ArrayList<>();
            for (KhachHang k : arrTen) {
                String sdts = k.sdt;
                if (sdts.indexOf(sdt) == sdts.length() - 3) {
                    arrSDT.add(k);
                }
            }
        } else {
            arrSDT = arrTen;
        }
//ktra gioi tinh
        ArrayList<KhachHang> arrGt;
        if (chonGt != 0) {
            arrGt = new ArrayList<>();
            Boolean gt = false;
            if (chonGt == 1) {
                gt = true;
            }
            for (KhachHang k : arrSDT) {
               /* if(k.gt){
                    System.out.println("dumv1995 nam");
                }*/
                if (k.gt == gt) {
                    arrGt.add(k);
                }
            }
        } else {
            arrGt = arrSDT;
        }
        danhSachKHAdapter.updateKhachHang(arrGt);
    }

    public void setUpGt() {
        for (TextView txv : arrTxvGt) {
            txv.setTextColor(this.getResources().getColor(R.color.den));
            txv.setBackgroundColor(this.getResources().getColor(R.color.trang));
        }
        arrTxvGt[chonGt].setTextColor(this.getResources().getColor(R.color.vang));
        arrTxvGt[chonGt].setBackgroundColor(this.getResources().getColor(R.color.den));
    }

    public String checkDatainData() {
        ///String nameTable="";
        if (Data.getData().arrKhacHang.size() == 0) {
            return KEY.TABLE_KHACH_HANG;
        }else if (Data.getData().arrThoCatToc.size()==0){
            return KEY.TABLE_THO_CAT_TOC;
        }
        return "";
    }

    public void setUpShowData() {
        danhSachKHAdapter = new DanhSachKHAdapter(this, 0, Data.getData().arrKhacHang);
        lsvDSKhachHang.setAdapter(danhSachKHAdapter);
    }

    @Override
    public void batDauLay() {
        Toast.makeText(this, "bat dau lay", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void ketThuc(String data, String tableName) {
        if (tableName.equals(KEY.TABLE_KHACH_HANG)) {
            Data.getData().arrKhacHang.clear();
            Data.getData().arrKhacHang.addAll(convert.jsonToKhachHang(data));
        }else if (tableName.equals(KEY.TABLE_THO_CAT_TOC)){
                Data.getData().arrThoCatToc.clear();
                Data.getData().arrThoCatToc.addAll(convert.jsonToThoCatToc(data));

        }
        String s = checkDatainData();
        if (s.length() > 0) {
            new GetDataFromTable(s, this).execute();

        }else {
            setUpShowData();
        }
    }

    public static int ID_MAN_THEM_KHACH_HANG = 99;
    public static int ID_MAN_SUA_KHACH_HANG = 100;
    public static int ID_MAN_CAT_KHACH_HANG = 101;


    @Override
    public void batDauChayDauSQL() {

    }

    @Override
    public void ketThuc() {
        if (thucHienCauLenh == 0) {
            Toast.makeText(this, "Them Tho Cat Toc Thanh Cong", Toast.LENGTH_SHORT).show();
            Data.getData().arrThoCatToc.clear();
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }else if (thucHienCauLenh==1){
            Toast.makeText(this, "Xoa Khach Hang Thanh Cong", Toast.LENGTH_SHORT).show();
            Data.getData().arrKhacHang.clear();
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();
            }
        }
    }

    @Override
    public void biloi() {
        Toast.makeText(this, "Loi Ket Noi", Toast.LENGTH_SHORT).show();

    }

    //lien quan den nut them
    public void luaChonThem(View view) {
        new LuaChonThemDialog(this).show();

    }

    public void chuyenDenManThemKhachHang() {
        Data.getData().idKhachHangCanSua=-1;
        Intent i = new Intent(this, ThemKhachHangActivity.class);
        startActivityForResult(i, ID_MAN_THEM_KHACH_HANG);
    }

    public void themThoCat() {
        thucHienCauLenh = 0;
        new ThemThoCatDialog(this).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ID_MAN_THEM_KHACH_HANG) {
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();

            }
        } else if (requestCode == ID_MAN_SUA_KHACH_HANG) {
            String s = checkDatainData();
            if (s.length() > 0) {
                new GetDataFromTable(s, this).execute();

            }
        }
    }
}