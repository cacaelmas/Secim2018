package com.cagdas.secim2018.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.R;

import java.util.List;

public class ElectionCityAdapter extends RecyclerView.Adapter<ElectionCityAdapter.ElectionCityHolder> {
    public static class ElectionCityHolder extends RecyclerView.ViewHolder {
        protected TextView idTV;
        protected TextView nameTV;

        private ElectionCityHolder(View v){
            super(v);
            this.idTV = v.findViewById(R.id.city_id_text_view);
            this.nameTV = v.findViewById(R.id.city_name_text_view);
        }
    }

    private Context context;
    private List<ElectionCity> electionCityList;
    private View.OnClickListener listener;

    public ElectionCityAdapter(Context context, List<ElectionCity> electionCities, View.OnClickListener listener){
        this.context = context;
        this.electionCityList = electionCities;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElectionCityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.election_city_single_view, null);
        v.setOnClickListener(listener);
        ElectionCityHolder ech = new ElectionCityHolder(v);
        return ech;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionCityHolder holder, int position) {
        holder.idTV.setText(electionCityList.get(position).id);
        holder.nameTV.setText(electionCityList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return this.electionCityList.size();
    }

    public ElectionCity getItemAt(int position) {
        return this.electionCityList.get(position);
    }

}
