package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 3/9/16.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import iit_dhanbad.teamrocket.alpha_cogn.R;


public class UserBasicInfo extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "name";
    private static final String ARG_PARAM2 = "address";
    String mName ="MAr",mAddress=" sdasd";
    TextView name,nameValue,address,addressValue;

    public UserBasicInfo() {
        // Required empty public constructor
    }


    public static UserBasicInfo newInstance(String param1, String param2) {
        UserBasicInfo fragment = new UserBasicInfo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* DoctorProfile activity=(DoctorProfile)view;
        mName = activity.mName;
        mAddress = activity.mAddress;*/
        if (getArguments() != null) {
            mName = getArguments().getString(ARG_PARAM1);
            mAddress = getArguments().getString(ARG_PARAM2);
            // Log.d("at frag docInfo name",mName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_basic_info, container, false);
        name = (TextView) view.findViewById(R.id.name);
        nameValue = (TextView) view.findViewById(R.id.nameValue);
        address = (TextView) view.findViewById(R.id.address);
        addressValue = (TextView) view.findViewById(R.id.addressValue);
        name.setVisibility(View.VISIBLE);
        nameValue.setVisibility(View.VISIBLE);
        address.setVisibility(View.VISIBLE);
        addressValue.setVisibility(View.VISIBLE);
        nameValue.setText(mName);
        addressValue.setText(mAddress);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


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

