package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.Sach;

public class SachDao {
    private SQLiteDatabase db;

    public SachDao() {
    }

    public SachDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());

        return db.insert("SACH", null, values);
    }

    public int update(Sach sach) {
        ContentValues values = new ContentValues();
        values.put("tenSach", sach.getTenSach());
        values.put("giaThue", sach.getGiaThue());
        values.put("maLoai", sach.getMaLoai());

        return db.update("SACH", values, "maSach=?", new String[]{String.valueOf(sach.getMaSach())});
    }

    public int delete(String ID) {
        return db.delete("SACH", "maSach = ?", new String[]{String.valueOf(ID)});
    }

    @SuppressLint("Range")
    public List<Sach> getData(String sql, String...selectionArgs) {
        List<Sach> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while(c.moveToNext()) {
            Sach sach = new Sach();
            sach.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            sach.setTenSach(c.getString(c.getColumnIndex("tenSach")));
            sach.setGiaThue(Integer.parseInt(c.getString(c.getColumnIndex("giaThue"))));
            sach.setMaLoai(Integer.parseInt(c.getString(c.getColumnIndex("maLoai"))));

            list.add(sach);
        }

        return list;
    }

    public List<Sach> getAll() {
        String sql = "select * from SACH";
        return getData(sql);
    }

    public Sach getID(String ID) {
        String sql = "select * from SACH where maSach = ?";
        List<Sach> list = getData(sql);
        return list.get(0);
    }
}
