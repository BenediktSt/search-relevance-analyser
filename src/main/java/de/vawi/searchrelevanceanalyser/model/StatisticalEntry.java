package de.vawi.searchrelevanceanalyser.model;

public class StatisticalEntry {
    private int rank;
    private double probability;

    public StatisticalEntry(int rank, double probability) {
        this.rank = rank;
        this.probability = probability;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString(){
        return getRank() + ":" + getProbability();
    }
}
