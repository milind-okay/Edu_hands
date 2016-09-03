package iit_dhanbad.teamrocket.alpha_cogn.user;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.R;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Const;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Content;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Content.ContentItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TabItemRecyclerViewAdapter extends RecyclerView.Adapter<TabItemRecyclerViewAdapter.ViewHolder> {

    private final List<Content.ContentItem> mValues;
    private final TabFragment.OnListFragmentInteractionListener mListener;
    int mlistID, mContentType;
    ArrayList<String> mList;
    Typeface type;
    SharedPreferences sharedPreferences;
    boolean mClinicType[];
    SharedPreferences.Editor editor;


    public TabItemRecyclerViewAdapter(List<Content.ContentItem> items, TabFragment.OnListFragmentInteractionListener listener, int listID, ArrayList<String> mList) {

        mValues = items;
        mListener = listener;
        mlistID = listID;

        this.mList = mList;
        mClinicType = new boolean[10];
        for (int i = 0; i < 10; i++) {
            mClinicType[i] = true;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_option, parent, false);
        type = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/bariol.ttf");

        sharedPreferences =parent.getContext().getSharedPreferences(Const.mypreference, 0);
        editor = sharedPreferences.edit();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.mItem = mValues.get(position);

            String data = getValue(mValues.get(position).id);
            holder.mContentView.setVisibility(View.VISIBLE);
            holder.mContentViewValue.setVisibility(View.VISIBLE);
            holder.mContentView.setText(mValues.get(position).content);
            holder.mContentView.setTypeface(type);
            holder.mContentViewValue.setText(data);
            holder.mContentViewValue.setTypeface(type);
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mListener.setBasicProfile(mValues.get(position).id);
                    notifyDataSetChanged();
                }
            });



    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public final CardView mCardView;
        public final TextView mContentViewValue;
        public final CheckBox mCheckBox;
        public Content.ContentItem mItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            mContentView = (TextView) view.findViewById(R.id.pager);
            mContentViewValue = (TextView)view.findViewById(R.id.contentValue);
            mCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
            mCardView = (CardView)view.findViewById(R.id.card_view);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
    public String getValue(int a){
        String temp;
        switch (a){

            case 2:
                temp = sharedPreferences.getString(Const.Mobile_pref,"null");
                break;
            case 3:
                temp = sharedPreferences.getString(Const.email_id_pref,"null");
                break;

                default:
                    temp = "null";
        }
        return temp;
    }


}
