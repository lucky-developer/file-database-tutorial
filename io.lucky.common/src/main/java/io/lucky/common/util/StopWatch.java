package io.lucky.common.util;

public class StopWatch {

    private long stime;
    private long etime;

    public StopWatch(){
        stime = System.currentTimeMillis();
        etime = 0;
    }

    public void start(){
        stime = System.currentTimeMillis();
        etime = 0;
    }

    public void reset(){
        start();
    }

    public long stop(){
        etime = System.currentTimeMillis();
        return etime - stime;
    }

}
