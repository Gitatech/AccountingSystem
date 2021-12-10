package com.prokopchyk.service;

import com.prokopchyk.building.Flat;
import com.prokopchyk.building.flatComparator.FlatAreaCompare;
import com.prokopchyk.building.flatComparator.FlatPersonsCompare;

import java.util.ArrayList;
import java.util.List;

public class FlatService {
    private static FlatService flatService;
    private FlatService(){};
    public synchronized static FlatService getFlatService(){
        if(flatService == null)
            flatService = new FlatService();
        return flatService;
    }
    public List<Integer> compareFlats(Flat flat1, Flat flat2){
        List<Integer> answer = new ArrayList<Integer>();
        answer.add(new FlatAreaCompare().compare(flat1,flat2));
        answer.add(new FlatPersonsCompare().compare(flat1,flat2));
        return answer;
    }
}
