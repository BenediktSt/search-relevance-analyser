package de.vawi.searchrelevanceanalyser.model;

import java.util.List;

public class SearchRankins {

    private String searchTerm;
    private List<StatisticalEntry> rankings;

    public SearchRankins(String searchTerm, List<StatisticalEntry> rankings) {
        this.searchTerm = searchTerm;
        this.rankings = rankings;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public List<StatisticalEntry> getRankings() {
        return rankings;
    }
}
