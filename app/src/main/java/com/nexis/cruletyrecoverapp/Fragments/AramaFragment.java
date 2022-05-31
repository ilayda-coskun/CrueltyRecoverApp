package com.nexis.cruletyrecoverapp.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.R;
import com.nexis.cruletyrecoverapp.adapter.UrunAdapter;
import com.nexis.cruletyrecoverapp.databinding.FragmentAramaBinding;
import com.nexis.cruletyrecoverapp.databinding.FragmentUrunlerBinding;
import com.nexis.cruletyrecoverapp.helper.FirebaseMarkaHelper;

import java.util.ArrayList;
import java.util.List;

public class AramaFragment extends Fragment implements UrunAdapter.UrunAdapterListener {


    private List<MarkaModel> markaModelList = new ArrayList<>();
    private FragmentAramaBinding binding;
    private UrunAdapter adapter;


    public AramaFragment() {
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_arama, container, false);

        adapter = new UrunAdapter(getContext(), markaModelList, this, 0);
        binding.recyclerUrun.setAdapter(adapter);

        binding.searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter.getFilter().filter(binding.searchView.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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

    }
}