package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.quick.buku.R;
import com.quick.buku.models.Datum;


public class ProfileAddFragment extends Fragment {

    EditText et_email_id, et_first_name, et_second_name;
    ImageView iv_display_picture;
    Button button_create;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        et_email_id = view.findViewById(R.id.et_email_id);
        et_first_name = view.findViewById(R.id.et_first_name);
        et_second_name = view.findViewById(R.id.et_second_name);
        button_create = view.findViewById(R.id.button_create);
        iv_display_picture = view.findViewById(R.id.iv_display_picture);

        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email_id.getText().toString();
                String firstName = et_email_id.getText().toString();
                String secondName = et_email_id.getText().toString();

                if (!email.isEmpty() && !firstName.isEmpty() && !secondName.isEmpty()) {
                    Datum userData = new Datum();
                    userData.setEmail(email);
                    userData.setFirstName(firstName);
                    userData.setLastName(secondName);
                }
            }
        });
    }
}