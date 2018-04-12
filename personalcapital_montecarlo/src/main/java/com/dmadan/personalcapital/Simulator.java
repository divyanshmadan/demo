package com.dmadan.personalcapital;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dmadan on 4/12/18.
 */
public class Simulator {
    private Portfolio[] portfolios;
    private Map<Portfolio, SimulatorState> progress;

    private double inflation;
    private long simulations;
    private int periods;

    public Simulator(Portfolio... portfolios) {
        this.portfolios = portfolios;

        this.progress = new HashMap<Portfolio, SimulatorState>(portfolios.length);
        for (Portfolio p : portfolios) {
            SimulatorState ss = new SimulatorState(p.getMean(), p.getStandardDeviation());
            progress.put(p, ss);
        }
        //these variable can either be fetched from DB or properties file in case we want to scale or have diff values basedon marketplace
        this.inflation = 0.035;// 3.5% inflation
        this.periods = 20;//20 yrs
        this.simulations = 10000;
    }

    public void run() {
        for (int i = 0; i < simulations; i++) {
            for (Portfolio p : portfolios) {
                double simResult = p.getInitialInvestment();//starting value
                for (int j = 0; j < periods; j++) {
                    double r = progress.get(p).nextSampleReturn();

                    simResult = (1 + r) * simResult;

                    simResult = (1 - inflation) * simResult;
                }
                progress.get(p).saveSimulationResult(simResult);
            }
        }

        //update portfolios at end of simulations
        for (Portfolio p : portfolios) {
            SimulatorState ss = progress.get(p);
            p.setSimulationMedian(ss.getPercentile(50));
            p.setSimulationBottom10(ss.getPercentile(10));
            p.setSimulationTop10(ss.getPercentile(90));
        }
    }

    public double getInflation() {
        return inflation;
    }

    public void setInflation(double inflation) {
        this.inflation = inflation;
    }

    public long getSimulations() {
        return simulations;
    }

    public void setSimulations(long simulations) {
        this.simulations = simulations;
    }

    public int getPeriods() {
        return periods;
    }

    public void setPeriods(int periods) {
        this.periods = periods;
    }

    //internal class to save state during a simulation run
    private class SimulatorState {
        private NormalDistribution normalDistribution;
        private DescriptiveStatistics stats;

        public SimulatorState(double mean, double standardDeviation) {
            //init distribution for sampling
            //using default Randomizer
            this.normalDistribution = new NormalDistribution(mean, standardDeviation);

            //to store results and compute percentiles
            this.stats = new DescriptiveStatistics();
        }

        public void saveSimulationResult(double simResult) {
            this.stats.addValue(simResult);
        }

        public double nextSampleReturn() {
            return this.normalDistribution.sample();
        }

        public double getPercentile(double n) {
            return this.stats.getPercentile(n);
        }
    }
}
