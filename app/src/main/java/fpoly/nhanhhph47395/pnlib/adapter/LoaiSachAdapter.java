package fpoly.nhanhhph47395.pnlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.nhanhhph47395.pnlib.R;
import fpoly.nhanhhph47395.pnlib.fragment.LoaiSachFragment;
import fpoly.nhanhhph47395.pnlib.fragment.ThanhVienFragment;
import fpoly.nhanhhph47395.pnlib.models.LoaiSach;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {

    private Context context;
    private LoaiSachFragment fragment;
    private List<LoaiSach> list;

    private TextView tvMaLoai, tvTenLoai;
    private ImageView imgDel;

    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment fragment, List<LoaiSach> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.loai_sach_item, null);
        }

        final LoaiSach item = list.get(position);
        if (item != null) {
            tvMaLoai = v.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã loại: " + item.getMaLoai());
            tvTenLoai = v.findViewById(R.id.tvTenLoai);
            tvTenLoai.setText("Tên Loại: " + item.getTenLoai());

            imgDel = v.findViewById(R.id.imgDeleteLS);
        }

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment.xoa(String.valueOf(item.getMaLoai()));
            }
        });

        return v;
    }
}
