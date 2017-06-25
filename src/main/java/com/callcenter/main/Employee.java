package com.callcenter.main;

/**
 * Created by hitok on 24/06/2017.
 */
public class Employee {
    int number;// 0-9 are respondents; 10-19 are managers; 20-29 are//
    //directors;
    boolean status;

    public Employee(int num, boolean status) {
        this.number = num;
        this.status = status;
    }
}
