package com.cagdas.secim2018.Activities;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cagdas.secim2018.Adapters.PartyResultListAdapter;
import com.cagdas.secim2018.Classes.CityResult;
import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.Classes.ElectionParty;
import com.cagdas.secim2018.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Iterator;

public class ResultDistrictActivity extends AppCompatActivity {
    String selectedElectionCityIndex;
    String selectedDistrictId;
    ElectionCity selectedCity;

    private final Handler loadUIHandler = new Handler();
    JSONObject data;

    CityResult cityResult;
    Runnable loadRunnable;

    RecyclerView partyRV;
    PartyResultListAdapter prla;

    public static String electionType = "1";// genel 2 özel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_district);

        selectedDistrictId = getIntent().getExtras().getString("SELECTED_DISTRICT_ID");
        partyRV = findViewById(R.id.city_result_recycler_view);
        loadRunnable = new Runnable() {
            @Override
            public void run() {
                TextView cityTV = findViewById(R.id.selected_city_name);
                TextView openBoxTV = findViewById(R.id.open_box_count);
                TextView totalBoxTV = findViewById(R.id.totalBoxCount);

                TextView validTV = findViewById(R.id.valid_vote);
                TextView voterCountTv = findViewById(R.id.voter_count);

                TextView lastUpdateTV = findViewById(R.id.last_update_text_view);

                Button toggleButton = findViewById(R.id.change_election_type_button);
                if(electionType.equals("1")){
                    toggleButton.setText("GENEL SECIM SONUCLARI");
                }
                else {
                    toggleButton.setText("CUMHURBASKANLIGI SECIM SONUCLARI");
                }

                cityTV.setText(cityResult.name);
                openBoxTV.setText(cityResult.open_box_count.toString());
                totalBoxTV.setText(cityResult.box_count.toString());

                validTV.setText(cityResult.total_valid_vote.toString());
                voterCountTv.setText(cityResult.voter_count.toString());
                lastUpdateTV.setText("Updated At: " + cityResult.updatedAt);

                prla = new PartyResultListAdapter(ResultDistrictActivity.this, cityResult.partyResultList);
                partyRV.setLayoutManager(new LinearLayoutManager(ResultDistrictActivity.this, LinearLayoutManager.VERTICAL, false));
                partyRV.setAdapter(prla);
            }
        };
        getJsonResult();
    }
    public void getJsonResult(){
        new AsyncTask<Void, Void, Void>() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected Void doInBackground(Void... params) {
                try {
                    URL url = new URL("https://secim-api.adilsecim.net/" + electionType + "/district/" + selectedDistrictId +".json");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod("GET");
                    connection.connect();



                    BufferedReader reader =
                            new BufferedReader(new InputStreamReader(connection.getInputStream()));

                    StringBuffer json = new StringBuffer(1024);
                    String tmp = "";

                    while((tmp = reader.readLine()) != null)
                        json.append(tmp).append("\n");
                    reader.close();

                    JSONArray ja = new JSONArray(json.toString());
                    data = ja.getJSONObject(0);
//                    data = new JSONObject(json.toString());
//
//                    if(data.getInt("cod") != 200) {
//                        System.out.println("Cancelled");
//                        return null;
//                    }


                } catch (Exception e) {

                    System.out.println("Exception "+ e.getMessage());
                    return null;
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void Void) {
                if(data!=null){
                    try {
                        //Log.d("my weather received",data.toString());
                        //JSONObject m = new JSONObject(data.get("main").toString());
                        cityResult = new CityResult();
                        cityResult.id = data.get("id").toString();
                        cityResult.name = data.get("name").toString();
                        cityResult.box_count = Integer.parseInt(data.get("box_count").toString());
                        cityResult.open_box_count = Integer.parseInt(data.get("open_box_count").toString());
                        cityResult.voter_count = Integer.parseInt(data.get("voter_count").toString());
                        cityResult.total_valid_vote = Integer.parseInt(data.get("total_valid_vote").toString());

                        JSONObject results = new JSONObject(data.get("results").toString());
                        cityResult.updatedAt = results.get("updated_at").toString();
                        Iterator<String> iter = results.keys();
                        while(iter.hasNext()){
                            String key = iter.next();
                            Integer voteCount;
                            try  {
                                voteCount = Integer.parseInt(results.get(key).toString());
                                cityResult.partyResultList.add(new ElectionParty(key, voteCount));
                            } catch (Exception ex) {
                                voteCount = 0;
                            }

                        }
                        Collections.sort(cityResult.partyResultList);
                        loadUIHandler.postDelayed(loadRunnable, 200);


                    }catch (Exception e) {
                        return ;
                    }


                }

            }
        }.execute();
    }

    public void change_election_type_click(View view) {
        if(electionType.equals("1")) {
            electionType = "2";
            getJsonResult();
        }
        else {
            electionType = "1";
            getJsonResult();
        }
    }
}
