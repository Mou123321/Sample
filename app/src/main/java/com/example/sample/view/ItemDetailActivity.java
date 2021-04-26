package com.example.sample.view;

import android.os.Bundle;

import com.example.sample.R;
import com.example.sample.data.Repository;
import com.example.sample.data.local.ItemDatabase;
import com.example.sample.data.model.ItemModel;
import com.example.sample.databinding.ActivityItemDetailBinding;
import com.example.sample.viewmodel.ItemDetailActivityViewModel;
import com.example.sample.viewmodellFactory.ItemDetailActivityViewModelFactory;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

public class ItemDetailActivity extends AppCompatActivity {
    private ActivityItemDetailBinding binding;
    private ItemDetailActivityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_item_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            ItemModel item = bundle.getParcelable("item");
            if (item != null) {
                viewModel = new ViewModelProvider(this,
                        new ItemDetailActivityViewModelFactory(Repository.getInstance(ItemDatabase.getInstance(this).itemDao()), item)).get(ItemDetailActivityViewModel.class);
                binding.setVm(viewModel);
            }
        }
    }
}
