/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package senatebusproblem;

import java.util.Random;

/**
 *
 * @author pc
 */
public class ExponentialDistributionGenerator {
    float mean;
    private static Random random = new Random();

    public ExponentialDistributionGenerator(float mean) {
        this.mean = mean;
    }
    
    public long sample() {
        float lamda = 1 / this.mean;
        return Math.round(-Math.log(1 - random.nextFloat()) / lamda);
    }
}
