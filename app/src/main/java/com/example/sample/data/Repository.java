package com.example.sample.data;

import com.example.sample.data.model.ItemModel;
import com.example.sample.data.remote.MyService;
import com.example.sample.util.AppUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    @Nullable
    private static Repository INSTANCE;

    @NonNull
    private final MyService myService;

    private Repository() {
        myService = AppUtil.getMyService();
    }

    public static Repository getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Repository();
        }

        return INSTANCE;
    }

    public Observable<List<ItemModel>> getAllItem() {
        return myService.getAllItem()
                .subscribeOn(Schedulers.io());
    }
}
