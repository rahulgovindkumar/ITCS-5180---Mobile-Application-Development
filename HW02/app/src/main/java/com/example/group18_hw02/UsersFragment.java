
package com.example.group18_hw02;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;

import com.example.group18_hw02.databinding.FragmentUsersBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;

public class UsersFragment extends Fragment {

    FragmentUsersBinding binding;
    UsersListAdapter usersListAdapter;
    ArrayList<DataServices.User> usersList = DataServices.getAllUsers();
    iListener mListener;
    final String TAG = "Demo";
    String sortingAttribute;
    Boolean sortingCriteria;

    public UsersFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUsersBinding.inflate(inflater,container,false);
        usersListAdapter = new UsersListAdapter(getContext(), R.layout.user_listview, usersList);
        binding.listViewUserFragmentUsers.setAdapter(usersListAdapter);
        binding.buttonFilterFragmentUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openStateFilterFragmentFromUsersFragment();
            }
        });
        binding.buttonSortFragmentUsers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openSortFragmentFromUsersFragment();
            }
        });
        return binding.getRoot();
    }

    public void updateUserNameListSortingUsersFragment(boolean sortingOrder) {
        Collections.sort(usersList, new Comparator<DataServices.User>() {
            @Override
            public int compare(DataServices.User u1, DataServices.User u2) {
                return (sortingOrder ? 1 : 0) * u1.name.compareTo(u2.name);
            }
        });
        usersListAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void filterByStateUserListUsersFragment(String stateName) {
        usersList = DataServices.getAllUsers();
        if (stateName != "All States") {
            usersList = (ArrayList<DataServices.User>) usersList.stream().filter(u -> u.state == stateName).collect(Collectors.toList());
        } /*else  {
            usersList = DataServices.getAllUsers();
        }*/
        if (sortingCriteria != null){
            sortBySelectedCriteria(sortingAttribute, sortingCriteria);
        }
        usersListAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortBySelectedCriteria(String attribute, Boolean criteria) {
        sortingAttribute = attribute;
        sortingCriteria = criteria;
        int criteriaInInteger = criteria ? 1 : -1;
        if (attribute == "Age") {
            usersList.sort(new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return criteriaInInteger * (u1.age -  u2.age);
                }
            });
            usersListAdapter.notifyDataSetChanged();
        } else  if (attribute == "Name") {
            usersList.sort(new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return criteriaInInteger * u1.name.compareTo(u2.name);
                }
            });
            usersListAdapter.notifyDataSetChanged();
        } else  {
            usersList.sort(new Comparator<DataServices.User>() {
                @Override
                public int compare(DataServices.User u1, DataServices.User u2) {
                    return criteriaInInteger * u1.state.compareTo(u2.state);
                }
            });
            usersListAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof iListener) {
            mListener = (iListener) context;
        }
    }

    public interface iListener {
        void openStateFilterFragmentFromUsersFragment();
        void openSortFragmentFromUsersFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle(getString(R.string.UsersHeader));

    }
}