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
public class Rider extends Thread{
    
    @Override
    public void run() {
        enteringToBoardingArea();
        Config.riderCount.incrementAndGet();
        newRiderEnteringToBoardingArea();
        waitForBus();
        boardBus();
    }

//    a rider will enter to the boarding area only
//    the bus is not arriving or already parked
//    ridercount is not exeeds 50
    private void enteringToBoardingArea() {
        try {
            Config.multiplex.acquire();
            Config.mutex.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    rider is going to boarding area
    private void newRiderEnteringToBoardingArea() {
        Config.mutex.release();
        System.out.printf(Config.ANSI_CYAN + "Rider %d is arrived\n", this.getId());
    }
    
//    riders are waiting for the next bus
    private void waitForBus() {
        try {
            Config.bus.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    waited riders can get on to the bus
    private void boardBus() {
        Config.multiplex.release();
        if (Config.riderCount.decrementAndGet() == 0) {
            notifyBusToDepart();
            System.out.printf(Config.ANSI_GREEN + "Last Rider %d is boarded\n", this.getId());
        } else {
            System.out.printf(Config.ANSI_YELLOW + "Rider %d is boarded\n", this.getId());
            notifyTheNextRider();
        }
    }
    
//    last rider says that bus is now full can dispatch
    private void notifyBusToDepart() {
        Config.allAbord.release();
    }
    
//    all the riders except the last one shoud in the bus
    private void notifyTheNextRider() {
        Config.bus.release();
    }
}
