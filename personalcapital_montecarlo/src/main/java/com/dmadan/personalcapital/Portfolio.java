package com.dmadan.personalcapital;

/**
 * Created by dmadan on 4/12/18.
 */
public class Portfolio {
    private String portfolioName;
    private double initialInvestment;
    private double mean;
    private double standardDeviation;

    private double simulationMedian20year;
    private double simulationTop10;
    private double simulationBottom10;

    public Portfolio(String name, double initialInvestment, double mean, double standardDeviation) {
        this.portfolioName=name;
        this.initialInvestment=initialInvestment;
        this.mean=mean;
        this.standardDeviation=standardDeviation;
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public double getInitialInvestment() {
        return initialInvestment;
    }

    public void setInitialInvestment(double initialInvestment) {
        this.initialInvestment = initialInvestment;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(double standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    public double getSimulationMedian20year() {
        return simulationMedian20year;
    }

    public void setSimulationMedian20year(double simulationMedian20year) {
        this.simulationMedian20year = simulationMedian20year;
    }

    public double getSimulationTop10() {
        return simulationTop10;
    }

    public void setSimulationTop10(double simulationTop10) {
        this.simulationTop10 = simulationTop10;
    }

    public double getSimulationBottom10() {
        return simulationBottom10;
    }

    public void setSimulationBottom10(double simulationBottom10) {
        this.simulationBottom10 = simulationBottom10;
    }

    @Override
    public String toString() {
        return "Portfolio [portfolioName=" + portfolioName + ", initialInvestment="
                + initialInvestment + ", return=" + mean + ", risk="
                + standardDeviation + ",20th year Median=" + simulationMedian20year
                + ", 10% Best=" + simulationTop10
                + ", 10% Worst=" + simulationBottom10 + "]";
    }
}

