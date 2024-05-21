package fpoly.nhanhhph47395.pnlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import fpoly.nhanhhph47395.pnlib.R;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private List<ThanhVien> list;
    private TextView tvMaTV, tvTenTV;

    public ThanhVienSpinnerAdapter(@NonNull Context context, List<ThanhVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }

        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText(item.getMaTV() + ". ");

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText(item.getHoTen());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.thanh_vien_item_spinner, null);
        }

        final ThanhVien item = list.get(position);
        if (item != null) {
            tvMaTV = v.findViewById(R.id.tvMaTV);
            tvMaTV.setText(item.getMaTV() + ". ");

            tvTenTV = v.findViewById(R.id.tvTenTV);
            tvTenTV.setText(item.getHoTen());
        }

        return v;
    }
}
