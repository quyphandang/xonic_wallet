package com.example.checkmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_fragment1, R.id.nav_fragment2, R.id.nav_fragment3, R.id.nav_fragment4)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.fragment_container);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNav, navController);


        Bundle bundle = new Bundle();
        bundle.putString("So1", "Cộng Hoà Xã Hội Chủ Nghĩa Việt Nam!");
        bundle.putString("So2", "Thông tin thông báo số 2");

        Four_Fragment four_fragment = new Four_Fragment();
        //five_fragment.getArguments(bundle);
        four_fragment.setArguments(bundle);
        FragmentManager fragmentMananger = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentMananger.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, new Four_Fragment());
        fragmentTransaction.commit();

    }
}
