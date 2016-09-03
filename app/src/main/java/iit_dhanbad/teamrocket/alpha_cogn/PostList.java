package iit_dhanbad.teamrocket.alpha_cogn;

/**
 * Created by milind on 3/9/16.
 */

import android.app.ListFragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class
DoctorListView extends ListFragment {
    private List<PostListViewItem> mItems;        // ListView items list
    private String specialtyValue = null;
    public PostListViewAdapter adapter;
    DoctorListComm mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DoctorListComm) {
            mListener = (DoctorListComm) context;
            mListener.setList();
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mItems = new ArrayList<PostListViewItem>();


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // remove the dividers from the ListView of the ListFragment
        getListView().setDivider(null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // retrieve theListView item
        PostListViewItem item = mItems.get(position);

        // do something
        Toast.makeText(getActivity(), item.description, Toast.LENGTH_SHORT).show();
    }


    public void showPlacesList(String response) {

        ListPlacesDisplayTask placesDisplayTask = new ListPlacesDisplayTask();
        Object[] toPass = new Object[2];
        toPass[0] = response;
        placesDisplayTask.execute(toPass);
    }


    public class ListPlacesDisplayTask extends AsyncTask<Object, Integer, List<HashMap<String, String>>> {

        JSONObject googlePlacesJson;


        @Override
        protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

            List<HashMap<String, String>> googlePlacesList = null;
            Places placeJsonParser = new Places();

            try {

                googlePlacesJson = new JSONObject((String) inputObj[0]);
                googlePlacesList = placeJsonParser.parse(googlePlacesJson);
            } catch (Exception e) {
                Log.d("Exception", e.toString());
            }
            return googlePlacesList;
        }

        @Override
        protected void onPostExecute(List<HashMap<String, String>> list) {
            mItems.clear();
            if (list != null)
                for (int i = 0; i < list.size(); i++) {
                    HashMap<String, String> googlePlace = list.get(i);
                    String placeName = googlePlace.get("place_name");
                    String vicinity = googlePlace.get("vicinity");
                    Log.d("rating", googlePlace.get("rating") + 3);
                    mItems.add(new PostListViewItem(placeName, specialtyValue, vicinity, googlePlace.get("reference"),
                            " ", " ", " ", googlePlace.get("type"), googlePlace.get("ad")));
                    adapter = new PostListViewAdapter(getActivity(), mItems);
                    setListAdapter(adapter);

                }

        }
    }

    interface DoctorListComm {
        void setList();
    }

}


