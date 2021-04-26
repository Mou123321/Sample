package com.example.sample.viewmodellFactory;

import com.example.sample.data.Repository;
import com.example.sample.data.model.ItemModel;
import com.example.sample.viewmodel.ItemDetailActivityViewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ItemDetailActivityViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final Repository repository;
    private final ItemModel item;

    public ItemDetailActivityViewModelFactory(Repository repository, ItemModel item) {
        this.repository = repository;
        this.item = item;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ItemDetailActivityViewModel(repository, item);
    }
}
