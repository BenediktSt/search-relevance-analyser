package de.vawi.searchrelevanceanalyser;

import de.vawi.searchrelevanceanalyser.dao.CsvImporter;
import de.vawi.searchrelevanceanalyser.dao.RelevanceEntryRepository;
import de.vawi.searchrelevanceanalyser.processor.RelevanceEntryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
public class SearchRelevanceAnalyserApplication implements CommandLineRunner {

    @Autowired
    private RelevanceEntryRepository relevanceEntryRepository;

    public static void main(String[] args) {
        SpringApplication.run(SearchRelevanceAnalyserApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        CsvImporter importer = new CsvImporter();
        RelevanceEntryProcessor processor = new RelevanceEntryProcessor();
        importer.readData("sample-data.csv").forEach(entry -> {
            try {
                entry = processor.process(entry);
                int count = relevanceEntryRepository.getToalCount();
                entry.setId(count != 0 ? relevanceEntryRepository.findHighestId() + 1 : 0);
                relevanceEntryRepository.save(entry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
