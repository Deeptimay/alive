package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.quick.buku.R;
import com.quick.buku.databinding.FragmentProfileDetailsBinding;
import com.quick.buku.models.Datum;

public class ProfileDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentProfileDetailsBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_details, container, false);
        View view = binding.getRoot();
        Datum userData = getArguments().getParcelable("Datum");
        binding.setUser(userData);
        return view;
    }
}
