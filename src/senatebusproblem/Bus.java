/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

/**
 *
 * @author pc
 */
public class Bus extends Thread{
    
    @Override
    public void run(){
        busIsArriving();
        if(Config.riderCount.get() > 0){
            busIsArrived();
            waitForAllRiders();
        }
        busDispatch();
    }

//    when bus is arriving to the bus stop
    private void busIsArriving() {
        try {
            Config.mutex.acquire();
            System.out.printf(Config.ANSI_BLUE+"Bus %d is arriving\n" , this.getId());
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
    }
    
//    when bus is arrived
    private void busIsArrived() {
        Config.bus.release();
        System.out.printf(Config.ANSI_PURPLE + "Bus %d is arrived\n", this.getId());
    }

//    when bus is leaved
    private void busDispatch() {
        Config.mutex.release();
        System.out.printf(Config.ANSI_RED + "Bus %d dispatched\n", this.getId());
    }

//    bus is waiting for all the riders in the boarding area
    private void waitForAllRiders() {
        try{
            Config.allAbord.acquire();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    
}
