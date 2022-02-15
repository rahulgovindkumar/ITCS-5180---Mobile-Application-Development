/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW05_forumfirebase;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass08_forumfirebase.R;
import com.example.inclass08_forumfirebase.databinding.FragmentForumsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ForumsFragment extends Fragment {

    FragmentForumsBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    LinearLayoutManager layoutManager;
    RecyclerViewForumsAdapter adapter;
    ArrayList<POJOclasses.Forum> forumsList = new ArrayList<>();


    public ForumsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentForumsBinding.inflate(inflater,container,false);

        adapter = new RecyclerViewForumsAdapter(forumsList, this);
        layoutManager = new LinearLayoutManager(getContext());

        binding.recyclerViewForumsList.setHasFixedSize(true);
        binding.recyclerViewForumsList.addItemDecoration(new DividerItemDecoration(binding.recyclerViewForumsList.getContext(), DividerItemDecoration.VERTICAL));
        binding.recyclerViewForumsList.setAdapter(adapter);
        binding.recyclerViewForumsList.setLayoutManager(layoutManager);

        db.collection("forums")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        forumsList.clear();
                        for (QueryDocumentSnapshot doc: value) {
                            forumsList.add(new POJOclasses.Forum(
                                    new String[] {doc.getString("userName"),
                                            doc.getString("title"),
                                            doc.getString("content"),
                                            doc.getString("time"),
                                            doc.getString("uid"),
                                            doc.getId()}
                            , (List<String>) doc.get("likes"), (List<Map<String, String>>) doc.get("comments")));
                        }
                        adapter.forumsList = forumsList;
                        adapter.notifyDataSetChanged();
                    }
                });

        binding.buttonLogoutPostListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main_ContainerView, new LoginFragment())
                        .commit();
            }
        });

        binding.buttonCreatePostPostListFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main_ContainerView, new CreateForumFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.Forums));
    }

    public void openForumDetails(POJOclasses.Forum forum) {
        getParentFragmentManager().beginTransaction()
                .replace(R.id.main_ContainerView, new SingleForumFragment(forum))
                .addToBackStack(null)
                .commit();
    }

}