package com.vango.testownik;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<MainModel> mainModels;
    Context context;
    private OnNoteListener mOnNoteListener;
    public MainAdapter(Context context,ArrayList<MainModel> mainModels,OnNoteListener mOnNoteListener){
        this.context=context;
        this.mainModels=mainModels;
        this.mOnNoteListener=mOnNoteListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_item,viewGroup,false);
        return new ViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
    viewHolder.imageView.setImageResource(mainModels.get(i).getLogo());
    viewHolder.textView.setText(mainModels.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return mainModels.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imageView;
        TextView textView;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener){
            super(itemView);
            this.onNoteListener=onNoteListener;
            imageView=itemView.findViewById(R.id.recycler_image);
            textView=itemView.findViewById(R.id.recycler_textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        onNoteListener.onNoteClick(getAdapterPosition());
        }
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}
