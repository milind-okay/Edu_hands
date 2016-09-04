package iit_dhanbad.teamrocket.alpha_cogn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import iit_dhanbad.teamrocket.alpha_cogn.user.UserProfile;
import iit_dhanbad.teamrocket.alpha_cogn.utils.InternetConnectionDetector;

public class FilterActivites extends AppCompatActivity {
    AutoCompleteTextView subject,topic;
    String subjectVal,topicVal,url;
    InternetConnectionDetector internetConnectionDetector;
    private ArrayAdapter<String> subAdapt,topicAdapt;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_activites);
        sendRequest("subject");
       subject = (AutoCompleteTextView) findViewById(R.id.subject);
       topic = (AutoCompleteTextView) findViewById(R.id.topic);
        subject.setThreshold(1);
        topic.setThreshold(1);
        subject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subjectVal =  parent.getItemAtPosition(position).toString();

                Log.d("m_degree",parent.getItemAtPosition(position).toString());
            }
        });
        topic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                topicVal =  parent.getItemAtPosition(position).toString();

                Log.d("m_degree",parent.getItemAtPosition(position).toString());
            }
        });
        submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               callMainActivity();
            }
        });
    }
   public void callMainActivity(){
       Intent intent = new Intent(this, MainActivity.class);
       Bundle bundle = new Bundle();
       bundle.putString("subject", subjectVal);
       intent.putExtras(bundle);
       startActivity(intent);
   }



    private void sendRequest(final String item) {

        if(item.equals("subject")){
            url = "http://alphacogn.net23.net/all_subjects.php";
        }else{
            url = "";
        }
        Log.d("atsendrequest ", url+ "ok");
        internetConnectionDetector = new InternetConnectionDetector(this);
        if (internetConnectionDetector.isConnectingToInternet()) {

            StringRequest stringRequest = new StringRequest(url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Toast.makeText(getApplicationContext(),response + "okat",Toast.LENGTH_LONG).show();
                            try {
                                ParseResult(item,response);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage() + "\n Try Again !", Toast.LENGTH_LONG).show();
                        }
                    });

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        } else {
            Toast.makeText(getApplicationContext(), "No Internet connection", Toast.LENGTH_LONG).show();
        }
    }
    private void ParseResult(String item,String response) throws JSONException {

            sendRequest("subject");
        JSONObject jsonObject = new JSONObject(response);
        JSONArray jsonArray = jsonObject.getJSONArray("server_response");
        String content[] = new String[jsonArray.length()];
        for(int i=0;i<jsonArray.length();i++){
            content[i] = jsonArray.getJSONObject(i).getString("subjects");
            Log.d(item,content[i]);
        }
        if(item.equals("subject")){
            subAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, content);
            subject.setAdapter(subAdapt);

        }else{
            topicAdapt = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, content);
            topic.setAdapter(topicAdapt);
        }
    }
}
