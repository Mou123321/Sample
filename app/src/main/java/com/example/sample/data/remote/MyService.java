package com.example.sample.data.remote;

import com.example.sample.data.model.ItemModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface MyService {

    String END_POINT = "phunware-services/dev-interview-homework/master/feed.json";

    @GET(END_POINT)
    Observable<List<ItemModel>> getAllItem();
}
