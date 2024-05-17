package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;

public class ThanhVienDao {
    private SQLiteDatabase db;

    public ThanhVienDao() {
    }

    public ThanhVienDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.getHoTen());
        values.put("namSinh", thanhVien.getNamSinh());

        return db.insert("THANHVIEN", null, values);
    }

    public int update(ThanhVien thanhVien) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thanhVien.getHoTen());
        values.put("namSinh", thanhVien.getNamSinh());

        return db.update("THANHVIEN", values, "maTV=?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }

    public int delete(ThanhVien thanhVien) {
        return db.delete("THANHVIEN", "maTV = ?", new String[]{String.valueOf(thanhVien.getMaTV())});
    }

    @SuppressLint("Range")
    public List<ThanhVien> getData(String sql, String...selectionArgs) {
        List<ThanhVien> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while(c.moveToNext()) {
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            thanhVien.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            thanhVien.setNamSinh(c.getString(c.getColumnIndex("namSinh")));
            Log.i("//=====", thanhVien.toString());
            list.add(thanhVien);
        }

        return list;
    }

    public List<ThanhVien> getAll() {
        String sql = "select * from THANHVIEN";
        return getData(sql);
    }

    public ThanhVien getID(String ID) {
        String sql = "select * from THANHVIEN where maTV = ?";
        List<ThanhVien> list = getData(sql);
        return list.get(0);
    }
}
