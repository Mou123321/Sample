package com.example.sample.adpter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.sample.BR;
import com.example.sample.R;
import com.example.sample.databinding.ListItemBinding;
import com.example.sample.viewmodel.ItemViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class ItemRecyclerViewAdapter extends RecyclerView.Adapter<ItemRecyclerViewAdapter.ItemViewHolder>{
    private ClickListener listener;
    private List<ItemViewModel> items;

    public ItemRecyclerViewAdapter(ClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public void setData(List<ItemViewModel> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.list_item, parent, false);
        return new ItemViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        ItemViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ItemViewModel item) {
            binding.setVariable(BR.vm, item);
            ((ListItemBinding) binding).item.setOnClickListener((view) ->
                    listener.lunchIntent(item.title.get(), item.date.get(), item.description.get(), item.url.get(), item.phone.get()));
            ((ListItemBinding) binding).shareButton.setOnClickListener((view -> listener.share()));
            binding.executePendingBindings();
        }
    }

    public interface ClickListener {
        public void lunchIntent(String title, String date, String description, String image, String phone);
        public void share();
    }
}
