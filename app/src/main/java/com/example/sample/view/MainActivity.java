package com.example.sample.view;

import android.os.Bundle;

import com.example.sample.R;
import com.example.sample.data.Repository;
import com.example.sample.viewmodel.MainActivityViewModel;
import com.example.sample.viewmodellFactory.MainActivityViewModelFactory;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this, new MainActivityViewModelFactory(Repository.getInstance())).get(MainActivityViewModel.class);

        viewModel.getAllItem();
    }
}
