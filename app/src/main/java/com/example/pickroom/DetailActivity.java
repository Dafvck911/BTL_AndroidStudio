package com.example.pickroom;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Room room = (Room) bundle.get("object_room");
        TextView tvAppname = findViewById(R.id.txtAppname);
        tvAppname.setText(room.getTenphong());
    }
}