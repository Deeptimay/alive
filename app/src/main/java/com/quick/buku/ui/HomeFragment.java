package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quick.buku.R;
import com.quick.buku.adapters.ProfileRvAdapter;
import com.quick.buku.callBacks.OnItemClickedListener;
import com.quick.buku.models.Datum;

import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickedListener {

    RecyclerView rv_user_list;
    ProfileRvAdapter profileRvAdapter;
    private List<Datum> userList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        rv_user_list = view.findViewById(R.id.rv_user_list);

        profileRvAdapter = new ProfileRvAdapter(userList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(HomeFragment.this.getContext());
        rv_user_list.setLayoutManager(mLayoutManager);
        rv_user_list.setItemAnimator(new DefaultItemAnimator());
        rv_user_list.setAdapter(profileRvAdapter);

    }

    @Override
    public void clickedItem(Bundle data) {

    }
}