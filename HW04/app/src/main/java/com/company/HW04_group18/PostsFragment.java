
package com.company.HW04_group18;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.company.HW04_group18.databinding.FragmentPostsBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class PostsFragment extends Fragment {

    Gson gson = new Gson();
    FragmentPostsBinding binding;
    UserDetails user;
    iListener mListener;
    LinearLayoutManager layoutManagerPosts, layoutManagerPageCount;
    int pageNumber = 1;
    RecyclerViewPostsAdapter adapterPosts;
    RecyclerViewPageCountAdapter adapterPageCount;
    ArrayList<POJOclasses.Post> postsList = new ArrayList<>();
    ArrayList<Integer> pageNumsList = new ArrayList<>();
    final String TAG = "DEMO";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostsBinding.inflate(inflater, container, false);
        layoutManagerPosts = new LinearLayoutManager(getContext());
        adapterPosts = new RecyclerViewPostsAdapter(postsList, mListener, user, this);

        binding.fragmentPostsRecyclerViewPosts.setLayoutManager(layoutManagerPosts);
        binding.fragmentPostsRecyclerViewPosts.addItemDecoration(new DividerItemDecoration(binding.fragmentPostsRecyclerViewPosts.getContext(), DividerItemDecoration.VERTICAL));

        binding.fragmentPostsRecyclerViewPosts.setAdapter(adapterPosts);
        binding.fragmentPostsRecyclerViewPosts.setHasFixedSize(true);

        binding.fragmentPostsTextViewPageCount.setText("Showing Page" + pageNumber + " out of ");

        layoutManagerPageCount = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        adapterPageCount = new RecyclerViewPageCountAdapter(pageNumsList, this);

        binding.fragmentPostsRecyclerViewPageNumbers.setLayoutManager(layoutManagerPageCount);
        binding.fragmentPostsRecyclerViewPageNumbers.setAdapter(adapterPageCount);
        binding.fragmentPostsRecyclerViewPageNumbers.setHasFixedSize(true);

        binding.fragmentPostsButtonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.goToCreatePost();
            }
        });
        binding.fragmentPostsButtonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.userLogout();
            }
        });

        return binding.getRoot();
    }

    public void updateUserDetails(UserDetails user) {
        this.user = user;
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.fragmentPostsTextViewWelcomeText.setText("Welcome " + user.user_fullname);
        refreshPosts();
    }

    public void refreshPosts() {
        CrudOperations.getPosts(user, pageNumber, new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                POJOclasses.postsResponse postsResponse = gson.fromJson(response.body().charStream(), POJOclasses.postsResponse.class);
                postsList = postsResponse.posts;
                adapterPosts.postsList = postsList;
                ArrayList<Integer> updatedNumsList = new ArrayList<>();
                for (int i = 1; i <= postsList.size() ; i++) {
                    updatedNumsList.add(i);
                }
                pageNumsList = updatedNumsList;
                adapterPageCount.pageNumsList = pageNumsList;
                Log.d(TAG, "onResponse: " + postsResponse.posts.size());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterPosts.notifyDataSetChanged();
                        adapterPageCount.notifyDataSetChanged();
                        binding.fragmentPostsTextViewPageCount.setText("Showing Page " + pageNumber + " out of " + postsResponse.pageSize);
                    }
                });
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof iListener){
            mListener = (iListener) context;
        }
    }

    public interface iListener {
        public void goToCreatePost();
        public void userLogout();
    }

    public void updatePageNumberAndRefesh(int pageNumber) {
        this.pageNumber = pageNumber;
        refreshPosts();
    }
}