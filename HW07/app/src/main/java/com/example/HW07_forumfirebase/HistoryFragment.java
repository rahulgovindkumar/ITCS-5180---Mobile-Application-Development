/*HW07
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW07_forumfirebase;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.HW07_forumfirebase.databinding.FragmentHistoryBinding;
import com.example.HW07_forumfirebase.databinding.FragmentLoginBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {

    FragmentHistoryBinding binding;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    LinearLayoutManager layoutManager;
    ListView listView;
    ArrayAdapter<POJOclasses.Route> adapter;
    ArrayList<POJOclasses.Route> previousJogs = new ArrayList<>();
    final static String intentKey = "Route";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentHistoryBinding.inflate(inflater, container, false);

        listView = binding.listviewJogHistory;
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, android.R.id.text1, previousJogs);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                ArrayList<ParcelableGeoPoint> pointsExtra = new ArrayList<ParcelableGeoPoint>();
                for(GeoPoint point: previousJogs.get(i).getPoints()) {
                    pointsExtra.add(new ParcelableGeoPoint(point));
                }
                intent.putExtra(HistoryFragment.intentKey, pointsExtra);
                startActivity(intent);
            }
        });

        String uid = mAuth.getCurrentUser().getUid();


        db.collection(uid).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                previousJogs.clear();
                for (QueryDocumentSnapshot doc: value) {
                    previousJogs.add(new POJOclasses.Route((ArrayList<GeoPoint>) doc.get("points"), doc.getId()));
                }
                adapter.notifyDataSetChanged();
            }
        });

        binding.buttonNewJog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.main_ContainerView, new LoginFragment())
                        .commit();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle(getString(R.string.JogHistoryRuns));

    }
}