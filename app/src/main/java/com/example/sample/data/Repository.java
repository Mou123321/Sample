package com.example.sample.data;

import android.os.Environment;

import com.example.sample.data.local.ItemDao;
import com.example.sample.data.local.ItemEntity;
import com.example.sample.data.model.ItemModel;
import com.example.sample.data.remote.MyService;
import com.example.sample.util.AppUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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
                       ItemEntity itemEntity= new ItemEntity(itemModel);
                       String s = saveImageToFIle(itemModel.getTitle(), itemModel.getImage());
                       System.out.println("base url is " + itemModel.getImage());
                       System.out.println("url is " + s);
                       itemEntity.setImage(s);
                       itemDao.addItem(itemEntity);
                   }
                });
    }

    public Observable<List<ItemEntity>> getAllItemFromLocal() {
        return itemDao.getAll()
                .subscribeOn(Schedulers.io());
    }

    private String saveImageToFIle(String title, String urlString) {
        try {
            URL url = new URL(urlString);

            String imageFileName = title + ".jpg";
            File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES).toString() + "/Sample");

            boolean success = true;

            if (!storageDir.exists()) {
                success = storageDir.mkdirs();
            }

            if (success) {
                File imageFile = new File(storageDir, imageFileName);
                String savedImagePath = imageFile.getAbsolutePath();

                InputStream input = url.openStream();

                FileOutputStream outputStream = new FileOutputStream(imageFile);

                int n = 0;
                byte[] buffer = new byte[1024];

                while (n != -1) {
                    n = input.read(buffer);
                    if (n == -1) {
                        break;
                    }
                    outputStream.write(buffer, 0, n);
                }

                return savedImagePath;
            } else {
                return null;
            }

        } catch (FileNotFoundException | MalformedURLException e) {
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
