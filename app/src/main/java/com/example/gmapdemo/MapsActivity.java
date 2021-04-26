package com.example.gmapdemo;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Button mbtn1;
    Button mbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {//for permission
            mMap.setMyLocationEnabled(true);
        }
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
    public void zoomFunc(View v) {

        if (v.getId() == R.id.btnin) {
            mMap.moveCamera(CameraUpdateFactory.zoomIn());
        }
        else
        {
            mMap.moveCamera(CameraUpdateFactory.zoomOut());
        }
    }

    public void satalite(View V)
    {
        if(mMap.getMapType()== GoogleMap.MAP_TYPE_NORMAL)
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        }
        else
        {
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    public void Searchloc(View v) {
        EditText medt1 = findViewById(R.id.edtloc);

        String mloc = medt1.getText().toString();

        List<Address> madd = null;

        if (mloc != null) {
            Geocoder g = new Geocoder(this);
            try {
                madd = g.getFromLocationName(mloc, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address mad = madd.get(0);
            LatLng latLng = new LatLng(mad.getLatitude(),mad.getLatitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(mloc));
            mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));

        } else {
            Toast.makeText(this, "cant find the location", Toast.LENGTH_LONG);
        }

    }

}
