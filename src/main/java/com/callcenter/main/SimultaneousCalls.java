package com.callcenter.main;

/**
 * Created by hitok on 25/06/2017.
 */
public class SimultaneousCalls extends Thread{

    private Dispatcher dispatcher;
    public static int num_thread = 0;
    private Call call;

    public SimultaneousCalls() throws InterruptedException {
        Thread.sleep(10);
        num_thread ++;
    }

    public void setCall(Call call) {
        this.call = call;
    }

    public void setDispatcher(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    @Override
    public void run() {

        if (num_thread <= 10)
            System.out.println(dispatcher.dispatchCall(this.call));
        else
            System.out.println("Todos nuestros operadores se encuentran ocupados, espere que uno se desocupe e intente nuevamente");

    }
}
