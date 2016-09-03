package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 3/9/16.
 */

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

import iit_dhanbad.teamrocket.alpha_cogn.R;


public class ReviewRecyclerViewAdapter extends RecyclerView.Adapter<ReviewRecyclerViewAdapter.ViewHolder> {

    private final List<ReviewListItem> items;

    Typeface type;



    public ReviewRecyclerViewAdapter(List<ReviewListItem> items) {

        this.items = items;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_item, parent, false);
        type = Typeface.createFromAsset(parent.getContext().getAssets(), "fonts/bariol.ttf");


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        ReviewListItem item = items.get(position);

            //String temp = getListValue(mlistID);

        holder.authorName.setText(item.author_name);
       holder.authorName.setTypeface(type);
        holder.text.setText(item.text);
        holder.text.setTypeface(type);
       holder.ratingBar.setRating(Float.parseFloat(item.rating));



    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        TextView authorName;
        TextView text;
        RatingBar ratingBar;

        public ViewHolder(View view) {
            super(view);

            mView = view;
           authorName = (TextView) view.findViewById(R.id.authorName);
            text = (TextView) view.findViewById(R.id.reviewText);

           ratingBar = (RatingBar) view.findViewById(R.id.ratingBar2);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + authorName.getText() + "'";
        }
    }

}
