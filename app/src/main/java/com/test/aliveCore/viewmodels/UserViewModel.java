package com.quick.buku.viewmodels;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.quick.buku.models.Datum;
import com.quick.buku.repositories.UserRepository;
import com.quick.buku.utils.Resource;

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





















