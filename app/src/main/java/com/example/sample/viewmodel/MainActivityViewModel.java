package com.example.sample.viewmodel;

import android.annotation.SuppressLint;

import com.example.sample.data.Repository;
import com.example.sample.data.model.ItemModel;

import java.util.List;

import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivityViewModel extends ViewModel {
    private List<ItemModel> items;
    private Repository repository;

    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
    }

    @SuppressLint("CheckResult")
    public void getAllItem() {
        repository.getAllItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    for (ItemModel item : list) {
                        System.out.println(item.getTitle());
                    }
                });
    }

}
