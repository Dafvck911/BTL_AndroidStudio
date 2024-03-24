package com.example.pickroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> implements Filterable {

    private Context context;// biến môi trường
    private List<Room> listroom;
    private List<Room> listroomOld;

    public RoomAdapter(Context context, List<Room> list) {
        this.context = context;
        this.listroom = list;
        this.listroomOld = list;
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

        holder.cardViewItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGotoDetail(room);
            }
        });

    }

    private  void onClickGotoDetail(Room room){
        Intent i = new Intent(context, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_room", room);
        i.putExtras(bundle);
        context.startActivity(i);
    }

    public  void release(){//giải phóng biến môi trường
        context = null;
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
        private CardView cardViewItem;
        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.phong1);
            tvten = itemView.findViewById(R.id.name);
            tvgia = itemView.findViewById(R.id.gia);
            tvdiachi = itemView.findViewById(R.id.diachi1);
            tvdientich = itemView.findViewById(R.id.dientich);
            tvsonguoi = itemView.findViewById(R.id.songuoi);

            cardViewItem = itemView.findViewById(R.id.cardview);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strsearch = constraint.toString();
                if (strsearch.isEmpty()){
                    listroom = listroomOld;
                }else {
                    List<Room> list = new ArrayList<>();
                    for (Room room : listroomOld){
                        if (room.getDiachi().toLowerCase().contains(strsearch.toLowerCase())){
                            list.add(room);
                        }
                    }

                    listroom = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = listroom;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listroom = (List<Room>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}
