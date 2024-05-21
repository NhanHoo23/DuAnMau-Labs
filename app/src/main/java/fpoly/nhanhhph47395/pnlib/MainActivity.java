package fpoly.nhanhhph47395.pnlib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.navigation.NavigationView;

import fpoly.nhanhhph47395.pnlib.dao.PhieuMuonDao;
import fpoly.nhanhhph47395.pnlib.dao.ThuThuDao;
import fpoly.nhanhhph47395.pnlib.fragment.ChangePassFragment;
import fpoly.nhanhhph47395.pnlib.fragment.DoanhThuFragment;
import fpoly.nhanhhph47395.pnlib.fragment.LoaiSachFragment;
import fpoly.nhanhhph47395.pnlib.fragment.PhieuMuonFragment;
import fpoly.nhanhhph47395.pnlib.fragment.SachFragment;
import fpoly.nhanhhph47395.pnlib.fragment.ThanhVienFragment;
import fpoly.nhanhhph47395.pnlib.fragment.ThemThuThuFragment;
import fpoly.nhanhhph47395.pnlib.fragment.TopFragment;
import fpoly.nhanhhph47395.pnlib.models.ThuThu;


public class MainActivity extends AppCompatActivity {
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private View mHeaderView;
    private TextView edUser;

    private ThuThuDao thuThuDao;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.menu);
        ab.setDisplayHomeAsUpEnabled(true);

        FragmentManager manager = getSupportFragmentManager();
        setTitle("Quản lý Phiếu Mượn");
        PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
        manager.beginTransaction()
                .replace(R.id.flContent, phieuMuonFragment)
                .commit();

        NavigationView nv = findViewById(R.id.nvView);
        mHeaderView = nv.getHeaderView(0);
        edUser = mHeaderView.findViewById(R.id.txtUser);
        Intent i = getIntent();
        user = i.getStringExtra("user");
        thuThuDao = new ThuThuDao(this);
        ThuThu thuThu = thuThuDao.getID(user);
        edUser.setText("Welcome! " + thuThu.getHoTen());

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_PhieuMuon) {
                    setTitle("Quản lý Phiếu Mượn");
                    PhieuMuonFragment phieuMuonFragment = new PhieuMuonFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, phieuMuonFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_LoaiSach) {
                    setTitle("Quản lý Loại Sách");
                    LoaiSachFragment loaiSachFragment = new LoaiSachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, loaiSachFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_Sach) {
                    setTitle("Quản lý Sách");
                    SachFragment sachFragment = new SachFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, sachFragment)
                            .commit();
                } else if (item.getItemId() == R.id.nav_ThanhVien) {
                    setTitle("Quản lý Thành Viên");
                    ThanhVienFragment thanhVienFragment = new ThanhVienFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, thanhVienFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_Top) {
                    setTitle("Top 10 sách cho thuê nhiều nhất");
                    TopFragment topFragment = new TopFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, topFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_DoanhThu) {
                    setTitle("Thống kê doanh thu");
                    DoanhThuFragment doanhThuFragment = new DoanhThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, doanhThuFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_AddUser) {
                    setTitle("Thêm người dùng");
                    ThemThuThuFragment themThuThuFragment = new ThemThuThuFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, themThuThuFragment)
                            .commit();
                } else if (item.getItemId() == R.id.sub_Pass) {
                    setTitle("Đổi mật khẩu");
                    ChangePassFragment changePassFragment = new ChangePassFragment();
                    manager.beginTransaction()
                            .replace(R.id.flContent, changePassFragment)
                            .commit();

                } else if (item.getItemId() == R.id.sub_Logout) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {

                }

                drawer.closeDrawers();

                return false;
            }
        });

        //Set visible
        Menu navMenu = nv.getMenu();
        if (user.equals("admin")) {
            navMenu.findItem(R.id.sub_AddUser).setVisible(true);
        } else {
            navMenu.findItem(R.id.sub_AddUser).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            drawer.openDrawer(GravityCompat.START);
        }

        return super.onOptionsItemSelected(item);
    }
}