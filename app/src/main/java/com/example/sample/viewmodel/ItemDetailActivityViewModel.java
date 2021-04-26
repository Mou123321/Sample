package com.example.sample.viewmodel;

import com.example.sample.data.Repository;
import com.example.sample.data.model.ItemModel;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

public class ItemDetailActivityViewModel extends ViewModel {

    public ObservableField<String> title = new ObservableField<>("");
    public ObservableField<String> url = new ObservableField<>("");
    public ObservableField<String> description = new ObservableField<>("");

    private Repository repository;

    public ItemDetailActivityViewModel(Repository repository, ItemModel item) {
        this.repository = repository;
        title.set(item.getTitle());
        url.set(item.getImage());
        description.set(item.getDescription());
    }
}
