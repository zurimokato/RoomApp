package job.project.com.roomapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import job.project.com.roomapp.pojo.Room;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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


        final ArrayList<Room>rooms;

        rooms=getIntent().getParcelableArrayListExtra("Lista");

        if (rooms!=null){
            for (Room r:rooms){
                LatLng sydney = new LatLng(r.getUbicacion().getLatd(), r.getUbicacion().getLongi());
                mMap.addMarker(new MarkerOptions().position(sydney).title(r.getTitulo()));
            }
        }
        // Add a marker in Sydney and move the camera

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                Intent intent =new Intent(getApplicationContext(),Main2Activity.class);
                for (Room r: rooms){
                    if (r.getTitulo().equals(marker.getTitle())){
                        intent.putExtra("room",r);
                    }
                }
                return false;
            }
        });

        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(11.2226453,-74.2232032)));
        mMap.setMinZoomPreference(10);
    }



}
