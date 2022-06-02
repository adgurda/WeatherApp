package com.example.WeatherApp.controller.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Sort {
    public List<Integer> bubbleSort (List<Integer> list){
        List<Integer> sortedList = new ArrayList<>();
        Integer temp;

       for(int i = 0; i < list.size()-1; i++){
           if(list.get(i) > list.get(i+1)){
               temp = list.get(i);
               list.set(i, list.get(i+1));
               list.set(i+1, temp);
               sortedList.add(list.get(i+1));
           }
       }
       return sortedList;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
