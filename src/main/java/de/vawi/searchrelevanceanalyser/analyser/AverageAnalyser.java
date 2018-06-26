package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class AverageAnalyser {
    private List<TrackingEntry> trackingEntries;
    private List<StatisticalEntry> averageValues;

    public AverageAnalyser(List<TrackingEntry> trackingEntries) {
        this.trackingEntries = trackingEntries;
        this.averageValues = new ArrayList<>();
        this.calculateDistribution();
    }

    public List<StatisticalEntry> getAverageValues() {
        return averageValues;
    }

    private void calculateDistribution() {
        long total = trackingEntries.size();

        new TreeMap<>(trackingEntries
                .stream()
                .collect(Collectors.groupingBy(
                        TrackingEntry::getRank,
                        Collectors.collectingAndThen(Collectors.summingDouble(x -> 1), x -> x / total))))
                .forEach((key, value) -> averageValues.add(new StatisticalEntry(key, value)));
    }
}
