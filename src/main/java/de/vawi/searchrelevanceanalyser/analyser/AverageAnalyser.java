package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AverageAnalyser {
    private List<TrackingEntry> relevanceEntries;
    private List<StatisticalEntry> averageValues;

    public AverageAnalyser(List<TrackingEntry> relevanceEntries) {
        this.relevanceEntries = relevanceEntries;
        this.averageValues = new ArrayList<>();
        this.generateAverageMap();
    }

    public List<StatisticalEntry> getAverageValues() {
        return averageValues;
    }

    private void generateAverageMap() {
        long total = relevanceEntries.size();

        new TreeMap<>(relevanceEntries
                .stream()
                .collect(Collectors.groupingBy(
                        TrackingEntry::getRank,
                        Collectors.collectingAndThen(Collectors.summingDouble(x -> 1), x -> x / total))))
                .forEach((key, value) -> averageValues.add(new StatisticalEntry(key, value)));
    }
}
