/*HW07
        Grouping3 - 18
        Name: Rahul Govindkumar
        Name: Amruth Nag
        */


package com.example.HW07_forumfirebase;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.HW07_forumfirebase.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    ArrayList<ParcelableGeoPoint> trip;
    ArrayList<GeoPoint> newRun = new ArrayList<>();
    double newRunLatMin, newRunLatMax, newRunLonMin, newRunLonMax;
    LatLng prev;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    final String TAG = "Demo";

    int LOCATION_REQUEST_CODE = 10001;

    FusedLocationProviderClient fusedLocationProviderClient;
    LocationRequest locationRequest;

    LocationCallback locationCallback = new LocationCallback() {
        @Override
        public void onLocationResult(@NonNull LocationResult locationResult) {
            if(locationResult==null || trip != null){
                return;
            }
            Log.d(TAG, "onLocationResult: Before");
            for(Location location: locationResult.getLocations()){
                if(newRun.size() == 0) {
                    newRunLatMin = newRunLatMax = location.getLatitude();
                    newRunLonMin = newRunLonMax = location.getLongitude();
                    prev = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(prev).title("Start"));
                } else {
                    newRunLatMin = location.getLatitude() < newRunLatMin ? location.getLatitude() : newRunLatMin;
                    newRunLonMin = location.getLongitude() < newRunLonMin ? location.getLongitude() : newRunLonMin;
                    newRunLonMax = location.getLongitude() > newRunLonMax ? location.getLongitude() : newRunLonMax;
                    newRunLatMax = location.getLatitude() > newRunLatMax ? location.getLatitude() : newRunLatMax;
                    LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                    CameraUpdate update = CameraUpdateFactory.newLatLngZoom(current, 16);
                    mMap.animateCamera(update);
                    mMap.addPolyline((new PolylineOptions())
                            .add(prev, current).width(6).color(Color.BLUE)
                            .visible(true));
                    prev=current;
                }
                newRun.add(new GeoPoint(location.getLatitude(), location.getLongitude()));
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(getIntent() != null && getIntent().getExtras() != null & getIntent().hasExtra(HistoryFragment.intentKey)) {
            trip = getIntent().getParcelableArrayListExtra(HistoryFragment.intentKey);
            binding.buttonEndJog.setVisibility(View.INVISIBLE);
        } else {
            trip = null;
            newRun = new ArrayList<>();
            binding.buttonEndJog.setVisibility(View.VISIBLE);
            binding.buttonEndJog.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String uid = mAuth.getCurrentUser().getUid();

                    HashMap<String, Object> newRunData = new HashMap<>();
                    newRunData.put("points", newRun);

                    db.collection(uid).document(String.valueOf(java.util.Calendar.getInstance().getTime()))
                            .set(newRunData, SetOptions.merge());
                    finish();
                }
            });
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(4000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (trip != null) {

            ArrayList<LatLng> coorList = new ArrayList<>();

            ParcelableGeoPoint start = trip.get(0), end = trip.get(trip.size() - 1);

            double camLatmin = start.getGeoPoint().getLatitude();
            double camLatmax = start.getGeoPoint().getLatitude();
            double camLongmin = start.getGeoPoint().getLongitude();
            double camLongmax = start.getGeoPoint().getLongitude();

            for (ParcelableGeoPoint p : trip) {
                coorList.add(new LatLng(p.getGeoPoint().getLatitude(), p.getGeoPoint().getLongitude()));
                camLatmin = p.getGeoPoint().getLatitude() < camLatmin ? p.getGeoPoint().getLatitude() : camLatmin;
                camLongmin = p.getGeoPoint().getLongitude() < camLongmin ? p.getGeoPoint().getLongitude() : camLongmin;
                camLongmax = p.getGeoPoint().getLongitude() > camLongmax ? p.getGeoPoint().getLongitude() : camLongmax;
                camLatmax = p.getGeoPoint().getLatitude() > camLatmax ? p.getGeoPoint().getLatitude() : camLatmax;
            }

            Polyline polyline = mMap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .addAll(coorList));

            mMap.addMarker(new MarkerOptions().position(new LatLng(start.getGeoPoint().getLatitude(), start.getGeoPoint().getLongitude())).title("Start"));
            mMap.addMarker(new MarkerOptions().position(new LatLng(end.getGeoPoint().getLatitude(), end.getGeoPoint().getLongitude())).title("End"));

            LatLngBounds latLngBounds = new LatLngBounds(
                    new LatLng(camLatmin, camLongmin), // SW bounds
                    new LatLng(camLatmax, camLongmax)  // NE bounds
            );
            Log.d(TAG, "onMapReady: " + camLatmax + " " + camLatmin + " " + camLongmax + " " + camLongmin);

            mMap.setLatLngBoundsForCameraTarget(latLngBounds);
            int width = getResources().getDisplayMetrics().widthPixels;
            int height = getResources().getDisplayMetrics().heightPixels;
            int padding = (int) (width * 0.10);
            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds, width, height, padding));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            //getLastLocation();
            checkSettingAndStartLocationUpdates();
        } else {
            askLocationPermission();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopLocationUpdates();
    }

    private void checkSettingAndStartLocationUpdates(){

        LocationSettingsRequest request = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest).build();

        SettingsClient client = LocationServices.getSettingsClient(this);

        Task<LocationSettingsResponse> locationSettingsResponseTask = client.checkLocationSettings(request);


        locationSettingsResponseTask.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                //Settings of device satisfied and start location update

                startLocationUpdates();
            }
        });

        locationSettingsResponseTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if(e instanceof ResolvableApiException){
                    ResolvableApiException apiExtension = (ResolvableApiException) e;
                    try {
                        apiExtension.startResolutionForResult(MapsActivity.this,1001);
                    } catch (IntentSender.SendIntentException sendIntentException) {
                        sendIntentException.printStackTrace();
                    }
                }

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void startLocationUpdates(){
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,locationCallback, Looper.getMainLooper());
    }

    private void stopLocationUpdates(){
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);
    }

    private void getLastLocation() {

        @SuppressLint("MissingPermission") Task<Location> locationTask = fusedLocationProviderClient.getLastLocation();

        locationTask.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!= null){
                    //We have a location
                    Log.d(TAG, "onSuccess: "+location.toString());
                    Log.d(TAG, "onSuccess: "+location.getLatitude());
                    Log.d(TAG, "onSuccess: "+location.getLongitude());
                    newRun.add(new GeoPoint(location.getLatitude(), location.getLongitude()));
                }else
                {
                    Log.d(TAG, "onSuccess: Location is NULL ");
                }

            }
        });

        locationTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "onFailure: "+e.getLocalizedMessage());

            }
        });
    }

    private void  askLocationPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                Log.d(TAG, "askLocationPermission: ");

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST_CODE);

            }else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},LOCATION_REQUEST_CODE);

            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permission Granted
                // getLastLocation();
                if(trip == null) {
                    checkSettingAndStartLocationUpdates();
                }
            } else {
                //Permission Not granted
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        setTitle(getString(R.string.JogTracking));

    }

}