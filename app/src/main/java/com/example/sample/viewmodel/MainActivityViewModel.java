package com.example.sample.viewmodel;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.sample.data.Repository;
import com.example.sample.data.local.ItemEntity;
import com.example.sample.data.model.ItemModel;
import com.example.sample.navigator.MainActivityNavigator;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivityViewModel extends ViewModel {
    private List<ItemViewModel> items;
    private Repository repository;

    private WeakReference<MainActivityNavigator> navigator;

    public MainActivityViewModel(Repository repository)
    {
        this.repository = repository;
        items = new ArrayList<>();
    }

    public void setNavigator(MainActivityNavigator navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    @SuppressLint("CheckResult")
    public void getAllItem() {
        repository.getAllItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    items.clear();

                    for (ItemModel itemModel : list) {
                        items.add(new ItemViewModel(itemModel));
                    }

                    setData();
                },
                        e -> Log.e("Get All Item Error is ", Objects.requireNonNull(e.getMessage())));
    }

    @SuppressLint("CheckResult")
    public void getAllItemFromLocal() {
        repository.getAllItemFromLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            items.clear();

                            for (ItemEntity itemEntity : list) {
                                items.add(new ItemViewModel(itemEntity));
                            }

                            setData();
                        },
                        e -> Log.e("Get All Item Error is ", Objects.requireNonNull(e.getMessage())));
    }

    private void setData() {
        if (navigator != null && navigator.get() != null) {
            navigator.get().setData(items);
        }
    }

}
