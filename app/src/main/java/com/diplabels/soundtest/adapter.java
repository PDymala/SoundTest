package com.diplabels.soundtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adapter  extends RecyclerView.Adapter <adapter.viewHolder>{
    ArrayList<ItemView> list;
    private OnItemClickListener mListener;
    @NonNull
    @Override
    public adapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sampleitem, parent, false);
        viewHolder vh = new viewHolder(v, mListener);
        return vh;
    }

       public adapter (ArrayList<ItemView> itemList){
        list = itemList;
       }

    @Override
    public void onBindViewHolder(@NonNull adapter.viewHolder holder, int position) {
        ItemView currentItem = list.get(position);
        holder.imageView.setImageResource(currentItem.getImage());

        holder.textViewPhase.setText(Double.toString(currentItem.getPhase()) );
        holder.textViewFreq.setText(Integer.toString(currentItem.getFreq()));
        holder.textViewAmp.setText(Double.toString( currentItem.getAmp()));



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void  setOnItemClickListerner(OnItemClickListener listener){
        mListener = listener;
    }
    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView textViewPhase;
        TextView       textViewFreq;
        TextView  textViewAmp;
        ImageView imageView;

        public viewHolder (View itemView, OnItemClickListener listener){
            super(itemView);
             textViewPhase = itemView.findViewById(R.id.textViewPhase);
                   textViewFreq= itemView.findViewById(R.id.textViewFreq);
              textViewAmp= itemView.findViewById(R.id.textViewAmp);
             imageView =  itemView.findViewById(R.id.imageView);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                 }
             });
        }
    }

}
