/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author pc
 */
public class Config {
    public static final float MEAN_BUS_ARRIVAL = 60f * 20;  // 20 minutes
    public static final float MEAN_RIDER_ARRIVAL = 30f;   // 30 seconds
    private static final int MAX_RIDERS_IN_BUS = 50;
    
    public static final AtomicInteger riderCount = new AtomicInteger();
    
    public static final Semaphore mutex = new Semaphore(1);    //semaphore for late arrivals
    public static final Semaphore bus = new Semaphore(0);
    public static final Semaphore allAbord = new Semaphore(0);    
    
    public static final Semaphore multiplex = new Semaphore(MAX_RIDERS_IN_BUS);
    
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
}
