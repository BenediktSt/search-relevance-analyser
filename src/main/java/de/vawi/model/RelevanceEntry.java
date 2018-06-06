package de.vawi.model;

public class RelevanceEntry {
    private String searchTerm;
    private String result;
    private int rank;

    public RelevanceEntry() {}

    public RelevanceEntry(String searchTerm, String result, int rank) {
        this.searchTerm = searchTerm;
        this.result = result;
        this.rank = rank;
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

    @Override
    public String toString() {
        return searchTerm + " - Rang " + rank + ": " + result;
    }
}
