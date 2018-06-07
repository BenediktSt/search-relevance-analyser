package de.vawi.searchrelevanceanalyser;

import de.vawi.searchrelevanceanalyser.dao.RelevanceEntryRepository;
import de.vawi.searchrelevanceanalyser.model.RelevanceEntry;
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
        RelevanceEntry relevanceEntry = new RelevanceEntry("Suchbegriff", "Ergebnis", 1);
        relevanceEntry.setId(0);
        relevanceEntryRepository.save(relevanceEntry);
    }
}
