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
@SuppressWarnings("InfiniteLoopStatement")
public class SenateBusProblem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Thread busGenerator = new Thread(()->{
            ExponentialDistributionGenerator exp = new ExponentialDistributionGenerator(Config.MEAN_BUS_ARRIVAL);
            while(true){
                try{
                    Thread.sleep(exp.sample());
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                new Bus().start();
            }
        });
        
        Thread riderGenerator = new Thread(()->{
            ExponentialDistributionGenerator exp = new ExponentialDistributionGenerator(Config.MEAN_RIDER_ARRIVAL);
            while(true){
                try{
                    Thread.sleep(exp.sample());
                }
                catch(InterruptedException e){
                    e.printStackTrace();
                }
                new Rider().start();
            }
        });
        
        busGenerator.start();
        riderGenerator.start();
    }
    
}
