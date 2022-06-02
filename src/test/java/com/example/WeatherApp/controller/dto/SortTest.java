package com.example.WeatherApp.controller.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;



class SortTest {

    @Test
    void should_sort_arrayList(){
        //given
        List<Integer> list = new ArrayList<>(List.of(6, 7, 9, 1, 2, 3, 4, 5, 10,8));
        Sort bubbleSort = new Sort();

        //when
        bubbleSort.bubbleSort(list);

        //then
        Assertions.assertThat(list.get(0)).isEqualTo(1);
    }

}