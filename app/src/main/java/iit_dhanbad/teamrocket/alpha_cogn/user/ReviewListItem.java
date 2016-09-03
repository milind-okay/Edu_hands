package iit_dhanbad.teamrocket.alpha_cogn.user;

/**
 * Created by milind on 25/5/16.
 */

public class ReviewListItem {
    // the drawable for the ListView item ImageView
    public final String author_name;        // the text for the ListView item title
    public final String text;
    public final String rating;

    public ReviewListItem(String author_name, String text, String rating) {

        this.author_name = author_name;
        this.text = text;
        this.rating = rating;

    }
}

