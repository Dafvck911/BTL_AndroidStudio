package com.example.pickroom.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pickroom.R;
import com.example.pickroom.Room;
import com.example.pickroom.RoomAdapter;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {

    private RecyclerView rcv_room;
    private RoomAdapter roomAdapter;
    private ViewFlipper viewFlipper;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Anhxa(view);
        ActionViewFlipper();
        setUpRecyclerView();
    }

    private void Anhxa(View view) {
        rcv_room = view.findViewById(R.id.recycleview1);
        viewFlipper = view.findViewById(R.id.viewflipermh);
        navigationView = getActivity().findViewById(R.id.navigationview);
        drawerLayout = getActivity().findViewById(R.id.drawerlayout);
    }

    private void setUpRecyclerView() {
        roomAdapter = new RoomAdapter(getContext(), getListRoom());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        rcv_room.setLayoutManager(linearLayoutManager);
        rcv_room.setAdapter(roomAdapter);
    }

    private void ActionViewFlipper() {
        List<String> quangcao = new ArrayList<>();
        quangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        quangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        quangcao.add("https://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");

        for(int i = 0; i < quangcao.size(); i++) {
            ImageView imageView = new ImageView(getContext());
            Glide.with(this).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private List<Room> getListRoom() {
        List<Room> list = new ArrayList<>();
        list.add(new Room("Phường Hạ Đình", "4.200.000đ/tháng", "30m2", "4 người/phòng", R.drawable.phong1, "Nhà mới đẹp"));
        list.add(new Room("Phường Thanh Trì", "1.200.000đ/tháng", "25m2", "3 người/phòng", R.drawable.phong2, "Căn hộ mới"));
        list.add(new Room("Phường Khương Đình", "3.400.000đ/tháng", "28m2", "4 người/phòng", R.drawable.phong3, "Chung cư mini"));
        list.add(new Room("Phường Thanh Xuân Bắc", "2.200.000đ/tháng", "29m2", "3 người/phòng", R.drawable.phong4, "Phòng đẹp, thoáng mát"));
        list.add(new Room("Phường Xuân Đỉnh", "2.800.000đ/tháng", "31m2", "4 người/phòng", R.drawable.phong5, "Nhà mới đẹp"));
        list.add(new Room("Phường Hoàng Văn Thụ", "1.700.000đ/tháng", "24m2", "2 người/phòng", R.drawable.phong6, "Phòng nhỏ, full đồ"));
        list.add(new Room("Phường Nhân Chính", "5.200.000đ/tháng", "40m2", "5 người/phòng", R.drawable.phong7, "HomeStay giá rẻ"));
        list.add(new Room("Phường Tân Mai", "3.500.000đ/tháng", "33m2", "3 người/phòng", R.drawable.phong8, "Căn Studio"));

        return list;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);

        SearchManager searchManager = (SearchManager) requireActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                roomAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                roomAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
