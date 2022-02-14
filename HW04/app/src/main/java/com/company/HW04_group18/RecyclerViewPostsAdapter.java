
package com.company.HW04_group18;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.company.HW04_group18.R;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecyclerViewPostsAdapter extends RecyclerView.Adapter<RecyclerViewPostsAdapter.PostsViewHolder>{

    ArrayList<POJOclasses.Post> postsList;
    PostsFragment.iListener mListener;
    UserDetails user;
    PostsFragment parentView;
    final String TAG = "Demo";

    public RecyclerViewPostsAdapter(ArrayList<POJOclasses.Post> postsList, PostsFragment.iListener mListener, UserDetails user, PostsFragment parentView) {
        this.postsList = postsList;
        this.mListener = mListener;
        this.user = user;
        this.parentView = parentView;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_layout, parent, false);
        PostsViewHolder postsViewHolder = new PostsViewHolder(view);
        return postsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        POJOclasses.Post post = postsList.get(position);
        holder.postText.setText(post.post_text);
        holder.userName.setText(post.created_by_name);
        holder.dateTime.setText(post.created_at);
        if (!user.user_id.trim().equals(post.created_by_uid.trim())) {
//            Log.d(TAG, "onBindViewHolder: " + user.user_fullname + "  " + post.created_by_name + "  " + post.created_by_uid);
            holder.deleteButton.setVisibility(View.INVISIBLE);
            holder.deleteButton.setMaxWidth(0);
            holder.deleteButton.setMaxHeight(0);
        } else {
            PostsFragment localParentView = this.parentView;
            holder.deleteButton.setVisibility(View.VISIBLE);
            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle("Confirm!")
                            .setMessage("Are you sure you want to delete the post?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    CrudOperations.deletePost(post.post_id, user, new Callback() {
                                        @Override
                                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                                            e.printStackTrace();
                                        }

                                        @Override
                                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                                            localParentView.getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    refreshPostsUsingParent();
                                                }
                                            });
                                        }
                                    });
                                }
                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return postsList.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView postText, userName, dateTime;
        ImageButton deleteButton;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            postText = itemView.findViewById(R.id.ppostsLayout_textView_PostText);
            userName = itemView.findViewById(R.id.ppostsLayout_textView_UserName);
            dateTime = itemView.findViewById(R.id.ppostsLayout_textView_DateTime);
            deleteButton = itemView.findViewById(R.id.ppostsLayout_imageButton_Delete);
        }
    }

    public void refreshPostsUsingParent() {
        this.parentView.refreshPosts();
    }
}
