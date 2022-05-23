package com.example.alist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;


import com.example.alist.activity.ComplaintDetails;
import com.example.alist.R;
import com.example.alist.databinding.ItemBinding;
import com.example.alist.model.complaint.ComplaintData;

import java.util.List;

public class ArchiveAdapter extends RecyclerView.Adapter<ArchiveAdapter.ViewHolder> {
    private List<ComplaintData> list;
    Context context;
    public final static String ARCHIVE_ID = "archive_id" ;

    public ArchiveAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ComplaintData> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ArchiveAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ArchiveAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ArchiveAdapter.ViewHolder holder, int position) {
        if (holder!=null) {
            holder.binding.title.setText(list.get(position).getName());
            holder.binding.date.setText(list.get(position).getDate());

            if (list.get(position).getReply_status().equals("0")){
                holder.binding.replyStatus.setText("تم الرد");
                holder.binding.container.setBackgroundResource(R.drawable.requestbtn);;
            }else if (list.get(position).getReply_status().equals("1")){
                holder.binding.replyStatus.setText("بانتظار الرد");
                holder.binding.container.setBackgroundResource(R.drawable.wating);;
            }else
            {
                holder.binding.replyStatus.setText("مغلق");
                holder.binding.container.setBackgroundResource(R.drawable.close);;
            }

            holder.binding.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ComplaintDetails.class);
                    intent.putExtra(ARCHIVE_ID, list.get(position).getId());
                    context.startActivity(intent);
                }
            });

        }


    }



    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        ItemBinding binding;
        public ViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }

}