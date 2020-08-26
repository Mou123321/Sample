package com.example.sample.navigator;

import com.example.sample.viewmodel.ItemViewModel;

import java.util.List;

public interface MainActivityNavigator {
    void setData(List<ItemViewModel> items);
}
