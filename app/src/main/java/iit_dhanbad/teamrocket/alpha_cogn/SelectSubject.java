package iit_dhanbad.teamrocket.alpha_cogn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class SelectSubject extends Activity {
    GridView grid;
    String[] web = {
            "Pethood",
            "Photography",
            "Motherhood",
            "Science",
            "Fine Art",
            "Fitness",
            "Music",
            "Film Making",
            "Computer Science",
            "Nature",
            "Social Science",
            "International Languages",
            "Mathematics"

    } ;
    int[] imageId = {
            R.drawable.pethood,
            R.drawable.photography,
            R.drawable.motherhood,
            R.drawable.science,
            R.drawable.fine_art,
            R.drawable.fitness,
            R.drawable.music,
            R.drawable.film_making,
            R.drawable.computer_programming,
            R.drawable.nature,
            R.drawable.social_science,
            R.drawable.international_languages,
            R.drawable.mathematics,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_subject);

        customGrid adapter = new customGrid(SelectSubject.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                callMainActivity(web[+position]);

            }
        });

    }
    public void callMainActivity(String subjectVal){
        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("subject", subjectVal.replace(" ","%25"));
        intent.putExtras(bundle);
        startActivity(intent);
    }

}
