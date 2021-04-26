package com.example.sample.viewmodel;

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
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class MainActivityViewModel extends ViewModel {
    private List<ItemViewModel> items;
    private Repository repository;

    private WeakReference<MainActivityNavigator> navigator;

    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public MainActivityViewModel(Repository repository)
    {
        this.repository = repository;
        items = new ArrayList<>();
    }

    public void setNavigator(MainActivityNavigator navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    public void getAllItem() {
        Disposable disposable = repository.getAllItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    items.clear();

                    for (ItemModel itemModel : list) {
                        items.add(new ItemViewModel(itemModel));
                    }

                    setData();
                },
                        e -> Log.e("Get All Item Error is ", Objects.requireNonNull(e.getMessage())));

        compositeDisposable.add(disposable);
    }

    public void getAllItemFromLocal() {
        Disposable disposable = repository.getAllItemFromLocal()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                            items.clear();

                            for (ItemEntity itemEntity : list) {
                                items.add(new ItemViewModel(itemEntity));
                            }

                            setData();
                        },
                        e -> Log.e("Get All Item Error is ", Objects.requireNonNull(e.getMessage())));

        compositeDisposable.add(disposable);
    }

    private void setData() {
        if (navigator != null && navigator.get() != null) {
            navigator.get().setData(items);
        }
    }

    public void dispose() {
        compositeDisposable.dispose();
    }
}
