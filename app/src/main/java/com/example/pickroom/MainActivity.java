package com.example.pickroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.example.pickroom.fragment.Canhan_Fragment;
import com.example.pickroom.fragment.Home_Fragment;
import com.example.pickroom.fragment.Notice_Fragment;
import com.example.pickroom.fragment.Support_Fragment;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_NOTICE = 1;
    private static final int FRAGMENT_SUPPORT = 2;
    private static final int FRAGMENT_CANHAN = 3;

    private int mCurrentFragment = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    private RecyclerView rcv_room;
    private  RoomAdapter roomAdapter;
    TextView textView;

    Toolbar toolbar;
    ViewFlipper viewFlipper;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_draw_open, R.string.navi_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new Home_Fragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);

//        firebaseUser = firebaseAuth.getCurrentUser();
//        View headerView = navigationView.getHeaderView(0);
//        TextView userEmail = headerView.findViewById(R.id.email);
//        userEmail.setText(firebaseUser.getEmail());

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home){
            if(mCurrentFragment != FRAGMENT_HOME){
                replaceFragment(new Home_Fragment());
                mCurrentFragment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_notic){
            if(mCurrentFragment != FRAGMENT_NOTICE){
                replaceFragment(new Notice_Fragment());
                mCurrentFragment = FRAGMENT_NOTICE;
            }

        }else if(id == R.id.nav_support){
            if(mCurrentFragment != FRAGMENT_SUPPORT){
                replaceFragment(new Support_Fragment());
                mCurrentFragment = FRAGMENT_SUPPORT;
            }

        }else if(id == R.id.nav_taikhoan){
            if(mCurrentFragment != FRAGMENT_CANHAN){
                replaceFragment(new Canhan_Fragment());
                mCurrentFragment = FRAGMENT_CANHAN;
            }

        }else if(id == R.id.nav_dangxuat){
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Đăng xuất") // tiêu đề của hộp thoại
                    .setMessage("Bạn có chắc muốn đăng xuất?") // nội dung thông báo
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            FirebaseAuth.getInstance().signOut();
                            Intent i = new Intent(getApplicationContext(), Dangnhap_Activity.class);
                            startActivity(i);
                            finish();
                        }
                    })
                    .setNegativeButton("Không", null) // không cần thực hiện hành động gì khi chọn "Không"
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}