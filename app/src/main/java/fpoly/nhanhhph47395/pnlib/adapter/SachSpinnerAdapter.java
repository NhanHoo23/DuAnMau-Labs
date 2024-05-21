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
import fpoly.nhanhhph47395.pnlib.models.Sach;
import fpoly.nhanhhph47395.pnlib.models.ThanhVien;


public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private List<Sach> list;
    private TextView tvMaSach, tvTenSach;

    public SachSpinnerAdapter(@NonNull Context context, List<Sach> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }

        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText(item.getMaSach() + ". ");

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText(item.getTenSach());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.sach_item_spinner, null);
        }

        final Sach item = list.get(position);
        if (item != null) {
            tvMaSach = v.findViewById(R.id.tvMaSach);
            tvMaSach.setText(item.getMaSach() + ". ");

            tvTenSach = v.findViewById(R.id.tvTenSach);
            tvTenSach.setText(item.getTenSach());
        }

        return v;
    }
}
