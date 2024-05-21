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
import fpoly.nhanhhph47395.pnlib.fragment.TopFragment;
import fpoly.nhanhhph47395.pnlib.models.Top;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    private TopFragment fragment;
    private List<Top> list;

    private TextView tvSach, tvSL;
    private ImageView imgDel;

    public TopAdapter(@NonNull Context context, TopFragment fragment, List<Top> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item, null);
        }

        final Top item = list.get(position);
        if (item != null) {
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSL = v.findViewById(R.id.tvSL);
            tvSL.setText("Số lượng: " + item.getSoLuong());
        }

        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.top_item, null);
        }

        final Top item = list.get(position);
        if (item != null) {
            tvSach = v.findViewById(R.id.tvSach);
            tvSach.setText("Sách: " + item.getTenSach());
            tvSL = v.findViewById(R.id.tvSL);
            tvSL.setText("Số lượng: " + item.getSoLuong());
        }

        return v;
    }
}
