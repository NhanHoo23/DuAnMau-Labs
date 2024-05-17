package fpoly.nhanhhph47395.pnlib.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fpoly.nhanhhph47395.pnlib.database.DbHelper;
import fpoly.nhanhhph47395.pnlib.models.PhieuMuon;
import fpoly.nhanhhph47395.pnlib.models.Sach;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;
import fpoly.nhanhhph47395.pnlib.models.Top;

public class PhieuMuonDao {
    private SQLiteDatabase db;
    private Context context;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonDao() {
    }

    public PhieuMuonDao(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("ngay", sdf.format(phieuMuon.getNgay()));
        values.put("giaThue", phieuMuon.getTienThue());
        values.put("traSach", phieuMuon.getTraSach());

        return db.insert("PHIEUMUON", null, values);
    }

    public int update(PhieuMuon phieuMuon) {
        ContentValues values = new ContentValues();
        values.put("maTT", phieuMuon.getMaTT());
        values.put("maTV", phieuMuon.getMaTV());
        values.put("maSach", phieuMuon.getMaSach());
        values.put("ngay", sdf.format(phieuMuon.getNgay()));
        values.put("giaThue", phieuMuon.getTienThue());
        values.put("traSach", phieuMuon.getTraSach());

        return db.update("PHIEUMUON", values, "maPM=?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    public int delete(PhieuMuon phieuMuon) {
        return db.delete("PHIEUMUON", "maPM = ?", new String[]{String.valueOf(phieuMuon.getMaPM())});
    }

    @SuppressLint("Range")
    public List<PhieuMuon> getData(String sql, String...selectionArgs) {
        List<PhieuMuon> list = new ArrayList<>();
        Cursor c = db.rawQuery(sql, selectionArgs);

        while(c.moveToNext()) {
            PhieuMuon phieuMuon = new PhieuMuon();
            phieuMuon.setMaPM(Integer.parseInt(c.getString(c.getColumnIndex("maPM"))));
            phieuMuon.setMaTT(c.getString(c.getColumnIndex("maTT")));
            phieuMuon.setMaTV(Integer.parseInt(c.getString(c.getColumnIndex("maTV"))));
            phieuMuon.setMaSach(Integer.parseInt(c.getString(c.getColumnIndex("maSach"))));
            phieuMuon.setTraSach(Integer.parseInt(c.getString(c.getColumnIndex("traSach"))));
            try {
                phieuMuon.setNgay(sdf.parse(c.getString(c.getColumnIndex("ngay"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            list.add(phieuMuon);
        }

        return list;
    }

    public List<PhieuMuon> getAll() {
        String sql = "select * from PHIEUMUON";
        return getData(sql);
    }

    public PhieuMuon getID(String ID) {
        String sql = "select * from PHIEUMUON where maPM = ?";
        List<PhieuMuon> list = getData(sql);
        return list.get(0);
    }

    //thongke Top10
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
        String sqlDoanhThu = "Select Sum(tienThue) as doanhThu From PHIEUMUON where ngay between ? and ?";
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
