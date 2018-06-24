package com.cagdas.secim2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.cagdas.secim2018.Activities.CityResultActivity;
import com.cagdas.secim2018.Adapters.ElectionCityAdapter;
import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.Classes.ElectionDistrict;
import com.cagdas.secim2018.Providers.ElectionCityProvider;
import com.cagdas.secim2018.Providers.ElectionDistrictProvider;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<ElectionCity> electionCityList;
    public static List<ElectionDistrict> electionDistricts;

    RecyclerView cityListView;
    ElectionCityAdapter eca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        electionCityList = new ArrayList<>();
        electionDistricts = new ArrayList<>();

        cityListView = findViewById(R.id.city_list_view);

        loadCitiesAndDistricts();
        loadUI();
    }

    private void loadCitiesAndDistricts(){
        ElectionDistrictProvider.getDistricts(electionDistricts);
        ElectionCityProvider.getCities(electionCityList);
    }

    private void loadUI() {
        eca = new ElectionCityAdapter(this, this.electionCityList, new CityClickListener());
        cityListView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        cityListView.setAdapter(eca);
    }
    /** Listeners */
    private class CityClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int position = cityListView.getChildAdapterPosition(v);
            ElectionCity ec = eca.getItemAt(position);
            Toast.makeText(MainActivity.this, ec.name,
                    Toast.LENGTH_SHORT).show();

            Intent districtIntent = new Intent(MainActivity.this, CityResultActivity.class);
            districtIntent.putExtra("SELECTED_CITY_ID", ec.id);
            startActivity(districtIntent);
        }
    }
}
