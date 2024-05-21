package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.LoaiSach;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;

public class LoaiSachDao {
    private SQLiteDatabase db;

    public LoaiSachDao() {
    }

    public LoaiSachDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());

        return db.insert("LOAISACH", null, values);
    }

    public int update(LoaiSach loaiSach) {
        ContentValues values = new ContentValues();
        values.put("tenLoai", loaiSach.getTenLoai());

        return db.update("LOAISACH", values, "maLoai=?", new String[]{String.valueOf(loaiSach.getMaLoai())});
    }

    public int delete(String ID) {
        return db.delete("LOAISACH", "maLoai = ?", new String[]{String.valueOf(ID)});
    }

    @SuppressLint("Range")
    public List<LoaiSach> getData(String sql, String...selectionArgs) {
        List<LoaiSach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while(c.moveToNext()) {
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));
            loaiSach.setTenLoai(c.getString(c.getColumnIndex("tenLoai")));

            list.add(loaiSach);
        }

        return list;
    }

    public List<LoaiSach> getAll() {
        String sql = "select * from LOAISACH";
        return getData(sql);
    }

    public LoaiSach getID(String ID) {
        String sql = "select * from LOAISACH where maLoai = ?";
        List<LoaiSach> list = getData(sql, ID);
        return list.get(0);
    }
}
