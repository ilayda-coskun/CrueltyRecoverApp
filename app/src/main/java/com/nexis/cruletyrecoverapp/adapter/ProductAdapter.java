package com.nexis.cruletyrecoverapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nexis.cruletyrecoverapp.Model.MarkaModel;
import com.nexis.cruletyrecoverapp.Model.ProductModel;
import com.nexis.cruletyrecoverapp.databinding.LayoutProductItemBinding;
import com.nexis.cruletyrecoverapp.databinding.LayoutUrunAdapterItemBinding;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    public interface ProductAdapterListener {
        void onItemSelect(MarkaModel markaModel);
    }

    private Context context;
    private List<ProductModel> productModelList;
    private ProductAdapterListener adapterListener;

    public ProductAdapter(Context context, List<ProductModel> productModelList) {
        this.context = context;
        this.productModelList = productModelList;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutProductItemBinding binding = LayoutProductItemBinding.inflate(inflater, parent, false);
        return new ProductAdapter.ViewHolder(binding, adapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindTo(context, productModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    public List<ProductModel> getProductModelList() {
        return productModelList;
    }

    public void setProductModelList(List<ProductModel> productModelList) {
        this.productModelList = productModelList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private Context context;
        LayoutProductItemBinding binding;
        private ProductAdapterListener adapterListener;

        public ViewHolder(LayoutProductItemBinding binding, ProductAdapterListener adapterListener) {
            super(binding.getRoot());
            this.binding = binding;
            this.adapterListener = adapterListener;

        }

        void bindTo(Context context, ProductModel productModel) {
            this.context = context;
            binding.productName.setText(productModel.getProductName());
            if (productModel.getType() == 0){
                binding.cruletyText.setText("CruletyFree değildir.");
            } else {
                binding.cruletyText.setText("CruletyFree");
            }
        }

    }
}