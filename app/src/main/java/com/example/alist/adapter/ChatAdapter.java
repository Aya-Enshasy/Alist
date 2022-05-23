package com.example.alist.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.example.alist.R;
import com.example.alist.activity.ComplaintDetails;
import com.example.alist.databinding.ChatItemBinding;
import com.example.alist.model.Data;
import com.example.alist.model.complaint.ComplaintData;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
    private List<Data> list;
    Context context;

    public ChatAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Data> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ChatItemBinding binding = ChatItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ChatAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        if (holder!=null) {
            holder.binding.massage.setText(list.get(position).getMassage());
            if (list.get(position).getSender().equals("user")){
                holder.binding.massage.setBackgroundResource(R.drawable.send_massage);;
            }else
            {
                holder.binding.massage.setBackgroundResource(R.drawable.massege);

                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                lp.setMargins(340, 16, 0, 0);
                lp.width = 110;
                lp.height =110;
                holder.binding.image.setImageResource(R.drawable.ic_baseline_supervised_user_circle_24);
                holder.binding.image.setLayoutParams(lp);

            }
        }


    }



    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder  {
        ChatItemBinding binding;
        public ViewHolder(ChatItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }


    }

}
