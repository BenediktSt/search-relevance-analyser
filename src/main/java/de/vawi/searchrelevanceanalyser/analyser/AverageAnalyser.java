package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.TrackingEntry;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AverageAnalyser {
    private List<TrackingEntry> relevanceEntries;
    private Map<Integer, Double> averageValues;

    public AverageAnalyser(List<TrackingEntry> relevanceEntries) {
        this.relevanceEntries = relevanceEntries;
        this.generateAverageMap();
    }

    public Map<Integer, Double> getAverageValues() {
        return averageValues;
    }

    private void generateAverageMap() {
        long total = relevanceEntries.size();

        this.averageValues = new TreeMap<>(relevanceEntries
                .stream()
                .collect(Collectors.groupingBy(
                        TrackingEntry::getRank,
                        Collectors.collectingAndThen(Collectors.summingDouble(x -> 1), x -> x/total))));

    }
}
