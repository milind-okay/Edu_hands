package iit_dhanbad.teamrocket.alpha_cogn;

/**
 * Created by milind on 4/9/16.
 */

public class ActivityListViewItem {
    // the drawable for the ListView item ImageView
    public final String title;        // the text for the ListView item title
    public final String description,address,ads_text,type;
    // the text for the ListView item description
    public final String place_id,exp,review,fee;

    public ActivityListViewItem( String title, String description,String address,String place_id,String exp,String fee,String review,String type,String ads_text) {

        this.title = title;
        this.description = description;
        this.address = address;
        this.place_id = place_id;
        this.exp = exp;
        this.fee = fee;
        this.review = review;
        this.type =type;
        this.ads_text = ads_text;

    }
}

