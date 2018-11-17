package lab.sngc.labsngc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import pub.devrel.easypermissions.EasyPermissions;

public class GPS extends AppCompatActivity implements OnMapReadyCallback {

    TextView tvlat,tvlon;
    MapView mview;
    GoogleMap gmap;
    double lat,lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        tvlat = findViewById(R.id.lat);
        tvlon = findViewById(R.id.lon);
        mview = findViewById(R.id.map);
        mview.onCreate(savedInstanceState);
        mview.getMapAsync(this);
    }

    @SuppressLint("MissingPermission")
    public void updateLocation(View v){

        // check permissions
        String perm[] = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};
        boolean status = EasyPermissions.hasPermissions(this,perm);
        if(!status){
            EasyPermissions.requestPermissions(this,"This permission is required",0,perm);
        }
        status = EasyPermissions.hasPermissions(this,perm);
        if(!status){
            Toast.makeText(this, "Goodbye", Toast.LENGTH_SHORT).show();
            System.exit(0);
        }

        GPSTracker gt = new GPSTracker(this);

        // remove previous marker
        gmap.clear();

        lat = gt.getLatitude();
        lon = gt.getLongitude();

        tvlat.setText("Latitude:    "+lat);
        tvlon.setText("Longitude:  "+lon);

        // zooming
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(Float.parseFloat(String.valueOf(lat)),Float.parseFloat(String.valueOf(lon))), 12.0f));
        gmap.addMarker(new MarkerOptions().position(new LatLng(lat,lon)).title("My Location"));
    }

    public void onBackPressed(){
        Intent i = new Intent(this,Launch.class);
        startActivity(i);
        finish();
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        gmap = googleMap;
        gmap.getUiSettings().setAllGesturesEnabled(true);
        gmap.getUiSettings().setMyLocationButtonEnabled(false);
        gmap.setMyLocationEnabled(true);
        gmap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        MapsInitializer.initialize(this);
    }


    public void onResume(){
        super.onResume();
        mview.onResume();
    }

    public void onStart(){
        super.onStart();
        mview.onStart();
    }

    public void onStop(){
        super.onStop();
        mview.onStop();
    }

    public void onPause(){
        super.onPause();
        mview.onPause();
    }

    public void onDestroy(){
        super.onDestroy();
        mview.onDestroy();
    }

    public void onLowMemory(){
        super.onLowMemory();
        mview.onLowMemory();
    }

}
