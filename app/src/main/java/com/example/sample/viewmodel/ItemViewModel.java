package com.example.sample.viewmodel;

import com.example.sample.data.model.ItemModel;

import androidx.databinding.ObservableField;

public class ItemViewModel {
    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> location = new ObservableField<>();
    public ObservableField<String> url = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> date = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();

    public ItemViewModel(ItemModel itemModel) {
        title.set(itemModel.getTitle());
        location.set(itemModel.getLocation1());
        if (itemModel.getImage() != null) {
            url.set(itemModel.getImage());
        }
        description.set(itemModel.getDescription());
        date.set(itemModel.getDate());
        if (itemModel.getPhone() != null) {
            phone.set(itemModel.getPhone());
        }
    }
}
