package com.nexis.cruletyrecoverapp.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.R;
import com.nexis.cruletyrecoverapp.adapter.UrunAdapter;
import com.nexis.cruletyrecoverapp.databinding.FragmentUrunlerBinding;
import com.nexis.cruletyrecoverapp.helper.FirebaseAddNewItemHelper;
import com.nexis.cruletyrecoverapp.helper.FirebaseMarkaHelper;

import java.util.ArrayList;
import java.util.List;

public class UrunlerFragment extends Fragment implements UrunAdapter.UrunAdapterListener {

    private List<MarkaModel> markaModelList = new ArrayList<>();
    private FragmentUrunlerBinding binding;
    private UrunAdapter adapter;

    public UrunlerFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_urunler, container, false);

        adapter = new UrunAdapter(getContext(), markaModelList, this, 1);

        binding.recyclerUrun.setAdapter(adapter);
        getData();

        return binding.getRoot();
    }


    private void getData() {
        FirebaseMarkaHelper fireBaseHelper = new FirebaseMarkaHelper();
        fireBaseHelper.databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                markaModelList.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    markaModelList.add(child.getValue(MarkaModel.class));
                }

                adapter.setMarkaList(markaModelList);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }

    @Override
    public void onItemSelect(MarkaModel markaModel) {
        fragmentAyarla(new ProductDetailFragment(markaModel));
    }


    private void fragmentAyarla(Fragment fragment ){

        FragmentTransaction transaction;
        transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.sekme_activity_frameLayout, fragment);
        transaction.commit();
    }
}