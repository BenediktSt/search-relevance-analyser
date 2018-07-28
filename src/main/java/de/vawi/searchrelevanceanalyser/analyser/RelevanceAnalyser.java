package de.vawi.searchrelevanceanalyser.analyser;

import de.vawi.searchrelevanceanalyser.model.StatisticalEntry;

import java.util.ArrayList;
import java.util.List;

public class RelevanceAnalyser {

    public static List<Integer> getRelevantRanksForTerm(
            List<StatisticalEntry> termStatistics,
            List<StatisticalEntry> averageStatistics){

        List<Integer> significantRanks = new ArrayList<>();
        averageStatistics.forEach(averagePerRank -> {
            int currentRank = averagePerRank.getRank();
            if (getProbabilityByRank(currentRank, termStatistics) > averagePerRank.getProbability()) {
                significantRanks.add(currentRank);
            }
        });
        return significantRanks;
    }

    private static double getProbabilityByRank(int rank, List<StatisticalEntry> entries) {
        double returnValue = 0;
        for (StatisticalEntry entry : entries) {
            if (entry.getRank() == rank)
                returnValue = entry.getProbability();
        }
        return returnValue;
    }
}
