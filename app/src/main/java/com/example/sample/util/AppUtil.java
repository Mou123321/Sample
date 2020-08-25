package com.example.sample.util;

import com.example.sample.data.remote.MyService;
import com.example.sample.data.remote.RetrofitClient;

public class AppUtil {

    private static final String BASE_UTIL = "https://raw.githubusercontent.com";

    public static MyService getMyService() {
        return RetrofitClient.getClient(BASE_UTIL).create(MyService.class);
    }
}
