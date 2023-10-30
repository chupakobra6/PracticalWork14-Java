package com.example.practicalwork14;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

public class second extends Fragment {
    private int counter = 0;
    private TextView textView;
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        Button button = view.findViewById(R.id.button);
        textView = view.findViewById(R.id.textView);

        textView.setText(String.valueOf(counter));

        button.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(requireContext(), v);
            popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int itemId = item.getItemId();
                if (itemId == R.id.increase) {
                    counter++;
                } else if (itemId == R.id.decrease) {
                    if (counter > 0) {
                        counter--;
                    }
                } else if (itemId == R.id.ghoul) {
                    counter = 1000;
                    handler.postDelayed(() -> startGhoulAlgorithm(), 1000);
                }

                textView.setText(String.valueOf(counter));
                return true;
            });

            popupMenu.show();
        });

        return view;
    }

    private void startGhoulAlgorithm() {
        if (counter > 6) {
            counter -= 7;
            textView.setText(String.valueOf(counter));

            textView.setTextColor(Color.RED);

            handler.postDelayed(() -> {
                startGhoulAlgorithm();
            }, counter / 10);
        } else {
            handler.postDelayed(() -> {
                textView.setTextColor(Color.WHITE);
            }, 1000);
        }
    }
}
