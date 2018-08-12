package de.vawi.searchrelevanceanalyser.model;

import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table
public class TrackingEntry {
    private String searchTerm;
    private String result;
    private int rank;
    private int count;

    @PrimaryKey
    private int id;

    public TrackingEntry() {}

    public TrackingEntry(String searchTerm, String result, int rank, int count) {
        this.searchTerm = searchTerm;
        this.result = result;
        this.rank = rank;
        this.count = count;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return searchTerm + " - Rang " + rank + ": " + result;
    }
}
