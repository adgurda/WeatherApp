package com.example.WeatherApp.controller.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



class SortTest {

    @Test
    void should_sort_arrayList(){
        //given
        List<Integer> list = new ArrayList<>(List.of(6, 7, 9, 1, 2, 3, 4, 5, 8));
        Sort sort = new Sort();


        //when
        var result = sort.bubbleSort(list);

        //then
        Assertions.assertThat(result).isEqualTo(new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9)));
        //Assertions.assertThat(result.get(8)).isEqualTo(9);
    }

}