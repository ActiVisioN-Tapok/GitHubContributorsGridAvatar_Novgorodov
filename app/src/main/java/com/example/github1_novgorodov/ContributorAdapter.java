package com.example.github1_novgorodov;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ContributorAdapter extends RecyclerView.Adapter<ContributorAdapter.ViewHolder> {
    ImageView userAvatarView;
    private final static String PHOTO_URL = "https://avatars.githubusercontent.com/u";
    private List<User> mContributors;
    private Context mContext;

    ContributorAdapter(List<User> users) {this.mContributors = users;};

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        User user = mContributors.get(position);
        userAvatarView = userAvatarView.findViewById(R.id.avatarImg);
        holder.loginTxt.setText(user.getLogin());
        holder.contributeTxt.setText(user.getContributions().toString());
        Picasso.get()
                .load(user.getAvatarUrl())
                .resize(100, 100)
                .into(userAvatarView);
    }
    @Override
    public int getItemCount() {
        if (mContributors == null) {
            return 0;
        }
        return mContributors.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView loginTxt;
        TextView contributeTxt;

        ViewHolder(View itemView)
        {
            super(itemView);
            loginTxt = itemView.findViewById(R.id.nickname_txt);
            contributeTxt = itemView.findViewById(R.id.contribute_txt);
            userAvatarView = itemView.findViewById(R.id.avatarImg);
        }
    }
}
