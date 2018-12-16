package de.vawi.searchrelevanceanalyser.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class StatisticalEntry {
    private int rank;
    private double probability;
    private String result;

    public StatisticalEntry(int rank, double probability) {
        this.rank = rank;
        this.probability = probability;
    }

    public StatisticalEntry(int rank, double probability, String result) {
        this(rank, probability);
        this.result = result;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString(){
        return getRank() + ":" + getProbability();
    }
}
