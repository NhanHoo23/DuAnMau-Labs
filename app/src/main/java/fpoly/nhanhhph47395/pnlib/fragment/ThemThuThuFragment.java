package fpoly.nhanhhph47395.pnlib.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.nhanhhph47395.pnlib.R;
import fpoly.nhanhhph47395.pnlib.dao.ThuThuDao;
import fpoly.nhanhhph47395.pnlib.models.ThuThu;

public class ThemThuThuFragment extends Fragment {
    private TextInputEditText edUsername, edFullname, edPass, edRePass;
    private Button btnSave, btnCancel;
    private ThuThuDao dao;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_them_thu_thu, container, false);
        edUsername = v.findViewById(R.id.edUserName);
        edFullname = v.findViewById(R.id.edFullname);
        edPass = v.findViewById(R.id.edPass);
        edRePass = v.findViewById(R.id.edRePass);
        btnSave = v.findViewById(R.id.btnSaveUser);
        btnCancel = v.findViewById(R.id.btnCancelUser);
        dao = new ThuThuDao(getContext());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edUsername.setText("");
                edFullname.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ThuThu thuThu = new ThuThu();
                thuThu.setMaTT(edUsername.getText().toString());
                thuThu.setHoTen(edFullname.getText().toString());
                thuThu.setMatKhau(edPass.getText().toString());
                if (validate()) {
                    if (dao.insert(thuThu) > 0) {
                        Toast.makeText(getContext(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edUsername.setText("");
                        edFullname.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getContext(), "Lưu thất bại ", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return v;
    }

    public boolean validate() {
        if (edUsername.getText().length() == 0 || edFullname.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải đăng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!rePass.equals(pass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }
}