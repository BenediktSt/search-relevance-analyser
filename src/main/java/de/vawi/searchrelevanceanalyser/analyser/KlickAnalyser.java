package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.SearchRankins;
import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;

import java.util.*;
import java.util.stream.Collectors;

public class KlickAnalyser {
    private List<TrackingEntry> trackingEntries;
    private List<SearchRankins> searchRankings;

    public KlickAnalyser(List<TrackingEntry> trackingEntries) {
        this.trackingEntries = trackingEntries;
        this.searchRankings = new ArrayList<>();
        this.generateList();
        this.searchRankings.sort(Comparator.comparing(SearchRankins::getSearchTerm));
    }

    private void generateList() {
        List<String> searchTerms = this.trackingEntries
                .stream()
                .map(TrackingEntry::getSearchTerm)
                .collect(Collectors.toList());

        searchTerms.forEach(searchTerm -> {
            List<StatisticalEntry> stats = new ArrayList<>();

            long total = this.trackingEntries
                    .stream()
                    .filter(track -> track.getSearchTerm().equals(searchTerm))
                    .count();

            this.trackingEntries
                    .stream()
                    .filter(track -> track.getSearchTerm().equals(searchTerm))
                    .collect(Collectors.groupingBy(
                            TrackingEntry::getRank,
                            Collectors.collectingAndThen(Collectors.summingDouble(x -> 1), x -> x / total)))
                .forEach((key, value) -> stats.add(new StatisticalEntry(key, value)));
            this.searchRankings.add(new SearchRankins(searchTerm, stats));
        });
    }

    public List<SearchRankins> getSearchRankings() {
        return this.searchRankings;
    }
}
