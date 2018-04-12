package com.dmadan.personalcapital;

import org.junit.Test;

/**
 * Created by dmadan on 4/12/18.
 */
public class RunSimulation {

    @Test
    public void testSimulation(){
        Portfolio aggressive = new Portfolio("Aggressive", 100000, 0.094324, 0.15675);
        Portfolio conservative = new Portfolio("Conservative", 100000, 0.06189, 0.063438);

        Simulator simulator = new Simulator(aggressive, conservative);
        simulator.run();

        System.out.println(aggressive);
        System.out.println(conservative);
    }
}
