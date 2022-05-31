package com.nexis.cruletyrecoverapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.Model.ProductModel;
import com.nexis.cruletyrecoverapp.databinding.LayoutUrunAdapterItemBinding;
import com.nexis.cruletyrecoverapp.helper.FirebaseAddNewItemHelper;
import com.nexis.cruletyrecoverapp.helper.FirebaseMarkaHelper;

import java.util.ArrayList;
import java.util.List;

public class UrunAdapter extends RecyclerView.Adapter<UrunAdapter.ViewHolder> implements Filterable {

    public interface UrunAdapterListener {
        void onItemSelect(MarkaModel markaModel);
    }

    private Context context;
    private List<MarkaModel> markaList;
    private List<MarkaModel> markaSearchList;
    private UrunAdapterListener adapterListener;
    private int pageType;

    public UrunAdapter(Context context, List<MarkaModel> markaList, UrunAdapterListener adapterListener, int pageType) {
        this.context = context;
        this.markaList = markaList;
        this.markaSearchList = this.markaList;
        this.adapterListener = adapterListener;
        this.pageType = pageType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutUrunAdapterItemBinding binding = LayoutUrunAdapterItemBinding.inflate(inflater, parent, false);
        return new UrunAdapter.ViewHolder(binding, adapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(context, markaList.get(position), pageType, markaList.size());
    }

    @Override
    public int getItemCount() {
        return markaList.size();
    }

    @Override
    public Filter getFilter() {
        return searchFilter;
    }

    private Filter searchFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<MarkaModel> filteredStockModels = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredStockModels.addAll(markaSearchList);
            } else {
                for (MarkaModel markaModel : markaSearchList) {
                    if (markaModel.getMarkaAdi().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredStockModels.add(markaModel);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredStockModels;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            markaList.clear();
            markaList = (List<MarkaModel>) filterResults.values;
            notifyDataSetChanged();
        }
    };


    public List<MarkaModel> getMarkaList() {
        return markaList;
    }

    public void setMarkaList(List<MarkaModel> markaList) {
        this.markaList = markaList;
        this.markaSearchList = new ArrayList<>(markaList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        LayoutUrunAdapterItemBinding binding;
        private UrunAdapterListener adapterListener;
        private List<ProductModel> productModelList = new ArrayList<>();
        private ProductAdapter adapter;

        public ViewHolder(LayoutUrunAdapterItemBinding binding, UrunAdapterListener adapterListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapterListener = adapterListener;

        }

        void bindTo(Context context, MarkaModel markaModel, int pageType, int position) {
            this.context = context;
            binding.markaAdi.setText(markaModel.getMarkaAdi());
            binding.recyclerProduct.setVisibility(View.GONE);
            binding.mainlayout.setOnClickListener(view -> {
                if (pageType == 0 && position == 1){
                    adapter = new ProductAdapter(context, productModelList);
                    binding.recyclerProduct.setVisibility(View.VISIBLE);
                    binding.recyclerProduct.setAdapter(adapter);
                    getData(markaModel);
                } else {
                    adapterListener.onItemSelect(markaModel);
                }
            });
        }

        private void getData(MarkaModel markaModel) {
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
}
