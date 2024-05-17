package fpoly.nhanhhph47395.pnlib.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(Context context) {
        super(context, "QuanLyThuVien", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo bảng ThuThu
        String tThuThu = "Create table THUTHU(" +
                "maTT text primary key," +
                "hoTen text not null," +
                "matKhau text not null)";
        db.execSQL(tThuThu);

        //Tạo bảng ThanhVien
        String tThanhVien = "Create table THANHVIEN(" +
                "maTV integer primary key autoincrement," +
                "hoTen text not null," +
                "namSinh text not null)";
        db.execSQL(tThanhVien);

        //Tạo bảng LoaiSachDao
        String tLoaiSach = "Create table LOAISACH(" +
                "maLoai integer primary key autoincrement," +
                "tenLoai text not null)";
        db.execSQL(tLoaiSach);

        //Tao bang Sach
        String tSach = "Create table SACH(" +
                "maSach integer primary key autoincrement," +
                "tenSach text not null," +
                "giaThue integer not null," +
                "maLoai integer references LOAISACH(maLoai))";
        db.execSQL(tSach);

        //Tao bang PhieuMuon
        String tPhieuMuon = "Create table PHIEUMUON(" +
                "maPM integer primary key autoincrement," +
                "maTT text references THUTHU(maTT)," +
                "maTV integer references THANHVIEN(maTV)," +
                "maSach integer references SACH(maSach)," +
                "giaThue integer not null," +
                "ngay date not null," +
                "traSach integer not null)";
        db.execSQL(tPhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTableThuThu = "drop table if exists THUTHU";
        db.execSQL(dropTableThuThu);
        String dropTableThanhVien = "drop table if exists THANHVIEN";
        db.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "drop table if exists LOAISACH";
        db.execSQL(dropTableLoaiSach);
        String dropTableSach = "drop table if exists SACH";
        db.execSQL(dropTableSach);
        String dropTablePhieuMuon = "drop table if exists PHIEUMUON";
        db.execSQL(dropTablePhieuMuon);

        onCreate(db);
    }
}
