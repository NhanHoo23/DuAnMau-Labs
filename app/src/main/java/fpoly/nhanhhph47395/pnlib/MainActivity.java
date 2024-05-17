package fpoly.nhanhhph47395.pnlib;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fpoly.nhanhhph47395.pnlib.dao.DemoDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tạo db
        DemoDB demoDB = new DemoDB(getApplicationContext());
        demoDB.thanhVien();
    }
}