package fpoly.nhanhhph47395.pnlib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fpoly.nhanhhph47395.pnlib.dao.ThuThuDao;

public class LoginActivity extends AppCompatActivity {
    private EditText edUserName, edPassword;
    private Button btnLogin, btnCancel;
    private CheckBox chkRememberPass;
    private String strUser, strPass;
    private ThuThuDao dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setTitle("Đăng nhập");
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.edPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnCancel = findViewById(R.id.btnCancel);
        chkRememberPass = findViewById(R.id.chkRememberPass);
        dao = new ThuThuDao(this);

        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        edUserName.setText(user);
        edPassword.setText(pass);
        chkRememberPass.setChecked(rem);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUserName.setText("");
                edUserName.setText("");
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        edit.commit();
    }

    public void checkLogin() {
        strUser = edUserName.getText().toString();
        strPass = edPassword.getText().toString();

        if (strUser.isEmpty() || strPass.isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống!!!", Toast.LENGTH_SHORT).show();
        } else {
            if (dao.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}