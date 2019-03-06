package com.DC.chatapp;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.support.v4.content.ContextCompat.startActivity;
import static java.lang.reflect.Array.get;

class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private DatabaseReference mUsersDatabase;
    private LayoutInflater inflater;
    private List<Users> data;
    private Context mContext;

    public UserAdapter(Context context, List<Users> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        View view = inflater.inflate(R.layout.users_single_layout, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        final MyHolder myHolder = (MyHolder) holder;
        final Users current = data.get(position);

        mUsersDatabase = FirebaseDatabase.getInstance().getReference("Users");

        final String key = current.getName().split("-")[1];
        String name = current.getName().split("-")[0];

        myHolder.name.setText(name);
        myHolder.status.setText(current.getStatus());
        Picasso.get().load(current.getImage()).placeholder(R.drawable.download).into(myHolder.img);

//        final String user_id = getRef(position).getKey();

        View.OnClickListener profileView = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_id = key;
                Intent profile_intent = new Intent(mContext,ProfileActivity.class);
                profile_intent.putExtra("user_id",user_id);
                mContext.startActivity(profile_intent);

            }
        };

        myHolder.itemView.setOnClickListener(profileView);
    }

    
//    private Object getRef(int position) {
//        mUsersDatabase = FirebaseDatabase.getInstance().getReference("Users");
//        return false;
//    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyHolder extends RecyclerView.ViewHolder {

       View mView;


        TextView name, status;
        CircleImageView img;

        MyHolder(final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.user_single_name);
            status = itemView.findViewById(R.id.user_single_status);
            img = itemView.findViewById(R.id.user_single_image);
        }

    }
}
