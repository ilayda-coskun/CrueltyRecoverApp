package com.nexis.cruletyrecoverapp.Fragments;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.Model.ProductModel;
import com.nexis.cruletyrecoverapp.R;
import com.nexis.cruletyrecoverapp.adapter.ProductAdapter;
import com.nexis.cruletyrecoverapp.databinding.FragmentProductDetailBinding;
import com.nexis.cruletyrecoverapp.helper.FirebaseAddNewItemHelper;
import com.nexis.cruletyrecoverapp.helper.FirebaseMarkaHelper;

import java.util.ArrayList;
import java.util.List;

public class ProductDetailFragment extends Fragment {
    private FragmentProductDetailBinding binding;
    private MarkaModel markaModel;
    private List<ProductModel> productModelList = new ArrayList<>();
    private ProductAdapter adapter;

    public ProductDetailFragment(MarkaModel markaModel) {
        // Required empty public constructor
        this.markaModel = markaModel;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_product_detail, container, false);
        adapter = new ProductAdapter(getContext(), productModelList);
        binding.recyclerProduct.setAdapter(adapter);
        getData();

        return binding.getRoot();
    }

    private void getData() {
        FirebaseAddNewItemHelper fireBaseHelper = new FirebaseAddNewItemHelper();
        Query query = fireBaseHelper.databaseReference.orderByChild("markaAd").equalTo(markaModel.getMarkaAdi());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productModelList.clear();
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    productModelList.add(child.getValue(ProductModel.class));
                }
                adapter.setProductModelList(productModelList);

            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }
}