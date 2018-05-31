package de.vawi.model;

public class RelevanceEntry {
    private String searchTerm;
    private String result;
    private String rank;

    public RelevanceEntry() {}

    public RelevanceEntry(String searchTerm, String result, String rank) {
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

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return searchTerm + " - " + rank + ": " + result;
    }
}
