package com.test.aliveCore.repositories;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.test.aliveCore.models.Datum;
import com.test.aliveCore.models.UserResponse;
import com.test.aliveCore.persistence.UserDao;
import com.test.aliveCore.persistence.UserDatabase;
import com.test.aliveCore.services.RetrofitClient;
import com.test.aliveCore.utils.NetworkBoundResource;
import com.test.aliveCore.utils.Resource;

import java.util.List;

import retrofit2.Call;

public class UserRepository {

    private static final String TAG = "UserRepository";
    private static UserRepository instance;
    private final UserDao userDao;

    private UserRepository(Context context) {
        userDao = UserDatabase.getInstance(context).getUserDao();
    }

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    /**
     * This method fetches the popular articles from the service.
     * Once the fetching is done the data is cached to local db so that the app can even work offline
     *
     * @return List of articles
     */
    public LiveData<Resource<List<Datum>>> loadUsers() {
        return new NetworkBoundResource<List<Datum>, UserResponse>() {

            @Override
            protected void saveCallResult(UserResponse item) {
                if (null != item)
                    userDao.insertAll(item.getData());
            }

            @NonNull
            @Override
            protected LiveData<List<Datum>> loadFromDb() {
                return userDao.getAll();
            }

            @NonNull
            @Override
            protected Call<UserResponse> createCall() {
                return RetrofitClient.getInstance().getUserList();
            }
        }.getAsLiveData();
    }
}












