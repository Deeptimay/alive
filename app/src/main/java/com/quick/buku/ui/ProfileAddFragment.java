package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.quick.buku.MyApplication;
import com.quick.buku.R;
import com.quick.buku.models.Datum;
import com.quick.buku.persistence.UserDao;
import com.quick.buku.persistence.UserDatabase;


public class ProfileAddFragment extends Fragment {

    NavController navController;
    int count;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        navController = Navigation.findNavController(ProfileAddFragment.this.getActivity(), R.id.nav_host_fragment);
        getCount();
    }

    private void initView(View view) {
        EditText et_email_id = view.findViewById(R.id.et_email_id);
        EditText et_first_name = view.findViewById(R.id.et_first_name);
        EditText et_second_name = view.findViewById(R.id.et_second_name);
        Button button_create = view.findViewById(R.id.button_create);
//        ImageView iv_display_picture = view.findViewById(R.id.iv_display_picture);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email_id.getText().toString();
                String firstName = et_first_name.getText().toString();
                String secondName = et_second_name.getText().toString();

                if (!email.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                    et_email_id.setError("Invalid Email Address");
                    return;
                }

                if (!email.isEmpty() && !firstName.isEmpty() && !secondName.isEmpty()) {
                    Datum userData = new Datum();
                    userData.setId(++count);
                    userData.setEmail(email);
                    userData.setFirstName(firstName);
                    userData.setLastName(secondName);

                    new Thread(() -> {
                        UserDao userDao = UserDatabase.getInstance(MyApplication.getInstance()).getUserDao();
                        userDao.insert(userData);
                    }).start();

                    Bundle bundle = new Bundle();
                    bundle.putParcelable("Datum", userData);
                    navController.navigate(R.id.action_profileAddFragment_to_profileDetailFragment, bundle);
                }
            }
        });
    }

    private void getCount() {
        new Thread(() -> {
            UserDao userDao = UserDatabase.getInstance(MyApplication.getInstance()).getUserDao();
            count = userDao.getTotalNumberOfColumns();
        }).start();
    }
}