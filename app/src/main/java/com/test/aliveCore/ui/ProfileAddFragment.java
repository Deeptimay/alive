package com.test.aliveCore.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.test.aliveCore.MyApplication;
import com.test.aliveCore.R;
import com.test.aliveCore.callBacks.OnItemClickedListenerDatum;
import com.test.aliveCore.databinding.FragmentProfileAddBinding;
import com.test.aliveCore.models.Age;
import com.test.aliveCore.models.Datum;
import com.test.aliveCore.persistence.UserDao;
import com.test.aliveCore.persistence.UserDatabase;
import com.test.aliveCore.utils.TimeUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class ProfileAddFragment extends Fragment {

    NavController navController;
    int count;
    FragmentProfileAddBinding binding;
    Datum datum;
    int mYear = 0, mMonth = 0, mDay = 0;
    DatePickerDialog datePickerDialog;
    Age age;
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
        binding.llDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCurrentDate();
        getCount();
    }

    public void createUserRoom() {
        try {
            if (!datum.getEmail().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
                binding.etEmailId.setError("Invalid Email Address");
                return;
            }

            if (!datum.getEmail().isEmpty() && !datum.getFirstName().isEmpty() && !datum.getLastName().isEmpty() && !datum.getDob().isEmpty()) {
                datum.setId(++count);
//                datum.setDob(String.valueOf(age.getYears()) + " Yrs " + String.valueOf(age.getYears()) + " Months " + String.valueOf(age.getYears()) + " Days");

                new Thread(() -> {
                    UserDao userDao = UserDatabase.getInstance(MyApplication.getInstance()).getUserDao();
                    userDao.insert(datum);
                }).start();

                navController = Navigation.findNavController(ProfileAddFragment.this.getActivity(), R.id.nav_host_fragment);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Datum", datum);
                navController.navigate(R.id.action_profileAddFragment_to_profileDetailFragment, bundle);
            } else
                Toast.makeText(ProfileAddFragment.this.getActivity(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
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

    void setCurrentDate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
    }

    public void showDatePicker() {

        datePickerDialog = new DatePickerDialog(ProfileAddFragment.this.getContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        try {
                            mDay = dayOfMonth;
                            mMonth = monthOfYear + 1;
                            mYear = year;
                            String sDate = mDay + "/" + mMonth + "/" + mYear;

                            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(sDate);
                            age = TimeUtil.calculateAge(date);
                            binding.tvDob.setText(sDate);
                            datum.setDob(String.valueOf(age.getYears()) + " Yrs " + String.valueOf(age.getYears()) + " Months " + String.valueOf(age.getYears()) + " Days");
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}