package com.example.astroweather2.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.astroweather2.R;
import com.example.astroweather2.util.ConnectivityUtil;

public class InitFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_init, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView info = view.findViewById(R.id.info);
        Button moveToHome = view.findViewById(R.id.moveToHome);

        if (ConnectivityUtil.isConnected(getContext())){
            Navigation.findNavController(view).navigate(R.id.toHomeFragment);
        }else{
            info.setVisibility(View.VISIBLE);
            moveToHome.setVisibility(View.VISIBLE);
        }

        moveToHome.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.toHomeFragment);
        });
    }
}
