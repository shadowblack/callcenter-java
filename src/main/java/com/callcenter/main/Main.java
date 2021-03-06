package com.callcenter.main;


import java.util.concurrent.ExecutionException;

/**
 * Created by hitok on 24/06/2017.
 */
public class Main {

    public static Dispatcher dispatcher = new Dispatcher();

    public static void main(String[] args)  {

        try {
            /**
             * Debe tener un test unitario donde lleguen 10 llamadas.
             * */

            //test10Call

            /**
             * Dar alguna solución sobre qué pasa con una llamada cuando no
             hay ningún empleado libre.
             * */
            //testNoAvailabilityCall();


            /**
             * Dar alguna solución sobre qué pasa con una llamada cuando
             entran más de 10 llamadas concurrentes.
             * */
            testMore10Call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    /**
     * @case normal cuando hay llamadas y hay empleados
     * */

    public static void test10Call()throws InterruptedException, ExecutionException {
        makeEmployessCaseA();
        try {
            makeCall();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * @case cuando hay mas de 10 llamadas
     * */
    public static void testMore10Call()throws InterruptedException, ExecutionException {
        makeEmployessCaseA();
        try {
            makeMore10Call();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * @case no hay diponibilidad
     * */
    public static void testNoAvailabilityCall()throws InterruptedException, ExecutionException {
        makeEmployessCaseB();
        try {
            makeCall();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void makeEmployessCaseB(){

        // operadores
        dispatcher.operadores.add( new Employee(1,  false));
        dispatcher.operadores.add( new Employee(2, false));

        // supervisores
        dispatcher.supervisores.add( new Employee(3, false));

        // empleados
        dispatcher.directores.add( new Employee(4, false));
    }

    private static void makeEmployessCaseA(){

        // operadores
        dispatcher.operadores.add( new Employee(1,  false));
        dispatcher.operadores.add( new Employee(2, true));

        // supervisores
        dispatcher.supervisores.add( new Employee(3, true));

        // empleados
        dispatcher.directores.add( new Employee(4, true));
    }

    /**
     * Armando 10 llamadas
     * */
    private static void makeCall() throws InterruptedException {

        for (int i = 0; i < 10; i++){
            SimultaneousCalls calling = new SimultaneousCalls();
            calling.setDispatcher(dispatcher);
            calling.setCall(new Call(i));
            calling.setPriority(10 - i);
            calling.start();
            System.out.println("Llamadas entrantes" + calling.num_thread);
        }
    }

    /**
     * Armando 10 llamadas
     * */
    private static void makeMore10Call() throws InterruptedException {

        for (int i = 0; i < 11; i++){
            SimultaneousCalls calling = new SimultaneousCalls();
            calling.setDispatcher(dispatcher);
            calling.setCall(new Call(i));
            //calling.setPriority(11 - i);
            calling.start();
            System.out.println("Llamadas entrantes" + calling.num_thread);
        }
    }

}
