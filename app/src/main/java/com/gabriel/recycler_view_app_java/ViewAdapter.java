package com.gabriel.recycler_view_app_java;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

    private Context context;
    private OnContactListener mOnContactListener;

    public ViewAdapter(Context context, OnContactListener onContactListener) {
        this.context = context;
        this.mOnContactListener = onContactListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row, parent, false);
        return new ViewHolder(view, mOnContactListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.contactName.setText(ContactList.GetInstance().getContactsData().get(position)[0]);
        holder.contactNumber.setText(ContactList.GetInstance().getContactsData().get(position)[1]);
    }

    @Override
    public int getItemCount() {
        return ContactList.GetInstance().getContactsData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView contactName, contactNumber;
        OnContactListener onContactListener;

        public ViewHolder(@NonNull View itemView, OnContactListener onContactListener) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactName);
            contactNumber = itemView.findViewById(R.id.contactNumber);
            this.onContactListener = onContactListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onContactListener.OnContactClick(getAdapterPosition());
        }
    }

    public interface OnContactListener {
        void OnContactClick(int position);
    }
}
