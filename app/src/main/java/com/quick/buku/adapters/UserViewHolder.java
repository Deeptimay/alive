package com.quick.buku.adapters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quick.buku.callBacks.OnItemClickedListener;
import com.quick.buku.databinding.UserSingleRowBinding;
import com.quick.buku.models.Datum;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private final UserSingleRowBinding binding;

    public UserViewHolder(@NonNull UserSingleRowBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public static UserViewHolder create(ViewGroup parent) {
        // Inflate
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        // Create the binding
        UserSingleRowBinding binding = UserSingleRowBinding.inflate(layoutInflater, parent, false);
        return new UserViewHolder(binding);
    }

    public void bindTo(final Datum user, OnItemClickedListener mCallback) {
        binding.setUser(user);
        // movie click event
        binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("Datum", user);
                mCallback.clickedItem(bundle);
            }
        });

        binding.executePendingBindings();
    }
}
