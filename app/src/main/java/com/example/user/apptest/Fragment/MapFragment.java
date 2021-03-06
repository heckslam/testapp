package com.example.user.apptest.Fragment;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.apptest.R;
import com.example.user.apptest.abstract_classes.AbstractTabFragment;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends AbstractTabFragment implements OnMapReadyCallback {

    private Context context;




    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        initMap();
        return view;

    }

    public static MapFragment getInstance(Context context){
        Bundle args = new Bundle();
        MapFragment mapFragment = new MapFragment();
        mapFragment.setArguments(args);
        mapFragment.setContext(context);
        mapFragment.setTitle(context.getString(R.string.tab_map));
        return mapFragment;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        final Random random = new Random();

        double lat;
        double lng;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(52, 52))
                .zoom(5)
                .build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        googleMap.moveCamera(cameraUpdate);
        for (int i = 0; i < 10; i++) {
            lat = random.nextInt(5) + 50;
            lng = random.nextInt(5) + 50;
            googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)));
        }
        googleMap.getUiSettings().setAllGesturesEnabled(true);
        googleMap.getUiSettings().setMapToolbarEnabled(true);
    }
}
