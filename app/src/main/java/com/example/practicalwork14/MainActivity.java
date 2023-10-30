package com.example.practicalwork14;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.jetbrains.annotations.NonNls;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setFragment(new first());
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.first_item) {
                setFragment(new first());
            } else if (itemId == R.id.second_item) {
                setFragment(new second());
            } else if (itemId == R.id.third_item) {
                setFragment(new third());
            }

            return true;
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.title) {
                mediaPlayer = MediaPlayer.create(this, R.raw.title);
            } else if (id == R.id.spooky) {
                mediaPlayer = MediaPlayer.create(this, R.raw.spooky);
            } else if (id == R.id.prelude) {
                mediaPlayer = MediaPlayer.create(this, R.raw.prelude);
            }

            mediaPlayer.setLooping(false);
            mediaPlayer.start();

            drawer.closeDrawer(GravityCompat.START);
            return true;
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idItem = item.getItemId();

        if (idItem == R.id.frag_first || idItem == R.id.settings) {
            Toast.makeText(this, "дайвинг", Toast.LENGTH_LONG).show();
            setFragment(new first());
        } else if (idItem == R.id.frag_second) {
            Toast.makeText(this, "точки", Toast.LENGTH_LONG).show();
            setFragment(new second());
        } else if (idItem == R.id.frag_third) {
            Toast.makeText(this, "стройка", Toast.LENGTH_LONG).show();
            setFragment(new third());
        }

        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_layout, fragment, null).commit();

        if (fragment instanceof first) {
            bottomNavigationView.getMenu().findItem(R.id.first_item).setChecked(true);
        } else if (fragment instanceof second) {
            bottomNavigationView.getMenu().findItem(R.id.second_item).setChecked(true);
        } else if (fragment instanceof third) {
            bottomNavigationView.getMenu().findItem(R.id.third_item).setChecked(true);
        }
    }
}