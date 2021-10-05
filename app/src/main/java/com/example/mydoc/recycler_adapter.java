package com.example.mydoc;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recycler_adapter extends RecyclerView.Adapter<recycler_viewHolder> {

    private ArrayList<Docs> docs;
    private Idoc_clicked_callback iListener;

    public recycler_adapter(Idoc_clicked_callback listener){

        docs = new ArrayList<>();
        iListener = listener;

    }

    @NonNull
    @Override
    public recycler_viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.doc_layout, parent, false);
        recycler_viewHolder viewHolder = new recycler_viewHolder(view);

        viewHolder.doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iListener.onDocClick(docs.get(viewHolder.getAdapterPosition()));
            }
        });

        viewHolder.delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iListener.onDeleteButtonClick(docs.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull recycler_viewHolder holder, int position) {

        holder.doc.setText((docs.get(position).getDoc()));

    }

    @Override
    public int getItemCount() {
        return docs.size();
    }

    public void onUpdate(List<Docs> d){

        docs = (ArrayList<Docs>) d;
        notifyDataSetChanged();

    }

}

class recycler_viewHolder extends RecyclerView.ViewHolder {

    public recycler_viewHolder(@NonNull View itemView) {
        super(itemView);
    }

    TextView doc = itemView.findViewById(R.id.doc);
    ImageButton delete_button = itemView.findViewById(R.id.deleteButton);

}

interface Idoc_clicked_callback{

    void onDocClick(Docs docs);
    void onDeleteButtonClick(Docs docs);

}
