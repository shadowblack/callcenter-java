package com.callcenter.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.Stack;

/**
 * Created by hitok on 24/06/2017.
 */
public class Dispatcher {

    public static ArrayList<Employee> operadores = new ArrayList<Employee>();
    public static ArrayList<Employee> supervisores = new ArrayList<Employee>();
    public static ArrayList<Employee> directores = new ArrayList<Employee>();
    public static Stack<Employee> stack = new Stack<Employee>();

    public  String dispatchCall(Call c){

        String str = "";

        //  operadores;
        for (Employee e : operadores) {
            if (e.status == true) {
                stack.add(e);
                e.status = false;

                Date date = new Date();
                long n =  date.getTime() ;

                return str = "Operador " + stack.peek().number
                        + " manejo la llamada iniciando a los "+n+"mll finalizando en " + this.waitCall(c.duration) + " segundos";

            }
        }

        for (Employee e : supervisores) {
            if (e.status == true) {
                stack.add(e);

                Date date = new Date();
                long n =  date.getTime() ;

                e.status = false;
                return str = "Manager " + stack.peek().number
                        + " manejo la llamada iniciando a los "+n+"mll finalizando en " +  this.waitCall(c.duration) + " segundos";
            }
        }

        for (Employee e : directores) {
            if (e.status == true) {
                stack.add(e);

                Date date = new Date();
                long n =  date.getTime() ;

                e.status = false;
                return str = "Director " + stack.peek().number
                        + " manejo la llamada iniciando a los "+n+"mll finalizando en " +  this.waitCall(c.duration) + " segundos";
            }
        }

        return str = "El sistema está ocupado, por favor, espere";
    }

    private int waitCall(int sec){
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        return sec;
    }
}
