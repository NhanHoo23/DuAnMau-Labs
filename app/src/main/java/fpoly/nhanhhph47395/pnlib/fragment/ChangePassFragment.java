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

public class ChangePassFragment extends Fragment {
    private TextInputEditText edPassOld, edPass, edRePass;
    private Button btnSave, btnCancel;
    private ThuThuDao dao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_change_pass, container, false);
        edPassOld = v.findViewById(R.id.edPassOld);
        edPass = v.findViewById(R.id.edPassChange);
        edRePass = v.findViewById(R.id.edRePassChange);
        btnSave = v.findViewById(R.id.btnSaveUserChange);
        btnCancel = v.findViewById(R.id.btnCancelUserChange);
        dao = new ThuThuDao(getContext());
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edPassOld.setText("");
                edPass.setText("");
                edRePass.setText("");
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = pref.getString("USERNAME", "");

                if (validate()) {
                    ThuThu thuThu = dao.getID(user);
                    thuThu.setMatKhau(edPass.getText().toString());

                    if (dao.updatePass(thuThu) > 0) {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        edPassOld.setText("");
                        edPass.setText("");
                        edRePass.setText("");
                    } else {
                        Toast.makeText(getContext(), "Thay đổi mật khẩu không thành công", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        return v;
    }

    public boolean validate() {
        if (edPassOld.getText().length() == 0 || edPass.getText().length() == 0 || edRePass.getText().length() == 0) {
            Toast.makeText(getContext(), "Bạn phải đăng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            SharedPreferences pref = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
            String passOld = pref.getString("PASSWORD", "");
            String pass = edPass.getText().toString();
            String rePass = edRePass.getText().toString();

            if (!passOld.equals(edPassOld.getText().toString())) {
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                return false;
            }
            if (!pass.equals(rePass)) {
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        return true;
    }
}