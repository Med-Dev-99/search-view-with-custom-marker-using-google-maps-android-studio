package com.example.addmarkertomaps;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback ,
        GoogleMap.OnMarkerClickListener{

    /////
    private GoogleMap mMap;

    private  LatLng bmce1 = new LatLng(27.1496461, -13.1996226);
    private  LatLng bmce2 = new LatLng(28.4387388, -11.1020437);
    private  LatLng bmce3 = new LatLng(27.140428999999997, -13.187147);
    private  LatLng bmce4 = new LatLng(28.983149299999997, -10.0572996);

    private Marker mbmce1;
    private Marker mbmce2;
    private Marker mbmce3;
    private Marker mbmce4;
    SearchView sr_location;
    //////






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_maps);
        mapFragment.getMapAsync((OnMapReadyCallback) this);
        sr_location=findViewById(R.id.sr_location);
        sr_location.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                String location =sr_location.getQuery().toString();
                List <Address> addresslist=null;
                if (location!=null || !location.equals("")){

                    Geocoder gc= new Geocoder(MainActivity.this);
                    try {
                        addresslist=gc.getFromLocationName(location,1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address =addresslist.get(0);
                    LatLng ltl=new LatLng(address.getLatitude(),address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(ltl).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ltl,10));

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        mMap = googleMap;


        List<Marker> markerList = new ArrayList<>();

        mbmce1 =   mMap.addMarker(new MarkerOptions()
                .position(bmce1).title("bmce 1")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))) ;
        mbmce1.setTag(0);
        markerList.add(mbmce1);

        mbmce2 =   mMap.addMarker(new MarkerOptions()
                .position(bmce2).title("bmce 2")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))) ;
        mbmce2.setTag(0);
        markerList.add(mbmce2);

        mbmce3 =   mMap.addMarker(new MarkerOptions()
                .position(bmce2).title("bmce 3")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))) ;
        mbmce3.setTag(0);
        markerList.add(mbmce3);

        mbmce4 =   mMap.addMarker(new MarkerOptions()
                .position(bmce4).title("bmce 4")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET))) ;
        mbmce4.setTag(0);
        markerList.add(mbmce4);


        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        mMap.setOnMarkerClickListener(this);

        for(Marker m : markerList){
            LatLng latLng = new LatLng(m.getPosition().latitude,m.getPosition().longitude);
            mMap.addMarker(new MarkerOptions().position(latLng));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,5));
        }


    }
    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Toast.makeText(this,marker.getTitle(),Toast.LENGTH_SHORT).show();
        return false;
    }

}




