package com.cagdas.secim2018.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cagdas.secim2018.Classes.ElectionCity;
import com.cagdas.secim2018.Classes.ElectionParty;
import com.cagdas.secim2018.R;

import java.util.List;

public class PartyResultListAdapter extends RecyclerView.Adapter<PartyResultListAdapter.ElectionPartyHolder> {
    public static class ElectionPartyHolder extends RecyclerView.ViewHolder {
        protected TextView nameTV;
        protected TextView countTV;

        private ElectionPartyHolder(View v){
            super(v);
            this.nameTV = v.findViewById(R.id.party_name);
            this.countTV = v.findViewById(R.id.party_vote_count);
        }
    }

    private Context context;
    private List<ElectionParty> electionPartyList;
    private View.OnClickListener listener;

    public PartyResultListAdapter(Context context, List<ElectionParty> partyList){
        this.context = context;
        this.electionPartyList = partyList;
    }

    @NonNull
    @Override
    public ElectionPartyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_result_single_view, null);
        ElectionPartyHolder eph = new ElectionPartyHolder(v);
        return eph;
    }

    @Override
    public void onBindViewHolder(@NonNull ElectionPartyHolder holder, int position) {
        holder.nameTV.setText(electionPartyList.get(position).name);
        holder.countTV.setText(electionPartyList.get(position).voteCount.toString());
    }

    @Override
    public int getItemCount() {
        return electionPartyList.size();
    }


}
