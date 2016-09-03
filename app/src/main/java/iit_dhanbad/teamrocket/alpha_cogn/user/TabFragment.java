package iit_dhanbad.teamrocket.alpha_cogn.user;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.R;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Content;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class TabFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    int mListType = 0;
    public TabItemRecyclerViewAdapter adapter;
    private List<Content.ContentItem> mItems;
    private OnListFragmentInteractionListener mListener;
    RecyclerView recyclerView;
    ArrayList<String> mListValues;
    TextView tabContent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TabFragment() {

    }


    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TabFragment newInstance(int columnCount) {
        TabFragment fragment = new TabFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
            mListType = getArguments().getInt("id");
            Log.d("mListType", "" + mListType);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.tab_list);


        recyclerView.setAdapter(new TabItemRecyclerViewAdapter(Content.ITEMS_MP, mListener, mListType, mListValues));
        //        adapter.notifyItemChanged(Content.ITEMS_MP.size());
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tabContent = (TextView) getActivity().findViewById(R.id.tab_content);
       Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/bariol.ttf");
        tabContent.setTypeface(type);
        // Set the adapter


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name

        void setBasicProfile(int id);

    }
}
