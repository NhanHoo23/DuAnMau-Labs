package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;
import fpoly.nhanhhph47395.pnlib.models.ThuThu;

public class ThuThuDao {
    private SQLiteDatabase db;

    public ThuThuDao() {
    }

    public ThuThuDao(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("maTT", thuThu.getMaTT());
        values.put("hoTen", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());

        return db.insert("THUTHU", null, values);
    }

    public int updatePass(ThuThu thuThu) {
        ContentValues values = new ContentValues();
        values.put("hoTen", thuThu.getHoTen());
        values.put("matKhau", thuThu.getMatKhau());

        return db.update("THUTHU", values, "maTT=?", new String[]{String.valueOf(thuThu.getMaTT())});
    }

    public int delete(ThuThu thuThu) {
        return db.delete("THUTHU", "maTT = ?", new String[]{String.valueOf(thuThu.getMaTT())});
    }

    @SuppressLint("Range")
    public List<ThuThu> getData(String sql, String...selectionArgs) {
        List<ThuThu> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while(c.moveToNext()) {
            ThuThu thuThu = new ThuThu();
            thuThu.setMaTT(c.getString(c.getColumnIndex("maTV")));
            thuThu.setHoTen(c.getString(c.getColumnIndex("hoTen")));
            thuThu.setMatKhau(c.getString(c.getColumnIndex("matKhau")));

            list.add(thuThu);
        }

        return list;
    }

    public List<ThuThu> getAll() {
        String sql = "select * from THUTHU";
        return getData(sql);
    }

    public ThuThu getID(String ID) {
        String sql = "select * from THUTHU where maTT = ?";
        List<ThuThu> list = getData(sql);
        return list.get(0);
    }

    public int checkLogin(String id, String pass) {
        String sql = "Select * from THUTHU where maTT = ? and matKhau = ?";
        List<ThuThu> list = getData(sql, id, pass);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }
}
