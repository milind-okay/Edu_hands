package iit_dhanbad;

/**
 * Created by milind on 3/9/16.
 */

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import iit_dhanbad.teamrocket.alpha_cogn.R;
import iit_dhanbad.teamrocket.alpha_cogn.utils.Const;

public class NavFunctions extends AppCompatActivity {
    int id;
    Context c;
    SharedPreferences sharedPreferences;
    String Session_Language_pref = "language";
    SharedPreferences.Editor editor;
    public NavFunctions(int mid, Context context){
        id = mid;
        c= context;
        sharedPreferences = c.getSharedPreferences(Const.mypreference, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }
    public void  selectItem(){



        if (id == R.id.nav_about) {
            Uri uri = Uri.parse("market://details?id=" + c.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                c.startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                c.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + c.getPackageName())));
            }
        }

        if (id == R.id.nav_rate) {
            Uri uri = Uri.parse("market://details?id=" + c.getPackageName());
            Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
            // To count with Play market backstack, After pressing back button,
            // to taken back to our application, we need to add following flags to intent.
            goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                    Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                    Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
            try {
                c.startActivity(goToMarket);
            } catch (ActivityNotFoundException e) {
                c.startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://play.google.com/store/apps/details?id=" + c.getPackageName())));
            }

        }
        if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey, checkout this new app on understanding our health better. https://play.google.com/store/apps/details?id=" + c.getPackageName());
            sendIntent.setType("text/plain");
            c.startActivity(Intent.createChooser(sendIntent, "alpha_"));

        }
        /*if (id == R.id.myProfile) {
            c.startActivity(new Intent(c, UserProfileActivity.class));
        }
       */

    }
}
