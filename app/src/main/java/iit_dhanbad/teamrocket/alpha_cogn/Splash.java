package iit_dhanbad.teamrocket.alpha_cogn;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class Splash extends AppCompatActivity {

    Intent startNext;
    Thread thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //waits for 2.5 seconds
                    Thread.sleep(2500);
                    if (!thread.isInterrupted()) {

                        startNext = new Intent(Splash.this, MainActivity.class);
                        startActivity(startNext);

                        finish();

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        thread.interrupt();
    }


}
