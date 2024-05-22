package fpoly.nhanhhph47395.pnlib.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import fpoly.nhanhhph47395.pnlib.R;
import fpoly.nhanhhph47395.pnlib.adapter.TopAdapter;
import fpoly.nhanhhph47395.pnlib.dao.LoaiSachDao;
import fpoly.nhanhhph47395.pnlib.dao.ThongKeDao;
import fpoly.nhanhhph47395.pnlib.models.LoaiSach;
import fpoly.nhanhhph47395.pnlib.models.Top;

public class TopFragment extends Fragment {
    private ListView lvTop;
    private List<Top> list;
    private TopAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_top, container, false);
        lvTop = v.findViewById(R.id.lvTop);

        capNhatLV();

        return v;
    }

    void capNhatLV() {
        ThongKeDao thongKeDao = new ThongKeDao(getActivity());
        list = (List<Top>) thongKeDao.getTop();
        adapter = new TopAdapter(getActivity(), this, list);
        lvTop.setAdapter(adapter);
    }
}