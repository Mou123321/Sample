package com.example.sample.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.sample.R;
import com.example.sample.adpter.ItemRecyclerViewAdapter;
import com.example.sample.data.Repository;
import com.example.sample.data.local.ItemDatabase;
import com.example.sample.databinding.ActivityMainBinding;
import com.example.sample.navigator.MainActivityNavigator;
import com.example.sample.viewmodel.ItemViewModel;
import com.example.sample.viewmodel.MainActivityViewModel;
import com.example.sample.viewmodellFactory.MainActivityViewModelFactory;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainActivity extends AppCompatActivity implements MainActivityNavigator {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private ItemRecyclerViewAdapter adapter;

    private static final int READ_REQUEST_CODE = 100;
    private static final int WRITE_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        viewModel = new ViewModelProvider(this,
                new MainActivityViewModelFactory(Repository.getInstance(ItemDatabase.getInstance(this).itemDao()))).get(MainActivityViewModel.class);

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

        checkWritePermission();
    }

    private void checkWritePermission() {
        int writePermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        );

        if (writePermission != PackageManager.PERMISSION_GRANTED) {
            makeWriteRequest();
        } else {
            viewModel.getAllItem();
        }
    }

    private void makeWriteRequest() {
        ActivityCompat.requestPermissions(
                this,
                new String[]{(Manifest.permission.WRITE_EXTERNAL_STORAGE)},
                WRITE_REQUEST_CODE
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_REQUEST_CODE:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    viewModel.getAllItem();
                }  else {
                    viewModel.getAllItem();
                    Toast.makeText(this, "So sad", Toast.LENGTH_LONG).show();
                }
                return;
        }
        // Other 'case' lines to check for other
        // permissions this app might request.
    }

    @Override
    public void setData(List<ItemViewModel> items) {
        adapter.setData(items);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.dispose();
    }
}
