package com.test.aliveCore.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.test.aliveCore.models.Datum;
import com.test.aliveCore.repositories.UserRepository;
import com.test.aliveCore.utils.Resource;

import java.util.List;

public class UserViewModel extends AndroidViewModel {

    private final LiveData<Resource<List<Datum>>> popularArticles;
    UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance(application);
        popularArticles = userRepository.loadUsers();
    }

    public LiveData<Resource<List<Datum>>> getUsers() {
        return popularArticles;
    }
}





















