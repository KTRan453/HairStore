package com.example.tiemcattoc.creen.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiemcattoc.R;

import java.util.ArrayList;
import java.util.List;

public class AnhKhachHangAdapter extends ArrayAdapter<Bitmap> {
    private Context myCt;
    private ArrayList<Bitmap> myArr;
    public AnhKhachHangAdapter(@NonNull Context context, int resource, @NonNull List<Bitmap> objects) {
        super(context, resource, objects);
        this.myCt=context;
        this.myArr=new ArrayList<>(objects);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
    public void setMyArr(ArrayList<Bitmap> arr){
        this.myArr=arr;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)myCt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.item_anh_cua_khach,null);
        }
        if(myArr.size()>0){
            ImageView imgAnhCuaKhachHang=convertView.findViewById(R.id.imgAnhCuaKhachHang);
            imgAnhCuaKhachHang.setImageBitmap(myArr.get(position));
        }
        return convertView;
    }
}
