package com.barrera.restapi.Adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.barrera.restapi.Models.Post;
import com.barrera.restapi.R;

import java.util.ArrayList;

public class GetAllAdapter extends RecyclerView.Adapter<GetAllAdapter.GetAllAdapterViewHolder> {

    private Activity activity;
    private ArrayList<Post> posts;

    public GetAllAdapter(Activity activity, ArrayList<Post> posts) {
        this.activity = activity;
        this.posts = posts;
    }

    @NonNull
    @Override
    public GetAllAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.cardview_list_post, parent, false);
        return new GetAllAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GetAllAdapterViewHolder holder, int position) {
        final Post post = posts.get(position);

        holder.txtTitle.setText(post.getTitle());
        holder.txtNumber.setText(String.valueOf(post.getId()));
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class GetAllAdapterViewHolder extends RecyclerView.ViewHolder {

        private TextView txtNumber, txtTitle;

        public GetAllAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNumber = itemView.findViewById(R.id.cVPostNumber);
            txtTitle = itemView.findViewById(R.id.cVPostTitle);
        }
    }
}
