package com.nexis.cruletyrecoverapp.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.nexis.cruletyrecoverapp.R;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link HakkindaFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class HakkindaFragment extends Fragment {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
    public HakkindaFragment() {
         // Required empty public constructor
     }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment HakkindaFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static HakkindaFragment newInstance(String param1, String param2) {
//        HakkindaFragment fragment = new HakkindaFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }

    private View view;
    private ListView hakkindaListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_hakkinda, container, false);
        hakkindaListView =view.findViewById(R.id.hakkinda_listView);

        String[] hakkindaItems = new String[]{"Hakkında 1", "Hakkında2"};
        String[] hakkindaDetay = new String[] {"Detay 1", "Detay2"};

        ArrayAdapter<String> hakkindaArrayAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                hakkindaItems
        );

        hakkindaListView.setAdapter(hakkindaArrayAdapter);

        hakkindaListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder hakkindaClikDetay = new AlertDialog.Builder(getActivity());
                hakkindaClikDetay.setMessage(hakkindaDetay[i]).setCancelable(false).setPositiveButton("KAPAT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        hakkindaClikDetay.setCancelable(true);
                    }
                }).show();
            }
        });

        return view;
    }
}