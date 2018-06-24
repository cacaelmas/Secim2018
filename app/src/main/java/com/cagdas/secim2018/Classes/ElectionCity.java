package com.cagdas.secim2018.Classes;

import java.util.ArrayList;
import java.util.List;

public class ElectionCity {
    public List<ElectionDistrict> districtList;
    public String id;
    public String name;

    public ElectionCity(String id, String name){
        this.districtList = new ArrayList<>();
        this.id = id;
        this.name = name;
    }
}
