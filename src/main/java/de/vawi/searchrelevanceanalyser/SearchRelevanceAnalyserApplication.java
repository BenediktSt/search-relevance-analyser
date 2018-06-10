package de.vawi.searchrelevanceanalyser;

import de.vawi.searchrelevanceanalyser.dao.CassandraSerializer;
import de.vawi.searchrelevanceanalyser.dao.CsvDeserializer;
import de.vawi.searchrelevanceanalyser.dao.RelevanceEntryRepository;
import de.vawi.searchrelevanceanalyser.model.RelevanceEntry;
import de.vawi.searchrelevanceanalyser.processor.RelevanceEntryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication()
public class SearchRelevanceAnalyserApplication implements CommandLineRunner {

    @Autowired
    private RelevanceEntryRepository relevanceEntryRepository;

    public static void main(String[] args) {
        SpringApplication.run(SearchRelevanceAnalyserApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        CsvDeserializer importer = new CsvDeserializer();
        RelevanceEntryProcessor processor = new RelevanceEntryProcessor();
        CassandraSerializer serializer = new CassandraSerializer(relevanceEntryRepository);

        List<RelevanceEntry> entryList = importer.readData("sample-data.csv");
        entryList.forEach(entry -> {
            try {
                entry = processor.process(entry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        serializer.serialize(entryList);

    }
}
