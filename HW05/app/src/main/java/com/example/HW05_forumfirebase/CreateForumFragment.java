/*HW 05
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */

package com.example.HW05_forumfirebase;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.inclass08_forumfirebase.R;
import com.example.inclass08_forumfirebase.databinding.FragmentCreateForumBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

public class CreateForumFragment extends Fragment {

    FragmentCreateForumBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CreateForumFragment() {
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
        binding = FragmentCreateForumBinding.inflate(inflater,container,false);

        binding.buttonCancelCreateForumFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().popBackStack();
            }
        });

        binding.buttonSubmitCreateForumFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextForumTitleCreateForumFragment.getText().toString();
                String content = binding.editTextForumDescriptionCreateForumFragment.getText().toString();

                if (title.isEmpty() || content.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setTitle("Error!")
                            .setMessage("Please make sure that none of the fields are empty!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                    builder.show();
                } else {
                    POJOclasses.Forum forum = new POJOclasses.Forum(new String[]{
                            mAuth.getCurrentUser().getDisplayName(),
                            title,
                            content,
                            new SimpleDateFormat("MM/dd/yyyy hh:mmaa").format(new Date()),
                            mAuth.getUid()
                    }, new List<String>() {
                        @Override
                        public int size() {
                            return 0;
                        }

                        @Override
                        public boolean isEmpty() {
                            return false;
                        }

                        @Override
                        public boolean contains(@Nullable Object o) {
                            return false;
                        }

                        @NonNull
                        @Override
                        public Iterator<String> iterator() {
                            return null;
                        }

                        @NonNull
                        @Override
                        public Object[] toArray() {
                            return new Object[0];
                        }

                        @NonNull
                        @Override
                        public <T> T[] toArray(@NonNull T[] ts) {
                            return null;
                        }

                        @Override
                        public boolean add(String s) {
                            return false;
                        }

                        @Override
                        public boolean remove(@Nullable Object o) {
                            return false;
                        }

                        @Override
                        public boolean containsAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public boolean addAll(@NonNull Collection<? extends String> collection) {
                            return false;
                        }

                        @Override
                        public boolean addAll(int i, @NonNull Collection<? extends String> collection) {
                            return false;
                        }

                        @Override
                        public boolean removeAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public boolean retainAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public void clear() {

                        }

                        @Override
                        public String get(int i) {
                            return null;
                        }

                        @Override
                        public String set(int i, String s) {
                            return null;
                        }

                        @Override
                        public void add(int i, String s) {

                        }

                        @Override
                        public String remove(int i) {
                            return null;
                        }

                        @Override
                        public int indexOf(@Nullable Object o) {
                            return 0;
                        }

                        @Override
                        public int lastIndexOf(@Nullable Object o) {
                            return 0;
                        }

                        @NonNull
                        @Override
                        public ListIterator<String> listIterator() {
                            return null;
                        }

                        @NonNull
                        @Override
                        public ListIterator<String> listIterator(int i) {
                            return null;
                        }

                        @NonNull
                        @Override
                        public List<String> subList(int i, int i1) {
                            return null;
                        }
                    },
                            new List<Map<String, String>>() {
                        @Override
                        public int size() {
                            return 0;
                        }

                        @Override
                        public boolean isEmpty() {
                            return false;
                        }

                        @Override
                        public boolean contains(@Nullable Object o) {
                            return false;
                        }

                        @NonNull
                        @Override
                        public Iterator<Map<String, String>> iterator() {
                            return null;
                        }

                        @NonNull
                        @Override
                        public Object[] toArray() {
                            return new Object[0];
                        }

                        @NonNull
                        @Override
                        public <T> T[] toArray(@NonNull T[] ts) {
                            return null;
                        }

                        @Override
                        public boolean add(Map<String, String> stringStringMap) {
                            return false;
                        }

                        @Override
                        public boolean remove(@Nullable Object o) {
                            return false;
                        }

                        @Override
                        public boolean containsAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public boolean addAll(@NonNull Collection<? extends Map<String, String>> collection) {
                            return false;
                        }

                        @Override
                        public boolean addAll(int i, @NonNull Collection<? extends Map<String, String>> collection) {
                            return false;
                        }

                        @Override
                        public boolean removeAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public boolean retainAll(@NonNull Collection<?> collection) {
                            return false;
                        }

                        @Override
                        public void clear() {

                        }

                        @Override
                        public Map<String, String> get(int i) {
                            return null;
                        }

                        @Override
                        public Map<String, String> set(int i, Map<String, String> stringStringMap) {
                            return null;
                        }

                        @Override
                        public void add(int i, Map<String, String> stringStringMap) {

                        }

                        @Override
                        public Map<String, String> remove(int i) {
                            return null;
                        }

                        @Override
                        public int indexOf(@Nullable Object o) {
                            return 0;
                        }

                        @Override
                        public int lastIndexOf(@Nullable Object o) {
                            return 0;
                        }

                        @NonNull
                        @Override
                        public ListIterator<Map<String, String>> listIterator() {
                            return null;
                        }

                        @NonNull
                        @Override
                        public ListIterator<Map<String, String>> listIterator(int i) {
                            return null;
                        }

                        @NonNull
                        @Override
                        public List<Map<String, String>> subList(int i, int i1) {
                            return null;
                        }
                    });
                    db.collection("forums").add(forum);
                    getFragmentManager().popBackStack();
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getString(R.string.NewForum));

    }
}