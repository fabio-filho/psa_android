package com.ufrj.nce.psa.Objects;

import com.ufrj.nce.psa.Utilities.Functions;
import com.ufrj.nce.psa.Utilities.Values;

/**
 * Created by filhoefilha on 15/04/15.
 */
public class MyThread extends Thread {


    private Boolean stop = false, started = false;
    private int timeLoop = Values.THREAD_TIME_ON_UPDATE;

    public MyThread(int timeLoop) {

        this.timeLoop = timeLoop;

        onCreate();
    }

    public MyThread() {

        onCreate();
    }


    public void onCreate(){


    }


    public void onStart(){


    }


    @Override
    public synchronized void start() {

        if(started) return;

        started = true;

        super.start();

    }

    public Boolean isStarded(){
        return started;
    }


    @Override
    public void run() {

        try {

            onStart();

            while (!stop) {

                runInLoop();
                Functions.sleep(timeLoop);

                super.run();
            }
        }catch(Exception o){

            Functions.Log("run", o.toString());
        }

    }

    public void runInLoop(){

        if(isStoped()) return;

    }


    public Boolean isStoped(){
        return stop;
    }


    public void stopProcess(){
        stop = true;
    }


}
