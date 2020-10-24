package com.quick.buku.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.quick.buku.R;
import com.quick.buku.callBacks.OnItemClickedListener;
import com.quick.buku.models.Datum;

import java.util.List;

public class ProfileRvAdapter extends RecyclerView.Adapter<ProfileRvAdapter.MyViewHolder> {

    ColorGenerator generator = ColorGenerator.MATERIAL; // or use DEFAULT
    OnItemClickedListener mCallback;
    private List<Datum> userList;

    public ProfileRvAdapter(List<Datum> userList, OnItemClickedListener mCallback) {
        this.userList = userList;
        this.mCallback = mCallback;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_single_row, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Datum userData = userList.get(position);

        String name = "N/A";
        try {
            name = String.valueOf(String.valueOf(userData.getFirstName().charAt(0)) + userData.getLastName().charAt(0)).toUpperCase();
        } catch (Exception ignored) {
        }

        holder.tv_name.setText(String.format("%s %s", userData.getFirstName(), userData.getLastName()));
        holder.tv_email.setText(userData.getEmail());

        int color = generator.getRandomColor();
        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4) /* thickness in px */
                .endConfig()
                .buildRoundRect(name, color, 10);

        Glide.with(holder.itemView.getContext())
                .load(userData.getAvatar())
                .apply(RequestOptions.placeholderOf(drawable).error(drawable))
                .into(holder.thumbnail);

        holder.card_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if (userList == null) {
            return 0;
        }
        return userList.size();
    }

    public void resetData() {
        userList.clear();
        notifyDataSetChanged();
    }

    public void swapData(List<Datum> items) {
        if (items != null) {
            userList.addAll(items);
            notifyDataSetChanged();

        } else {
            userList = null;
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_email, tv_id, tv_name;
        ImageView thumbnail;
        CardView card_view;

        public MyViewHolder(View view) {
            super(view);
            tv_email = view.findViewById(R.id.tv_email);
            tv_id = view.findViewById(R.id.tv_id);
            tv_name = view.findViewById(R.id.tv_name);
            thumbnail = view.findViewById(R.id.thumbnail);
            card_view = view.findViewById(R.id.card_view);
        }
    }
}