package com.cagdas.secim2018.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.Classes.ElectionDistrict;
import com.cagdas.secim2018.R;

import java.util.List;

public class ElectionDistrictAdapter extends RecyclerView.Adapter<ElectionDistrictAdapter.ElectionDistrictHolder>{
    public static class ElectionDistrictHolder extends RecyclerView.ViewHolder {
        protected TextView idTV;
        protected TextView nameTV;

        private ElectionDistrictHolder(View v){
            super(v);
            this.idTV = v.findViewById(R.id.city_id_text_view);
            this.nameTV = v.findViewById(R.id.city_name_text_view);
        }
    }

    private Context context;
    private List<ElectionDistrict> electionDistrictList;
    private View.OnClickListener listener;

    public ElectionDistrictAdapter(Context context, List<ElectionDistrict> electionDistrictList, View.OnClickListener listener){
        this.context = context;
        this.electionDistrictList = electionDistrictList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ElectionDistrictHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.election_city_single_view, null);
        v.setOnClickListener(listener);
        ElectionDistrictHolder ech = new ElectionDistrictHolder(v);
        return ech;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionDistrictHolder holder, int position) {
        holder.idTV.setText(electionDistrictList.get(position).id);
        holder.nameTV.setText(electionDistrictList.get(position).name);
    }

    @Override
    public int getItemCount() {
        return this.electionDistrictList.size();
    }

    public ElectionDistrict getItemAt(int position) {
        return this.electionDistrictList.get(position);
    }

}
