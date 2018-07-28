package de.vawi.searchrelevanceanalyser;

import de.vawi.searchrelevanceanalyser.analyser.AverageAnalyser;
import de.vawi.searchrelevanceanalyser.analyser.KlickAnalyser;
import de.vawi.searchrelevanceanalyser.analyser.RelevanceAnalyser;
import de.vawi.searchrelevanceanalyser.dao.CassandraSerializer;
import de.vawi.searchrelevanceanalyser.dao.CsvDeserializer;
import de.vawi.searchrelevanceanalyser.dao.TrackingEntryRepository;
import de.vawi.searchrelevanceanalyser.model.TrackingEntry;
import de.vawi.searchrelevanceanalyser.processor.TrackingEntryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication()
public class SearchRelevanceAnalyserApplication implements CommandLineRunner {

    @Autowired
    private TrackingEntryRepository trackingEntryRepository;

    public static void main(String[] args) {
        SpringApplication.run(SearchRelevanceAnalyserApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        CsvDeserializer importer = new CsvDeserializer();
        TrackingEntryProcessor processor = new TrackingEntryProcessor();
        CassandraSerializer serializer = new CassandraSerializer(trackingEntryRepository);

        List<TrackingEntry> entryList = importer.readData("sample-data-piwik.csv");

        List<TrackingEntry> saveList = new ArrayList<>();

        entryList.forEach(entry -> {
            try {
                saveList.add(processor.process(entry));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        // serializer.serialize(saveList);
        AverageAnalyser average = new AverageAnalyser(saveList);
        System.out.println(average.getAverageValues());
        KlickAnalyser statistics = new KlickAnalyser(saveList);
        System.out.println(statistics.getSearchTermValues());

        statistics.getSearchTermValues().forEach((term, values) -> {
            List<Integer> relevantRanks = RelevanceAnalyser.getRelevantRanksForTerm(values, average.getAverageValues());
            if (relevantRanks.size() > 0) {
                System.out.print(term + ": ");
                relevantRanks.forEach(rank -> {
                    System.out.print(rank + ", ");
                });
                System.out.println();
            }
        });
    }
}
