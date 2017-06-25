package com.callcenter.main;

import java.util.Random;

/**
 * Created by hitok on 24/06/2017.
 */
public class Call {
    int caller;
    int duration;

    public Call(int caller) {
        Random rn = new Random();
        // Cada llamada puede durar un tiempo aleatorio entre 5 y 10 segundos
        this.caller = caller;
        this.duration = rn.nextInt(10) +5;
    }

}
