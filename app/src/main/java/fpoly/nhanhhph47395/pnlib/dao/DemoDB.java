package fpoly.nhanhhph47395.pnlib.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;

public class DemoDB {
    private SQLiteDatabase db;
    private  ThanhVienDao thanhVienDao;
    public DemoDB(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
        thanhVienDao = new ThanhVienDao(context);

    }

    public void thanhVien() {

        ThanhVien tv = new ThanhVien(1, "Nguyen Van A", "2001");
        if (thanhVienDao.insert(tv) > 0) {
            Log.i("//=====", "Them thanh cong");
        } else {
            Log.i("//=====", "Them that bai");
        } 

        Log.i("//=====", "Tong so thanhVien: " + thanhVienDao.getAll().size());
    }
}
