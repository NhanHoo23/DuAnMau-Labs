package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.Sach;
import fpoly.nhanhhph47395.pnlib.models.Top;

public class ThongKeDao {
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ThongKeDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    @SuppressLint("Range")
    public List<Top> getTop() {
        String sqlTop = "Select maSach, count(maSach) as soLuong From PHIEUMUON group by maSach order by soLuong DESC LIMIT 10";
        List<Top> list = new ArrayList<>();
        SachDao dao = new SachDao(context);
        Cursor c = db.rawQuery(sqlTop, null);

        while(c.moveToNext()) {
            Top top = new Top();
            Sach sach = dao.getID(c.getString(c.getColumnIndex("maSach")));
            top.setTenSach(sach.getTenSach());
            top.setSoLuong(Integer.parseInt(c.getString(c.getColumnIndex("soLuong"))));

            list.add(top);
        }

        return list;
    }

    @SuppressLint("Range")
    public int getDoanhThu(String tuNgay, String denNgay) {
        String sqlDoanhThu = "Select Sum(giaThue) as doanhThu From PHIEUMUON where ngay between ? and ?";
        List<Integer> list = new ArrayList<>();
        Cursor c = db.rawQuery(sqlDoanhThu, new String[]{tuNgay, denNgay});

        while(c.moveToNext()) {
            try {
                list.add(Integer.parseInt(c.getString(c.getColumnIndex("doanhThu"))));
            } catch (Exception e) {
                list.add(0);
            }
        }

        return list.get(0);
    }
}
