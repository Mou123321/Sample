package com.example.sample.view;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sample.R;
import com.example.sample.adpter.ItemRecyclerViewAdapter;
import com.example.sample.data.Repository;
import com.example.sample.databinding.ActivityMainBinding;
import com.example.sample.navigator.MainActivityNavigator;
import com.example.sample.viewmodel.ItemViewModel;
import com.example.sample.viewmodel.MainActivityViewModel;
import com.example.sample.viewmodellFactory.MainActivityViewModelFactory;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ItemRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(Repository.getInstance())).get(MainActivityViewModel.class);

        viewModel.getAllItem();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new ItemRecyclerViewAdapter(new ItemRecyclerViewAdapter.ClickListener() {
            @Override
            public void lunchIntent(String title, String date, String description, String image, String phone) {
                Toast.makeText(getApplicationContext(), "lunch called", Toast.LENGTH_LONG).show();
            }

            @Override
            public void share() {
                Toast.makeText(getApplicationContext(), "share called", Toast.LENGTH_LONG).show();
            }
        });

        binding.recyclerView.setAdapter(adapter);

        viewModel.setNavigator(this);
    }

    @Override
    public void setData(List<ItemViewModel> items) {
        adapter.setData(items);
    }
}
