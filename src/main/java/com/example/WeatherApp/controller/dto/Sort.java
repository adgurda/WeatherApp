package com.example.WeatherApp.controller.dto;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Sort {
    public List<Integer> bubbleSort (List<Integer> list){
        Integer temp;

       for(int i = 0; i < list.size()-1; i++){
           for(int j = 0; j<list.size()-1-i; j++)
           if(list.get(j).compareTo(list.get(j+1)) > 0){
               temp = list.get(j);
               list.set(j, list.get(j+1));
               list.set(j+1, temp);
           }
       }
       return list;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
