package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 3/9/16.
 */

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.PlaceDetailsParser;
import iit_dhanbad.teamrocket.alpha_cogn.Places;
import iit_dhanbad.teamrocket.alpha_cogn.R;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Const;
import iit_dhanbad.teamrocket.alpha_cogn.utils.InternetConnectionDetector;

public class UserProfile extends AppCompatActivity implements TabFragment.OnListFragmentInteractionListener {

    float mRating;
    ImageLoader imageLoader;
    public String PLACE_ID,url;
    String user_image, default_google_image = "https://maps.gstatic.com/mapfiles/place_api/icons/generic_business-71.png";
    public String mName, mAddress, mPhone, mInterPhone, mEmail, mWebsite, mReview;
    ArrayList<String> mWeekDays = new ArrayList<>();


    ImageView userImage, userBackgroundImage;
    SharedPreferences sharedPreferences;
    InternetConnectionDetector internetConnectionDetector;
    GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            PLACE_ID = extras.getString("place_id");
            // Log.d("place_id", PLACE_ID);
            sendRequest("review");
        }
        userImage = (ImageView) findViewById(R.id.userImage);
        userBackgroundImage = (ImageView) findViewById(R.id.backgroundImage);
        sharedPreferences = getSharedPreferences(Const.mypreference, MODE_PRIVATE);

        internetConnectionDetector = new InternetConnectionDetector(getApplicationContext());
     /*   TODO imageloder
     imageLoader = ImageLoader.getInstance();
        // Initialize ImageLoader with configuration. Do it once.

        if (!imageLoader.isInited()) {
            imageLoader.init(ImageLoaderConfiguration.createDefault(getApplicationContext()));
        }*/

    }

    protected void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        if (getIntent().getBooleanExtra("addusersFragment", false)) {
            Fragment fragment = new RateAndReviewInfo();
            Bundle bundle3 = new Bundle();
            bundle3.putString("onClickTargetId", getIntent().getStringExtra("onClickTargetId"));
            bundle3.putString("onClickTargetType", getIntent().getStringExtra("onClickTargetType"));
            fragment.setArguments(bundle3);
            adapter.addFragment(fragment, "users");
        }

        UserBasicInfo userContactInfo = new UserBasicInfo();
        Bundle bundle1 = new Bundle();
        bundle1.putString("name", mName);
        bundle1.putString("address", mAddress);
        bundle1.putString("phone_number","+918877006184");
        //  bundle1.putString("international_phone_number", mInterPhone);
        // bundle1.putString("email", mEmail);
        //  bundle1.putString("website", mWebsite);
        userContactInfo.setArguments(bundle1);

        adapter.addFragment(userContactInfo, "Profile");

        addActivitiesFragment(adapter);

        RateAndReviewInfo userRateAndReviewInfo = new RateAndReviewInfo();
        Bundle bundle2 = new Bundle();
        bundle2.putFloat("rating", mRating);
        bundle2.putString("review", mReview);
        userRateAndReviewInfo.setArguments(bundle2);

        adapter.addFragment(userRateAndReviewInfo, "Rate & Review");

        viewPager.setAdapter(adapter);
    }

    protected void addActivitiesFragment(ViewPagerAdapter adapter) {
        UserActivities userActivities = new UserActivities();
        Bundle bundle3 = new Bundle();
        bundle3.putStringArrayList("week_text", mWeekDays);
        userActivities.setArguments(bundle3);
        adapter.addFragment(userActivities, "subjects");
    }


    @Override
    public void setBasicProfile(int id) {

    }


    public class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void sendRequest(final String item) {
        if(item.equals("review")){
            url = "http://alphacogn.net23.net/reviews.php?user_id="+PLACE_ID;
        }else{
            url = "http://alphacogn.net23.net/teacher_profile.php?user_id="+PLACE_ID;
        }
        internetConnectionDetector = new InternetConnectionDetector(getApplicationContext());
        if (internetConnectionDetector.isConnectingToInternet()) {
            Log.d("place-Detail", url );
            StringRequest stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //  Toast.makeText(getActivity().getApplicationContext(),response,Toast.LENGTH_LONG).show();
                            // Log.d("Server response...<>>", response);
                            if(item.equals("review")){
                                mReview = response;
                                sendRequest("hh");
                            }else{
                            MapPlacesDisplayTask placesDisplayTask = new MapPlacesDisplayTask();
                            Object[] toPass = new Object[2];
                            toPass[0] = mMap;
                            toPass[1] = response;
                            placesDisplayTask.execute(toPass);}

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {


                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(this, "No Internet connection", Toast.LENGTH_LONG).show();
        }

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


            // mMarkerHash.clear();
            if (list != null)
                for (int i = 0; i < list.size(); i++) {

                    HashMap<String, String> googlePlace = list.get(i);
                    double lat = Double.parseDouble(googlePlace.get("lat"));
                    double lng = Double.parseDouble(googlePlace.get("lng"));
                    mAddress = googlePlace.get("subject");
                    String vicinity = googlePlace.get("vicinity");
                    mName = googlePlace.get("name");
                    mRating = Float.parseFloat(googlePlace.get("rating"));
                    // pDialog.dismiss();
                }
            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            setupViewPager(viewPager);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.user_tab_layout);
            assert tabLayout != null;
            tabLayout.setupWithViewPager(viewPager);


        }
    }
    /**
     * A class to parse the Google Place Details in JSON format
     */
   /* private class ParserTask extends AsyncTask<String, Integer, HashMap<String, String>> {

        JSONObject jObject;

        // Invoked by execute() method of this object
        @Override
        protected HashMap<String, String> doInBackground(String... jsonData) {

            HashMap<String, String> hPlaceDetails = null;
            PlaceDetailsParser placeDetailsJsonParser = new PlaceDetailsParser();

            try {
                jObject = new JSONObject(jsonData[0]);

                // Start parsing Google place details in JSON format
                hPlaceDetails = placeDetailsJsonParser.parse(jObject);

            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return hPlaceDetails;
        }

        @Override
        protected void onPostExecute(HashMap<String, String> hPlaceDetails) {
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .showStubImage(R.drawable.user) //this is the image that will be displayed if download fails
                    .cacheInMemory()
                    .cacheOnDisc()
                    .build();
            mName = hPlaceDetails.get("name");

            //  Log.d("user_image",user_image);
        /*    if (!user_image.equals(default_google_image))
                imageLoader.displayImage(user_image, userImage, options);*/
          /*  mAddress = hPlaceDetails.get("vicinity");
            String lat = hPlaceDetails.get("lat");
            String lng = hPlaceDetails.get("lng");
            // mAddress = hPlaceDetails.get("formatted_address");

            //  mWebsite = hPlaceDetails.get("website");
            mRating = Float.parseFloat(hPlaceDetails.get("rating"));
            //   mInterPhone = hPlaceDetails.get("international_phone_number");


            mReview = hPlaceDetails.get("review");
            ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
            setupViewPager(viewPager);
            TabLayout tabLayout = (TabLayout) findViewById(R.id.user_tab_layout);
            assert tabLayout != null;
            tabLayout.setupWithViewPager(viewPager);
        }
    }*/

}
