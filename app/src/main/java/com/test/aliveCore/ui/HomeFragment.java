package com.test.aliveCore.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.test.aliveCore.R;
import com.test.aliveCore.adapters.ProfileRvAdapter;
import com.test.aliveCore.callBacks.OnItemClickedListener;
import com.test.aliveCore.models.Datum;
import com.test.aliveCore.utils.Resource;
import com.test.aliveCore.viewmodels.UserViewModel;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnItemClickedListener {

    RecyclerView rv_user_list;
    ProfileRvAdapter profileRvAdapter;
    UserViewModel userViewModel;
    NavController navController;
    private List<Datum> userList = new ArrayList<>();

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

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        initRecyclerView();
        subscribeObservers();
    }


    private void subscribeObservers() {
        userViewModel.getUsers().observe(this.getViewLifecycleOwner(), new Observer<Resource<List<Datum>>>() {
            @Override
            public void onChanged(Resource<List<Datum>> listResource) {
                profileRvAdapter.resetData();
                profileRvAdapter.swapData(listResource.data);
            }
        });
    }

    private void initRecyclerView() {
        profileRvAdapter = new ProfileRvAdapter(userList, this);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(HomeFragment.this.getContext());
        rv_user_list.setLayoutManager(mLayoutManager);
        rv_user_list.setItemAnimator(new DefaultItemAnimator());
        rv_user_list.setAdapter(profileRvAdapter);
    }

    @Override
    public void clickedItem(Bundle data) {
        navController = Navigation.findNavController(HomeFragment.this.getActivity(), R.id.nav_host_fragment);
        navController.navigate(R.id.action_homeFragment_to_profileDetailFragment, data);
    }
}