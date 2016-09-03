package iit_dhanbad.teamrocket.alpha_cogn;

import android.Manifest;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.utils.InternetConnectionDetector;
import iit_dhanbad.teamrocket.alpha_cogn.utils.PermissionUtils;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback, GoogleMap.OnMarkerDragListener,
        ActivityCompat.OnRequestPermissionsResultCallback, View.OnClickListener{

    LatLng mylatlng;
    Marker myMarker;
    int radius;
    Button searchThisArea;
    double  minlat, maxlat, minlng, maxlng;
    private ProgressDialog pDialog;
    boolean moveCamera,isList;
    InternetConnectionDetector internetConnectionDetector;
    String url;
    private HashMap<String, Marker> mPlaceIdAnd = new HashMap<>();
    private static final String MAP_FRAGMENT_TAG = "map";
    /**
     * Request code for location permission request.
     *
     * @see #onRequestPermissionsResult(int, String[], int[])
     */
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    /**
     * Flag indicating whether a requested permission has been denied after returning in
     * {@link #onRequestPermissionsResult(int, String[], int[])}.
     */
    private boolean mPermissionDenied = false;

    private GoogleMap mMap;
    private HashMap<Marker, String> blueMarkers = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MapFragment mapFragment = (MapFragment)
                getFragmentManager().findFragmentByTag(MAP_FRAGMENT_TAG);
        if (mapFragment == null) {

            mapFragment = MapFragment.newInstance();

            FragmentTransaction fragmentTransaction =
                    getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.map, mapFragment, MAP_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * Displays a dialog with error message explaining that the location permission is missing.
     */
    private void showMissingPermissionError() {
        // Log.d("at show MissingPer", "Dialog");
        PermissionUtils.PermissionDeniedDialog
                .newInstance(false).show(getSupportFragmentManager(), "dialog");
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        NavFunctions navFunctions = new NavFunctions(id, MainActivity.this);
        navFunctions.selectItem();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
     /*   if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

*/
        // Log.d("at permissionresult", "ok");
        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {

            enableMyLocation();
            mPermissionDenied = false;
        } else {

            mPermissionDenied = true;
        }
    }
    @Override
    protected void onResumeFragments() {
        super.onResumeFragments();
        // Log.d("at resume fragment", "ok");
        if (mPermissionDenied) {

            showMissingPermissionError();
            mPermissionDenied = false;
        }
    }
    @Override
    public void onMapReady(GoogleMap map) {
        moveCamera = true;
        mMap = map;
        // Log.d("onMapready", "okay--");
        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationChangeListener(myLocationChangeListener());

        enableMyLocation();
        mMap.setOnMarkerDragListener(this);
        mMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener()
        {
            @Override
            public void onCameraChange(CameraPosition cameraPosition)
            {
                if (!isList) {
                    double difflat = mMap.getCameraPosition().target.latitude - mylatlng.latitude,maxdifflat = maxlat-minlat;
                    double difflng = mMap.getCameraPosition().target.longitude - mylatlng.longitude,maxdifflng = maxlng-minlng;
                    if(difflat<0.0)
                        difflat=-difflat;
                    if(difflng<0.0)
                        difflng=-difflng;

                    if ((difflat) > (maxdifflat) || (difflng) > (maxdifflng))
                        // if ((mMap.getCameraPosition().target.latitude) > maxlat  ||  mMap.getCameraPosition().target.longitude >  maxlng
                        //       ||mMap.getCameraPosition().target.latitude > minlat ||  mMap.getCameraPosition().target.longitude >  minlng)
                        searchThisArea.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    /**
     * Enables the My Location layer if the fine location permission has been granted.
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                    Manifest.permission.ACCESS_FINE_LOCATION, true);
            // PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE, Manifest.permission.ACCESS_COARSE_LOCATION, true);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);


        }
    }
    @Override
    public void onClick(View v) {

    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {

    }
    private boolean checkReady() {
        if (mMap == null) {
            Toast.makeText(this, "MAp Not ready yet", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    /**
     * Called when the Clear button is clicked.
     */
    public void onClearMap(View view) {
        if (!checkReady()) {
            return;
        }
        mMap.clear();
    }
    /**
     * Called when the Reset button is clicked.
     */
    public void onResetMap() {
        if (!checkReady()) {
            return;
        } else {
            mMap.clear();
        }
    }
    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }
    private GoogleMap.OnMyLocationChangeListener myLocationChangeListener() {
        return new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                if (moveCamera) {
                    mylatlng = new LatLng(location.getLatitude(), location.getLongitude());


                    //  Log.d("at movecamera", "true");
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(mylatlng, 15.0f), new GoogleMap.CancelableCallback() {
                        @Override
                        public void onFinish() {
                            setApi();
                        }

                        @Override
                        public void onCancel() {

                        }
                    });
                    myMarker = mMap.addMarker(new MarkerOptions()
                            .position(mylatlng)
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                            .title("Your location")
                            .snippet("you can drag it")
                            .draggable(true));
                    moveCamera = false;

                }
            }
            //Toast.makeText(getApplicationContext(),"You are at [" + longitude + " ; " + latitude + " ]",Toast.LENGTH_LONG).show();

            //get current address by invoke an AsyncTask object


        };
    }
/********************end map section*********************************************************/
    private void sendRequest() {
        //  Log.d("at ", "sendRequest");
        internetConnectionDetector = new InternetConnectionDetector(getApplicationContext());
        if (internetConnectionDetector.isConnectingToInternet()) {
            Log.d("sending request", url);
            //pDialog.setTitle("");
            searchThisArea.setVisibility(View.GONE);
            if (pDialog == null) {
                pDialog = new ProgressDialog(this);
                pDialog.setMessage("Fetching Clinics around You");
                pDialog.setCancelable(true);
                pDialog.show();
            }
            StringRequest stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonObject= new JSONObject(response);
                                if(jsonObject.getString("status").equals("OK")){
                                    if (isList) {/**TODO check listview
                                        ListView fragment = (ListView) getFragmentManager().findFragmentById(R.id.list);
                                        if (fragment != null) {
                                            fragment.showPlacesList(response);
                                        }*/
                                    }/* else if (isAds)
                                try {
                                    parseAds(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }*/ else
                                        showPlaces(response);
                                }else{
                                    Toast.makeText(getApplicationContext(),"No Clinic Found in 100 Km radius",Toast.LENGTH_LONG).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            pDialog.dismiss();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
        }
    }
    public void showPlaces(String result) {
        onResetMap();

        myMarker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(mylatlng.latitude, mylatlng.longitude))
                .title("Your location")
                .snippet("you can drag it")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                .draggable(true));
        ArrayList<String> temp = new ArrayList<>();
        temp.add("m");
        temp.add("!");
        MapPlacesDisplayTask placesDisplayTask = new MapPlacesDisplayTask();
        Object[] toPass = new Object[2];
        toPass[0] = mMap;
        toPass[1] = result;
        placesDisplayTask.execute(toPass);

    }

    public void setApi() {
        url = "";
        sendRequest();

    }
    public class MapPlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

        JSONObject googlePlacesJson;
        GoogleMap googleMap;

        @Override
        protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

            List<HashMap<String, String>> googlePlacesList = null;
            Places placeJsonParser = new Places();
            try {
                googleMap = (GoogleMap) inputObj[0];
                googlePlacesJson = new JSONObject((String) inputObj[1]);
                googlePlacesList = placeJsonParser.parse(googlePlacesJson);
            } catch (Exception e) {
                //    Log.d("Exception", e.toString());
            }
            return googlePlacesList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {
            //  googleMap.clear();

            minlat = mylatlng.latitude;
            minlng = mylatlng.longitude;
            maxlat = mylatlng.latitude;
            maxlng = mylatlng.longitude;

            // mMarkerHash.clear();
            if (list != null)
                for (int i = 0; i < list.size(); i++) {
                    MarkerOptions markerOptions = new MarkerOptions();
                    HashMap<String, String> googlePlace = list.get(i);
                    double lat = Double.parseDouble(googlePlace.get("lat"));
                    double lng = Double.parseDouble(googlePlace.get("lng"));
                    String placeName = googlePlace.get("place_name");
                    String vicinity = googlePlace.get("vicinity");
                    LatLng latLng = new LatLng(lat, lng);
                    markerOptions.position(latLng);
                    markerOptions.title(placeName);
                    markerOptions.snippet(vicinity);
                    ArrayList<String> temp = new ArrayList<>();
                    temp.add(googlePlace.get("reference"));
                    Marker marker;
                    /*if (googlePlace.get("type").equals(Const.HC_RESULT_TYPE1)) {
                        temp.add(googlePlace.get("ad"));
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
                        marker = mMap.addMarker(markerOptions);
                    } else if (googlePlace.get("type").equals(Const.HC_RESULT_TYPE2)) {
                        temp.add("!");
                        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                        marker = mMap.addMarker(markerOptions);
                        blueMarkers.put(marker, googlePlace.get("hcid"));
                    } else {
                        temp.add("!");
                        marker = mMap.addMarker(markerOptions);
                    }
*/                  marker = mMap.addMarker(markerOptions);
                    mPlaceIdAnd.put(googlePlace.get("reference"), marker);
                    if (minlat > lat)
                        minlat = lat;
                    if (maxlat < lat)
                        maxlat = lat;
                    if (minlng > lng)
                        minlng = lng;
                    if (maxlng < lng)
                        maxlng = lng;

                }
            LatLngBounds bounds = new LatLngBounds.Builder()
                    .include(new LatLng(minlat, minlng))
                    .include(new LatLng(maxlat, maxlng))
                    .build();

            mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 50));
            // pDialog.dismiss();
        }

    }


}
