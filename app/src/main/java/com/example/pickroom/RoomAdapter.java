package com.example.pickroom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder>{

    private Context context;
    private List<Room> listroom;

    public RoomAdapter(Context context, List<Room> list) {
        this.context = context;
        this.listroom = list;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = listroom.get(position);
        if(room == null){
            return;
        }
        holder.imageView.setImageResource(room.getHinh());
        holder.tvten.setText(room.getTenphong());
        holder.tvgia.setText(room.getGiathue());
        holder.tvdiachi.setText(room.getDiachi());
        holder.tvdientich.setText(room.getDientich());
        holder.tvsonguoi.setText(room.getSonguoi());

    }

    @Override
    public int getItemCount() {
        if(listroom != null){
            return listroom.size();
        }
        return 0;
    }

    public class RoomViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;
        private TextView tvten;
        private TextView tvgia;
        private TextView tvdiachi;
        private TextView tvdientich;
        private TextView tvsonguoi;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.phong1);
            tvten = itemView.findViewById(R.id.name);
            tvgia = itemView.findViewById(R.id.gia);
            tvdiachi = itemView.findViewById(R.id.diachi1);
            tvdientich = itemView.findViewById(R.id.dientich);
            tvsonguoi = itemView.findViewById(R.id.songuoi);
        }
    }
}
