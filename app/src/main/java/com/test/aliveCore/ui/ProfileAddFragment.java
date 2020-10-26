package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.quick.buku.MyApplication;
import com.quick.buku.R;
import com.quick.buku.callBacks.OnItemClickedListenerDatum;
import com.quick.buku.databinding.FragmentProfileAddBinding;
import com.quick.buku.models.Datum;
import com.quick.buku.persistence.UserDao;
import com.quick.buku.persistence.UserDatabase;


public class ProfileAddFragment extends Fragment {

    NavController navController;
    int count;
    FragmentProfileAddBinding binding;
    Datum datum;
    private final OnItemClickedListenerDatum onItemClickedListenerDatum = new OnItemClickedListenerDatum() {
        @Override
        public void clickedItem() {
            createUserRoom();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile_add, container, false);
        datum = new Datum();
        binding.setCreateUser(datum);
        binding.setHandler(onItemClickedListenerDatum);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(ProfileAddFragment.this.getActivity(), R.id.nav_host_fragment);
        getCount();
    }

    public void createUserRoom() {
        try {
            if (!datum.getEmail().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                binding.etEmailId.setError("Invalid Email Address");
                return;
            }

            if (!datum.getEmail().isEmpty() && !datum.getFirstName().isEmpty() && !datum.getLastName().isEmpty()) {
                datum.setId(++count);

                new Thread(() -> {
                    UserDao userDao = UserDatabase.getInstance(MyApplication.getInstance()).getUserDao();
                    userDao.insert(datum);
                }).start();

                Bundle bundle = new Bundle();
                bundle.putParcelable("Datum", datum);
                navController.navigate(R.id.action_profileAddFragment_to_profileDetailFragment, bundle);
            }
        } catch (Exception e) {
            Toast.makeText(ProfileAddFragment.this.getActivity(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
        }
    }

    private void getCount() {
        new Thread(() -> {
            UserDao userDao = UserDatabase.getInstance(MyApplication.getInstance()).getUserDao();
            count = userDao.getTotalNumberOfColumns();
        }).start();
    }
}