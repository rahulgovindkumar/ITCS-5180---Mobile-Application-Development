/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */
package com.example.HW05_forumfirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.inclass08_forumfirebase.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;

public class RecyclerViewCommentAdapter extends RecyclerView.Adapter<RecyclerViewCommentAdapter.SingleForumViewHolder> {

    POJOclasses.Forum forum;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    SingleForumFragment parentView;

    public RecyclerViewCommentAdapter(POJOclasses.Forum forum, SingleForumFragment parentView) {
        this.forum = forum;
        this.parentView = parentView;
    }

    @NonNull
    @Override
    public SingleForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forum_comment_item, parent, false);
        SingleForumViewHolder viewHolder = new SingleForumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SingleForumViewHolder holder, int position) {
        Map<String, String> comment = forum.getComments().get(position);
        int commentPosition = position;

        holder.creatorUsername.setText(comment.get("userName"));
        holder.timeStamp.setText(comment.get("time"));
        holder.commentText.setText(comment.get("content"));

        if (comment.get("Uid").equals(mAuth.getUid())) {
            RecyclerViewCommentAdapter r = this;

            holder.deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    forum.getComments().remove(commentPosition);
                    db.collection("forums").document(forum.docId)
                            .set(forum);

                    r.notifyDataSetChanged();
                    parentView.binding.textViewCommentsCountSingleFourmFragment.setText(forum.getComments().size() + " Comments");




                }
            });
        } else {
            holder.deleteButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return forum.getComments().size();
    }

    public class SingleForumViewHolder extends RecyclerView.ViewHolder {
        TextView creatorUsername, commentText, timeStamp;
        ImageButton deleteButton;
        public SingleForumViewHolder(@NonNull View itemView) {
            super(itemView);
            creatorUsername = itemView.findViewById(R.id.textView_SingleComment_ForumCommentCreator);
            commentText = itemView.findViewById(R.id.textView_SingleComment_Comment_Fourm);
            timeStamp = itemView.findViewById(R.id.textView_SingleComment_CommentDate);
            deleteButton = itemView.findViewById(R.id.imageButton_SingleComment_DeleteComment);

        }
    }
}
