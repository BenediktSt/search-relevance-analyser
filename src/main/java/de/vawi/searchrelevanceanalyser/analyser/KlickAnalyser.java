package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class KlickAnalyser {
    private List<TrackingEntry> trackingEntries;
    private Map<String, List<StatisticalEntry>> searchTermValues;

    public KlickAnalyser(List<TrackingEntry> trackingEntries) {
        this.trackingEntries = trackingEntries;
        this.searchTermValues = new TreeMap<>();
        this.generateMap();
    }

    private void generateMap() {
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
            this.searchTermValues.put(searchTerm, stats);
        });
    }

    public Map<String, List<StatisticalEntry>> getSearchTermValues() {
        return searchTermValues;
    }
}
