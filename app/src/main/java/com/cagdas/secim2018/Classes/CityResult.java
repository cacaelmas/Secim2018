package com.cagdas.secim2018.Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CityResult {
    public String id;
    public String name;
    public Integer box_count;
    public Integer open_box_count;
    public Integer total_valid_vote;
    public Integer voter_count;
    public List<ElectionParty> partyResultList;
    public String updatedAt;

    public  CityResult(){
        this.partyResultList = new ArrayList<>();
    }
}
