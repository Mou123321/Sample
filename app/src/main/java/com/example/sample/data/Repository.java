package com.example.sample.data;

import com.example.sample.data.local.ItemDao;
import com.example.sample.data.local.ItemEntity;
import com.example.sample.data.model.ItemModel;
import com.example.sample.data.remote.MyService;
import com.example.sample.util.AppUtil;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class Repository {

    @Nullable
    private static Repository INSTANCE;

    @NonNull
    private final MyService myService;

    @NonNull
    private final ItemDao itemDao;

    private Repository(@NonNull ItemDao itemDao) {
        myService = AppUtil.getMyService();
        this.itemDao = itemDao;
    }

    public static Repository getInstance(ItemDao itemDao) {
        if (INSTANCE == null) {
            INSTANCE = new Repository(itemDao);
        }

        return INSTANCE;
    }

    public Observable<List<ItemModel>> getAllItem() {
        return myService.getAllItem()
                .subscribeOn(Schedulers.io())
                .doOnNext(list ->{
                   for (ItemModel itemModel : list) {
                       itemDao.addItem(new ItemEntity(itemModel));
                   }
                });
    }

    public Observable<List<ItemEntity>> getAllItemFromLocal() {
        return itemDao.getAll()
                .subscribeOn(Schedulers.io());
    }
}
