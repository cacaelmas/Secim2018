package com.cagdas.secim2018.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cagdas.secim2018.Adapters.ElectionDistrictAdapter;
import com.cagdas.secim2018.Adapters.PartyResultListAdapter;
import com.cagdas.secim2018.Classes.CityResult;
import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.Classes.ElectionDistrict;
import com.cagdas.secim2018.Classes.ElectionParty;
import com.cagdas.secim2018.MainActivity;
import com.cagdas.secim2018.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DistrictResultActivity extends AppCompatActivity {
    String selectedElectionDistrictIndex;
    Integer selectedDistrictId;
    ElectionDistrict selectedCity;

    private final Handler loadUIHandler = new Handler();
    JSONObject data;

    CityResult cityResult;
    Runnable loadRunnable;

    RecyclerView districtRV;
    ElectionDistrictAdapter eda;
    private String selectedCityId;

    public static String electionType = "1";// genel 2 özel
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district_result);

        districtRV = findViewById(R.id.district_result_recycler_view);
        selectedCityId = getIntent().getExtras().getString("SELECTED_CITY_ID");

        List<ElectionDistrict> cityDistrictList = new ArrayList<>();
        for(int i = 0; i < MainActivity.electionDistricts.size(); i++) {
            if(MainActivity.electionDistricts.get(i).city_id.equals(selectedCityId)){
                cityDistrictList.add(MainActivity.electionDistricts.get(i));
            }
        }
        eda = new ElectionDistrictAdapter(this, cityDistrictList, new DistrictClickListener());
        districtRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        districtRV.setAdapter(eda);
    }

    /** Listeners */
    private class DistrictClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = districtRV.getChildAdapterPosition(v);
            ElectionDistrict ed = eda.getItemAt(position);
            Toast.makeText(DistrictResultActivity.this, ed.name,
                    Toast.LENGTH_SHORT).show();

            Intent districtIntent = new Intent(DistrictResultActivity.this, ResultDistrictActivity.class);
            districtIntent.putExtra("SELECTED_CITY_ID", ed.id);
            districtIntent.putExtra("SELECTED_DISTRICT_ID", ed.id);
            startActivity(districtIntent);
        }
    }

}
