package iit_dhanbad.teamrocket.alpha_cogn;

/**
 * Created by milind on 4/9/16.
 */

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.user.UserProfile;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Const;


public class ActivityListViewAdapter extends ArrayAdapter<ActivityListViewItem> {
    Typeface type;
    String place_id;
    List<ActivityListViewItem> items;

    public ActivityListViewAdapter(Context context, List<ActivityListViewItem> items) {
        super(context, R.layout.fragment_list, items);
        this.items = items;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.fragment_list, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();

           // viewHolder.ratingBar = (RatingBar) convertView.findViewById(R.id.ratingBar0);

            viewHolder.Name = (TextView) convertView.findViewById(R.id.Name);
            viewHolder.Specification = (TextView) convertView.findViewById(R.id.Specification);

            viewHolder.address = (TextView) convertView.findViewById(R.id.place);
            viewHolder.book_button = (Button) convertView.findViewById(R.id.book_appointment);
            viewHolder.Fee = (TextView) convertView.findViewById(R.id.fee);
            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        final ActivityListViewItem item = items.get(position);





        viewHolder.Name.setText(item.title);
        viewHolder.Name.setTypeface(type);
        viewHolder.Specification.setText(item.description);

        viewHolder.address.setTypeface(type);
        viewHolder.address.setText(item.address);

        viewHolder.Fee.setText(item.fee);

        //viewHolder.ratingBar.setRating(Float.parseFloat(item.exp)/2);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToClinicProfile(item.place_id);
            }
        });

        return convertView;
    }
    public void ToClinicProfile(String place_id){
        Bundle bundle = new Bundle();
        Intent intent = new Intent(getContext(), UserProfile.class);
        bundle.putString("place_id", place_id);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     * @see
     */
    private static class ViewHolder {
        ImageView ivIcon;
        TextView Name;
        TextView Specification;
        TextView address;
        TextView Fee;


       // RatingBar ratingBar;
        Button book_button;


    }
}
