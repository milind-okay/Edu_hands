package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 3/9/16.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import iit_dhanbad.teamrocket.alpha_cogn.R;


public class RateAndReviewInfo extends Fragment {

    private static final String ARG_PARAM1 = "rating";
    private static final String ARG_PARAM2 = "review";
    String mReviewListItems;
    ArrayList<ReviewListItem> reviewListItems = new ArrayList<>();
    RecyclerView recyclerView;
    Typeface type;
    boolean flag = false;

    float mRating;


    public RateAndReviewInfo() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRating = getArguments().getFloat(ARG_PARAM1);
            mReviewListItems = getArguments().getString(ARG_PARAM2);
            // Log.d("at frag doc review",mReviewListItems + "okay");
            setReviewList(mReviewListItems);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.rating_n_reviews, container, false);
        type = Typeface.createFromAsset(getContext().getAssets(), "fonts/bariol.ttf");
        RatingBar ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBar.setRating(mRating);
        recyclerView = (RecyclerView) view.findViewById(R.id.review_list);
        if (flag) {

            recyclerView.setAdapter(new ReviewRecyclerViewAdapter(reviewListItems));
        }
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void setReviewList(String review) {
        if (review != null)
            try {

                JSONArray jsonArray = new JSONArray(review);
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        reviewListItems.add(new ReviewListItem(jsonObject.getString("author_name"), jsonObject.getString("text"), jsonObject.getString("rating")));
                    } catch (JSONException e) {

                    }
                }
            } catch (JSONException e) {

            }
        Log.d("at set Review list", reviewListItems.size() + " ");
        flag = true;
    }

}
