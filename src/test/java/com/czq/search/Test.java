package com.czq.search;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(5);
        list.add(7);

        for (Integer num : list) {
            list.remove(num);
        }
        System.out.println(list.toString());
    }
}
