package com.quick.buku.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.quick.buku.R;
import com.quick.buku.models.Datum;

public class ProfileDetailFragment extends Fragment {

    TextView tv_email, tv_id, tv_name;
    ImageView iv_display_picture;
    Datum userData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        userData = getArguments().getParcelable("Datum");
        initView(view);
    }

    private void initView(View view) {
        tv_email = view.findViewById(R.id.tv_email);
        tv_id = view.findViewById(R.id.tv_id);
        tv_name = view.findViewById(R.id.tv_name);
        iv_display_picture = view.findViewById(R.id.iv_display_picture);

        tv_name.setText(String.format("%s %s", userData.getFirstName(), userData.getLastName()));
        tv_email.setText(userData.getEmail());
        tv_id.setText(String.valueOf(userData.getId()));

        String name = "";
        try {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(userData.getFirstName().charAt(0)).append(userData.getLastName().charAt(0));
            name = String.valueOf(stringBuilder).toUpperCase();
        } catch (Exception e) {
        }

        ColorGenerator generator = ColorGenerator.MATERIAL;
        int color = generator.getRandomColor();

        TextDrawable drawable = TextDrawable.builder()
                .beginConfig()
                .withBorder(4) /* thickness in px */
                .endConfig()
                .buildRoundRect(name, color, 10);

        Glide.with(ProfileDetailFragment.this)
                .load(userData.getAvatar())
                .apply(RequestOptions.placeholderOf(drawable).error(drawable))
                .into(iv_display_picture);
    }
}
