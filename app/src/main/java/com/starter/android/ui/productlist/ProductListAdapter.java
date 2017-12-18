package com.starter.android.ui.productlist;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.starter.android.R;
import com.starter.android.databinding.ItemProductBinding;
import com.starter.android.model.Product;
import com.starter.android.util.ActivityRouter;

import io.realm.RealmResults;


public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ItemViewHolder> {

    private ActivityRouter router;
    private final RealmResults<Product> dataList;

    public ProductListAdapter(ActivityRouter router, RealmResults<Product> dataList) {
        this.router=router;
        this.dataList = dataList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemProductBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);
        return new ItemViewHolder(binding, new ProductItemViewModel());
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.bind(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    class ItemViewHolder extends RecyclerView.ViewHolder {

        private final ProductItemViewModel viewModel;
        private final ItemProductBinding binding;

        public ItemViewHolder(ItemProductBinding binding, ProductItemViewModel viewModel) {
            super(binding.getRoot());
            this.viewModel = viewModel;
            this.binding = binding;
        }

        public void bind(Product product) {
            viewModel.setProduct(product);
            binding.setVm(viewModel);
            binding.executePendingBindings();

            binding.btnProduct.setOnClickListener(v -> {

                if(getAdapterPosition()==RecyclerView.NO_POSITION)return;
                Product product1=dataList.get(getAdapterPosition());
                router.startProductDetailActivity(product1.getId());
            });
        }
    }

}