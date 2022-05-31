package com.nexis.cruletyrecoverapp.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.Model.ProductModel;
import com.nexis.cruletyrecoverapp.R;
import com.nexis.cruletyrecoverapp.databinding.FragmentAddItemBinding;
import com.nexis.cruletyrecoverapp.helper.FirebaseAddNewItemHelper;
import com.nexis.cruletyrecoverapp.helper.FirebaseMarkaHelper;

import java.util.ArrayList;
import java.util.List;

public class AddItemFragment extends Fragment {


    private List<MarkaModel> markaModelList = new ArrayList<>();
    private List<String> markaNameList = new ArrayList<>();

    private FragmentAddItemBinding binding;


    public AddItemFragment() {
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

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_item, container, false);

        binding.addNewProduct.setOnClickListener(view -> {
            ProductModel markaModel = new ProductModel();
            markaModel.setProductName(binding.productName.getText().toString());
            markaModel.setMarkaAd(binding.productSpinner.getSelectedItem().toString());
            if (binding.isCrulety.isChecked()){
                markaModel.setType(0);
            } else {
                markaModel.setType(1);
            }
            FirebaseAddNewItemHelper firebaseAddNewItemHelper = new FirebaseAddNewItemHelper();
            firebaseAddNewItemHelper.addUser(markaModel.getProductName(), markaModel);
        });

        binding.addNewItem.setOnClickListener(view -> {
            MarkaModel markaModel = new MarkaModel();
            markaModel.setMarkaAdi(binding.markaAdi.getText().toString());
            if (binding.isCrulety.isChecked()){
                markaModel.setType(0);
            } else {
                markaModel.setType(1);
            }
            FirebaseMarkaHelper firebaseAddNewItemHelper = new FirebaseMarkaHelper();
            firebaseAddNewItemHelper.addUser(markaModel.getMarkaAdi(), markaModel);
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
                    MarkaModel markaModel = child.getValue(MarkaModel.class);
                    markaModelList.add(markaModel);
                    markaNameList.add(markaModel.getMarkaAdi());
                }


                ArrayAdapter<String> offerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, markaNameList);
                offerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                binding.productSpinner.setAdapter(offerAdapter);
                binding.productSpinner.setSelection(0);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}