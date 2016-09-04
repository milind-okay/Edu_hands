package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 3/9/16.
 */

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;

import iit_dhanbad.teamrocket.alpha_cogn.R;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Content;


public class UserActivities extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "week_text";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<String> mWeekDays = new ArrayList<>();
    CardView cardViewDay0,cardViewDay1,cardViewDay2,cardViewDay3,cardViewDay4,cardViewDay5,cardViewDay6;
    TextView Day0,Day1,Day2,Day3,Day4,Day5,Day6;



    public UserActivities() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWeekDays = getArguments().getStringArrayList(ARG_PARAM1);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_basic_info, container, false);
        Typeface type = Typeface.createFromAsset(getContext().getAssets(), "fonts/bariol.ttf");
        cardViewDay0 = (CardView) view.findViewById(R.id.card_day0);
        cardViewDay1 = (CardView) view.findViewById(R.id.card_day1);
        cardViewDay2 = (CardView) view.findViewById(R.id.card_day2);
        cardViewDay3 = (CardView) view.findViewById(R.id.card_day3);
        cardViewDay4 = (CardView) view.findViewById(R.id.card_day4);
        cardViewDay5 = (CardView) view.findViewById(R.id.card_day5);
        cardViewDay6 = (CardView) view.findViewById(R.id.card_day6);
        cardViewDay0.setVisibility(View.VISIBLE);
        cardViewDay1.setVisibility(View.VISIBLE);
        cardViewDay2.setVisibility(View.VISIBLE);
        cardViewDay3.setVisibility(View.VISIBLE);
        cardViewDay4.setVisibility(View.VISIBLE);
        cardViewDay5.setVisibility(View.VISIBLE);
        cardViewDay6.setVisibility(View.VISIBLE);
        Day0 = (TextView) view.findViewById(R.id.day0);
        Day1 = (TextView) view.findViewById(R.id.day1);
        Day2 = (TextView) view.findViewById(R.id.day2);
        Day3 = (TextView) view.findViewById(R.id.day3);
        Day4 = (TextView) view.findViewById(R.id.day4);
        Day5 = (TextView) view.findViewById(R.id.day5);
        Day6 = (TextView) view.findViewById(R.id.day6);
        Day0.setTypeface(type);
        Day1.setTypeface(type);
        Day2.setTypeface(type);
        Day3.setTypeface(type);
        Day4.setTypeface(type);
        Day5.setTypeface(type);
        Day6.setTypeface(type);

            Day0.setText(Content.web[0]);
            Day1.setText(Content.web[1]);
            Day2.setText(Content.web[2]);
            Day3.setText(Content.web[3]);
            Day4.setText(Content.web[4]);
            Day5.setText(Content.web[5]);
            Day6.setText(Content.web[6]);



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


}
