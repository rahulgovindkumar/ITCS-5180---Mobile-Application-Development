/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.HW05_forumfirebase;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclass08_forumfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class RecyclerViewForumsAdapter extends RecyclerView.Adapter<RecyclerViewForumsAdapter.PostsViewHolder>{

    ForumsFragment forumsFragment;
    ArrayList<POJOclasses.Forum> forumsList;
    final String TAG = "Demo";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public RecyclerViewForumsAdapter(ArrayList<POJOclasses.Forum> postsList, ForumsFragment forumsFragment) {
        this.forumsList = postsList;
        this.forumsFragment = forumsFragment;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_list_item, parent, false);
        PostsViewHolder postsViewHolder = new PostsViewHolder(view);
        return postsViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        POJOclasses.Forum forum = forumsList.get(position);
        holder.title.setText(forum.title);
        holder.userName.setText(forum.userName);
        holder.content.setText(forum.content);
        holder.time.setText(forum.time);

        Log.d(TAG, "onBindViewHolder: " + mAuth.getCurrentUser().getUid() + "    " + forum.getUid() + "   " + mAuth.getCurrentUser().getUid().equals(forum.getUid()));

        if(mAuth.getCurrentUser().getUid().equals(forum.getUid())) {
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
                                    db.collection("forums")
                                            .document(forum.docId)
                                            .delete();
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
        } else {
            holder.deleteButton.setVisibility(View.INVISIBLE);
        }

        if (forum.getLikes().contains(mAuth.getCurrentUser().getUid())) {
            holder.likeButton.setImageResource(R.drawable.like_favorite);
            holder.likeButton.setTooltipText("L");
        } else {
            holder.likeButton.setTooltipText("NL");
        }

        holder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getTooltipText().equals("L")) {
                    holder.likeButton.setImageResource(R.drawable.like_not_favorite);
                    holder.likeButton.setTooltipText("NL");
                    forum.likes.remove(mAuth.getCurrentUser().getUid());
                    db.collection("forums").document(forum.docId)
                            .set(forum);
                } else {
                    holder.likeButton.setImageResource(R.drawable.like_favorite);
                    holder.likeButton.setTooltipText("L");
                    forum.likes.add(mAuth.getCurrentUser().getUid());
                    db.collection("forums").document(forum.docId)
                            .set(forum);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forumsFragment.openForumDetails(forum);
            }
        });
    }

    @Override
    public int getItemCount() {
        return forumsList.size();
    }

    public static class PostsViewHolder extends RecyclerView.ViewHolder {
        TextView title, userName, content, time;
        ImageButton deleteButton, likeButton;
        public PostsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.textView_ForumTitle);
            userName = itemView.findViewById(R.id.textView_ForumContentCreator);
            time = itemView.findViewById(R.id.textView_ForumContentDate);
            content = itemView.findViewById(R.id.textView_ForumContentDescription);
            deleteButton = itemView.findViewById(R.id.imageButton_Delete);
            likeButton = itemView.findViewById(R.id.imageButton_Like);
        }
    }
}

