package com.prodadimhaski.lastwill.rvadapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prodadimhaski.lastwill.R;
import com.prodadimhaski.lastwill.Room.entities.Wills;

import java.util.List;

public class WillsAdapter extends RecyclerView.Adapter<WillsAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Wills> willsList;

    public WillsAdapter(Context context, List<Wills> willsList) {
        this.willsList = willsList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public WillsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.will_item, parent, false);
        return new WillsAdapter.ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return willsList == null ? 0 : willsList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Wills will = willsList.get(position);

        holder.willID.setText(will.getId());
        holder.willMessage.setText(will.getMessage());
        holder.willNumber.setText(will.getNumber());
        holder.willMail.setText(will.getMail());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView willID;
        TextView willMessage;
        TextView willNumber;
        TextView willMail;

        ViewHolder(View view) {
            super(view);
            willID = view.findViewById(R.id.willID);
            willMessage = view.findViewById(R.id.willMessage);
            willNumber = view.findViewById(R.id.willNumber);
            willMail = view.findViewById(R.id.willMail);
        }
    }

    public void setWillsList(List<Wills> willsList) {
        this.willsList = willsList;
    }
}
