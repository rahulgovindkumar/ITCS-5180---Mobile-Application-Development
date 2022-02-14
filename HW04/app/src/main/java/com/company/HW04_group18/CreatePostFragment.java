
package com.company.HW04_group18;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.HW04_group18.databinding.FragmentCreatepostBinding;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CreatePostFragment extends Fragment {

    FragmentCreatepostBinding binding;
    UserDetails user;
    iListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreatepostBinding.inflate(inflater, container, false);
        binding.fragmentCreatePostButtonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String postText = binding.fragmentCreatePostEditTextPostContent.getText().toString();
                if(postText.length()<1) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error!")
                            .setMessage("Please make sure that the post is not empty.")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                } else {
                    CrudOperations.createPost(user, postText, new Callback() {
                        @Override
                        public void onFailure(@NonNull Call call, @NonNull IOException e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                            mListener.gotoPosts();
                        }
                    });
                }
            }
        });

        binding.fragmentCreatePostButtonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.cancelNewPost();
            }
        });

        return binding.getRoot();
    }

    public void updateUserDetails(UserDetails user) {
        this.user = user;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof iListener){
            mListener = (iListener) context;
        }
    }

    public interface iListener {
        public void gotoPosts();
        public void cancelNewPost();
    }
}