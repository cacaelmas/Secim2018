package com.cagdas.secim2018.Classes;

public class ElectionParty implements Comparable<ElectionParty> {
    public String name;
    public Integer voteCount;

    public ElectionParty(String name, Integer voteCount) {
        this.name = name;
        this.voteCount = voteCount;
    }

    @Override
    public int compareTo(ElectionParty ep){
        if( this.voteCount > ep.voteCount)
            return -1;
        else if(this.voteCount < ep.voteCount)
            return  1;
        else
            return 0;
    }
}
