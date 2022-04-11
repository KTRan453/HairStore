package com.example.tiemcattoc.creen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiemcattoc.R;
import com.example.tiemcattoc.creen.object.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class DanhSachKHAdapter extends ArrayAdapter<KhachHang> {
    Context myCt;
    public ArrayList<KhachHang> myArr;
    public DanhSachKHAdapter(@NonNull Context context, int resource, @NonNull List<KhachHang> objects) {
        super(context, resource, objects);
        this.myCt=context;
        this.myArr= new ArrayList<>(objects);
    }
    public  void  updateKhachHang(ArrayList<KhachHang> arr){
        this.myArr= arr;
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return this.myArr.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_khach_hang,null);

        }
        if(myArr.size()>0){
            TextView txvTenKhachHang = convertView.findViewById(R.id.txvTenKhachHang);
            TextView txvGtKhachHang = convertView.findViewById(R.id.txvGtKhachHang);
            TextView txvNgaySinhKhachHang = convertView.findViewById(R.id.txvNgaySinhKhachHang);
            TextView txvSdtKhachHang = convertView.findViewById(R.id.txvSdtKhachHang);
            TextView txvSoLanCatKhachHang = convertView.findViewById(R.id.txvSoLanCatKhachHang);

            KhachHang kh=myArr.get(position);
            txvSdtKhachHang.setText(kh.sdt);
            txvTenKhachHang.setText(kh.ten);
            if (kh.gt){
                txvGtKhachHang.setText("Nam");

            }else{
                txvGtKhachHang.setText("Nu");
            }
            txvNgaySinhKhachHang.setText(kh.ngaySInh);
            txvSdtKhachHang.setText(kh.sdt);
            txvSoLanCatKhachHang.setText(""+kh.solanCat+ "lan");
        }
        return convertView;
    }
}
