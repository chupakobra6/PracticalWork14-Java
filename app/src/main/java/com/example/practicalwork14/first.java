package com.example.practicalwork14;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class first extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        registerForContextMenu(view);
        return view;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        TextView textView = requireView().findViewById(R.id.textView);
        ImageView imageView = requireView().findViewById(R.id.imageView);

        if (itemId == R.id.change_text && textView.getText().toString().equals("Мелвин")) {
            textView.setText("Повелитель");
            imageView.setImageResource(R.drawable.overlord);
        } else {
            textView.setText("Мелвин");
            imageView.setImageResource(R.drawable.melvin);
        }

        return super.onContextItemSelected(item);
    }
}