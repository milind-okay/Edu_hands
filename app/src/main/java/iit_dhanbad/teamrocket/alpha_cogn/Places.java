package iit_dhanbad.teamrocket.alpha_cogn;

/**
 * Created by milind on 3/9/16.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Places {

    public List<HashMap<String, String>> parse(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("server_response");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> placeMap = null;

        for (int i = 0; i < placesCount; i++) {
            try {
                placeMap = getPlace((JSONObject) jsonArray.get(i));
                placesList.add(placeMap);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return placesList;
    }

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson) {
        HashMap<String, String> googlePlaceMap = new HashMap<String, String>();
        String placeName = "-NA-";
        String vicinity = "-NA-";
        String latitude = "";
        String longitude = "";
        String reference = "";
        String rating = "";
        String fee = "";
        try {
            if (!googlePlaceJson.isNull("name")) {
                placeName = googlePlaceJson.getString("name");
            }
            if (!googlePlaceJson.isNull("topic")) {
                vicinity = googlePlaceJson.getString("topic");
            }
            if (!googlePlaceJson.isNull("learning_level")) {
                rating = googlePlaceJson.getString("learning_level");
            }
            if (!googlePlaceJson.isNull("fee")) {
                rating = googlePlaceJson.getString("fee");
            }
            latitude = googlePlaceJson.getString("x_coord");
            longitude = googlePlaceJson.getString("y_coord");
            reference = googlePlaceJson.getString("user_id");
            googlePlaceMap.put("rating",rating);
            googlePlaceMap.put("fee",fee);
            googlePlaceMap.put("name", placeName);
            googlePlaceMap.put("subject", googlePlaceJson.getString("subject"));
            googlePlaceMap.put("vicinity", vicinity);
            googlePlaceMap.put("lat", latitude);
            googlePlaceMap.put("lng", longitude);
            googlePlaceMap.put("reference", reference);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return googlePlaceMap;
    }
}
