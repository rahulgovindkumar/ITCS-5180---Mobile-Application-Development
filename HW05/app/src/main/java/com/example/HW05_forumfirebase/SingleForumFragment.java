/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.HW05_forumfirebase;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass08_forumfirebase.databinding.FragmentSingleForumBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SingleForumFragment extends Fragment {

    FragmentSingleForumBinding binding;
    POJOclasses.Forum forum;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    LinearLayoutManager layoutManager;
    RecyclerViewCommentAdapter adapter;

    public SingleForumFragment(POJOclasses.Forum forum) {
        // Required empty public constructor
        this.forum = forum;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSingleForumBinding.inflate(inflater, container, false);

        layoutManager = new LinearLayoutManager(getContext());
        adapter = new RecyclerViewCommentAdapter(forum,this);



        binding.textViewFourmTitleSinglrForumFragment.setText(forum.title);
        binding.textViewForumContentCreatorSingleForumFragment.setText(forum.userName);
        binding.textViewForumContentDescriptionSingleForumFragment.setText(forum.content);
        binding.textViewCommentsCountSingleFourmFragment.setText(forum.getComments().size() + " Comments");

        binding.buttonPostSingleForumFragment.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SimpleDateFormat")
            @Override
            public void onClick(View view) {
                String commentText = binding.editTextNewCommentSingleForumFragment.getText().toString();
                if (commentText.length() < 1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error!")
                            .setMessage("Comments cannot be empty!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                } else {
                    Map<String, String> newComment = new HashMap<>();
                    newComment.put("userName", mAuth.getCurrentUser().getDisplayName());
                    newComment.put("content", commentText);
                    newComment.put("time", new SimpleDateFormat("MM/dd/yyyy hh:mm aa").format(new Date()));
                    newComment.put("Uid", mAuth.getUid());
                    forum.getComments().add(newComment);
                    db.collection("forums").document(forum.docId)
                            .set(forum);
                    binding.editTextNewCommentSingleForumFragment.setText("");
                    binding.textViewCommentsCountSingleFourmFragment.setText(forum.getComments().size() + " Comments");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        binding.recyclerViewCommentList.setHasFixedSize(true);
        binding.recyclerViewCommentList.addItemDecoration(new DividerItemDecoration(binding.recyclerViewCommentList.getContext(), DividerItemDecoration.VERTICAL));

        binding.recyclerViewCommentList.setLayoutManager(layoutManager);
        binding.recyclerViewCommentList.setAdapter(adapter);



        return binding.getRoot();
    }
}